package iut.info1.sae.algorithmiquegestion.controleurs;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ControleurAlerte {

	private static final String TITRE_BOITE_DIALOGUE_AIDE = "Explication du jeu";

	public static void aide(String contenuAide) {
		Alert boiteDialogue;
		boiteDialogue = new Alert(Alert.AlertType.INFORMATION);
		boiteDialogue.setTitle(TITRE_BOITE_DIALOGUE_AIDE);
		boiteDialogue.setHeaderText(contenuAide);
		boiteDialogue.show();
	}
	
	public static boolean alerte(String messageAlerte, String titreFenetre, AlertType typeAlerte) {
		Alert boiteDialogue;
		Optional<ButtonType> option;
		
		boiteDialogue = new Alert(typeAlerte);
		boiteDialogue.setTitle(titreFenetre);
		boiteDialogue.setHeaderText(messageAlerte);
		option = boiteDialogue.showAndWait();
		
		return option.get() == ButtonType.OK;
		
		
		
	}
}
