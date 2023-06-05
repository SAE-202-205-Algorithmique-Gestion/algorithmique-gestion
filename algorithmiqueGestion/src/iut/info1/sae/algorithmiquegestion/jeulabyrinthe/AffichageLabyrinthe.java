/*
 * AffichageLabyrinthe.java											 2 juin 2023
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
	"________ COMMANDES MENU PRINCIPAL ________"
	+ "\n\n- N : création d'un nouveau labyrinthe"
    //+ "\n- O : ouverture d'un labyrinthe sauvegardé"
    + "\n__________________________________________"
    + DEMANDE_COMMANDE;
    
    public final static String BIENVENUE
    = "Bienvenue sur ce jeu de Labyrinthe !\n";
	
	public final static String LANCEMENT_JEU =
	BIENVENUE +
	"""
	Veuillez renseigner la longueur et la largeur du labyrinthe.
	
	Voici la liste des commandes utilisables dans la console texte :
	"""
	+ COMMANDES +
	"""
	Tapez la touche entrée une fois que votre liste de commandes correspond à l'action souhaitée.
	
	Vous commencez sur la case E et devez atteindre la case S.
	Votre position courante est représentée par X.
	Si vous revenez sur la case E (l'entrée), X ne sera plus affiché.
	
	Vous devez vous déplacer là où une liaison apparaît.
	Dans le cas contraire rien ne se passe.
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
		
		
//		for (int indexSommet = 0; indexSommet < testSam.getGraphe().getNombreSommets(); indexSommet++) {
//			System.out.println("Sommet : " + testSam.getGraphe().getListeSommets()[indexSommet]);
//			for (int i = 0; i < testSam.getGraphe().getListeSommets()[indexSommet].getLiaisons().size(); i++) {
//				System.out.println("Sommet lie : " + testSam.getGraphe().getListeSommets()[indexSommet].getLiaisons().get(i));
//			}
//			
//		}
//		ParcoursProfondeur.algorithmeParcours();
		
		int ligneCourante,
		    longueurTemporaire,
		    hauteurTemporaire;
		
		boolean menuPrincipalPasse,
		        saisieLongueurLabyrintheTermine,
		        saisieHauteurLabyrintheTermine;
		
		String saisieMenuPrincipal;
		
		Scanner analyseurSaisie;
		
		menuPrincipalPasse = false;
		saisieLongueurLabyrintheTermine = false;
		saisieHauteurLabyrintheTermine = false;
		
		analyseurSaisie = new Scanner(System.in);
		
		System.out.println(BIENVENUE);
		
        do {
			System.out.println(COMMANDES_MENU_PRINCIPAL);
			                 
			saisieMenuPrincipal = analyseurSaisie.next();
			analyseurSaisie.nextLine();
			
			switch (saisieMenuPrincipal.toLowerCase()) {
			case "n":
                System.out.println("\n\n>> NOUVEAU LABYRINTHE");

                while (!saisieLongueurLabyrintheTermine) {
					if (saisirLongueurLabyrinthe(analyseurSaisie)) {
						saisieLongueurLabyrintheTermine = true;
                    } else {
						System.out.println("\nErreur : longueur invalide.");
					}
				}
				
				while (!saisieHauteurLabyrintheTermine) {
                    if (saisirHauteurLabyrinthe(analyseurSaisie)) {
                        saisieHauteurLabyrintheTermine = true;
                    } else {
                        System.out.println("\nErreur : hauteur invalide.");
                    }
                }
                
                System.out.println("\n");
                
                labyrinthe = new Labyrinthe(hauteurLabyrinthe,
                                            longueurLabyrinthe);
                
                listeSommets = labyrinthe.getGraphe()
						   .getListeSommets();
                
                menuPrincipalPasse = true;
			    break;
			    
			case "o":
                // TODO : Ouvrir le labyrinthe sauvegardé
			    break;
			    
			default:
                System.out.println("\nErreur : commande inexistante !\n");
			    break;
			}
		} while (!menuPrincipalPasse);
		
        do {
			ligneCourante = 0;
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
						if (affichageMursVertical(i, rangLigne)) {
							System.out.print(LIAISON);
						} else {
							System.out.print(MUR_VERTICALE);
						}
						
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
			System.out.println();
			

		} while (labyrinthe.getPositionActuelle() != labyrinthe.getSortie());
		System.out.println(PARTIE_GAGNEE);
		System.out.print(PARCOURS_FIN);
		ParcoursProfondeur.algorithmeParcours();
		
	}
	
	/**
     * Vérification de la validité de la longueur saisie.
     *
     * @return true si la longueur saisie est correcte.
     */
	private static boolean saisirLongueurLabyrinthe(Scanner analyseurEntree) {
		boolean longueurValide;
		
		longueurValide = true;
		
		System.out.print("\nEntrez la longueur (horizontale) du labyrinthe : ");
		
		if (analyseurEntree.hasNextInt()) {
			longueurLabyrinthe = analyseurEntree.nextInt();
		} else {
			analyseurEntree.nextLine();
			longueurValide = false;
		}
		System.out.println();
		
		return longueurValide
		       && longueurLabyrinthe > LONGUEUR_MINIMALE_LABYRINTHE
               && longueurLabyrinthe <= LONGUEUR_MAXIMALE_LABYRINTHE;
	}
	
	/**
	 * Vérification de la validité de la hauteur saisie.
	 *
	 * @return true si la hauteur saisie est correcte.
	 */
	private static boolean saisirHauteurLabyrinthe(Scanner analyseurEntree) {
        boolean hauteurValide;
        
        hauteurValide = true;
        
        System.out.print("\n\nEntrez la hauteur (verticale) du labyrinthe : ");
        
        if (analyseurEntree.hasNextInt()) {
            hauteurLabyrinthe = analyseurEntree.nextInt();
        } else {
			analyseurEntree.nextLine();
            hauteurValide = false;
        }
        System.out.println();
        
        return hauteurValide
               && hauteurLabyrinthe > HAUTEUR_MINIMALE_LABYRINTHE
               && hauteurLabyrinthe <= HAUTEUR_MAXIMALE_LABYRINTHE;
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
