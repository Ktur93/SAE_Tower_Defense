package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;


public abstract class Tour {

    private int atk;
    private int prix;
    private int portee;
    private Position pos;

    public Tour(int attaque, int prix, int portee, Position position) {
        this.atk = attaque;
        this.prix = prix;
        this.portee = portee;
        this.pos = position;
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

    public int getPortee() {
        return portee;
    }

    public void setPortee(int portee) {
        this.portee = portee;
    }

    public void setPos(Position pos){
        this.pos = pos;
    }

    public Position getPosition() {
        return pos;
    }

    public void attaquer(ObservableList<Monstre> monstres) {

        for (Monstre m : monstres) {
            if (estAPortee(m)) {
                m.recevoirDegats(atk);
            }
        }
    }

    private boolean estAPortee(Monstre monstre) {

        int dx = pos.getX() - monstre.getPosition().getX();
        int dy = pos.getY() - monstre.getPosition().getY();
        return Math.sqrt(dx * dx + dy * dy) <= this.portee;
    }


    private void ameliorer(int nouvAtk, int nouvPrix, int nouvPortee){
        //la methode sert a change les stats de la tour ex : passe du niv 1 a 2
        setAtk(nouvAtk);
        setPrix(nouvPrix);
        setPortee(nouvPortee);
    }
}
