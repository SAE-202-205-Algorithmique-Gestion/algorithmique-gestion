spackage iut.info1.sae.algorithmiquegestion.composants;

import java.util.ArrayList;
import java.util.Random;

import iut.info1.sae.algorithmiquegestion.piles.Pile;
/**
 * 
 * 
 * @author simon.guiraud
 * @autour samuel.lacam
 */
public class ConstructionBacktracking extends Graphe{

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
    public static Sommet sommetAdjacentAleatoire(ArrayList<Sommet> sommetsAdjacents) {
		/**
		 * entiers aléatoires correspondant à un indice dans la liste des sommets
		 */
		int indiceSommetAleatoire;
		
		indiceSommetAleatoire = new Random().nextInt(sommetsAdjacents.size());
		
		Sommet sommetAleatiore = sommetsAdjacents.get(indiceSommetAleatoire);
		return sommetAleatiore;
    }

	/**
	 * Création d'un graphe avec des sommets choisis aléatoirement et définition
	 * des marques nécessaires entre les sommets.
	 */
	public void creationDuGraphe() {
		Pile pileSommetsParcourus = new Pile();
        Sommet sommetCourant;
        Sommet sommetAEmpiler;
        Sommet[] PileSommetsAEmpiler;
        Sommet[] listeSommets = getListeSommets();
        ArrayList<Sommet> sommetsAdjacents = new ArrayList<>();
        
        int indiceSommetCourant = new Random().nextInt(listeSommets.length);
        listeSommets[indiceSommetCourant].setParcouru(true);
        pileSommetsParcourus.empiler(listeSommets[indiceSommetCourant]);
        
        do {
			sommetCourant = (Sommet) pileSommetsParcourus.sommet();
            sommetsAdjacents = sommetsAdjacentsNonParcourus(indiceSommetCourant);
        
            if (sommetsAdjacents.size() == 0) {
                pileSommetsParcourus.depiler();
                
            } else {
				sommetAEmpiler = sommetAdjacentAleatoire(sommetsAdjacents);
				pileSommetsParcourus.empiler(sommetAEmpiler);
                sommetAEmpiler.setParcouru(true);
			}  
           
        } while (pileSommetsParcourus.isVide); //tant que la pile n'est pas vide je crois
        pileSommetsParcourus.empiler(labyrinthe.getSortie());
       
        /*for (int i = 0; i < parcours.getContenu().length; i++) {
    	    System.out.println(parcours.getContenu()[i]);
        }*/
        
        System.out.println("\n");
        while (!pileSommetsParcourus.isVide()) {
			System.out.println(pileSommetsParcourus.sommet());
			pileSommetsParcourus.depiler();
		}
		
	}
	
	public ArrayList<Sommet> sommetsAdjacentsNonParcourus (int indiceSommet) {
		Sommet[] sommetsAdjacents;
		ArrayList<Sommet> adjacentsNonParcourus = new ArrayList<>();
		
		sommetsAdjacents = tousLesSommetsAdjacentsDuSommet(indiceSommet);
		
		for (int indiceAdjacents = 0; indiceAdjacents < sommetsAdjacents.length; indiceAdjacents++ ) {
			if (!sommetsAdjacents[indiceAdjacents].isParcouru()) {
				adjacentsNonParcourus.add(sommetsAdjacents[indiceAdjacents]);
			}
		}
	}
}
