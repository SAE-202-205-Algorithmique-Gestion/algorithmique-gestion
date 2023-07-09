package iut.info1.sae.algorithmiquegestion.controleurs;

import java.util.ArrayList;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.modeles.CreationEtChargementLabyrinthe;
import iut.info1.sae.algorithmiquegestion.modeles.PartieLabyrinthe2;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;

public class ControleurPartieLabyrinthe2 {
	
	@FXML
	private BorderPane borderPane;
	
	private static GridPane gridPane;
	
	private static StackPane stackPane;
	
	private static Labyrinthe labyrinthe;
	
	private static  Sommet[] listeSommets;
	
//	private int nombreSommetMinimalAParcourir;
//	
//	private ArrayList<Sommet> sommetsDuParcours;
	
	private static int typeAffichage;
	
	private static Image caseCouranteSource = new Image(ControleurPartieLabyrinthe.class.getResource("/images/caseCourante.png").toString());

	private static ArrayList<ImageView> listeImageMurs = new ArrayList<>();
	
	private static ImageView caseCourante = new ImageView(caseCouranteSource);
	
	public static ImageView getCaseCourante() {
		return caseCourante;
	}
	
	public static void setTypeAffichage(int indexChoiceBox) {
		typeAffichage = indexChoiceBox;
	}
	
	@FXML
	private void initialize() {
		labyrinthe = CreationEtChargementLabyrinthe.getLabyrinthe();
		listeSommets = labyrinthe.getGraphe().getListeSommets();
		switch (typeAffichage) {
		case 0:
			labyrintheEntier();
			break;
		case 1:
			caseCouranteAvecMouvement();
			break;
		case 2:
			caseCouranteSansMouvement();
			break;

		default:
			break;
		}
		
//		PartieLabyrinthe.initialiserGridPane(gridPane);
		/* Affichage de la case courante */		
	}
	
	public void labyrintheEntier() {
		creationGridPane();
		gridPane.setGridLinesVisible(true);
		affichageLabyrintheEntier();
		affichageEntreeSortiePositionActuelleLabyEntier();
		PartieLabyrinthe2.deplacementLabyEntier();
	}
	
	public void caseCouranteAvecMouvement() {
		creationGridPane();
		gridPane.setGridLinesVisible(true);
		affichageLabyrintheCCAM();
		
		/* A modifier */
		affichageEntreeSortiePositionActuelleLabyEntier();
		PartieLabyrinthe2.deplacementCCAM();
	}
	
	public void caseCouranteSansMouvement() {
		
	}
	
	private void creationGridPane() {
		gridPane = new GridPane();
		gridPane.setPrefHeight(500);
		gridPane.setPrefWidth(500);
		
		for (int ligne = 0; ligne < labyrinthe.getNombreDeLigne(); ligne++) {
			RowConstraints contraintesLigne = new RowConstraints();
			contraintesLigne.setPrefHeight(50);
			gridPane.getRowConstraints().add(contraintesLigne);
//			gridPane.addRow(ligne);
		}
		
		for (int colonne = 0; colonne < labyrinthe.getNombreDeColonne(); colonne++) {
			ColumnConstraints contraintesColonne = new ColumnConstraints();
			contraintesColonne.setPrefWidth(50);
			gridPane.getColumnConstraints().add(contraintesColonne);
//			gridPane.addColumn(colonne);
		}
		borderPane.setCenter(gridPane);
	}
	
//	public static void enlevementMurCCAM() {
//		for (int i = 0; i < 4 - PartieLabyrinthe2.getLabyrinthe().getPositionActuelle().getLiaisons().size(); i++) {
//			gridPane.getChildren().remove(listeImageMurs.get(i));
//		}
//	}
	
	public static void enlevementMurCCAM() {
	    for (ImageView imageMurCourant : listeImageMurs) {
	        gridPane.getChildren().remove(imageMurCourant);
	    }
	}
	
	public static void affichageLabyrintheCCAM() {
		gestionAffichageMurs(labyrinthe.getIndiceSommetActuel());
	}
	
	public static void affichageEntreeSortiePositionActuelleCCAM() {
		if (PartieLabyrinthe2.getLabyrinthe().getPositionActuelle()
				.equals(labyrinthe.getEntree())) {
			
//			gridPane.add
		}
	}
	
	public static void affichageLabyrintheEntier() {
		for (int iSommetCourant = 0;
			iSommetCourant < labyrinthe.getGraphe().getNombreSommets();
			iSommetCourant++) {
			
			gestionAffichageMurs(iSommetCourant);
		}
	}
	
	private static void gestionAffichageMurs(int indiceSommet) {
		ObservableList<ColumnConstraints> columnConstraintsList
		= gridPane.getColumnConstraints();
		Insets marginsMurGauche = new Insets(0, 0, 0, 0);
		Insets marginsMurHaut = new Insets(-45, 0, 0, 0);
		Insets marginsMurDroite = new Insets(0, 0, 0, 45);
		Insets marginsMurBas = new Insets(45, 0, 0, 0);
		
		if (!labyrinthe.getGraphe().sommetExiste(indiceSommet -1)
			|| !listeSommets[indiceSommet].liaisonExiste(listeSommets[indiceSommet - 1])) {
			
			affichageImagesMurs(marginsMurGauche, columnConstraintsList.get(0).getPrefWidth(), 5 + 1, indiceSommet);
		}
		
		if (!labyrinthe.getGraphe().sommetExiste(indiceSommet - labyrinthe.getNombreDeColonne())
			|| !listeSommets[indiceSommet].liaisonExiste(listeSommets[indiceSommet - labyrinthe.getNombreDeColonne()])) {
			
		    affichageImagesMurs(marginsMurHaut, 5 + 1, columnConstraintsList.get(0).getPrefWidth(), indiceSommet);
		}
		
		if (!labyrinthe.getGraphe().sommetExiste(indiceSommet +1)
			|| !listeSommets[indiceSommet].liaisonExiste(listeSommets[indiceSommet +1])) {
				
			affichageImagesMurs(marginsMurDroite, columnConstraintsList.get(0).getPrefWidth(), 5 + 1, indiceSommet);
		}
		
		if (!labyrinthe.getGraphe().sommetExiste(indiceSommet + labyrinthe.getNombreDeColonne())
			|| !listeSommets[indiceSommet].liaisonExiste(listeSommets[indiceSommet + labyrinthe.getNombreDeColonne()])) {
			
		    affichageImagesMurs(marginsMurBas, 5 + 1, columnConstraintsList.get(0).getPrefWidth(), indiceSommet);
		}
	}
	
	private static void affichageImagesMurs(Insets marge, double hauteur, double largeur, int indiceSommet) {
		// Créer une propriété pour l'image commune
        ObjectProperty<Image> imageProperty
        	= new SimpleObjectProperty<>(new Image("/images/murnoir1.png"));

        // Ajouter les ImageView avec la même image
        ImageView imageMurs = new ImageView();
        listeImageMurs.add(imageMurs);
		imageMurs.imageProperty().bind(imageProperty);
		imageMurs.setFitHeight(hauteur);
		imageMurs.setFitWidth(largeur);
		GridPane.setMargin(imageMurs, marge);
		gridPane.add(imageMurs,
		listeSommets[indiceSommet].getCoordonneeX(),
		listeSommets[indiceSommet].getCoordonneeY());
	}
	
	private void affichageEntreeSortiePositionActuelleLabyEntier() {
		Image entreeSource;
		Image sortieSource;
		ImageView entree;
		ImageView sortie;
		Insets entreeSortieMarge;
		Insets caseCouranteMarge;
		
		entreeSource = new Image(getClass().getResource("/images/Entrée.png").toString());
		sortieSource = new Image(getClass().getResource("/images/Sortie.png").toString());
		entree = new ImageView(entreeSource);
		sortie = new ImageView(sortieSource);
		
		/* Affichage entrée sortie */
		entreeSortieMarge = new Insets(5);
		GridPane.setMargin(entree, entreeSortieMarge);
		GridPane.setMargin(sortie, entreeSortieMarge);
		gridPane.add(sortie, labyrinthe.getSortie().getCoordonneeX(), labyrinthe.getSortie().getCoordonneeY());
		gridPane.add(entree, labyrinthe.getEntree().getCoordonneeX(), labyrinthe.getEntree().getCoordonneeY());
		
		/* Affichage de la case courante */	
		caseCouranteMarge = new Insets(5);
		GridPane.setMargin(caseCourante, caseCouranteMarge);
		GridPane.setColumnIndex(caseCourante, labyrinthe.getPositionActuelle().getCoordonneeX());
		GridPane.setRowIndex(caseCourante, labyrinthe.getPositionActuelle().getCoordonneeY());
		gridPane.getChildren().add(caseCourante);
	}
	
	
	
	public static void gestionPartieGagnee() {
		ControleurAlerte.autreAlerte("Bien joué, vous avez gagné", "Partie Gagné", AlertType.INFORMATION);
		ControleurResolutionLabyrinthe.initialiserGridPane(gridPane, typeAffichage);
		ControleurNavigation.changerVue("ResolutionLabyrinthe.fxml");
	}
	
	@FXML
	private void abandonner() {
		final String MESSAGE_ALERTE_ABANDONNER
			= "Êtes-vous vraiment sur de vouloir quitter ?"
			  + "\nVous perdrez ce labybyrinthe si vous ne l'avez pas sauvegarder";
		
		final String TITRE_FENETRE = "Confirmation quitter partie";
		
		if (ControleurAlerte.alerteConfirmation
				(MESSAGE_ALERTE_ABANDONNER, TITRE_FENETRE)) {
			ControleurNavigation.changerVue("MenuPrincipal.fxml");
		}
	}
	
	@FXML
	private void indice() {
		if (typeAffichage == 0) {
			PartieLabyrinthe2.rechercheSommetSuivantLabyEntier();
		} else if (typeAffichage == 1) {
			PartieLabyrinthe2.rechercheSommetSuivantCCAM();
		}
	}
	
	@FXML
	private void afficherAide() {
		ControleurAlerte.autreAlerte(ControleurMenuPrincipal.TEXTE_AIDE, "Explication du jeu", AlertType.INFORMATION);
	}
	
	@FXML
	private void sauvegarder() {
		ControleurNavigation.changerVue("SauvegarderLabyrinthe.fxml");
	}
}
