package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileBoulet;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileGlace;

public class TourGlace extends Tour{

    public TourGlace(Position position){
        super(1, 100, 3, position,120 , 3);
    }
    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 10);
        this.setPorter(this.getPorter() + 10);
        this.setCadence(this.getCadence() + 10);
        this.setPrix(this.getPrix() + 10);
    }

    @Override
    public Projectile creerProjectile(Monstre cible) {
        double x = getPosition().getX() * 64;
        double y = getPosition().getY() * 64;
        return new ProjectileGlace(x, y, cible);
    }

    @Override
    public void infligerDegat(Monstre cible){
        cible.recevoirDegatsGlace(getAtk());
    }
}
