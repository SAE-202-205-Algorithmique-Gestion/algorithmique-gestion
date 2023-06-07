package iut.info1.sae.algorithmiquegestion.jeulabyrinthe;

import java.util.Scanner;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

public class MenuLabytinthe {
	
	private final static String DEMANDE_COMMANDE
    = "\n\nEntrez votre/vos commande(s) : ";
	
	public final static String COMMANDES_MENU_PRINCIPAL =
		"- 1 : Construction d'un labyrinthe"
	// + "\n- 2 : ouverture d'un labyrinthe sauvegardé"
	    + "\n____________________________________________"
	    + DEMANDE_COMMANDE;
	
	public final static String BIENVENUE
    = "Bienvenue sur ce jeu de Labyrinthe !\n";
	
	
	
	private static final int HAUTEUR_MINIMALE_LABYRINTHE = 2;
    
    private static final int LONGUEUR_MINIMALE_LABYRINTHE = 2;

    private static final int HAUTEUR_MAXIMALE_LABYRINTHE = 10;
    
    private static final int LONGUEUR_MAXIMALE_LABYRINTHE = 10;
	
	private static int hauteurLabyrinthe;
    
    private static int longueurLabyrinthe;
	
	private static Labyrinthe labyrinthe;
	
	private static Sommet[] listeSommets;
	
	private static int typeLabyrinthe;
	
	public static Labyrinthe getLabyrinthe() {
		return labyrinthe;
	}
	
	public static void main(String[] args) {
		boolean menuPrincipalPasse;
		
		System.out.println(BIENVENUE);
		
	    do {
	    	menuPrincipalPasse = demandeParametresLabyrinthe();
		} while (!menuPrincipalPasse);
	    
	    
	}
	
	/**
	 * Affichage console texte demandant en boucle les informations du
	 * labyrinthe souhaité par l'utilisateur (hauteur, longueur) jusqu'à
	 * avoir des données valides.
	 * 
	 * @return true si les paramètres sont valides.
	 */
	private static boolean demandeParametresLabyrinthe() {

		final String CHOIX_NOUVEAU_LABYRINTHE = "1";
		
		final String CHOIX_OUVRIR_SAUVEGARDE = "2";
		
		final String NOUVEAU_LABYRINTHE = "\n>> NOUVEAU LABYRINTHE";
		
		final String COMMANDE_INEXISTANTE = "\nErreur : commande inexistante !\n";
		
		Scanner analyseurSaisie;		
		
		String saisieMenuPrincipal;
		
		boolean saisieLongueurLabyrintheTermine = false;
		boolean saisieHauteurLabyrintheTermine = false;
		boolean saisieTypeLabyrintheTermine = false;
		
		boolean resultatValide = false;
				
		System.out.println(COMMANDES_MENU_PRINCIPAL);
		
		analyseurSaisie = new Scanner(System.in);
        
		saisieMenuPrincipal = analyseurSaisie.next();
		
//		c'est pour gérer les erreurs de saisie dans le cas où 
//		le saisie contient plus de 1 caractère. Ne fonctionne pas correctement	
//		if (saisieMenuPrincipal.length() != 1) {
//			analyseurSaisie.close();
//			System.out.println(COMMANDE_INEXISTANTE);
//			return resultatValide;
//		}

		analyseurSaisie.nextLine();
		
		switch (saisieMenuPrincipal) {
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
            
            do {
                saisieTypeLabyrintheTermine
                = saisirTypeConstructionLabyrinthe(analyseurSaisie);
            } while (!saisieTypeLabyrintheTermine);
            
            System.out.println("\n");
            
            try {
            	labyrinthe = new Labyrinthe(hauteurLabyrinthe,
            			longueurLabyrinthe,
            			typeLabyrinthe);
			} catch (Exception e) {
				// TODO: handle exception
			}
            
            listeSommets = labyrinthe.getGraphe().getListeSommets();
            
            resultatValide = true;
            AffichageLabyrinthe.lancement();
		    break;
		    
//		case CHOIX_OUVRIR_SAUVEGARDE:
//            // TODO : Ouvrir le labyrinthe sauvegardé
//		    break;
		    
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
     * Vérification de la validité de la hauteur saisie.
     *
     * @return true si la hauteur saisie est correcte.
     */
    private static boolean saisirTypeConstructionLabyrinthe(Scanner analyseurEntree) {
        
        final String ENTRER_TYPE
        = """
          Entrez la type de construction du labyrinthe : 
           - 1 : Construction par chaines ascendentes
           - 2 : Construction par descente en profondeur
          """;
        
        final String TYPE_INVALIDE = "\nErreur : type invalide.";
        
        boolean typeValide = true;
        
        System.out.print(ENTRER_TYPE);
        
        if (analyseurEntree.hasNextInt()) {
            typeLabyrinthe = analyseurEntree.nextInt();
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
