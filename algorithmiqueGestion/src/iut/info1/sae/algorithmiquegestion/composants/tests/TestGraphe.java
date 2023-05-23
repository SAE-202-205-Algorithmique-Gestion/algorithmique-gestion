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
		
//		Graphe grapheTeste2 = new Graphe(5, 5);
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
//		for (int i = 0; i < grapheTeste.sommetsDeMemeMarque(marque).length; i++) {
//			
//			System.out.println("Sommets : "
//					+ "X : " + grapheTeste.sommetsDeMemeMarque(marque)[i].getCoordonneeX()
//					+ "  Y : " + grapheTeste.sommetsDeMemeMarque(marque)[i].getCoordonneeY()
//					+ "\tMarques : " + grapheTeste.sommetsDeMemeMarque(marque)[i].getMarque());
//		}
		
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
	    System.out.println("Test d'affectation de marque :"
	    		+ "Attention au setMarque de la méthode précédente");
	    /* Réinitialisation des marques */
	    for (int i = 0; i < grapheTeste.getListeSommets().length; i++) {
	    	grapheTeste.getListeSommets()[i].setMarque(-1);
	    }
	    
	    
	    /* Test du 'if' */
	    grapheTeste.definitUneMarque(grapheTeste.getListeSommets()[0],
	    							 grapheTeste.getListeSommets()[1]);
//	    System.out.println(grapheTeste.getListeSommets()[0].getMarque());
	    assertEquals(grapheTeste.getListeSommets()[0].getMarque(), 0);
	    assertEquals(grapheTeste.getListeSommets()[1].getMarque(), 0);
	    
	    
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
	
}

