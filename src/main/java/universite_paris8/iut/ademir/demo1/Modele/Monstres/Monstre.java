package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import javafx.geometry.Pos;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public abstract class Monstre {

    private int pv;
    private int vitesse;
    private int recompense;
    private ArrayList<Position> chemin;
    private int indiceChemin;

    //position du monstre EN PIXEL
    private double x;
    private double y;



    public Monstre(int pv, int vitesse, int recompense, ArrayList<Position> chemin) {
        Position depart = chemin.get(0);
        this.pv = pv;
        this.vitesse = vitesse;
        this.recompense = recompense;
        this.chemin = chemin;
        this.indiceChemin = 0;
        this.x = depart.getColonne() * 64;
        this.y = depart.getLigne() * 64;
    }

    public void avancer(long now) {
        if (!estArrive()) {
            Position position = chemin.get(indiceChemin);
            Position posSuivante = chemin.get(indiceChemin + 1);
            double cibleX = posSuivante.getColonne() * 64;
            double cibleY = posSuivante.getLigne() * 64;

            // si il est a gauche de la cible, on le fait avancer via sa vitesse
            if (this.x < cibleX) {
                x += this.vitesse;

                if (this.x > cibleX) {
                    x = cibleX;
                }
            } else if (this.x > cibleX) {
                x -= this.vitesse;

                if (this.x < cibleX) {
                    this.x = cibleX;
                }
            }

            if (this.y > cibleY) {
                y -= this.vitesse;

                if (this.y < cibleY) {
                    y = cibleY;
                }
            } else if (this.y < cibleY) {
                y += this.vitesse;

                if (this.y > cibleY) {
                    y = cibleY;
                }
            }


            if (this.x == posSuivante.getColonne() * 64 && this.y == posSuivante.getLigne() * 64) {
                this.indiceChemin++;

                System.out.println(posSuivante.getLigne());
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
        return this.pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public int getRecompense() {
        return this.recompense;
    }

    public void recevoirDegats(int degats) {
        this.pv -= degats;

    }

    public double getX () {
        return this.x;
    }

    public double getY() {
        return this.y;
    }


    public boolean estMort() {
        return this.pv <= 0;
    }
}

