package iut.info1.sae.algorithmiquegestion.controleurs;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class ControleurChargementSauvegarde {

	@FXML
    private ListView<String> sauvegardeListView;
    
    private ObservableList<String> sauvegardes;

    @FXML
    public void initialize() {
        // Initialisez votre liste de noms de sauvegardes ici
        sauvegardes = FXCollections.observableArrayList(
            "Sauvegarde 1",
            "Sauvegarde 2",
            "Sauvegarde 3"
        );
        
        sauvegardeListView.setItems(sauvegardes);
    }
    
    @FXML
    private void handleSauvegardeClicked(ActionEvent event) {
        String selectedSauvegarde = sauvegardeListView.getSelectionModel().getSelectedItem();
        
        if (selectedSauvegarde != null) {
            // Gérer le clic sur la sauvegarde sélectionnée
            // Par exemple, ouvrir la sauvegarde ou effectuer une action associée
            System.out.println("Vous avez cliqué sur : " + selectedSauvegarde);
        }
    }
	
	@FXML
	private void retour() {
		ControleurNavigation.changerVue("MenuPrincipal.fxml");
	}
}
