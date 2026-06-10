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
        if(cible.estMort()) {
            touche = true;
            return;
        }
        int x = cible.getPosition().getX() - pos.getX();
        int y = cible.getPosition().getY() - pos.getY();

        if(x > 0){

        }

    }
}
