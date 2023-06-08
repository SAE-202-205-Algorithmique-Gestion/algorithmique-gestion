/*
 * ChainesAscendantes.java                                           5 juin 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.ArrayList;
import java.util.Random;

/**
 * Création d'un graphe en utilisant la méthode de création
 * par chaînes ascendantes.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class ChainesAscendantes extends Graphe {

	
	/**
	 * Valeur maximale des valeurs des marques.
	 * <p>
	 * Permet d'affecter une marque non existante à un sommet.</p>
	 */
    private int valeurMarqueCourante;
	
	/**
	 * Création d'un graphe par chaînes ascendantes avec les nombres de colonnes
	 * et de lignes du labyrinthe lié.
	 *
	 * @param nombreColonnesLabyrinthe Nombre de colonnes du graphe hérité.
	 * @param nombreLignesLabyrinthe Nombre de lignes du graphe hérité.
	 */
	public ChainesAscendantes(int nombreColonnesLabyrinthe, int nombreLignesLabyrinthe) {
		super(nombreColonnesLabyrinthe, nombreLignesLabyrinthe);
		
		
		this.valeurMarqueCourante = 0;
		
		creationDuGraphe();
	}
	
	/**
	 * Vérifie si la marque des sommets en paramètres est différente.
	 * 
	 * @param sommet1 1ère instance d'un sommet à comparer.
	 * @param sommet2 2nde instance d'un sommet à comparer.
	 * @return Si les 2 sommets ont des marques différentes.
	 */
	public boolean marqueDifferente(Sommet sommet1, Sommet sommet2) {
	    return sommet1.getMarque() != sommet2.getMarque();
	}
	
	/**
	 * Définit une marque pour le ou les sommets.
	 * 
	 * @param sommet1 1ère instance d'un sommet à modifier.
	 * @param sommet2 2nde instance d'un sommet à modifier.
	 */
    public boolean definitUneMarque(Sommet sommet1, Sommet sommet2) {
	    boolean resultat = true;
    	
    	if (sommet1.getMarque() < 0 && sommet2.getMarque() < 0) {
    		sommet1.setMarque(valeurMarqueCourante);
    		sommet2.setMarque(valeurMarqueCourante);
    		valeurMarqueCourante++;
    		
    	} else if (marqueDifferente(sommet1, sommet2)) {
    		if (sommet1.getMarque() < 0) {
    			sommet1.setMarque(sommet2.getMarque());
    			
    		} else if (sommet2.getMarque() < 0) {
    			sommet2.setMarque(sommet1.getMarque());
    			
    		} else {
    			ArrayList<Sommet> sommetMarqueSommet2
    			= sommetsDeMemeMarque(sommet2.getMarque());
    			
    			for (int i = 0; i < sommetMarqueSommet2.size(); i++) {
    				sommetMarqueSommet2.get(i).setMarque(sommet1.getMarque());
    			}
    		}
    	} else {
    		resultat = false;
    	}
    	return resultat;
	}
    
    /**
	 * Renvoie la liste des sommets portant une certaine marque.
	 * 
	 * @param marque La marque que les sommets doivent posséder.
	 * @return La liste des sommets possédant la marque.
	 */
	public ArrayList<Sommet> sommetsDeMemeMarque(int marque) {
		
		ArrayList<Sommet> laListeSommetsMarque = new ArrayList<>();
		for (Sommet sommetATester : getListeSommets() ) {
			if (sommetATester.getMarque() == marque) {
				laListeSommetsMarque.add(sommetATester);
			}
		}
		return laListeSommetsMarque;
	}
	
	/** 
	 * @return Une liste de sommets aléatoirement choisis parmi
	 * le liste des sommets et la liste des sommets adjacents.
	 */
	public Sommet[] sommetsAleatoires() {
		/**
		 * Entiers aléatoires correspondant à un indice
		 * dans la liste des sommets
		 */
		int sommet,
			sommetAdjacent;
		
		sommet = new Random().nextInt(this.getNombreSommets()); 
			
		Sommet[] listeDesSommetsAdjacents = this.tousLesSommetsAdjacentsDuSommet(sommet);
		sommetAdjacent = new Random().nextInt(listeDesSommetsAdjacents.length);
		
		Sommet[] sommetsAleatiores = {
			this.getListeSommets()[sommet],
		    listeDesSommetsAdjacents[sommetAdjacent]
		};
		return sommetsAleatiores;
	}
	
	/**
	 * Création d'un graphe avec des sommets choisis aléatoirement et définition
	 * des marques nécessaires entre les sommets.
	 */
	public void creationDuGraphe() {
		int nombreDeLiaison = 1;
		
		while (nombreDeLiaison < getNombreSommets()) {
			Sommet[] sommetsChoisi = new Sommet[2];
			
			sommetsChoisi = this.sommetsAleatoires();
				
			if (definitUneMarque(sommetsChoisi[0], sommetsChoisi[1])) {
				
				sommetsChoisi[0].creerLiaison(sommetsChoisi[1]);
				nombreDeLiaison++;
			}
			
		}	
	}

}
