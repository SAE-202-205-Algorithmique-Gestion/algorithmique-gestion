/*
 * ParametresLabyrinthe.java										 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.parametres;

import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;

/**
 * Classe de paramétrage du labyrinthe par l'utilisateur.
 * 
 * @author Jonathan GUIL
 */
public class ParametresLabyrinthe {
	
	private String nomLabyrinthe;
	
	private Labyrinthe labyrintheASauvegarder;
	
	public ParametresLabyrinthe(String nomLabyrinthe, 
								Labyrinthe labyrintheASauvegarder) {
		
		super();
		
		this.nomLabyrinthe = nomLabyrinthe;
		this.labyrintheASauvegarder = labyrintheASauvegarder;
		
	}
	
	public int getLongueurLabyrinthe() {
		return this.labyrintheASauvegarder.getNombreDeColonne();
	}
	
	/*
	public void setLongueurLabyrinthe(int nouvelleLongueur) {
		this.longueurLabyrinthe = nouvelleLongueur;
	}
	*/
	
	public int getHauteurLabyrinthe() {
		return this.labyrintheASauvegarder.getNombreDeLigne();
	}
	
	/*
	public void setHauteurLabyrinthe(int nouvelleHauteur) {
		this.hauteurLabyrinthe = nouvelleHauteur;
	}
	*/
	
	public String getNomLabyrinthe() {
		return this.nomLabyrinthe;
	}

}
