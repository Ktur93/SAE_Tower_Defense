package universite_paris8.iut.ademir.demo1.Modele.Cartes;

import java.util.ArrayList;

public class Carte {

    private int[][] carte;

   public Carte() {
    }

    public void remplir() {

        carte = new int[][]{
                {21,21,21,21,21,21,21,21,21,21,21, 0,16, 0, 0,21,21,21,21,21,21,21,21,21,21},
                {21,21,21,21,21,21,21,21,21,21, 0, 0, 2, 0, 0, 0,21,21,21,21,21,21,21,21,21},
                {21,21,21,21,21,21,21,21,21,21, 0, 0, 2, 0,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {21,21,21,21,21,21,21,21,21,21, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {21,21,21, 0, 0, 0, 0, 0, 0, 0, 0, 0,10, 1, 1, 1, 7, 1, 1, 1, 3, 0, 0, 0, 0},
                {21,21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0,20, 0, 2, 0,20, 0, 2, 0, 0, 0, 0},
                {15, 1, 1, 1, 3, 0,20, 0, 0, 0,20, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0},
                { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 4, 1, 1, 1, 9, 0, 0, 0, 0},
                { 0, 0,20, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                { 0, 0, 0, 0, 2, 0, 0, 0,20, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0,20, 0, 0},
                { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0,21,21,21,21, 0, 2, 0, 0, 0, 0},
                { 0, 0, 0, 0, 4, 1, 1, 1, 7, 1, 1, 1, 6, 0,21,21,21,21,21, 0, 4, 1, 1, 1,11},
                { 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0,21,21,21,21,21, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0,21,21,21,21,21,21,21,21,21,21,21, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0,17, 0, 0,21,21,21,21,21,21,21,21,21,21,21, 0, 0, 0}

        };
        System.out.println("Largeur = " + this.largeur() + "   hauteur = " + hauteur());
    }

    private void set(int x, int y, int code) {
        if (dansCarte(x, y)) {
            carte[y][x] = code;
        }
    }

    private boolean dansCarte(int x, int y) {
        return x >= 0 && x < largeur() && y >= 0 && y < hauteur();
    }

    public int largeur() {
        return carte[0].length;
    }

    public int hauteur() {
        return carte.length;
    }

    public int Tuile(int col, int ligne) {
        return carte[ligne][col];
    }

    public boolean estDansCarte(int colonne, int ligne) {
        return colonne >= 0 && colonne < largeur()
                && ligne >= 0 && ligne < hauteur();
    }

    public boolean estMarchable(int colonne, int ligne) {
        if (!estDansCarte(colonne, ligne)) {
            return false;
        }

        int code = Tuile(colonne, ligne);

        return code >= 1 && code <= 18;
    }

    public ArrayList<Position> getVoisinsMarchables(Position position) {
        ArrayList<Position> voisins = new ArrayList<>();

        int colonne = position.getX();
        int ligne = position.getY();

        Position haut = new Position(colonne, ligne - 1);
        Position bas = new Position(colonne, ligne + 1);
        Position gauche = new Position(colonne - 1, ligne);
        Position droite = new Position(colonne + 1, ligne);

        if (estMarchable(haut.getX(), haut.getY())) {
            voisins.add(haut);
        }

        if (estMarchable(bas.getX(), bas.getY())) {
            voisins.add(bas);
        }

        if (estMarchable(gauche.getX(), gauche.getY())) {
            voisins.add(gauche);
        }

        if (estMarchable(droite.getX(), droite.getY())) {
            voisins.add(droite);
        }

        return voisins;
    }

    public Position trouverDepart() {
        for (int ligne = 0; ligne < hauteur(); ligne++) {
            for (int colonne = 0; colonne < largeur(); colonne++) {
                int code = Tuile(colonne, ligne);

                if (code >= 15 && code <= 18) {
                    return new Position(colonne, ligne);
                }
            }
        }

        return null;
    }

    public Position trouverArrivee() {
        for (int ligne = 0; ligne < hauteur(); ligne++) {
            for (int colonne = 0; colonne < largeur(); colonne++) {
                int code = Tuile(colonne, ligne);

                if (code >= 11 && code <= 14) {
                    return new Position(colonne, ligne);
                }
            }
        }
        return null;
    }

    public boolean estCaseTour(int colonne, int ligne) {
        return estDansCarte(colonne, ligne) &&
                (Tuile(colonne, ligne) == 19 || Tuile(colonne, ligne) == 20);
    }

}
