package iut.info1.sae.algorithmiquegestion.composants;

import java.util.Scanner;

import iut.info1.sae.algorithmiquegestion.MenuLabyrinthe;
import iut.info1.sae.algorithmiquegestion.sauvegardes.ChargementEtCreationSauvegarde;
import iut.info1.sae.algorithmiquegestion.sauvegardes.LectureNomsFichier;

public class JeuxLabyrinthe {

	/** Référence du labyrinthe */
	private static Labyrinthe labyrinthe;
	
	/** Référence des sommet du labyrinthe */
	private static Sommet[] listeSommets;
	
	/** Touche permettant d'aller en haut. */
    private static final char HAUT = 'z';

    /** Touche permettant d'aller en bas. */
    private static final char BAS = 's';

    /** Touche permettant d'aller à droite. */
    private static final char DROITE = 'd';

    /** Touche permettant d'aller à gauche. */
    private static final char GAUCHE = 'q';

    /**
     * Touche permettant de déclencher le processus de sauvegarde du labyrinthe.
     */
    private static final char SAUVER = 'h';

    /** Symbole correspondant à la position du joueur. */
    private static final char SOMMET_ACTUEL_SYMBOLE = 'X';

    /** Symbole correspondant à la position du l'entrée. */
    private static final char ENTREE_SYMBOLE = 'E';

    /** Symbole correspondant à la position de la sortie. */
    private static final char SORTIE_SYMBOLE = 'S';

    /** Entrée récupérant les commandes du joueur. */
    private static Scanner entreeDeplacementEtSauvegarde = new Scanner(System.in);
    
    /** Nom
    
    /** @return Le symbole d'entrée de labyrinthe. */
    public static char getEntreeSymbole() {
        return ENTREE_SYMBOLE;
    }

    /** @return Le symbole de sortie de labyrinthe. */
    public static char getSortieSymbole() {
        return SORTIE_SYMBOLE;
    }

    /** @return Le symbole du sommet actuel de labyrinthe. */
    public static char getSommetActuelSymbole() {
        return SOMMET_ACTUEL_SYMBOLE;
    }
    
    private static void initialisation() {
    	labyrinthe = MenuLabyrinthe.getLabyrinthe();
    	listeSommets = labyrinthe.getGraphe().getListeSommets();
    }
    
    /**
     * Vérification du déplacement que le joueur essaie d'effectuer.
     *
     * @param indiceSommetDeplacement Indice du sommet sur lequel le joueur s'est
     *                                déplacé.
     */
    public static boolean verificationDeplacement(int indiceSommetDeplacement) {

        if (labyrinthe.getGraphe().sommetExiste(labyrinthe.getIndiceSommetActuel() + indiceSommetDeplacement)
                && listeSommets[labyrinthe.getIndiceSommetActuel()]
                        .liaisonExiste(listeSommets[labyrinthe.getIndiceSommetActuel() + indiceSommetDeplacement])) {
            labyrinthe.setIndiceSommetActuel(labyrinthe.getIndiceSommetActuel() + indiceSommetDeplacement);
            labyrinthe.setPositionActuelle(listeSommets[labyrinthe.getIndiceSommetActuel()]);
            labyrinthe.setNombreCasesParcourues(labyrinthe.getNombreCasesParcourues() + 1);
            return true;
        }
        return false;
    }

    /**
     * Gestion de la saisie sur console texte de l'utilisateur lorsqu'il se déplace
     * sur le labyrinthe.
     */
    public static boolean demandeDeplacement() {
    	initialisation();
        boolean saisieCorrecte = true;
        boolean saisieSauvegardeCorrecte = false;
        int indiceSaisieDeplacement;
        String saisieSauvegarde;
        
        String saisieDeplacement = entreeDeplacementEtSauvegarde.next()
        		+ entreeDeplacementEtSauvegarde.nextLine();

        saisieDeplacement = saisieDeplacement.replaceAll(" ", "");

        for (indiceSaisieDeplacement = 0; indiceSaisieDeplacement < saisieDeplacement.length()
                && saisieCorrecte; indiceSaisieDeplacement++) {

            switch (saisieDeplacement.toLowerCase().charAt(indiceSaisieDeplacement)) {
            case HAUT:
                if (!verificationDeplacement(-labyrinthe.getNombreDeColonne())) {
                    saisieCorrecte = false;
                }
                break;

            case BAS:
                if (!verificationDeplacement(labyrinthe.getNombreDeColonne())) {
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
//                ParametresLabyrinthe parametres;
//                SauvegardeLabyrinthe sauvegarde;
//
//                parametres = new ParametresLabyrinthe("NOM_BOUCHON", labyrinthe);
//                sauvegarde = new SauvegardeLabyrinthe(parametres);
//                sauvegarde.sauvegarderParametres();
            	
            	System.out.println("Entré un nom de sauvegarde qui n'est pas déjà utilisé"
            				+ "\nVoici la liste des sauvegarde :\n");
            	LectureNomsFichier.listeNomsFichiersConsole();
            	
            	do {
            		System.out.println("\nEntrez le nom de la sauvegarde :");
	            	saisieSauvegarde = entreeDeplacementEtSauvegarde.next()
	                		+ entreeDeplacementEtSauvegarde.nextLine();
	            	
	            	if (LectureNomsFichier.isNomFichierDejaExistant(saisieSauvegarde)) {
	                	System.out.println("Le Nom de la sauvegarde est déjà existante."
	                			+ "\nVeuillez en choisir une qui ne figure pas dans cette liste : \n");
	                	LectureNomsFichier.listeNomsFichiersConsole();
	                } else {
	                	saisieSauvegardeCorrecte = true;
	                }
            	} while (!saisieSauvegardeCorrecte);
            	
//            	saisieSauvegarde = DeuxChargementEtCreationSauvegarde2.ajoutExtensionNomSauvegarde(saisieSauvegarde);
            	ChargementEtCreationSauvegarde.creerUneSauvegarde(saisieSauvegarde);
                break;

            default:
                saisieCorrecte = false;
                break;
            }
        }
        return saisieCorrecte;
    }
}
