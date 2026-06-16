package universite_paris8.iut.ademir.demo1.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public class CoeurVue {
    public static final int TAILLE_COEUR = 32;

    private Partie partie;
    private Pane paneCoeurs;

    private Image coeurRouge;
    private Image coeurVide;

    private ImageView coeur1;
    private ImageView coeur2;
    private ImageView coeur3;

    public CoeurVue(Partie partie, Pane paneCoeurs){
        this.partie = partie;
        this.paneCoeurs = paneCoeurs;

        this.coeurRouge = new Image(Main.class.getResourceAsStream("Coeurs/coeurRouge.png"));
        this.coeurVide = new Image(Main.class.getResourceAsStream("Coeurs/coeurVide.png"));

        creerSpriteCoeur();
        mettreAJourPvPortail(partie.getPvPortailIntegerProperty());

        partie.pvPortailIntegerPropertyProperty().addListener((obs, oldValue, newValue) -> {
            mettreAJourPvPortail(newValue.intValue());
        });
    }

    public void creerSpriteCoeur(){
        coeur1 = new ImageView(coeurRouge);
        coeur2 = new ImageView(coeurRouge);
        coeur3 = new ImageView(coeurRouge);

        coeur1.setFitWidth(TAILLE_COEUR);
        coeur1.setFitHeight(TAILLE_COEUR);

        coeur2.setFitWidth(TAILLE_COEUR);
        coeur2.setFitHeight(TAILLE_COEUR);

        coeur3.setFitWidth(TAILLE_COEUR);
        coeur3.setFitHeight(TAILLE_COEUR);

        coeur1.setLayoutX(1570);
        coeur1.setLayoutY(850);

        coeur2.setLayoutX(1547);
        coeur2.setLayoutY(850);

        coeur3.setLayoutX(1524);
        coeur3.setLayoutY(850);

        paneCoeurs.getChildren().addAll(coeur1, coeur2, coeur3);
    }

    public void mettreAJourPvPortail(int pv) {

        if (pv == 3) {
            coeur1.setImage(coeurRouge);
            coeur2.setImage(coeurRouge);
            coeur3.setImage(coeurRouge);

        } else if (pv == 2) {
            coeur1.setImage(coeurRouge);
            coeur2.setImage(coeurRouge);
            coeur3.setImage(coeurVide);

        } else if (pv == 1) {
            coeur1.setImage(coeurRouge);
            coeur2.setImage(coeurVide);
            coeur3.setImage(coeurVide);

        } else {
            coeur1.setImage(coeurVide);
            coeur2.setImage(coeurVide);
            coeur3.setImage(coeurVide);
        }
    }


}
