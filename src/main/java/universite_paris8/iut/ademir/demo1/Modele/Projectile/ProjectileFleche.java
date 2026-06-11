package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class ProjectileFleche extends Projectile{
    public ProjectileFleche(Position position, ObservableList<Monstre> cible ){
        super(2, position, cible );
    }
}
