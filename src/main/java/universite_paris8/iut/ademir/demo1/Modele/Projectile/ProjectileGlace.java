package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class ProjectileGlace extends Projectile{
    public ProjectileGlace(Position position, Monstre cible ){
        super(2, position, cible);
    }
}
