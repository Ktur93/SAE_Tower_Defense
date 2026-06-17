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
        // Petit chemin utilise pour les monstres de la vague
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        return chemin;
    }

    @Test
    public void vague1AjouteUnMonstreAuPremierTick() {
        // On cree une vague 1
        Vague vague = new Vague();
        vague.creeVague1(creerPetitChemin());

        // Liste des monstres presents dans la partie
        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        // On met a jour la vague
        vague.mettreAJourVague(0, monstres);

        // Un monstre doit etre ajoute
        assertEquals(1, monstres.size());
    }

    @Test
    public void vague1EstTermineeApresAvoirEnvoyeSonMonstre() {
        // On cree une vague 1
        Vague vague = new Vague();
        vague.creeVague1(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        // La vague envoie son monstre
        vague.mettreAJourVague(0, monstres);

        // La vague 1 contient un seul monstre dans la version actuelle
        assertTrue(vague.tousLesMonstresEnvoyes());
    }

    @Test
    public void resetVaguePermetDeLaRelancer() {
        // On cree une vague 1
        Vague vague = new Vague();
        vague.creeVague1(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        // On envoie le monstre
        vague.mettreAJourVague(0, monstres);

        // On remet l indice de monstre a zero
        vague.setIndiceMonstreZero();

        // La vague ne doit plus etre terminee
        assertFalse(vague.tousLesMonstresEnvoyes());
    }

    @Test
    public void vague2AjouteAussiUnMonstreDansLaVersionActuelle() {
        // On cree une vague 2
        Vague vague = new Vague();
        vague.creeVague2(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        // On met a jour la vague
        vague.mettreAJourVague(0, monstres);

        // Dans ton code actuel, vague 2 contient aussi seulement le zombie de vague 1
        assertEquals(1, monstres.size());
    }

    @Test
    public void vague5AjouteAussiUnMonstreDansLaVersionActuelle() {
        // On cree une vague 5
        Vague vague = new Vague();
        vague.creeVague5(creerPetitChemin());

        ObservableList<Monstre> monstres = FXCollections.observableArrayList();

        // On met a jour la vague
        vague.mettreAJourVague(0, monstres);

        // vague 5 contient aussi seulement le zombie de vague 1
        assertEquals(1, monstres.size());
    }
}