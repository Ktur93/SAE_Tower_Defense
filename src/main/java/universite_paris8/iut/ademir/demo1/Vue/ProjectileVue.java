//package universite_paris8.iut.ademir.demo1.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.*;

//public class ProjectileVue {
//
//    public static final int TAILLE_TUILE = 64;
//    public static final int TAILLE_PROJECTILE = 32;
//
//    private Partie partie;
//    private Pane paneSprites;
//    private Image imageProjectile = null;
//    private ImageView sprite;
//
//    public ProjectileVue(Partie partie, Pane paneSprites) {
//        this.partie = partie;
//        this.paneSprites = paneSprites;
//        this.partie.getProjectiles().addListener(listener);
//    }
//
//    ListChangeListener<Projectile> listener = new ListChangeListener<Projectile>() {
//        @Override
//        public void onChanged(Change<? extends Projectile> c) {
//            while (c.next()) {
//                if (c.wasAdded()) {
//                    for (int i = 0; i < c.getAddedSubList().size(); i++) {
//                        Projectile projectile = c.getAddedSubList().get(i);
//                        creerSpriteProjectile(projectile);
//                    }
//                }
//
//                if (c.wasRemoved()) {
//                    enleverSpriteProjectile();
//                }
//            }
//        }
//    };
//
//    public void creerSpriteProjectile(Projectile projectile) {
//        sprite = new ImageView(imageProjectile(projectile));
//        sprite.setId("projectile");
//
//        sprite.setFitWidth(TAILLE_PROJECTILE);
//        sprite.setFitHeight(TAILLE_PROJECTILE);
//
//        sprite.setLayoutX(projectile.getPos().getX() * TAILLE_TUILE);
//        sprite.setLayoutY(projectile.getPos().getY() * TAILLE_TUILE);
//
//        paneSprites.getChildren().add(sprite);
//
//    }
//
//    private Image imageProjectile(Projectile projectile) {
//        imageProjectile = null;
//
//        if (projectile instanceof ProjectileBoulet) {
//            imageProjectile = new Image(Main.class.getResourceAsStream("Projectiles/boulet.png"));
//        } else if (projectile instanceof ProjectileFleche) {
//            imageProjectile = new Image(Main.class.getResourceAsStream("Projectiles/fleche.png"));
//        } else if (projectile instanceof ProjectileGlace) {
//            imageProjectile = new Image(Main.class.getResourceAsStream("Projectiles/glace.png"));
//        } else if (projectile instanceof ProjectilePoison) {
//            imageProjectile = new Image(Main.class.getResourceAsStream("Projectiles/poison.png"));
//        }
//
//        return imageProjectile;
//    }
//
//    public void enleverSpriteProjectile() {
//        for (int i = paneSprites.getChildren().size() - 1; i >= 0; i--) {
//            Node node = paneSprites.getChildren().get(i);
//
//            if ("projectile".equals(node.getId())) {
//                paneSprites.getChildren().remove(i);
//            }
//        }
//    }
//
//}