package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public abstract class Monstre {

    private int pv;
    private int vitesse;
    private int recompense;

    private ArrayList<Position> chemin;
    private int indiceChemin;

    private Position anciennePos;
    private long debutAnimation;


    public Monstre(int pv, int vitesse, int recompense, ArrayList<Position> chemin) {
        this.pv = pv;
        this.vitesse = vitesse;
        this.recompense = recompense;
        this.chemin = chemin;
        this.indiceChemin = 0;


    }

    public void avancer(long now) {
        if (!estArrive()) {
            anciennePos = getPosition();
            indiceChemin++;
            debutAnimation = now;
        }
    }

    public boolean estArrive() {
        return indiceChemin >= chemin.size() - 1;
    }

    public Position getPosition() {
        return chemin.get(indiceChemin);
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getRecompense() {
        return recompense;
    }

    public void recevoirDegats(int degats) {
        pv -= degats;

    }

    public Position getAnciennePosition () {
        return this.anciennePos;
    }


    public boolean estMort() {
        return pv <= 0;
    }
}

