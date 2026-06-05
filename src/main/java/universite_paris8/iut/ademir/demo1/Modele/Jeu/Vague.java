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

//    private ArrayList<Monstre> monstresVague1;
//    private ArrayList<Monstre> monstresVague2;
//    private ArrayList<Monstre> monstresVague3;
//    private ArrayList<Monstre> monstresVague4;
//    private ArrayList<Monstre> monstresVague5;

    public Vague () {
        this.monstresVague = new ArrayList<>();

        this.indiceMonstre = 0;
        this.dernierSpawn = 0;
        this.delaiSpawn = 2_000_000_000L; // 2 secondes

//        this.monstresVague2 = new ArrayList<>();
//        this.monstresVague3 = new ArrayList<>();
//        this.monstresVague4 = new ArrayList<>();
//        this.monstresVague5 = new ArrayList<>();


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

    public ArrayList<Monstre> getMonstresVague() {
        return monstresVague;
    }

    public void mettreAJourVague(long now, ObservableList<Monstre> monstresPartie) {

        if (indiceMonstre >= monstresVague.size()) {

        } else if (indiceMonstre == 0 || now - dernierSpawn >= delaiSpawn) {
            monstresPartie.add(monstresVague.get(indiceMonstre));
            indiceMonstre++;
            dernierSpawn = now;
        }
    }

    public boolean tousLesMonstresEnvoyes() {
        return indiceMonstre >= monstresVague.size();
    }




}
