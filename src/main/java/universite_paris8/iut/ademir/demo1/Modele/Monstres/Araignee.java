package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Araignee extends Monstre {

    public Araignee(ArrayList<Position> chemin) {
        super(60, 2, 8, chemin);
    }
}