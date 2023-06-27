package iut.info1.sae.algorithmiquegestion.affichage;

import iut.info1.sae.algorithmiquegestion.composants.JeuxLabyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.MenuLabyrinthe;

import static iut.info1.sae.algorithmiquegestion.affichage.AttributsDesAffichages.*;

import java.util.ArrayList;

public class AffichageResolution {
	
	private final static String MUR_VERTICALE = getMurVerticale();
    private final static String MUR_HORIZONTALE = getMurHorizontale();

    private final static String LIAISON = getLiaison();

    private static final String MUR_BORDURE = getMurBordure();

    private static final String BORDURE_DROITE = getBordureDroite();
    private static final String BORDURE_GAUCHE = getBordureGauche();

    private static final String COIN_DE_MUR = getCoinDeMur();

    private static final char CASE = getCase();

    public static final String RED = "\u001B[31m";

    public static final String RESET_COLOR = "\u001B[0m";
    
    private static final String CASE_SOLUTION = RED + "X" + RESET_COLOR;

	private static ArrayList<Sommet> parcours;
	
	private static Labyrinthe labyrinthe;
	
	private static Sommet[] listeSommets;
	
	public static void main(String[] args) {
		labyrinthe = new Labyrinthe(5, 5, 1);
		listeSommets = labyrinthe.getGraphe().getListeSommets();
		ParcoursProfondeur.algorithmeParcours();
		
		affichageParcoursSolution();
	}
	
	/**
     * Affichage du labyrinthe à chaque coup joué, gestion des déplacements du
     * joueur dans celui-ci et arrêt lorsque la position du joueur est sur la case
     * de sortie.
     */
	public static void affichageParcoursSolution() {
		labyrinthe = MenuLabyrinthe.getLabyrinthe();
		listeSommets = MenuLabyrinthe.getLabyrinthe().getGraphe().getListeSommets();
		parcours = ParcoursProfondeur.getParcoursListe();
		
		System.out.println();
		
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

            affichageSommets(i);
            affichageMurHorizontal(i);
        }

        // Dernière bordure latérale droite
        System.out.println(" " + BORDURE_DROITE);

        bordureHauteEtBasse();

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
    private static void affichageSommets(int indiceSommet) {
        /* Entrée */
        if (labyrinthe.getEntree().equals(listeSommets[indiceSommet])) {
            System.out.print(JeuxLabyrinthe.getEntreeSymbole());

        /* Sortie */
        } else if (labyrinthe.getSortie().equals(listeSommets[indiceSommet])) {
            System.out.print(JeuxLabyrinthe.getSortieSymbole());
         
        /* Sommets de la résolution */
        } else if (isSommetDansParcours(listeSommets[indiceSommet])) {
        	System.out.print(CASE_SOLUTION);
        	
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
    
    /**
     * Vérifie si le sommet en argument fait parti de la liste parcours.
     * @param sommetATester
     * @return si sommetATester fait parti de la liste parcours
     */
    private static boolean isSommetDansParcours(Sommet sommetATester) {
    	for (int i = parcours.size() - 1; i >= 0; i--) {
    		if (sommetATester == parcours.get(i)) {
    			parcours.remove(i);
    			return true;
    		}
    	}
    	return false;
    }
    
}
