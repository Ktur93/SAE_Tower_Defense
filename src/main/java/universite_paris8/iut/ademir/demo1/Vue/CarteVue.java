package universite_paris8.iut.ademir.demo1.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;

public class CarteVue {

    private Carte carte;
    private TilePane paneCarte;
    private Pane paneDécoration;

    public CarteVue(Carte carte, TilePane paneCarte , Pane paneDécoration) {
        this.carte = carte;
        this.paneCarte = paneCarte;
        this.paneDécoration = paneDécoration;
    }

    public void dessinerCarte() {

        Image herbe = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/herbe.png"));

        Image cheminD = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-D/chemin-D.png"));
        Image cheminD90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-D/chemin-D90.png"));

        Image cheminL = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-L/chemin-L.png"));
        Image cheminL45 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-L/chemin-L45.png"));
        Image cheminL90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-L/chemin-L90.png"));
        Image cheminL180 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-L/chemin-L180.png"));

        Image cheminT = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-T/chemin-T.png"));
        Image cheminT45 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-T/chemin-T45.png"));
        Image cheminT90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-T/chemin-T90.png"));
        Image cheminT180 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-T/chemin-T180.png"));

        Image cheminArriver = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Arriver/chemin-Arriver.png"));
        Image cheminArriver45 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Arriver/chemin-Arriver45.png"));
        Image cheminArriver90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Arriver/chemin-Arriver90.png"));
        Image cheminArriver180 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Arriver/chemin-Arriver180.png"));

        Image cheminDepart = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Depart/chemin-Depart.png"));
        Image cheminDepart45 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Depart/chemin-Depart45.png"));
        Image cheminDepart90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Depart/chemin-Depart90.png"));
        Image cheminDepart180 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Depart/chemin-Depart180.png"));

        Image EmplacementToursDébloquer = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/EmplacementTours/TDébloquer.png"));
        Image EmplacementToursBloquer = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/EmplacementTours/TBloquer.png"));

        Image eau = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/eau/Uberren.gif"));
        Image arbre = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/eau/Uberren.gif"));
        Image pierre = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/eau/Uberren.gif"));



        int tailleTuile = 64;

        for (int ligne = 0; ligne < carte.getHauteur(); ligne++) {

            for (int col = 0; col < carte.getLargeur(); col++) {

                Image image = null;

                switch (carte.Tuile(col, ligne)) {

                    case 0:
                        image = herbe;
                        break;


                    case 1:
                        image = cheminD;
                        break;
                    case 2:
                        image = cheminD90;
                        break;


                    case 3:
                        image = cheminL;
                        break;

                    case 4:
                        image = cheminL45;
                        break;

                    case 5:
                        image = cheminL90;
                        break;

                    case 6:
                        image = cheminL180;
                        break;

                    case 7:
                        image = cheminT;
                        break;

                    case 8:
                        image = cheminT45;
                        break;

                    case 9:
                        image = cheminT90;
                        break;

                    case 10:
                        image = cheminT180;
                        break;

                    case 11:
                        image = cheminArriver;
                        break;

                    case 12:
                        image = cheminArriver45;
                        break;

                    case 13:
                        image = cheminArriver90;
                        break;

                    case 14:
                        image = cheminArriver180;
                        break;

                    case 15:
                        image = cheminDepart;
                        break;

                    case 16:
                        image = cheminDepart45;
                        break;

                    case 17:
                        image = cheminDepart90;
                        break;

                    case 18:
                        image = cheminDepart180;
                        break;

                    case 19:
                        image = EmplacementToursDébloquer;
                        break;

                    case 20:
                        image = EmplacementToursBloquer;
                        break;

                    case 21:
                        image = eau;
                        break;

                    case 22:
                        image = arbre;
                        break;

                    case 23:
                        image = pierre;
                        break;

                }
                ImageView tuile = new ImageView(image);
                tuile.setFitWidth(tailleTuile);
                tuile.setFitHeight(tailleTuile);
                paneCarte.getChildren().add(tuile);

            }
        }

        for (int ligne = 0; ligne < carte.getHauteurDécoration(); ligne++) {

            for (int col = 0; col < carte.getLargeurDécoration(); col++) {

                Image image = null;

                switch (carte.TuileDécorations(col, ligne)) {

                    case 22:
                        image = arbre;
                        break;

                    case 23:
                        image = pierre;
                        break;

                }
                ImageView tuileDecoration = new ImageView(image);

                tuileDecoration.setFitWidth(tailleTuile);
                tuileDecoration.setFitHeight(tailleTuile);
                tuileDecoration.setLayoutX(col * tailleTuile);
                tuileDecoration.setLayoutY(ligne * tailleTuile);
                paneDécoration.getChildren().add(tuileDecoration);
            }
        }
    }

    public void viderCarte() {
        paneCarte.getChildren().clear();
    }




}
