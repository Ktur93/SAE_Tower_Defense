package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;

import java.util.ArrayList;

public class Partie {

    private ArrayList<Monstre> monstres;

    public Partie() {
        monstres = new ArrayList<>();
    }

    public void ajouterZombie() {
        monstres.add(new Zombie());
    }

    public void MettreAJour() {
        for (Monstre m : monstres) {
            m.avancer();
        }
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }
}