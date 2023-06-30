package iut.info1.sae.algorithmiquegestion.modeles;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.sauvegardes.ChargementEtCreationSauvegarde;
import iut.info1.sae.algorithmiquegestion.sauvegardes.VerificationInitialisationAttributs;

public class CreationEtChargementLabyrinthe {
	
	private static Labyrinthe labyrinthe;
	
	public static Labyrinthe getLabyrinthe() {
		return labyrinthe;
	}

	public static boolean chargementLabyrinthePossible(String nomSauvegarde) {
		labyrinthe = ChargementEtCreationSauvegarde.chargerUneSauvegarde(nomSauvegarde);
		if (labyrinthe == null
 			|| !VerificationInitialisationAttributs.areTousLesAttributsInitialisé(labyrinthe)) {
	 		System.out.println("Le labyrinthe demandé à eu un problème de sauvegarde");
	 		return false;
	 		
	 	}
		return true;
	}
	
	public static void creationLabyrinthe(int nombreLignes, int nombreColonnes, int typeConstruction) {
		labyrinthe = new Labyrinthe(nombreLignes, nombreColonnes, typeConstruction);
	}
}
