/**
 * Sommet.java														 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.Objects;

//import java.util.Objects;

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
public class Sommet {
	
	/** Liste des sommets qui sont voisins dans le graphe au sommet instancié. */
	private Sommet[] liaisons;
	
	/**
	 * Indice de liaison du tableau liaisons
	 * pour se repérer dans la liste des liaisons.
	 */
	private int indiceLiaison;

	/** Valeur X (horizontale) des coordonnées du sommet dans le graphe. */
	private int coordonneeX;
	
	/** Valeur Y (verticale) des coordonnées du sommet dans le graphe. */
	private int coordonneeY;
	
	/** indice de la liste de sommet dans la classe Graphe */
//	private int indiceSommet;
	
	/**
	 * Permet d'identifier la chaîne auquel le sommet appartient.
	 * Par défaut, la marque est initialisée à -1.
	 */
	private int marque;
	
	/**
	 * Initialisation d'un sommet du graphe.
	 * 
	 * @param coordonneeX Entier de la coordonnée X du sommet créé.
	 * @param coordonneeY Entier de la coordonnée Y du sommet créé.
	 */
	public Sommet(int coordonneeX, int coordonneeY/*, int indiceSommet*/) {
		super();
		
		this.indiceLiaison = 0;
		this.liaisons = new Sommet[4];
//		this.indiceSommet = indiceSommet;
		this.coordonneeX = coordonneeX;
		this.coordonneeY = coordonneeY;
		
		this.marque = -1; // marque par défaut
	}
	
	/**
	 * Accesseur de l'attribut liaisons contenant la liste
	 * des sommets voisins du sommet instancié.
	 * 
	 * @return La liste des liaisons du sommet instancié.
	 */
	public Sommet[] getLiaisons() {
		return this.liaisons;
	}
	
	/**
	 * Accesseur de l'attribut coordonneeX, contenant la
	 * coordonnée horizontale du sommet instancié.
	 * 
	 * @return Entier correspondant à la coordonnée X du sommet instancié.
	 */
	public int getCoordonneeX() {
		return this.coordonneeX;
	}
	
	/**
	 * Accesseur de l'attribut coordonneeY, contenant la
     * coordonnée verticale du sommet instancié.
     * 
	 * @return Entier correspondant à la coordonnée Y du sommet instancié.
	 */
	public int getCoordonneeY() {
		return this.coordonneeY;
	}

    /**
     * Accesseur de l'attribut marque identifiant la chaîne auquel
     * le sommet instancié appartient.
     * 
     * @return Entier correspondant à l'attribut marque du sommet instancié.
     */
    public int getMarque() {
        return marque;
    }
    
    /**
     * Acesseur de l'attribut indiceSommet.
     * 
     * @return Entier correspondant à l'indice du sommet.
     */
//    public int getIndiceSommet() {
//		return indiceSommet;
//	}
    
    /**
     * Modifieur de l'attribut coordonneeX du sommet instancié.
     * 
     * @param coordonneeX Nouvelle coordonnée horizontale du sommet instancié.
     */
    public void setCoordonneeX(int coordonneeX) {
        this.coordonneeX = coordonneeX;
    }

    /**
     * Modifieur de l'attribut coordonneeY du sommet instancié.
     * 
     * @param coordonneeY Nouvelle coordonnée verticale du sommet instancié.
     */
    public void setCoordonneeY(int coordonneeY) {
        this.coordonneeY = coordonneeY;
    }
    
    /**
     * Modifieur de l'attribut marque du sommet instancié.
     * 
     * @param marque Nouvelle valeur de marque du sommet instancié.
     * */
    public void setMarque(int marque) {
        this.marque = marque;
    }

	/**
	 * Création d'une liaison entre le sommet instancié et celui en paramètre.
	 * Cette liaison est ajoutée à l'attribut liaisons.
	 * 
	 * @param sommetALier Le sommet à lier au sommet instancié.
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
	 * liste de liaisons du sommet instancié.
	 * 
	 * @param sommetATester Sommet dont il faut vérifier la présence
	 * 					    dans la liste de liaisons de l'appelant.
	 * @return Un booléen informant si la liaison existe ou non.
	 */
	public boolean liaisonExiste(Sommet sommetATester) {
		boolean resultat = false;

		for (Sommet sommetLie : this.getLiaisons()) {
			if (sommetLie == sommetATester)	{
				resultat = true;
			}
		}
		return resultat;
	}
	
	/**
	 * Vérifie si le sommet en paramètre a les mêmes coordonnées
	 * que le sommet instancié.
	 * 
	 * @param sommetATester Sommet dont il faut vérifier l'égalité des
	 * 						coordonnées avec le sommet instancié.
	 * @return Un booléen informant si les coordonnées des sommets sont les mêmes.
	 */
	public boolean sommetEgal(Sommet sommetATester) {
		if (this.getCoordonneeX() == sommetATester.getCoordonneeX()
			&& this.getCoordonneeY() == sommetATester.getCoordonneeY()) {
			return true;
		}
		return false;
	}
	
	/** non javadoc - @see java.util.Objects#toString() */
	@Override
    public String toString() {
        return "(" + this.getCoordonneeX() + " ; " + this.getCoordonneeY() + ")";
    }
    
    /* C'est pour que la méthode assertArrayEquals de Junit
     * compare les valeurs des tableau et non leur référence */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Sommet other = (Sommet) obj;
        // Comparez les valeurs des propriétés des objets Sommet
        return this.coordonneeX == other.coordonneeX && this.coordonneeY == other.coordonneeY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordonneeX, coordonneeY);
    }
	
}
