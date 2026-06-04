package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public abstract class Monstre {

    private int pv;
    private int vitesse;
    private int recompense;
    private ArrayList<Position> chemin;
    private int indiceChemin;

    public Monstre(int pv, int vitesse, int recompense, ArrayList<Position> chemin) {
        this.pv = pv;
        this.vitesse = vitesse;
        this.recompense = recompense;
        this.chemin = chemin;
        this.indiceChemin = 0;
    }

    public void avancer() {
        if (!estArrive()) {
            indiceChemin++;
        }
    }

    public boolean estArrive() {
        return this.indiceChemin >= this.chemin.size() - 1;
    }

    public Position getPosition() {
        return this.chemin.get(this.indiceChemin);

    }

    public int getPv() {
        return this.pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public int getRecompense() {
        return this.recompense;
    }

    public void recevoirDegats(int degats) {
        this.pv -= degats;

    }

    public boolean estMort() {
        return this.pv <= 0;
    }
}

