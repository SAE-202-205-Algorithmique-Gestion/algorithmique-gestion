/*
 * TestPile.java											  		 12 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */

package iut.info1.sae.algorithmiquegestion.piles.tests;

import iut.info1.sae.algorithmiquegestion.piles.Pile;
import iut.info1.sae.algorithmiquegestion.piles.PileVideException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires de la classe Pile
 * (iut.info1.sae.algorithmiquegestion.piles.Pile).
 * 
 * @author Simon GUIRAUD
 * @author Loïc FAUGIERES
 */
public class TestPile {
	
	/* fixture de test */
	private Pile pileVide = new Pile();
	
	private Pile[] piles;
	
	/**
	 * Fixture de création de piles non vides pour les divers tests.
	 */
	@BeforeEach
	void creerPilesNonVides() {
		piles = new Pile[3];
		
		piles[0] = new Pile();
		piles[0].empiler(Integer.valueOf(-3));
		
		piles[1] = new Pile();
		for (int valeur = 1; valeur <= 50000; valeur++) {
			final Integer nombre = Integer.valueOf(valeur);
			piles[1].empiler(nombre);
		}
	}
	
	/**
	 * Méthode de test de {@link iut.info1.sae.algorithmiquegestion.piles.Pile}.
	 */
	@Test
	@DisplayName("Test du constructeur de la classe Pile")
	void testPile() {
		assertTrue(pileVide.isVide());
	}
	
	/**
	 * Méthode de test de
	 * {@link iut.info1.sae.algorithmiquegestion.piles.Pile#isVide()}.
	 */
	@Test
	@DisplayName("Test de la méthode isVide() de la classe Pile")
	void testIsVide() {
		assertTrue(pileVide.isVide());
		assertFalse(piles[0].isVide());
		assertFalse(piles[1].isVide());
	}
	
	/**
	 * Méthode de test de
	 * {@link iut.info1.sae.algorithmiquegestion.piles.Pile#empiler()}.
	 */
	@Test
	@DisplayName("Test de la méthode empiler() de la classe Pile")
	void testEmpiler() {
		assertThrows(NullPointerException.class,
					 () -> piles[0].empiler(null));
		
		Pile grandePile = new Pile();
		for (int valeur = 1; valeur < 50000; valeur++) {
			final Integer nombre = Integer.valueOf(valeur);
			assertDoesNotThrow(() -> grandePile.empiler(nombre));
		}
	}
	
	/**
	 * Méthode de test de
	 * {@link iut.info1.sae.algorithmiquegestion.piles.Pile#depiler()}.
	 */
	@Test
	@DisplayName("Test de la méthode depiler() de la classe Pile")
	void testDepiler() {
		assertThrows(PileVideException.class, () -> pileVide.depiler());
		
		for (int valeur = 50000; valeur >= 1; valeur--) {
			assertEquals(Integer.valueOf(valeur), piles[1].sommet());
			piles[1].depiler();
		}
		assertTrue(piles[1].isVide());
	}
	
	/**
	 * Méthode de test de
	 * {@link iut.info1.sae.algorithmiquegestion.piles.Pile#sommet()}.
	 */
	@Test
	@DisplayName("Test de la méthode sommet() de la classe Pile")
	void testSommet() {
		assertThrows(PileVideException.class, () -> pileVide.sommet());
		assertEquals(Integer.valueOf(-3), piles[0].sommet());
	}
}
