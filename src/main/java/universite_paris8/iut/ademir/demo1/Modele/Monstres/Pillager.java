package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Pillager extends Monstre {
    public Pillager(ArrayList<Position> chemin) {
        super(400, 5, 5,60,1, chemin);
    }
}