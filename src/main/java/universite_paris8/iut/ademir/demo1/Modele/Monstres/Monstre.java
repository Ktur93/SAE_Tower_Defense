package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class Monstre {

    private static int compteurID = 0;

    private double vitesse;
    private int recompense;
    private int indiceChemin;
    private int degat;
    private ArrayList<Position> chemin;
    private IntegerProperty pv;
    private DoubleProperty x;
    private DoubleProperty y;
    private String monstreID;
    private int cadence;
    private int compteurPoison;
    private int compteurGlace;
    private boolean monstreEmpoisone = false;
    private int monstreGlacee;


    public Monstre(int pv, double vitesse, int recompense, int degat, ArrayList<Position> chemin) {
        Position depart = chemin.get(0);
        this.pv = new SimpleIntegerProperty(pv);
        this.vitesse = vitesse;
        this.recompense = recompense;
        this.chemin = chemin;
        this.indiceChemin = 0;
        this.degat = degat;
        this.x = new SimpleDoubleProperty(depart.getX() * 64);
        this.y = new SimpleDoubleProperty(depart.getY() * 64);
        this.monstreID = "monstre" + compteurID;
        this.cadence = 10;
        this.compteurPoison = 0;
        this.compteurGlace = 0;
        this.monstreGlacee = 0;
        compteurID++;
    }

    public void avancer() {
        if(monstreEmpoisone == true){
            if (compteurPoison%150==0) {
                pv.setValue(getPv() - 5);
            }
            compteurPoison++;
        }
        if(monstreGlacee == 1){
            setVitesse(0.5);
            if(compteurGlace%600 == 0){
                monstreGlacee = 0;
                setVitesse(1);
                System.out.println("fin de effet Glace");
            }
            compteurGlace++;
        }
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

    public double getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
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

    public void recevoirDegatsGlace(int degats) {
        pv.setValue(getPv() - degats);
        monstreGlacee = 1;
    }

    public void recevoirDegatsPoison(int degats) {
        pv.setValue(getPv() - degats);
        monstreEmpoisone = true;
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

