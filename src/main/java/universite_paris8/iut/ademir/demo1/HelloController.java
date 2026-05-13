package universite_paris8.iut.ademir.demo1;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TilePane grilleCarte;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  Image imageChemin = new Image(Main.class.getResourceAsStream("tuiles/chemin.png"));
    }
}