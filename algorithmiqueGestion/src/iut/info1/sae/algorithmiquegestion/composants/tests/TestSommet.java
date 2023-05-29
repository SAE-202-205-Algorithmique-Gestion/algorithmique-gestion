/*
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

import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * Classe de tests unitaires JUnit de la classe Sommet.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
@TestInstance(Lifecycle.PER_CLASS)
class TestSommet {
	
	/**
	 * Affichage d'un message dans la console texte annonçant
	 * le début de tous les tests JUnit.
	 */
	@BeforeAll
	void debutTest() {
        System.out.println("\n---  DÉBUT TESTS UNITAIRES DES SOMMETS ---\n");
	}
	
	/**
	 * Affichage d'un message dans la console texte annonçant
	 * la fin de tous les tests JUnit.
	 */
	@AfterAll
    void finTest() {
        System.out.println("\n---  FIN TESTS UNITAIRES DES SOMMETS ---\n");
    }
	
	/**
	 * Méthode de test de la méthode getLiaisons().
	 */
	@Test
	@DisplayName("Test de la méthode getLiaisons()")
    void testGetLiaisons() {
		Sommet[][] ensembleSommetsALier = {
		    {new Sommet(1, 1), new Sommet(1, 0)},
		    {new Sommet(1, 1), new Sommet(2, 1)},
		    {new Sommet(1, 1), new Sommet(1, 2)},
	        {new Sommet(1, 1), new Sommet(0, 1)}
		};
		
		int indice = 0;
		
		for (Sommet[] listeSommets : ensembleSommetsALier) {
			
			Sommet sommet1 = listeSommets[0];
            Sommet sommet2 = listeSommets[1];
	        
            /* Création des liaisons entre les sommets */
            sommet1.creerLiaison(sommet2);
            
            Sommet[] liaisonsSommet1 = sommet1.getLiaisons();
            Sommet[] liaisonsSommet2 = sommet2.getLiaisons();
            
            assertTrue(ensembleSommetsALier[indice][1]
            		   .sommetEgal(liaisonsSommet1[0]));
		
            assertTrue(sommet1.sommetEgal(liaisonsSommet2[0]));
            
            indice++;
		}
    }
	
	/**
	 * Méthode de test de la méthode getCoordonneeX().
	 */
	@Test
	@DisplayName("Test de la méthode getCoordonneeX()")
    void testGetCoordonneeX() {
		Sommet[] sommetsAVerifier = {
            new Sommet(0, 0), new Sommet(0, 1), new Sommet(2, 0),
            new Sommet(3, 0), new Sommet(0, 3), new Sommet(0, 4),
            new Sommet(3, 0), new Sommet(4, 0), new Sommet(2, 2)
        };
		
		int[] coordonneesX = {
		    0, 0, 2, 3, 0, 0, 3, 4, 2
		};
        
        for (int indice = 0;
        	 indice < sommetsAVerifier.length;
        	 indice++) {
            assertEquals(coordonneesX[indice],
            			 sommetsAVerifier[indice].getCoordonneeX());
        }
    }
	
	/**
	 * Méthode de test de la méthode getCoordonneeY().
	 */
	@Test
	@DisplayName("Test de la méthode getCoordonneeY()")
    void testGetCoordonneeY() {
		Sommet[] sommetsAVerifier = {
            new Sommet(0, 0), new Sommet(0, 1), new Sommet(2, 0),
            new Sommet(3, 0), new Sommet(0, 3), new Sommet(0, 4),
            new Sommet(3, 0), new Sommet(4, 0), new Sommet(2, 2)
        };
		
		int[] coordonneesY = {
			0, 1, 0, 0, 3, 4, 0, 0, 2
		};
        
        for (int indice = 0;
        	 indice < sommetsAVerifier.length;
        	 indice++) {
            assertEquals(coordonneesY[indice],
            			 sommetsAVerifier[indice].getCoordonneeY());
        }
    }
	
	/**
	 * Méthode de test de la méthode getMarque().
	 */
	@Test
	@DisplayName("Test de la méthode getMarque()")
    void testGetMarque() {
	    Sommet[] listeSommetsATester = {
	        new Sommet(0, 0), new Sommet(0, 1), new Sommet(2, 0),
            new Sommet(3, 0), new Sommet(0, 3), new Sommet(0, 4),
            new Sommet(3, 0), new Sommet(4, 0), new Sommet(2, 2)
		};
		
		int[] marquesDeReference = {39,45,2005,5,86,1993,6,2048,1024};
        
        for (int indice = 0; indice < listeSommetsATester.length; indice++) {
			listeSommetsATester[indice].setMarque(marquesDeReference[indice]);
        	assertEquals(marquesDeReference[indice], listeSommetsATester[indice].getMarque());        	
        }
        
        
    }
	
	/**
	 * Méthode de test de la méthode setCoordonneeX().
	 */
	@Test
	@DisplayName("Test de la méthode setCoordonneeX()")
    void testSetCoordonneeX() {
		Sommet[] ensembleSommetsAVerifier = {
            new Sommet(0, 0), new Sommet(0, 1), new Sommet(2, 0),
            new Sommet(3, 0), new Sommet(0, 3), new Sommet(0, 4),
            new Sommet(3, 0), new Sommet(4, 0), new Sommet(2, 2)
        };
        
        int[][] coordonneesX = {
          // indiceSommet, coordonneeX
            {0,              1},
            {1,             -13},
            {2,              8},
            {3,             10},
            {4,              0},
            {5,              7},
            {6,             -1},
            {7,              3},
            {8,            100}
        };
        
        for (int[] coordonneeCourante : coordonneesX) {
             ensembleSommetsAVerifier[coordonneeCourante[0]]
             .setCoordonneeX(coordonneeCourante[1]);
        }
        
        for (int[] coordonneeCourante : coordonneesX) {
            assertEquals(coordonneeCourante[1],
                         ensembleSommetsAVerifier[coordonneeCourante[0]]
                         .getCoordonneeX());
        }
         
    }
	
	/**
	 * Méthode de test de la méthode setCoordonneeY().
	 */
	@Test
	@DisplayName("Test de la méthode setCoordonneeY()")
    void testSetCoordonneeY() {
		Sommet[] ensembleSommetsAVerifier = {
            new Sommet(0, 0), new Sommet(0, 1), new Sommet(2, 0),
            new Sommet(3, 0), new Sommet(0, 3), new Sommet(0, 4),
            new Sommet(3, 0), new Sommet(4, 0), new Sommet(2, 2)
        };
        
		int[][] coordonneesY = {
		  // indiceSommet, coordonneeY
			{0,              1},
			{1,              2},
			{2,            -18},
			{3,             10},
			{4,              0},
			{5,             -1},
			{6,              4},
			{7,              3},
			{8,            100}
		};
		
		for (int[] coordonneeCourante : coordonneesY) {
			 ensembleSommetsAVerifier[coordonneeCourante[0]]
			 .setCoordonneeY(coordonneeCourante[1]);
		}
		
		for (int[] coordonneeCourante : coordonneesY) {
			assertEquals(coordonneeCourante[1],
						 ensembleSommetsAVerifier[coordonneeCourante[0]]
						 .getCoordonneeY());
		}
    }
	
	/**
	 * Méthode de test de la méthode setMarque()
	 */
	@Test
	@DisplayName("Test de la méthode setMarque()")
    void testSetMarque() {
		Sommet[] listeSommetsATester = {
	        new Sommet(0, 0), new Sommet(0, 1), new Sommet(2, 0),
            new Sommet(3, 0), new Sommet(0, 3), new Sommet(0, 4),
            new Sommet(3, 0), new Sommet(4, 0), new Sommet(2, 2)
		};
		
		int[] marquesDeReference = {1, 2, 10, 1000, -1, -1000, 500, 12, -1}; 
        
        for (int indice = 0; indice < listeSommetsATester.length; indice++) {
        	final int valeurMarque = marquesDeReference[indice];
        	
        	listeSommetsATester[indice].setMarque(valeurMarque);
        	assertEquals(valeurMarque, listeSommetsATester[indice].getMarque());
        }
    }
    
	/**
	 * Méthode de test de la méthode creerLiaison(Sommet).
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
	        
            /* Création des liaisons entre les sommets */
            sommet1.creerLiaison(sommet2);
            
            assertTrue(sommet1.liaisonExiste(sommet2));
            assertTrue(sommet2.liaisonExiste(sommet1));
		}
    }
	
	/**
	 * Méthode de test de la méthode liaisonExiste(Sommet).
	 */
	@Test
	@DisplayName("Test de la méthode liaisonExiste(Sommet)")
    void testLiaisonExiste() {
		Sommet[][] ensembleSommetsALier = {
            {new Sommet( 5, 5), new Sommet( 5, 6)},
            {new Sommet( 6, 0), new Sommet( 7, 0)},
		    {new Sommet(10, 3), new Sommet(10, 4)},
            {new Sommet( 3, 5), new Sommet( 4, 5)},
            {new Sommet( 2, 2), new Sommet( 2, 3)}
		};
		
		for (Sommet[] listeSommets : ensembleSommetsALier) {
            Sommet sommet1 = listeSommets[0];
            Sommet sommet2 = listeSommets[1];
            
            assertFalse(sommet1.liaisonExiste(sommet2));
            assertFalse(sommet2.liaisonExiste(sommet1));
            
            /* Création de la liaison entre les sommets */
            sommet1.creerLiaison(sommet2);
            
            assertTrue(sommet1.liaisonExiste(sommet2));
            assertTrue(sommet2.liaisonExiste(sommet1));
		}
    }
	
    /**
     * Méthode de test de la méthode sommetEgal(Sommet).
     */
    @Test
    @DisplayName("Test de la méthode sommetEgal(Sommet)")
    void testSommetEgal() {
        Sommet[] ensembleSommetsAVerifier = {
            new Sommet(0,   0), new Sommet( 0,  0),
            new Sommet(1,  -3), new Sommet( 1, -3),
            new Sommet(7,   9), new Sommet( 7,  9),
            new Sommet(15,  4), new Sommet(15,  4),
            new Sommet(-2, 18), new Sommet(-2, 18)
        };
        
        for (int i = 0; i < ensembleSommetsAVerifier.length - 1; i += 2) {
            assertTrue(ensembleSommetsAVerifier[i]
            		   .sommetEgal(ensembleSommetsAVerifier[i + 1]));
            assertTrue(ensembleSommetsAVerifier[i + 1]
            		   .sommetEgal(ensembleSommetsAVerifier[i]));
        }
        
        for (int i = 1; i < ensembleSommetsAVerifier.length - 1; i = i + 2) {
            if (i != 9) {
                assertFalse(ensembleSommetsAVerifier[i]
                			.sommetEgal(ensembleSommetsAVerifier[i + 1]));
                assertFalse(ensembleSommetsAVerifier[i + 1]
                		    .sommetEgal(ensembleSommetsAVerifier[i]));
            } else {
			    assertFalse(ensembleSommetsAVerifier[9]
			    		    .sommetEgal(ensembleSommetsAVerifier[0]));
				assertFalse(ensembleSommetsAVerifier[0]
						    .sommetEgal(ensembleSommetsAVerifier[9]));
			}
        }
    }
    
    /**
     * Méthode de test de la méthode toString().
     */
    @Test
    @DisplayName("Test de la méthode toString()")
    void testToString() {
    	
    	Sommet[] sommetsInstancies = {
    		new Sommet(0, 0), new Sommet(0, 1), new Sommet(1, 0),
    		new Sommet(1, 1), new Sommet(10, 10)
    	};
    	
    	String[] messagesAttendus = {
    		"(0 ; 0)", "(0 ; 1)", "(1 ; 0)", "(1 ; 1)", "(10 ; 10)"
    	};
    	
    	for (int indice = 0; indice < sommetsInstancies.length; indice++) {
    		Sommet sommetCourant = sommetsInstancies[indice];
    		
    		assertEquals(messagesAttendus[indice], sommetCourant.toString());
    	}
        
    }

}