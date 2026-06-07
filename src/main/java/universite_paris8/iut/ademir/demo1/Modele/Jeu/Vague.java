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
        this.delaiSpawn = 2_000_000_000L; // 2 seconde
    }


    public void creeVague1(ArrayList<Position> chemin) {
        this.monstresVague.clear();
        this.monstresVague.add(new Zombie(chemin));
        this.monstresVague.add(new Zombie(chemin));
        this.monstresVague.add(new Zombie(chemin));
    }

    public void creeVague2(ArrayList<Position> chemin) {
        this.monstresVague.clear();
        creeVague1(chemin);
        this.monstresVague.add(new Araignee(chemin));
        this.monstresVague.add(new Araignee(chemin));
    }

    public void creeVague3(ArrayList<Position> chemin) {
        this.monstresVague.clear();
        creeVague2(chemin);
        this.monstresVague.add(new Squelette(chemin));
        this.monstresVague.add(new Squelette(chemin));
    }

    public void creeVague4(ArrayList<Position> chemin) {
        this.monstresVague.clear();
        creeVague3(chemin);
        this.monstresVague.add(new Pillager(chemin));
        this.monstresVague.add(new Pillager(chemin));
    }

    public void creeVague5 (ArrayList<Position> chemin ) {
        this.monstresVague.clear();
        creeVague4(chemin);
        this.monstresVague.add(new Boss(chemin));
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
}
