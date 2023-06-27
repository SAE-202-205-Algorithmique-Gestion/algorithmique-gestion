package iut.info1.sae.algorithmiquegestion.controleurs;

import javafx.fxml.FXML;

public class ControleurCreationLabyrinthe {
	
	@FXML
	private void suivant() {
		ControleurNavigation.changerVue(null); //BUSH
	}
	
	@FXML
	private void retour() {
		ControleurNavigation.changerVue("MenuPrincipal.fxml");
	}
	
	
}
