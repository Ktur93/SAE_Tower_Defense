package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Squelette extends Monstre {

    public Squelette(ArrayList<Position> chemin) {
        super(90, 1, 15, chemin);

    }
}