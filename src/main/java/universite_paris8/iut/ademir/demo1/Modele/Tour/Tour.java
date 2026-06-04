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
        Monstre aPortee = null; //aucun monstres trouvé
        int i = 0; //indice qui parcoure la liste de monstres
        while(i < monstres.size() && aPortee == null){ //on parcourt la liste tant qu'on a pas trouvé de mosntre a porté
            Monstre m = monstres.get(i);
            if(estAPortee(m)){
                aPortee = m;
            }
            i++;
        }
        if(aPortee != null){
            aPortee.recevoirDegats(this.atk);
        }
    }

    private boolean estAPortee(Monstre monstre) {
        int dx = pos.getColonne() - monstre.getPosition().getColonne();
        int dy = pos.getLigne() - monstre.getPosition().getLigne();
        return Math.sqrt(dx * dx + dy * dy) <= this.portee;
    }

    private void ameliorer(int nouvAtk, int nouvPrix, int nouvPortee){
        //la methode sert a change les stats de la tour ex : passe du niv 1 a 2
        setAtk(nouvAtk);
        setPrix(nouvPrix);
        setPortee(nouvPortee);
    }
}
