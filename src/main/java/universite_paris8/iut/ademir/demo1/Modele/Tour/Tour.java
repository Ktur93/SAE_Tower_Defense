package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import java.util.ArrayList;

public abstract class Tour {

    private int atk;
    private int prix;
    private int portee;
    private Position pos;

    public Tour(int attaque, int prix, int portee, Position position) {
        this.atk = attaque;
        this.prix = prix;
        this.portee = portee;
        this.pos = position;
    }

    public int getPrix() {
        return prix;
    }

    public Position getPosition() {
        return pos;
    }

    public void attaquer(ObservableList<Monstre> monstres) {
        for (Monstre m : monstres) {
            if (estAPortee(m)) {
                m.recevoirDegats(atk);
                break;
            }
        }
    }

    private boolean estAPortee(Monstre monstre) {
        int dx = pos.getX() - monstre.getPosition().getX();
        int dy = pos.getY() - monstre.getPosition().getY();

        return Math.sqrt(dx * dx + dy * dy) <= portee;
    }
}
