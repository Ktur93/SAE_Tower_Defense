package universite_paris8.iut.ademir.demo1.Modele.Cartes;

import java.util.Objects;

public class Position {

    private int colonne;
    private int ligne;

    public Position(int colonne, int ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Position)) {
            return false;
        }

        Position autre = (Position) obj;
        return colonne == autre.colonne && ligne == autre.ligne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colonne, ligne);
    }

    @Override
    public String toString() {
        return "(" + colonne + ", " + ligne + ")";
    }
}