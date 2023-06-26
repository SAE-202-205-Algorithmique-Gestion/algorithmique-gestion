package iut.info1.sae.algorithmiquegestion.sauvegardes.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import iut.info1.sae.algorithmiquegestion.sauvegardes.ChargementEtCreationSauvegarde;

class TestChargementEtCreationSauvegarde {

	@Test
	void testAjoutExtensionNomSauvegarde() {
		String nomAvecExtention = "labyby.json";
		String nomSansExtention = "labyrinthe";
		
		String attendu1 = "labyby.json";
		String attendu2 = "labyrinthe.json";
		
		System.out.println(ChargementEtCreationSauvegarde.ajoutExtensionNomSauvegarde(nomAvecExtention) + "labyby.json");
		System.out.println(ChargementEtCreationSauvegarde.ajoutExtensionNomSauvegarde(nomSansExtention) + "labyrinthe.json");
		assertEquals(ChargementEtCreationSauvegarde.ajoutExtensionNomSauvegarde(nomAvecExtention), attendu1);
		assertEquals(ChargementEtCreationSauvegarde.ajoutExtensionNomSauvegarde(nomSansExtention), attendu2);
		
		
	}
}
