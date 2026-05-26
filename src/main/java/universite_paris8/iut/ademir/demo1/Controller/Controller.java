package universite_paris8.iut.ademir.demo1.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Vue.CarteVue;
import universite_paris8.iut.ademir.demo1.Modele.Algorithmes.BFS;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TilePane paneCarte;
    private Carte carte;
    private Partie partie;
    private Pane paneSprites;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carte = new Carte();
        carte.Remplir();
        CarteVue carteVue = new CarteVue(carte, paneCarte);
        carteVue.dessinerCarte();
        partie = new Partie();
        partie.ajouterZombie();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                partie.MettreAJour();
                afficherMonstres();
            }
        };
        gameLoop.start();
    }

    private void creerSprite(Monstre monstre) {
        Image image = null;
        if (monstre instanceof Zombie) {
            image = new Image(Main.class.getResourceAsStream("Monstres/zombie.png"));
        }
        else if(monstre instanceof Araignee){
            image = new Image(Main.class.getResourceAsStream("Monstres/araignee.png"));
        }

        else if(monstre instanceof Squelette){
            image = new Image(Main.class.getResourceAsStream("Monstres/squelette.png"));
        }

        else if(monstre instanceof Pillager){
            image = new Image(Main.class.getResourceAsStream("Monstres/pillager.png"));
        }

        else if(monstre instanceof Boss){
            image = new Image(Main.class.getResourceAsStream("Monstres/boss.png"));
        }
        ImageView sprite = new ImageView(image);

        sprite.setId("monstre");

        sprite.setFitWidth(64);
        sprite.setFitHeight(64);

        sprite.setTranslateX(monstre.getX());
        sprite.setTranslateY(monstre.getY());

        paneCarte.getChildren().add(sprite);
    }

    private void afficherMonstres() {
        paneCarte.getChildren().removeIf(node -> "monstre".equals(node.getId()));

        paneCarte.getChildren().removeIf(node ->
                "monstre".equals(node.getId())
        );
        for(Monstre m : partie.getMonstres()){
            creerSprite(m);
        }
    }
}
