package universite_paris8.iut.ademir.demo1.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import universite_paris8.iut.ademir.demo1.Modele.Algorithmes.BFS;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;
import universite_paris8.iut.ademir.demo1.Vue.CarteVue;
import universite_paris8.iut.ademir.demo1.Vue.MonstreVue;
import universite_paris8.iut.ademir.demo1.Vue.RubisVue;
import universite_paris8.iut.ademir.demo1.Vue.ToursVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int TAILLE_TUILE = 64;

    @FXML
    private Button btnRecommencer;
    @FXML
    private TilePane paneCarte;
    @FXML
    private Pane paneSprites;
    @FXML
    private Pane paneDecoration;
    @FXML
    private Label labelRubis;
    @FXML
    private Button btnCanon;
    @FXML
    private Button btnArcher;
    @FXML
    private Button btnGlace;
    @FXML
    private Button btnPoison;
    @FXML
    private Button btnVague;
    @FXML
    private Button btnAcheterCase;
    @FXML
    private Button btnAmeliorer;

    private Carte carte;
    private Partie partie;
    private String tourSelectionne;
    private boolean achatCase = false;
    private boolean ameliorerTour = false;
    private CarteVue carteVue;
    private boolean defaiteLance;
    private ToursVue toursVue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carte = new Carte();
        this.carteVue = new CarteVue(carte, paneCarte , paneDecoration);
        carteVue.dessinerCarte();
        Label labelDefaite = new Label("Vous etes mort ! ");


        Position depart = new Position(0, 7);
        Position depart2 = new Position(12, 0);
        Position depart3 = new Position(8, 15);
        Position arrivee = carte.trouverArrivee();
        BFS bfs = new BFS(carte, depart);
        BFS bfs2 = new BFS(carte,depart2);
        BFS bfs3 = new BFS(carte,depart3);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);
        ArrayList<Position> chemin2 = bfs2.cheminDeSourceVersCible(arrivee);
        ArrayList<Position> chemin3 = bfs3.cheminDeSourceVersCible(arrivee);
        partie = new Partie(chemin,chemin2,chemin3);


        RubisVue rubisVue = new RubisVue(partie, labelRubis);
        rubisVue.afficherRubis();


         // 5 secondes




        //bouton pour lancer la vague/lancement du gameLoop/deplacement des monstre:
        btnVague.setOnAction(actionEvent -> {
            partie.lancerProchaineVague();
        });

        MonstreVue monstreVue = new MonstreVue(partie, paneSprites);

        AnimationTimer gameLoop = new AnimationTimer() {
            long dernierDeplacement = 0;
            boolean defaiteLanceBoucle = false;
            long momentDefaite = 0;
            public void handle(long tempActuel) {

                if (tempActuel - dernierDeplacement > 20_000_000) {

                    partie.mettreAJour(tempActuel);
                    //monstreVue.mettreAJourSprites();

                    mettreAJourBoutonVague();
                    mettreAJourBoutonRecommencer();
                    rubisVue.afficherRubis();
                    dernierDeplacement = tempActuel;
                    btnAcheterCase.setText("Acheter case - " + partie.getPrixCase());
                    if (partie.portailMort()) {
                        if (!(partie.getVagueEnCours())) {
                            if (!defaiteLanceBoucle) {
                                carteVue.ajouterEcranDefaite();
                                momentDefaite = tempActuel;
                                defaiteLanceBoucle = true;
                                desactiverToutLesBoutons();
                            }

                            if (tempActuel - momentDefaite >= 5_000_000_000L) {
                                carteVue.retirerEcranDefaite();
                                recommencer();
                                defaiteLanceBoucle = false;
                                activerToutLesBoutons();
                            }
                        }
                    }
                }
            }
        };
        gameLoop.start();

        //pour recommencer
        btnRecommencer.setOnAction(actionEvent -> {
            recommencer();
        });



        // pour selectionnée les tours via des boutons:
        btnCanon.setOnAction(actionEvent -> {
            tourSelectionne = "CANON";
        });

        btnArcher.setOnAction(actionEvent -> {
            tourSelectionne = "ARCHER";
        });

        btnGlace.setOnAction(actionEvent -> {
            tourSelectionne = "GLACE";
        });

        btnPoison.setOnAction(actionEvent -> {
            tourSelectionne = "POISON";
        });

        ToursVue T = new ToursVue(partie, paneSprites);


        btnAcheterCase.setOnAction(actionEvent -> {
            achatCase = true;
        });

        btnAmeliorer.setOnAction(actionEvent -> {
            ameliorerTour = true;
            System.out.println("click");
        });




        paneCarte.setOnMouseClicked(event -> {


            int colonne = (int) (event.getX() / TAILLE_TUILE);
            int ligne = (int) (event.getY() / TAILLE_TUILE);


            Position position = new Position(colonne, ligne);

            if (achatCase == true) {
                partie.acheterCase(position , carte);
                carteVue.viderCarte();
                carteVue.dessinerCarte();
                rubisVue.afficherRubis();
                achatCase = false;
                return;
            }


            Tour tour = creerTourSelectionnee(tourSelectionne, position);

            boolean placeDisponible = partie.placerTour(tour, carte);

            if (placeDisponible) {
                rubisVue.afficherRubis();
                System.out.println("placement");
            } else {
                System.out.println("null");
            }

        });

        paneSprites.setOnMouseClicked(event -> {

            int colonne = (int) (event.getX() / TAILLE_TUILE);
            int ligne = (int) (event.getY() / TAILLE_TUILE);

            Position position = new Position(colonne, ligne);

            if(ameliorerTour == true){
                int i = 0;
                while (i < partie.getTours().size()) {
                    System.out.println("envoyer");
                    Tour tour = partie.getTours().get(i);

                    if (tour.getPosition().equals(position)) {
                        partie.faireAmeliorerTours(tour);
                        System.out.println("envoyer");
                    }

                    i++;
                }
                ameliorerTour = false;

            }
        }
        );
    }



    public Tour creerTourSelectionnee(String tourSelectionnee, Position position) {
        switch (tourSelectionnee) {
            case "CANON":
                return new TourCanon(position);
            case "ARCHER":
                return new TourArcher(position);
            case "GLACE":
                return new TourGlace(position);
            case "POISON":
                return new TourPoison(position);
            default:
                return null;
        }
    }


    public void mettreAJourBoutonVague() {
        if (partie.toutesLesVaguesTerminees()) {
            btnVague.setDisable(true);
            btnVague.setText("Toutes les vagues sont terminees");
        } else if (partie.getVagueEnCours()) {
            btnVague.setDisable(true);
            btnVague.setText("Vague " + partie.getIndiceVaguePlusUn() + " En cours");
        } else if (this.defaiteLance) {
            btnVague.setDisable(true);
        } else {
            btnVague.setDisable(false);
            btnVague.setText("Lancer vague " + partie.getIndiceVaguePlusUn());
        }


    }

    public void mettreAJourBoutonRecommencer() {
        if (partie.getVagueEnCours()) {
            btnRecommencer.setDisable(true);
        } else if (this.defaiteLance) {
            btnRecommencer.setDisable(true);
        } else {
            btnRecommencer.setDisable(false);
        }
    }

    public void recommencer() {
        partie.recommnencer();
        carte.caseBloquer();
        carteVue.viderCarte();
        carteVue.dessinerCarte();
    }

    public void desactiverToutLesBoutons() {
        btnRecommencer.setDisable(true);
        btnVague.setDisable(true);
        btnArcher.setDisable(true);
        btnCanon.setDisable(true);
        btnAcheterCase.setDisable(true);
        btnGlace.setDisable(true);
        btnRecommencer.setDisable(true);
        btnPoison.setDisable(true);
        this.defaiteLance = true;
    }

    public void activerToutLesBoutons() {
        btnRecommencer.setDisable(false);
        btnVague.setDisable(false);
        btnArcher.setDisable(false);
        btnCanon.setDisable(false);
        btnAcheterCase.setDisable(false);
        btnGlace.setDisable(false);
        btnRecommencer.setDisable(false);
        btnPoison.setDisable(false);
        this.defaiteLance = false;
    }
}