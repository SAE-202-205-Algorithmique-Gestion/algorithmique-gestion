package iut.info1.sae.algorithmiquegestion.controleurs;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.modeles.CreationEtChargementLabyrinthe;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class ControleurPartieLabyrinthe {
	
//	@FXML
//	private Text colonnes;
//
//	@FXML
//	private Text lignes;
//	
//	@FXML
//	private Text construction;
	
	@FXML
	private GridPane gridPane;
	
	private Labyrinthe labyrinthe;

	@FXML
	private void initialize() {
		labyrinthe = CreationEtChargementLabyrinthe.getLabyrinthe();
//		colonnes.setText(colonnes.getText() + labyrinthe.getNombreDeColonne());
//		lignes.setText(lignes.getText() + labyrinthe.getNombreDeLigne());
//		construction.setText(construction.getText() + labyrinthe.getTypeConstruction());
		affichageLabyrinthe();
	}
	
	@FXML
	private void abandonner() {
		final String MESSAGE_ALERTE_ABANDONNER
			= "Êtes-vous vraiment sur de vouloir quitter ?"
			  + "\nVous perdrez ce labybyrinthe si vous ne l'avez pas sauvegarder";
		
		final String TITRE_FENETRE = "Confirmation quitter partie";
		
		if (ControleurAlerte.alerte
				(MESSAGE_ALERTE_ABANDONNER, TITRE_FENETRE, Alert.AlertType.CONFIRMATION)) {
			ControleurNavigation.changerVue("MenuPrincipal.fxml");
		}
	}
	
	@FXML
	private void indice() {
		
	}
	
	@FXML
	private void afficherAide() {
		
	}
	
	@FXML
	private void affichageLabyrinthe() {
		ImageView murGauche;
		ImageView murDroite;
		ImageView murHaut;
		ImageView murBas;
		Image mur1Chemin;
		mur1Chemin = new Image(getClass().getResource("/images/murnoir1.png").toString());
		ObservableList<ColumnConstraints> columnConstraintsList
			= gridPane.getColumnConstraints();
		
//		ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>(new Image("/images/murnoir1.png"));
//
//        // Ajouter les ImageView avec la même image
//        for (int i = 0; i < 5; i++) {
//            ImageView imageView = new ImageView();
//            imageView.imageProperty().bind(imageProperty);
//            Insets marginsMurBas = new Insets(45, 0, 0, 0);
//    		imageView.setFitHeight(5);
//    		imageView.setFitWidth(columnConstraintsList.get(0).getPrefWidth() /2);
//    	    GridPane.setMargin(imageView, marginsMurBas);
//    		GridPane.setRowIndex(imageView, 0);
//    		GridPane.setColumnIndex(imageView, 0);
//            gridPane.add(imageView, i, 0);
//        }
		//MurGauche
		murGauche = new ImageView();
		murGauche.setImage(mur1Chemin);
//		murGauche.setFitHeight(columnConstraintsList.get(0).getPrefWidth() /2);
//		murGauche.setFitWidth(5);
		Insets marginsMurGauche = new Insets(0, 0, 0, 0);
//		GridPane.setRowIndex(murGauche, 0);
//		GridPane.setColumnIndex(murGauche, 0);
//		this.gridPane.getChildren().add(murGauche);
		
		//MurHaut
		murHaut = new ImageView();
		murHaut.setImage(mur1Chemin);
//		murHaut.setFitHeight(5);
//		murHaut.setFitWidth(columnConstraintsList.get(0).getPrefWidth() /2);
		Insets marginsMurHaut = new Insets(-45, 0, 0, 0);
//	    GridPane.setMargin(murHaut, marginsMurHaut);
//		GridPane.setRowIndex(murHaut, 0);
//		GridPane.setColumnIndex(murHaut, 0);
//		this.gridPane.getChildren().add(murHaut);
		
		//MurDroite
		murDroite = new ImageView();
		murDroite.setImage(mur1Chemin);
//		murDroite.setFitHeight(columnConstraintsList.get(0).getPrefWidth() /2);
//		murDroite.setFitWidth(5);
		Insets marginsMurDroite = new Insets(0, 0, 0, 45);
//	    GridPane.setMargin(murDroite, marginsMurDroite);
//		GridPane.setRowIndex(murDroite, 0);
//		GridPane.setColumnIndex(murDroite, 0);
//		this.gridPane.getChildren().add(murDroite);
		
		//MurBas
		murBas = new ImageView();
		murBas.setImage(mur1Chemin);
		Insets marginsMurBas = new Insets(45, 0, 0, 0);
//		murBas.setFitHeight(5);
//		murBas.setFitWidth(columnConstraintsList.get(0).getPrefWidth() /2);
//	    GridPane.setMargin(murBas, marginsMurBas);
//		GridPane.setRowIndex(murBas, 0);
//		GridPane.setColumnIndex(murBas, 0);
//		this.gridPane.getChildren().add(murBas);

//		gridPane.add(murBas, 0, 1);
//		gridPane.add(murBas, 1, 0);
		
	    
	    affichageImagesMurs(marginsMurGauche, columnConstraintsList.get(0).getPrefWidth() /2, 5 + 1);
	    affichageImagesMurs(marginsMurHaut, 5 + 1, columnConstraintsList.get(0).getPrefWidth() /2);
	    affichageImagesMurs(marginsMurDroite, columnConstraintsList.get(0).getPrefWidth() /2, 5 + 1);
	    affichageImagesMurs(marginsMurBas, 5 + 1, columnConstraintsList.get(0).getPrefWidth() /2);
//		gridPane.setGridLinesVisible(true);
	}
//	
	
	private void affichageImagesMurs(Insets marge, double hauteur, double largeur) {
		// Créer une propriété pour l'image commune
        ObjectProperty<Image> imageProperty
        	= new SimpleObjectProperty<>(new Image("/images/murnoir1.png"));

        // Ajouter les ImageView avec la même image
        for (int indexLigne = 0; indexLigne < labyrinthe.getNombreDeLigne(); indexLigne++) {
        	for (int indexColonne = 0; indexColonne < labyrinthe.getNombreDeColonne(); indexColonne++) {
        		ImageView imageMurs = new ImageView();
        		imageMurs.imageProperty().bind(imageProperty);
        		imageMurs.setFitHeight(hauteur);
        		imageMurs.setFitWidth(largeur);
        		GridPane.setMargin(imageMurs, marge);
        		gridPane.add(imageMurs, indexColonne, indexLigne);
        	}
        }
	}
}
