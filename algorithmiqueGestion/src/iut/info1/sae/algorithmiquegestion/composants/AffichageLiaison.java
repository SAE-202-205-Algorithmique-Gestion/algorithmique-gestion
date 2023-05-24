package iut.info1.sae.algorithmiquegestion.composants;

import iut.info1.sae.algorithmiquegestion.composants.Graphe;

public class AffichageLiaison {
	
	public final static String LIAISON_HORIZONTALE = "----";
		
	public final static String VIDE_HORIZONTAL = "\t";
	
	public final static String LIAISON_VERTICALE = " |     ";
	
	public final static String VIDE_VERTICAL = "   \u0000   ";
	
	// TODO el ravadocumentacion
	public static void main(String[] args) {
				
		int ligne;
		int colonne;
		
		Graphe testTom = new Graphe(5, 5);
		
		for (ligne = 0; ligne < testTom.getNombreLignesLabyrinthe(); ligne++) {
			for (colonne = 0; colonne < testTom.getNombreColonnesLabyrinthe(); colonne++) {
			
				System.out.print(colonne + "," + ligne);
				
				if (testTom.sommetExiste(new Sommet(colonne + 1, ligne)) 
					&& testTom.estAdjacent(testTom.getListeSommets()[colonne], 
						                   testTom.getListeSommets()[colonne + 1])) {
					
					System.out.print(LIAISON_HORIZONTALE);
				} else {
					System.out.print(VIDE_HORIZONTAL);
						
				}
					
			}
			
			System.out.println();
			
			if (ligne != testTom.getNombreLignesLabyrinthe() - 1) {
				for (colonne = 0; colonne < testTom.getNombreColonnesLabyrinthe(); colonne++) {
					if (testTom.sommetExiste(new Sommet(colonne, ligne + 1)) 
						&& testTom.estAdjacent(testTom.getListeSommets()[ligne], 
						   testTom.getListeSommets()[ligne + 1])) {
						
						System.out.print(LIAISON_VERTICALE);
					} else {
						System.out.print(VIDE_VERTICAL);
							
					}
				}
			}
			
			System.out.println();

		}
	}
}

