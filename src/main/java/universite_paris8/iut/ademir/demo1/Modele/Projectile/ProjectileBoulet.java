package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class ProjectileBoulet extends Projectile {
    public ProjectileBoulet(double x, double y, Monstre cible) {
        super(x, y, 20, cible);
    }
}