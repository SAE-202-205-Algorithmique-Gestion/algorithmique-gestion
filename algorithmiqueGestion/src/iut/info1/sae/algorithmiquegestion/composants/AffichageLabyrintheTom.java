package iut.info1.sae.algorithmiquegestion.composants;

import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;

// TODO el ravadocumentacion
public class AffichageLabyrintheTom {
	
	public final static String MUR_VERTICALE = "---+";

	public final static String MUR_VERTICALE_FIN_DE_LIGNE = "---";
	
	public final static String LIAISON_HORIZONTALE = "   ";
	
	public final static String MUR_HORIZONTALE = " | ";
	
//	public final static String VIDE_VERTICAL = "   \u0000   ";
	public final static String LIAISON_VERTICALE = "   +";
	
	private final static String PARTIE_GAGNEE = "\nBravo !\nVous avez gagné, la"
			+ " partie est terminée !";

	private static final String LIAISON_VERTICALE_FIN_DE_LIGNE = "   ";

	private static final String MUR_BORDURE = "----";

	private static Labyrinthe testSam = new Labyrinthe(10, 10);
	
	// TODO el ravadocumentacion
	public static void main(String[] args) {
		
		
//		for (int indexSommet = 0; indexSommet < testSam.getGraphe().getNombreSommets(); indexSommet++) {
//			System.out.println("Sommet : " + testSam.getGraphe().getListeSommets()[indexSommet]);
//			for (int i = 0; i < testSam.getGraphe().getListeSommets()[indexSommet].getLiaisons().size(); i++) {
//				System.out.println("Sommet lie : " + testSam.getGraphe().getListeSommets()[indexSommet].getLiaisons().get(i));
//			}
//			
//		}
		
		int ligne;
		do {
			ligne = 0;
			System.out.print("+");
			for (int j = 0; j < testSam.getGraphe().getNombreColonnesLabyrinthe() - 1; j++) {
				System.out.print(MUR_BORDURE);
			}
			System.out.print("---+");
			System.out.print("\n| ");

//			System.out.println();
			
			for (int i = 0; i < testSam.getGraphe().getNombreSommets(); i++) {
				
				if (testSam.getGraphe().getListeSommets()[i].getCoordonneeX() == 0
					&& testSam.getGraphe().getListeSommets()[i].getCoordonneeY() != 0) {
					System.out.print(" |");
				}
				
				if (testSam.getGraphe().getListeSommets()[i].getCoordonneeY() == ligne + 1) {
//					System.out.println("");
					System.out.print("\n|");
					ligne++;
					
					for (int rangLigne = 0; rangLigne < testSam.getGraphe().getNombreColonnesLabyrinthe(); rangLigne++) {
						if (affichageLiaisonVertical(i, rangLigne)) {
							if (finDeLigne(rangLigne)) {
								System.out.print(LIAISON_VERTICALE_FIN_DE_LIGNE);
							} else {
								System.out.print(LIAISON_VERTICALE);
							}
							
						} else {
							if (finDeLigne(rangLigne)) {
								System.out.print(MUR_VERTICALE_FIN_DE_LIGNE);
							} else {
								System.out.print(MUR_VERTICALE);
							}
						}
					}
					System.out.print("|" + "\n| ");
					
				}
				/* Affichage des sommets :
				 * Entrée */
				if (testSam.getEntree() == testSam.getGraphe().getListeSommets()[i]) {
					System.out.print(testSam.getEntreeSymbole());
					
				/* Sortie */
				} else if (testSam.getSortie() == testSam.getGraphe().getListeSommets()[i]) {
					System.out.print(testSam.getSortieSymbole());
					
				/* Position actuelle */
				} else if (testSam.getPositionActuelle() != testSam.getEntree()
						   && testSam.getPositionActuelle() == testSam.getGraphe().getListeSommets()[i]) {
					System.out.print(testSam.getSommetActuelleSymbole());
				
				/* les autres sommets */
				} else {
					System.out.print(" ");
				}
					
				if (i < testSam.getGraphe().getNombreSommets() - 1) {
					if (testSam.getGraphe().getListeSommets()[i].getCoordonneeY() == testSam.getGraphe().getListeSommets()[i + 1].getCoordonneeY()) {
						if (testSam.getGraphe().getListeSommets()[i].liaisonExiste(testSam.getGraphe().getListeSommets()[i + 1])) {
							System.out.print(LIAISON_HORIZONTALE);
						} else {
							System.out.print(MUR_HORIZONTALE);
						}
					}
				}
				
			}
			System.out.print(" |");
			System.out.print("\n+");

			for (int j = 0; j < testSam.getGraphe().getNombreColonnesLabyrinthe() - 1; j++) {
                System.out.print(MUR_BORDURE);
            }
			System.out.print("---+");

			testSam.demandeDeplacement();
			System.out.println("");
			
		} while (testSam.getPositionActuelle() != testSam.getSortie());
		System.out.println(PARTIE_GAGNEE);
	}
	
	private static boolean affichageLiaisonVertical(int i, int rangLigne) {
		if (testSam.getGraphe().getListeSommets()[i + rangLigne]
			.liaisonExiste
			(testSam.getGraphe().getListeSommets()[i + rangLigne - testSam.getGraphe()
												.getNombreColonnesLabyrinthe()])) {
			return true;
		}
		return false;
	}
	
	private static boolean finDeLigne(int rangLigne) {
		return rangLigne == testSam.getGraphe().getNombreColonnesLabyrinthe() - 1;
	}
}
