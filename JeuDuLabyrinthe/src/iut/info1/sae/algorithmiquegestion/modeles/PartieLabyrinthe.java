package iut.info1.sae.algorithmiquegestion.modeles;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.controleurs.ControleurNavigation;
import iut.info1.sae.algorithmiquegestion.controleurs.ControleurPartieLabyrinthe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PartieLabyrinthe {
	
//	private static GridPane gridPane;
	
	private static Labyrinthe labyrinthe;
	
	private static Sommet[] listeSommets;
	
	private static Scene scene;
	
	private static Sommet positionActuelle;
	
	private static ImageView caseCourante = ControleurPartieLabyrinthe.getCaseCourante();
	
	private static Insets caseCouranteMarge = new Insets(5);
	
//	private static boolean deplacer;
	
	/** Touche permettant d'aller en haut. */
    private static final String HAUT = "Z";

    /** Touche permettant d'aller en bas. */
    private static final String BAS = "S";

    /** Touche permettant d'aller à droite. */
    private static final String DROITE = "D";

    /** Touche permettant d'aller à gauche. */
    private static final String GAUCHE = "Q";
    
    public static Sommet getPositionActuelle() {
    	return labyrinthe.getPositionActuelle();
    }
    
//    public static boolean getDeplacer() {
//    	return deplacer;
//    }
	
//	private static GridPane gridPane;
	
	private static void initialisation() {
		labyrinthe = CreationEtChargementLabyrinthe.getLabyrinthe();
		listeSommets = labyrinthe.getGraphe().getListeSommets();
		scene = ControleurNavigation.getScene();
	}
	
	public static boolean isPartieGagnee() {
		return labyrinthe.getPositionActuelle().equals(labyrinthe.getSortie());
	}
	
	public static void deplacement() {
		initialisation();
//		StackPane myNode = new StackPane(gridPane); // Nœud sur lequel nous voulons écouter les événements
        scene.setOnKeyPressed(event -> {
            // Code à exécuter lorsque la touche est enfoncée
            System.out.println("Touche enfoncée : " + event.getCode());
            demandeDeplacement(event.getCode().toString());
    		positionActuelle = PartieLabyrinthe.getPositionActuelle();
		
			GridPane.setMargin(caseCourante, caseCouranteMarge);
			GridPane.setColumnIndex(caseCourante, positionActuelle.getCoordonneeX());
			GridPane.setRowIndex(caseCourante, positionActuelle.getCoordonneeY());
			
            System.out.println("Case courante " + labyrinthe.getIndiceSommetActuel());
            System.out.println("Ses coordonées " + labyrinthe.getPositionActuelle());
            System.out.println("nombre de case parcouru " + labyrinthe.getNombreCasesParcourues());
            
            if (isPartieGagnee()) {
            	ControleurPartieLabyrinthe.gestionPartieGagnee();
            }
        });
	}
	
	/**
     * Vérification du déplacement que le joueur essaie d'effectuer.
     *
     * @param indiceSommetDeplacement Indice du sommet sur lequel le joueur s'est
     *                                déplacé.
     */
    private static void verificationDeplacement(int indiceSommetDeplacement) {

        if (labyrinthe.getGraphe().sommetExiste(labyrinthe.getIndiceSommetActuel() + indiceSommetDeplacement)
                && listeSommets[labyrinthe.getIndiceSommetActuel()]
                        .liaisonExiste(listeSommets[labyrinthe.getIndiceSommetActuel() + indiceSommetDeplacement])) {
            labyrinthe.setIndiceSommetActuel(labyrinthe.getIndiceSommetActuel() + indiceSommetDeplacement);
            labyrinthe.setPositionActuelle(listeSommets[labyrinthe.getIndiceSommetActuel()]);
            labyrinthe.setNombreCasesParcourues(labyrinthe.getNombreCasesParcourues() + 1);
        }
    }

    /**
     * Gestion de la saisie sur console texte de l'utilisateur lorsqu'il se déplace
     * sur le labyrinthe.
     */
    private static void demandeDeplacement(String touche) {
//        boolean deplacementValide = true;
//        boolean saisieSauvegardeCorrecte = false;
        
        switch (touche) {
        case HAUT:
            verificationDeplacement(-labyrinthe.getNombreDeColonne());
            break;

        case BAS:
            verificationDeplacement(labyrinthe.getNombreDeColonne());
            break;

        case DROITE:
            verificationDeplacement(1);
            break;

        case GAUCHE:
            verificationDeplacement(-1);
            break;
        
        default:
//        	deplacementValide = false;
        	break;
       
        }
//        return deplacementValide;
    }
//	public static void initialiserGridPane(GridPane leGridPane) {
//		gridPane = leGridPane;
//	}
	
}
