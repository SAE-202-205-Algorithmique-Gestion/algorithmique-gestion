/*
 * Pile.java											  			 12 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */

package iut.info1.sae.algorithmiquegestion.piles;

import java.util.ArrayList;

/**
 * Classe de fabrication et de gestion de piles
 * Une pile fonctionne de la façon suivante : LIFO.
 * @author Simon GUIRAUD
 * @author Loïc FAUGIERES
 */
public class Pile {
	/**	Nombre max d'objets empilables initialement */
	private static final int CAPACITE_INITIALE = 10;

	/** Representation contigue = bloc de références des objets empilés */
	private Object[] contenu;
	
	/** TODO : faire la javadoc */
	private int taille;
	
	/**
	 * TODO : faire la javadoc
	 */
	public Pile() {
		super();
		contenu = new Object[CAPACITE_INITIALE];
		// taille = 0;
	}
	
	/**
	 * TODO : faire la javadoc
	 */
	public Pile(Object element) {
		contenu = new Object[1];
		contenu[0] = element;
		
		
	}
	
	/**
	 * TODO : faire la javadoc
	 */
	public boolean isVide() {
		return taille == 0;
		// TODO : compléter la méthode
	}
	
	/**
	 * Accesseur de l'attribut contenu
	 * @return Attribut contenu
	 */
	public Object getContenu() {
		return contenu;
	}
	/**
	 * TODO : faire la javadoc
	 */
	public Pile empiler(Object element) {
		Pile pileEmpile = new Pile();
		
	}
	
	/**
	 * TODO : faire la javadoc
	 */
	public Pile depiler(Object element) {
		Pile pileDepile = new Pile();
		
	}
}
