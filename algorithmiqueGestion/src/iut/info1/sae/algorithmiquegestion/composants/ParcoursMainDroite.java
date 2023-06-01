package iut.info1.sae.algorithmiquegestion.composants;

import java.util.ArrayList;

import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;

/**
 * Programme de parcours de labyrinthe à la façon "suivis main droite"          01/06/2023
 * @author Samuel
 *
 */
public class ParcoursMainDroite {
	/*
	 * Création d'un labyrinthe 
	 */
	private Labyrinthe labyrinthe = new Labyrinthe(5, 5);
	
	/*
	 * Liste des sommets liés au sommet de départ
	 */
	private ArrayList<Sommet> listeSommetLieDepart = labyrinthe.getEntree().getLiaisons();
	
	/*
	 * Liste des sommets liés au sommet courant
	 */
	private ArrayList<Sommet> listeSommetsLie = labyrinthe.getPositionActuelle().getLiaisons();
	
	/*
	 * Orientation courante sur une case
	 * ('N'ord, 'E'st, 'S'ud, 'O'uest)
	 */
	private char orientationCourante;
	
	public char determinationOrientationDepart() {
		return ' '; //bush
	}
	
	public void nouvelleOrientation() {
		
	}
}

