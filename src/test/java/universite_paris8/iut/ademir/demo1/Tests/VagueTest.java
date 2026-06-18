package universite_paris8.iut.ademir.demo1.Tests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Vague;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VagueTest {

    public ArrayList<Position> creerPetitChemin() {
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        return chemin;
    }

    public ArrayList<Position> creerPetitChemin2() {
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(1, 0));
        chemin.add(new Position(0, 1));
        return chemin;
    }

    public ArrayList<Position> creerPetitChemin3() {
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(1, 1));
        chemin.add(new Position(1, 0));
        return chemin;
    }

    @Test
    public void vague1AjouteUnMonstreAuPremierTick() {
        Vague vague = new Vague();
        vague.creeVague1(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        vague.mettreAJourVague(0, monstres);

        assertEquals(1, monstres.size());
    }

    @Test
    public void vague1EstPasTermineeApresAvoirEnvoyeSonPremierMonstre() {
        Vague vague = new Vague();
        vague.creeVague1(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        vague.mettreAJourVague(0, monstres);

        // La vague 1 contient maintenant 5 zombies, donc apres le premier envoi elle continue.
        assertFalse(vague.tousLesMonstresEnvoyes());
    }

    @Test
    public void resetVaguePermetDeLaRelancer() {
        Vague vague = new Vague();
        vague.creeVague1(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        vague.mettreAJourVague(0, monstres);
        vague.setIndiceMonstreZero();

        assertFalse(vague.tousLesMonstresEnvoyes());
    }

    @Test
    public void vague2AjouteUnMonstreAuPremierTick() {
        Vague vague = new Vague();
        vague.creeVague2(creerPetitChemin(), creerPetitChemin2());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        vague.mettreAJourVague(0, monstres);

        assertEquals(1, monstres.size());
    }

    @Test
    public void vague5AjouteUnMonstreAuPremierTick() {
        Vague vague = new Vague();
        vague.creeVague5(creerPetitChemin(), creerPetitChemin2(), creerPetitChemin3());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        vague.mettreAJourVague(0, monstres);

        assertEquals(1, monstres.size());
    }
}
