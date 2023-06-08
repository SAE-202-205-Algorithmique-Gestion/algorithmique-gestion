/*
 * TODO : javadoc
 */
package iut.info1.sae.algorithmiquegestion.affichage;

import iut.info1.sae.algorithmiquegestion.composants.Graphe;

/**
 * TODO : javadoc
 */
public class AffichageLiaison {
	
	public final static String LIAISON_HORIZONTALE = "----";
	
	public final static String VIDE_HORIZONTAL = "\t";
	
	public final static String LIAISON_VERTICALE = " |     ";
	
//	public final static String VIDE_VERTICAL = "   \u0000   ";
	public final static String VIDE_VERTICAL = "       ";
	
	/**
	 * TODO : javadoc
	 */
	public static void main(String[] args) {
		int ligne = 0;
		
		Graphe testSam = new Graphe(5, 5);
		for (int indexSommet = 0; indexSommet < testSam.getNombreSommets(); indexSommet++) {
			System.out.println("Sommet : " + testSam.getListeSommets()[indexSommet]);
			for (int i = 0; i < testSam.getListeSommets()[indexSommet].getLiaisons().size(); i++) {
				System.out.println("Sommet lie : " + testSam.getListeSommets()[indexSommet].getLiaisons().get(i));
			}
			
		}
		
//		for (ligne = 0; ligne < testSam.getNombreLignesLabyrinthe(); ligne++) {
//			for (colonne = 0; colonne < testSam.getNombreColonnesLabyrinthe(); colonne++) {
//				    
//				}
//		}
		
		for (int i = 0; i < testSam.getNombreSommets(); i++) {
			
			if (testSam.getListeSommets()[i].getCoordonneeY() == ligne + 1) {
				System.out.println("");
				ligne++;
				
				for (int rang = 0; rang < testSam.getNombreColonnesLabyrinthe(); rang++) {
					if (testSam.getListeSommets()[i + rang].liaisonExiste(testSam.getListeSommets()[i + rang - testSam.getNombreColonnesLabyrinthe()])) {
						System.out.print(LIAISON_VERTICALE);
					} else {
						System.out.print (VIDE_VERTICAL);
					}
				}
				System.out.println("");
				
			}
			
			System.out.print(testSam.getListeSommets()[i].getCoordonneeX()
					+ "," + testSam.getListeSommets()[i].getCoordonneeY());
			
			if (testSam.getListeSommets()[i].getCoordonneeY() == testSam.getListeSommets()[i + 1].getCoordonneeY()) {
				if (testSam.getListeSommets()[i].liaisonExiste(testSam.getListeSommets()[i + 1])) {
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
