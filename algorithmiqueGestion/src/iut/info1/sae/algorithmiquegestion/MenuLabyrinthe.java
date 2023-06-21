/**
 * MenuLabyrinthe.java                                               7 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion;

import java.util.Scanner;

import iut.info1.sae.algorithmiquegestion.affichage.AffichageLabyrinthe;
import iut.info1.sae.algorithmiquegestion.affichage.AffichageCaseCourante;
import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;

/**
 * Utilisation du jeu de labyrinthe faisant appel aux classes d'affichages, de
 * création et de parcours de labyrinthe.
 *
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class MenuLabyrinthe {

    private final static String DEMANDE_COMMANDE = "\n\nEntrez votre/vos commande(s) : ";

    public final static String COMMANDES_MENU_PRINCIPAL
    = "Commandes disponibles :"
      + "\n- 1 : Construction d'un labyrinthe"
      + "\n- 2 : Non implémenté : ouverture d'un labyrinthe sauvegardé"
      + "\n____________________________________________" + DEMANDE_COMMANDE;

    public final static String BIENVENUE = "Bienvenue sur ce jeu de Labyrinthe !";

    private static final int HAUTEUR_MINIMALE_LABYRINTHE = 2;

    private static final int LONGUEUR_MINIMALE_LABYRINTHE = 2;

    private static final int HAUTEUR_MAXIMALE_LABYRINTHE = 10;

    private static final int LONGUEUR_MAXIMALE_LABYRINTHE = 10;

    private static int hauteurLabyrinthe;

    private static int longueurLabyrinthe;

    private static int typeLabyrinthe;

    private static int typeAffichage;

    private static Labyrinthe labyrinthe;
    
    /** @return Attribut labyrinthe de this. */
    public static Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }

    /**
     * Affichage du message de bienvenue et appel de la méthode de demande des
     * paramètres de création et de parcours du labyrinthe.
     *
     * @param args non utilisé
     */
    public static void main(String[] args) {
        final String QUITTER_PROGRAMME =
        """
        \n\nVoulez-vous quitter ce programme ?
        - Entrez 'Q' pour quitter
        - Entrez 'R' ou autre chose pour rester\n
        """;
        
        boolean menuPrincipalPasse = false;
        boolean quitterProgramme = false;
        
        Scanner analyseurEntree = new Scanner(System.in);

        System.out.println(BIENVENUE);

        do {
            do {
                menuPrincipalPasse = demandeParametresLabyrinthe();
            } while (!menuPrincipalPasse);
            
            System.out.println(QUITTER_PROGRAMME);
            
            switch (analyseurEntree.next().toLowerCase()) {
            case "q":
                quitterProgramme = true;
                break;
            default:
                break;
            }
        } while (!quitterProgramme);
        
        analyseurEntree.close();
    }

    /**
     * Affichage console texte demandant en boucle les informations du labyrinthe
     * souhaité par l'utilisateur (hauteur, longueur) jusqu'à avoir des données
     * valides.
     * 
     * @return true si les paramètres sont valides.
     */
    private static boolean demandeParametresLabyrinthe() {

        final String CHOIX_NOUVEAU_LABYRINTHE = "1";

        final String NOUVEAU_LABYRINTHE = "\n>> NOUVEAU LABYRINTHE";

        final String COMMANDE_INEXISTANTE = "\nErreur : commande inexistante !\n";

        Scanner analyseurSaisie;

        String saisieMenuPrincipal;

        boolean saisieLongueurLabyrintheTermine = false;
        boolean saisieHauteurLabyrintheTermine = false;

        boolean saisieTypeLabyrintheTermine = false;
        boolean saisieTypeAffichageTerminee = false;

        boolean resultatValide = false;

        System.out.println(COMMANDES_MENU_PRINCIPAL);

        analyseurSaisie = new Scanner(System.in);

        saisieMenuPrincipal = analyseurSaisie.next();
        analyseurSaisie.nextLine();

        switch (saisieMenuPrincipal) {
        case CHOIX_NOUVEAU_LABYRINTHE:
            System.out.println(NOUVEAU_LABYRINTHE);

            do {
                saisieLongueurLabyrintheTermine = saisirLongueurLabyrinthe(analyseurSaisie);
            } while (!saisieLongueurLabyrintheTermine);

            do {
                saisieHauteurLabyrintheTermine = saisirHauteurLabyrinthe(analyseurSaisie);
            } while (!saisieHauteurLabyrintheTermine);

            do {
                saisieTypeLabyrintheTermine = saisirTypeConstructionLabyrinthe(analyseurSaisie);
            } while (!saisieTypeLabyrintheTermine);

            do {
                saisieTypeAffichageTerminee = saisirTypeAffichage(analyseurSaisie);
            } while (!saisieTypeAffichageTerminee);

            System.out.println("\n");
            
            try {
                labyrinthe = new Labyrinthe(hauteurLabyrinthe,
                                            longueurLabyrinthe,
                                            typeLabyrinthe);
            } catch (IllegalArgumentException e) {
                System.out.println(COMMANDE_INEXISTANTE);
            }
            
            System.out.println("nb lignes et colonne et co sortie " + labyrinthe.getNombreDeColonne() + " "
            		 		   + labyrinthe.getNombreDeLigne() + " " + labyrinthe.getSortie());

            resultatValide = true;

            if (typeAffichage == 1) {
                AffichageLabyrinthe.lancement();
            } else {
                AffichageCaseCourante.lancement();
            }
            break;

        default:
            System.out.println(COMMANDE_INEXISTANTE);
            break;
        }

        return resultatValide;
    }

    /**
     * Demande console texte de la longueur du labyrinthe à créer.
     *
     * @param analyseurEntree un scanneur qui regarde l'entrée utilisateurs
     * @return true si la longueur saisie est correcte.
     */
    private static boolean saisirLongueurLabyrinthe(Scanner analyseurEntree) {

        final String ENTRER_LONGUEUR
        = "\nEntrez la longueur (horizontale) du labyrinthe : ";

        final String LONGUEUR_INVALIDE = "\nErreur : longueur invalide.";

        boolean entierEntre = true;
        boolean longueurValide = true;

        System.out.print(ENTRER_LONGUEUR);

        if (analyseurEntree.hasNextInt()) {
            longueurLabyrinthe = analyseurEntree.nextInt();
        } else {
            entierEntre = false;
        }
        analyseurEntree.nextLine();

        System.out.println();

        longueurValide = entierEntre
                         && longueurLabyrinthe >= LONGUEUR_MINIMALE_LABYRINTHE
                         && longueurLabyrinthe <= LONGUEUR_MAXIMALE_LABYRINTHE;

        if (!longueurValide) {
            System.out.println(LONGUEUR_INVALIDE);
        }
        return longueurValide;
    }

    /**
     * Demande console texte de la hauteur du labyrinthe à créer.
     *
     * @param analyseurEntree un scanneur qui regarde l'entrée utilisateurs
     * @return true si la hauteur saisie est correcte.
     */
    private static boolean saisirHauteurLabyrinthe(Scanner analyseurEntree) {

        final String ENTRER_HAUTEUR = "Entrez la hauteur (verticale) du labyrinthe : ";

        final String HAUTEUR_INVALIDE = "\nErreur : hauteur invalide.";

        boolean entierEntre = true;
        boolean hauteurValide = true;

        System.out.print(ENTRER_HAUTEUR);

        if (analyseurEntree.hasNextInt()) {
            hauteurLabyrinthe = analyseurEntree.nextInt();
        } else {
            entierEntre = false;
        }
        analyseurEntree.nextLine();

        hauteurValide = entierEntre
                        && hauteurLabyrinthe >= HAUTEUR_MINIMALE_LABYRINTHE
                        && hauteurLabyrinthe <= HAUTEUR_MAXIMALE_LABYRINTHE;

        if (!hauteurValide) {
            System.out.println(HAUTEUR_INVALIDE);
        }
        return hauteurValide;
    }

    /**
     * Demande console texte du type de construction du labyrinthe.
     *
     * @param analyseurEntree un scanneur qui regarde l'entrée utilisateurs
     * @return true si le type saisi est correct.
     */
    private static boolean saisirTypeConstructionLabyrinthe(Scanner analyseurEntree) {

        final String ENTRER_TYPE = """
                \nEntrez la type de construction du labyrinthe :
                 - 1 : Construction par chaînes ascendantes
                 - 2 : Construction par descente en profondeur
                """;

        final String TYPE_INVALIDE = "\nErreur : type invalide.";

        boolean typeValide = true;

        System.out.print(ENTRER_TYPE);

        if (analyseurEntree.hasNextInt()) {
            typeLabyrinthe = analyseurEntree.nextInt();
            if (typeLabyrinthe != 1 && typeLabyrinthe != 2) {
                typeValide = false;
            }
        } else {
            typeValide = false;
        }
        analyseurEntree.nextLine();

        if (!typeValide) {
            System.out.println(TYPE_INVALIDE);
        }
        return typeValide;
    }

    /**
     * Demande console texte du type d'affichage sur console du labyrinthe.
     *
     * @param analyseurEntree un scanneur qui regarde l'entrée utilisateurs
     * @return true si le type saisi est correct.
     */
    private static boolean saisirTypeAffichage(Scanner analyseurEntree) {

        final String ENTRER_TYPE = """
                \nEntrez le type d'affichage console texte du labyrinthe :
                 - 1 : Affichage complet du labyrinthe
                 - 2 : Affichage de la case courante seulement
                """;

        final String TYPE_INVALIDE = "\nErreur : type invalide.";

        boolean typeValide = true;

        System.out.print(ENTRER_TYPE);

        if (analyseurEntree.hasNextInt()) {
            typeAffichage = analyseurEntree.nextInt();
            if (typeAffichage != 1 && typeAffichage != 2) {
                typeValide = false;
            }
        } else {
            typeValide = false;
        }
        analyseurEntree.nextLine();

        if (!typeValide) {
            System.out.println(TYPE_INVALIDE);
        }
        return typeValide;
    }
}
