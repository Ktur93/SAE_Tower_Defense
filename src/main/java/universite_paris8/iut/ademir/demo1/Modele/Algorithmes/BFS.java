package universite_paris8.iut.ademir.demo1.Modele.Algorithmes;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BFS {

    private Carte carte;
    private Position source;
    private ArrayList<Position> parcours;
    private Map<Position, Position> predecesseurs;

    public BFS(Carte carte, Position source) {
        this.carte = carte;
        this.source = source;
        this.parcours = new ArrayList<>();
        this.predecesseurs = new HashMap<>();
        algoBFS();
    }

    private void algoBFS() {
        LinkedList<Position> fifo = new LinkedList<>();

        fifo.add(source);
        parcours.add(source);
        predecesseurs.put(source, null);

        while (!fifo.isEmpty()) {
            Position positionActuelle = fifo.removeFirst();

            for (Position voisin : carte.getVoisinsMarchables(positionActuelle)) {

                if (!predecesseurs.containsKey(voisin)) {
                    fifo.add(voisin);
                    parcours.add(voisin);
                    predecesseurs.put(voisin, positionActuelle);
                }
            }
        }
    }

    public ArrayList<Position> cheminVersSource(Position cible) {
        ArrayList<Position> chemin = new ArrayList<>();

        if (!predecesseurs.containsKey(cible)) {
            return chemin;
        }

        Position positionActuelle = cible;

        while (positionActuelle != null) {
            chemin.add(positionActuelle);
            positionActuelle = predecesseurs.get(positionActuelle);
        }

        return chemin;
    }


    public ArrayList<Position> getParcours() {
        return this.parcours;
    }

    public Map<Position,Position> getPredecesseurs() {
        return this.predecesseurs;
    }

    public ArrayList<Position> cheminDeSourceVersCible(Position cible) {
        ArrayList<Position> chemin = cheminVersSource(cible);
        java.util.Collections.reverse(chemin);
        return chemin;
    }

}