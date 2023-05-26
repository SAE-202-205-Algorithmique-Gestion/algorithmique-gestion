package iut.info1.sae.algorithmiquegestion.composants;

import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;

// TODO el ravadocumentacion
public class AffichageLabyrinthe {
	
	public final static String LIAISON_HORIZONTALE = "----";
	
	public final static String VIDE_HORIZONTAL = "\t";
	
	public final static String LIAISON_VERTICALE = "|    ";
	
//	public final static String VIDE_VERTICAL = "   \u0000   ";
	public final static String VIDE_VERTICAL = "     ";
	
	// TODO el ravadocumentacion
	public static void main(String[] args) {
		int ligne = 0;
		
		Labyrinthe testSam = new Labyrinthe(5, 5);
		for (int indexSommet = 0; indexSommet < testSam.getGraphe().getNombreSommets(); indexSommet++) {
			System.out.println("Sommet : " + testSam.getGraphe().getListeSommets()[indexSommet]);
			for (int i = 0; i < testSam.getGraphe().getListeSommets()[indexSommet].getLiaisons().size(); i++) {
				System.out.println("Sommet lie : " + testSam.getGraphe().getListeSommets()[indexSommet].getLiaisons().get(i));
			}
			
		}
		

		for (int i = 0; i < testSam.getGraphe().getNombreSommets(); i++) {
			
			if (testSam.getGraphe().getListeSommets()[i].getCoordonneeY() == ligne + 1) {
				System.out.println("");
				ligne++;
				
				for (int rang = 0; rang < testSam.getGraphe().getNombreColonnesLabyrinthe(); rang++) {
					if (testSam.getGraphe().getListeSommets()[i + rang].liaisonExiste(testSam.getGraphe().getListeSommets()[i + rang - testSam.getGraphe().getNombreColonnesLabyrinthe()])) {
						System.out.print(LIAISON_VERTICALE);
					} else {
						System.out.print (VIDE_VERTICAL);
					}
				}
				System.out.println("");
				
			}
			/* Affichage des sommets*/
			System.out.print("#");
			
			if (testSam.getGraphe().getListeSommets()[i].getCoordonneeY() == testSam.getGraphe().getListeSommets()[i + 1].getCoordonneeY()) {
				if (testSam.getGraphe().getListeSommets()[i].liaisonExiste(testSam.getGraphe().getListeSommets()[i + 1])) {
					System.out.print(LIAISON_HORIZONTALE);
				} else {
					System.out.print("    ");
				}
			}
			
			
			

//				if (testSam.getListeSommets()[i].getLiaisons()[rang] != (Sommet) null) {
//					if (testSam.getListeSommets()[i].getLiaisons()[rang].getCoordonneeX() == testSam.getListeSommets()[i + 1].getCoordonneeX()) {
//						System.out.print(LIAISON_HORIZONTALE);
//					} 
//				} 
				
				
//				if (testSam.sommetExiste() 
//					&& testSam.estAdjacent(testSam.getListeSommets()[colonne], 
//						                   testSam.getListeSommets()[colonne + 1])) {
//					
//					System.out.print(LIAISON_HORIZONTALE);
//				} else {
//					System.out.print(VIDE_HORIZONTAL);
//						
//				}
//					
//			}
//			
//			System.out.println();
//			
//			if (ligne != testSam.getNombreLignesLabyrinthe() - 1) {
//				for (colonne = 0; colonne < testSam.getNombreColonnesLabyrinthe(); colonne++) {
//					if (testSam.sommetExiste(new Sommet(colonne, ligne + 1)) 
//						&& testSam.estAdjacent(testSam.getListeSommets()[ligne], 
//						   testSam.getListeSommets()[ligne + 1])) {
//						
//						System.out.print(LIAISON_VERTICALE);
//					} else {
//						System.out.print(VIDE_VERTICAL);
//							
//					}
//				}

		}
	}
}
