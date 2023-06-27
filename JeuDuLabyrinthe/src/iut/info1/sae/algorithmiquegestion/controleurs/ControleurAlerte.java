package iut.info1.sae.algorithmiquegestion.controleurs;

import javafx.scene.control.Alert;

public class ControleurAlerte {

	private static final String TITRE_BOITE_DIALOGUE_AIDE = "Explication du jeu";

	public static void aide(String contenuAide) {
		Alert boiteDialogue;
		boiteDialogue = new Alert(Alert.AlertType.INFORMATION);
		boiteDialogue.setTitle(TITRE_BOITE_DIALOGUE_AIDE);
		boiteDialogue.setHeaderText(contenuAide);
		boiteDialogue.show();
	}
}
