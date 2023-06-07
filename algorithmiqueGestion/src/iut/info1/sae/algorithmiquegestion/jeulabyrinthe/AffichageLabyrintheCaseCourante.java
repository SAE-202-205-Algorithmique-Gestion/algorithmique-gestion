/*
 * AffichageLabyrinthe.java											 2 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.jeulabyrinthe;

import java.util.List;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
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
public class AffichageLabyrintheCaseCourante {
	
	public final static String MUR_VERTICALE = "---";
	public final static String MUR_HORIZONTALE = " | ";

	public final static String LIAISON = "   ";
	
	private static final String MUR_BORDURE = "----";
	
	private static final String BORDURE_DROITE = "|";
	private static final String BORDURE_GAUCHE = "\n|";

	private static final String COIN_DE_MUR = "+";

	private static final char CASE = ' ';
	
	public final static String COMMANDES =
	"""
	- Z : déplacement vers le haut
	- S : déplacement vers le bas
	- Q : déplacement vers la gauche
	- D : déplacement vers la droite
	""";
	
	public final static String LANCEMENT_JEU =
	"""
	Bienvenue sur ce jeu de Labyrinthe !
	
	Veuillez renseigner la longueur et la largeur du labyrinthe. // TODO
	
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

    private final static String ERREUR_SAISIE =
    """
    \nVous avez entré une commande inconnue !
    
    Voici la liste des commandes utilisables dans la console texte :
    """
    + COMMANDES;
    
    private final static String DEMANDE_COMMANDE
	= "\n\nEntrez votre/vos commande(s) : ";

    private static final int NOMBRE_LIGNES = 10;
    private static final int NOMBRE_COLONNES = 10;

	private static Labyrinthe labyrinthe = new Labyrinthe(NOMBRE_LIGNES,
														  NOMBRE_COLONNES, 1);
	
	private static Sommet[] listeSommets = labyrinthe.getGraphe()
										   .getListeSommets();
	
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
		
		System.out.println(LANCEMENT_JEU);
		
		int ligneCourante;
		do {
			labyEntier();
			ligneCourante = 0;
//			bordureHauteEtBasse();
			
//			System.out.print(BORDURE_GAUCHE + " ");

			int i = labyrinthe.getIndiceSommetActuelle();
			
			if (listeSommets[i].getCoordonneeY() == 0
//				|| listeSommets[i].getCoordonneeY() == labyrinthe.getNombreDeLigne() - 1
				) {
				
				bordureHauteEtBasse2(listeSommets[i]);
				toutesLesLiaisons(listeSommets[i], 'N');
				
			} else {
				
			}
			if (labyrinthe.getGraphe().sommetExiste(i - 1)
				&& listeSommets[i].liaisonExiste(listeSommets[i-1])) {
				
				System.out.print("  ");
			} else {
				System.out.print("| ");
			}
			affichageSommets(i);
			
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
			affichageMursHorizontaux(i);
		
			
			// Dernière bordure latérale droite
//			System.out.println(" " + BORDURE_DROITE);
			
//			bordureHauteEtBasse();

			System.out.println(DEMANDE_COMMANDE);
			if (!labyrinthe.demandeDeplacement()) {
				System.out.print(ERREUR_SAISIE);
			}
			System.out.println();
			
		} while (labyrinthe.getPositionActuelle() != labyrinthe.getSortie());
		System.out.println(PARTIE_GAGNEE);
	}
	
	public static void toutesLesLiaisons(Sommet sommet, char nePasRegarderLiaison) {
		
		
	}

	public static boolean liaison(int indiceSommet, int indiceSommetALie) {
		return (labyrinthe.getGraphe().sommetExiste(indiceSommetALie)
			&& listeSommets[indiceSommet].liaisonExiste(listeSommets[indiceSommetALie]));
	}
	
	public static void bordureHauteEtBasse2(Sommet positionActuelle) {
		if (positionActuelle.getCoordonneeX() == 0) {
			System.out.println("+----");
			
		} else if (positionActuelle.getCoordonneeX()
				   == labyrinthe.getNombreDeColonne() - 1) {
			System.out.println("----+");
			
		} else {
			System.out.println("-----");
		}
	}
	
	
	
	/*///////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*///////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*///////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*///////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*///////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	
	
	
	
	/**
	 * TODO javadoc samuel
	 * @param i
	 * @param rangLigne
	 * @return Si l'affichage est possible
	 */
	private static boolean affichageMursVertical(int i, int rangLigne) {
		return listeSommets[i + rangLigne]
			   .liaisonExiste(listeSommets[i + rangLigne
			                               - labyrinthe.getNombreDeColonne()]);
	}
	
	/**
	 * TODO : javadoc samuel
	 */
	private static void bordureHauteEtBasse() {
		System.out.print(COIN_DE_MUR);
		for (int j = 0; j < labyrinthe.getGraphe().getNombreColonnesLabyrinthe() - 1; j++) {
			System.out.print(MUR_BORDURE);
		}
		System.out.print("---+");
	}
	
	/**
	 * TODO javadoc samuel
	 * @param i
	 */
	private static void affichageSommets(int i) {
		/* Entrée */
		if (labyrinthe.getEntree() == listeSommets[i]) {
			System.out.print(labyrinthe.getEntreeSymbole());
			
		/* Sortie */
		} else if (labyrinthe.getSortie() == listeSommets[i]) {
			System.out.print(labyrinthe.getSortieSymbole());
			
		/* Position actuelle */
		} else if (labyrinthe.getPositionActuelle() != labyrinthe.getEntree()
				   && labyrinthe.getPositionActuelle() == listeSommets[i]) {
			System.out.print(labyrinthe.getSommetActuelleSymbole());
		
		/* les autres sommets */
		} else {
			System.out.print(CASE);
		}
	}
	
	/**
	 * TODO : javadoc Samuel
	 * @param i
	 */
	private static void affichageMursHorizontaux(int i) {
		if (i < labyrinthe.getGraphe().getNombreSommets() - 1
			&& listeSommets[i].getCoordonneeY()
			   == listeSommets[i + 1].getCoordonneeY()) {
			
			if (listeSommets[i].liaisonExiste(listeSommets[i + 1])) {
				System.out.print(LIAISON);
			} else {
				System.out.print(MUR_HORIZONTALE);
			}
		}
	}
	
	private static void labyEntier() {
		int ligneCourante;
		
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
 				affichageMursHorizontaux(i);
			}
			
			// Dernière bordure latérale droite
			System.out.println(" " + BORDURE_DROITE);
			
			bordureHauteEtBasse();
			System.out.println();
			
	}
	
}
