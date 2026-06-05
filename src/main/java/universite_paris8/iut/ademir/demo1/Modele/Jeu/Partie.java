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
    private ObservableList<Tour> tours;
    private ArrayList<Position> chemin;

    private int rubis;

    public Partie(ArrayList<Position> chemin) {
        this.chemin = chemin;
        this.monstres = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.rubis = 200;
    }

    public void ajouterZombie() {
        monstres.add(new Zombie(chemin));
    }

    public void mettreAJour() {
        faireAvancerMonstres();
        faireAttaquerTours();
        supprimerMonstresMorts();
    }

    private void faireAvancerMonstres() {
        for (Monstre monstre : monstres) {
            monstre.avancer();
        }
    }

    private void supprimerMonstresMorts() {
        monstres.removeIf(monstre -> {
            if (monstre.estMort()) {
                rubis += monstre.getRecompense();
                return true;
            }
            return false;
        });
    }

    public ObservableList<Monstre> getMonstres() {
        return monstres;
    }


    private void faireAttaquerTours() {
        for (Tour tour : tours) {
            tour.attaquer(monstres);
        }
    }



    //tours /placement des tours dans la liste observable/ajout
    public ObservableList<Tour> getTours() {
        return tours;
    }

    public boolean placerTour(Tour tour, Carte carte) {
        Position position = tour.getPosition();

        if (!carte.estCaseTour(position.getX(), position.getY())) {
            return false;
        }

        if (rubis < tour.getPrix()) {
            return false;
        }

        rubis -= tour.getPrix();
        tours.add(tour);

        return true;
    }




    public int getRubis() {
        return rubis;
    }
}