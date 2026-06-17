package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileBoulet;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileGlace;

public class TourGlace extends Tour{

    public TourGlace(Position position){
        super(30, 150, 3, position,50 , 3);
    }

    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 5);
        this.setPorter(this.getPorter() + 1);
        this.setCadence(this.getCadence() - 5);
    }

    @Override
    public Projectile creerProjectile(Monstre cible) {
        double x = getPosition().getX() * 64;
        double y = getPosition().getY() * 64;
        return new ProjectileGlace(x, y, getAtk(),cible);
    }
}
