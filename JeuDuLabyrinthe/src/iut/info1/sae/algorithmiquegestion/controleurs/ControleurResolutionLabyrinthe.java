package iut.info1.sae.algorithmiquegestion.controleurs;

import java.util.ArrayList;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.modeles.CreationEtChargementLabyrinthe;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ControleurResolutionLabyrinthe {
	
	@FXML
	private BorderPane borderPane;
	
	private static GridPane gridPaneResolution;
	
	private static Labyrinthe labyrinthe;
	
	private ArrayList<ImageView> listeImageCaseResolution = new ArrayList<>();
	
	@FXML
	private void initialize() {
		labyrinthe = CreationEtChargementLabyrinthe.getLabyrinthe();
		borderPane.setCenter(gridPaneResolution);
		gridPaneResolution.setGridLinesVisible(true);
		affichageResolution();
	}
	
	private void affichageResolution() {
//		enlevementResolutionPrecedente();
//		
		int nombreSommetMinimalAParcourir;
		ArrayList<Sommet> sommetsDuParcours;
//		
		nombreSommetMinimalAParcourir =
		ParcoursProfondeur.algorithmeParcours(labyrinthe.getEntree().getIndiceSommet());
		sommetsDuParcours = ParcoursProfondeur.getParcoursListe();
//		
//		for (Sommet sommetResolution : sommetsDuParcours) {
//			
//			ObjectProperty<Image> imageProperty
//			= new SimpleObjectProperty<>(new Image("/images/caseResolution.png"));
//			ImageView caseResolution = new ImageView();
//			listeImageCaseResolution.add(caseResolution);
//			Insets margeCaseResolution = new Insets(5);
//			
//			caseResolution.imageProperty().bind(imageProperty);
//			GridPane.setMargin(caseResolution, margeCaseResolution);
//			
//			gridPaneResolution.add(caseResolution,
//					sommetResolution.getCoordonneeX(),
//					sommetResolution.getCoordonneeY());
//		}
//		enlevementResolutionPrecedente();

	    for (Sommet sommetResolution : sommetsDuParcours) {
	        ImageView caseResolution = new ImageView(new Image("/images/caseResolution.png"));
	        listeImageCaseResolution.add(caseResolution);
	        Insets margeCaseResolution = new Insets(5);
	        GridPane.setMargin(caseResolution, margeCaseResolution);

	        gridPaneResolution.add(caseResolution,
	                sommetResolution.getCoordonneeX(),
	                sommetResolution.getCoordonneeY());
	    }
		
	}
	
	// TODO : NE FONCTIONNE PAS
	private void enlevementResolutionPrecedente() {
//		gridPaneResolution.getChildren().removeAll(gridPaneResolution.getChildren());
		
//		if (!listeImageCaseResolution.isEmpty()) {
//	        for (ImageView imageCaseResolution : listeImageCaseResolution) {
//	            gridPaneResolution.getChildren().remove(imageCaseResolution);
//	        }
//	        listeImageCaseResolution.clear();
//	    }
		
		gridPaneResolution.getChildren().removeAll(listeImageCaseResolution);
	    listeImageCaseResolution.clear();
		
//		gridPaneResolution.getChildren().clear();
	    
//		if (!listeImageCaseResolution.isEmpty()) {
//			for (ImageView imageCaseResolution : listeImageCaseResolution) {
//				gridPaneResolution.getChildren().remove(imageCaseResolution);
//			}
//		}
	}
	
	public static void initialiserGridPane(GridPane gridPane, int typeAffichage) {
		gridPaneResolution = gridPane;
		if (typeAffichage != 0) {
			/* En faisant l'affectation du gridPane ci-dessus, 'gridPaneResolution'
			 * et 'gridPane' (dans la classe ControleurPartieLabyrinthe du coups)
			 * ont la même référence mémoire, donc lorsque nous modifions 'gridPane',
			 * est lui aussi modifié par conséquent.*/
			ControleurPartieLabyrinthe2.affichageLabyrintheEntier();
		}
	}

	@FXML
	private void quitter() {
		gridPaneResolution = null;
		ControleurNavigation.changerVue("MenuPrincipal.fxml");
	}
}
