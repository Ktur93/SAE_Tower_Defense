package universite_paris8.iut.ademir.demo1.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;

public class MonstreVue {

    private static final int TAILLE_TUILE = 64;

    private Partie partie;
    private Pane paneSprites;


    public MonstreVue(Partie partie, Pane paneSprites) {
        this.partie = partie;
        this.paneSprites = paneSprites;
        this.partie.getMonstres().addListener(listener);
    }

    ListChangeListener<Monstre> listener = new ListChangeListener<Monstre>() {

        public void onChanged(Change<? extends Monstre> c) {

            while (c.next()) {

                if (c.wasAdded()) {
                    creerSprite(c.getAddedSubList().get(0));
                }

                if (c.wasRemoved()) {
                    supprimerAffichageMonstre(c.getRemoved().get(0));
                }
            }
        }
    };



    public void supprimerAffichageMonstre(Monstre m){
        for (int i = paneSprites.getChildren().size() - 1; i >= 0; i--) {

            Node node = paneSprites.getChildren().get(i);

            if ((m.getMonstreID()).equals(node.getId()) ) {
                paneSprites.getChildren().remove(i);
                paneSprites.getChildren().remove(i);
            }
        }
    }

    public void creerSprite(Monstre monstre) {
        Label vie = new Label();
        vie.setTextFill(new Color(1,0,0,1));
        vie.setStyle("-fx-font-weight: bold;");
        Image image = imageMonstre(monstre);
        ImageView sprite = new ImageView(image);
        sprite.setId(monstre.getMonstreID());


        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        sprite.layoutXProperty().bind(monstre.xProperty());
        sprite.layoutYProperty().bind(monstre.yProperty());
        vie.textProperty().bind(monstre.pvProperty().asString());
        vie.layoutXProperty().bind(monstre.xProperty());
        vie.layoutYProperty().bind(monstre.yProperty());
        vie.setTranslateY(-20);
        vie.setTranslateX(17);
        paneSprites.getChildren().add(sprite);
        paneSprites.getChildren().add(vie);
    }

    public Image imageMonstre(Monstre monstre) {

        Image image = null;

        if (monstre instanceof Zombie) {
            image = new Image(Main.class.getResourceAsStream("Monstres/zombie.png"));
        } else if (monstre instanceof Araignee) {
            image = new Image(Main.class.getResourceAsStream("Monstres/araignee.png"));
        } else if (monstre instanceof Squelette) {
            image = new Image(Main.class.getResourceAsStream("Monstres/squelette.png"));
        } else if (monstre instanceof Pillager) {
            image = new Image(Main.class.getResourceAsStream("Monstres/pillager.png"));
        } else if (monstre instanceof Boss) {
            image = new Image(Main.class.getResourceAsStream("Monstres/boss.png"));
        }

        return image;
    }

}
