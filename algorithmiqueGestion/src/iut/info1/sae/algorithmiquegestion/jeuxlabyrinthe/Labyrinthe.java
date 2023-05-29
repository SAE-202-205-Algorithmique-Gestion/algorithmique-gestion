/*
 * TODO : javadoc
 */
package iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe;

import java.util.Scanner;

import iut.info1.sae.algorithmiquegestion.composants.Graphe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * classe labyrinthe
 * @author Samuel
 * 
 */
public class Labyrinthe {
	private final String MESSAGE_GAGNER = "\nBravo, vous avez réussi !";
	
	private final String MESSAGE_ERREUR_SAISIE = "\nVous avez fait une erreur de saisie."
											   + "\nTapez 'z' pour allez en haut"
											   + "\nsoit 's' pour aller en bas"
											   + "\nsoit 'd' pour aller à droite"
											   + "\nsoit 'q' pour aller à gauche."
											   + "\nPour se déplacer sur un sommet adjacent,"
											   + " il doit y avoir une liaison.\n";
	
	private final char HAUT = 'z';
	
	private final char BAS = 's';
	
	private final char DROITE = 'd';
	
	private final char GAUCHE = 'q';
	
	private final char SOMMET_ACTUEL_SYMBOLE = 'X';
	
	private final char ENTREE_SYMBOLE = 'E';
	
	private final char SORTIE_SYMBOLE = 'S';
	
	private Scanner entreeDeplacement;
	
	private Sommet positionActuelle;
	
	private Sommet entree;

	private Sommet sortie;
	
	private int nombreDeColonne;

	private int nombreDeLigne;
	
	private int indiceSommetActuelle;
	
	private Graphe graphe;
	
	public Labyrinthe(int nombreDeLigne, int nombreDeColonne) {

		super();
		
		this.nombreDeLigne = nombreDeLigne;
		
		this.nombreDeColonne = nombreDeColonne;
		
		/* C'est normal que le nombre de colonnes et de lignes soit inversées dans l'appel*/
		this.graphe = new Graphe(nombreDeColonne, nombreDeLigne);
		this.entreeDeplacement = new Scanner(System.in);
		this.definirEntree();
		this.definirSortie();
//		this.positionActuelle = this.getEntree();
	}
	
	public String getMessageGagner() {
		return MESSAGE_GAGNER;
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
		} else {
			System.out.println(MESSAGE_ERREUR_SAISIE);
			return false;
		}
	}
	
	public void demandeDeplacement() {
		boolean saisieBonne = true;
		int indiceSaisieDeplacement;
		
		System.out.println("\nCommande : ");
		String saisieDeplacement = this.entreeDeplacement.nextLine();
		
		for (indiceSaisieDeplacement = 0;
			 indiceSaisieDeplacement < saisieDeplacement.length()
			 && saisieBonne;
			 indiceSaisieDeplacement++) {
			
//				System.out.println(saisieDeplacement.toLowerCase().charAt(indiceSaisieDeplacement)
//						+ " " + indiceSaisieDeplacement);
			switch (saisieDeplacement.toLowerCase().charAt(indiceSaisieDeplacement)) {
			case 'z':
				if (!verificationDeplacement(-this.nombreDeColonne))
					saisieBonne = false;
				break;
				
			case 's':
				if (!verificationDeplacement(this.nombreDeColonne))
					saisieBonne = false;
				break;
				
			case 'd':
				if (!verificationDeplacement(1))
					saisieBonne = false;
				break;
				
			case 'q':
				if (!verificationDeplacement(-1))
					saisieBonne = false;
				break;
				
			case ' ':
				break;
			default:
				saisieBonne = false;
				System.out.println(MESSAGE_ERREUR_SAISIE);
				break;
			}
		}
	}
}
