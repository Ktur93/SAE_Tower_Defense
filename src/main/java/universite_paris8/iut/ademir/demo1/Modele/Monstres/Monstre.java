package universite_paris8.iut.ademir.demo1.Modele.Monstres;

public abstract class Monstre {
    private int pv;

    public Monstre(int pv){
        this.pv = pv;
    }

    public int getPv() {
        return this.pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }


}
