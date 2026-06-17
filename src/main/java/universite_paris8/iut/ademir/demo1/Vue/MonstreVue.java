package universite_paris8.iut.ademir.demo1.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

            String id = paneSprites.getChildren().get(i).getId();

            if (id != null && (id.equals(m.getMonstreID()) || id.equals(m.getMonstreID() + "_fondVie") || id.equals(m.getMonstreID() + "_barreVie"))) {
                paneSprites.getChildren().remove(i);
            }
        }
    }


    public void creerSprite(Monstre monstre) {

        Image image = imageMonstre(monstre);

        if (image == null) {
            return;
        }

        ImageView sprite = new ImageView(image);
        sprite.setId(monstre.getMonstreID());

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        sprite.layoutXProperty().bind(monstre.xProperty());
        sprite.layoutYProperty().bind(monstre.yProperty());
        //affichage barre de vie:
        Rectangle fondVie = new Rectangle(40, 5);
        fondVie.setId(monstre.getMonstreID() + "_fondVie");
        fondVie.setFill(Color.BLACK);

        Rectangle barreVie = new Rectangle(40, 5);
        barreVie.setId(monstre.getMonstreID() + "_barreVie");
        barreVie.setFill(Color.RED);

        fondVie.layoutXProperty().bind(monstre.xProperty());
        fondVie.layoutYProperty().bind(monstre.yProperty());
        barreVie.layoutXProperty().bind(monstre.xProperty());
        barreVie.layoutYProperty().bind(monstre.yProperty());

        fondVie.setTranslateX(11);
        fondVie.setTranslateY(-8);
        barreVie.setTranslateX(11);
        barreVie.setTranslateY(-8);

        barreVie.widthProperty().bind(monstre.pvProperty().multiply(40.0 / monstre.getPvMax()));

        paneSprites.getChildren().add(fondVie);
        paneSprites.getChildren().add(barreVie);
        paneSprites.getChildren().add(sprite);
    }


    public Image imageMonstre(Monstre monstre) {

        Image image = null;

        if (monstre instanceof Zombie) {
            if(monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/zombieGlace.png"));
            }
            else {
                image = new Image(Main.class.getResourceAsStream("Monstres/zombie.png"));
            }
        } else if (monstre instanceof Araignee) {
            if(monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/araigneeGlace.png"));
            }
            else {
                image = new Image(Main.class.getResourceAsStream("Monstres/araignee.png"));
            }
        } else if (monstre instanceof Squelette) {
            if(monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/squeletteGlace.png"));
            }
            else {
                image = new Image(Main.class.getResourceAsStream("Monstres/squelette.png"));
            }
        } else if (monstre instanceof Pillager) {
            if(monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/pillagerGlace.png"));
            }
            else {
                image = new Image(Main.class.getResourceAsStream("Monstres/pillager.png"));
            }
        } else if (monstre instanceof Boss) {
            if(monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/bossGlace.png"));
            }
            else {
                image = new Image(Main.class.getResourceAsStream("Monstres/boss.png"));
            }
        }

        return image;
    }

    public void effetAffichage() {

        for (Node node : paneSprites.getChildren()) {

            if (node instanceof ImageView) {

                ImageView sprite = (ImageView) node;

                for (Monstre monstre : partie.getMonstres()) {

                    if (monstre.getMonstreID().equals(sprite.getId())) {

                        sprite.setImage(imageMonstre(monstre));

                    }
                }
            }
        }
    }

}
