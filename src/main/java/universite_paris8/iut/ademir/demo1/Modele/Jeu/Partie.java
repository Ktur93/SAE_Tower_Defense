package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;

import java.util.ArrayList;

public class Partie {

    private final static int TAILLE_VAGUE = 5;

    private ObservableList<Tour> tours;
    private ObservableList<Monstre> monstres;
    private ObservableList<Projectile> projectiles;

    private ArrayList<Position> chemin;
    private ArrayList<Position> chemin2;
    private ArrayList<Position> chemin3;

    private ArrayList<Vague> vagues;


    private int rubis;
    private int pvPortail;
    private int prixCase;

    private int indiceVague;
    private IntegerProperty pvPortailIntegerProperty;

    private int prixAmelioration;
    private int compteur;
    private int compteurDefaite;

    private BooleanProperty vagueEnCours; //
    private BooleanProperty toutesLesVaguesTermine; //
    private BooleanProperty portailMort; //
    private BooleanProperty victoire;
    private BooleanProperty defaite;

    private boolean victoireBoucleLanceUneFois;
    private boolean defaiteBoucleLanceUneFois;


    public Partie(ArrayList<Position> chemin,ArrayList<Position> chemin2,ArrayList<Position> chemin3) {

        this.tours = FXCollections.observableArrayList();
        this.monstres = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();


        this.chemin = chemin;
        this.chemin2 = chemin2;
        this.chemin3 = chemin3;

        this.vagues = new ArrayList<>();


        this.rubis = 400;
        this.pvPortail = 10;
        this.prixCase = 25;

        this.indiceVague = 0;
        this.vagueEnCours = new SimpleBooleanProperty(false);
        this.pvPortailIntegerProperty = new SimpleIntegerProperty(3);

        this.prixAmelioration = 75;
        this.indiceVague = 0;
        this.compteur = 0;
        this.compteurDefaite = 0;

        this.vagueEnCours = new SimpleBooleanProperty(false);
        this.toutesLesVaguesTermine = new SimpleBooleanProperty(false);
        this.portailMort = new SimpleBooleanProperty(false);
        this.victoire = new SimpleBooleanProperty(false);
        this.defaite = new SimpleBooleanProperty(false);
        this.victoireBoucleLanceUneFois = false;
        this.defaiteBoucleLanceUneFois = false;

        creeVagues();
    }

    public ArrayList<Position> getChemin() {
        return chemin;
    }

    public int getPrixAmelioration() {
        return prixAmelioration;
    }

    public void mettreAJour() {
        // Faire avancer les modeles bougeables
        faireAvancerMonstres();
        faireAttaquerTours(compteur);
        supprimerMonstresMorts();
        projectilePourMettreAJour();




        // Verification de si la vague est en cours pour envoyer les monstres et sinon avancer la vague suivante
        if (this.vagueEnCours.get()) {
            Vague vagueActuelle = getVagues().get(indiceVague);
            vagueActuelle.mettreAJourVague(compteur, getMonstres());


            if (vagueActuelle.tousLesMonstresEnvoyes() && getMonstres().isEmpty()) {
                this.indiceVague++;
                this.prixAmelioration += 75;
                this.vagueEnCours.set(false);

            }
        }


        // partie Victoire
        if (getIndiceVague() > (getVagues().size() - 1) && !(victoireBoucleLanceUneFois) && !(portailMort())) {
            this.victoireBoucleLanceUneFois = true;
            this.victoire.set(true);
        }

        // partie defaite
        if (portailMort() && !defaiteBoucleLanceUneFois) {
            if (!(getVagueEnCours())) {
                defaite.set(true);
                this.compteurDefaite = compteur;
                defaiteBoucleLanceUneFois = true;
            }
        }

        if (!(portailMort())) {
            defaite.set(false);
        }
    }

    public void faireAvancerMonstres() {

        for (int i = 0 ; i < getMonstres().size() ; i++){
            Monstre monstre = getMonstres().get(i);
            monstre.avancer();
            if (monstre.estADestination()) {
                recevoirDegatPortail(monstre.getDegat());
                monstre.setRecompense(0);
                monstre.setPv(0);
            }
        }
    }

    public void supprimerMonstresMorts() {
        this.monstres.removeIf(monstre -> {
            if (monstre.estMort()) {
                this.rubis += monstre.getRecompense();
                return true;
            }
            return false;
        });
    }


    public void acheterCase(Position position, Carte carte) {
        if (rubis >= prixCase ) {
            if (carte.caseDebloquer(position)) {
                rubis -= prixCase;
                prixCase += 25;
            }
        }
    }


    public boolean placerTour(Tour tour, Carte carte) {
        if (tour == null) {
            return false;
        }

        Position position = tour.getPosition();

        if (!carte.estCaseTour(position.getX(), position.getY())) {
            return false;
        }

        if (this.rubis < tour.getPrix()) {
            return false;
        }

        this.rubis -= tour.getPrix();
        this.tours.add(tour);

        return true;
    }

    public void faireAttaquerTours(int compteur) {

        for (int i = 0; i < tours.size(); i++) {
            Tour tour = tours.get(i);
            Projectile projectile = tour.attaquer(monstres, compteur);
            if (projectile != null) {
                projectiles.add(projectile);
            }
        }
    }

    public void faireAmeliorerTours(Tour t){
        if (t.getNivT() < t.getNivMax() && rubis >= this.prixAmelioration) {
            t.ameliorer();
            rubis -= prixAmelioration;
            t.setNivT(t.getNivT() + 1);
        }
    }





    public void creeVagues() {
        Vague vague1 = new Vague();
        vague1.creeVague1(chemin);

        Vague vague2 = new Vague();
        vague2.creeVague2(chemin, chemin2);

        Vague vague3 = new Vague();
        vague3.creeVague3(chemin, chemin2);

        Vague vague4 = new Vague();
        vague4.creeVague4(chemin, chemin2, chemin3);

        Vague vague5 = new Vague();
        vague5.creeVague5(chemin, chemin2, chemin3);

        getVagues().add(vague1);
        getVagues().add(vague2);
        getVagues().add(vague3);
        getVagues().add(vague4);
        getVagues().add(vague5);

    }

    public void lancerProchaineVague() {
        if (this.vagueEnCours.get()) {

        } else if (indiceVague >= vagues.size()) {

        } else {
            this.vagueEnCours.set(true);
        }
    }

    public void recommnencer() {
        setIndiceVaguePlusUn(0);
        this.rubis = 400;
        this.prixCase = 25;
        pvPortailIntegerProperty.set(3);
        this.defaiteBoucleLanceUneFois = false;
        this.prixAmelioration = 75;

        // recreation des vagues
        vagues.get(0).creeVague1(this.chemin);

        vagues.get(1).creeVague2(
                this.chemin,
                this.chemin2
        );

        vagues.get(2).creeVague3(
                this.chemin,
                this.chemin2
        );

        vagues.get(3).creeVague4(
                this.chemin,
                this.chemin2,
                this.chemin3
        );

        vagues.get(4).creeVague5(
                this.chemin,
                this.chemin2,
                this.chemin3
        );

        for (int i = 0; i < TAILLE_VAGUE;i++){
            vagues.get(i).setIndiceMonstreZero();
        }

        tours.clear();
    }

    public void projectilePourMettreAJour(){
        int i = 0;

        while (i < projectiles.size()) {
            Projectile projectile = projectiles.get(i);
            if (projectile.avancer()) {
                projectiles.remove(i);
                System.out.println("supp");
            } else {
                i++;
            }
        }
    }




    public int getRubis() {
        return this.rubis;
    }

    public ObservableList<Monstre> getMonstres() {
        return monstres;
    }

    public ObservableList<Projectile> getProjectiles(){
        return projectiles;
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public int getIndiceVague () {
        return this.indiceVague;
    }

    public int getIndiceVaguePlusUn () {
        return indiceVague + 1;
    }
    public void setIndiceVaguePlusUn(int i){
        indiceVague = i;
    }

    public boolean getToutesLesVaguesTerminees() {
        return this.getIndiceVague() >= this.vagues.size();
    }

    public ArrayList<Vague> getVagues () {
        return this.vagues;
    }

    public boolean getVagueEnCours () {
        return this.vagueEnCours.get();
    }

    public int getPrixCase() {
        return this.prixCase;
    }


    public int getCompteur() {
        return this.compteur;
    }

    public IntegerProperty pvPortailIntegerPropertyProperty(){
        return pvPortailIntegerProperty;
    }

    public int getPvPortailIntegerProperty() {
        return pvPortailIntegerProperty.get();
    }

    public int getCompteurDefaite() {
        return this.compteurDefaite;
    }




    public void setDefaiteProperty (boolean defaite) {
        this.defaite.set(defaite);
    }

    public void setCompteurPlusPlus() {
        this.compteur++;
    }

    public void recevoirDegatPortail(int nbDegat) {
        pvPortailIntegerProperty.set(pvPortailIntegerProperty.get() - nbDegat);
    }

    public boolean portailMort() {
        return pvPortailIntegerProperty.get() <= 0;
    }


    public BooleanProperty vagueEnCoursProperty() {
        return vagueEnCours;
    }

    public BooleanProperty toutesLesVaguesTermineProperty() {
        return toutesLesVaguesTermine;
    }

    public BooleanProperty portailMortProperty() {
        return portailMort;
    }

    public BooleanProperty defaiteProperty() {
        return defaite;
    }

    public BooleanProperty victoireProperty() {
        return victoire;
    }




    public void setIndiceVague (int i) {
        this.indiceVague = i;
    }

    public int getPvPortail() {
        return this.pvPortail;
    }



}