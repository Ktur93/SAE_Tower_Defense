package universite_paris8.iut.ademir.demo1.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
                    afficherMonstres();
                }

                if (c.wasRemoved()) {
                    supprimerAffichageMonstre();
                }
            }
        }
    };

    public void afficherMonstres() {
        for (int i = 0; i < partie.getMonstres().size(); i++) {
            Monstre monstre = partie.getMonstres().get(i);
            creerSprite(monstre);
        }
    }

    public void supprimerAffichageMonstre(){
        for (int i = paneSprites.getChildren().size() - 1; i >= 0; i--) {

            Node node = paneSprites.getChildren().get(i);

            if ("monstre".equals(node.getId())) {
                paneSprites.getChildren().remove(i);
            }
        }
    }

    public void creerSprite(Monstre monstre) {

        Image image = imageMonstre(monstre);
        ImageView sprite = new ImageView(image);
        sprite.setId("monstre");

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);
        sprite.setLayoutX(monstre.getX());
        sprite.setLayoutY(monstre.getY());

        paneSprites.getChildren().add(sprite);
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

    public void mettreAJourSprites() {
        for (int i = paneSprites.getChildren().size() - 1; i >= 0; i--) {

            Node node = paneSprites.getChildren().get(i);

            if ("monstre".equals(node.getId())) {
                paneSprites.getChildren().remove(i);
            }
        }
        afficherMonstres();
    }
}
