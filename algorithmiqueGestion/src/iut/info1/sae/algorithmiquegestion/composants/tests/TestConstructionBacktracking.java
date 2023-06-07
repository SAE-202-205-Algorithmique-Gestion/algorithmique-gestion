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
		Sommet sommetChoisi = grapheTeste.getListeSommets()[7];
		
		Sommet[] sommetsAdjacents = {grapheTeste.getListeSommets()[6],
                                        grapheTeste.getListeSommets()[8], 
                                        grapheTeste.getListeSommets()[2], 
                                        grapheTeste.getListeSommets()[12]};
		
		Sommet[] sommetsNonAdjacents = {grapheTeste.getListeSommets()[5],
		                                grapheTeste.getListeSommets()[9], 
		                                grapheTeste.getListeSommets()[1], 
		                                grapheTeste.getListeSommets()[3], 
		                                grapheTeste.getListeSommets()[11], 
		                                grapheTeste.getListeSommets()[13]};
		                                
        
	}

}
