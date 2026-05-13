module universite_paris8.iut.ademir.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens universite_paris8.iut.ademir.demo1 to javafx.fxml;
    exports universite_paris8.iut.ademir.demo1;
}