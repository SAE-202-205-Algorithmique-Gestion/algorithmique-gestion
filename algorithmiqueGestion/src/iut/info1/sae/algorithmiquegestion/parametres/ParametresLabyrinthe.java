/*
 * ParametresLabyrinthe.java										 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.parametres;

/**
 * Classe de paramétrage du labyrinthe par l'utilisateur.
 * 
 * @author Jonathan GUIL
 */
public class ParametresLabyrinthe {
	
	private int longueurLabyrinthe;
	
	private int hauteurLabyrinthe;
	
	public ParametresLabyrinthe() {
		super();
		
		this.longueurLabyrinthe = 5;
		this.hauteurLabyrinthe = 5;
	}
	
	public int getLongueurLabyrinthe() {
		return this.longueurLabyrinthe;
	}
	
	public void setLongueurLabyrinthe(int nouvelleLongueur) {
		this.longueurLabyrinthe = nouvelleLongueur;
	}
	
	public int getHauteurLabyrinthe() {
		return this.hauteurLabyrinthe;
	}
	
	public void setHauteurLabyrinthe(int nouvelleHauteur) {
		this.hauteurLabyrinthe = nouvelleHauteur;
	}

}
