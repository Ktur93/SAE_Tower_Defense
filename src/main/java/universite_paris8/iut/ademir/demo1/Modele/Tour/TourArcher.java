package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileBoulet;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileFleche;

public class TourArcher extends Tour{

    public TourArcher(Position position){
        super(30, 50, 5, position,50 , 5); // 1 seconde
    }

    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 10);
        this.setPorter(this.getPorter() + 1);
        this.setCadence(this.getCadence() - 2);
    }

    @Override
    public Projectile creerProjectile(Monstre cible) {
        double x = getPosition().getX() * 64;
        double y = getPosition().getY() * 64;
        return new ProjectileFleche(x, y, getAtk(), cible);
    }
}
