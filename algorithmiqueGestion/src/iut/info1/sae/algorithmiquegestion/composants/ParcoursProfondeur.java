/*
 * ParcoursProfondeur.java                                           5 juin 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import iut.info1.sae.algorithmiquegestion.jeulabyrinthe.AffichageLabyrinthe;
import iut.info1.sae.algorithmiquegestion.piles.Pile;
import java.util.ArrayList;
import java.util.Random;

/**
 * Parcours en profondeur du graphe afin de déterminer le plus court
 * chemin entre l'entrée et la sortie.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class ParcoursProfondeur {
	
	static Labyrinthe labyrinthe = AffichageLabyrinthe.getLabyrinthe();
    
    /**
     * Parcours du labyrinthe afin de determiner le chemin direct
     * allant de l'entrée à la sortie.
     */
    public static void algorithmeParcours() {
        Pile parcours = new Pile();
        Sommet sommetCourant;
        Sommet sommetAEmpiler;
        ArrayList<Sommet> listeSommetsAEmpiler = new ArrayList<>();
        
        
        parcours.empiler(labyrinthe.getEntree());
        labyrinthe.getEntree().setParcouru(true);
        do {
            sommetCourant = (Sommet)parcours.sommet();
            listeSommetsAEmpiler = listeSommetsLiesNonParcourus(sommetCourant);
            
            if (listeSommetsAEmpiler.size() == 0) {
                parcours.depiler();
            } else {
				sommetAEmpiler = sommetLieAleatoire(listeSommetsAEmpiler);
				parcours.empiler(sommetAEmpiler);
                sommetAEmpiler.setParcouru(true);
			}  
           
        } while (sommetCourant != labyrinthe.getSortie());
        parcours.empiler(labyrinthe.getSortie());
       
        /*for (int i = 0; i < parcours.getContenu().length; i++) {
    	    System.out.println(parcours.getContenu()[i]);
        }*/
        
        System.out.println("\n");
        while (!parcours.isVide()) {
			System.out.println(parcours.sommet());
			parcours.depiler();
		}
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
		
		Sommet sommetsAleatiores = listeSommets.get(indiceSommetAleatoire);
		return sommetsAleatiores;
    }
    
    /**
     * @param sommet Sommet courant dans le parcours du labyrinthe.
     * @return Les sommets adjacents au sommet en paramètre qui ne sont
     *         parcourus.
     */
    public static ArrayList<Sommet> listeSommetsLiesNonParcourus(Sommet sommet) {
		
		ArrayList<Sommet> liaisonsCourantes = sommet.getLiaisons();
		ArrayList<Sommet> liaisonsNonParcourues = new ArrayList<>();
		
		
		for (int indiceLiaisons = 0; indiceLiaisons < liaisonsCourantes.size(); indiceLiaisons++) {
            if (!liaisonsCourantes.get(indiceLiaisons).isParcouru()) {
				liaisonsNonParcourues.add(liaisonsCourantes.get(indiceLiaisons));
            }
    
		}
		return liaisonsNonParcourues;
    }
    
}
