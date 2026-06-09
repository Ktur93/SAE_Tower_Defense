package universite_paris8.iut.ademir.demo1.Vue;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;

public class ToursVue {
    public static final int TAILLE_TUILE = 64;

    private Partie partie;
    private Pane paneSprites;

    public ToursVue(Partie partie , Pane paneSprites) {
        this.partie = partie;
        this.paneSprites = paneSprites;
        this.partie.getTours().addListener(listener);
    }

    ListChangeListener<Tour> listener = new ListChangeListener<Tour>() {

        public void onChanged(Change<? extends Tour> c) {

            while (c.next()) {
                if (c.wasAdded()) {
                    for (int i = 0; i < c.getAddedSubList().size(); i++) {
                        Tour tour = c.getAddedSubList().get(i);
                        creerSpriteTour(tour);
                    }
                }

                if (c.wasRemoved()) {
                    enleverSpriteTour();
                }
            }
        }
    };



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
        Position position = tour.getPosition();

        Label nivL = new Label();
        nivL.setText("niveau: " + tour.getNivT());
        nivL.setTextFill(Color.WHITE);
        nivL.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
        nivL.setLayoutX(position.getX() * TAILLE_TUILE);
        nivL.setLayoutY(position.getY() * TAILLE_TUILE - 20);
        paneSprites.getChildren().add(nivL);

        nivL.textProperty().bind(tour.nivTProperty().asString("niveau: %d"));


        ImageView sprite = new ImageView(imageTour);
        sprite.setId("tour");

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        sprite.setLayoutX(position.getX() * TAILLE_TUILE);
        sprite.setLayoutY(position.getY() * TAILLE_TUILE);


        paneSprites.getChildren().add(sprite);

    }

    public void enleverSpriteTour(){
        for (int i = paneSprites.getChildren().size() - 1; i >= 0; i--) {

            Node node = paneSprites.getChildren().get(i);

            if ("tour".equals(node.getId())) {
                paneSprites.getChildren().remove(i);
            }
        }
    }
}
