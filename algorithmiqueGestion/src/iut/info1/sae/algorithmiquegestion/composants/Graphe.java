/**
 * Graphe.java														 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.Random;

/**
 * Classe de modélisation du graphe du labyrinthe.
 * En lien direct avec la classe Sommet.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class Graphe {
	
	/** Liste des sommets du graphe */
	private Sommet[] listeSommets;
		
	/**
	 * Longueur du labyrinthe
	 * <p>
	 * Cette donnée est choisie par l'utilisateur
	 * au lancement du jeu de labyrinthe</p>
	 */
	private int nombreColonnesLabyrinthe;
	
	/**
     * Hauteur du labyrinthe.
     * <p>
     * Cette donnée est choisie par l'utilisateur
     * au lancement du jeu de labyrinthe</p>
     */
	private int nombreLignesLabyrinthe;
	
	/** Liste des marques des sommets déjà existantes */
	private int[] marquesExistante;
	
	/**
	 * Valeur maximale des valeurs des marques
	 * <p>
	 * Permet d'affecter une marque non existante à un sommet</p>
	 */
    private int valeurMarqueCourante;
	
	/**
	 * Constructeur de la classe Graphe.
	 * Initialisation des attributs de la classe.
	 * 
	 * @param listeSommets // TODO : javadoc
	 */
	public Graphe(int nombreColonnesLabyrinthe, int nombreLignesLabyrinthe) {
		super();

//		this.indiceCourantListeSommet = 0;
		
		// TODO: remplacer par JSON
		this.nombreColonnesLabyrinthe = nombreColonnesLabyrinthe;
		this.nombreLignesLabyrinthe = nombreLignesLabyrinthe;
		this.marquesExistante = new int[this.nombreColonnesLabyrinthe   // car le nombre de marques
		                                * this.nombreLignesLabyrinthe]; // possible est < aux 
		                                                                // nombre de sommet. 
		                                                                // Histoire d'être sur d'avoir
		                                                                // la place, valeur temporaire.
		
		this.listeSommets = new Sommet[this.nombreColonnesLabyrinthe * this.nombreLignesLabyrinthe];
		for (int i = 0; i < getNombreSommets(); i++) {
			this.listeSommets[i] = determinationCoordonnees(i);
		}
		
		this.valeurMarqueCourante = 0;
//		this.creationDuGraphe();
		
	}
	
	/**
	 * Retourne le nombre de sommets du graphe.
	 * 
	 * @return La longueur de la liste des sommets du 
	 * 		   graphe
	 */
	public int getNombreSommets() {
		return this.listeSommets.length;
	}
	
	/**
	 * Retourne la liste des sommets du graphe.
	 * 
	 * @return La liste des sommets du graphe
	 */
	public Sommet[] getListeSommets() {
		return this.listeSommets;
	}
	
	public int getNombreColonnesLabyrinthe() {
		return nombreColonnesLabyrinthe;
	}

	public int getNombreLignesLabyrinthe() {
		return nombreLignesLabyrinthe;
	}

	/**
	 * Création d'un sommet rattaché au graphe de 
	 * l'instance.
	 * 
	 * @return L'instance du sommet créé
	 */
	public Sommet determinationCoordonnees(int indiceCourantListeSommet) {
		
		int nombreLiaisonsMaximum, //TODO (à enlever je pense)
			coordonneeX,
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
		
		
		Sommet sommetCree = new Sommet(coordonneeX, coordonneeY);
		System.out.printf("X = %d ; Y = %d\n", coordonneeX, coordonneeY);
		
		return sommetCree;
		
	}
	
	/**
	 * Vérifie si deux sommets sont adjacents à l'aide de 
	 * leurs coordonnées.
	 * 
	 * @param sommet1 Instance du premier sommet
	 * @param sommet2 Instance du second sommet
	 * @return Si les deux sommets sont adjacents
	 */
	public boolean estAdjacent(Sommet sommet1, Sommet sommet2) {
		if (!this.sommetExiste(sommet1)
			|| !this.sommetExiste(sommet2)) {
			
			return false;
		}
		
		return (sommet1.getCoordonneeX() == sommet2.getCoordonneeX()
				&& (sommet1.getCoordonneeY() == sommet2.getCoordonneeY() + 1
					|| sommet1.getCoordonneeY() == sommet2.getCoordonneeY() - 1))
					
			|| (sommet1.getCoordonneeY() == sommet2.getCoordonneeY()
				&& (sommet1.getCoordonneeX() == sommet2.getCoordonneeX() + 1
					|| sommet1.getCoordonneeX() == sommet2.getCoordonneeX() - 1));
	}
	
	
	/** 
	 * @param sommet1
	 * @param sommet2
	 * @return si les 2 sommets ont des marques différentes.
	 */
	public boolean marqueDifferente(Sommet sommet1, Sommet sommet2) {
	    return sommet1.getMarque() != sommet2.getMarque();
	}
	
	/**
	 * Définit une marque pour le ou les sommets
	 * @param sommet1
	 * @param sommet2
	 */
	public void definitUneMarque(Sommet sommet1, Sommet sommet2) {
		
//	    if (estAdjacent(sommet1, sommet2)) { //plus besoin
	    	
	    	/* Dans le cas ou les marques des 2 sommets en question
	    	 * ne sont pas initialisées. */
	    	if (sommet1.getMarque() < 0 && sommet2.getMarque() < 0) {
	    		sommet1.setMarque(valeurMarqueCourante);
	    		sommet2.setMarque(valeurMarqueCourante);
	    		valeurMarqueCourante++;
	    	}
	    	
	    	else if (marqueDifferente(sommet1, sommet2)){
	    		if (sommet1.getMarque() < 0) {
	    			sommet1.setMarque(sommet2.getMarque());
	    		} else if (sommet2.getMarque() < 0) {
	    			sommet2.setMarque(sommet1.getMarque());
	    			
	    			/* Si les deux sommets ont déjà des marques (autre que -1), 
	    			 * les sommets de même chaîne que sommet2 prend la marque du sommet1 */	
	    		} else {
	    			Sommet[] sommetMarqueSommet2 = sommetsDeMemeMarque(sommet2.getMarque());
	    			for (int i = 0; i < sommetMarqueSommet2.length; i++) {
	    				sommetMarqueSommet2[i].setMarque(sommet1.getMarque());
	    			}
	    		}
	    	}
//	    }
	}
	
	/**
	 * Renvoie la liste des sommets portant une certaine marque.
	 * @param marque La marque que les sommets doivent posséder.
	 * @return La liste des sommets possédant la marque
	 */
	public Sommet[] sommetsDeMemeMarque(int marque) {
		int tailleTableau = 0;
		int rang = 0;
		
//		Sommet[] listeSommetsMarque = new Sommet[getNombreSommets()-1];
		for (int i = 0; i < getNombreSommets(); i++) {
			if (getListeSommets()[i].getMarque() == marque) {
				tailleTableau++;
			}
		}
		Sommet[] listeSommetsMarque = new Sommet[tailleTableau];
		for (int i = 0; i < getNombreSommets(); i++) {
			if (getListeSommets()[i].getMarque() == marque) {
				listeSommetsMarque[rang] = getListeSommets()[i];
				rang++;
			}
		}
		return listeSommetsMarque;
	}
	
	/**
	 * méthode utilisant Random pour choisir un sommet de façon
	 * aléatoire ainsi qu'un deuxième sommet, restreint
	 *  à ceux adjacents au premier
	 * @return couple de sommets adjacents
	 */
	public Sommet[] sommetsAleatoires() {
		/**
		* entiers aléatoires correspondant à un indice dans la liste des sommets
		*/
		int sommet,
			sommetAdjacent;
		
		sommet = new Random().nextInt(this.getNombreSommets());//x = Math.random() * this.getNombreSommets();
//		System.out.println("sommet random tiré : " + sommet);
			
		Sommet[] listeDesSommetsAdjacents = this.tousLesSommetsAdjacentsDuSommet(this.getListeSommets()[sommet]);
		sommetAdjacent = new Random().nextInt(listeDesSommetsAdjacents.length);
		
		Sommet[] sommetsAleatiores = {this.getListeSommets()[sommet], listeDesSommetsAdjacents[sommetAdjacent]};
		return sommetsAleatiores;
	}

	/**
	 * Permet de déterminer les sommets adjacent 
	 * @param sommet du graphe
	 * @return la liste des sommet adjacents mis en paramètre
	 */
	public Sommet[] tousLesSommetsAdjacentsDuSommet(Sommet sommet) { //TODO méthode a améliorer
		int tailleTableau = 0;

		Sommet adjacent1 = new Sommet(sommet.getCoordonneeX(), sommet.getCoordonneeY() + 1);
		Sommet adjacent2 = new Sommet(sommet.getCoordonneeX(), sommet.getCoordonneeY() - 1);
		Sommet adjacent3 = new Sommet(sommet.getCoordonneeX() + 1, sommet.getCoordonneeY());
		Sommet adjacent4 = new Sommet(sommet.getCoordonneeX() - 1, sommet.getCoordonneeY());
		
		Sommet[] listeSommetAdjacentPossible = {adjacent1, adjacent2, adjacent3, adjacent4};
		
		for (int i = 0; i < listeSommetAdjacentPossible.length; i++) {
			if (estAdjacent(sommet, listeSommetAdjacentPossible[i])) {
				tailleTableau++;
			}
		}
		
		Sommet listeSommetAdjacent[] = new Sommet[tailleTableau];
		int position = 0;
		
		for (int i = 0; i < listeSommetAdjacentPossible.length; i++) {
			if (estAdjacent(sommet, listeSommetAdjacentPossible[i])) {
				listeSommetAdjacent[position] = listeSommetAdjacentPossible[i];
				position++;
			}
		}
		
		return listeSommetAdjacent;
	}
	
	/**
	 * Permet de vérifier l'existance d'un sommet dans le graphe
	 * à partir de ses coordonnées.
	 * @param sommet
	 * @return si le sommet fais bien parti du graphe ou pas
	 */
	public boolean sommetExiste(Sommet sommet) {
		return sommet.getCoordonneeX() >= 0 && sommet.getCoordonneeX() < this.getNombreColonnesLabyrinthe()
			&& sommet.getCoordonneeY() >= 0 && sommet.getCoordonneeY() < this.getNombreLignesLabyrinthe();
	}
	
	public void creationDuGraphe() {
		int nombreDeLiaison = 1;
		
		while (nombreDeLiaison < getNombreSommets()) {
			System.out.println("Nombre de laison : " + nombreDeLiaison);
			Sommet[] sommetsChoisi = new Sommet[2];
			
			sommetsChoisi = this.sommetsAleatoires();
//			if (marqueDifferente(sommetsChoisi[0], sommetsChoisi[1])) {
				
				definitUneMarque(sommetsChoisi[0], sommetsChoisi[1]);
				sommetsChoisi[0].creerLiaison(sommetsChoisi[1]);
				nombreDeLiaison++;
				
//			}
		}
		
	}
}