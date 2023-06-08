/*
 * TestGraphe.java													 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import iut.info1.sae.algorithmiquegestion.composants.*;

/**
 * Tests unitaires avec JUnit de la classe Graphe.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
@TestInstance(Lifecycle.PER_CLASS)
class TestGraphe {
	
	Graphe grapheTeste = new Graphe(5, 5);
	
	Sommet[] sommetsGraphe = new Sommet[25];
	
	/**
	 * Redéfinit les coordonnées et les marques
	 * initiales de tout les sommets du graphe grapheTeste.
	 */
	@BeforeEach
	void resetDeGrapheTeste() {
		for (int i = 0; i < grapheTeste.getNombreSommets(); i++) {
			grapheTeste.getListeSommets()[i] = grapheTeste.creerSommet(i);
		}
	}
	
	
	
	
	/**
     * Méthode de test qui récupère les coordonnées  X et Y
     * de différents sommets
     */
	@Test
	void testRecuperationCoordonnees() {
		System.out.println("Test récupération coordonnées"
		            + " pour un labyrinthe/graphe 5*5 :");
		
		assertEquals(grapheTeste.getListeSommets()[5].getCoordonneeX(), 0);
		assertEquals(grapheTeste.getListeSommets()[5].getCoordonneeY(), 1);
		
		assertEquals(grapheTeste.getListeSommets()[3].getCoordonneeX(), 3);
		assertEquals(grapheTeste.getListeSommets()[3].getCoordonneeY(), 0);
		
		assertEquals(grapheTeste.getListeSommets()[10].getCoordonneeX(), 0);
		assertEquals(grapheTeste.getListeSommets()[10].getCoordonneeY(), 2);
		
		assertEquals(grapheTeste.getListeSommets()[22].getCoordonneeX(), 2);
		assertEquals(grapheTeste.getListeSommets()[22].getCoordonneeY(), 4);
	}
	
   /**
     * Méthode de test qui r
     */
	@Test
	void testEstAdjacent() {
		System.out.println("Test adjacence pour un labyrinthe/graphe 5*5 :");
		                                                                                   						 // sommet 2 par rapport au sommet 1
		assertTrue(grapheTeste.estAdjacent(0, 1)); // x + 1 et y similaire
		assertTrue(grapheTeste.estAdjacent(4, 3)); // x - 1 et y similaire
		assertTrue(grapheTeste.estAdjacent(0, 5)); // x similaire et y + 1
		assertTrue(grapheTeste.estAdjacent(7, 2)); // x similaire et y - 1
		
		assertFalse(grapheTeste.estAdjacent(2, 4)); // x + 2 et y similaire
		assertFalse(grapheTeste.estAdjacent(3, 0)); // x - 3 et y similaire
		assertFalse(grapheTeste.estAdjacent(1, 11)); // x similaire et y + 2
		assertFalse(grapheTeste.estAdjacent(14, 4)); // x similaire et y - 2
		
		//test de la méthode pour des sommets placer en diagonales d'un autre sommet
		assertFalse(grapheTeste.estAdjacent(7, 1)); // x - 1 et y - 1 
		assertFalse(grapheTeste.estAdjacent(7, 3)); // x + 1 et y - 1
		assertFalse(grapheTeste.estAdjacent(7, 11)); // x - 1 et y + 1
		assertFalse(grapheTeste.estAdjacent(7, 13)); // x + 1 et y + 1
		
	}
	
	
	
	
	
	
	/**
	 * test de la classe sommetExiste
	 */
	@Test
	void testSommetExiste() {
		System.out.println("Test d'existence du sommet dans le graphe : ");
//		Graphe grapheTeste2 = new Graphe(5, 5);
		
		assertFalse(grapheTeste.sommetExiste(-2));
		assertFalse(grapheTeste.sommetExiste(-1));
		assertFalse(grapheTeste.sommetExiste(grapheTeste.getNombreSommets()));
		assertFalse(grapheTeste.sommetExiste(grapheTeste.getNombreSommets() + 1));
		
		assertTrue(grapheTeste.sommetExiste(0));
		assertTrue(grapheTeste.sommetExiste(1));
		assertTrue(grapheTeste.sommetExiste(2));
		assertTrue(grapheTeste.sommetExiste(grapheTeste.getNombreSommets() / 2));
		assertTrue(grapheTeste.sommetExiste(grapheTeste.getNombreSommets() - 1));		
	}
	
	@Test
	void testTousLesSommetsAdjacentsDuSommet() {
		System.out.println("Test récuperation des sommets adjacent d'un sommet donné : ");
		
		for (int i = 0;
			 i < grapheTeste.tousLesSommetsAdjacentsDuSommet(6).length;
			 i++) {
			
			System.out.println("Sommet adjacent : X = "
					+ grapheTeste.tousLesSommetsAdjacentsDuSommet(6)[i].getCoordonneeX()
					+ " ; Y = "
					+ grapheTeste.tousLesSommetsAdjacentsDuSommet(6)[i].getCoordonneeY());
		}
		//Premier Test
		Sommet[] listeAttendu = {
				grapheTeste.getListeSommets()[11],
				grapheTeste.getListeSommets()[1],
				grapheTeste.getListeSommets()[7],
				grapheTeste.getListeSommets()[5]
			};
		
		assertEquals(listeAttendu[0].getCoordonneeX(), grapheTeste.tousLesSommetsAdjacentsDuSommet(6)[0].getCoordonneeX());
		assertEquals(listeAttendu[0].getCoordonneeY(), grapheTeste.tousLesSommetsAdjacentsDuSommet(6)[0].getCoordonneeY());
		assertEquals(listeAttendu.length, grapheTeste.tousLesSommetsAdjacentsDuSommet(6).length);
		
		//Second Test
		Sommet[] listeAttendu2 = {
				grapheTeste.getListeSommets()[19],
				grapheTeste.getListeSommets()[23]
			};
		
		assertEquals(listeAttendu2[0].getCoordonneeX(), grapheTeste.tousLesSommetsAdjacentsDuSommet(24)[0].getCoordonneeX());
		assertEquals(listeAttendu2[0].getCoordonneeY(), grapheTeste.tousLesSommetsAdjacentsDuSommet(24)[0].getCoordonneeY());
		assertEquals(listeAttendu2.length, grapheTeste.tousLesSommetsAdjacentsDuSommet(24).length);

		//Troisième Test
		Sommet[] listeAttendu3 = {
			grapheTeste.getListeSommets()[6],
			grapheTeste.getListeSommets()[2],
			grapheTeste.getListeSommets()[0]
		};
		
		assertEquals(listeAttendu3[2].getCoordonneeX(), grapheTeste.tousLesSommetsAdjacentsDuSommet(1)[2].getCoordonneeX());
		assertEquals(listeAttendu3[2].getCoordonneeY(), grapheTeste.tousLesSommetsAdjacentsDuSommet(1)[2].getCoordonneeY());
		assertEquals(listeAttendu3.length, grapheTeste.tousLesSommetsAdjacentsDuSommet(1).length);
		
		/* l'assertArrayEquals fonctionne seulement si on ne met pas en commentaire l'Override de equals dans la classe Sommet */
		assertArrayEquals(listeAttendu, grapheTeste.tousLesSommetsAdjacentsDuSommet(6));
		assertArrayEquals(listeAttendu2, grapheTeste.tousLesSommetsAdjacentsDuSommet(24));
		assertArrayEquals(listeAttendu3, grapheTeste.tousLesSommetsAdjacentsDuSommet(1));

	}
	
	
	
}

