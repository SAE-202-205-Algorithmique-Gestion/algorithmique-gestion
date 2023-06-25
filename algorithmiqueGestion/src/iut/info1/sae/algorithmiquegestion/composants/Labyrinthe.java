/*
 * Labyrinthe.java                                                   5 juin 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants;

//import java.util.Scanner;
import com.google.gson.annotations.Expose;
//import iut.info1.sae.algorithmiquegestion.sauvegardes.ChargementEtCreationSauvegarde;

/**
 * Modélisation d'un labyrinthe utilisable sur console texte à partir des
 * classes Graphe et Sommet.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class Labyrinthe {

//    /** Touche permettant d'aller en haut. */
//	@Expose(serialize = false, deserialize = false)
//    private static final char HAUT = 'z';
//
//    /** Touche permettant d'aller en bas. */
//    @Expose(serialize = false, deserialize = false)
//    private static final char BAS = 's';
//
//    /** Touche permettant d'aller à droite. */
//    @Expose(serialize = false, deserialize = false)
//    private static final char DROITE = 'd';
//
//    /** Touche permettant d'aller à gauche. */
//    @Expose(serialize = false, deserialize = false)
//    private static final char GAUCHE = 'q';
//
//    /**
//     * Touche permettant de déclencher le processus de sauvegarde du labyrinthe.
//     */
//    @Expose(serialize = false, deserialize = false)
//    private static final char SAUVER = 'h';
//
//    /** Symbole correspondant à la position du joueur. */
//    @Expose(serialize = false, deserialize = false)
//    private static final char SOMMET_ACTUEL_SYMBOLE = 'X';
//
//    /** Symbole correspondant à la position du l'entrée. */
//    @Expose(serialize = false, deserialize = false)
//    private static final char ENTREE_SYMBOLE = 'E';
//
//    /** Symbole correspondant à la position de la sortie. */
//    @Expose(serialize = false, deserialize = false)
//    private static final char SORTIE_SYMBOLE = 'S';
//
//    /** Entrée récupérant les commandes du joueur. */
//    @Expose(serialize = false, deserialize = false)
//    private static Scanner entreeDeplacement;

    /** Sommet où se situe le joueur (marqué par X). */
    private Sommet positionActuelle;

    /** Sommet où se situe l'entrée du labyrinthe (marqué par E). */
    private Sommet entree;

    /** Sommet où se situe la sortie du labyrinthe (marqué par S). */
    private Sommet sortie;

    /** Nombre de colonnes du labyrinthe. */
    private int nombreDeColonne;

    /** Nombre de lignes du labyrinthe. */
    private int nombreDeLigne;

    /** Indice du sommet où se situe le joueur. */
    private int indiceSommetActuel;

    /** Nombre de cases parcourues par le joueur. */
    @Expose
    private int nombreCasesParcourues;

    /** Graphe servant à créer le labyrinthe. */
    @Expose
    private Graphe graphe;

    /**
     * Labyrinthe avec son nombre de lignes et de colonnes.
     *
     * @param nombreDeLignes   Le nombre de lignes (Y) du labyrinthe.
     * @param nombreDeColonnes Le nombre de colonnes (X) du labyrinthe.
     */
    public Labyrinthe(int nombreDeLignes, int nombreDeColonnes, int typeConstruction) {
        super();
        if (typeConstruction == 1) {
            this.graphe = new ChainesAscendantes(nombreDeColonnes, nombreDeLignes);

        } else if (typeConstruction == 2) {
            this.graphe = new ConstructionBacktracking(nombreDeColonnes, nombreDeLignes);

        } else {
            throw new IllegalArgumentException("Vous avez donné un type de construction de labyrinthe invalide");
        }

        this.nombreDeLigne = nombreDeLignes;
        this.nombreDeColonne = nombreDeColonnes;

//        entreeDeplacement = new Scanner(System.in);
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
    public int getIndiceSommetActuel() {
        return indiceSommetActuel;
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

//    /** @return Le symbole d'entrée de this. */
//    public char getEntreeSymbole() {
//        return ENTREE_SYMBOLE;
//    }
//
//    /** @return Le symbole de sortie de this. */
//    public char getSortieSymbole() {
//        return SORTIE_SYMBOLE;
//    }
//
//    /** @return Le symbole du sommet actuel de this. */
//    public char getSommetActuelSymbole() {
//        return SOMMET_ACTUEL_SYMBOLE;
//    }

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
    public void setIndiceSommetActuel(int indiceSommetActuelle) {
        this.indiceSommetActuel = indiceSommetActuelle;
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
     * Définition de l'entrée du labyrinthe en prenant le premier sommet ayant une
     * seule liaison.
     */
    public void definirEntree() {
        boolean entreeTrouvee = false;
        for (int i = 0; i < graphe.getListeSommets().length && !entreeTrouvee; i++) {
            if (graphe.getListeSommets()[i].getLiaisons().size() == 1) {
                this.setEntree(graphe.getListeSommets()[i]);
                this.indiceSommetActuel = i;
                entreeTrouvee = true;
            }
        }
    }

    /**
     * Définition de la sortie du labyrinthe en prenant le dernier sommet ayant une
     * seule liaison.
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

//    /**
//     * Vérification du déplacement que le joueur essaie d'effectuer.
//     *
//     * @param indiceSommetDeplacement Indice du sommet sur lequel le joueur s'est
//     *                                déplacé.
//     */
//    public boolean verificationDeplacement(int indiceSommetDeplacement) {
//
//        if (graphe.sommetExiste(indiceSommetActuel + indiceSommetDeplacement)
//                && graphe.getListeSommets()[indiceSommetActuel]
//                        .liaisonExiste(graphe.getListeSommets()[indiceSommetActuel + indiceSommetDeplacement])) {
//            this.setIndiceSommetActuel(indiceSommetActuel + indiceSommetDeplacement);
//            this.setPositionActuelle(graphe.getListeSommets()[indiceSommetActuel]);
//            this.setNombreCasesParcourues(this.getNombreCasesParcourues() + 1);
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Gestion de la saisie sur console texte de l'utilisateur lorsqu'il se déplace
//     * sur le labyrinthe.
//     */
//    public boolean demandeDeplacement() {
//
//        boolean saisieCorrecte = true;
//
//        int indiceSaisieDeplacement;
//
//        String saisieDeplacement = entreeDeplacement.next() + entreeDeplacement.nextLine();
//
//        saisieDeplacement = saisieDeplacement.replaceAll(" ", "");
//
//        for (indiceSaisieDeplacement = 0; indiceSaisieDeplacement < saisieDeplacement.length()
//                && saisieCorrecte; indiceSaisieDeplacement++) {
//
//            switch (saisieDeplacement.toLowerCase().charAt(indiceSaisieDeplacement)) {
//            case HAUT:
//                if (!verificationDeplacement(-this.nombreDeColonne)) {
//                    saisieCorrecte = false;
//                }
//                break;
//
//            case BAS:
//                if (!verificationDeplacement(this.nombreDeColonne)) {
//                    saisieCorrecte = false;
//                }
//                break;
//
//            case DROITE:
//                if (!verificationDeplacement(1)) {
//                    saisieCorrecte = false;
//                }
//                break;
//
//            case GAUCHE:
//                if (!verificationDeplacement(-1)) {
//                    saisieCorrecte = false;
//                }
//                break;
//
//            case SAUVER:
////                ParametresLabyrinthe parametres;
////                SauvegardeLabyrinthe sauvegarde;
////
////                parametres = new ParametresLabyrinthe("NOM_BOUCHON", this);
////                sauvegarde = new SauvegardeLabyrinthe(parametres);
////                sauvegarde.sauvegarderParametres();
//            	ChargementEtCreationSauvegarde.creerUneSauvegarde();
//                break;
//
//            default:
//                saisieCorrecte = false;
//                break;
//            }
//        }
//        return saisieCorrecte;
//    }

}
