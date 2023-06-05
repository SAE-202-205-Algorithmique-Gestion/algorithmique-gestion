package iut.info1.sae.algorithmiquegestion.composants;

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
		
		
		// TODO Auto-generated constructor stub
		creationDuGraphe();
	}
	
	/**
     * Accès aléatoire à un sommet de la liste de sommets en paramètre.
     *
     * @param listeSommets La liste des sommets à laquelle prendre un sommet.
     * @return Un sommet aléatoire dans la liste de sommets en paramètre.
     */
    public static Sommet sommetAdjacentAleatoire(ArrayList<Sommet> listeSommets) {
		/**
		 * entiers aléatoires correspondant à un indice dans la liste des sommets
		 */
		int indiceSommetAleatoire;
		
		indiceSommetAleatoire = new Random().nextInt(listeSommets.size());
		
		Sommet sommetAleatiore = listeSommets.get(indiceSommetAleatoire);
		return sommetAleatiore;
    }

	/**
	 * Création d'un graphe avec des sommets choisis aléatoirement et définition
	 * des marques nécessaires entre les sommets.
	 */
	public void creationDuGraphe() {
		Pile listeSommetsParcourus = new Pile();
        Sommet sommetCourant;
        Sommet sommetAEmpiler;
        ArrayList<Sommet> listeSommetsAEmpiler = new ArrayList<>();
        Sommet[] listeSommets = this.getListeSommets();
        
        int indiceSommetDeDepart = new Random().nextInt(listeSommets.length);
        
        sommetAdjacentAleatoire(listeSommetsAEmpiler);
        
        listeSommetsParcourus.empiler(sommetDeDepart);
        do {
            sommetCourant = (Sommet)listeSommetsParcourus.sommet();
            listeSommetsAEmpiler = listeSommetsLiesNonParcourus(sommetCourant);
            
            if (listeSommetsAEmpiler.size() == 0) {
                listeSommetsParcourus.depiler();
            } else {
				sommetAEmpiler = sommetAdjacentAleatoire(listeSommetsAEmpiler);
				listeSommetsParcourus.empiler(sommetAEmpiler);
                sommetAEmpiler.setParcouru(true);
			}  
           
        } while (sommetCourant != labyrinthe.getSortie());
        listeSommetsParcourus.empiler(labyrinthe.getSortie());
       
        /*for (int i = 0; i < parcours.getContenu().length; i++) {
    	    System.out.println(parcours.getContenu()[i]);
        }*/
        
        System.out.println("\n");
        while (!listeSommetsParcourus.isVide()) {
			System.out.println(listeSommetsParcourus.sommet());
			listeSommetsParcourus.depiler();
		}
		
	}
}
