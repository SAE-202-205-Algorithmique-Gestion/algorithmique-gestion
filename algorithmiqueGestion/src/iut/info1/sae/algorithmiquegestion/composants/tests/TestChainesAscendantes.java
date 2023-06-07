/*
 * TestChainesAscendantes.java                                       5 juin 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import iut.info1.sae.algorithmiquegestion.composants.*;

/**
 * Tests unitaires avec JUnit de la classe ChainesAscendantes.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
@TestInstance(Lifecycle.PER_CLASS)
class TestChainesAscendantes {

	ChainesAscendantes grapheTeste = new ChainesAscendantes(5, 5);
	
	Sommet[] sommetsGraphe = new Sommet[25];
	
	/**
	 * Méthode de test qui redéfinit les coordonnées et les marques
	 * initiales de tout les sommets du graphe grapheTeste.
	 */
	@BeforeEach
	void resetDeGrapheTeste() {
		for (int i = 0; i < grapheTeste.getNombreSommets(); i++) {
			grapheTeste.getListeSommets()[i] = grapheTeste.creerSommet(i);
		}
	}
	
	/**
	 * Méthode de test de
	 * {@link iut.info1.sae.algorithmiquegestion.composants.ChainesAscendantes#marqueDifferente()}.
	 */
	@Test
	void testMarqueDifferente() {
		//les marques ont été réinitialisées par le BeforeEach elles vallent -1 pour tous les sommets
		assertFalse(grapheTeste.marqueDifferente(
				grapheTeste.getListeSommets()[0], grapheTeste.getListeSommets()[1]));
		
		grapheTeste.getListeSommets()[5].setMarque(2);
		grapheTeste.getListeSommets()[6].setMarque(5);
		assertTrue(grapheTeste.marqueDifferente(
				grapheTeste.getListeSommets()[5], grapheTeste.getListeSommets()[6]));
	}

	/**
	 * Méthode de test de
	 * {@link iut.info1.sae.algorithmiquegestion.composants.ChainesAscendantes#sommetsDeMemeMarque()}.
	 */
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
		
		Sommet[] sommetRetourneMethode = grapheTeste.sommetsDeMemeMarque(marque)
				.toArray(new Sommet[grapheTeste.sommetsDeMemeMarque(marque).size()]);
		
		assertArrayEquals(sommetRetourneMethode, sommetAttendus1);
		
		for (int i = 0; i < grapheTeste.sommetsDeMemeMarque(marque).size(); i++) {
			
			System.out.println("Sommets : "
					+ "X : " + grapheTeste.sommetsDeMemeMarque(marque).get(i).getCoordonneeX()
					+ "  Y : " + grapheTeste.sommetsDeMemeMarque(marque).get(i).getCoordonneeY()
					+ "\tMarques : " + grapheTeste.sommetsDeMemeMarque(marque).get(i).getMarque());
		}
		
		grapheTeste.getListeSommets()[8].setMarque(4);
		grapheTeste.getListeSommets()[12].setMarque(4);
		grapheTeste.getListeSommets()[19].setMarque(4);
		
		Sommet[] sommetsAttendus2 = {
			    grapheTeste.getListeSommets()[8],
			    grapheTeste.getListeSommets()[12],
			    grapheTeste.getListeSommets()[19]
			};
		
		Sommet[] sommetRetourneMethode2 = grapheTeste.sommetsDeMemeMarque(4)
				.toArray(new Sommet[grapheTeste.sommetsDeMemeMarque(4).size()]);
		
		assertArrayEquals(sommetRetourneMethode2, sommetsAttendus2);
	}

	/**
	 * Méthode de test de
	 * {@link iut.info1.sae.algorithmiquegestion.composants.ChainesAscendantes#definitUneMarque()}.
	 */
	@Test
	void testDefinitUneMarque() {
	    
	    /* Test du 'if' */
	    grapheTeste.definitUneMarque(grapheTeste.getListeSommets()[0],
	    							 grapheTeste.getListeSommets()[1]);
	    
	    
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
	    
	    
	    /* Test du cas où les deux sommets on la même marque déjà définie */
	    grapheTeste.getListeSommets()[15].setMarque(7);
	    grapheTeste.getListeSommets()[16].setMarque(7);
	    grapheTeste.definitUneMarque(grapheTeste.getListeSommets()[15],
			     					 grapheTeste.getListeSommets()[16]);
	    
	    assertEquals(grapheTeste.getListeSommets()[15].getMarque(), 7);
	    assertEquals(grapheTeste.getListeSommets()[16].getMarque(), 7);
	}
	
	@Test
	void testSommetAleatoires() {
		System.out.println("Test de renvoie tableau contenant un sommet aléatoire " 
				+ "ainsi qu'un de ses sommets adjacent : ");
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
	}
	
	/**
     * Créé un graphe à partir de l'algorithme de création de 
     * chaînes puis affiche ses liaisons dans la console texte 
     * afin de vérifier sa validité
     */
	@AfterAll
	void testCreationDuGraphe() {
		System.out.println("Test de la creation du graphe : ");
		
		ChainesAscendantes graphe2 = new ChainesAscendantes(2, 2);
		
		for (int indexSommet = 0; indexSommet < graphe2.getNombreSommets(); indexSommet++) {
			System.out.println("Sommet : " + graphe2.getListeSommets()[indexSommet]);
			for (int i = 0; i < graphe2.getListeSommets()[indexSommet].getLiaisons().size(); i++) {
				System.out.println("Sommet lie : " + graphe2.getListeSommets()[indexSommet].getLiaisons().get(i));
			}
		System.out.print("\n");
			
		}
	}

}
