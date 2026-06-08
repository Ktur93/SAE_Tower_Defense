package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public class TourPoison extends Tour{
    public TourPoison(Position position){
        super(25, 100, 3, position,500_000_000L ); // 0,5 seconde

    }
}
