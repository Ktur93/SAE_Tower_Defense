package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileBoulet;


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

    public Projectile attaquer(ObservableList<Monstre> monstres, int compteur) {

        if (compteur - dernierTir >= cadence) {

            Monstre cible = null;

            int i = 0;
            while (i < monstres.size() && cible == null) {
                Monstre monstre = monstres.get(i);

                if (estAPorter(monstre)) {
                    cible = monstre;
                }

                i++;
            }

            if (cible != null) {
                infligerDegat(cible);
                dernierTir = compteur;
                return creerProjectile(cible);
            }
        }

        return null;
    }


    public void infligerDegat(Monstre cible){
        cible.recevoirDegats(atk);
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
    public abstract Projectile creerProjectile(Monstre cible);
}
