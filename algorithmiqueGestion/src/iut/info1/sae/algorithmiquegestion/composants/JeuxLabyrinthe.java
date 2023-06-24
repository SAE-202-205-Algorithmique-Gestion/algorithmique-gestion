package iut.info1.sae.algorithmiquegestion.composants;

import java.util.Scanner;

import com.google.gson.annotations.Expose;

import iut.info1.sae.algorithmiquegestion.MenuLabyrinthe;
import iut.info1.sae.algorithmiquegestion.sauvegardes.ChargementEtCreationSauvegarde;

public class JeuxLabyrinthe {

	/** Référence du labyrinthe */
	private static Labyrinthe labyrinthe;
	
	private static Sommet[] listeSommets;
	
	/** Touche permettant d'aller en haut. */
	@Expose(serialize = false, deserialize = false)
    private static final char HAUT = 'z';

    /** Touche permettant d'aller en bas. */
    @Expose(serialize = false, deserialize = false)
    private static final char BAS = 's';

    /** Touche permettant d'aller à droite. */
    @Expose(serialize = false, deserialize = false)
    private static final char DROITE = 'd';

    /** Touche permettant d'aller à gauche. */
    @Expose(serialize = false, deserialize = false)
    private static final char GAUCHE = 'q';

    /**
     * Touche permettant de déclencher le processus de sauvegarde du labyrinthe.
     */
    @Expose(serialize = false, deserialize = false)
    private static final char SAUVER = 'h';

    /** Symbole correspondant à la position du joueur. */
    @Expose(serialize = false, deserialize = false)
    private static final char SOMMET_ACTUEL_SYMBOLE = 'X';

    /** Symbole correspondant à la position du l'entrée. */
    @Expose(serialize = false, deserialize = false)
    private static final char ENTREE_SYMBOLE = 'E';

    /** Symbole correspondant à la position de la sortie. */
    @Expose(serialize = false, deserialize = false)
    private static final char SORTIE_SYMBOLE = 'S';

    /** Entrée récupérant les commandes du joueur. */
    @Expose(serialize = false, deserialize = false)
    private static Scanner entreeDeplacement = new Scanner(System.in);
    
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

        int indiceSaisieDeplacement;
        
        String saisieDeplacement = entreeDeplacement.next() + entreeDeplacement.nextLine();

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
            	ChargementEtCreationSauvegarde.creerUneSauvegarde();
                break;

            default:
                saisieCorrecte = false;
                break;
            }
        }
        return saisieCorrecte;
    }
}
