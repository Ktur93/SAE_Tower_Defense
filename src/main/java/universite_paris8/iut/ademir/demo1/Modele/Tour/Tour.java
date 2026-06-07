package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;


public class Tour {

    private int atk;
    private int prix;
    private int porter;
    private Position position;

    public Tour(int attaque, int prix, int porter, Position position) {
        this.atk = attaque;
        this.prix = prix;
        this.porter = porter;
        this.position = position;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix){
        this.prix = prix;
    }
    public int getAtk(){
        return this.atk;
    }
    public void setAtk(int atk){
        this.atk = atk;
    }
    public int getPorter() {
        return porter;
    }

    public void setPorter(int portee) {
        this.porter = portee;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pos){
        this.position = pos;
    }

    public void attaquer(ObservableList<Monstre> monstres) {

        for (Monstre m : monstres) {
            if (estAPorter(m)) {
                m.recevoirDegats(getAtk());
            }
        }
    }
    private boolean estAPorter(Monstre monstre) {
        int x = position.getX() - monstre.getPosition().getX();
        int y = position.getY() - monstre.getPosition().getY();
        int CalculePorter = (x * x + y * y);
        if (CalculePorter <= getPorter() * getPorter()){
            return true;
        }
        return false;
    }
}
