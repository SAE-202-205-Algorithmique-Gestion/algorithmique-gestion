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
	
	private Graphe graphe;
	
	public Labyrinthe(int nombreDeLigne, int nombreDeColonne) {

		super();
		
		this.nombreDeLigne = nombreDeLigne;
		
		this.nombreDeColonne = nombreDeColonne;
		
		/* C'est normal que le nombre de colonnes et de lignes soit inversées dans l'appel*/
		this.graphe = new Graphe(nombreDeColonne, nombreDeLigne);
		this.entreeDeplacement = new Scanner(System.in);
		this.definirEntree();
		System.out.println("Entrée : " + this.getEntree().getCoordonneeX()
				+ " ; " + this.getEntree().getCoordonneeY());
		this.definirSortie();
		System.out.println("Sortie : " + this.getSortie().getCoordonneeX()
				+ " ; " + this.getSortie().getCoordonneeY());
		
		this.positionActuelle = this.getEntree();
		System.out.println("Position actuelle : " + this.positionActuelle);
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
	
	public Sommet getPositionActuelle() {
		return positionActuelle;
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
	
	public boolean gestionErreurSaisie(String entreeScanner) {
		if (entreeScanner.length() != 1
			|| entreeScanner.toLowerCase().charAt(0) != this.HAUT
			|| entreeScanner.toLowerCase().charAt(0) != this.BAS
			|| entreeScanner.toLowerCase().charAt(0) != this.DROITE
			|| entreeScanner.toLowerCase().charAt(0) != this.GAUCHE) {
			
			System.out.println(MESSAGE_ERREUR_SAISIE);
			return false;
		}
		return true;
	}
	
	public void demandeDeplacement() {
		boolean saisieBonne = false;
		while (!saisieBonne) {
			System.out.println("\nCommande : ");
			String saisieDeplacement = this.entreeDeplacement.nextLine();
//			System.out.println(saisieDeplacement);
			switch (saisieDeplacement.toLowerCase().charAt(0)) {
			case 'z':
				for (Sommet liaisons : this.getPositionActuelle().getLiaisons()) {
					if (liaisons.getCoordonneeY()
						== this.getPositionActuelle().getCoordonneeY() - 1) {
						
						this.positionActuelle = liaisons;
					} else {
						System.out.println(MESSAGE_ERREUR_SAISIE);
					}
				}
				saisieBonne = true;
				break;
				
			case 's':
				for (Sommet liaisons : this.getPositionActuelle().getLiaisons()) {
					if (liaisons.getCoordonneeY()
						== this.getPositionActuelle().getCoordonneeY() + 1) {
						
						this.positionActuelle = liaisons;
					} else {
						System.out.println(MESSAGE_ERREUR_SAISIE);
					}
				}
				saisieBonne = true;
				break;
				
			case 'd':
				for (Sommet liaisons : this.getPositionActuelle().getLiaisons()) {
					if (liaisons.getCoordonneeX()
						== this.getPositionActuelle().getCoordonneeX() + 1) {
						
						this.positionActuelle = liaisons;
					} else {
						System.out.println(MESSAGE_ERREUR_SAISIE);
					}
				}
				saisieBonne = true;
				break;
				
			case 'q':
				for (Sommet liaisons : this.getPositionActuelle().getLiaisons()) {
					if (liaisons.getCoordonneeX()
						== this.getPositionActuelle().getCoordonneeX() - 1) {
						
						this.positionActuelle = liaisons;
					} else {
						System.out.println(MESSAGE_ERREUR_SAISIE);
					}
				}
				saisieBonne = true;
				break;
				
			default:
				System.out.println(MESSAGE_ERREUR_SAISIE);
				break;
			}
		}
	}
}
