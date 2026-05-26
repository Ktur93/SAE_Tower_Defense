package universite_paris8.iut.ademir.demo1.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Vue.CarteVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TilePane paneCarte;

    @FXML
    private Pane pane;

    private Carte carte;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        carte = new Carte();
        carte.Remplir();
        CarteVue carteVue = new CarteVue(carte, paneCarte);
        carteVue.dessinerCarte();


    }

    public void creeSprite(Monstre m){

    }
}