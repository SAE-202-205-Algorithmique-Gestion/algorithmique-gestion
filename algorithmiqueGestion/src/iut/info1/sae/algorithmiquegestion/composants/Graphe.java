/**
 * Graphe.java														 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

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
	
	/**
	 * Liste des sommets du graphe.
	 */
	private Sommet[] listeSommets;
		
	/**
	 * Longueur du labyrinthe.
	 * Donnée choisie par l'utilisateur.
	 */
	private int nombreColonnesLabyrinthe;
	
	/**
	 * Hauteur du labyrinthe.
	 * Donnée choisie par l'utilisateur.
	 */
	private int nombreLignesLabyrinthe;
	
	/**
	 * Liste des marques déjà existante.
	 */
	private int[] marquesExistante;
	
	/**
	 * Valeur de la marque Maximum, permet d'affecter
	 * une marque non existante à un sommet.
	 */
    private int valeurMarqueMaximum;
	
	/**
	 * Constructeur de la classe.
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
			this.listeSommets[i] = creerSommet(i);
		
//			initialiser listeSommet pour éviter l'erreur
//			à la méthode sommetDeMemeMarque.
		}
		this.valeurMarqueMaximum = 0;
		
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
	
	/**
	 * Création d'un sommet rattaché au graphe de 
	 * l'instance.
	 * 
	 * @return L'instance du sommet créé
	 */
	public Sommet creerSommet(int indiceCourantListeSommet) {
		
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
	public static boolean marqueDifferente(Sommet sommet1, Sommet sommet2) {
	    return sommet1.getMarque() != sommet2.getMarque();
	}
	
	/**
	 * Définit une marque pour le ou les sommets
	 * @param sommet1
	 * @param sommet2
	 */
	public void definitUneMarque(Sommet sommet1, Sommet sommet2) {
	    
	    
	    /* Dans le cas ou les marques des 2 sommets en question
	     * ne sont pas initialisées. */
	    if (sommet1.getMarque() < 0 && sommet2.getMarque() < 0) {
	        
	        /* pas besoin de d'itérer sur tout le tableau car après la première
	         * valeur 0 trouver, toutes les autres seront égal a 0.*/
	        /*for (int i = 0; this.marquesExistante[i] != 0; i++) {
	            if (this.marquesExistante[i] > this.marquesExistante[i+1]) {
	                valeurMarqueMaximum = this.marquesExistante[i];
	            }
	            
	    	
	        }*/
	        sommet1.setMarque(valeurMarqueMaximum);
	        sommet2.setMarque(valeurMarqueMaximum);
	        valeurMarqueMaximum++;
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
	}
	
	/**
	 * Renvoie la liste des sommets portant une certaine marque
	 * @param marque
	 * @return Sommet[] liste de sommets
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

	
}