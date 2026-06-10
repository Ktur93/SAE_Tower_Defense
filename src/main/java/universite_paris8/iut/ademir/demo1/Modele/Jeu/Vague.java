package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;

import java.util.ArrayList;

public class Vague {

    private ArrayList<Monstre> monstresVague;
    private int indiceMonstre;
    private long dernierSpawn;
    private long delaiSpawn;


    public Vague () {
        this.monstresVague = new ArrayList<>();
        this.indiceMonstre = 0;
        this.dernierSpawn = 0;
        this.delaiSpawn = 500_000_000L; // 2 seconde
    }


    public void creeVague1(ArrayList<Position> chemin) {
        this.monstresVague.clear();
        for (int i = 0; i < 1; i++) {
            monstresVague.add(new Zombie(chemin));
        }
    }

    public void creeVague2(ArrayList<Position> chemin) {
        this.monstresVague.clear();
        creeVague1(chemin);
        for (int i = 0; i < 1; i++) {
            this.monstresVague.add(new Araignee(chemin));
        }
    }

    public void creeVague3(ArrayList<Position> chemin) {
        this.monstresVague.clear();
        creeVague2(chemin);
        for (int i = 0; i < 1; i++) {
            this.monstresVague.add(new Squelette(chemin));
        }
    }

    public void creeVague4(ArrayList<Position> chemin) {
        this.monstresVague.clear();
        creeVague3(chemin);
        for (int i = 0; i < 1; i++) {
        this.monstresVague.add(new Pillager(chemin));
        }
    }

    public void creeVague5 (ArrayList<Position> chemin ) {
        this.monstresVague.clear();
        creeVague4(chemin);
        for (int i = 0; i < 1; i++) {
            this.monstresVague.add(new Boss(chemin));
        }
    }

    public void mettreAJourVague(long tempsActuelle, ObservableList<Monstre> monstresPartie) {
        if (indiceMonstre >= monstresVague.size()) {

        } else if (indiceMonstre == 0 || tempsActuelle - dernierSpawn >= delaiSpawn) {
            monstresPartie.add(monstresVague.get(indiceMonstre));
            indiceMonstre++;
            dernierSpawn = tempsActuelle;
        }
    }

    public boolean tousLesMonstresEnvoyes() {
        return indiceMonstre >= monstresVague.size();
    }

    public void setIndiceMonstreZero() {
        this.indiceMonstre = 0;
    }
}