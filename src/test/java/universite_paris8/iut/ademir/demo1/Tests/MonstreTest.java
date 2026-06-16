package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MonstreTest {

    public ArrayList<Position> creerPetitChemin() {
        // On cree un petit chemin horizontal simple
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        chemin.add(new Position(2, 0));
        return chemin;
    }

    @Test
    public void zombieCommenceAvecCentPv() {
        // On cree un zombie
        Zombie zombie = new Zombie(creerPetitChemin());

        // Le zombie commence avec 100 PV
        assertEquals(100, zombie.getPv());
    }

    @Test
    public void recevoirDegatsDiminueLesPv() {
        // On cree un zombie
        Zombie zombie = new Zombie(creerPetitChemin());

        // On lui inflige des degats
        zombie.recevoirDegats(30);

        // Les PV doivent diminuer
        assertEquals(70, zombie.getPv());
    }

    @Test
    public void zombieNestPasMortAuDebut() {
        // On cree un zombie
        Zombie zombie = new Zombie(creerPetitChemin());

        // Au debut il ne doit pas etre mort
        assertFalse(zombie.estMort());
    }

    @Test
    public void zombieEstMortQuandPvZero() {
        // On cree un zombie
        Zombie zombie = new Zombie(creerPetitChemin());

        // On lui enleve tous ses PV
        zombie.recevoirDegats(100);

        // Il doit etre mort
        assertTrue(zombie.estMort());
    }

    @Test
    public void zombieAvanceSurLeChemin() {
        // On cree un zombie sur un chemin horizontal
        Zombie zombie = new Zombie(creerPetitChemin());

        // On garde sa position X avant le deplacement
        double xAvant = zombie.getX();

        // On le fait avancer
        zombie.avancer();

        // Sa position X doit augmenter
        assertTrue(zombie.getX() > xAvant);
    }

    @Test
    public void zombieArriveApresPlusieursAvancements() {
        // On cree un zombie avec un petit chemin
        Zombie zombie = new Zombie(creerPetitChemin());

        // On le fait avancer plusieurs fois pour atteindre la fin
        for (int i = 0; i < 10; i++) {
            zombie.avancer();
        }

        // Il doit etre a destination
        assertTrue(zombie.estADestination());
    }

    @Test
    public void recevoirDegatsPoisonDiminueLesPv() {
        // On cree un zombie
        Zombie zombie = new Zombie(creerPetitChemin());

        // On lui inflige des degats poison
        zombie.recevoirDegatsPoison(20);

        // Ses PV doivent diminué
        assertEquals(80, zombie.getPv());
    }

    @Test
    public void recevoirDegatsGlaceDiminueLesPv() {

        // on cree un zombie
        Zombie zombie = new Zombie(creerPetitChemin());

        // On lui inflige des degats glace
        zombie.recevoirDegatsGlace(20);

        // Ses PV doivent diminuer
        assertEquals(80, zombie.getPv());
    }
}