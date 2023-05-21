/**
 * TestGraphe.java													 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import iut.info1.sae.algorithmiquegestion.composants.*;

/**
 * TODO : javadoc
 * @author Jonathan GUIL, Samuel Lacam, Simon Guiraud, Loic Faugières, Tom Douaud
 */
@TestInstance(Lifecycle.PER_CLASS)
class TestGraphe {
	
	Graphe grapheTeste = new Graphe();
	
	Sommet[] sommetsGraphe = new Sommet[25];

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	void initialisationTest() throws Exception {
		this.sommetsGraphe[0] = grapheTeste.creerSommet();		//  ( 0 ; 0 )
		this.sommetsGraphe[1] = grapheTeste.creerSommet();		//  ( 1 ; 0 )
		this.sommetsGraphe[2] = grapheTeste.creerSommet();		//  ( 2 ; 0 )
		this.sommetsGraphe[3] = grapheTeste.creerSommet();		//  ( 3 ; 0 )
		this.sommetsGraphe[4] = grapheTeste.creerSommet();		//  ( 4 ; 0 )
		
		this.sommetsGraphe[5] = grapheTeste.creerSommet();		//  ( 0 ; 1 )
		this.sommetsGraphe[6] = grapheTeste.creerSommet();		//  ( 1 ; 1 )
		this.sommetsGraphe[7] = grapheTeste.creerSommet();		//  ( 2 ; 1 )
		this.sommetsGraphe[8] = grapheTeste.creerSommet();		//  ( 3 ; 1 )
		this.sommetsGraphe[9] = grapheTeste.creerSommet();		//  ( 4 ; 1 )
		
		this.sommetsGraphe[10] = grapheTeste.creerSommet();		//  ( 0 ; 2 )
		this.sommetsGraphe[11] = grapheTeste.creerSommet();		//  ( 1 ; 2 )
		this.sommetsGraphe[12] = grapheTeste.creerSommet();		//  ( 2 ; 2 )
		this.sommetsGraphe[13] = grapheTeste.creerSommet();		//  ( 3 ; 2 )		
		this.sommetsGraphe[14] = grapheTeste.creerSommet();		//  ( 4 ; 2 )		
		
		this.sommetsGraphe[15] = grapheTeste.creerSommet();		//  ( 0 ; 3 )		
		this.sommetsGraphe[16] = grapheTeste.creerSommet();		//  ( 1 ; 3 )		
		this.sommetsGraphe[17] = grapheTeste.creerSommet();		//  ( 2 ; 3 )		
		this.sommetsGraphe[18] = grapheTeste.creerSommet();		//  ( 3 ; 3 )		
		this.sommetsGraphe[19] = grapheTeste.creerSommet();		//  ( 4 ; 3 )		
		
		this.sommetsGraphe[20] = grapheTeste.creerSommet();		//  ( 0 ; 4 )		
		this.sommetsGraphe[21] = grapheTeste.creerSommet();		//  ( 1 ; 4 )		
		this.sommetsGraphe[22] = grapheTeste.creerSommet();		//  ( 2 ; 4 )		
		this.sommetsGraphe[23] = grapheTeste.creerSommet();		//  ( 3 ; 4 )		
		this.sommetsGraphe[24] = grapheTeste.creerSommet();		//  ( 4 ; 4 )		
	}

	@Test
	void testRecuperationCoordonnees() {
		System.out.println("Test récupération coordonnées"
		            + " pour un labyrinthe/graphe 5*5 :");
		
		// la boucle ne fonctionne pas car pour les this.sommetsGraphe[5] et supérieur,
		// leur coordonnée x ne sera pas 6, 7, 8 etc.. car c'est un labyrinthe/graphe 5*5
//		for (int x = 0; 
//			 x < this.sommetsGraphe.length; 
//			 x++) {
//			
//			//assertEquals(this.sommetsGraphe[x].getCoordonneeX(), x);
//			
//		}
		assertEquals(this.sommetsGraphe[5].getCoordonneeX(), 0);
	}
	
	@Test
	void testEstAdjacent() {
		System.out.println("Test adjacence pour un labyrinthe/graphe 5*5 :");
		                                                                                   // sommet 2 par rapport au sommet 1
		assertTrue(grapheTeste.estAdjacent(this.sommetsGraphe[0], this.sommetsGraphe[1])); // x + 1 et y similaire
		assertTrue(grapheTeste.estAdjacent(this.sommetsGraphe[4], this.sommetsGraphe[3])); // x - 1 et y similaire
		assertTrue(grapheTeste.estAdjacent(this.sommetsGraphe[0], this.sommetsGraphe[5])); // x similaire et y + 1
		assertTrue(grapheTeste.estAdjacent(this.sommetsGraphe[7], this.sommetsGraphe[2])); // x similaire et y - 1
		
		assertFalse(grapheTeste.estAdjacent(this.sommetsGraphe[2], this.sommetsGraphe[4])); // x + 2 et y similaire
		assertFalse(grapheTeste.estAdjacent(this.sommetsGraphe[3], this.sommetsGraphe[0])); // x - 3 et y similaire
		assertFalse(grapheTeste.estAdjacent(this.sommetsGraphe[1], this.sommetsGraphe[11])); // x similaire et y + 2
		assertFalse(grapheTeste.estAdjacent(this.sommetsGraphe[14], this.sommetsGraphe[4])); // x similaire et y - 2
		
		//test de la méthode pour des sommets placer en diagonales d'un autre sommet
		assertFalse(grapheTeste.estAdjacent(this.sommetsGraphe[7], this.sommetsGraphe[1])); // x - 1 et y - 1 
		assertFalse(grapheTeste.estAdjacent(this.sommetsGraphe[7], this.sommetsGraphe[3])); // x + 1 et y - 1
		assertFalse(grapheTeste.estAdjacent(this.sommetsGraphe[7], this.sommetsGraphe[11])); // x - 1 et y + 1
		assertFalse(grapheTeste.estAdjacent(this.sommetsGraphe[7], this.sommetsGraphe[13])); // x + 1 et y + 1
		
	}
	
	//TODO faire un test de la méthode definitUneMarque
	@Test
	void testDefinitUneMarque() {
	    System.out.println("Test d'affectation de marque : ");
	    
	    
	}
}

