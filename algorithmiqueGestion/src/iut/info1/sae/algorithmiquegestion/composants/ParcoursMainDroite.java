/*
 * ParcoursMainDroite.java                                           5 juin 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.ArrayList;

/**
 * Parcours d'un graphe suivant la méthode de parcours dit "à main droite".
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class ParcoursMainDroite {
	
	/** Labyrinthe de longueur et largeur 5 utilisé pour ce parcours. */
	private Labyrinthe labyrinthe = new Labyrinthe(5, 5);
	
	/** Liste des sommets liés au sommet de départ. */
	private ArrayList<Sommet> listeSommetsLiesDepart
	= labyrinthe.getEntree().getLiaisons();
	
	/** Liste des sommets liés au sommet courant. */
	private ArrayList<Sommet> listeSommetsLies
	= labyrinthe.getPositionActuelle().getLiaisons();
	
	/**
	 * Orientation courante sur une case
	 * ('N'ord, 'E'st, 'S'ud, 'O'uest)
	 */
	private char orientationCourante;
	
	/** @return L'orientation de départ choisie. */
	public char determinationOrientationDepart() {
		return ' '; //bush
	}
	
	/**
	 * Détermine une nouvelle orientation pour this.
	 */
	public void nouvelleOrientation() {
		
	}
}

