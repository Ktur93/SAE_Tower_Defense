package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

import java.util.ArrayList;

public class Partie {

    private ObservableList<Monstre> monstres;
    private ArrayList<Tour> tours;
    private ArrayList<Position> chemin;
    private int rubis;


    // Partie sur les vagues (d'ennemis)
    private ArrayList<Vague> vagues;
    private int indiceVague;
    private boolean vagueEnCours;

    public Partie(ArrayList<Position> chemin) {

        // Partie Chemin
        this.chemin = chemin;

        // Partie Monstres
        this.monstres = FXCollections.observableArrayList();

        // Partie Tour
        this.tours = new ArrayList<>();
        this.rubis = 200;

        // Partie Vagues
        this.vagues = new ArrayList<>();
        this.indiceVague = 0;
        this.vagueEnCours = false;
        creeVagues();
    }

    public void ajouterZombie() {
        this.monstres.add(new Zombie(this.chemin));
    }

    public void ajouterAraignee() {
        monstres.add(new Araignee(chemin));
    }

    public void ajouterSquelette() {
        monstres.add(new Squelette(chemin));
    }

    public void ajouterPillager() {
        monstres.add(new Pillager(chemin));
    }

    public void ajouterBoss() {
        monstres.add(new Boss(chemin));
    }



    public void mettreAJour(long now) {
        faireAvancerMonstres(now);
        faireAttaquerTours();
        supprimerMonstresMorts();

        if (this.vagueEnCours) {
            Vague vagueActuelle = this.vagues.get(this.indiceVague);
            vagueActuelle.mettreAJourVague(now, this.monstres);

            if (vagueActuelle.tousLesMonstresEnvoyes() && this.monstres.isEmpty()) {
                this.vagueEnCours = false;
                this.indiceVague++;
            }
        }
    }



    private void faireAvancerMonstres(long now) {
        for (Monstre monstre : monstres) {
            monstre.avancer(now);
        }
    }

    private void faireAttaquerTours() {
        for (Tour tour : this.tours) {
            tour.attaquer(this.monstres);
        }
    }

    private void supprimerMonstresMorts() {
        this.monstres.removeIf(monstre -> {
            if (monstre.estMort()) {
                this.rubis += monstre.getRecompense();
                return true;
            }
            return false;
        });
    }

    public boolean placerTour(Tour tour, Carte carte) {
        Position position = tour.getPosition();

        if (!carte.estCaseTour(position.getColonne(), position.getLigne())) {
            return false;
        }

        if (this.rubis < tour.getPrix()) {
            return false;
        }

        this.rubis -= tour.getPrix();
        this.tours.add(tour);

        return true;
    }

    public ObservableList<Monstre> getMonstres() {
        return this.monstres;
    }

    public ArrayList<Tour> getTours() {
        return this.tours;
    }

    public int getRubis() {
        return this.rubis;
    }

    public boolean isVagueEnCours () {
        return this.vagueEnCours;
    }

    public int getIndiceVague () {
        return this.indiceVague;
    }

    public int getIndiceVaguePlusUn () {
        return (this.indiceVague + 1);
    }

    public boolean toutesLesVaguesTerminees () {
        return this.getIndiceVague() >= this.vagues.size();
    }

    public ArrayList<Vague> getVagues () {
        return this.vagues;
    }

    public int getNbVagues() {
        return this.vagues.size();
    }

    public void creeVagues() {
        Vague vague1 = new Vague();
        vague1.creeVague1(chemin);

        Vague vague2 = new Vague();
        vague2.creeVague2(chemin);

        Vague vague3 = new Vague();
        vague3.creeVague3(chemin);

        Vague vague4 = new Vague();
        vague4.creeVague4(chemin);

        Vague vague5 = new Vague();
        vague5.creeVague5(chemin);

        this.vagues.add(vague1);
        this.vagues.add(vague2);
        this.vagues.add(vague3);
        this.vagues.add(vague4);
        this.vagues.add(vague5);
    }

    public void lancerProchaineVague() {
        if (this.vagueEnCours) {

        } else if (indiceVague >= vagues.size()) {

        } else {
            this.vagueEnCours = true;
        }
    }






}