package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Monstre {

    private static int compteur = 0;

    private int vitesse;
    private int recompense;
    private int indiceChemin;
    private int degat;
    private ArrayList<Position> chemin;
    private IntegerProperty pv;
    private DoubleProperty x;
    private DoubleProperty y;
    private String monstreID;


    public Monstre(int pv, int vitesse, int recompense, int degat, ArrayList<Position> chemin) {
        Position depart = chemin.get(0);
        this.pv = new SimpleIntegerProperty(pv);
        this.vitesse = vitesse;
        this.recompense = recompense;
        this.chemin = chemin;
        this.indiceChemin = 0;
        this.degat = degat;
        this.x = new SimpleDoubleProperty(depart.getX() * 64);
        this.y = new SimpleDoubleProperty(depart.getY() * 64);
        this.monstreID = "monstre" + compteur;
        compteur++;
    }

    public void avancer() {
        if (!estArrive()) {
            Position position = chemin.get(indiceChemin);
            Position posSuivante = chemin.get(indiceChemin + 1);
            double cibleX = posSuivante.getX() * 64;
            double cibleY = posSuivante.getY() * 64;

            // si il est a gauche de la cible, on le fait avancer via sa vitesse
            if (x.get() < cibleX) {
                x.set(x.get()+this.vitesse);

                if (x.get() > cibleX) {
                    x.set(cibleX);
                }
            } else if (x.get() > cibleX) {
                x.set( x.get() - this.vitesse);

                if (x.get() < cibleX) {
                    x.set(cibleX);
                }
            }

            if (y.get() > cibleY) {
                y.set(y.get() - this.vitesse);

                if (y.get() < cibleY) {
                    y.set(cibleY);
                }
            } else if (y.get() < cibleY) {
                y.set(y.get() + this.vitesse);

                if (y.get() > cibleY) {
                    y.set(cibleY);
                }
            }


            if (x.get() == posSuivante.getX() * 64 && y.get() == posSuivante.getY() * 64) {
                this.indiceChemin++;

                System.out.println(posSuivante.getY());
            }

        }
    }


//    public boolean diffEntreLesPositionsHaut (Position point, Position pointSuivant) {
//        boolean reponse = false;
//        if (pointSuivant.getLigne() < point.getLigne()) {
//            reponse = true;
//        }
//        return reponse;
//    }
//
//    public boolean diffEntreLesPositionsBas (Position point, Position pointSuivant) {
//        boolean reponse = false;
//        if (pointSuivant.getLigne() > point.getLigne()) {
//            reponse = true;
//        }
//        return reponse;
//    }
//
//    public boolean diffEntreLesPositionsGauche (Position point, Position pointSuivant) {
//        boolean reponse = false;
//        if (pointSuivant.getColonne() < point.getColonne()) {
//            reponse = true;
//        }
//        return reponse;
//    }
//
//    public boolean diffEntreLesPositionsDroite (Position point, Position pointSuivant) {
//        boolean reponse = false;
//        if (pointSuivant.getColonne() > point.getColonne()) {
//            reponse = true;
//        }
//        return reponse;
//    }


    public boolean estArrive() {
        return this.indiceChemin >= this.chemin.size() - 1;
    }

    public Position getPosition() {
        return this.chemin.get(this.indiceChemin);
    }

    public int getPv() {
        return this.pv.intValue();
    }

    public void setPv(int nouveauPv) {
        this.pv.setValue(nouveauPv);
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public int getRecompense() {
        return this.recompense;
    }

    public int getDegat() {
        return this.degat;
    }

    public void recevoirDegats(int degats) {
        pv.setValue(getPv() - degats);
    }

    public double getX () {
        return this.x.doubleValue();
    }

    public double getY() {
        return this.y.doubleValue();
    }

    public void setX (double newX) {
        this.x.setValue(newX);
    }

    public void setY (double newY) {
        this.y.setValue(newY);
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public IntegerProperty pvProperty(){
        return pv;
    }

    public boolean estMort() {
        return this.pv.intValue() <= 0;
    }

    public String getMonstreID() {
        return monstreID;
    }

    public double getDernierePositionX() {
        return this.chemin.get(this.chemin.size() - 1).getX();
    }

    public double getDernierePositionY(){
        return this.chemin.get(this.chemin.size() - 1).getY();
    }

    public boolean estADestination() {
        return (getDernierePositionX() == this.getPosition().getX() && getDernierePositionY() == this.getPosition().getY());
    }

    public void setRecompense(int recompense) {
        this.recompense = recompense;
    }


}

