package iut.info1.sae.algorithmiquegestion.composants;

import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;

public class AffichageLabyrintheCaseCourante3 {
	
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
														  NOMBRE_COLONNES);
	
	private static Sommet[] listeSommets = labyrinthe.getGraphe()
										   .getListeSommets();
	public static void main(String[] args) {
		System.out.println(LANCEMENT_JEU);
	
		int ligneCourante;
		do {
			labyEntier();
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
						if (listeSommets[i + rangLigne] == labyrinthe.getPositionActuelle()
							|| listeSommets[i + rangLigne - labyrinthe.getNombreDeColonne()] == labyrinthe.getPositionActuelle()) {
							if (affichageMursVertical(i, rangLigne)) {
								System.out.print(LIAISON);
							} else {
								System.out.print(MUR_VERTICALE);
							}
						
						} else {
							System.out.print(LIAISON);
						}
						
						if (rangLigne < labyrinthe.getNombreDeColonne() - 1) {
							System.out.print(COIN_DE_MUR);
						}
					}
					System.out.print(BORDURE_DROITE + BORDURE_GAUCHE + " "); 
				}
				affichageSommets2(i);
				
				if (i + 1 < labyrinthe.getGraphe().getNombreSommets()
					&& listeSommets[i + 1] == labyrinthe.getPositionActuelle()
					|| listeSommets[i] == labyrinthe.getPositionActuelle()) {
					affichageMursHorizontaux2(i);
				} else {
					System.out.print("   ");
				}
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
	}
	
	/**
	 * TODO javadoc samuel
	 * @param i
	 */
	private static void affichageSommets2(int i) {
		/* Entrée */
		if (labyrinthe.getEntree() == listeSommets[i]) {
			System.out.print(labyrinthe.getEntreeSymbole());
			
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
	private static void affichageMursHorizontaux2(int i) {
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
	
		/*//////////////////////////////////////////////////////////////////////////////////////////////////*/
		/*//////////////////////////////////////////////////////////////////////////////////////////////////*/
		/*//////////////////////////////////////////////////////////////////////////////////////////////////*/
		/*//////////////////////////////////////////////////////////////////////////////////////////////////*/

	
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
			   == listeSommets[i + 1].getCoordonneeY()
			   ) {
			
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
