package universite_paris8.iut.ademir.demo1.Modele;

public class Carte {

    private int[][] carte ;

    public Carte () {
        this.carte = new int[][] {
                {0, 0, 0, 0, 0},
                {4, 1, 1, 1, 3},
                {0, 2, 0, 2, 0}
        };
    }

    public int[][] getCarte() {
        return carte;
    }
}
