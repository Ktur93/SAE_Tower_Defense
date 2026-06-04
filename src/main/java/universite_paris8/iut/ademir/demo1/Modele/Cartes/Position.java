package universite_paris8.iut.ademir.demo1.Modele.Cartes;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    public Position(int colonne, int ligne) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        return getX() == autre.getX() && getY() == autre.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}