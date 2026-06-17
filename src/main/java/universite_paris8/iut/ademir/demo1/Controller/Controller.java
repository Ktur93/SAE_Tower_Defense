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
import universite_paris8.iut.ademir.demo1.Vue.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private static final int TAILLE_TUILE = 64;

    // Pane
    @FXML
    private TilePane paneCarte;
    @FXML
    private Pane paneSprites;
    @FXML
    private Pane paneCoeurs;
    @FXML
    private Pane paneDecoration;

    // Label
    @FXML
    private Label labelRubis;

    // Boutons
    @FXML
    private Button btnRecommencer;
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
    private CarteVue carteVue;
    private String tourSelectionne;
    private boolean achatCase = false;
    private boolean ameliorerTour = false;
    private boolean defaiteLance;

    private CoeurVue coeurVue;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carte = new Carte();
        this.carteVue = new CarteVue(carte, paneCarte , paneDecoration);
        carteVue.dessinerCarte();

        // Positions
        Position depart = new Position(0, 7);
        Position depart2 = new Position(12, 0);
        Position depart3 = new Position(8, 15);
        Position arrivee = carte.trouverArrivee();

        // BFS
        BFS bfs = new BFS(carte, depart);
        BFS bfs2 = new BFS(carte,depart2);
        BFS bfs3 = new BFS(carte,depart3);

        // Chemins
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);
        ArrayList<Position> chemin2 = bfs2.cheminDeSourceVersCible(arrivee);
        ArrayList<Position> chemin3 = bfs3.cheminDeSourceVersCible(arrivee);

        // Creation de la partie
        partie = new Partie(chemin,chemin2,chemin3);

        // Creation de RubisVue
        RubisVue rubisVue = new RubisVue(partie, labelRubis);
        rubisVue.afficherRubis();


        // Creation de MonstreVue
        MonstreVue monstreVue = new MonstreVue(partie, paneSprites);

        // Creation de ToursVue
        ToursVue T = new ToursVue(partie, paneSprites);

        // Creation de ProjectileVue
        ProjectileVue projectileVue = new ProjectileVue(partie, paneSprites);




        coeurVue = new CoeurVue(partie, paneCoeurs);


        // Initialisation des listeners
        initialiserListeners();

        // Bouton pour lancer la vague/lancement du gameLoop/deplacement des monstre:
        btnVague.setOnAction(actionEvent -> {
            partie.lancerProchaineVague();
        });


        // Bouton pour recommencer
        btnRecommencer.setOnAction(actionEvent -> {
            recommencer();
        });


        // Bouton pour selectionnée les tours via des boutons:
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

        btnAcheterCase.setOnAction(actionEvent -> {
            achatCase = true;
        });

        btnAmeliorer.setOnAction(actionEvent -> {
            ameliorerTour = true;
            // System.out.println("click");
        });




        paneCarte.setOnMouseClicked(event -> {
            // Sauvegarde de la position ou l'utilisateur a cliqué
            int colonne = (int) (event.getX() / TAILLE_TUILE);
            int ligne = (int) (event.getY() / TAILLE_TUILE);
            Position position = new Position(colonne, ligne);

            // Achete la case si possible
            if (achatCase) {
                partie.acheterCase(position , carte);
                carteVue.viderCarte();
                carteVue.dessinerCarte();
                rubisVue.afficherRubis();
                achatCase = false;
                return;
            }

            // Creation de la tour a partir des coordonées
            Tour tour = creerTourSelectionnee(tourSelectionne, position);

            // Verification de si la carte est placable
            boolean placeDisponible = partie.placerTour(tour, carte);

            // Si oui on place la tour
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
                    // System.out.println("envoyer");
                    Tour tour = partie.getTours().get(i);

                    if (tour.getPosition().equals(position)) {
                        partie.faireAmeliorerTours(tour);
                        T.MiseAjourImage(tour);
                        // System.out.println("envoyer");
                    }

                    i++;
                }
                ameliorerTour = false;
            }
        });

        AnimationTimer gameLoop = new AnimationTimer() {

            long dernierTemps = 0;

            long ticke = 1_000_000_000 /60; // 60 ticke

            @Override
            public void handle(long tempActuel) {

                if (tempActuel - dernierTemps >= ticke) {
                    partie.mettreAJour();

                    rubisVue.afficherRubis();
                    btnAcheterCase.setText("Acheter case - " + partie.getPrixCase());

                    if (partie.defaiteProperty().get()) {
                        int tempsDefaite = partie.getCompteur() - partie.getCompteurDefaite();

                        carteVue.timerRecommencer(tempsDefaite);

                        if (tempsDefaite > 175) {
                            partie.setDefaiteProperty(false);
                        }
                    }

                    partie.setCompteurPlusPlus();
                    dernierTemps = tempActuel;
                }
            }
        };
        gameLoop.start();
    }

    public void initialiserListeners() {

        partie.vagueEnCoursProperty().addListener((obs,old,now) -> {
                mettreAJourBoutonRecommencer();
                mettreAJourBoutonVague();
        });

        partie.toutesLesVaguesTermineProperty().addListener((obs,old,now) -> {
            mettreAJourBoutonRecommencer();
            mettreAJourBoutonVague();
        });

        partie.portailMortProperty().addListener((obs,old,now) -> {
            mettreAJourBoutonRecommencer();
            mettreAJourBoutonVague();
        });

        partie.victoireProperty().addListener((obs,old,now) -> {
            desactiverToutLesBoutons();
            carteVue.ajouterEcranVictoire();
        });

        partie.defaiteProperty().addListener((obs,old,now) -> {
            if (now) {
//                A GARDER SI ON VEUT QUE QUAND ON A L'ECRAN DE DEFAITE
//                IL Y A ECRIT LANCER VAGUE 1 SINON YAURA MARQUE LANCER VAGUE 2
//                partie.setIndiceVague(0);
//                mettreAJourBoutonVague();
                desactiverToutLesBoutons();
                carteVue.ajouterEcranDefaite();
            } else {
                carteVue.retirerEcranDefaite();
                recommencer();
                activerToutLesBoutons();
                mettreAJourBoutonVague();
            }
        });

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
        if (partie.getToutesLesVaguesTerminees()) {
            btnVague.setDisable(true);
            btnVague.setText("Vagues terminées");
        } else if (partie.getVagueEnCours()) {
            btnVague.setDisable(true);
            btnVague.setText("Vague " + partie.getIndiceVaguePlusUn() + " en cours");
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
        paneSprites.getChildren().clear();
        coeurVue.mettreAJourPvPortail(3);
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
        btnAmeliorer.setDisable(true);
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
        btnAmeliorer.setDisable(false);
        this.defaiteLance = false;
    }


}