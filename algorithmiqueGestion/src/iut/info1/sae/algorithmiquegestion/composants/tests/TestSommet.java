/**
 * TestSommet.java													 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * TODO : javadoc
 * @author Jonathan GUIL
 */
@TestInstance(Lifecycle.PER_CLASS)
class TestSommet {
	
	private Sommet[] listeSommetsATester = {
		/*
		new Sommet("x1"),  // x1
		new Sommet("x2"),  // x2
		new Sommet("x3"),  // x3
		new Sommet("x4"),  // x4
		new Sommet("x5"),  // x5
		*/
	};
	
	void ajoutLiaisons() {
		//  x1  --  x2
		this.listeSommetsATester[0].creerLiaison(this.listeSommetsATester[1]);
	}
	
	
	@BeforeAll
	void debutTest() throws Exception {
		System.out.println("---  DÉBUT TEST UNITAIRE DES SOMMETS ---\n");
		
		this.ajoutLiaisons();
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		//
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@AfterAll
	void finTest() {
		System.out.println("---  FIN TEST UNITAIRE DES SOMMETS ---\n");
	}
	
	

	@Test
	void test() {
		/*
		assertEquals(this.listeSommetsATester[0].getLiaisons(), new Sommet[] { this.listeSommetsATester[1] });
		assertEquals(this.listeSommetsATester[1].getLiaisons(), new Sommet[] { this.listeSommetsATester[0] });
		*/
		
		assertTrue(this.listeSommetsATester[0].liaisonExiste(this.listeSommetsATester[1]));
		assertTrue(this.listeSommetsATester[1].liaisonExiste(this.listeSommetsATester[0]));
	}

}
