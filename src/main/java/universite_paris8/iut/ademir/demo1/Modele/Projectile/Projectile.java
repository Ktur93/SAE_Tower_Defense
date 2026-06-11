package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class  Projectile {
    private int vitesse;
    private Position pos;
    private ObservableList<Monstre> cible;
    private boolean touche;

    public Projectile(int vit, Position pos, ObservableList<Monstre> cible){
        this.vitesse = vit;
        this.pos = pos;
        this.cible = cible;
        this.touche = false;
    }

    public int getVitesse() {
        return vitesse;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }


    public boolean tir() {

        int i = 0;
        while(i < cible.size()){
            // Si le projectile n'a plus de cible,
            // ou si la cible est déjà morte, le projectile disparaît.
            if (cible == null || cible.get(i).estMort()) {
                touche = true;
                return true;
            }

            int x = pos.getX() - cible.get(i).getPosition().getX();
            int y = pos.getY() - cible.get(i).getPosition().getY();

            int distanceCarree = x * x + y * y;

            // Si le projectile est assez proche de la cible,
            // on considère qu'il l'a touchée.
            if (distanceCarree <= vitesse * vitesse) {
                touche = true;
                return true;
            }

            int nouvX = pos.getX();
            int nouvY = pos.getY();

            // x > 0 : le projectile est à droite de la cible,
            // donc il doit aller vers la gauche.
            if (x > 0) {
                nouvX -= vitesse;
            } else if (x < 0) {
                nouvX += vitesse;
            }

            // y > 0 : le projectile est en dessous de la cible,
            // donc il doit monter.
            if (y > 0) {
                nouvY -= vitesse;
            } else if (y < 0) {
                nouvY += vitesse;
            }

            this.pos = new Position(nouvX, nouvY);

            i++;
        }

        return false;
    }
}
