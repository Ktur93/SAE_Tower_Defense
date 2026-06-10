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
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourCanon;
import universite_paris8.iut.ademir.demo1.Vue.RubisVue;

import java.util.ArrayList;

public class Partie {

    private final static int TAILLE_VAGUE = 5;

    private int rubis;
    private ObservableList<Tour> tours;
    private ObservableList<Monstre> monstres;
    private ArrayList<Position> chemin;
    private ArrayList<Position> chemin2;
    private ArrayList<Position> chemin3;
    private ArrayList<Vague> vagues;
    private IntegerProperty indiceVague;
    private BooleanProperty vagueEnCours;
    private int prixCase;
    private int pvPortail;
    private int prixAmelioration;
    private BooleanProperty toutesLesVaguesTermine;


    public Partie(ArrayList<Position> chemin,ArrayList<Position> chemin2,ArrayList<Position> chemin3) {
        this.rubis = 2000;
        this.tours = FXCollections.observableArrayList();
        this.monstres = FXCollections.observableArrayList();
        this.chemin = chemin;
        this.chemin2 = chemin2;
        this.chemin3 = chemin3;
        this.vagues = new ArrayList<>();
        this.indiceVague = new SimpleIntegerProperty(0);
        this.vagueEnCours = new SimpleBooleanProperty(false);
        this.prixCase = 50;
        this.pvPortail = 10;
        this.prixAmelioration = 50;
        this.toutesLesVaguesTermine = new SimpleBooleanProperty(false);

        Vagues();
    }

    public int getRubis() {
        return this.rubis;
    }

    public ObservableList<Monstre> getMonstres() {
        return monstres;
    }

    private void faireAvancerMonstres() {

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

    private void supprimerMonstresMorts() {
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

    public void faireAttaquerTours(long tempsActuel) {
        for (Tour tour : tours) {
            tour.attaquer(monstres,tempsActuel);
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
        return this.indiceVague.get();
    }

    public int getIndiceVaguePlusUn () {
        return (this.indiceVague.get() + 1);
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

        } else if (indiceVague.get() >= vagues.size()) {

        } else {
            this.vagueEnCours.set(true);
        }
    }

    public void recommnencer() {
        this.indiceVague.set(0);
        this.rubis = 2000;
        this.prixCase = 50;
        this.pvPortail = 10;

        // recreation des vagues
        vagues.get(0).creeVague1(this.chemin);
        vagues.get(1).creeVague2(this.chemin2);
        vagues.get(2).creeVague3(this.chemin3);
        vagues.get(3).creeVague4(this.chemin);
        vagues.get(4).creeVague5(this.chemin3);




        for (int i = 0; i < TAILLE_VAGUE;i++){
            vagues.get(i).setIndiceMonstreZero();
        }

        for(int i = 0 ; i < tours.size() ; i++){
            tours.remove(i);
        }
    }

    public void mettreAJour(long tempActuel, RubisVue rubisVue) {
        // Faire avancer les modeles bougeables
        faireAvancerMonstres();
        faireAttaquerTours(tempActuel);
        supprimerMonstresMorts();

        // Verification de si la vague est en cours pour envoyer les monstres et sinon avancer la vague suivante
        if (this.vagueEnCours.get()) {
            Vague vagueActuelle = getVagues().get(indiceVague.get());
            vagueActuelle.mettreAJourVague(tempActuel, getMonstres());

            if (vagueActuelle.tousLesMonstresEnvoyes() && getMonstres().isEmpty()) {
                this.vagueEnCours.set(false);
                this.indiceVague.set(this.indiceVague.get() + 1);
            }
        }

        rubisVue.afficherRubis();












    }

    public int getPrixCase() {
        return this.prixCase;
    }

    public int getPvPortail() {
        return this.pvPortail;
    }

    public void recevoirDegatPortail(int nbDegat) {
        this.pvPortail -= nbDegat;
    }

    public boolean portailMort() {
        return this.pvPortail <= 0;
    }


    public BooleanProperty vagueEnCoursProperty() {
        return vagueEnCours;
    }

    public BooleanProperty toutesLesVaguesTermineProperty() {
        if (this.getIndiceVague() >= this.vagues.size()) {
            this.toutesLesVaguesTermine.set(true);
        }
        return toutesLesVaguesTermine;
    }
}