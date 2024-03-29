/*
 * Sommet.java														 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Modélisation d'un sommet d'un graphe.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class Sommet {
	
	/** Indice du sommet */
	private int indiceSommet;

    /** Sommets voisins à this dans le graphe. */
    private ArrayList<Integer> liaisons;

    /** Valeur X (horizontale) des coordonnées de this dans le graphe. */
    private int coordonneeX;

    /** Valeur Y (verticale) des coordonnées de this dans le graphe. */
    private int coordonneeY;

    /**
     * Permet d'identifier la chaîne auquel le sommet appartient. Par défaut, la
     * marque est initialisée à -1.
     */
    private int marque;

    /**
     * Permet de savoir si un sommet a déjà été parcoru lors d'une recherche de
     * solution. Par défaut, est initialisé à false.
     */
    private boolean parcouru;

    /**
     * Sommet d'un graphe avec ses coordonnées X et Y.
     * 
     * @param coordonneeX Entier correspondant à la coordonnée X de this.
     * @param coordonneeY Entier correspondant à la coordonnée Y de this.
     */
    public Sommet(int coordonneeX, int coordonneeY, int indiceSommet) {
        super();

        this.indiceSommet = indiceSommet;
        
        this.coordonneeX = coordonneeX;
        this.coordonneeY = coordonneeY;

        this.marque = -1; // marque par défaut
        this.parcouru = false;

        this.liaisons = new ArrayList<>();
    }

    /** @return La liste des liaisons de this. */
    public ArrayList<Integer> getLiaisons() {
        return this.liaisons;
    }

    /** @return Entier correspondant à la coordonnée X de this. */
    public int getCoordonneeX() {
        return this.coordonneeX;
    }

    /** @return Entier correspondant à la coordonnée Y de this. */
    public int getCoordonneeY() {
        return this.coordonneeY;
    }

    /** @return Entier correspondant à la marque de this. */
    public int getMarque() {
        return marque;
    }

    /** @return si le sommet a été parcouru */
    public boolean isParcouru() {
        return parcouru;
    }

    /** @param coordonneeX Nouvelle coordonnée horizontale de this. */
    public void setCoordonneeX(int coordonneeX) {
        this.coordonneeX = coordonneeX;
    }

    /** @param coordonneeY Nouvelle coordonnée verticale de this. */
    public void setCoordonneeY(int coordonneeY) {
        this.coordonneeY = coordonneeY;
    }

    /** @param marque Nouvelle valeur de marque de this. */
    public void setMarque(int marque) {
        this.marque = marque;
    }

    /** @param parcouru, true si le sommet a été parcouru, false sinon */
    public void setParcouru(boolean estParcouru) {
        this.parcouru = estParcouru;
    }

    /**
     * Création d'une liaison entre this et le sommet en paramètre. Cette liaison
     * est ajoutée à l'attribut liaisons.
     * 
     * @param sommetALier Le sommet à lier à this.
     */
    public void creerLiaison(Sommet sommetALier) {
        if (!this.liaisonExiste(sommetALier)) {
            this.liaisons.add(sommetALier.indiceSommet);
        }
        if (!sommetALier.liaisonExiste(this)) {
            sommetALier.creerLiaison(this);
        }
    }

    /**
     * Vérifie si le sommet en paramètre se trouve dans la liste de liaisons de
     * this.
     * 
     * @param sommetATester Sommet dont il faut vérifier la présence dans la liste
     *                      de liaisons de this.
     * @return Un booléen informant si la liaison existe ou non.
     */
    public boolean liaisonExiste(Sommet sommetATester) {
        boolean resultat = false;

        for (int sommetLie : this.getLiaisons()) {
            if (sommetLie == sommetATester.indiceSommet) {
                resultat = true;
            }
        }
        return resultat;
    }

    /**
     * Vérifie si le sommet en paramètre a les mêmes coordonnées que this.
     * Utile pour le jeu de test ou l'on a mis tout les indice du sommet à 0,
     * donc la redéfinition de la méthode equals ne serais pas fonctionnel.
     * 
     * @param sommetATester Sommet dont il faut vérifier l'égalité des coordonnées
     *                      avec this.
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

    /** non javadoc - @see java.util.Objects#equals(Object) */
    @Override
    public boolean equals(Object obj) {
        Sommet autreSommet = (Sommet) obj;

        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        return this.indiceSommet == autreSommet.indiceSommet;
    }

    /** non javadoc - @see java.util.Objects#hashCode() */
    @Override
    public int hashCode() {
        return Objects.hash(coordonneeX, coordonneeY);
    }

}
