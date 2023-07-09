package iut.info1.sae.algorithmiquegestion.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ControleurChoixAffichage {
	
	private static String vuePrecedente;
	
	public static void setVuePrecedente(String nomVuePrecedente) {
		vuePrecedente = nomVuePrecedente;
	}
	
	@FXML
	private ChoiceBox<String> choixAffichage;
	
	@FXML
	private void suivant() {
		String valeurChoisi;
		int indexValeurChoisi;
		
		valeurChoisi = choixAffichage.getValue();
		indexValeurChoisi = choixAffichage.getItems().indexOf(valeurChoisi);
		
		ControleurPartieLabyrinthe2.setTypeAffichage(indexValeurChoisi);
		ControleurNavigation.changerVue("PartieLabyrinthe2.fxml");
	}
		
	@FXML
	private void retour() {
		ControleurNavigation.changerVue(vuePrecedente);
	}
}
