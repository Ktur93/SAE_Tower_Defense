package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Vue.CarteVue;
import universite_paris8.iut.ademir.demo1.Vue.RubisVue;


import java.util.ArrayList;

public class Partie {

    private final static int TAILLE_VAGUE = 5;

    private int rubis;
    private ObservableList<Tour> tours;
    private ObservableList<Monstre> monstres;
    private ObservableList<Projectile> projectiles;
    private ArrayList<Position> chemin;
    private ArrayList<Position> chemin2;
    private ArrayList<Position> chemin3;
    private ArrayList<Vague> vagues;
    private int indiceVague;
    private int prixCase;
    private IntegerProperty pvPortailIntegerProperty;
    private int prixAmelioration;
    private BooleanProperty vagueEnCours; //
    private BooleanProperty toutesLesVaguesTermine; //
    private BooleanProperty portailMort; //
    private BooleanProperty victoire;
    private BooleanProperty defaite;
    private int compteur;
    private boolean victoireBoucleLanceUneFois;
    private int compteurDefaite;
    private boolean defaiteBoucleLanceUneFois;


    public Partie(ArrayList<Position> chemin,ArrayList<Position> chemin2,ArrayList<Position> chemin3) {
        this.rubis = 10000;
        this.tours = FXCollections.observableArrayList();
        this.monstres = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.chemin = chemin;
        this.chemin2 = chemin2;
        this.chemin3 = chemin3;
        this.vagues = new ArrayList<>();
        this.indiceVague = 0;
        this.vagueEnCours = new SimpleBooleanProperty(false);
        this.prixCase = 50;
        this.pvPortailIntegerProperty = new SimpleIntegerProperty(3);
        this.prixAmelioration = 50;
        this.toutesLesVaguesTermine = new SimpleBooleanProperty(false);
        this.portailMort = new SimpleBooleanProperty(false);
        this.victoire = new SimpleBooleanProperty(false);
        this.victoireBoucleLanceUneFois = false;
        this.defaite = new SimpleBooleanProperty(false);
        this.compteur = 0;
        this.compteurDefaite = 0;
        this.defaiteBoucleLanceUneFois = false;
        Vagues();
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
        if (rubis > prixCase ) {
            if (carte.caseDebloquer(position)) {
                rubis -= prixCase;
                prixCase += 50;
            }
        }
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public boolean placerTour(Tour tour, Carte carte) {
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
        if (t.getNivT() < t.getNivMax() && rubis >= 50) {
            t.ameliorer();
            rubis -= prixAmelioration;
            t.setNivT(t.getNivT() + 1);
        }
    }

    public boolean getVagueEnCours () {
        return this.vagueEnCours.get();
    }

    public int getIndiceVague () {
        return this.indiceVague;
    }

    public int getIndiceVaguePlusUn () {
        return indiceVague + 1;
    }

    public boolean toutesLesVaguesTerminees () {
        return this.getIndiceVague() >= this.vagues.size();
    }

    public ArrayList<Vague> getVagues () {
        return this.vagues;
    }

    public void Vagues() {
        Vague vague1 = new Vague();
        vague1.creeVague1(chemin);

        Vague vague2 = new Vague();
        vague2.creeVague2(chemin2);

        Vague vague3 = new Vague();
        vague3.creeVague3(chemin3);

        Vague vague4 = new Vague();
        vague4.creeVague4(chemin);

        Vague vague5 = new Vague();
        vague5.creeVague5(chemin3);

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
        this.indiceVague = 0;
        this.rubis = 2000;
        this.prixCase = 50;
        pvPortailIntegerProperty.set(3);

        // recreation des vagues
        vagues.get(0).creeVague1(this.chemin);
        vagues.get(1).creeVague2(this.chemin2);
        vagues.get(2).creeVague3(this.chemin3);
        vagues.get(3).creeVague4(this.chemin);
        vagues.get(4).creeVague5(this.chemin3);

        for (int i = 0; i < TAILLE_VAGUE;i++){
            vagues.get(i).setIndiceMonstreZero();
        }

        tours.clear();
    }


    public void mettreAJour(CarteVue carteVue, RubisVue rubisVue , Button button) {
        // Faire avancer les modeles bougeables
        faireAvancerMonstres();
        faireAttaquerTours(compteur);
        supprimerMonstresMorts();
        projectilePourMettreAJour();
        rubisVue.afficherRubis();
        button.setText("Acheter case - " + getPrixCase());



        // Verification de si la vague est en cours pour envoyer les monstres et sinon avancer la vague suivante
        if (this.vagueEnCours.get()) {
            Vague vagueActuelle = getVagues().get(indiceVague);
            vagueActuelle.mettreAJourVague(compteur, getMonstres());


            if (vagueActuelle.tousLesMonstresEnvoyes() && getMonstres().isEmpty()) {
                this.indiceVague++;
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



        if ((compteur - compteurDefaite) > 300 && defaite.get()) {

            defaite.set(false);
            defaiteBoucleLanceUneFois = false;
        } else {
            carteVue.timerRecommencer(compteur - compteurDefaite);
        }

        compteur++;
    }

    public int getPrixCase() {
        return this.prixCase;
    }

    public IntegerProperty pvPortailIntegerPropertyProperty(){
        return pvPortailIntegerProperty;
    }

    public int getPvPortailIntegerProperty() {
        return pvPortailIntegerProperty.get();
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

    public void projectilePourMettreAJour(){

        int i = 0;

        while (i < projectiles.size()) {

            Projectile projectile = projectiles.get(i);

            if (projectile.toucher()) {
                projectiles.remove(i);
                System.out.println("supp");
            } else {
                i++;
            }
        }
    }


}