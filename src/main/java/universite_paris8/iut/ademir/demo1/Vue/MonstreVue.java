package universite_paris8.iut.ademir.demo1.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;

public class MonstreVue {
    private Partie partie;
    private Pane paneSprites;


    public MonstreVue(Partie partie , Pane paneSprites) {
        this.partie = partie;
        this.paneSprites = paneSprites;
    }


    private void creerSprite(Monstre monstre) {

        Image image = imageDeMonstre(monstre);


        ImageView sprite = new ImageView(image);
        sprite.setId("monstre");

        sprite.setFitWidth(64);
        sprite.setFitHeight(64);

        Position position = monstre.getPosition();

        sprite.setLayoutX(position.getX() * 64);
        sprite.setLayoutY(position.getY() * 64);

        paneSprites.getChildren().add(sprite);
    }


    public Image imageDeMonstre(Monstre monstre) {
        Image image = null;
        if (monstre instanceof Zombie) {
            image = new Image(Main.class.getResourceAsStream("Monstres/zombie.png"));
        } else if (monstre instanceof Araignee) {
            image = new Image(Main.class.getResourceAsStream("Monstres/araignee.png"));

        } else if (monstre instanceof Squelette) {
            image = new Image(Main.class.getResourceAsStream("Monstres/squelettes.png"));

        } else if (monstre instanceof Pillager) {
            image = new Image(Main.class.getResourceAsStream("Monstres/pillager.png"));
        } else if (monstre instanceof Boss) {
            image = new Image(Main.class.getResourceAsStream("Monstres/boss.png"));
        }
        return image;
    }


    private void afficherMonstres() {
        paneSprites.getChildren().removeIf(node -> "monstre".equals(node.getId()));

        for (int i = 0; i < partie.getMonstres().size(); i++) {
            Monstre m = partie.getMonstres().get(i);
            creerSprite(m);
        }
    }
}
