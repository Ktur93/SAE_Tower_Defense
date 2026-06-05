package universite_paris8.iut.ademir.demo1.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import universite_paris8.iut.ademir.demo1.Modele.Algorithmes.BFS;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;
import universite_paris8.iut.ademir.demo1.Vue.CarteVue;
import universite_paris8.iut.ademir.demo1.Vue.RubisVue;
import universite_paris8.iut.ademir.demo1.Vue.ToursVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int TAILLE_TUILE = 64;
    
    @FXML
    private Button btnLancerVague;

    @FXML
    private TilePane paneCarte;
    @FXML
    private Pane paneSprites;
    @FXML
    private Label labelRubis;
    @FXML
    private Button btnCanon;
    @FXML
    private Button btnArcher;
    @FXML
    private Button btnGlace;
    @FXML
    private Button btnPoison;

    private Carte carte;
    private Partie partie;
    private String tourSelectionne;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carte = new Carte();
        carte.remplir();

        CarteVue carteVue = new CarteVue(carte, paneCarte);
        carteVue.dessinerCarte();

        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();

        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);

        partie = new Partie(chemin);

        mettreAJourBoutonVague();


        RubisVue rubisVue = new RubisVue(partie, labelRubis);
        rubisVue.afficherRubis();



        // pour selectionnée les tours via des boutons:
        btnCanon.setOnAction(actionEvent -> {
            tourSelectionne = "CANON";

        });

        btnArcher.setOnAction(actionEvent -> {
            tourSelectionne = "ARCHER";
        });

        btnGlace.setOnAction(actionEvent -> {
            tourSelectionne = "GLACE";
        });

        btnPoison.setOnAction(actionEvent -> {
            tourSelectionne = "POISON";
        });

        new ToursVue(partie, paneSprites);

        paneSprites.setOnMouseClicked(event -> {


            int colonne = (int) (event.getX() / TAILLE_TUILE);
            int ligne = (int) (event.getY() / TAILLE_TUILE);


            Position position = new Position(colonne, ligne);

            Tour tour = creerTourSelectionnee(tourSelectionne, position);

            boolean placeDisponible = partie.placerTour(tour, carte);

            if (placeDisponible) {
                rubisVue.afficherRubis();
                System.out.println("placement");
            } else {
                System.out.println("null");
            }
        });


        AnimationTimer gameLoop = new AnimationTimer() {
            private long dernierDeplacement = 0;

            @Override
            public void handle(long now) {

                if (now - dernierDeplacement > 20_000_000) {

                    partie.mettreAJour(now);
                    mettreAJourBoutonVague();



                    dernierDeplacement = now;
                }
            }
        };

        gameLoop.start();
    }

    public Tour creerTourSelectionnee(String tourSelectionnee, Position position) {
        switch (tourSelectionnee) {
            case "CANON":
                return new TourCanon(position);
            case "ARCHER":
                return new TourArcher(position);
            case "GLACE":
                return new TourGlace(position);
            case "POISON":
                return new TourPoison(position);
            default:
                return null;
        }
    }






    @FXML
    public void lancerVague() {
        partie.lancerProchaineVague();
        mettreAJourBoutonVague();
    }

    public void mettreAJourBoutonVague() {
        if (partie.toutesLesVaguesTerminees()) {
            btnLancerVague.setDisable(true);
            btnLancerVague.setText("Toutes les vagues sont terminees");
        } else if (partie.isVagueEnCours()) {
            btnLancerVague.setDisable(true);
            btnLancerVague.setText("Vague " + partie.getIndiceVaguePlusUn() + " En cours");
        } else {
            btnLancerVague.setDisable(false);
            btnLancerVague.setText("Lancer vague " + partie.getIndiceVaguePlusUn());
        }
    }

}