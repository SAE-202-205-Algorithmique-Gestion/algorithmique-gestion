package iut.info1.sae.algorithmiquegestion.controleurs;

import java.util.ArrayList;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.ParcoursProfondeur;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.modeles.CreationEtChargementLabyrinthe;
import iut.info1.sae.algorithmiquegestion.modeles.PartieLabyrinthe;
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

public class ControleurPartieLabyrinthe {
	
	@FXML
	private GridPane gridPane;
	
	private Labyrinthe labyrinthe;
	
	private Sommet[] listeSommets;
	
	private int nombreSommetMinimalAParcourir;
	
	private ArrayList<Sommet> sommetsDuParcours;
	
	private static Image caseCouranteSource = new Image(ControleurPartieLabyrinthe.class.getResource("/images/caseCourante.png").toString());

	private static ImageView caseCourante = new ImageView(caseCouranteSource);
	
	public static ImageView getCaseCourante() {
		return caseCourante;
	}
	
	@FXML
	private void initialize() {
//		nombreSommetMinimalAParcourir = ParcoursProfondeur.algorithmeParcours();
//		sommetsDuParcours = ParcoursProfondeur.getParcoursListe();
		labyrinthe = CreationEtChargementLabyrinthe.getLabyrinthe();
		listeSommets = labyrinthe.getGraphe().getListeSommets();
		
		GridPane.setColumnIndex(caseCourante, labyrinthe.getPositionActuelle().getCoordonneeX());
		GridPane.setRowIndex(caseCourante, labyrinthe.getPositionActuelle().getCoordonneeY());
		gridPane.getChildren().add(caseCourante);
//		PartieLabyrinthe.initialiserGridPane(gridPane);
		
		affichageLabyrinthe();
		affichageEntreeSortie();
		PartieLabyrinthe.deplacement();
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
		
	}
	
	@FXML
	private void afficherAide() {
		ControleurAlerte.autreAlerte(ControleurMenuPrincipal.TEXTE_AIDE, "Explication du jeu", AlertType.INFORMATION);
	}
	
	@FXML
	private void sauvegarder() {
		ControleurNavigation.changerVue("SauvegarderLabyrinthe.fxml");
	}
	
	
	private void affichageLabyrinthe() {
		ImageView murGauche;
		ImageView murDroite;
		ImageView murHaut;
		ImageView murBas;
		Image mur1Source;
		mur1Source = new Image(getClass().getResource("/images/murnoir1.png").toString());
		ObservableList<ColumnConstraints> columnConstraintsList
			= gridPane.getColumnConstraints();
		
		//MurGauche
		murGauche = new ImageView();
		murGauche.setImage(mur1Source);
		Insets marginsMurGauche = new Insets(0, 0, 0, 0);
		
		//MurHaut
		murHaut = new ImageView();
		murHaut.setImage(mur1Source);
		Insets marginsMurHaut = new Insets(-45, 0, 0, 0);
		
		//MurDroite
		murDroite = new ImageView();
		murDroite.setImage(mur1Source);
		Insets marginsMurDroite = new Insets(0, 0, 0, 45);

		//MurBas
		murBas = new ImageView();
		murBas.setImage(mur1Source);
		Insets marginsMurBas = new Insets(45, 0, 0, 0);

		for (int iSommetCourant = 0;
			iSommetCourant < labyrinthe.getGraphe().getNombreSommets();
			iSommetCourant++) {
			
//			for (int iTableauSommetLie = 0;
//				iTableauSommetLie
//				< labyrinthe.getGraphe().getListeSommets()[iSommetCourant].getLiaisons().size();
//				iTableauSommetLie++) {
			
				
			//TODO afficher les Murs en fonction des liaisons des sommets
			if (!labyrinthe.getGraphe().sommetExiste(iSommetCourant -1)
				|| !listeSommets[iSommetCourant].liaisonExiste(listeSommets[iSommetCourant - 1])) {
				
				affichageImagesMurs(marginsMurGauche, columnConstraintsList.get(0).getPrefWidth() /2, 5 + 1, iSommetCourant);
			}
			
			if (!labyrinthe.getGraphe().sommetExiste(iSommetCourant - labyrinthe.getNombreDeColonne())
				|| !listeSommets[iSommetCourant].liaisonExiste(listeSommets[iSommetCourant - labyrinthe.getNombreDeColonne()])) {
				
			    affichageImagesMurs(marginsMurHaut, 5 + 1, columnConstraintsList.get(0).getPrefWidth() /2, iSommetCourant);
			}
			
			if (!labyrinthe.getGraphe().sommetExiste(iSommetCourant +1)
				|| !listeSommets[iSommetCourant].liaisonExiste(listeSommets[iSommetCourant +1])) {
					
				affichageImagesMurs(marginsMurDroite, columnConstraintsList.get(0).getPrefWidth() /2, 5 + 1, iSommetCourant);
			}
			
			if (!labyrinthe.getGraphe().sommetExiste(iSommetCourant + labyrinthe.getNombreDeColonne())
				|| !listeSommets[iSommetCourant].liaisonExiste(listeSommets[iSommetCourant + labyrinthe.getNombreDeColonne()])) {
				
			    affichageImagesMurs(marginsMurBas, 5 + 1, columnConstraintsList.get(0).getPrefWidth() /2, iSommetCourant);
			}
		}
		
//		gridPane.setGridLinesVisible(true);
	}
	
	private void affichageImagesMurs(Insets marge, double hauteur, double largeur, int indiceSommet) {
		// Créer une propriété pour l'image commune
        ObjectProperty<Image> imageProperty
        	= new SimpleObjectProperty<>(new Image("/images/murnoir1.png"));

        // Ajouter les ImageView avec la même image
        
		ImageView imageMurs = new ImageView();
		imageMurs.imageProperty().bind(imageProperty);
		imageMurs.setFitHeight(hauteur);
		imageMurs.setFitWidth(largeur);
		GridPane.setMargin(imageMurs, marge);
		gridPane.add(imageMurs,
		listeSommets[indiceSommet].getCoordonneeX(),
		listeSommets[indiceSommet].getCoordonneeY());
        
	}
	
	private void affichageEntreeSortie() {
		Image entreeSource;
		Image sortieSource;
		ImageView entree;
		ImageView sortie;
		Insets entreeSortieMarge;
		
		entreeSource = new Image(getClass().getResource("/images/Entrée.png").toString());
		sortieSource = new Image(getClass().getResource("/images/Sortie.png").toString());
		entree = new ImageView(entreeSource);
		sortie = new ImageView(sortieSource);
		
		entreeSortieMarge = new Insets(5);
		GridPane.setMargin(entree, entreeSortieMarge);
		GridPane.setMargin(sortie, entreeSortieMarge);
		gridPane.add(sortie, labyrinthe.getSortie().getCoordonneeX(), labyrinthe.getSortie().getCoordonneeY());
		gridPane.add(entree, labyrinthe.getEntree().getCoordonneeX(), labyrinthe.getEntree().getCoordonneeY());
	}
	
	public static void gestionPartieGagnee() {
		ControleurAlerte.autreAlerte("Bien joué, vous avez gagné", "Partie Gagné", AlertType.INFORMATION);
		ControleurNavigation.changerVue("MenuPrincipal.fxml");
	}
}
