package universite_paris8.iut.ademir.demo1.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private String tourSelectionnés;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carte affichage
        carte = new Carte();
        carte.remplir();

        CarteVue carteVue = new CarteVue(carte, paneCarte);
        carteVue.dessinerCarte();

        //rubis affichage
        RubisVue rubis = new RubisVue(partie , labelRubis);
        rubis.afficherRubis();

        //tours affichage

        ToursVue toursVue = new ToursVue(partie , carte , paneSprites , tourSelectionnés);


        btnCanon.setOnAction(actionEvent -> {
            tourSelectionnés = "CANON";
        });

        btnArcher.setOnAction(actionEvent -> {
            tourSelectionnés = "ARCHER";
        });

        btnGlace.setOnAction(actionEvent -> {
            tourSelectionnés = "GLACE";
        });

        btnPoison.setOnAction(actionEvent -> {
            tourSelectionnés = "POISON";
        });




        paneCarte.setOnMouseClicked(event -> {
                    Tour tour = creerTourSelectionnés(tourSelectionnés, new Position(event.getX(), event.getY()));
                    partie.ajoutTours(tour);
                }
                //toursVue.placerTourSurCarte(event.getX(), event.getY())
        );





        //position des monstres/deplacement
        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();


        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);


        partie = new Partie(chemin);
        partie.ajouterZombie();




        AnimationTimer gameLoop = new AnimationTimer() {
            private long dernierDeplacement = 0;

            @Override
            public void handle(long now) {
                if (now - dernierDeplacement > 500_000_000) {
                    partie.mettreAJour();
                    afficherMonstres();
                    afficherTours();
                    afficherRubis();
                    dernierDeplacement = now;
                }
            }
        };

        gameLoop.start();
    }


    public Tour creerTourSelectionnés(String tourSelectionnés, Position position) {
        switch (tourSelectionnés) {
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
}