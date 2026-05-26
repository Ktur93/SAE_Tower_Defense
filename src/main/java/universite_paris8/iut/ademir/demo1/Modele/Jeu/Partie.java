package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

import java.util.ArrayList;

public class Partie {

    private ArrayList<Monstre> monstres;
    private ArrayList<Position> chemin;
    private ArrayList<Tour> tours;

    private int rubis;

    public Partie(ArrayList<Position> chemin) {

        this.monstres = new ArrayList<>();
        this.chemin = chemin;

        this.tours = new ArrayList<>();

        this.rubis = 200;
    }

    public void ajouterZombie() {
        monstres.add(new Zombie(chemin));
    }

    public void mettreAJour() {

        for (Monstre m : monstres) {
            m.avancer();
        }

        for (Tour t : tours) {
            t.attaquer(monstres);
        }

        monstres.removeIf(monstre -> {

            if (monstre.estMort()) {

                rubis += monstre.getRecompense();
                return true;
            }

            return false;
        });
    }

    public boolean placerTour(Tour tour, Carte carte) {

        Position position = tour.getPosition();

        if (!carte.estCaseTour(
                position.getColonne(),
                position.getLigne()
        )) {
            return false;
        }

        if (rubis >= tour.getPrix()) {

            rubis -= tour.getPrix();

            tours.add(tour);

            return true;
        }

        return false;
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public int getRubis() {
        return rubis;
    }
}