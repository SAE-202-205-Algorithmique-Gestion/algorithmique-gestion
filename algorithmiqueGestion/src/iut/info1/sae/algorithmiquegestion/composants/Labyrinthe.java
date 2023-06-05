/*
 * Labyrinthe.java                                                   5 juin 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

import java.util.Scanner;

import iut.info1.sae.algorithmiquegestion.parametres.ParametresLabyrinthe;
import iut.info1.sae.algorithmiquegestion.sauvegardes.SauvegardeLabyrinthe;

/**
 * Modélisation d'un labyrinthe utilisable sur console texte
 * à partir des classes Graphe et Sommet.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class Labyrinthe {
	
	private static final char HAUT = 'h';
	
	private static final char BAS = 'b';
	
	private static final char DROITE = 'd';
	
	private static final char GAUCHE = 'g';
	
	/**
	 * Touche permettant de déclancher le processus de 
	 * sauvegarde du labyrinthe.
	 */
	private static final char SAUVER = 's';
	
	private static final char SOMMET_ACTUEL_SYMBOLE = 'X';
	
	private static final char ENTREE_SYMBOLE = 'E';
	
	private static final char SORTIE_SYMBOLE = 'S';
	
	private Scanner entreeDeplacement;
	
	private Sommet positionActuelle;
	
	private Sommet entree;

	private Sommet sortie;
	
	private int nombreDeColonne;

	private int nombreDeLigne;
	
	private int indiceSommetActuelle;
	
	private ChainesAscendantes graphe;
	
	/**
	 * Labyrinthe avec son nombre de lignes et de colonnes.
	 *
	 * @param nombreDeLignes Le nombre de lignes (Y) du labyrinthe.
	 * @param nombreDeColonnes Le nombre de colonnes (X) du labyrinthe.
	 */
	public Labyrinthe(int nombreDeLignes, int nombreDeColonnes) {

		super();
		
		this.nombreDeLigne = nombreDeLignes;
		
		this.nombreDeColonne = nombreDeColonnes;
		
		/* C'est normal que le nombre de colonnes et de lignes soit inversées dans l'appel*/
		this.graphe = new ChainesAscendantes(nombreDeColonnes, nombreDeLignes);
		this.entreeDeplacement = new Scanner(System.in);
		this.definirEntree();
		this.definirSortie();
//		this.positionActuelle = this.getEntree();
	}
	
	public Sommet getEntree() {
		return entree;
	}

	public void setEntree(Sommet entree) {
		this.entree = entree;
	}

	public Sommet getSortie() {
		return sortie;
	}

	public void setSortie(Sommet sortie) {
		this.sortie = sortie;
	}
	
	public int getIndiceSommetActuelle() {
		return indiceSommetActuelle;
	}
	
	public void setIndiceSommetActuelle(int indiceSommetActuelle) {
		this.indiceSommetActuelle = indiceSommetActuelle;
	}
	
	public Sommet getPositionActuelle() {
		return positionActuelle;
	}
	
	public void setPositionActuelle(Sommet positionActuelle) {
		this.positionActuelle = positionActuelle;
	}
	
	public Graphe getGraphe() {
		return graphe;
	}
	
	public char getEntreeSymbole() {
		return ENTREE_SYMBOLE;
	}

	public char getSortieSymbole() {
		return SORTIE_SYMBOLE;
	}
	
	public char getSommetActuelleSymbole() {
		return SOMMET_ACTUEL_SYMBOLE;
	}

	public void definirEntree() {
		boolean entreeTrouvee = false;
		for (int i = 0; i < graphe.getListeSommets().length && !entreeTrouvee; i++) {
			if (graphe.getListeSommets()[i].getLiaisons().size() == 1) {
				this.setEntree(graphe.getListeSommets()[i]);
				this.indiceSommetActuelle = i;
				entreeTrouvee = true;
			}
		}
	}
	
	public void definirSortie() {
		boolean sortieTrouvee = false;
		for (int i = graphe.getListeSommets().length - 1; i >= 0 && !sortieTrouvee; i--) {
			if (graphe.getListeSommets()[i].getLiaisons().size() == 1) {
				this.setSortie(graphe.getListeSommets()[i]);
				sortieTrouvee = true;
			}
		}
	}
	
//	public boolean gestionErreurSaisie(char entreeScanner) {
//		for (int indiceChaine = 0; indiceChaine < entreeScanner.length(); indiceChaine++) {
//				if (entreeScanner.toLowerCase().charAt(indiceChaine) != 'z'
//					&& entreeScanner.toLowerCase().charAt(indiceChaine) != 's'
//					&& entreeScanner.toLowerCase().charAt(indiceChaine) != 'd'
//					&& entreeScanner.toLowerCase().charAt(indiceChaine) != 'q'
//					&& entreeScanner.toLowerCase().charAt(indiceChaine) != ' ') {
//					
//					System.out.println(MESSAGE_ERREUR_SAISIE + " eeedd");
//					return false;
//			}
//		}
//		return true;
//		
//	}
	
	public boolean verificationDeplacement(int indiceSommetDeplacement) {
		
		if (graphe.sommetExiste(indiceSommetActuelle + indiceSommetDeplacement)
			&& graphe.getListeSommets()[indiceSommetActuelle]
				.liaisonExiste
				(graphe.getListeSommets()[indiceSommetActuelle +
				                          indiceSommetDeplacement])) {
			this.setIndiceSommetActuelle(indiceSommetActuelle + indiceSommetDeplacement);
			this.setPositionActuelle(graphe.getListeSommets()[indiceSommetActuelle]);
			return true;
		}
		return false;
	}
	
	public boolean demandeDeplacement() {
		boolean conditionArret = false;
		boolean saisieCorrecte = true;
		
		int indiceSaisieDeplacement;
		
		String saisieDeplacement = this.entreeDeplacement.next()
								   + this.entreeDeplacement.nextLine();
		
		saisieDeplacement = saisieDeplacement.replaceAll(" ", "");

		for (indiceSaisieDeplacement = 0;
			 indiceSaisieDeplacement < saisieDeplacement.length()
			 && !conditionArret;
			 indiceSaisieDeplacement++) {
			
//				System.out.println(saisieDeplacement.toLowerCase().charAt(indiceSaisieDeplacement)
//						+ " " + indiceSaisieDeplacement);
			switch (saisieDeplacement.toLowerCase().charAt(indiceSaisieDeplacement)) {
			case HAUT:
				if (!verificationDeplacement(-this.nombreDeColonne)) {
					conditionArret = true;					
				}
				break;
				
			case BAS:
				if (!verificationDeplacement(this.nombreDeColonne)) {
					conditionArret = true;					
				}
				break;
				
			case DROITE:
				if (!verificationDeplacement(1)) {
					conditionArret = true;					
				}
				break;
				
			case GAUCHE:
				if (!verificationDeplacement(-1)) {
					conditionArret = true;					
				}
				break;
				
			case SAUVER:
			    ParametresLabyrinthe parametres;
			    SauvegardeLabyrinthe sauvegarde;
			    
			    parametres = new ParametresLabyrinthe("NOM_BOUCHON", this);
			    sauvegarde = new SauvegardeLabyrinthe(parametres);
			    sauvegarde.sauvegarderParametres();
			    break;

			default:
				saisieCorrecte = false;
				conditionArret = true;
				break;
			}
		}
		return saisieCorrecte;
	}
	
	public int getNombreDeColonne() {
		return this.nombreDeColonne;
	}
	
	public int getNombreDeLigne() {
		return this.nombreDeLigne;
	}
	
	
}
