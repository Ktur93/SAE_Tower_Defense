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
        Zombie zombie = new Zombie(creerPetitChemin());

        assertEquals(100, zombie.getPv());
    }

    @Test
    public void recevoirDegatsDiminueLesPv() {
        Zombie zombie = new Zombie(creerPetitChemin());

        zombie.recevoirDegats(30);

        assertEquals(70, zombie.getPv());
    }

    @Test
    public void zombieNestPasMortAuDebut() {
        Zombie zombie = new Zombie(creerPetitChemin());

        assertFalse(zombie.estMort());
    }

    @Test
    public void zombieEstMortQuandPvZero() {
        Zombie zombie = new Zombie(creerPetitChemin());

        zombie.recevoirDegats(100);

        assertTrue(zombie.estMort());
    }

    @Test
    public void zombieAvanceSurLeChemin() {
        Zombie zombie = new Zombie(creerPetitChemin());

        double xAvant = zombie.getX();

        zombie.avancer();

        assertTrue(zombie.getX() > xAvant);
    }

    @Test
    public void zombieArriveApresPlusieursAvancements() {
        Zombie zombie = new Zombie(creerPetitChemin());

        // Le zombie avance en pixels. Avec une vitesse de 3,5, il faut plus que 10 ticks pour parcourir deux cases de 64 pixels.
        for (int i = 0; i < 50; i++) {
            zombie.avancer();
        }

        assertTrue(zombie.estADestination());
    }

    @Test
    public void recevoirDegatsPoisonDiminueLesPvEtEmpoisonne() {
        Zombie zombie = new Zombie(creerPetitChemin());

        zombie.recevoirDegatsPoison(20);

        assertEquals(80, zombie.getPv());
    }

    @Test
    public void recevoirDegatsGlaceDiminueLesPvEtMetEtatGlace() {
        Zombie zombie = new Zombie(creerPetitChemin());

        zombie.recevoirDegatsGlace(20);

        assertEquals(80, zombie.getPv());
        assertTrue(zombie.estGlace());
    }
}
