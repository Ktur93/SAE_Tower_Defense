package universite_paris8.iut.ademir.demo1.Modele.Cartes;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        return getX() == autre.getX() && getY() == autre.getY();
    }

    // Important pour HashMap : si deux positions sont egales,
    // elles doivent avoir le meme hashCode
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

//    On a besoin de equals et hashCode dans Position parce que le BFS utilise une HashMap pour retenir
//    les cases deja visitees et leurs predecesseurs. Mais en Java, deux objets Position avec les memes coordonnees
//    ne sont pas consideres egaux automatiquement. Sans equals, Java compare les adresses memoire.
//    Donc le BFS ne reconnaitrait pas correctement qu'une case a deja ete visitee. hashCode est necessaire
//    parce que HashMap utilise le hash pour ranger et retrouver les objets rapidement.
//    et ducoup sans redéfinir hashCode(), Java aurait utilisé le hashCode() de base de Object.
//    mais ce hashCode() identifie l’objet lui-même, pas ses coordonnées.

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}