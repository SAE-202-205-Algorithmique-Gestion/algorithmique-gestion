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
	 * Touche permettant de déclencher le processus de 
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
	
	private int nombreCasesParcourues;
	
	private ConstructionBacktracking graphe;
	
	/**
	 * Labyrinthe avec son nombre de lignes et de colonnes.
	 *
	 * @param nombreDeLignes Le nombre de lignes (Y) du labyrinthe.
	 * @param nombreDeColonnes Le nombre de colonnes (X) du labyrinthe.
	 */
	public Labyrinthe(int nombreDeLignes, int nombreDeColonnes, String typeConstruction) {
		super();
		if (!typeConstruction.equals("ConstructionBacktracking")) {
			throw new IllegalArgumentException("Vous avez donné un type de"
										+ " construction de labyrinthe invalide");
		}
		
		this.nombreDeLigne = nombreDeLignes;
		
		this.nombreDeColonne = nombreDeColonnes;
		
		/* Le nombre de colonnes et de lignes sont inversés dans l'appel, c'est normal */
		this.graphe = new ConstructionBacktracking(nombreDeColonnes, nombreDeLignes);
		this.entreeDeplacement = new Scanner(System.in);
		this.definirEntree();
		this.definirSortie();
		this.positionActuelle = this.getEntree();
	}
	
	/** @return Le sommet contenant l'entrée de this. */
	public Sommet getEntree() {
		return entree;
	}

    /** @return Le sommet contenant la sortie de this. */
	public Sommet getSortie() {
		return sortie;
	}
	
	/** @return L'indice du sommet sur lequel est placé le joueur. */
	public int getIndiceSommetActuelle() {
		return indiceSommetActuelle;
	}
	
	/** @return Le nombre de cases que le joueur à parcouru. */
	public int getNombreCasesParcourues() {
		return nombreCasesParcourues;
	}
	
	/** @return Le sommet sur lequel est placé le joueur. */
	public Sommet getPositionActuelle() {
		return positionActuelle;
	}
	
	/** @return Le graphe de this. */
	public Graphe getGraphe() {
		return graphe;
	}
	
	/** @return Le symbole d'entrée de this. */
	public char getEntreeSymbole() {
		return ENTREE_SYMBOLE;
	}

    /** @return Le symbole de sortie de this. */
	public char getSortieSymbole() {
		return SORTIE_SYMBOLE;
	}
	
	/** @return Le symbole du sommet actuel de this. */
	public char getSommetActuelleSymbole() {
		return SOMMET_ACTUEL_SYMBOLE;
	}
	
	/** @return Le nombre de colonnes du labyrinthe. */
    public int getNombreDeColonne() {
        return this.nombreDeColonne;
    }
    
    /** @return Le nombre de lignes du labyrinthe. */
    public int getNombreDeLigne() {
        return this.nombreDeLigne;
    }
    
    /** @param entree Le sommet correspondant à l'entrée du labyrinthe. */
    public void setEntree(Sommet entree) {
        this.entree = entree;
    }
    
    /** @param sortie Le sommet correspondant à la sortie du labyrinthe. */
    public void setSortie(Sommet sortie) {
        this.sortie = sortie;
    }
    
    /** @param indiceSommetActuelle L'indice du sommet à set. */
    public void setIndiceSommetActuelle(int indiceSommetActuelle) {
        this.indiceSommetActuelle = indiceSommetActuelle;
    }
    
	/** @param nombreCasesParcourues Le nombre de cases que le joueur à parcouru. */
	public void setNombreCasesParcourues(int nombreCasesParcourues) {
		this.nombreCasesParcourues = nombreCasesParcourues;
	}
    
    /** @param positionActuelle Le sommet correspondant à la position actuelle. */
    public void setPositionActuelle(Sommet positionActuelle) {
        this.positionActuelle = positionActuelle;
    }

    /**
     * Définition de l'entrée du labyrinthe en prenant le premier
     * sommet ayant une seule liaison.
     */
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
	
	/**
     * Définition de la sortie du labyrinthe en prenant le dernier
     * sommet ayant une seule liaison.
     */
	public void definirSortie() {
		boolean sortieTrouvee = false;
		for (int i = graphe.getListeSommets().length - 1; i >= 0 && !sortieTrouvee; i--) {
			if (graphe.getListeSommets()[i].getLiaisons().size() == 1) {
				this.setSortie(graphe.getListeSommets()[i]);
				sortieTrouvee = true;
			}
		}
	}
	
	/**
	 * Vérification du déplacement que le joueur essaie d'effectuer.
	 *
	 * @param indiceSommetDeplacement Indice du sommet sur lequel le joueur
	 *                                s'est déplacé.
	 */
	public boolean verificationDeplacement(int indiceSommetDeplacement) {
		
		if (graphe.sommetExiste(indiceSommetActuelle + indiceSommetDeplacement)
			&& graphe.getListeSommets()[indiceSommetActuelle]
				.liaisonExiste
				(graphe.getListeSommets()[indiceSommetActuelle +
				                          indiceSommetDeplacement])) {
			this.setIndiceSommetActuelle(indiceSommetActuelle + indiceSommetDeplacement);
			this.setPositionActuelle(graphe.getListeSommets()[indiceSommetActuelle]);
			this.setNombreCasesParcourues(this.getNombreCasesParcourues() + 1);
			return true;
		}
		return false;
	}
	
	/**
	 * Gestion de la saisie sur console texte de l'utilisateur lorsqu'il
	 * se déplace sur le labyrinthe.
	 */
	public boolean demandeDeplacement() {
		
		boolean saisieCorrecte = true;
		
		int indiceSaisieDeplacement;
		
		String saisieDeplacement = this.entreeDeplacement.next()
								   + this.entreeDeplacement.nextLine();
		
		saisieDeplacement = saisieDeplacement.replaceAll(" ", "");

		for (indiceSaisieDeplacement = 0;
			 indiceSaisieDeplacement < saisieDeplacement.length()
			 && saisieCorrecte;
			 indiceSaisieDeplacement++) {
			
			switch (saisieDeplacement.toLowerCase().charAt(indiceSaisieDeplacement)) {
			case HAUT:
				if (!verificationDeplacement(-this.nombreDeColonne)) {
					saisieCorrecte = false;
				}
				break;
				
			case BAS:
				if (!verificationDeplacement(this.nombreDeColonne)) {
					saisieCorrecte = false;
				}
				break;
				
			case DROITE:
				if (!verificationDeplacement(1)) {
					saisieCorrecte = false;
				}
				break;
				
			case GAUCHE:
				if (!verificationDeplacement(-1)) {
					saisieCorrecte = false;
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
				break;
			}
		}
		return saisieCorrecte;
	}
	
}
