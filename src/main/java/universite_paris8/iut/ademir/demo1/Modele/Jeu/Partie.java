package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;

import java.util.ArrayList;

public class Partie {

    private ArrayList<Monstre> monstres;
    private ArrayList<Position> chemin;

    public Partie(ArrayList<Position> chemin) {
        this.monstres = new ArrayList<>();
        this.chemin = chemin;
    }

    public void ajouterZombie() {
        monstres.add(new Zombie(chemin));
    }

    public void mettreAJour() {
        for (Monstre m : monstres) {
            m.avancer();
        }
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }
}