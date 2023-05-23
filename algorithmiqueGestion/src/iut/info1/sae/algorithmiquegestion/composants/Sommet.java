/**
 * Sommet.java														 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

/**
 * Classe de modélisation d'un sommet du graphe du labyrinthe.
 * En lien direct avec la classe Graphe.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class Sommet /*implements Comparable*/ {
	
	//private String nomSommet;
	
	/**
	 * Liste des sommets qui sont directement liés à
	 * celui-ci.
	 */
	private Sommet[] liaisons;
	
	/**
	 * indice de Liaison du tableau liaisons
	 * Pour se repérer en ajoutant / supprimant / éditant
	 * dans la liste des liaison
	 */
	private int indiceLiaison;

	/**
	 * Valeur X des coordonnées du sommet dans le 
	 * labyrinthe (graphe).
	 */
	private int coordonneeX;
	
	/**
	 * Valeur Y des coordonnées du sommet dans le 
	 * labyrinthe (graphe).
	 */
	private int coordonneeY;
	
	/**
	 * Marque du sommet, permet d'identifier
	 * les différentes chaînes.
	 */
	private int marque;
	
	/**
	 * Constructeur de la classe.
	 * Initialisation des attributs de la classe.
	 * 
	 * @param coordonneeX Entier de la coordonnée X du sommet créé
	 * @param coordonneeY Entier de la coordonnée Y du sommet créé
	 */
	public Sommet(int coordonneeX, int coordonneeY) {
		super();
		
		this.indiceLiaison = 0;
		this.liaisons = new Sommet[4];
		
		this.coordonneeX = coordonneeX;
		this.coordonneeY = coordonneeY;
		
		this.marque = -1; // marque par défaut
		
	}
	
	/**
	 * Retourne la liste des liaisons du sommet.
	 * 
	 * @return La liste des liaisons du sommet
	 */
	public Sommet[] getLiaisons() {
		return this.liaisons;
	}
	
	/**
	 * Création d'une liaison entre le sommet de l'instance 
	 * et celui en paramètre.
	 * 
	 * @param sommetDeLiaison
	 */
	public void creerLiaison(Sommet sommetDeLiaison) {
		if (!this.liaisonExiste(sommetDeLiaison)) {
			this.liaisons[this.indiceLiaison] = sommetDeLiaison;
		}
		
		if (!sommetDeLiaison.liaisonExiste(this)) {
			sommetDeLiaison.creerLiaison(this);
		}
		
		this.indiceLiaison++;
	}
	
	/**
	 * Retourne si la liaison entre le sommet de l'instance 
	 * et celui en paramètre existe.
	 * 
	 * @param autreSommet
	 * @return L'état de la liaison
	 */
	public boolean liaisonExiste(Sommet autreSommet) {
		for (Sommet rechercheSommet : this.getLiaisons()) {
			if (rechercheSommet == autreSommet)	{
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Retourne la coordonnée X du sommet de l'instance
	 * @return X
	 */
	public int getCoordonneeX() {
		return this.coordonneeX;
	}
	
	/**
	 * Retourne la coordonnée Y du sommet de l'instance
	 * @return Y
	 */
	public int getCoordonneeY() {
		return this.coordonneeY;
	}

    public void setCoordonneeX(int coordonneeX) {
		this.coordonneeX = coordonneeX;
	}

	public void setCoordonneeY(int coordonneeY) {
		this.coordonneeY = coordonneeY;
	}

	/**
     * Retourne la marque du sommet
     * @return valeur de marque 
     */
    public int getMarque() {
        return marque;
    }

    /** @param marque nouvelle valeur de marque */
    public void setMarque(int marque) {
        this.marque = marque;
    }
	
}
