package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public class  Projectile {
    private int vitesse;
    private Position pos;

    public Projectile(int vit, Position pos){
        this.vitesse = vit;
        this.pos = pos;
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



}
