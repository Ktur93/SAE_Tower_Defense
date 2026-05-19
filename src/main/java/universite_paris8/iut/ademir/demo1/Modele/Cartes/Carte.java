package universite_paris8.iut.ademir.demo1.Modele.Cartes;

public class Carte {

    private int[][] carte;

    private final int LARGEUR = 40;
    private final int HAUTEUR = 25;

    public Carte() {
        this.carte = new int[HAUTEUR][LARGEUR];
    }

    public void Remplir() {

        // 0 = herbe
        for (int y = 0; y < HAUTEUR; y++) {
            for (int x = 0; x < LARGEUR; x++) {
                carte[y][x] = 0;
                carte[1][1] = 1;
            }
        }
    }

    private void set(int x, int y, int code) {
        if (dansCarte(x, y)) {
            carte[y][x] = code;
        }
    }

    private boolean dansCarte(int x, int y) {
        return x >= 0 && x < LARGEUR && y >= 0 && y < HAUTEUR;
    }

    public int largeur() {
        return carte[0].length;
    }

    public int hauteur() {
        return carte.length;
    }

    public int codeTuile(int col, int ligne) {
        return carte[ligne][col];
    }
}
