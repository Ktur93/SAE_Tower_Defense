package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class TourGlace extends Tour{
    public TourGlace(Position position){
        super(25, 100, 3, position,50 , 3);
    }
    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 10);
        this.setPorter(this.getPorter() + 10);
        this.setCadence(this.getCadence() + 10);
        this.setPrix(this.getPrix() + 10);
    }

    @Override
    public void infligerDegat(Monstre cible){
        cible.recevoirDegatsGlace(getAtk());
    }
}
