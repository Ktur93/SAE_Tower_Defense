package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileBoulet;

public class TourCanon extends Tour {

    public TourCanon(Position position) {
        super(45, 125, 4, position,75 , 4);
    }

    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 8);
        this.setPorter(this.getPorter() + 1);
        this.setCadence(this.getCadence() - 3);
    }

    public Projectile creerProjectile(Monstre cible) {
        double x = getPosition().getX() * 64;
        double y = getPosition().getY() * 64;
        return new ProjectileBoulet(x, y, getAtk(),cible);
    }
}