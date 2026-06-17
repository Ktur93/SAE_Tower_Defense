package universite_paris8.iut.ademir.demo1.Tests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourCanon;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TourTest {

    public ArrayList<Position> creerPetitChemin() {
        // Petit chemin pour creer un zombie proche de la tour
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        return chemin;
    }

    @Test
    public void tourCanonCreeUnProjectileQuandMonstreAPortee() {
        // On cree une tour canon et un zombie proche
        TourCanon tour = new TourCanon(new Position(0, 0));
        Zombie zombie = new Zombie(creerPetitChemin());

        // Liste des monstres de la partie
        ObservableList<Monstre> monstres = FXCollections.observableArrayList();
        monstres.add(zombie);

        // On met un compteur assez grand pour depasser la cadence
        Projectile projectile = tour.attaquer(monstres, 1000);

        // La tour doit creer un projectile
        assertNotNull(projectile);
    }

    @Test
    public void tourCanonInfligeDesDegatsAuZombie() {
        // On cree une tour canon et un zombie proche
        TourCanon tour = new TourCanon(new Position(0, 0));
        Zombie zombie = new Zombie(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();
        monstres.add(zombie);

        // La tour attaque
        tour.attaquer(monstres, 1000);

        // Le canon inflige 35 degats, donc le zombie passe de 100 a 65 PV
        assertEquals(65, zombie.getPv());
    }

    @Test
    public void tourCanonNeCreePasProjectileSiAucunMonstre() {
        // On cree une tour
        TourCanon tour = new TourCanon(new Position(0, 0));

        // Liste vide de monstres
        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        // La tour attaque une liste vide
        Projectile projectile = tour.attaquer(monstres, 1000);

        // Aucun projectile ne doit etre cree
        assertNull(projectile);
    }

    @Test
    public void tourCanonRespecteSaCadence() {
        // On cree une tour canon et un zombie proche
        TourCanon tour = new TourCanon(new Position(0, 0));
        Zombie zombie = new Zombie(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();
        monstres.add(zombie);

        // Le compteur 10 est inferieur a la cadence du canon qui vaut 50
        Projectile projectile = tour.attaquer(monstres, 10);

        // La tour ne doit pas encore tirer
        assertNull(projectile);
        assertEquals(100, zombie.getPv());
    }

    @Test
    public void ameliorerTourCanonAugmenteLesStats() {
        // On cree une tour canon
        TourCanon tour = new TourCanon(new Position(0, 0));

        // On garde les anciennes valeurs
        int ancienneAtk = tour.getAtk();
        int anciennePortee = tour.getPorter();
        long ancienneCadence = tour.getCadence();

        // On ameliore la tour
        tour.ameliorer();

        // Les statistiques doivent augmenter
        assertTrue(tour.getAtk() > ancienneAtk);
        assertTrue(tour.getPorter() > anciennePortee);
        assertTrue(tour.getCadence() > ancienneCadence);
    }
}