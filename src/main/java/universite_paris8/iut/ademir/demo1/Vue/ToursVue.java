package universite_paris8.iut.ademir.demo1.Vue;


import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;

public class ToursVue {
    public static final int TAILLE_TUILE = 64;

    private Partie partie;
    private Pane paneSprites;

    public ToursVue(Partie partie , Pane paneSprites) {
        this.partie = partie;
        this.paneSprites = paneSprites;

        this.partie.getTours().addListener((ListChangeListener<Tour>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Tour tour : change.getAddedSubList()) {
                        creerSpriteTour(tour);
                    }
                }
            }
        });
    }




    public void creerSpriteTour(Tour tour) {

        Image imageTour = null;

        if (tour instanceof TourCanon) {
            imageTour = new Image(Main.class.getResourceAsStream("Tours/canon.png"));
        }

        if (tour instanceof TourArcher) {
            imageTour = new Image(Main.class.getResourceAsStream("Tours/archer.png"));
        }

        if (tour instanceof TourGlace) {
            imageTour = new Image(Main.class.getResourceAsStream("Tours/glace.png"));
        }

        if (tour instanceof TourPoison) {
            imageTour = new Image(Main.class.getResourceAsStream("Tours/poison.png"));
        }


        ImageView sprite = new ImageView(imageTour);

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        Position position = tour.getPosition();

        sprite.setLayoutX(position.getX() * TAILLE_TUILE);

        sprite.setLayoutY(position.getY() * TAILLE_TUILE);

        paneSprites.getChildren().add(sprite);

    }
}
