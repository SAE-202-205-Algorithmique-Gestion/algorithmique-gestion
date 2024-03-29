/*
 * AffichageLabyrintheCaseCourante.java						     	 7 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.affichage;

import iut.info1.sae.algorithmiquegestion.MenuLabyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.JeuxLabyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/*
 * Affichage sur console texte d'un jeu de labyrinthe représenté
 * sous forme "humaine" avec un point de départ (l'entrée) E, un point
 * de sortie S et une position courante X.
 * L'utilisateur voit seulement la case courante sur laquelle il se trouve.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class AffichageCaseCourante {

    public final static String MUR_HORIZONTAL = "---";
    public final static String MUR_VERTICAL = "|";
    public final static String COIN = "+";

    public final static String LIAISON = "   ";
    public final static String LIAISON2 = " ";

    private final static String DEMANDE_COMMANDE = "\n\nEntrez votre/vos commande(s) : ";

    public final static String COMMANDES = """
            - Z : déplacement vers le haut
            - S : déplacement vers le bas
            - Q : déplacement vers la gauche
            - D : déplacement vers la droite
            """;

    public final static String CONSIGNES_JEU = "______________________ COMMANDES - JEU ______________________\n" + """
            \nVoici la liste des commandes utilisables dans la console texte :
            """ + COMMANDES + """
            \nTapez la touche 'Entrée' une fois que votre liste de commandes correspond à l'action souhaitée.

            Vous commencez sur la case E et devez atteindre la case S.
            Votre position courante est représentée par X lorsque vous quittez l'entrée E.

            Vous devez vous déplacer là où une liaison apparaît.
            """;

    private final static String PARTIE_GAGNEE = "\nBravo !\nVous avez gagné, la" + " partie est terminée !";

    private final static String PARCOURS_FIN = "\nVoici le parcours " + "de résolution : ";

    private final static String ERREUR_SAISIE = """
            \nVous avez entré une commande invalide ou un déplacement impossible !

            Voici la liste des commandes utilisables dans la console texte :
            """ + COMMANDES;

    private static Labyrinthe labyrinthe;

    private static Sommet[] listeSommets;

    private static int nombreColonnes;

    /**
     * Lancement de l'affichage du labyrinthe généré en fonction de la largeur
     * NOMBRE_COLONNES et la longueur NOMBRE_LIGNES.
     * 
     * @param labyrintheCourant Le labyrinthe actuel créé au menu principal.
     */
    public static void lancement() {
        
        labyrinthe = MenuLabyrinthe.getLabyrinthe();
        listeSommets = MenuLabyrinthe.getLabyrinthe().getGraphe().getListeSommets();
        nombreColonnes = MenuLabyrinthe.getLabyrinthe().getNombreDeColonne();

        System.out.println(CONSIGNES_JEU);

        do {
            AffichageCaseCourante.gestionDeplacementsLabyrinthe();
        } while (!labyrinthe.getPositionActuelle().equals(labyrinthe.getSortie()));

        System.out.println(PARTIE_GAGNEE);
        System.out.print(PARCOURS_FIN);
        System.out.println("\nLe parcours optimal de ce labyrinthe passe par "
				   + ParcoursProfondeur.algorithmeParcours() + " cases.");

        System.out.print("Vous avez parcouru ce labyrinthe en "
				 + labyrinthe.getNombreCasesParcourues() + " cases.");
        
        System.out.print("Voici le parcours de résolution");
        AffichageResolution.affichageParcoursSolution();
    }

    /**
     * Affichage du labyrinthe à chaque coup joué, gestion des déplacements du
     * joueur dans celui-ci et arrêt lorsque la position du joueur est sur la case
     * de sortie.
     */
    private static void gestionDeplacementsLabyrinthe() {

        System.out.println();
        System.out.print(COIN);

        if (!affichageMursVerticalement(labyrinthe.getIndiceSommetActuel(),
                labyrinthe.getIndiceSommetActuel() - nombreColonnes)) {

            System.out.print(MUR_HORIZONTAL);
        } else {
            System.out.print(LIAISON);

        }
        System.out.print(COIN);
        System.out.println();

        if (!affichageMursHorizontalement(labyrinthe.getIndiceSommetActuel(), labyrinthe.getIndiceSommetActuel() - 1)) {
            System.out.print(MUR_VERTICAL);
        } else {
            System.out.print(LIAISON2);
        }

        affichageSommets(labyrinthe.getIndiceSommetActuel());

        if (!affichageMursHorizontalement(labyrinthe.getIndiceSommetActuel(), labyrinthe.getIndiceSommetActuel() + 1)) {
            System.out.print(MUR_VERTICAL);
        }
        System.out.println();
        System.out.print(COIN);

        if (!affichageMursVerticalement(labyrinthe.getIndiceSommetActuel(),
                labyrinthe.getIndiceSommetActuel() + nombreColonnes)) {

            System.out.print(MUR_HORIZONTAL);
        } else {
            System.out.print(LIAISON);
        }
        System.out.print(COIN);

        System.out.println(DEMANDE_COMMANDE);
        if (!JeuxLabyrinthe.demandeDeplacement()) {
            System.out.print(ERREUR_SAISIE);
        }

        System.out.println();

    }

    /**
     * Méthode d'affichage des sommets
     * 
     * @param positionActuelle l'entier de la position actuelle
     */
    private static void affichageSommets(int positionActuelle) {
        /* Entrée */
        if (labyrinthe.getEntree().equals(listeSommets[positionActuelle])) {
            System.out.print(" " + JeuxLabyrinthe.getEntreeSymbole() + " ");

            /* Position actuelle */
        } else if (labyrinthe.getIndiceSommetActuel() == positionActuelle) {
            System.out.print(" " + JeuxLabyrinthe.getSommetActuelSymbole() + " ");
        }
    }

    /**
     * Vérification de la possibilité d'afficher un mur horizontal en fonction des
     * liaisons du sommet dont l'indice de position est en paramètre et le sommet
     * adjacent dont l'indice est aussi en paramètre.
     *
     * @param indicePositionActuelle Indice du sommet où se trouve le joueur.
     * @param indiceSommetAdjacent   Indice du sommet adjacent auquel vérifier la
     *                               liaison.
     * @return true si il faut afficher un mur horizontal.
     */
    private static boolean affichageMursHorizontalement(int indicePositionActuelle, int indiceSommetAdjacent) {
        return indicePositionActuelle < labyrinthe.getGraphe().getNombreSommets() - 1
                && labyrinthe.getGraphe().sommetExiste(indiceSommetAdjacent)
                && listeSommets[indicePositionActuelle].liaisonExiste(listeSommets[indiceSommetAdjacent]);
    }

    /**
     * Vérification de la possibilité d'afficher un mur vertical en fonction des
     * liaisons du sommet dont l'indice de position est en paramètre et le sommet
     * adjacent dont l'indice est aussi en paramètre.
     *
     * @param indicePositionActuelle Indice du sommet où se trouve le joueur.
     * @param indiceSommetAdjacent   Indice du sommet adjacent auquel vérifier la
     *                               liaison.
     * @return true si il faut afficher un mur vertical.
     */
    private static boolean affichageMursVerticalement(int indicePositionActuelle, int indiceSommetAdjacent) {
        return labyrinthe.getGraphe().sommetExiste(indiceSommetAdjacent)
                && listeSommets[indicePositionActuelle].liaisonExiste(listeSommets[indiceSommetAdjacent]);
    }
}
