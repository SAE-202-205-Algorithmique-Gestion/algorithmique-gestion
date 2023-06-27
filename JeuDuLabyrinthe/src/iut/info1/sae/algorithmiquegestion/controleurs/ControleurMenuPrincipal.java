package iut.info1.sae.algorithmiquegestion.controleurs;

import javafx.fxml.FXML;
import javafx.application.Platform;

public class ControleurMenuPrincipal {

	private static final String TEXTE_AIDE =
	"""
	//explication du jeu//
	""";
	
	@FXML
	void creerLabyrinthe() {
		ControleurNavigation.changerVue("CreationLabyrinthe.fxml");
	}
	@FXML
	void chargerLabyrinthe() {
		ControleurNavigation.changerVue("ChargementSauvegarde.fxml");
	}
	
	@FXML
	void afficherAide() {
		ControleurAlerte.aide(TEXTE_AIDE);
	}
	
	@FXML
	void quitter() {
		Platform.exit();
	}
}
