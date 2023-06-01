/*
 * Graphe.java														 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.ArrayList;
import java.util.Random;


/**
 * Modélisation du graphe sans circuit et n'ayant qu'une seule chaîne.
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
	
	/** Liste des marques des sommets déjà existantes */
	private int[] marquesExistante;
	
	/**
	 * Valeur maximale des valeurs des marques.
	 * <p>
	 * Permet d'affecter une marque non existante à un sommet.</p>
	 */
    private int valeurMarqueCourante;
	
	/**
	 * Création d'un graphe à partir de son nombre de colonnes et de lignes.
	 * @param nombreColonnesLabyrinthe un entier du nombre de Colonnes du Labyrinthe
	 * @param nombreLignesLabyrinthe un entier du nombre de Lignes du Labyrinthe
	 */
	public Graphe(int nombreColonnesLabyrinthe, int nombreLignesLabyrinthe) {
		super();
		
		// TODO: remplacer par JSON
		this.nombreColonnesLabyrinthe = nombreColonnesLabyrinthe;
		this.nombreLignesLabyrinthe = nombreLignesLabyrinthe;
		this.marquesExistante = new int[this.nombreColonnesLabyrinthe
		                                * this.nombreLignesLabyrinthe]; 
		
		this.listeSommets = new Sommet[this.nombreColonnesLabyrinthe
		                               * this.nombreLignesLabyrinthe];
		for (int i = 0; i < getNombreSommets(); i++) {
			this.listeSommets[i] = determinationCoordonnees(i);
		}
		
		this.valeurMarqueCourante = 0;
		this.creationDuGraphe();
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
	 * Création d'un sommet rattaché à this.
	 * 
	 * @return L'instance du sommet créé.
	 */
	public Sommet determinationCoordonnees(int indiceCourantListeSommet) {
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
	 * @param sommet1 Instance du premier sommet.
	 * @param sommet2 Instance du second sommet.
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
    	
    	/* Dans le cas ou les marques des 2 sommets en question
    	 * ne sont pas initialisées. */
    	if (sommet1.getMarque() < 0 && sommet2.getMarque() < 0) {
    		sommet1.setMarque(valeurMarqueCourante);
    		sommet2.setMarque(valeurMarqueCourante);
    		valeurMarqueCourante++;
    		
    	} else if (marqueDifferente(sommet1, sommet2)) {
    		if (sommet1.getMarque() < 0) {
    			sommet1.setMarque(sommet2.getMarque());
    			
    		} else if (sommet2.getMarque() < 0) {
    			sommet2.setMarque(sommet1.getMarque());
    			
    			/* Si les deux sommets ont déjà  des marques (autre que -1), 
    			 * les sommets de même chaîne que sommet2 prend la marque du sommet1 
    			 */	
    		} else {
    			ArrayList<Sommet> sommetMarqueSommet2 = sommetsDeMemeMarque(sommet2.getMarque());
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
		int tailleTableau = 0;
		int rang = 0;
		
		ArrayList<Sommet> laListeSommetsMarque = new ArrayList<>();
		for (Sommet sommetATester : getListeSommets() ) {
			if (sommetATester.getMarque() == marque) {
				laListeSommetsMarque.add(sommetATester);
			}
		}
		return laListeSommetsMarque;
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
	 * Permet de déterminer les sommets adjacent 
	 * @param sommet du graphe
	 * @return la liste des sommet adjacents mis en paramètre
	 */
	public Sommet[] tousLesSommetsAdjacentsDuSommet(int sommet) { //TODO méthode à améliorer
		int tailleTableau = 0;

		int[] listeSommetAdjacentPossible = {
			sommet + this.getNombreColonnesLabyrinthe(),
			sommet - this.getNombreColonnesLabyrinthe(),
			sommet + 1, 
			sommet - 1 
		};	
		
		/* 
		 * Attention dans le cas où par exemple 4 est à la fin 
		 * d'une  ligne , 5 ne doit pas être un sommet adjacent.
		 * Normalement y'a pas de problème avec la méthode estAdjacent
		*/
		
		for (int i = 0; i < listeSommetAdjacentPossible.length; i++) {
			if (estAdjacent(sommet, listeSommetAdjacentPossible[i])) {
				tailleTableau++;
			}	
		}
		
		Sommet listeSommetAdjacent[] = new Sommet[tailleTableau];
		int position = 0;
		
		for (int i = 0; i < listeSommetAdjacentPossible.length; i++) {
			if (estAdjacent(sommet, listeSommetAdjacentPossible[i])) {
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
	
	/**
	 * Algorithme faisant les appels nécessaires pour créer les graphes
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