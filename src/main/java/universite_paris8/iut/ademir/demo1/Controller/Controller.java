package universite_paris8.iut.ademir.demo1.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import universite_paris8.iut.ademir.demo1.Vue.CarteVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int TAILLE_TUILE = 64;

    @FXML
    private TilePane paneCarte;

    @FXML
    private Pane paneSprites;

    private Carte carte;
    private Partie partie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carte = new Carte();
        carte.remplir();

        CarteVue carteVue = new CarteVue(carte, paneCarte);
        carteVue.dessinerCarte();

        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();

        if (arrivee == null) {
            System.out.println("Erreur : aucune arrivée trouvée dans la carte.");
            return;
        }

        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);

        System.out.println("Départ : " + depart);
        System.out.println("Arrivée : " + arrivee);
        System.out.println("Chemin BFS : " + chemin);

        partie = new Partie(chemin);
        partie.ajouterZombie();
        afficherMonstres();

        AnimationTimer gameLoop = new AnimationTimer() {
            private long dernierDeplacement = 0;

            @Override
            public void handle(long now) {
                if (now - dernierDeplacement > 500_000_000) {
                    partie.mettreAJour();
                    afficherMonstres();
                    dernierDeplacement = now;
                }
            }
        };

        gameLoop.start();
    }

    private void creerSprite(Monstre monstre) {
        Image image = imagePourMonstre(monstre);

        if (image == null) {
            System.out.println("Image introuvable pour : " + monstre.getClass().getSimpleName());
            return;
        }

        ImageView sprite = new ImageView(image);

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        Position position = monstre.getPosition();

        sprite.setLayoutX(position.getColonne() * TAILLE_TUILE);
        sprite.setLayoutY(position.getLigne() * TAILLE_TUILE);

        paneSprites.getChildren().add(sprite);
    }

    private Image imagePourMonstre(Monstre monstre) {
        if (monstre instanceof Zombie) {
            return new Image(Main.class.getResourceAsStream("Monstres/zombie.png"));
        }

        if (monstre instanceof Araignee) {
            return new Image(Main.class.getResourceAsStream("Monstres/araignee.png"));
        }

        if (monstre instanceof Squelette) {
            return new Image(Main.class.getResourceAsStream("Monstres/squelettes.png"));
        }

        if (monstre instanceof Pillager) {
            return new Image(Main.class.getResourceAsStream("Monstres/pillager.png"));
        }

        if (monstre instanceof Boss) {
            return new Image(Main.class.getResourceAsStream("Monstres/boss.png"));
        }

        return null;
    }

    private void afficherMonstres() {
        paneSprites.getChildren().clear();

        for (Monstre m : partie.getMonstres()) {
            creerSprite(m);
        }
    }
}