package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void deuxPositionsAvecMemesCoordonneesSontEgales() {
        // On cree deux positions avec les memes coordonnees
        Position p1 = new Position(4, 7);
        Position p2 = new Position(4, 7);

        // Les deux positions doivent etre egales
        assertEquals(p1, p2);
    }

    @Test
    public void deuxPositionsAvecCoordonneesDifferentesNeSontPasEgales() {
        // On cree deux positions avec des coordonnees differentes
        Position p1 = new Position(4, 7);
        Position p2 = new Position(5, 7);

        // Les deux positions ne doivent pas etre egales
        assertNotEquals(p1, p2);
    }

    @Test
    public void hashCodeIdentiqueSiPositionsEgales() {
        // Deux positions egales doivent avoir le meme hashCode
        Position p1 = new Position(2, 3);
        Position p2 = new Position(2, 3);

        // C est utile pour les collections comme HashMap
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void positionNestPasEgaleANull() {
        // Une position ne doit pas etre egale a null
        Position p = new Position(1, 1);

        assertNotEquals(null, p);
    }

    @Test
    public void positionNestPasEgaleAUnAutreTypeObjet() {
        // Une position ne doit pas etre egale a un autre type d objet
        Position p = new Position(1, 1);

        assertNotEquals("position", p);
    }
}