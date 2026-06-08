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
        this.carte = carte; // map du jeu
        this.source = source; // point de depart du BFs
        this.parcours = new ArrayList<>(); // toutes les cases visitees
        this.predecesseurs = new HashMap<>(); // sert a memorise d'ou je viens (2,0) vient de (1,0) ...
        algoBFS();
    }

    // Objectif BFS est de  Trouver un chemin dans la carte.

    private void algoBFS() {
        LinkedList<Position> fifo = new LinkedList<>(); // fifo = First In First Out = Premier dedans Premier dehors comme une file d'attente

        fifo.add(source); // on met le depart dans la file
        parcours.add(source); // on marque la source comme visite
        predecesseurs.put(source, null); // null parce que ya pas de case precedente vu que source c'est le debut

        while (!fifo.isEmpty()) { // tant qu'il reste des cases a visites
            Position positionActuelle = fifo.removeFirst(); // on recupere la premiere case

            for (Position voisin : carte.getVoisinsMarchables(positionActuelle)) { // On regarde les voisins marchable

                if (!predecesseurs.containsKey(voisin)) { // ca veut dire = Est-ce qu'on a déjà visité cette case ? inverse ducoup avec "!"
                    fifo.add(voisin); // sinon on ajoute a la file (fifo)
                    parcours.add(voisin); // on marque comme visite
                    predecesseurs.put(voisin, positionActuelle); // on memorise que voisin vient de positionActuelle
                }
            }
        } // quand la file est vide ca veut dire que toutes les cases accessibles ont été visitées.
    }

    public ArrayList<Position> cheminVersSource(Position cible) {
        // exemple Source = A | A -> B -> C -> D
        ArrayList<Position> chemin = new ArrayList<>();

        if (!predecesseurs.containsKey(cible)) { // si la cible n'a jamais été atteinte
            return chemin; // pas de chemin on retourne un liste vide
        }

        Position positionActuelle = cible; // on commence par la cible, donc le debut ici

        while (positionActuelle != null) {
            chemin.add(positionActuelle); // premier tour
            positionActuelle = predecesseurs.get(positionActuelle); // ca donne le truc d'avant donc dans l'exemple ici C puis B puis A
        }

        return chemin; // chemin a l'envers car chemin vers source
    }


    public ArrayList<Position> getParcours() {
        return this.parcours;
    }

    public Map<Position,Position> getPredecesseurs() {
        return this.predecesseurs;
    }

    public ArrayList<Position> cheminDeSourceVersCible(Position cible) {
        ArrayList<Position> chemin = cheminVersSource(cible); // D,C,B,A
        java.util.Collections.reverse(chemin); // donne A,B,C,D
        return chemin;
    }

}