package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourCanon;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PartieTest {

    public ArrayList<Position> creerPetitChemin() {
        // Petit chemin utilise pour creer une partie simple
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        chemin.add(new Position(2, 0));
        return chemin;
    }

    public Partie creerPartieSimple() {
        // On utilise le meme chemin pour les trois departs
        ArrayList<Position> chemin = creerPetitChemin();
        return new Partie(chemin, chemin, chemin);
    }

    @Test
    public void partieCommenceALaVagueZero() {
        // On cree une partie
        Partie partie = creerPartieSimple();

        // La partie commence a la vague 0
        assertEquals(0, partie.getIndiceVague());
    }

    @Test
    public void nouvellePartieNaPasDeVagueEnCours() {
        // On cree une partie
        Partie partie = creerPartieSimple();

        // Au debut aucune vague n est en cours
        assertFalse(partie.getVagueEnCours());
    }

    @Test
    public void lancerProchaineVagueMetVagueEnCoursATrue() {
        // On cree une partie
        Partie partie = creerPartieSimple();

        // On lance une vague
        partie.lancerProchaineVague();

        // La vague doit etre en cours
        assertTrue(partie.getVagueEnCours());
    }

    @Test
    public void portailCommenceAvecTroisPvDansLaProperty() {
        // On cree une partie
        Partie partie = creerPartieSimple();

        // les coeurs du portail commencent a 3
        assertEquals(3, partie.getPvPortailIntegerProperty());
    }

    @Test
    public void recevoirDegatPortailDiminueLesPvProperty() {
        // On cree une partie
        Partie partie = creerPartieSimple();

        // Le portail recoit 1 degat
        partie.recevoirDegatPortail(1);

        // Il doit passer de 3 a 2 PV
        assertEquals(2, partie.getPvPortailIntegerProperty());
    }

    @Test
    public void portailMortQuandPvPropertyZero() {
        // On cree une partie
        Partie partie = creerPartieSimple();

        // On enleve les 3 PV du portail
        partie.recevoirDegatPortail(3);

        // Le portail doit etre mort
        assertTrue(partie.portailMort());
    }

    @Test
    public void recommencerRemetIndiceVagueAZero() {
        // On cree une partie
        Partie partie = creerPartieSimple();

        // On change l indice de vague
        partie.setIndiceVague(3);

        // On recommence
        partie.recommnencer();

        // L indice de vague doit revenir a 0
        assertEquals(0, partie.getIndiceVague());
    }

    @Test
    public void recommencerRemetPvPortailATrois() {
        // On cree une partie
        Partie partie = creerPartieSimple();

        // On fait perdre des PV au portail
        partie.recevoirDegatPortail(2);

        // On recommence
        partie.recommnencer();

        // Les PV du portail doivent revenir a 3
        assertEquals(3, partie.getPvPortailIntegerProperty());
    }

    @Test
    public void placerTourSurCaseNonTourRetourneFalse() {
        // On cree une partie et une carte
        Partie partie = creerPartieSimple();
        Carte carte = new Carte();

        // La position 0,0 n est pas une case tour
        TourCanon tour = new TourCanon(new Position(0, 0));

        // Le placement doit echouer
        assertFalse(partie.placerTour(tour, carte));
    }

    @Test
    public void placerTourSurCaseDebloqueeRetourneTrue() {
        // On cree une partie et une carte
        Partie partie = creerPartieSimple();
        Carte carte = new Carte();

        // On debloque une case achetable
        Position position = new Position(14, 2);
        carte.caseDebloquer(position);

        // On place une tour sur cette case
        TourCanon tour = new TourCanon(position);

        // Le placement doit reussir
        assertTrue(partie.placerTour(tour, carte));
        assertEquals(1, partie.getTours().size());
    }
}