package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

import static org.junit.jupiter.api.Assertions.*;

public class CarteTest {

    @Test
    public void caseHorsCarteNestPasMarchable() {
        // On cree une carte
        Carte carte = new Carte();

        // Les cases hors limite ne doivent pas etre marchable
        assertFalse(carte.estMarchable(-1, 0));
        assertFalse(carte.estMarchable(0, -1));
        assertFalse(carte.estMarchable(100, 0));
        assertFalse(carte.estMarchable(0, 100));
    }

    @Test
    public void caseDeDepartEstMarchable() {
        // On cree une carte
        Carte carte = new Carte();

        // La case de depart de la premiere entree doit etre marchable
        assertTrue(carte.estMarchable(0, 7));
    }

    @Test
    public void caseVideNestPasMarchable() {
        // On cree une carte
        Carte carte = new Carte();

        // Une case vide ne doit pas etre marchable
        assertFalse(carte.estMarchable(0, 0));
    }

    @Test
    public void trouverArriveeRetourneUnePosition() {
        // On cree une carte
        Carte carte = new Carte();

        // On recupere la position d arrivee
        Position arrivee = carte.trouverArrivee();

        // l'arrivée doit existé
        assertNotNull(arrivee);
    }

    @Test
    public void caseDebloquerTransformeUneCaseAchetable() {
        // On cree une carte
        Carte carte = new Carte();

        // La case 14,2 = 20 dans la carte, donc elle est achetable
        Position position = new Position(14, 2);

        // Au debut ce n'est pas encore une case tour
        assertFalse(carte.estCaseTour(14, 2));

        // On debloque la case
        boolean resultat = carte.caseDebloquer(position);

        // Le deblocage doit marcher et la case devient une case tour
        assertTrue(resultat);
        assertTrue(carte.estCaseTour(14, 2));
    }

    @Test
    public void caseBloquerRebloqueLesCasesAchetees() {
        // On cree une carte
        Carte carte = new Carte();

        // On debloque une case achetable
        Position position = new Position(14, 2);
        carte.caseDebloquer(position);

        // La case est devenue une case tour
        assertTrue(carte.estCaseTour(14, 2));

        //on rebloque les cases achetees
        carte.caseBloquer();

        // La case ne doit plus etre une case tour
        assertFalse(carte.estCaseTour(14, 2));
    }
}