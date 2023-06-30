package iut.info1.sae.algorithmiquegestion.controleurs;

import javafx.fxml.FXML;
import javafx.application.Platform;

public class ControleurMenuPrincipal {

	private static final String TEXTE_AIDE =
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
		ControleurAlerte.aide(TEXTE_AIDE);
	}
	
	@FXML
	private void quitter() {
		Platform.exit();
	}
}
