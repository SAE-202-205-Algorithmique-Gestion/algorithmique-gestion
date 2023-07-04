package iut.info1.sae.algorithmiquegestion.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;

public class ControleurMenuPrincipal {

	static final String TEXTE_AIDE =
	"""
	//explication du jeu//
	""";
	
	@FXML
	private void creerLabyrinthe() {
		ControleurNavigation.changerVue("CreationLabyrinthe.fxml");
	}
	@FXML
	private void chargerLabyrinthe() {
		ControleurNavigation.changerVue("ChargementSauvegarde.fxml");
	}
	
	@FXML
	private void afficherAide() {
		ControleurAlerte.autreAlerte(TEXTE_AIDE, "Explication du jeu", AlertType.INFORMATION);
	}
	
	@FXML
	private void quitter() {
		Platform.exit();
	}
}
