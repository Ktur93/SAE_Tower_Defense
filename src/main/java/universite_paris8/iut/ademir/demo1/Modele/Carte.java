package universite_paris8.iut.ademir.demo1.Modele;

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
            }
        }

        // Eau en haut gauche
        rectangle(0, 0, 15, 2, 3);
        rectangle(0, 3, 2, 4, 3);
        rectangle(3, 2, 11, 2, 3);

        // Eau en haut droite
        rectangle(29, 0, 11, 2, 3);
        rectangle(31, 2, 9, 2, 3);
        rectangle(36, 4, 4, 2, 3);

        // Eau en bas
        rectangle(18, 23, 22, 2, 3);
        rectangle(20, 22, 8, 1, 3);
        rectangle(30, 22, 7, 1, 3);

        // Sable autour de l'eau
        ligneHorizontale(2, 14, 4, 7);
        ligneHorizontale(29, 35, 3, 7);
        ligneHorizontale(18, 39, 22, 7);

        // Chemin principal
        cheminHorizontal(0, 3, 5);
        cheminHorizontal(4, 5, 6);
        cheminHorizontal(5, 6, 7);
        cheminVertical(6, 8, 15);
        cheminHorizontal(7, 17, 17);
        cheminVertical(18, 0, 16);
        cheminHorizontal(19, 31, 6);
        cheminHorizontal(32, 33, 7);
        cheminVertical(33, 8, 18);
        cheminHorizontal(34, 39, 19);

        // Branche vers le bas
        cheminVertical(12, 18, 24);

        // Bordures terre autour du chemin
        borduresTerre();

        // Rochers gris
        rocher(13, 6);
        rocher(13, 14);
        rocher(25, 3);
        rocher(25, 11);
        rocher(25, 20);
        rocher(4, 21);
        rocher(38, 18);
        rocher(38, 22);

        // Entrées / sorties noires
        rectangle(0, 5, 1, 2, 8);
        rectangle(18, 0, 2, 1, 8);
        rectangle(12, 24, 2, 1, 8);
        rectangle(39, 19, 1, 2, 8);
    }

    private void cheminHorizontal(int x1, int x2, int y) {
        for (int x = x1; x <= x2; x++) {
            set(x, y, 1);
            set(x, y + 1, 1);
        }
    }

    private void cheminVertical(int x, int y1, int y2) {
        for (int y = y1; y <= y2; y++) {
            set(x, y, 1);
            set(x + 1, y, 1);
        }
    }

    private void borduresTerre() {
        for (int y = 0; y < HAUTEUR; y++) {
            for (int x = 0; x < LARGEUR; x++) {
                if (carte[y][x] == 1) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int nx = x + dx;
                            int ny = y + dy;

                            if (dansCarte(nx, ny) && carte[ny][nx] == 0) {
                                carte[ny][nx] = 2;
                            }
                        }
                    }
                }
            }
        }
    }

    private void rocher(int x, int y) {
        set(x, y, 5);
        set(x + 1, y, 5);
        set(x - 1, y + 1, 5);
        set(x, y + 1, 5);
        set(x + 1, y + 1, 5);
        set(x, y + 2, 5);
    }

    private void rectangle(int x, int y, int largeur, int hauteur, int code) {
        for (int ligne = y; ligne < y + hauteur; ligne++) {
            for (int col = x; col < x + largeur; col++) {
                set(col, ligne, code);
            }
        }
    }

    private void ligneHorizontale(int x1, int x2, int y, int code) {
        for (int x = x1; x <= x2; x++) {
            set(x, y, code);
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
