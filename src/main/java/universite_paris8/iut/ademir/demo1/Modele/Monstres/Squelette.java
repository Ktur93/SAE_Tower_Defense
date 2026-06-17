package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Squelette extends Monstre {
    public Squelette(ArrayList<Position> chemin) {
        super(300, 5, 5,40,1, chemin);
    }
}