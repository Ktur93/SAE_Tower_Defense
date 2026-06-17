package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileBoulet;

public class TourCanon extends Tour {

    public TourCanon(Position position) {
        super(35, 125, 4, position,50 , 4);
    }

    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 10);
        this.setPorter(this.getPorter() + 10);
        this.setCadence(this.getCadence() + 10);
        this.setPrix(this.getPrix() + 10);
    }

    public Projectile creerProjectile(Monstre cible) {
        double x = getPosition().getX() * 64;
        double y = getPosition().getY() * 64;
        return new ProjectileBoulet(x, y, getAtk(),cible);
    }
}