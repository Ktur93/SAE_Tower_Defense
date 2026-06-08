package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;
import java.util.ArrayList;

public class Partie {

    private final static int TAILLE_VAGUE = 5;

    private int rubis;
    private ObservableList<Tour> tours;
    private ObservableList<Monstre> monstres;
    private ArrayList<Position> chemin;
    private ArrayList<Vague> vagues;
    private int indiceVague;
    private boolean vagueEnCours;
    private int prixCase;


    public Partie(ArrayList<Position> chemin) {
        this.rubis = 200;
        this.tours = FXCollections.observableArrayList();
        this.monstres = FXCollections.observableArrayList();
        this.chemin = chemin;
        this.vagues = new ArrayList<>();
        this.indiceVague = 0;
        this.vagueEnCours = false;
        this.prixCase = 50;

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



    public boolean getVagueEnCours () {
        return this.vagueEnCours;
    }

    public int getIndiceVague () {
        return this.indiceVague;
    }

    public int getIndiceVaguePlusUn () {
        return (this.indiceVague + 1);
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
        vague2.creeVague2(chemin);

        Vague vague3 = new Vague();
        vague3.creeVague3(chemin);

        Vague vague4 = new Vague();
        vague4.creeVague4(chemin);

        Vague vague5 = new Vague();
        vague5.creeVague5(chemin);

        getVagues().add(vague1);
        getVagues().add(vague2);
        getVagues().add(vague3);
        getVagues().add(vague4);
        getVagues().add(vague5);

    }

    public void lancerProchaineVague() {
        if (this.vagueEnCours) {

        } else if (indiceVague >= vagues.size()) {

        } else {
            this.vagueEnCours = true;
        }
    }


    public void recommnencer() {
        this.indiceVague = 0;
        this.rubis = 200;
        this.prixCase = 50;

        // recreation des vagues
        vagues.get(0).creeVague1(this.chemin);
        vagues.get(1).creeVague2(this.chemin);
        vagues.get(2).creeVague3(this.chemin);
        vagues.get(3).creeVague4(this.chemin);
        vagues.get(4).creeVague5(this.chemin);




        for (int i = 0; i < TAILLE_VAGUE;i++){
            vagues.get(i).setIndiceMonstreZero();
        }

        for(int i = 0 ; i < tours.size() ; i++){
            tours.remove(i);
        }
    }


    public void mettreAJour(long tempActuel) {
        faireAvancerMonstres();
        faireAttaquerTours(tempActuel);
        supprimerMonstresMorts();

        if (this.vagueEnCours == true) {
            Vague vagueActuelle = getVagues().get(indiceVague);
            vagueActuelle.mettreAJourVague(tempActuel, getMonstres());

            if (vagueActuelle.tousLesMonstresEnvoyes() && getMonstres().isEmpty()) {
                this.vagueEnCours = false;
                this.indiceVague++;
            }
        }
    }

    public int getPrixCase() {
        return this.prixCase;
    }
}