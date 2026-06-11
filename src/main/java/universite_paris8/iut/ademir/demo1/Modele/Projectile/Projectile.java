package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Projectile {

    private DoubleProperty xProperty;
    private DoubleProperty yProperty;

    private int vitesse;
    private Monstre cible;

    public Projectile(double x, double y, int vitesse, Monstre cible) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.vitesse = vitesse;
        this.cible = cible;
    }

    public boolean toucher() {
        if (cible == null || cible.estMort()) {
            return true;
        }

        double cibleX = cible.getX();
        double cibleY = cible.getY();

        double dx = cibleX - getX();
        double dy = cibleY - getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= vitesse) {
            return true;
        }

        setX(getX() + vitesse * dx / distance);
        setY(getY() + vitesse * dy / distance);

        return false;
    }

    public double getX() {
        return xProperty.getValue();
    }

    public void setX(double x) {
        xProperty.setValue(x);
    }

    public DoubleProperty xProperty() {
        return xProperty;
    }

    public double getY() {
        return yProperty.getValue();
    }

    public void setY(double y) {
        yProperty.setValue(y);
    }

    public DoubleProperty yProperty() {
        return yProperty;
    }
}