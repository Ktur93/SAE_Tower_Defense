package universite_paris8.iut.ademir.demo1.Modele.Cartes;

import java.util.ArrayList;

public class Carte {

    private int[][] carte;
    private int[][] carteDecoration;

    public Carte(){
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

        carteDecoration = new int[][]{
                {21,21,21,21,21,21,21,21,21,21,21, 0,16, 0, 0,21,21,21,21,21,21,21,21,21,21},
                {21,21,21,21,21,21,21,21,21,21, 0, 0, 2, 0, 0, 0,21,21,21,21,21,21,21,21,21},
                {21,21,21,21,21,21,21,21,21,21, 0, 0, 2, 0,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {21,21,21,21,21,21,21,21,21,21, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {21,21,21, 0, 0, 0, 0, 0, 0, 0, 0, 0,10, 1, 1, 1, 7, 1, 1, 1, 3, 0, 0, 0, 0},
                {21,21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0,20, 0, 2, 0,19, 0, 2, 0, 0, 0, 0},
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

    }


    public void set(int x, int y, int code) {
        if (dansCarte(x, y)) {
            carte[y][x] = code;
        }
    }

    public boolean dansCarte(int x, int y) {
        if(x >= 0 && x < getLargeur() && y >= 0 && y < getHauteur()){
            return true;
        }else {
            return false;
        }
    }

    public int getLargeur() {
        return carte[0].length;
    }
    public int getHauteur() {
        return carte.length;
    }
    public int Tuile(int col, int ligne) {
        return carte[ligne][col];
    }



    public int getLargeurDecoration() {
        return carte[0].length;
    }
    public int getHauteurDecoration() {
        return carte.length;
    }
    public int TuileDecorations(int col, int ligne) {
        return carteDecoration[ligne][col];
    }


    //marchable
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
    //


    public Position trouverArrivee() {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                int arriver = Tuile(j, i);
                if (arriver >= 11 && arriver <= 14) {
                    return new Position(j, i);
                }
            }
        }
        return null;
    }


    public boolean caseDebloquer(Position position) {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                if (position.getX() == j && position.getY() == i && carte[i][j] == 20) {
                    carte[i][j] = 19;
                    System.out.println("achat de la case");
                    return true;
                }
            }
        }
        return false;
    }

    public void caseBloquer(){
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                if (carte[i][j] == 19) {
                    carte[i][j] = 20;
                    System.out.println("case bloquer");
                }
            }
        }
    }



    //verification pour l'emplacement des tours :

    public boolean estDansCarte(int colonne, int ligne) {
        if (colonne < 0 || colonne >= getLargeur()) {
            return false;
        }

        if (ligne < 0 || ligne >=  getHauteur()) {
            return false;
        }

        return true;
    }

    public boolean estCaseTour(int colonne, int ligne) {
        if (!estDansCarte(colonne, ligne)) {
            return false;
        }

        int tuile = Tuile(colonne, ligne);

        if(tuile == 19) {
            return true;
        }

        return false;
    }
}
