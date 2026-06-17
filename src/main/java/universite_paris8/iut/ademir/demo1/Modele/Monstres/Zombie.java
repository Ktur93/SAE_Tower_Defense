package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Zombie extends Monstre {
    public Zombie(ArrayList<Position> chemin) {
        super(100, 4, 10,1, chemin);
    }
}