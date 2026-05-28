package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

import java.util.ArrayList;
import java.util.Iterator;

public class Partie {

    private ArrayList<Monstre> monstres;
    private ArrayList<Tour> tours;
    private ArrayList<Position> chemin;

    private int rubis;

    public Partie(ArrayList<Position> chemin) {
        this.chemin = chemin;
        this.monstres = new ArrayList<>();
        this.tours = new ArrayList<>();
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

    private void faireAttaquerTours() {
        for (Tour tour : tours) {
            tour.attaquer(monstres);
        }
    }

    private void supprimerMonstresMorts() {
        Iterator<Monstre> it = monstres.iterator();

        while (it.hasNext()) {
            Monstre monstre = it.next();

            if (monstre.estMort()) {
                rubis += monstre.getRecompense();
                it.remove();
            }
        }
    }

    public boolean placerTour(Tour tour, Carte carte) {
        Position position = tour.getPosition();

        if (!carte.estCaseTour(position.getColonne(), position.getLigne())) {
            return false;
        }

        if (rubis < tour.getPrix()) {
            return false;
        }

        rubis -= tour.getPrix();
        tours.add(tour);

        return true;
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