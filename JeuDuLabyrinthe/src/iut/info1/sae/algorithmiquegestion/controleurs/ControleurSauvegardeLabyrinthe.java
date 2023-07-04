package iut.info1.sae.algorithmiquegestion.controleurs;

import java.util.ArrayList;

import iut.info1.sae.algorithmiquegestion.sauvegardes.ChargementEtCreationSauvegarde;
import iut.info1.sae.algorithmiquegestion.sauvegardes.LectureNomsFichier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleurSauvegardeLabyrinthe {
	
	@FXML
	private TextField nomSauvegarde;
	
	@FXML
	private ListView<String> labyrintheDejaSauvegarder;
	
	private ObservableList<String> sauvegardes;
	
	@FXML
	private void retour() {
		ControleurNavigation.changerVue("PartieLabyrinthe.fxml");
	}
	
	@FXML
	private void creerSauvegarde() {
		final String MESSAGE_ERREUR = "Le nom du fichier est déjà pris, veuillez en choisir un autre.";
//		final String MESSAGE_CONFIRMATION = "Le fichier à bien été sauvegardé !";
		if (LectureNomsFichier.isNomFichierDejaExistant(nomSauvegarde.getText())) {
			ControleurAlerte.autreAlerte(MESSAGE_ERREUR, "Fichier déjà existant", AlertType.ERROR);
		} else {
			String nomSauvegardeAvecExtension;
			
			ChargementEtCreationSauvegarde.creerUneSauvegarde(nomSauvegarde.getText());
			nomSauvegardeAvecExtension
			= ChargementEtCreationSauvegarde.ajoutExtensionNomSauvegarde(nomSauvegarde.getText());
			sauvegardes.add(0, nomSauvegardeAvecExtension); //ajout de l'élément à la première place
			labyrintheDejaSauvegarder.setItems(sauvegardes);
			labyrintheDejaSauvegarder.getSelectionModel().select(0);
		}
	}
	
	@FXML
	private void initialize() {
		affichageNomSauvegarde();
	}
	
	private void affichageNomSauvegarde() {
		ArrayList<String> nomsDesFichiers = new ArrayList<>();
    	nomsDesFichiers = LectureNomsFichier.listeNomsFichier();
    	sauvegardes = FXCollections.observableArrayList(nomsDesFichiers);
    	labyrintheDejaSauvegarder.setItems(sauvegardes);
	}
}
