/*
 * AffichageLabyrinthe.java											 6 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.jeulabyrinthe;

import java.util.Scanner;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * Affichage sur console texte d'un jeu de labyrinthe représenté
 * sous forme "humaine" avec un point de départ (l'entrée) E, un point
 * de sortie S et une position courante X.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class AffichageLabyrinthe {
	
	public final static String MUR_VERTICALE = "---";
	public final static String MUR_HORIZONTALE = " | ";

	public final static String LIAISON = "   ";
	
	private static final String MUR_BORDURE = "----";
	
	private static final String BORDURE_DROITE = "|";
	private static final String BORDURE_GAUCHE = "\n|";

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
	"- 1 : Construction d'un labyrinthe"
// + "\n- 2 : ouverture d'un labyrinthe sauvegardé"
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
    
    private static final int HAUTEUR_MINIMALE_LABYRINTHE = 2;
    
    private static final int LONGUEUR_MINIMALE_LABYRINTHE = 2;

    private static final int HAUTEUR_MAXIMALE_LABYRINTHE = 10;
    
    private static final int LONGUEUR_MAXIMALE_LABYRINTHE = 10;

    private static int hauteurLabyrinthe;
    
    private static int longueurLabyrinthe;
    
    private static int typeLabyrinthe;
    
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
		
//		for (int indexSommet = 0; indexSommet < testSam.getGraphe().getNombreSommets(); indexSommet++) {
//			System.out.println("Sommet : " + testSam.getGraphe().getListeSommets()[indexSommet]);
//			for (int i = 0; i < testSam.getGraphe().getListeSommets()[indexSommet].getLiaisons().size(); i++) {
//				System.out.println("Sommet lie : " + testSam.getGraphe().getListeSommets()[indexSommet].getLiaisons().get(i));
//			}
//			
//		}
//		ParcoursProfondeur.algorithmeParcours();
		
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
		
		System.out.print("Vous avez parcouru ce labyrinthe avec "
				 	     + labyrinthe.getNombreCasesParcourues()
				         + " cases.");
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
            
            labyrinthe = new Labyrinthe(hauteurLabyrinthe,
                                        longueurLabyrinthe,
                                        typeLabyrinthe);
            
            listeSommets = labyrinthe.getGraphe().getListeSommets();
            
            resultatValide = true;
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
	
	/**
	 * Affichage du labyrinthe à chaque coup joué, gestion des déplacements
	 * du joueur dans celui-ci et arrêt lorsque la position du joueur
	 * est sur la case de sortie.
	 */
	private static void gestionDeplacementsLabyrinthe() {
		
		int ligneCourante = 0;
		
		bordureHauteEtBasse();
		
		System.out.print(BORDURE_GAUCHE + " ");

		for (int i = 0; i < listeSommets.length; i++) {
			
			if (listeSommets[i].getCoordonneeX() == 0
				&& listeSommets[i].getCoordonneeY() != 0) {
				// Bordure latérale droite
				System.out.print(" " + BORDURE_DROITE);
			}
			
			if (listeSommets[i].getCoordonneeY() == ligneCourante + 1) {
				System.out.print(BORDURE_GAUCHE);
			
				ligneCourante++;
				
				for (int rangLigne = 0; rangLigne < labyrinthe.getNombreDeColonne(); rangLigne++) {
					
					System.out.print(affichageMursVertical(i, rangLigne)
									 ? LIAISON
									 : MUR_VERTICALE);
					
					if (rangLigne < labyrinthe.getNombreDeColonne() - 1) {
						System.out.print(COIN_DE_MUR);
					}
				}
				System.out.print(BORDURE_DROITE + BORDURE_GAUCHE + " "); 
			}
			
			affichageSommets(i);
			affichageMurHorizontal(i);
		}
		
		// Dernière bordure latérale droite
		System.out.println(" " + BORDURE_DROITE);
		
		bordureHauteEtBasse();
		
		System.out.println(DEMANDE_COMMANDE);
		
		if (!labyrinthe.demandeDeplacement()) {
			System.out.print(ERREUR_SAISIE);
		}
	}
	
	/**
	 * Vérification des liaisons afin d'afficher ou non les murs verticaux.
	 *
	 * @param indiceSommet Indice du sommet pour lequel vérifier la liaison.
	 * @param rangLigne Le rang de la ligne permettant la vérification.
	 * @return true si l'affichage est possible.
	 */
	private static boolean affichageMursVertical(int indiceSommet, int rangLigne) {
		return listeSommets[indiceSommet + rangLigne]
			   .liaisonExiste(listeSommets[indiceSommet + rangLigne
			                               - labyrinthe.getNombreDeColonne()]);
	}
	
	/**
	 * Affichage console texte des bordures du haut et du bas du labyrinthe.
	 */
	private static void bordureHauteEtBasse() {
		System.out.print(COIN_DE_MUR);
		for (int j = 0; j < labyrinthe.getGraphe().getNombreColonnesLabyrinthe() - 1; j++) {
			System.out.print(MUR_BORDURE);
		}
		System.out.print("---+");
	}
	
    /**
     * Affichage console texte des sommets du labyrinthe.
     *
     * @param indiceSommet Indice du sommet courant.
     */
	private static void affichageSommets(int indiceSommet) {
		/* Entrée */
		if (labyrinthe.getEntree() == listeSommets[indiceSommet]) {
			System.out.print(labyrinthe.getEntreeSymbole());
			
		/* Sortie */
		} else if (labyrinthe.getSortie() == listeSommets[indiceSommet]) {
			System.out.print(labyrinthe.getSortieSymbole());
			
		/* Position actuelle */
		} else if (labyrinthe.getPositionActuelle() != labyrinthe.getEntree()
				   && labyrinthe.getPositionActuelle()
				      == listeSommets[indiceSommet]) {
			System.out.print(labyrinthe.getSommetActuelleSymbole());
		
		/* Les autres sommets */
		} else {
			System.out.print(CASE);
		}
	}
	
	/**
	 * Affichage console texte de tous les murs horizontaux du labyrinthe.
	 *
	 * @param indiceSommet Indice du sommet courant à côté du mur à afficher.
	 */
	private static void affichageMurHorizontal(int indiceSommet) {
		if (indiceSommet < labyrinthe.getGraphe().getNombreSommets() - 1
			&& listeSommets[indiceSommet].getCoordonneeY()
			   == listeSommets[indiceSommet + 1].getCoordonneeY()) {
			
			if (listeSommets[indiceSommet].liaisonExiste(listeSommets[indiceSommet + 1])) {
				System.out.print(LIAISON);
			} else {
				System.out.print(MUR_HORIZONTALE);
			}
		}
	}
}
