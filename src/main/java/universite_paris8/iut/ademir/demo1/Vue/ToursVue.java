package universite_paris8.iut.ademir.demo1.Vue;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;

public class ToursVue {
    public static final int TAILLE_TUILE = 64;

    private Partie partie;
    private Carte carte;
    private Pane paneSprites;
    private ObservableList<Tour> tourSelectionnés;

    public ToursVue(Partie partie , Carte carte , Pane paneSprites , ObservableList<Tour> tourSelectionnés) {
        this.partie = partie;
        this.carte = carte;
        this.paneSprites = paneSprites;
        this.tourSelectionnés = FXCollections.observableArrayList();
    }


    public void placerTourSurCarte(double x, double y){
         //colonne
         int i = (int) (x / TAILLE_TUILE);
         //ligne
         int j = (int) (y / TAILLE_TUILE);

        Position position = new Position(i , j);

        if (tour == null) {
            return;
        }

        boolean place = partie.placerTour(tour, carte);

        if (place) {
            afficherTours();
            System.out.println("Tour placée : " + tourSelectionnés);
        } else {
            System.out.println("Impossible de poser la tour ici.");
        }
    }


    public void creerSpriteTour(Tour tour) {

        Image image = null;

        if (tour instanceof TourCanon) {
            image = new Image(Main.class.getResourceAsStream("Tours/canon.png"));
        }

        if (image == null) {
            return;
        }

        ImageView sprite = new ImageView(image);

        sprite.setId("tour");

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        Position position = tour.getPosition();

        sprite.setLayoutX(position.getX() * TAILLE_TUILE);

        sprite.setLayoutY(position.getY() * TAILLE_TUILE);

        paneSprites.getChildren().add(sprite);
    }


    public void afficherTours() {

        paneSprites.getChildren().removeIf(node ->
                "tour".equals(node.getId())
        );

        for (Tour t : partie.getTours()) {
            creerSpriteTour(t);
        }
    }

}
