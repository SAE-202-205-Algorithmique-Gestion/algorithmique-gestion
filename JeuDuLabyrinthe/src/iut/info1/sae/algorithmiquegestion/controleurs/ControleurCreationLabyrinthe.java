package iut.info1.sae.algorithmiquegestion.controleurs;

import iut.info1.sae.algorithmiquegestion.modeles.CreationEtChargementLabyrinthe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

public class ControleurCreationLabyrinthe {
	
	@FXML
	private ChoiceBox<String> choixNombreLignes;
	
	@FXML
	private ChoiceBox<String> choixNombreColonnes;
	
	@FXML
	private ChoiceBox<String> choixConstruction;
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void suivant() {
		final String MESSAGE_ERREUR
		= "Vous n'avez pas choisi de valeur dans au moins l'une des boîte de saisie";
		final String TITRE_ERREUR = "Problème de saisie";
		
		if (!isBoitesSaisiesRemplis()) {
			ControleurAlerte.autreAlerte(MESSAGE_ERREUR, TITRE_ERREUR, Alert.AlertType.ERROR);
		} else {
			int nombreLignes;
			int nombreColonnes;
			String typeConstruction;
			int typeConstructionInt;
			
			nombreLignes = Integer.parseInt(choixNombreLignes.getValue());
			nombreColonnes = Integer.parseInt(choixNombreColonnes.getValue());
			typeConstruction = choixConstruction.getValue();
		
			if (typeConstruction.equals("Création par chaîne ascendante")) {
				typeConstructionInt = 1;
			} else {
				typeConstructionInt = 2;
			}
			CreationEtChargementLabyrinthe.creationLabyrinthe(nombreLignes, nombreColonnes, typeConstructionInt);
			
			ControleurChoixAffichage.setVuePrecedente("CreationLabyrinthe.fxml");
			ControleurNavigation.changerVue("ChoixAffichage.fxml");
		}
		
	}
	
	private boolean isBoitesSaisiesRemplis() {
		return choixNombreLignes.getValue() !=  null
			&& choixNombreColonnes.getValue() !=  null
			&& choixConstruction.getValue() != null;
	}
	
	@FXML
	private void retour() {
		ControleurNavigation.changerVue("MenuPrincipal.fxml");
	}
	
	
}
