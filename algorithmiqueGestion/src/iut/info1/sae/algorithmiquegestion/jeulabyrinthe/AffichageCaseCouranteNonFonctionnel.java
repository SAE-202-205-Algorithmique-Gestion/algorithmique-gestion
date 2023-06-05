/*
 * AffichageCaseCourante.java                                        6 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.jeulabyrinthe;

import java.util.Scanner;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * Affichage sur console texte de la case courante d'un jeu de labyrinthe
 * représenté sous forme "humaine".
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class AffichageCaseCouranteNonFonctionnel {
    
    public final static String MUR_VERTICAL = " | ";
    
    public final static String MUR_HORIZONTAL = "----";
    
    private static final String COIN_DE_MUR = "+";

    private static final char CASE = ' ';
    
    private final static String DEMANDE_COMMANDE
    = "\n\nEntrez votre/vos commande(s) : ";
    
    public final static String COMMANDES =
    """
    - H : déplacement vers le haut
    - B : déplacement vers le bas
    - G : déplacement vers la gauche
    - D : déplacement vers la droite
    """;
    
    public final static String COMMANDES_MENU_PRINCIPAL =
    "________ COMMANDES - MENU PRINCIPAL ________"
    + "\n\n- N : création d'un nouveau labyrinthe"
    //+ "\n- O : ouverture d'un labyrinthe sauvegardé"
    + "\n____________________________________________"
    + DEMANDE_COMMANDE;
    
    public final static String BIENVENUE
    = "Bienvenue sur ce jeu de Labyrinthe !\n";
    
    public final static String CONSIGNES_JEU =
    "______________________ COMMANDES - JEU ______________________\n" +
    """
    \nVoici la liste des commandes utilisables dans la console texte :
    """
    + COMMANDES +
    """
    \nTapez la touche 'Entrée' une fois que votre liste de commandes correspond à l'action souhaitée.
    
    Vous commencez sur la case E et devez atteindre la case S.
    Votre position courante est représentée par X lorsque vous quittez l'entrée E.
    
    Vous devez vous déplacer là où une liaison apparaît.
    """;
    
    private final static String PARTIE_GAGNEE = "\nBravo !\nVous avez gagné, la"
                                                + " partie est terminée !";
    
    private final static String PARCOURS_FIN = "\nVoici le parcours "
                                               + "de résolution : ";

    private final static String ERREUR_SAISIE =
    """
    \nVous avez entré une commande invalide ou un déplacement impossible !
    
    Voici la liste des commandes utilisables dans la console texte :
    """
    + COMMANDES;

//    private static int nombreLignes;
//    
//    private static int nombreColonnes;
    
    private static final int HAUTEUR_MINIMALE_LABYRINTHE = 0;
    
    private static final int LONGUEUR_MINIMALE_LABYRINTHE = 0;

    private static final int HAUTEUR_MAXIMALE_LABYRINTHE = 10;
    
    private static final int LONGUEUR_MAXIMALE_LABYRINTHE = 10;

    private static int hauteurLabyrinthe;
    
    private static int longueurLabyrinthe;
    
    private static Labyrinthe labyrinthe;
    
    public static Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }

    private static Sommet[] listeSommets;
    
    /**
     * Lancement de l'affichage du labyrinthe généré en fonction de
     * la largeur NOMBRE_COLONNES et la longueur NOMBRE_LIGNES.
     * 
     * @param args inutilisé
     */
    public static void main(String[] args) {
        
        boolean menuPrincipalPasse;
        
        System.out.println(BIENVENUE);
        
        do {
            menuPrincipalPasse = demandeParametresLabyrinthe();
        } while (!menuPrincipalPasse);
        
        System.out.println(CONSIGNES_JEU);
        
        do {
            gestionDeplacementsLabyrinthe();
        } while (labyrinthe.getPositionActuelle() != labyrinthe.getSortie());
        
        System.out.println(PARTIE_GAGNEE);
        System.out.print(PARCOURS_FIN);
        ParcoursProfondeur.algorithmeParcours();
    }
    
    /**
     * Affichage console texte demandant en boucle les informations du
     * labyrinthe souhaité par l'utilisateur (hauteur, longueur) jusqu'à
     * avoir des données valides.
     * 
     * @return true si les paramètres sont valides.
     */
    private static boolean demandeParametresLabyrinthe() {

        final String CHOIX_NOUVEAU_LABYRINTHE = "n";
        
        final String CHOIX_OUVRIR_SAUVEGARDE = "o";
        
        final String NOUVEAU_LABYRINTHE = "\n>> NOUVEAU LABYRINTHE";
        
        final String COMMANDE_INEXISTANTE = "\nErreur : commande inexistante !\n";
        
        Scanner analyseurSaisie;        
        
        String saisieMenuPrincipal;
        
        boolean saisieLongueurLabyrintheTermine = false;
        boolean saisieHauteurLabyrintheTermine = false;
        
        boolean resultatValide = false;
                
        System.out.println(COMMANDES_MENU_PRINCIPAL);
        
        analyseurSaisie = new Scanner(System.in);
        
        saisieMenuPrincipal = analyseurSaisie.next();
        analyseurSaisie.nextLine();
        
        switch (saisieMenuPrincipal.toLowerCase()) {
        case CHOIX_NOUVEAU_LABYRINTHE:
            System.out.println(NOUVEAU_LABYRINTHE);

            do {    
                saisieLongueurLabyrintheTermine
                = saisirLongueurLabyrinthe(analyseurSaisie);
            } while (!saisieLongueurLabyrintheTermine);
            
            do {
                saisieHauteurLabyrintheTermine
                = saisirHauteurLabyrinthe(analyseurSaisie);
            } while (!saisieHauteurLabyrintheTermine);
            
            System.out.println("\n");
            
            labyrinthe = new Labyrinthe(hauteurLabyrinthe,
                                        longueurLabyrinthe);
            
            listeSommets = labyrinthe.getGraphe().getListeSommets();
            
            resultatValide = true;
            break;
            
        case CHOIX_OUVRIR_SAUVEGARDE:
            // TODO : Ouvrir le labyrinthe sauvegardé
            break;
            
        default:
            System.out.println(COMMANDE_INEXISTANTE);
            break;
        }
        
        return resultatValide;
    }
    
    /**
     * Vérification de la validité de la longueur saisie.
     *
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
                         && longueurLabyrinthe > LONGUEUR_MINIMALE_LABYRINTHE
                         && longueurLabyrinthe <= LONGUEUR_MAXIMALE_LABYRINTHE;
        
        if (!longueurValide) {
            System.out.println(LONGUEUR_INVALIDE);
        }
        return longueurValide;
    }
    
    /**
     * Vérification de la validité de la hauteur saisie.
     *
     * @return true si la hauteur saisie est correcte.
     */
    private static boolean saisirHauteurLabyrinthe(Scanner analyseurEntree) {
        
        final String ENTRER_HAUTEUR
        = "Entrez la hauteur (verticale) du labyrinthe : ";
        
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
                        && hauteurLabyrinthe > HAUTEUR_MINIMALE_LABYRINTHE
                        && hauteurLabyrinthe <= HAUTEUR_MAXIMALE_LABYRINTHE;
        
        if (!hauteurValide) {
            System.out.println(HAUTEUR_INVALIDE);
        }
        return hauteurValide;
    }
    
    /**
     * Affichage du labyrinthe à chaque coup joué, gestion des déplacements
     * du joueur dans celui-ci et arrêt lorsque la position du joueur
     * est sur la case de sortie.
     */
    private static void gestionDeplacementsLabyrinthe() {
        
    	int indiceActuel = labyrinthe.getIndiceSommetActuelle();
    	
    	Sommet sommetActuel = labyrinthe.getPositionActuelle();
    	
    	System.out.println("\n-- Position actuelle = " + sommetActuel);
    	
    	//affichageMurHaut(indiceActuel, sommetActuel);
        //affichageMurGauche(indiceActuel, sommetActuel);
        affichageSommet(sommetActuel);
        //affichageMurDroit(indiceActuel, sommetActuel);
        //affichageMurBas(indiceActuel, sommetActuel);
        
        System.out.println(DEMANDE_COMMANDE);
        
        if (!labyrinthe.demandeDeplacement()) {
            System.out.print(ERREUR_SAISIE);
        }
        System.out.println();
    }
    
    /**
     * Affichage console texte du sommet actuel du labyrinthe.
     */
    private static void affichageSommet(Sommet sommetActuel) {
        /* Entrée */
        if (labyrinthe.getEntree() == sommetActuel) {
            System.out.print(labyrinthe.getEntreeSymbole());
            
        /* Sortie */
        } else if (labyrinthe.getSortie() == sommetActuel) {
            System.out.print(labyrinthe.getSortieSymbole());
            
        /* Position actuelle */
        } else {
        	System.out.print(labyrinthe.getSommetActuelleSymbole());
        }
    }
    
    /**
     * Affichage console texte du mur haut du sommet courant s'il existe.
     */
    private static void affichageMurHaut(int indiceActuel, Sommet sommetActuel) {
    	
        if (indiceActuel > 0
            && listeSommets[indiceActuel].getCoordonneeX()
               == listeSommets[indiceActuel + 1].getCoordonneeX()
            && sommetActuel.liaisonExiste(listeSommets[indiceActuel + 1])) {
        	
        	System.out.println(MUR_HORIZONTAL);
        }
    }
    
    /**
     * Affichage console texte du mur bas du sommet courant s'il existe.
     */
    private static void affichageMurBas(int indiceActuel, Sommet sommetActuel) {
    	
        if (indiceActuel > 0
            && listeSommets[indiceActuel].getCoordonneeX()
               == listeSommets[indiceActuel - 1].getCoordonneeX()
            && sommetActuel.liaisonExiste(listeSommets[indiceActuel - 1])) {
        	
        	System.out.print(MUR_HORIZONTAL);
        }
    }
    
    /**
     * Affichage console texte du mur gauche du sommet courant s'il existe.
     */
    private static void affichageMurGauche(int indiceActuel, Sommet sommetActuel) {
    	
        if (indiceActuel > 0
            && listeSommets[indiceActuel].getCoordonneeY()
               == listeSommets[indiceActuel - 1].getCoordonneeY()
            && sommetActuel.liaisonExiste(listeSommets[indiceActuel - 1])) {
        	
        	System.out.print(MUR_VERTICAL);
        }
    }
    
    /**
     * Affichage console texte du mur droit du sommet courant s'il existe.
     */
    private static void affichageMurDroit(int indiceActuel, Sommet sommetActuel) {
    	
        if (indiceActuel < labyrinthe.getGraphe().getNombreSommets() - 1
            && listeSommets[indiceActuel].getCoordonneeY()
               == listeSommets[indiceActuel + 1].getCoordonneeY()
            && sommetActuel.liaisonExiste(listeSommets[indiceActuel + 1])) {
        	
        	System.out.print(MUR_HORIZONTAL);
        }
    }
}
