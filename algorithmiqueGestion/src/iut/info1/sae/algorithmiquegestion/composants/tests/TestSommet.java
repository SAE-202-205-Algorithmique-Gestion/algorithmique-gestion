/**
 * TestSommet.java													 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import iut.info1.sae.algorithmiquegestion.composants.Graphe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * Classe de tests de la classe Sommet
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
@TestInstance(Lifecycle.PER_CLASS)
class TestSommet {
	
	/**
	 * TODO : faire la javadoc
	 */
	@BeforeAll
	void debutTest() {
     	System.out.println("---  DÉBUT TESTS UNITAIRES DES SOMMETS ---\n");
	}
	
	/**
	 * TODO : faire la javadoc
	 */
	@AfterAll
	void finTest() {
		System.out.println("\n---  FIN TESTS UNITAIRES DES SOMMETS ---\n");
    } 
	
	/**
	 * Méthode de test de la méthode sontAdjacents(Sommet, Sommet)
	 */
	@Test
	void testSontAdjacents() {
		System.out.println("Test adjacence pour un labyrinthe/graphe 5*5 :");
		
		Graphe grapheDeTest = new Graphe(5, 5);
		
		Sommet[] listeSommets = grapheDeTest.getListeSommets();
		                                                               // sommet 2 par rapport au sommet 1
		assertTrue(Sommet.sontAdjacents(listeSommets[0], listeSommets[1])); // x + 1 et y similaire
		assertTrue(Sommet.sontAdjacents(listeSommets[4], listeSommets[3])); // x - 1 et y similaire
		assertTrue(Sommet.sontAdjacents(listeSommets[0], listeSommets[5])); // x similaire et y + 1
		assertTrue(Sommet.sontAdjacents(listeSommets[7], listeSommets[2])); // x similaire et y - 1
		
		assertFalse(Sommet.sontAdjacents(listeSommets[2], listeSommets[4])); // x + 2 et y similaire
		assertFalse(Sommet.sontAdjacents(listeSommets[3], listeSommets[0])); // x - 3 et y similaire
		assertFalse(Sommet.sontAdjacents(listeSommets[1], listeSommets[11])); // x similaire et y + 2
		assertFalse(Sommet.sontAdjacents(listeSommets[14], listeSommets[4])); // x similaire et y - 2
		
		// Test de la méthode pour des sommets placés en diagonales d'un autre sommet
		assertFalse(Sommet.sontAdjacents(listeSommets[7], listeSommets[1])); // x - 1 et y - 1 
		assertFalse(Sommet.sontAdjacents(listeSommets[7], listeSommets[3])); // x + 1 et y - 1
		assertFalse(Sommet.sontAdjacents(listeSommets[7], listeSommets[11])); // x - 1 et y + 1
		assertFalse(Sommet.sontAdjacents(listeSommets[7], listeSommets[13])); // x + 1 et y + 1
	}
	
	/**
	 * Méthode de test de la méthode creerLiaison(Sommet)
	 */
	@Test
	@DisplayName("Test de la méthode creerLiaison(Sommet)")
    void testCreerLiaison() {
		Sommet[][] ensembleSommetsALier = {
		    {new Sommet(0, 0), new Sommet(0, 1)},
		    {new Sommet(2, 0), new Sommet(3, 0)},
		    {new Sommet(0, 3), new Sommet(0, 4)},
	        {new Sommet(3, 0), new Sommet(4, 0)},
	        {new Sommet(2, 2), new Sommet(2, 3)}
		};
		
		for (Sommet[] listeSommets : ensembleSommetsALier) {
			Sommet sommet1 = listeSommets[0];
	        Sommet sommet2 = listeSommets[1];
	        
	        /* Création de la liaison entre les sommets */
	        sommet1.creerLiaison(sommet2);
	        
	        assertTrue(sommet1.liaisonExiste(sommet2));
		}
    }
	
	/**
	 * Méthode de test de la méthode liaisonExiste(Sommet)
	 */
	@Test
	@DisplayName("Test de la méthode liaisonExiste(Sommet)")
    void testLiaisonExiste() {
		Sommet[][] ensembleSommetsALier = {
		    {new Sommet(5, 5), new Sommet(5, 6)},
		    {new Sommet(6, 0), new Sommet(7, 0)},
		    {new Sommet(10, 3), new Sommet(10, 4)},
	        {new Sommet(3, 5), new Sommet(4, 5)},
	        {new Sommet(2, 2), new Sommet(2, 3)}
		};
		
		for (Sommet[] listeSommets : ensembleSommetsALier) {
			Sommet sommet1 = listeSommets[0];
	        Sommet sommet2 = listeSommets[1];
	        
	        assertFalse(sommet1.liaisonExiste(sommet2));
	        
	        /* Création de la liaison entre les sommets */
	        sommet1.creerLiaison(sommet2);
	        
	        assertTrue(sommet1.liaisonExiste(sommet2));
		}
    }
	
	/**
	 * Méthode de test de la méthode getLiaisons()
	 */
	@Test
	@DisplayName("Test de la méthode getLiaisons()")
    void testGetLiaisons() {
		// TODO : faire le test
    }
	
	/**
	 * Méthode de test de la méthode getCoordonneeX()
	 */
	@Test
	@DisplayName("Test de la méthode getCoordonneeX()")
    void testGetCoordonneeX() {
		// TODO : faire le test
    }
	
	/**
	 * Méthode de test de la méthode getCoordonneeY()
	 */
	@Test
	@DisplayName("Test de la méthode getCoordonneeY()")
    void testGetCoordonneeY() {
		// TODO : faire le test
    }
	
	/**
	 * Méthode de test de la méthode setCoordonneeX()
	 */
	@Test
	@DisplayName("Test de la méthode setCoordonneeX()")
    void testSetCoordonneeX() {
		// TODO : faire le test
    }
	
	/**
	 * Méthode de test de la méthode setCoordonneeY()
	 */
	@Test
	@DisplayName("Test de la méthode setCoordonneeY()")
    void testSetCoordonneeY() {
		// TODO : faire le test
    }
	
	/**
	 * Méthode de test de la méthode getMarque()
	 */
	@Test
	@DisplayName("Test de la méthode getMarque()")
    void testGetMarque() {
		// TODO : faire le test
    }
	
	/**
	 * Méthode de test de la méthode setMarque()
	 */
	@Test
	@DisplayName("Test de la méthode setMarque()")
    void testSetMarque() {
		// TODO : faire le test
    }

}
