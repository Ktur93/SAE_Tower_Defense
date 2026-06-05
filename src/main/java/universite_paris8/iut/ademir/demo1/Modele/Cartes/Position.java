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


    // UTILITE DANS LE BFS, SINON MARCHE PAS !!! PAS SUPPRIME
    @Override
    public boolean equals(Object obj) {
        // si la position a la meme adresse memoire que obj
        if (this == obj) {
            return true;
        }

        // si c'est pas passé on test si c'est une position
        if (!(obj instanceof Position)) {
            return false;
        }

        // une fois qu'on est sur que c'est une position on compare les coordonnées entre eux pour voir si ils sont egaux
        Position autre = (Position) obj;
        return colonne == autre.colonne && ligne == autre.ligne;
    }

    // Si deux positions ont les memes coordonnées ils auront les memes hashcode donc seront "trié" dans le meme tiroir
    @Override
    public int hashCode() {
        return Objects.hash(colonne, ligne);
    }

    @Override
    public String toString() {
        return "(" + colonne + ", " + ligne + ")";
    }
}