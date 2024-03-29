/*
 * SauvegardeGraphe.java											 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.sauvegardes;

import iut.info1.sae.algorithmiquegestion.composants.Graphe;

/**
 * Sauvegarde d'un graphe au format JSON.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class SauvegardeGraphe {

    /**
     * Chemin absolu vers le fichier de sauvegarde dédié au graphe concerné par
     * l'instance.
     * 
     * <i>Donnée modifiable par l'utilisateur.</i>
     */
    private String cheminFichierSauvegarde;

    /**
     * Nom de la sauvegarde.
     * 
     * <i>Donnée modifiable par l'utilisateur.</i>
     */
    private String nomSauvegarde;

    /**
     * Graphe concerné par l'instance.
     */
    private Graphe graphe;

    /**
     * Constructeur de la classe. Initialisation des attributs de la classe.
     * 
     * @param cheminFichierSauvegarde
     * @param nomSauvegarde
     * @param graphe
     */
    public SauvegardeGraphe(String cheminFichierSauvegarde, String nomSauvegarde, Graphe graphe) {

        super();

        this.cheminFichierSauvegarde = cheminFichierSauvegarde;
        this.nomSauvegarde = nomSauvegarde;
        this.graphe = graphe;

    }

    /**
     * Retourne le chemin vers le fichier de sauvegarde du graphe.
     * 
     * @return Le chemin absolu vers le fichier de sauvegarde du graphe
     */
    public String getCheminFichierSauvegarde() {
        return this.cheminFichierSauvegarde;
    }

    /**
     * Modifie le chemin vers le fichier de sauvegarde du graphe.
     * 
     * @param nouveauChemin Nouveau chemin qui remplacera le courant
     */
    public void setCheminFichierSauvegarde(String nouveauChemin) {
        this.cheminFichierSauvegarde = nouveauChemin;
    }

    /**
     * Retourne le nom de la sauvegarde du graphe.
     * 
     * @return Le nom choisi par l'utilisateur pour la sauvegarde courante
     */
    public String getNomSauvegarde() {
        return this.nomSauvegarde;
    }

    /**
     * Modifie le nom de la sauvegarde du graphe.
     * 
     * @param nouveauNomSauvegarde Le nom choisi par l'utilisateur pour la
     *                             sauvegarde courante
     */
    public void setNomSauvegarde(String nouveauNomSauvegarde) {
        this.nomSauvegarde = nouveauNomSauvegarde;
    }

    /**
     * Retourne le graphe concerné par l'instance de sauvegarde courante.
     * 
     * @return L'instance du graphe concerné par la sauvegarde courante
     */
    public Graphe getGraphe() {
        return this.graphe;
    }

}
