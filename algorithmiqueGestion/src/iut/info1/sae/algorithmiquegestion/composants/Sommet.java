/**
 * Sommet.java														 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.Objects;

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
	
	/**
	 * Liste des sommets qui sont directement liés à
	 * celui-ci.
	 */
	private Sommet[] liaisons;
	
	/**
	 * Indice de liaison du tableau liaisons
	 * pour se repérer en ajoutant / supprimant / éditant
	 * dans la liste des liaisons
	 */
	private int indiceLiaison;

	/**
	 * Valeur X (horizontale) des coordonnées du sommet dans le 
	 * labyrinthe (graphe)
	 */
	private int coordonneeX;
	
	/**
	 * Valeur Y (verticale) des coordonnées du sommet dans le 
	 * labyrinthe (graphe)
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
	 * et celui en paramètre
	 * 
	 * @param sommetALier Le sommet à lier au sommet d'instance
	 */
	public void creerLiaison(Sommet sommetALier) {
		if (!this.liaisonExiste(sommetALier)) {
			this.liaisons[this.indiceLiaison] = sommetALier;
		}
		
		if (!sommetALier.liaisonExiste(this)) {
			sommetALier.creerLiaison(this);
		}
		
		this.indiceLiaison++;
	}
	
	/**
	 * Vérifie si le sommet en paramètre se trouve dans la
	 * liste de liaisons du sommet appelant.
	 * @param sommetTeste Sommet dont il faut vérifier la présence
	 * 					  dans la liste de liaisons de l'appelant
	 * @return Un booléen informant si la liaison existe ou non
	 */
	public boolean liaisonExiste(Sommet sommetTeste) {
		boolean resultat = false;

		for (Sommet sommetLie : this.getLiaisons()) {
			if (sommetLie == sommetTeste)	{
				resultat = true;
			}
		}
		return resultat;
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
    
//    /* C'est pour que la méthode assertArrayEquals de Junit
//     * compare les valeurs des tableau et non leur référence*/
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null || getClass() != obj.getClass())
//            return false;
//        Sommet other = (Sommet) obj;
//        // Comparez les valeurs des propriétés des objets Sommet
//        return this.coordonneeX == other.coordonneeX && this.coordonneeY == other.coordonneeY;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(coordonneeX, coordonneeY);
//    }
	
}
