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

        Image herbe = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/herbe.png"));
        Image cheminD = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/chemin-D.png"));
        Image cheminL = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/chemin-L.png"));
        Image cheminT = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/chemin-T.png"));
        Image cheminArriver = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/chemin-Depart.png"));
        Image cheminDépart = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/chemin-Arriver.png"));

        int tailleTuile = 64;

        for (int ligne = 0; ligne < carte.hauteur(); ligne++){

            for (int col = 0; col < carte.largeur(); col++){

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

                    case 4:
                        image = cheminArriver;
                        break;

                    case 5:
                        image = cheminDépart;
                        break;
                }

                ImageView tuile = new ImageView(image);

                tuile.setFitWidth(tailleTuile);
                tuile.setFitHeight(tailleTuile);

                paneCarte.getChildren().add(tuile);
            }
        }
    }
}
