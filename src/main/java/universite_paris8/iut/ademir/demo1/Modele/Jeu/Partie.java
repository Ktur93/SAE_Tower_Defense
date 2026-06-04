package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

import java.util.ArrayList;

public class Partie {

    private ObservableList<Monstre> monstres;
    private ArrayList<Tour> tours;
    private ArrayList<Position> chemin;
    private int rubis;

    public Partie(ArrayList<Position> chemin) {
        this.chemin = chemin;
        this.monstres = FXCollections.observableArrayList();
        this.tours = new ArrayList<>();
        this.rubis = 200;
    }

    public void ajouterZombie() {
        this.monstres.add(new Zombie(this.chemin));
    }

    public void mettreAJour() {
        faireAvancerMonstres();
        faireAttaquerTours();
        supprimerMonstresMorts();
    }

    private void faireAvancerMonstres() {
        for (Monstre monstre : this.monstres) {
            monstre.avancer();
        }
    }

    private void faireAttaquerTours() {
        for (Tour tour : this.tours) {
            tour.attaquer(this.monstres);
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

    public boolean placerTour(Tour tour, Carte carte) {
        Position position = tour.getPosition();

        if (!carte.estCaseTour(position.getColonne(), position.getLigne())) {
            return false;
        }

        if (this.rubis < tour.getPrix()) {
            return false;
        }

        this.rubis -= tour.getPrix();
        this.tours.add(tour);

        return true;
    }

    public ObservableList<Monstre> getMonstres() {
        return this.monstres;
    }

    public ArrayList<Tour> getTours() {
        return this.tours;
    }

    public int getRubis() {
        return this.rubis;
    }
}