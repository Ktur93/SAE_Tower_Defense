package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class  Projectile {
    private int vitesse;
    private Position pos;
    private Monstre cible;
    private boolean touche;

    public Projectile(int vit, Position pos, Monstre cible){
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

    public Monstre getCible() {
        return cible;
    }

    public void estTouche(){
        touche = true;
    }

    public void tir(){
        if(cible == null || cible.estMort()) {
            touche = true;
            return;
        }
        int x = pos.getX() - cible.getPosition().getX();
        int y = pos.getY() - cible.getPosition().getY();

        int distanceCarree = x * x + y * y;

        if(distanceCarree <= vitesse * vitesse){
            touche = true;
        }

        int nouvX = pos.getX();
        int nouvY = pos.getY();

        if(x > 0){
            nouvX -= vitesse;
        } else if (x < 0) {
            nouvX += vitesse;
        }
        if(y > 0){
            nouvY -= vitesse;
        } else if (y < 0){
            nouvY += vitesse;
        }

        pos = new Position(nouvX, nouvY);


    }
}
