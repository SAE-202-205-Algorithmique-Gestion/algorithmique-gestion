package iut.info1.sae.algorithmiquegestion.composants;

import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;

// TODO el ravadocumentacion
public class AffichageLabyrintheTom {
	
	public final static String MUR_VERTICALE = "---";

	public final static String LIAISON_HORIZONTALE = "   ";
	
	public final static String MUR_HORIZONTALE = " | ";
	
	public final static String LIAISON_VERTICALE = "   ";
	
	private static final String MUR_BORDURE = "----";

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
			
			Veuillez d'abord renseigner la longueur et la largeur'
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

    private static final int nombreLignes = 5;
    private static final int nombreColonnes = 5;

	private static Labyrinthe labyrinthe = new Labyrinthe(nombreLignes, nombreColonnes);
	
	private static Sommet[] listeSommets = labyrinthe.getGraphe().getListeSommets();
	
	// TODO el ravadocumentacion
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
			ligneCourante = 0;
			bordureHauteEtBasse();
			
			//Première bordure de gauche
			System.out.print("\n| ");

			for (int i = 0; i < listeSommets.length; i++) {
				
				if (listeSommets[i].getCoordonneeX() == 0
					&& listeSommets[i].getCoordonneeY() != 0) {
					//Bordure latérale droite
					System.out.print(" |");
				}
				
 				if (listeSommets[i].getCoordonneeY() == ligneCourante + 1) {
 					//Bordure latérale gauche
					System.out.print("\n|");
					
					ligneCourante++;
					
					for (int rangLigne = 0; rangLigne < labyrinthe.getNombreDeColonne(); rangLigne++) {
						if (affichageMursVertical(i, rangLigne)) {
							System.out.print(LIAISON_VERTICALE);
						} else {
							System.out.print(MUR_VERTICALE);
						}
						
						if (rangLigne < labyrinthe.getNombreDeColonne() - 1) {
							System.out.print(COIN_DE_MUR);
						}
					}
					//Bordure latérale droite puis gauche
					System.out.print("|" + "\n| "); 
					
				}
 				affichageSommets(i);
 				affichageMursHorizontaux(i);
				
			}
			
			//dernière bordure latérale droite
			System.out.println(" |");
			
			bordureHauteEtBasse();

			System.out.println(DEMANDE_COMMANDE);
			if (!labyrinthe.demandeDeplacement()) {
				System.out.print(ERREUR_SAISIE);
			}
			System.out.println();
			
		} while (labyrinthe.getPositionActuelle() != labyrinthe.getSortie());
		System.out.println(PARTIE_GAGNEE);
	}
	
	
	
	



	private static boolean affichageMursVertical(int i, int rangLigne) {
		if (listeSommets[i + rangLigne]
			.liaisonExiste
			(listeSommets[i + rangLigne - labyrinthe.getNombreDeColonne()])) {
			return true;
		}
		return false;
	}
	
	private static void bordureHauteEtBasse() {
		System.out.print(COIN_DE_MUR);
		for (int j = 0; j < labyrinthe.getGraphe().getNombreColonnesLabyrinthe() - 1; j++) {
			System.out.print(MUR_BORDURE);
		}
		System.out.print("---+");
	}
	
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
	
	private static void affichageMursHorizontaux(int i) {
		if (i < labyrinthe.getGraphe().getNombreSommets() - 1
			&& listeSommets[i].getCoordonneeY() == listeSommets[i + 1].getCoordonneeY()) {
			
			if (listeSommets[i].liaisonExiste(listeSommets[i + 1])) {
				System.out.print(LIAISON_HORIZONTALE);
			} else {
				System.out.print(MUR_HORIZONTALE);
			}
		}
		
	}
}
