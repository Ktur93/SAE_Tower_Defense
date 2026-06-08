package universite_paris8.iut.ademir.demo1.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import javafx.scene.text.Text;
import universite_paris8.iut.ademir.demo1.Modele.Algorithmes.BFS;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;
import universite_paris8.iut.ademir.demo1.Vue.CarteVue;
import universite_paris8.iut.ademir.demo1.Vue.MonstreVue;
import universite_paris8.iut.ademir.demo1.Vue.RubisVue;
import universite_paris8.iut.ademir.demo1.Vue.ToursVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int TAILLE_TUILE = 64;

    @FXML
    private Button btnRecommencer;
    @FXML
    private TilePane paneCarte;
    @FXML
    private Pane paneSprites;
    @FXML
    private Pane paneDecoration;
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
    @FXML
    private Button btnVague;
    @FXML
    private Button btnAcheterCase;

    private TextField acheterCase;

    private Carte carte;
    private Partie partie;
    private String tourSelectionne;
    private boolean AchatCase = false;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carte = new Carte();
        CarteVue carteVue = new CarteVue(carte, paneCarte , paneDecoration);
        carteVue.dessinerCarte();




        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();
        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);
        partie = new Partie(chemin);


        RubisVue rubisVue = new RubisVue(partie, labelRubis);
        rubisVue.afficherRubis();



        //bouton pour lancer la vague/lancement du gameLoop/deplacement des monstre:
        btnVague.setOnAction(actionEvent -> {
            partie.lancerProchaineVague();
        });

        MonstreVue monstreVue = new MonstreVue(partie, paneSprites);

        AnimationTimer gameLoop = new AnimationTimer() {
            long dernierDeplacement = 0;
            public void handle(long tempActuel) {

                if (tempActuel - dernierDeplacement > 20_000_000) {

                    partie.mettreAJour(tempActuel);
                    monstreVue.mettreAJourSprites();
                    mettreAJourBoutonVague();
                    mettreAJourBoutonRecommencer();
                    rubisVue.afficherRubis();
                    dernierDeplacement = tempActuel;
                    btnAcheterCase.setText("Acheter case - " + partie.getPrixCase());
                }
            }
        };
        gameLoop.start();

        //pour recommencer
        btnRecommencer.setOnAction(actionEvent -> {
            partie.recommnencer();
            carte.caseBloquer();
            carteVue.viderCarte();
            carteVue.dessinerCarte();
        });



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

        ToursVue T = new ToursVue(partie, paneSprites);


        btnAcheterCase.setOnAction(actionEvent -> {
            AchatCase = true;
        });

        paneCarte.setOnMouseClicked(event -> {


            int colonne = (int) (event.getX() / TAILLE_TUILE);
            int ligne = (int) (event.getY() / TAILLE_TUILE);


            Position position = new Position(colonne, ligne);

            if (AchatCase == true) {
                partie.acheterCase(position , carte);
                carteVue.viderCarte();
                carteVue.dessinerCarte();
                rubisVue.afficherRubis();
                AchatCase = false;
                return;
            }

            Tour tour = creerTourSelectionnee(tourSelectionne, position);

            boolean placeDisponible = partie.placerTour(tour, carte);

            if (placeDisponible) {
                rubisVue.afficherRubis();
                System.out.println("placement");
            } else {
                System.out.println("null");
            }

        });
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


    public void mettreAJourBoutonVague() {
        if (partie.toutesLesVaguesTerminees()) {
            btnVague.setDisable(true);
            btnVague.setText("Toutes les vagues sont terminees");
        } else if (partie.getVagueEnCours()) {
            btnVague.setDisable(true);
            btnVague.setText("Vague " + partie.getIndiceVaguePlusUn() + " En cours");
        } else {
            btnVague.setDisable(false);
            btnVague.setText("Lancer vague " + partie.getIndiceVaguePlusUn());
        }
    }

    public void mettreAJourBoutonRecommencer() {
        if (partie.getVagueEnCours()) {
            btnRecommencer.setDisable(true);
        } else {
            btnRecommencer.setDisable(false);
        }
    }
}