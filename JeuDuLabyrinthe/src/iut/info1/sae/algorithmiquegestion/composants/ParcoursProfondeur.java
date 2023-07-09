/*
 * ParcoursProfondeur.java                                           5 juin 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

//import iut.info1.sae.algorithmiquegestion.MenuLabyrinthe;
import iut.info1.sae.algorithmiquegestion.modeles.CreationEtChargementLabyrinthe;
import iut.info1.sae.algorithmiquegestion.piles.Pile;

import java.util.ArrayList;
import java.util.Random;

/**
 * Parcours en profondeur du graphe afin de déterminer le plus court chemin
 * entre l'entrée et la sortie.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class ParcoursProfondeur {

    private static Labyrinthe labyrinthe;
    
    private static Sommet[] listeSommets;
    
    private static Pile parcours;
    
    private static ArrayList<Sommet> parcoursListe = new ArrayList<>();
    
    public static ArrayList<Sommet> getParcoursListe() {
    	return parcoursListe;
    }
    
    /**
     * Parcours du labyrinthe afin de determiner le chemin direct 
     * allant de l'indice du sommet en argument à la sortie.
     * @param indice du sommet de début du parcours
     * @return le nombre de de sommet minimum à parcourir
     * pour allez jusqu'à la sortie.
     */
    public static int algorithmeParcours(int indiceDepartParcours) {
//    	labyrinthe = MenuLabyrinthe.getLabyrinthe();
    	labyrinthe = CreationEtChargementLabyrinthe.getLabyrinthe();
    	listeSommets = labyrinthe.getGraphe().getListeSommets();
        parcours = new Pile();
        Sommet sommetCourant;
        Sommet sommetAEmpiler;
        ArrayList<Sommet> listeSommetsAEmpiler = new ArrayList<>();
        int longueurParcours = 0;

        for (Sommet sommet : labyrinthe.getGraphe().getListeSommets()) {
            sommet.setParcouru(false);
        }

        parcours.empiler(listeSommets[indiceDepartParcours]);
        listeSommets[indiceDepartParcours].setParcouru(true);
        sommetCourant = (Sommet) parcours.sommet();
        while (!sommetCourant.equals(labyrinthe.getSortie())) {
            listeSommetsAEmpiler = listeSommetsLiesNonParcourus(sommetCourant);

            if (listeSommetsAEmpiler.size() == 0) {
                parcours.depiler();
            } else {
                sommetAEmpiler = sommetLieAleatoire(listeSommetsAEmpiler);
                parcours.empiler(sommetAEmpiler);
                sommetAEmpiler.setParcouru(true);
            }
            sommetCourant = (Sommet) parcours.sommet();

        }
        parcours.empiler(labyrinthe.getSortie());

        System.out.println("\n");
        while (!parcours.isVide()) {
            System.out.println(parcours.sommet());
            parcoursListe.add((Sommet) parcours.sommet());
            parcours.depiler();
            longueurParcours++;
        }
        
        /* Suppression de l'indiceDepartParcours et de la sortie */
        parcoursListe.remove(0);
        parcoursListe.remove(parcoursListe.size() -1);
        
        // -1 pour ne pas compter l'indiceDepartParcours dans le parcours
        return longueurParcours -1;
    }

    /**
     * Accès aléatoire à un sommet de la liste de sommets en paramètre.
     *
     * @param listeSommets La liste des sommets à laquelle prendre un sommet.
     * @return Un sommet aléatoire dans la liste de sommets en paramètre.
     */
    public static Sommet sommetLieAleatoire(ArrayList<Sommet> listeSommets) {
        /**
         * entiers aléatoires correspondant à un indice dans la liste des sommets
         */
        int indiceSommetAleatoire;

        indiceSommetAleatoire = new Random().nextInt(listeSommets.size());

        Sommet sommetAleatiore = listeSommets.get(indiceSommetAleatoire);
        return sommetAleatiore;
    }

    /**
     * @param sommet Sommet courant dans le parcours du labyrinthe.
     * @return Les sommets adjacents au sommet en paramètre qui ne sont parcourus.
     */
    public static ArrayList<Sommet> listeSommetsLiesNonParcourus(Sommet sommet) {
    	Sommet[] listeSommet = labyrinthe.getGraphe().getListeSommets();
        ArrayList<Integer> liaisonsCourantes = sommet.getLiaisons();
        ArrayList<Sommet> liaisonsNonParcourues = new ArrayList<>();

        for (int indiceLiaisons = 0; indiceLiaisons < liaisonsCourantes.size(); indiceLiaisons++) {
            if (!listeSommet[liaisonsCourantes.get(indiceLiaisons)].isParcouru()) {
                liaisonsNonParcourues.add(listeSommet[liaisonsCourantes.get(indiceLiaisons)]);
            }

        }
        return liaisonsNonParcourues;
    }

}
