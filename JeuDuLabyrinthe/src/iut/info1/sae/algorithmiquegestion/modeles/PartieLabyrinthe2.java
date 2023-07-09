package iut.info1.sae.algorithmiquegestion.modeles;

import java.util.ArrayList;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.controleurs.ControleurNavigation;
//import iut.info1.sae.algorithmiquegestion.controleurs.ControleurPartieLabyrinthe;
import iut.info1.sae.algorithmiquegestion.controleurs.ControleurPartieLabyrinthe2;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class PartieLabyrinthe2 {
	
//	private static GridPane gridPane;
	
	private static Labyrinthe labyrinthe;
	
	private static Sommet[] listeSommets;

	private static int nombreSommetMinimalAParcourir;
	
	private static ArrayList<Sommet> sommetsDuParcours;
	
//	private static Sommet sommetActuelle;
	
	private static Scene scene;
	
	private static ImageView caseCourante = ControleurPartieLabyrinthe2.getCaseCourante();
	
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
    
    public static Labyrinthe getLabyrinthe() {
    	return labyrinthe;
    }
    
//    public static Sommet getSommetActuelle() {
//    	return sommetActuelle;
//    }
    
//    public static boolean getDeplacer() {
//    	return deplacer;
//    }
	
//	private static GridPane gridPane;
	
	private static void initialisation() {
		labyrinthe = CreationEtChargementLabyrinthe.getLabyrinthe();
		listeSommets = labyrinthe.getGraphe().getListeSommets();
		scene = ControleurNavigation.getScene();
		GridPane.setMargin(caseCourante, caseCouranteMarge);
	}
	
	private static boolean isPartieGagnee() {
		return labyrinthe.getPositionActuelle().equals(labyrinthe.getSortie());
	}
	
	public static void deplacementLabyEntier() {
		initialisation();
		
		/* on pourrais le faire l'eventListener sur le gridPane au lieu de sur la scene */
//		StackPane myNode = new StackPane(gridPane); // Nœud sur lequel nous voulons écouter les événements
        scene.setOnKeyPressed(event -> {
        	/* Pour qu'on arrête la possibilité de bouger lorsque la partie est terminé
        	 * Je pense que ce n'est pas la meilleur façon de faire */
        	if (ControleurNavigation.getVueCourante().equals("PartieLabyrinthe2.fxml")) {
        		deplacement(event);
        		if (isPartieGagnee()) {
//	            	event.consume(); //ne fonctionne pas
        			ControleurPartieLabyrinthe2.gestionPartieGagnee();
        		}
        	}
        	
        });
	}
	
	public static void deplacementCCAM() {
		initialisation();
		
		/* on pourrais le faire l'eventListener sur le gridPane au lieu de sur la scene */
//		StackPane myNode = new StackPane(gridPane); // Nœud sur lequel nous voulons écouter les événements
		scene.setOnKeyPressed(event -> {
			/* Pour qu'on arrête la possibilité de bouger lorsque la partie est terminé
	    	 * Je pense que ce n'est pas la meilleur façon de faire */
	    	if (ControleurNavigation.getVueCourante().equals("PartieLabyrinthe2.fxml")) {
	    		ControleurPartieLabyrinthe2.enlevementMurCCAM();
	    		deplacement(event);
				ControleurPartieLabyrinthe2.affichageLabyrintheCCAM();
				if (isPartieGagnee()) {
//	            	event.consume(); //ne fonctionne pas
					ControleurPartieLabyrinthe2.gestionPartieGagnee();
				}
			}
			
			
		});
	}
	
	private static void deplacement(KeyEvent event) {
		/* Pour qu'on arrête la possibilité de bouger lorsque la partie est terminé
    	 * Je pense que ce n'est pas la meilleur façon de faire */
    	if (ControleurNavigation.getVueCourante().equals("PartieLabyrinthe2.fxml")) {
    		// Code à exécuter lorsque la touche est enfoncée
            System.out.println("Touche enfoncée : " + event.getCode());
            demandeDeplacement(event.getCode().toString());
//    		sommetActuelle = labyrinthe.getPositionActuelle();
		
			GridPane.setColumnIndex(caseCourante, labyrinthe.getPositionActuelle().getCoordonneeX());
			GridPane.setRowIndex(caseCourante, labyrinthe.getPositionActuelle().getCoordonneeY());
			
            System.out.println("Case courante " + labyrinthe.getIndiceSommetActuel());
            System.out.println("Ses coordonées " + labyrinthe.getPositionActuelle());
            System.out.println("nombre de case parcouru " + labyrinthe.getNombreCasesParcourues());
    	}
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
    
    public static void rechercheSommetSuivantLabyEntier() {
    	rechercheSommetSuivant();
    	if (isPartieGagnee()) {
			ControleurPartieLabyrinthe2.gestionPartieGagnee();
		}
    }
    
    public static void rechercheSommetSuivantCCAM() {
    	ControleurPartieLabyrinthe2.enlevementMurCCAM();
    	rechercheSommetSuivant();
    	ControleurPartieLabyrinthe2.affichageLabyrintheCCAM();
    	if (isPartieGagnee()) {
			ControleurPartieLabyrinthe2.gestionPartieGagnee();
		}
    }
    
    private static void rechercheSommetSuivant() {
//    	if (!isPartieGagnee()) {
    		initialisation();
//    	sommetActuelle = labyrinthe.getPositionActuelle();
    		nombreSommetMinimalAParcourir
    		= ParcoursProfondeur.algorithmeParcours(labyrinthe.getIndiceSommetActuel());
    		sommetsDuParcours = ParcoursProfondeur.getParcoursListe();
    		
    		System.out.println(nombreSommetMinimalAParcourir);
    		
    		/* Position du sommet suivant (la liste est inversé) */
    		labyrinthe.setPositionActuelle(sommetsDuParcours.get(sommetsDuParcours.size() -1));
    		labyrinthe.setIndiceSommetActuel(labyrinthe.getPositionActuelle().getIndiceSommet()); 
    		
    		GridPane.setColumnIndex(caseCourante, labyrinthe.getPositionActuelle().getCoordonneeX());
    		GridPane.setRowIndex(caseCourante, labyrinthe.getPositionActuelle().getCoordonneeY());
    		
    		labyrinthe.setNombreCasesParcourues(labyrinthe.getNombreCasesParcourues() + 1);
//    	}
    }
}
