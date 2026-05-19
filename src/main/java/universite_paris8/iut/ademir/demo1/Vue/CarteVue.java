package universite_paris8.iut.ademir.demo1.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Carte;

public class CarteVue {

    private Carte carte;
    private TilePane paneCarte;

    public CarteVue(Carte carte, TilePane paneCarte) {
        this.carte = carte;
        this.paneCarte = paneCarte;
    }

    public void dessinerCarte() {

        Image herbe = new Image(Main.class.getResourceAsStream("tuile/herbe.png"));
        Image cheminD = new Image(Main.class.getResourceAsStream("tuile/chemin-D.png"));
        Image cheminL = new Image(Main.class.getResourceAsStream("tuile/chemin-L.png"));
        Image cheminT = new Image(Main.class.getResourceAsStream("tuile/chemin-T.png"));


        int tailleTuile = 32;

        for (int ligne = 0; ligne < carte.hauteur(); ligne++){

            for (int col = 0; col < carte.largeur();col++){

                Image image = null;

                switch (carte.codeTuile(col,ligne)) {

                    case 0:
                        image = herbe;
                        break;

                    case 1:
                        image = cheminD;
                        break;

                    case 2:
                        image = cheminL;
                        break;

                    case 3:
                        image = cheminT;
                        break;
                }

                ImageView tuile = new ImageView(image);

//                tuile.setFitWidth(tailleTuile);
//                tuile.setFitHeight(tailleTuile);
//
//                tuile.setLayoutX(x * tailleTuile);
//                tuile.setLayoutY(y * tailleTuile);

                paneCarte.getChildren().add(tuile);
            }
        }
    }
}
