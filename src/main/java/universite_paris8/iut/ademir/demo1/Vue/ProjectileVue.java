package universite_paris8.iut.ademir.demo1.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.*;

public class ProjectileVue {

    private static final int TAILLE_PROJECTILE = 64;

    private Partie partie;
    private Pane paneSprites;

    public ProjectileVue(Partie partie, Pane paneSprites) {
        this.partie = partie;
        this.paneSprites = paneSprites;
        this.partie.getProjectiles().addListener(listener);
    }

    private ListChangeListener<Projectile> listener = new ListChangeListener<Projectile>() {

        @Override
        public void onChanged(Change<? extends Projectile> c) {

            while (c.next()) {

                if (c.wasAdded()) {
                    for (int i = 0; i < c.getAddedSubList().size(); i++) {
                        Projectile projectile = c.getAddedSubList().get(i);
                        creerSpriteProjectile(projectile);
                    }
                }

                if (c.wasRemoved()) {
                    supprimerSpritesProjectiles();
                    afficherProjectiles();
                }
            }
        }
    };

    public void creerSpriteProjectile(Projectile projectile) {

        Image image = imageProjectile(projectile);

        if (image == null) {
            return;
        }

        ImageView sprite = new ImageView(image);

        sprite.setId("projectile");

        sprite.setFitWidth(TAILLE_PROJECTILE);
        sprite.setFitHeight(TAILLE_PROJECTILE);

        sprite.layoutXProperty().bind(projectile.xProperty());
        sprite.layoutYProperty().bind(projectile.yProperty());

        paneSprites.getChildren().add(sprite);
    }

    public Image imageProjectile(Projectile projectile) {

        Image image = null;

        if (projectile instanceof ProjectileBoulet) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/boulet.png"));
        }
        if (projectile instanceof ProjectilePoison) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/poison.png"));
        }
        if (projectile instanceof ProjectileGlace) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/glace.png"));
        }
        if (projectile instanceof ProjectileFleche) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/fleche.png"));
        }

        return image;
    }

    public void supprimerSpritesProjectiles() {
        for (int i = paneSprites.getChildren().size() - 1; i >= 0; i--) {

            if ("projectile".equals(paneSprites.getChildren().get(i).getId())) {
                paneSprites.getChildren().remove(i);
            }
        }
    }

    public void afficherProjectiles() {
        for (int i = 0; i < partie.getProjectiles().size(); i++) {
            Projectile projectile = partie.getProjectiles().get(i);
            creerSpriteProjectile(projectile);
        }
    }
}