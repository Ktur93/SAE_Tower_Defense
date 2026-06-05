package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Boss extends Monstre {

    public Boss(ArrayList<Position> chemin) {
        super(500, 5, 100, chemin);
    }
}