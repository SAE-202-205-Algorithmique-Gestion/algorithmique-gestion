/*
 * ConstructionBacktracking.java                                     5 juin 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import iut.info1.sae.algorithmiquegestion.composants.*;
import java.util.ArrayList;

/**
 * Tests unitaires avec JUnit de la classe ConstructionBacktracking.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
@TestInstance(Lifecycle.PER_CLASS)
class TestConstructionBacktracking {

	ConstructionBacktracking grapheTeste = new ConstructionBacktracking(5, 5);
	
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
     * {@link iut.info1.sae.algorithmiquegestion.composants.ConstructionBacktracking#testSommetsAdjacentsNonParcourus()}.
     */
    @Test
    void testSommetsAdjacentsNonParcourus() {
		int[] sommetChoisit = {7, 0, 22};
		
        /* Sommet adjacent du sommet sommetChoisit[0]*/                     
		Sommet[] sommetsAdjacents1 = {grapheTeste.getListeSommets()[12],
									  grapheTeste.getListeSommets()[2],
									  grapheTeste.getListeSommets()[8],
									  grapheTeste.getListeSommets()[6]};
		
	    /* Sommet adjacent du sommet sommetChoisit[1]*/
		Sommet[] sommetsAdjacents2 = {grapheTeste.getListeSommets()[5],
									  grapheTeste.getListeSommets()[1]};
		
        /* Sommet adjacent du sommet sommetChoisit[2]*/
		Sommet[] sommetsAdjacents3 = {grapheTeste.getListeSommets()[17],
									  grapheTeste.getListeSommets()[23],
									  grapheTeste.getListeSommets()[21]};
		
		/* Sommet[] sommetsNonAdjacents = {grapheTeste.getListeSommets()[5],
		                                grapheTeste.getListeSommets()[9], 
		                                grapheTeste.getListeSommets()[1], 
		                                grapheTeste.getListeSommets()[3], 
		                                grapheTeste.getListeSommets()[11], 
		                                grapheTeste.getListeSommets()[13]}; */      
		                                
		Sommet[] listeDeLaMethode1 = grapheTeste.sommetsAdjacentsNonParcourus(sommetChoisit[0])
				.toArray(new Sommet[grapheTeste.sommetsAdjacentsNonParcourus(sommetChoisit[0]).size()]);
				
		Sommet[] listeDeLaMethode2 = grapheTeste.sommetsAdjacentsNonParcourus(sommetChoisit[1])
				.toArray(new Sommet[grapheTeste.sommetsAdjacentsNonParcourus(sommetChoisit[1]).size()]);
				
		Sommet[] listeDeLaMethode3 = grapheTeste.sommetsAdjacentsNonParcourus(sommetChoisit[2])
				.toArray(new Sommet[grapheTeste.sommetsAdjacentsNonParcourus(sommetChoisit[2]).size()]);
				                     
        
        assertArrayEquals(listeDeLaMethode1, sommetsAdjacents1);
    
        assertArrayEquals(listeDeLaMethode2, sommetsAdjacents2);
    
        assertArrayEquals(listeDeLaMethode3, sommetsAdjacents3);
    
    }
    
    /**
     *
     */
     void testSommetAdjacentAleatoire() {
		 
		 ArrayList<Sommet> listeSommetsAdjacents = new ArrayList<>();
		 //int[] listeSommetsAdjacents = {2, 6, 8, 12};
		 
		 // 0 --> 1 ou 5
		 // 7 --> 2, 6, 8 ou 12
		 // 18 --> 13, 17, 19 ou 23
		 
		 listeSommetsAdjacents.add(grapheTeste.getListeSommets()[2]);
		 listeSommetsAdjacents.add(grapheTeste.getListeSommets()[6]);
		 listeSommetsAdjacents.add(grapheTeste.getListeSommets()[8]);
		 listeSommetsAdjacents.add(grapheTeste.getListeSommets()[12]);
		 
	     System.out.println(grapheTeste.sommetAdjacentAleatoire(listeSommetsAdjacents));
		 
	 }
}
