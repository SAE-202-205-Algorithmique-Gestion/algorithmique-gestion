/*
 * TestPile.java											  		 12 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */

package iut.info1.sae.algorithmiquegestion.piles.tests;

import iut.info1.sae.algorithmiquegestion.piles.Pile;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @author Simon Guiraud
 * @author Loïc Faugières
 */
public class TestPile {
	
	/**
	 * Méthode de test du constructeur de la classe Pile
	 */
	@Test
	void testPile() {
		Pile pileTest = new Pile();
		
		assertTrue(pileTest.isVide());
	}
	
	/**
	 * Méthode de test de la méthode getContenu()
	 */
	@Test
	void testGetContenu() {
		Pile pileTest = new Pile(5);
		
		Object[] objetAttendu = new Object[5];
		
		assertEquals(objetAttendu, pileTest.getContenu());
	}
	
}
