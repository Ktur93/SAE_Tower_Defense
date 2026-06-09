package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;


public abstract class Tour {

    private int atk;
    private int prix;
    private int porter;
    private Position position;
    private long dernierTir;
    private long cadence;
    private IntegerProperty nivT;
    private int nivMax;

    public Tour(int attaque, int prix, int porter, Position position, long cadence , int nivMax) {
        this.atk = attaque;
        this.prix = prix;
        this.porter = porter;
        this.position = position;
        this.cadence = cadence;
        this.dernierTir = 0;
        this.nivT = new SimpleIntegerProperty(1);
        this.nivMax = nivMax;
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
    public long getCadence(){
        return cadence;
    }
    public void setCadence(long cadence){
        this.cadence = cadence;
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


    public int getNivT() {
        return nivT.getValue();
    }

    public void setNivT(int nivT) {
        this.nivT.setValue(nivT);
    }

    public IntegerProperty nivTProperty() {
        return nivT;
    }

    public int getNivMax() {
        return nivMax;
    }

    public void setNivMax(int nivMax) {
        this.nivMax = nivMax;
    }

    public void attaquer(ObservableList<Monstre> monstres, long tempsActuel) {
        if (tempsActuel - dernierTir >= cadence) {
            Monstre cible = null;

            int i = 0;
            while (i < monstres.size() && cible == null) {
                // Monstre courant
                Monstre monstre = monstres.get(i);
                // Si le monstre est à portée, il devient la cible
                if (estAPorter(monstre)) {
                    cible = monstre;
                }
                // Passage au monstre suivant
                i++;
            }

            // Si une cible a été trouvée, on l'attaque
            if (cible != null) {
                cible.recevoirDegats(atk);
                dernierTir = tempsActuel;
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

    public abstract void ameliorer();
}
