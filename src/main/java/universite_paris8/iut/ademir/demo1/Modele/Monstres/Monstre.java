package universite_paris8.iut.ademir.demo1.Modele.Monstres;

public abstract class Monstre {
    private int pv;
    private double x;
    private double y;
    private double vitesse;

    public Monstre(int pv, double x, double y, double vitesse) {
        this.pv = pv;
        this.x = x;
        this.y = y;
        this.vitesse = vitesse;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void avancer() {
        x += vitesse;
    }

    public boolean estMort() {
        return pv <= 0;
    }
}

