package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public class TourArcher extends Tour{
    public TourArcher(Position position){
        super(25, 75, 4, position,500_000_000L); // 1 seconde
    }
    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 10);
        this.setPorter(this.getPorter() + 10);
        this.setCadence(this.getCadence() + 10);
        this.setPrix(this.getPrix() + 10);
    }
}
