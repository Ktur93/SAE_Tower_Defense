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

import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Algorithmes.BFS;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;
import universite_paris8.iut.ademir.demo1.Vue.CarteVue;

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

    private Carte carte;
    private Partie partie;

    @FXML
    private Label labelRubis;

    private String tourSelectionnee = "CANON";



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carte = new Carte();
        carte.remplir();

        CarteVue carteVue = new CarteVue(carte, paneCarte);
        carteVue.dessinerCarte();
        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();

        if (arrivee == null) {
            System.out.println("Erreur : aucune arrivée trouvée.");
            return;
        }

        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);

        partie = new Partie(chemin);

        mettreAJourBoutonVague();


        afficherRubis();

        paneCarte.setOnMouseClicked(event ->
                placerTourSurCarte(event.getX(), event.getY())
        );

        AnimationTimer gameLoop = new AnimationTimer() {
            private long dernierDeplacement = 0;

            @Override
            public void handle(long now) {
                if (now - dernierDeplacement > 20_000_000) {

                    partie.mettreAJour(now);
                    mettreAJourBoutonVague();
                    afficherMonstres(now);
                    afficherTours();
                    afficherRubis();

                    dernierDeplacement = now;
                }
            }
        };

        gameLoop.start();
    }


    private void creerSprite(Monstre monstre) {

        Image image = imagePourMonstre(monstre);

        if (image == null) {
            return;
        }

        ImageView sprite = new ImageView(image);

        sprite.setId("monstre");

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        Position position = monstre.getPosition();

        sprite.setLayoutX(monstre.getX());

        sprite.setLayoutY(monstre.getY());

        paneSprites.getChildren().add(sprite);
    }

    private void creerSpriteTour(Tour tour) {

        Image image = null;

        if (tour instanceof TourCanon) {

            image = new Image(Main.class.getResourceAsStream("Tours/canon.png")
            );
        }



        ImageView sprite = new ImageView(image);

        sprite.setId("tour");

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        Position position = tour.getPosition();

        sprite.setLayoutX(
                position.getColonne() * TAILLE_TUILE
        );

        sprite.setLayoutY(
                position.getLigne() * TAILLE_TUILE
        );

        paneSprites.getChildren().add(sprite);
    }

    private Image imagePourMonstre(Monstre monstre) {

        if (monstre instanceof Zombie) {

            return new Image(
                    Main.class.getResourceAsStream(
                            "Monstres/zombie.png"
                    )
            );
        }

        if (monstre instanceof Araignee) {

            return new Image(
                    Main.class.getResourceAsStream(
                            "Monstres/araignee.png"
                    )
            );
        }

        if (monstre instanceof Squelette) {

            return new Image(
                    Main.class.getResourceAsStream(
                            "Monstres/squelette.png"
                    )
            );
        }

        if (monstre instanceof Pillager) {

            return new Image(
                    Main.class.getResourceAsStream(
                            "Monstres/pillager.png"
                    )
            );
        }

        if (monstre instanceof Boss) {

            return new Image(
                    Main.class.getResourceAsStream(
                            "Monstres/boss.png"
                    )
            );
        }

        return null;
    }

    private void afficherMonstres(long now) {

        paneSprites.getChildren().removeIf(
                node -> "monstre".equals(node.getId())
        );

        for (Monstre m : partie.getMonstres()) {

            creerSprite(m);
        }
    }

    private void afficherTours() {

        paneSprites.getChildren().removeIf(node ->
                "tour".equals(node.getId())
        );

        for (Tour t : partie.getTours()) {
            creerSpriteTour(t);
        }
    }
    @FXML
    private void selectionnerCanon() {
        tourSelectionnee = "CANON";
    }

    @FXML
    private void selectionnerArcher() {
        tourSelectionnee = "ARCHER";
    }

    @FXML
    private void selectionnerGlace() {
        tourSelectionnee = "GLACE";
    }

    @FXML
    private void selectionnerPoison() {
        tourSelectionnee = "POISON";
    }

    private Tour creerTourSelectionnee(Position position) {
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

    private void afficherRubis() {
        if (labelRubis != null) {
            labelRubis.setText("Rubis : " + partie.getRubis());
        }
    }

    private void placerTourSurCarte(double x, double y) {
        int colonne = (int) (x / TAILLE_TUILE);
        int ligne = (int) (y / TAILLE_TUILE);

        Position position = new Position(colonne, ligne);
        Tour tour = creerTourSelectionnee(position);

        if (tour == null) {
            return;
        }

        boolean placee = partie.placerTour(tour, carte);

        if (placee) {
            afficherTours();
            afficherRubis();
            System.out.println("Tour placée : " + tourSelectionnee);
        } else {
            System.out.println("Impossible de poser la tour ici.");
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