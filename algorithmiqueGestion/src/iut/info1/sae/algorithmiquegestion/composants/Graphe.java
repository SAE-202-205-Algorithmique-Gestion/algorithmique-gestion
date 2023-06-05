/*
 * Graphe.java														 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.ArrayList;
import java.util.Random;


/**
 * Modélisation d'un graphe sans circuit et n'ayant qu'une seule chaîne.
 * Utilisation de la classe Sommet dans la création du graphe.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class Graphe {
	
	/** Liste des sommets de this */
	private Sommet[] listeSommets;
		
	/**
	 * Longueur (horizontale) du labyrinthe.
	 * <p>
	 * Cette donnée est choisie par l'utilisateur
	 * au lancement du jeu de labyrinthe.</p>
	 */
	private int nombreColonnesLabyrinthe;
	
	/**
     * Largeur (verticale) du labyrinthe.
     * <p>
     * Cette donnée est choisie par l'utilisateur
     * au lancement du jeu de labyrinthe.</p>
     */
	private int nombreLignesLabyrinthe;
	
	/**
	 * Création d'un graphe à partir de son nombre de colonnes et de lignes.
	 * @param nombreColonnesLabyrinthe un entier du nombre de Colonnes du Labyrinthe
	 * @param nombreLignesLabyrinthe un entier du nombre de Lignes du Labyrinthe
	 */
	public Graphe(int nombreColonnesLabyrinthe, int nombreLignesLabyrinthe) {
		super();
		
		this.nombreColonnesLabyrinthe = nombreColonnesLabyrinthe;
		this.nombreLignesLabyrinthe = nombreLignesLabyrinthe;

		
		this.listeSommets = new Sommet[this.nombreColonnesLabyrinthe
		                               * this.nombreLignesLabyrinthe];
		for (int i = 0; i < getNombreSommets(); i++) {
			this.listeSommets[i] = creerSommet(i);
		}
		
	}
	
	/** @return Nombre de sommets de this. */
	public int getNombreSommets() {
		return this.listeSommets.length;
	}
	
	/** @return Liste des sommets de this. */
	public Sommet[] getListeSommets() {
		return this.listeSommets;
	}
	
	/** @return Nombre de colonnes de this. */
	public int getNombreColonnesLabyrinthe() {
		return nombreColonnesLabyrinthe;
	}

	/** @return Nombre de lignes de this. */
	public int getNombreLignesLabyrinthe() {
		return nombreLignesLabyrinthe;
	}

	/**
	 * Création d'un sommet rattaché à this en fonction de son indice
	 * dans la liste des sommets.
	 *
	 * @param indiceCourantListeSommet Indice 
	 * @return L'instance du sommet créé.
	 */
	public Sommet creerSommet(int indiceCourantListeSommet) {
	   int coordonneeX,
		   coordonneeY,
		   indiceCaseCourante;
		
		coordonneeX = indiceCourantListeSommet 
		              % this.nombreColonnesLabyrinthe;
		
		coordonneeY = 0;
		indiceCaseCourante = indiceCourantListeSommet;
		
		while (indiceCaseCourante >= this.nombreColonnesLabyrinthe) {
			indiceCaseCourante -= this.nombreColonnesLabyrinthe;	
			coordonneeY++;
		}
		return new Sommet(coordonneeX, coordonneeY);
	}
	
	/**
	 * Vérifie si deux sommets sont adjacents à l'aide de 
	 * leurs coordonnées.
	 * 
	 * @param indiceSommet1 Indice du premier sommet dans la liste des sommets.
	 * @param indiceSommet2 Instance du second sommet dans la liste des sommets.
	 * @return Si les deux sommets sont adjacents.
	 */
	public boolean estAdjacent(int indiceSommet1, int indiceSommet2) {
		
		if (!this.sommetExiste(indiceSommet1)
			|| !this.sommetExiste(indiceSommet2)) {
			return false;
		}
		
		final Sommet[] listeSommet = this.getListeSommets();
		
		final Sommet sommet1 = listeSommet[indiceSommet1];
		final Sommet sommet2 = listeSommet[indiceSommet2];
		
		return (sommet1.getCoordonneeX() == sommet2.getCoordonneeX()
				&& (    sommet1.getCoordonneeY() == sommet2.getCoordonneeY() + 1
				    ||  sommet1.getCoordonneeY() == sommet2.getCoordonneeY() - 1))
			    || (    sommet1.getCoordonneeY() == sommet2.getCoordonneeY()
				    && (sommet1.getCoordonneeX() == sommet2.getCoordonneeX() + 1
				        || sommet1.getCoordonneeX() == sommet2.getCoordonneeX() - 1));
	}
	

	
	/**
	 * Choix d'un sommet aléatoire et d'un sommet aléatoire adjacent à celui-ci.
	 * 
	 * @return Couple de sommets adjacents choisis aléatoirement.
	 */
	public Sommet[] sommetsAleatoires() {
		/**
		* entiers aléatoires correspondant à un indice dans la liste des sommets
		*/
		int sommet,
			sommetAdjacent;
		
		sommet = new Random().nextInt(this.getNombreSommets()); 
		//x = Math.random() * this.getNombreSommets();
			
		Sommet[] listeDesSommetsAdjacents = this.tousLesSommetsAdjacentsDuSommet(sommet);
		sommetAdjacent = new Random().nextInt(listeDesSommetsAdjacents.length);
		
		Sommet[] sommetsAleatiores = {this.getListeSommets()[sommet], listeDesSommetsAdjacents[sommetAdjacent]};
		return sommetsAleatiores;
	}

	/**
	 * Permet de déterminer les sommets adjacents à un sommet dont l'indice dans
	 * la liste des sommets de this est en paramètre.
	 *
	 * @param indiceSommet L'indice du sommet dans la liste des sommets du graphe.
	 * @return la liste des sommet adjacents mis en paramètre
	 */
	public Sommet[] tousLesSommetsAdjacentsDuSommet(int indiceSommet) {
		int tailleTableau = 0;

		int[] listeSommetAdjacentPossible = {
			indiceSommet + this.getNombreColonnesLabyrinthe(),
			indiceSommet - this.getNombreColonnesLabyrinthe(),
			indiceSommet + 1, 
			indiceSommet - 1 
		};	
		
		/* 
		 * Attention dans le cas où par exemple 4 est à la fin 
		 * d'une  ligne , 5 ne doit pas être un sommet adjacent.
		 * Normalement y'a pas de problème avec la méthode estAdjacent
		*/
		
		for (int i = 0; i < listeSommetAdjacentPossible.length; i++) {
			if (estAdjacent(indiceSommet, listeSommetAdjacentPossible[i])) {
				tailleTableau++;
			}	
		}
		
		Sommet listeSommetAdjacent[] = new Sommet[tailleTableau];
		int position = 0;
		
		for (int i = 0; i < listeSommetAdjacentPossible.length; i++) {
			if (estAdjacent(indiceSommet, listeSommetAdjacentPossible[i])) {
				listeSommetAdjacent[position] = getListeSommets()[listeSommetAdjacentPossible[i]];
				position++;
			}
		}
		return listeSommetAdjacent;
	}
	
	/**
	 * Permet de vérifier l'existance d'un sommet dans le graphe
	 * à partir de ses coordonnées.
	 * @param sommet
	 * @return si le sommet fais bien partie du graphe ou pas
	 */
	public boolean sommetExiste(int sommet) {
		return sommet >= 0 && sommet < this.getNombreSommets();
	}
	
	
	
}