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
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        return chemin;
    }

    @Test
    public void tourCanonCreeUnProjectileQuandMonstreAPortee() {
        TourCanon tour = new TourCanon(new Position(0, 0));
        Zombie zombie = new Zombie(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();
        monstres.add(zombie);

        Projectile projectile = tour.attaquer(monstres, 1000);

        assertNotNull(projectile);
    }

    @Test
    public void tourCanonNeBlessePasDirectementMaisSonProjectileBlesseLeZombie() {
        TourCanon tour = new TourCanon(new Position(0, 0));
        Zombie zombie = new Zombie(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();
        monstres.add(zombie);

        Projectile projectile = tour.attaquer(monstres, 1000);

        // La tour cree seulement le projectile. Les degats sont appliques quand le projectile touche.
        assertNotNull(projectile);
        assertEquals(100, zombie.getPv());

        projectile.avancer();

        // Le canon a maintenant 40 degats dans la nouvelle version.
        assertEquals(60, zombie.getPv());
    }

    @Test
    public void tourCanonNeCreePasProjectileSiAucunMonstre() {
        TourCanon tour = new TourCanon(new Position(0, 0));

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        Projectile projectile = tour.attaquer(monstres, 1000);

        assertNull(projectile);
    }

    @Test
    public void tourCanonRespecteSaCadence() {
        TourCanon tour = new TourCanon(new Position(0, 0));
        Zombie zombie = new Zombie(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();
        monstres.add(zombie);

        // Le compteur 10 est inferieur a la cadence du canon qui vaut 60.
        Projectile projectile = tour.attaquer(monstres, 10);

        assertNull(projectile);
        assertEquals(100, zombie.getPv());
    }

    @Test
    public void ameliorerTourCanonAmelioreLesStatsUtiles() {
        TourCanon tour = new TourCanon(new Position(0, 0));

        int ancienneAtk = tour.getAtk();
        int anciennePortee = tour.getPorter();
        long ancienneCadence = tour.getCadence();

        tour.ameliorer();

        assertTrue(tour.getAtk() > ancienneAtk);
        assertTrue(tour.getPorter() > anciennePortee);
        // Une cadence plus petite veut dire que la tour attend moins longtemps avant de tirer.
        assertTrue(tour.getCadence() < ancienneCadence);
    }
}
