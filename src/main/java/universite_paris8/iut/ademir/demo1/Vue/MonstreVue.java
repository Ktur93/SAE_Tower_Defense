package universite_paris8.iut.ademir.demo1.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;

import java.util.HashMap;
import java.util.Map;

public class MonstreVue {

    private static final int TAILLE_TUILE = 64;
    private Partie partie;
    private Pane paneSprites;
    private Map<Monstre, ImageView> spritesMonstres;

    public MonstreVue(Partie partie, Pane paneSprites) {
        this.partie = partie;
        this.paneSprites = paneSprites;
        this.spritesMonstres = new HashMap<>();
        this.partie.getMonstres().addListener(listener);
    }

    ListChangeListener<Monstre> listener = new ListChangeListener<Monstre>() {

        public void onChanged(Change<? extends Monstre> c) {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (int i = 0; i < c.getAddedSubList().size(); i++) {
                        Monstre monstre = c.getAddedSubList().get(i);
                        creerSprite(monstre);
                    }
                }
                if (c.wasRemoved()) {
                    for (int i = 0; i < c.getRemoved().size(); i++) {
                        Monstre monstre = c.getRemoved().get(i);
                        supprimerSprite(monstre);
                    }
                }
            }
        }
    };

    public void creerSprite(Monstre monstre) {
        Image image = imageMonstre(monstre);

        if (image == null) {
            return;
        }

        ImageView sprite = new ImageView(image);

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        sprite.setLayoutX(monstre.getX());
        sprite.setLayoutY(monstre.getY());

        spritesMonstres.put(monstre, sprite);
        paneSprites.getChildren().add(sprite);
    }

    public void supprimerSprite(Monstre monstre) {

        ImageView sprite = spritesMonstres.get(monstre);

        if (sprite != null) {
            paneSprites.getChildren().remove(sprite);
            spritesMonstres.remove(monstre);
        }
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
        for(int i = 0 ; i < partie.getMonstres().size() ; i++){
            Monstre monstre = partie.getMonstres().get(i);
            ImageView sprite = spritesMonstres.get(monstre);
            if (sprite != null) {
                sprite.setLayoutX(monstre.getX());
                sprite.setLayoutY(monstre.getY());
            }
        }
    }
}
