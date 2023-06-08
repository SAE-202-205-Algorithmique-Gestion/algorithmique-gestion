/*
 * ParametresLabyrinthe.java										 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.parametres;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * Classe de paramétrage du labyrinthe par l'utilisateur.
 * 
 * @author Jonathan GUIL
 */
public class ParametresLabyrinthe {

    private String nomLabyrinthe;

    /* private Sommet[] listeSommetsLabyrinthe; */

    private Labyrinthe labyrintheASauvegarder;

    public ParametresLabyrinthe(String nomLabyrinthe, Labyrinthe labyrintheASauvegarder) {

        super();

        this.nomLabyrinthe = nomLabyrinthe;
        this.labyrintheASauvegarder = labyrintheASauvegarder;

    }

    public int getLongueurLabyrinthe() {
        return this.labyrintheASauvegarder.getNombreDeColonne();
    }

    /*
     * public void setLongueurLabyrinthe(int nouvelleLongueur) {
     * this.longueurLabyrinthe = nouvelleLongueur; }
     */

    public int getHauteurLabyrinthe() {
        return this.labyrintheASauvegarder.getNombreDeLigne();
    }

    /*
     * public void setHauteurLabyrinthe(int nouvelleHauteur) {
     * this.hauteurLabyrinthe = nouvelleHauteur; }
     */

    public String getNomLabyrinthe() {
        return this.nomLabyrinthe;
    }

    public int getPointEntreeX() {
        return this.labyrintheASauvegarder.getEntree().getCoordonneeX();
    }

    public int getPointEntreeY() {
        return this.labyrintheASauvegarder.getEntree().getCoordonneeY();
    }

    public int getPointSortieX() {
        return this.labyrintheASauvegarder.getSortie().getCoordonneeX();
    }

    public int getPointSortieY() {
        return this.labyrintheASauvegarder.getSortie().getCoordonneeY();
    }

    public int getPointActuelX() {
        return this.labyrintheASauvegarder.getPositionActuelle().getCoordonneeX();
    }

    public int getPointActuelY() {
        return this.labyrintheASauvegarder.getPositionActuelle().getCoordonneeY();
    }

    public boolean pointActuelExistant() {
        return this.labyrintheASauvegarder.getPositionActuelle() != null;
    }

    public Sommet getListeSommetsLabyrinthe() {
        return this.getListeSommetsLabyrinthe();
    }

    // [{x1, [x2, x3]}, {x2, {x1, x6}}]

}
