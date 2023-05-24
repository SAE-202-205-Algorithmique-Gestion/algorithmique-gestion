/**
 * TestGraphe.java													 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import iut.info1.sae.algorithmiquegestion.composants.*;

/**
 * TODO : javadoc
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
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	void initialisationTest() throws Exception {
//		0	//  ( 0 ; 0 )
//		1	//  ( 1 ; 0 )
//		2	//  ( 2 ; 0 )
//		3	//  ( 3 ; 0 )
//		4	//  ( 4 ; 0 )
//		
//		5	//  ( 0 ; 1 )
//		6	//  ( 1 ; 1 )
//		7	//  ( 2 ; 1 )
//		8	//  ( 3 ; 1 )
//		9	//  ( 4 ; 1 )
//		
//		10	//  ( 0 ; 2 )
//		11	//  ( 1 ; 2 )
//		12	//  ( 2 ; 2 )
//		13	//  ( 3 ; 2 )		
//		14	//  ( 4 ; 2 )		
//		
//		15	//  ( 0 ; 3 )		
//		16	//  ( 1 ; 3 )		
//		17	//  ( 2 ; 3 )		
//		18	//  ( 3 ; 3 )		
//		19	//  ( 4 ; 3 )		
//		
//		20	//  ( 0 ; 4 )		
//		21	//  ( 1 ; 4 )		
//		22	//  ( 2 ; 4 )		
//		23	//  ( 3 ; 4 )		
//		24	//  ( 4 ; 4 )
		
	}
	/**
	 * Méthode qui redéfinit les coordonnées et les marques
	 * initiales de tout les sommets du graphe grapheTeste.
	 */
	@BeforeEach
	void resetDeGrapheTeste() {
		for (int i = 0; i < grapheTeste.getNombreSommets(); i++) {
			grapheTeste.getListeSommets()[i] = grapheTeste.determinationCoordonnees(i);
		}
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
		assertEquals(grapheTeste.getListeSommets()[5].getCoordonneeX(), 0);
		assertEquals(grapheTeste.getListeSommets()[5].getCoordonneeY(), 1);
		
		assertEquals(grapheTeste.getListeSommets()[3].getCoordonneeX(), 3);
		assertEquals(grapheTeste.getListeSommets()[3].getCoordonneeY(), 0);
		
		assertEquals(grapheTeste.getListeSommets()[10].getCoordonneeX(), 0);
		assertEquals(grapheTeste.getListeSommets()[10].getCoordonneeY(), 2);
		
		assertEquals(grapheTeste.getListeSommets()[22].getCoordonneeX(), 2);
		assertEquals(grapheTeste.getListeSommets()[22].getCoordonneeY(), 4);
	}
	
	@Test
	void testEstAdjacent() {
		System.out.println("Test adjacence pour un labyrinthe/graphe 5*5 :");
		                                                                                   						 // sommet 2 par rapport au sommet 1
		assertTrue(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[0], grapheTeste.getListeSommets()[1])); // x + 1 et y similaire
		assertTrue(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[4], grapheTeste.getListeSommets()[3])); // x - 1 et y similaire
		assertTrue(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[0], grapheTeste.getListeSommets()[5])); // x similaire et y + 1
		assertTrue(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[7], grapheTeste.getListeSommets()[2])); // x similaire et y - 1
		
		assertFalse(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[2], grapheTeste.getListeSommets()[4])); // x + 2 et y similaire
		assertFalse(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[3], grapheTeste.getListeSommets()[0])); // x - 3 et y similaire
		assertFalse(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[1], grapheTeste.getListeSommets()[11])); // x similaire et y + 2
		assertFalse(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[14], grapheTeste.getListeSommets()[4])); // x similaire et y - 2
		
		//test de la méthode pour des sommets placer en diagonales d'un autre sommet
		assertFalse(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[7], grapheTeste.getListeSommets()[1])); // x - 1 et y - 1 
		assertFalse(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[7], grapheTeste.getListeSommets()[3])); // x + 1 et y - 1
		assertFalse(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[7], grapheTeste.getListeSommets()[11])); // x - 1 et y + 1
		assertFalse(grapheTeste.estAdjacent(grapheTeste.getListeSommets()[7], grapheTeste.getListeSommets()[13])); // x + 1 et y + 1
		
	}
	
	@Test
	void testMarqueDifferente() {
		//les marques on étai réinitialisé par le BeforeEach elle vaut -1 pour tout les sommets
		assertFalse(grapheTeste.marqueDifferente(
				grapheTeste.getListeSommets()[0], grapheTeste.getListeSommets()[1]));
		
		grapheTeste.getListeSommets()[5].setMarque(2);
		grapheTeste.getListeSommets()[6].setMarque(5);
		assertTrue(grapheTeste.marqueDifferente(
				grapheTeste.getListeSommets()[5], grapheTeste.getListeSommets()[6]));
	}
	
	// TODO faire la javadoc
	@Test
	void testSommetsDeMemeMarque() {
		System.out.println("Test de sommetsDeMemeMarque : ");
		
		final int premierIndex = 2;
		final int dernierIndex = 8;
		final int marque = 3;
		int rang = 0;
		Sommet[] sommetAttendus1 = new Sommet[dernierIndex - premierIndex + 1];
		for (int i = premierIndex; i <= dernierIndex; i++) {
			grapheTeste.getListeSommets()[i].setMarque(marque);
			sommetAttendus1[rang] = grapheTeste.getListeSommets()[i];
			rang++;
		}
		
		assertArrayEquals(grapheTeste.sommetsDeMemeMarque(marque), sommetAttendus1);
		for (int i = 0; i < grapheTeste.sommetsDeMemeMarque(marque).length; i++) {
			
			System.out.println("Sommets : "
					+ "X : " + grapheTeste.sommetsDeMemeMarque(marque)[i].getCoordonneeX()
					+ "  Y : " + grapheTeste.sommetsDeMemeMarque(marque)[i].getCoordonneeY()
					+ "\tMarques : " + grapheTeste.sommetsDeMemeMarque(marque)[i].getMarque());
		}
		
		grapheTeste.getListeSommets()[8].setMarque(4);
		grapheTeste.getListeSommets()[12].setMarque(4);
		grapheTeste.getListeSommets()[19].setMarque(4);
		
		Sommet[] sommetsAttendus2 = {
			    grapheTeste.getListeSommets()[8],
			    grapheTeste.getListeSommets()[12],
			    grapheTeste.getListeSommets()[19]
			};
		
		assertArrayEquals(grapheTeste.sommetsDeMemeMarque(4), sommetsAttendus2);
	}
	
	//TODO faire un test de la méthode definitUneMarque
	//TODO faire la javadoc
	@Test
	void testDefinitUneMarque() {
	    System.out.println("Test d'affectation de marque : ");
	    
	    /* Test du 'if' */
	    grapheTeste.definitUneMarque(grapheTeste.getListeSommets()[0],
	    							 grapheTeste.getListeSommets()[1]);
//	    System.out.println(grapheTeste.getListeSommets()[0].getMarque());
	    
	    /* Les test du if dépende des nombres aléatoire, on ne peut pas
	     * vérifier les test si on appelle la méthode creationDuGraphe
	     * Dans le constructeur. */
//	    assertEquals(grapheTeste.getListeSommets()[0].getMarque(), 0);
//	    assertEquals(grapheTeste.getListeSommets()[1].getMarque(), 0);
	    
	    
	    /* Test du 'if' dans le 'else if' */
	    grapheTeste.getListeSommets()[6].setMarque(8);
	    grapheTeste.definitUneMarque(grapheTeste.getListeSommets()[5],
	    						     grapheTeste.getListeSommets()[6]);
	    
	    assertEquals(grapheTeste.getListeSommets()[5].getMarque(), 8);
	    
	    
	    /* Test du 'else if' dans le 'else if' */
	    grapheTeste.getListeSommets()[15].setMarque(7);
	    grapheTeste.definitUneMarque(grapheTeste.getListeSommets()[15],
	    						     grapheTeste.getListeSommets()[16]);
	    
	    assertEquals(grapheTeste.getListeSommets()[16].getMarque(), 7);
	    
	    
	    /* Test du else */
	    grapheTeste.getListeSommets()[16].setMarque(9);
	    grapheTeste.getListeSommets()[17].setMarque(2);
	    grapheTeste.getListeSommets()[18].setMarque(2);
	    grapheTeste.getListeSommets()[19].setMarque(2);
	    grapheTeste.definitUneMarque(grapheTeste.getListeSommets()[16],
	    							 grapheTeste.getListeSommets()[17]);
	    
	    assertEquals(grapheTeste.getListeSommets()[17].getMarque(), 9);
	    assertEquals(grapheTeste.getListeSommets()[18].getMarque(), 9);
	    assertEquals(grapheTeste.getListeSommets()[19].getMarque(), 9);
	    
	    
	    /* Test du cas où les deux sommets on la meme marque déjà définie */
	    grapheTeste.getListeSommets()[15].setMarque(7);
	    grapheTeste.getListeSommets()[16].setMarque(7);
	    grapheTeste.definitUneMarque(grapheTeste.getListeSommets()[15],
			     					 grapheTeste.getListeSommets()[16]);
	    
	    assertEquals(grapheTeste.getListeSommets()[15].getMarque(), 7);
	    assertEquals(grapheTeste.getListeSommets()[16].getMarque(), 7);
	}
	/**
	 * test de la classe sommetExiste
	 */
	@Test
	void testSommetExiste() {
//		Graphe grapheTeste2 = new Graphe(5, 5);
		
		//AssertFalse
		grapheTeste.getListeSommets()[0].setCoordonneeX(-1);
		assertFalse(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[0]));
		
		grapheTeste.getListeSommets()[1].setCoordonneeY(-1);
		assertFalse(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[1]));
		
		grapheTeste.getListeSommets()[2].setCoordonneeX(grapheTeste.getNombreColonnesLabyrinthe());
		assertFalse(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[2]));
		
		grapheTeste.getListeSommets()[3].setCoordonneeY(grapheTeste.getNombreLignesLabyrinthe());
		assertFalse(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[3]));
		
		//AssertTrue
		grapheTeste.getListeSommets()[4].setCoordonneeX(0);
		assertTrue(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[4]));
		
		grapheTeste.getListeSommets()[5].setCoordonneeY(0);
		assertTrue(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[5]));
		
		grapheTeste.getListeSommets()[6].setCoordonneeX(grapheTeste.getNombreColonnesLabyrinthe() - 1);
		assertTrue(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[6]));
		
		grapheTeste.getListeSommets()[7].setCoordonneeY(grapheTeste.getNombreLignesLabyrinthe() - 1);
		assertTrue(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[7]));
		
		
		grapheTeste.getListeSommets()[8].setCoordonneeY(3);
		assertTrue(grapheTeste.sommetExiste(grapheTeste.getListeSommets()[8]));
		
	}
	
	@Test
	void testTousLesSommetsAdjacentsDuSommet() {
		System.out.println("Test récuperation des sommets adjacent d'un sommet donné : ");
		
		for (int i = 0;
			 i < grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[6]).length;
			 i++) {
			
			System.out.println("Sommet adjacent : X = "
					+ grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[6])[i].getCoordonneeX()
					+ " ; Y = "
					+ grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[6])[i].getCoordonneeY());
		}
		//Premier Test
		Sommet[] listeAttendu = {
				grapheTeste.getListeSommets()[11],
				grapheTeste.getListeSommets()[1],
				grapheTeste.getListeSommets()[7],
				grapheTeste.getListeSommets()[5]
			};
		
		assertEquals(listeAttendu[0].getCoordonneeX(), grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[6])[0].getCoordonneeX());
		assertEquals(listeAttendu[0].getCoordonneeY(), grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[6])[0].getCoordonneeY());
		assertEquals(listeAttendu.length, grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[6]).length);
		
		//Second Test
		Sommet[] listeAttendu2 = {
				grapheTeste.getListeSommets()[19],
				grapheTeste.getListeSommets()[23]
			};
		
		assertEquals(listeAttendu2[0].getCoordonneeX(), grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[24])[0].getCoordonneeX());
		assertEquals(listeAttendu2[0].getCoordonneeY(), grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[24])[0].getCoordonneeY());
		assertEquals(listeAttendu2.length, grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[24]).length);

		//Troisième Test
		Sommet[] listeAttendu3 = {
			grapheTeste.getListeSommets()[6],
			grapheTeste.getListeSommets()[2],
			grapheTeste.getListeSommets()[0]
		};
		
		assertEquals(listeAttendu3[2].getCoordonneeX(), grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[1])[2].getCoordonneeX());
		assertEquals(listeAttendu3[2].getCoordonneeY(), grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[1])[2].getCoordonneeY());
		assertEquals(listeAttendu3.length, grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[1]).length);
		
		/* l'assertArrayEquals fonctionne seulement si on ne met pas en commentaire l'Owerride de equals dans la classe Sommet */
//		assertArrayEquals(listeAttendu, grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[6]));
//		assertArrayEquals(listeAttendu2, grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[24]));
//		assertArrayEquals(listeAttendu3, grapheTeste.tousLesSommetsAdjacentsDuSommet(grapheTeste.getListeSommets()[1]));

	}
	
	@Test
	void testSommetAleatoires() {
		System.out.println("Test de renvoie tableau contenant un sommet aléatoire " 
				+ "ainsi qu'un de ses sommets adjacent : ");
//		for (int i = 0; i < grapheTeste.sommetsAleatoires().length; i++) {
		Sommet[] lesSommets= new Sommet[2];
		lesSommets = grapheTeste.sommetsAleatoires();
		
		System.out.println("Sommet choisi au hasard : X = "
				+ lesSommets[0].getCoordonneeX()
				+ " ; Y = "
				+ lesSommets[0].getCoordonneeY()
				+ "\nl'un de ses sommet adjacent : X = "
				+ lesSommets[1].getCoordonneeX()
				+ " ; Y = "
				+ lesSommets[1].getCoordonneeY());
			
//		}
	}
	@AfterAll
	void testCreationDuGraphe() {
		System.out.println("Test de la création du graphe : ");
		Graphe graphe2 = new Graphe(2, 2);
//		graphe2.creationDuGraphe();
		graphe2.getListeSommets()[0].creerLiaison(graphe2.getListeSommets()[1]);
		graphe2.getListeSommets()[1].creerLiaison(graphe2.getListeSommets()[3]);
		graphe2.getListeSommets()[3].creerLiaison(graphe2.getListeSommets()[2]);
		
		for (int indexSommet = 0; indexSommet < graphe2.getNombreSommets(); indexSommet++) {
			System.out.println("Sommet : " + graphe2.getListeSommets()[indexSommet]);
			for (int i = 0; i < graphe2.getListeSommets()[indexSommet].getLiaisons().length; i++) {
				System.out.println("Sommet liai : " + graphe2.getListeSommets()[indexSommet].getLiaisons()[i]);
			}
			
		}
	}
}

