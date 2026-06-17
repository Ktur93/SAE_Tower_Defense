package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Projectile {

    private DoubleProperty xProperty;
    private DoubleProperty yProperty;

    private int vitesse;
    public int degats;
    public Monstre cible;


    public Projectile(double x, double y, int vitesse, int degats, Monstre cible) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);

        this.vitesse = vitesse;
        this.degats = degats;
        this.cible = cible;
    }

    public boolean avancer() {
        if (cible == null || cible.estMort()) {
            return true;
        }

        double cibleX = cible.getX();
        double cibleY = cible.getY();

        double dx = cibleX - getX();
        double dy = cibleY - getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= vitesse) {
            toucherCible();
            return true;
        }

        setX(getX() + vitesse * dx / distance);
        setY(getY() + vitesse * dy / distance);

        return false;
    }

    public double getX() {
        return xProperty.getValue();
    }

    public double getY() {
        return yProperty.getValue();
    }


    public void setY(double y) {
        yProperty.setValue(y);
    }

    public void setX(double x) {
        xProperty.setValue(x);
    }


    public DoubleProperty xProperty() {
        return xProperty;
    }

    public DoubleProperty yProperty() {
        return yProperty;
    }

    public abstract void toucherCible();
}