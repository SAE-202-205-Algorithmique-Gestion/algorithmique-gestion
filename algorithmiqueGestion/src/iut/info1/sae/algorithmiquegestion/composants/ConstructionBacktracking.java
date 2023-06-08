/*
 * ConstructionBacktracking.java								     1 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.ArrayList;
import java.util.Random;

import iut.info1.sae.algorithmiquegestion.piles.Pile;

/**
 * Construction par algorithme de backtracking d'un labyrinthe.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class ConstructionBacktracking extends Graphe {

    /**
     * Construction par backtracking avec un nombre de colonnes et de lignes pour
     * créer le labyrinthe.
     * 
     * @param nombreColonnesLabyrinthe Nombre de colonnes du graphe parent.
     * @param nombreLignesLabyrinthe   Nombre de lignes du graphe parent.
     */
    public ConstructionBacktracking(int nombreColonnesLabyrinthe, int nombreLignesLabyrinthe) {
        super(nombreColonnesLabyrinthe, nombreLignesLabyrinthe);

        creationDuGraphe();
    }

    /**
     * Accès aléatoire à un sommet de la liste de sommets en paramètre.
     *
     * @param listeSommets La liste des sommets à laquelle prendre un sommet.
     * @return Un sommet aléatoire dans la liste de sommets en paramètre.
     */
    public Sommet sommetAdjacentAleatoire(ArrayList<Sommet> sommetsAdjacents) {
        /**
         * entiers aléatoires correspondant à un indice dans la liste des sommets
         */
        int indiceSommetAleatoire;

        indiceSommetAleatoire = new Random().nextInt(sommetsAdjacents.size());

        Sommet sommetAleatiore = sommetsAdjacents.get(indiceSommetAleatoire);
        return sommetAleatiore;
    }

    /**
     * Création d'un graphe avec des sommets choisis aléatoirement et définition des
     * marques nécessaires entre les sommets.
     */
    public void creationDuGraphe() {

        Pile pileSommetsParcourus = new Pile();

        Sommet sommetCourant;
        Sommet sommetAEmpiler;
        Sommet[] listeSommets = getListeSommets();

        ArrayList<Sommet> sommetsAdjacents = new ArrayList<>();

        int indiceSommetCourant = new Random().nextInt(listeSommets.length);
        listeSommets[indiceSommetCourant].setParcouru(true);
        pileSommetsParcourus.empiler(listeSommets[indiceSommetCourant]);

        do {
            sommetCourant = (Sommet) pileSommetsParcourus.sommet();
            for (int indiceSommet = 0; indiceSommet < getListeSommets().length; indiceSommet++) {
                if (getListeSommets()[indiceSommet] == sommetCourant) {
                    indiceSommetCourant = indiceSommet;
                }
            }

            sommetsAdjacents = sommetsAdjacentsNonParcourus(indiceSommetCourant);

            if (sommetsAdjacents.size() == 0) {
                pileSommetsParcourus.depiler();

            } else {
                sommetAEmpiler = sommetAdjacentAleatoire(sommetsAdjacents);
                pileSommetsParcourus.empiler(sommetAEmpiler);
                sommetAEmpiler.setParcouru(true);
                sommetCourant.creerLiaison(sommetAEmpiler);
            }

        } while (!pileSommetsParcourus.isVide());

    }

    /**
     * Détermination des sommets adjacents au sommet en paramètre qui n'ont pas été
     * parcourus.
     * 
     * @param indiceSommet L'indice du sommet dont on cherche les adjacents
     * @return la liste des sommets adjacent qui n'ont pas été parcourus.
     */
    public ArrayList<Sommet> sommetsAdjacentsNonParcourus(int indiceSommet) {
        Sommet[] sommetsAdjacents;
        ArrayList<Sommet> adjacentsNonParcourus = new ArrayList<>();

        sommetsAdjacents = tousLesSommetsAdjacentsDuSommet(indiceSommet);

        for (int indiceAdjacents = 0; indiceAdjacents < sommetsAdjacents.length; indiceAdjacents++) {
            if (!sommetsAdjacents[indiceAdjacents].isParcouru()) {
                adjacentsNonParcourus.add(sommetsAdjacents[indiceAdjacents]);
            }
        }
        return adjacentsNonParcourus;
    }
}
