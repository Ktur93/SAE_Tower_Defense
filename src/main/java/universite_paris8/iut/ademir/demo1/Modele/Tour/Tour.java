package universite_paris8.iut.ademir.demo1.Modele.Tour;

public abstract class Tour {
    private int atk;
    public Tour(int atk){
        this.atk = atk;
    }
    public int getAtk(){
        return this.atk;
    }
}
