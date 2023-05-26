package iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe;

import iut.info1.sae.algorithmiquegestion.composants.Graphe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * classe labyrinthe
 * @author Samuel
 * 
 */
public class Labyrinthe {
	
	private final char HAUT = 'z';
	
	private final char BAS = 's';
	
	private final char DROITE = 'd';
	
	private final char GAUCHE = 'q';
	
	private final char SOMMET_ACTUEL = 'X';
	
	private final char ENTREE = 'E';
	
	private final char SORTIE = 'S';
	
	private Sommet entree;

	private Sommet sortie;
	
	private final String MESSAGE_GAGNER = "\nBravo, vous avez réussi !";
	
	private int nombreDeColonne;

	private int nombreDeLigne;
	
	private Graphe graphe;
	
	public Labyrinthe(int nombreDeLigne, int nombreDeColonne) {
		super();
		
		this.nombreDeLigne = nombreDeLigne;
		
		this.nombreDeColonne = nombreDeColonne;
		
		/* C'est normal que le nb de colonnes et de lignes soit inversées dans l'appel*/
		Graphe graphe = new Graphe(nombreDeColonne, nombreDeLigne);
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
	
	public Graphe getGraphe() {
		return graphe;
	}
	
	public void definirEntree() {
		boolean entreeTrouvee = false;
		for (int i = 0; i < graphe.getListeSommets().length || entreeTrouvee == true; i++) {
			if (graphe.getListeSommets()[i].getLiaisons().size() == 1) {
				this.setEntree(graphe.getListeSommets()[i]);
				entreeTrouvee = true;
			}
		}
	}
	
	public void definirSortie() {
		boolean sortieTrouvee = false;
		for (int i = graphe.getListeSommets().length; i >= 0 || sortieTrouvee == true; i--) {
			if (graphe.getListeSommets()[i].getLiaisons().size() == 1) {
				this.setSortie(graphe.getListeSommets()[i]);
				sortieTrouvee = true;
			}
		}
	}
	
	
}
