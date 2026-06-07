package universite_paris8.iut.ademir.demo1.Vue;

import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public class RubisVue {

    private Partie partie;
    private Label labelRubis;

    public RubisVue(Partie partie, Label labelRubis) {
        this.partie = partie;
        this.labelRubis = labelRubis;
    }

    public void afficherRubis() {
        labelRubis.setText("Rubis : " + partie.getRubis());
    }

}
