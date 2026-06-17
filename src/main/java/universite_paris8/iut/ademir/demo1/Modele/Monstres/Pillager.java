package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Pillager extends Monstre {
    public Pillager(ArrayList<Position> chemin) {
        super(250, 5, 5,25,1, chemin);
    }
}