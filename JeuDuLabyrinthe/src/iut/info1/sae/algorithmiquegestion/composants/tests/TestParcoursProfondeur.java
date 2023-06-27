/*
 * TestParcoursProfondeur.java										 5 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.composants.tests;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;

/**
 * Tests unitaires avec JUnit de la classe ParcoursProfondeur.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
class TestParcoursProfondeur {

    Labyrinthe labyrinthe = new Labyrinthe(5, 5, 1);

    @BeforeEach
    void initaliserLabyrinthe() {
        labyrinthe = new Labyrinthe(5, 5, 1);
    }

    /**
     * Méthode de test de
     * {@link iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur#sommetLieAleatoire()}.
     */
    @Test
    void testSommetLieAleatoire() {
        Sommet sommet1 = new Sommet(1, 1, 6);
        Sommet sommet2 = new Sommet(3, 1, 8);
        Sommet sommet3 = new Sommet(2, 0, 2);
        Sommet sommet4 = new Sommet(2, 2, 13);
        ArrayList<Sommet> listeEnEntree = new ArrayList<>();
        listeEnEntree.add(sommet1);
        listeEnEntree.add(sommet2);
        listeEnEntree.add(sommet3);
        listeEnEntree.add(sommet4);

        System.out.println(ParcoursProfondeur.sommetLieAleatoire(listeEnEntree));

    }

    /**
     * Méthode de test de
     * {@link iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur#listeSommetsLiesNonParcourus()}.
     */
//    Maintenant Impossible à tester, car l'attribut labyrinthe 
//    est nul dans la class ParcoursProfondeur. Cette attribut
//    s'initialise avec le getter labyrinthe de MenuLabyrinthe
//    cependant lui aussi est nul puisque l'attribut est définie
//    en fonction des informations donné par l'utilisateur.
//    @Test
//    void testListeSommetsLiesNonParcourus() {
//        Sommet sommetLie = new Sommet(1, 1, 6);
//        Sommet sommetAlie1 = new Sommet(2, 1, 7);
//        Sommet sommetAlie2 = new Sommet(0, 1, 5);
//        Sommet sommetAlie3 = new Sommet(1, 2, 11);
//
//        sommetLie.creerLiaison(sommetAlie1);
//        sommetLie.creerLiaison(sommetAlie2);
//        sommetLie.creerLiaison(sommetAlie3);
//
//        Sommet[] sommetAttendue1 = { sommetAlie1, sommetAlie2, sommetAlie3 };
//
//        Sommet[] sommetRetourne1 = ParcoursProfondeur.listeSommetsLiesNonParcourus(sommetLie)
//                .toArray(new Sommet[ParcoursProfondeur.listeSommetsLiesNonParcourus(sommetLie).size()]);
//
//        assertArrayEquals(sommetAttendue1, sommetRetourne1);
//
//        sommetAlie2.setParcouru(true);
//
//        Sommet[] sommetAttendue2 = { sommetAlie1, sommetAlie3 };
//
//        Sommet[] sommetRetourne2 = ParcoursProfondeur.listeSommetsLiesNonParcourus(sommetLie)
//                .toArray(new Sommet[ParcoursProfondeur.listeSommetsLiesNonParcourus(sommetLie).size()]);
//
//        assertArrayEquals(sommetAttendue2, sommetRetourne2);
//
//    }

}
