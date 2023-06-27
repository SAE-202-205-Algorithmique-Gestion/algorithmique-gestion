/*
 * AffichageLabyrinthe.java											 6 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.affichage;

import iut.info1.sae.algorithmiquegestion.MenuLabyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.JeuxLabyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;

/**
 * Affichage sur console texte d'un jeu de labyrinthe représenté sous forme
 * "humaine" avec un point de départ (l'entrée) E, un point de sortie S et une
 * position courante X. L'utilisateur voit l'entièreté du labyrinthe dans lequel
 * il se trouve.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class AffichageLabyrinthe {

    public final static String MUR_VERTICALE = "---";
    public final static String MUR_HORIZONTALE = " | ";

    public final static String LIAISON = "   ";

    private static final String MUR_BORDURE = "----";

    private static final String BORDURE_DROITE = "|";
    private static final String BORDURE_GAUCHE = "\n|";

    private static final String COIN_DE_MUR = "+";

    private static final char CASE = ' ';

    private final static String DEMANDE_COMMANDE = "\n\nEntrez votre/vos commande(s) : ";

    public final static String COMMANDES = """
            - Z : déplacement vers le haut
            - S : déplacement vers le bas
            - Q : déplacement vers la gauche
            - D : déplacement vers la droite
            - H : Sauvegarde du labyrinthe
                  sur la position actuelle
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

    private final static String PARCOURS_FIN = "\nVoici le parcours de résolution : ";

    private final static String ERREUR_SAISIE = """
            \nVous avez entré une commande invalide ou un déplacement impossible !

            Voici la liste des commandes utilisables dans la console texte :
            """ + COMMANDES;

    private static Labyrinthe labyrinthe;

    private static Sommet[] listeSommets;

    /**
     * Lancement de l'affichage du labyrinthe généré en fonction de la largeur
     * NOMBRE_COLONNES et la longueur NOMBRE_LIGNES.
     * 
     * @param labyrintheCourant Le labyrinthe actuel créé au menu principal.
     */
    public static void lancement() {
    	labyrinthe = MenuLabyrinthe.getLabyrinthe();
    	listeSommets = labyrinthe.getGraphe().getListeSommets();
//    	System.out.println("AffichageLabyrinthe : nb colonne" + labyrinthe.getNombreDeColonne()
//    					   + "AffichageLabyrinthe : nb ligne" + labyrinthe.getNombreDeLigne());
        System.out.println(CONSIGNES_JEU);

        do {
            gestionDeplacementsLabyrinthe();
        } while (!labyrinthe.getPositionActuelle().equals(labyrinthe.getSortie()));

        System.out.println(PARTIE_GAGNEE);
        System.out.print(PARCOURS_FIN);
        System.out.println("\nLe parcours optimal de ce labyrinthe passe par "
        				   + ParcoursProfondeur.algorithmeParcours() + " cases.");
        
        System.out.print("Vous avez parcouru ce labyrinthe en "
        				 + labyrinthe.getNombreCasesParcourues() + " cases.");
        
        AffichageResolution.affichageParcoursSolution();
    }

    /**
     * Affichage du labyrinthe à chaque coup joué, gestion des déplacements du
     * joueur dans celui-ci et arrêt lorsque la position du joueur est sur la case
     * de sortie.
     */
    private static void gestionDeplacementsLabyrinthe() {

        int ligneCourante = 0;

        bordureHauteEtBasse();

        System.out.print(BORDURE_GAUCHE + " ");

        for (int i = 0; i < listeSommets.length; i++) {

            if (listeSommets[i].getCoordonneeX() == 0 && listeSommets[i].getCoordonneeY() != 0) {
                // Bordure latérale droite
                System.out.print(" " + BORDURE_DROITE);
            }

            if (listeSommets[i].getCoordonneeY() == ligneCourante + 1) {
                System.out.print(BORDURE_GAUCHE);

                ligneCourante++;

                for (int rangLigne = 0; rangLigne < labyrinthe.getNombreDeColonne(); rangLigne++) {

                    System.out.print(affichageMursVertical(i, rangLigne) ? LIAISON : MUR_VERTICALE);

                    if (rangLigne < labyrinthe.getNombreDeColonne() - 1) {
                        System.out.print(COIN_DE_MUR);
                    }
                }
                System.out.print(BORDURE_DROITE + BORDURE_GAUCHE + " ");
            }

            affichageSommets(listeSommets[i]);
            affichageMurHorizontal(i);
        }

        // Dernière bordure latérale droite
        System.out.println(" " + BORDURE_DROITE);

        bordureHauteEtBasse();

        System.out.println(DEMANDE_COMMANDE);

        if (!JeuxLabyrinthe.demandeDeplacement()) {
            System.out.print(ERREUR_SAISIE);
        }
    }

    /**
     * Vérification des liaisons afin d'afficher ou non les murs verticaux.
     *
     * @param indiceSommet Indice du sommet pour lequel vérifier la liaison.
     * @param rangLigne    Le rang de la ligne permettant la vérification.
     * @return true si l'affichage est possible.
     */
    private static boolean affichageMursVertical(int indiceSommet, int rangLigne) {
        return listeSommets[indiceSommet + rangLigne]
                .liaisonExiste(listeSommets[indiceSommet + rangLigne - labyrinthe.getNombreDeColonne()]);
    }

    /**
     * Affichage console texte des bordures du haut et du bas du labyrinthe.
     */
    private static void bordureHauteEtBasse() {
        System.out.print(COIN_DE_MUR);
        for (int j = 0; j < labyrinthe.getGraphe().getNombreColonnesLabyrinthe() - 1; j++) {
            System.out.print(MUR_BORDURE);
        }
        System.out.print("---+");
    }

    /**
     * Affichage console texte des sommets du labyrinthe.
     *
     * @param indiceSommet Indice du sommet courant.
     */
//    private static void affichageSommetsDoubleEgal(Sommet indiceSommet) {
//        /* Entrée */
//        if (labyrinthe.getEntree() == indiceSommet) {
//            System.out.print(JeuxLabyrinthe.getEntreeSymbole());
//
//            /* Sortie */
//        } else if (labyrinthe.getSortie() == indiceSommet) {
//            System.out.print(JeuxLabyrinthe.getSortieSymbole());
//
//            /* Position actuelle */
//        } else if (labyrinthe.getPositionActuelle() != labyrinthe.getEntree()
//                && labyrinthe.getPositionActuelle() == indiceSommet) {
//            System.out.print(JeuxLabyrinthe.getSommetActuelSymbole());
//
//            /* Les autres sommets */
//        } else {
//            System.out.print(CASE);
//        }
//    }
    private static void affichageSommets(Sommet sommetCourant) {
    	/* Entrée */
    	if (labyrinthe.getEntree().equals(sommetCourant)) {
    		System.out.print(JeuxLabyrinthe.getEntreeSymbole());
    		
    		/* Sortie */
    	} else if (labyrinthe.getSortie().equals(sommetCourant)) {
    		System.out.print(JeuxLabyrinthe.getSortieSymbole());
    		
    		/* Position actuelle */
    	} else if (labyrinthe.getPositionActuelle() != labyrinthe.getEntree()
    			&& labyrinthe.getPositionActuelle().equals(sommetCourant)) {
    		System.out.print(JeuxLabyrinthe.getSommetActuelSymbole());
    		
    		/* Les autres sommets */
    	} else {
    		System.out.print(CASE);
    	}
    }

    /**
     * Affichage console texte de tous les murs horizontaux du labyrinthe.
     *
     * @param indiceSommet Indice du sommet courant à côté du mur à afficher.
     */
    private static void affichageMurHorizontal(int indiceSommet) {
        if (indiceSommet < labyrinthe.getGraphe().getNombreSommets() - 1
                && listeSommets[indiceSommet].getCoordonneeY() == listeSommets[indiceSommet + 1].getCoordonneeY()) {

            if (listeSommets[indiceSommet].liaisonExiste(listeSommets[indiceSommet + 1])) {
                System.out.print(LIAISON);
            } else {
                System.out.print(MUR_HORIZONTALE);
            }
        }
    }
}
