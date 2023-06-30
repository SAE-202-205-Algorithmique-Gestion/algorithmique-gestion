package iut.info1.sae.algorithmiquegestion.controleurs;

import javafx.fxml.FXML;

import java.util.ArrayList;

import iut.info1.sae.algorithmiquegestion.modeles.CreationEtChargementLabyrinthe;
import iut.info1.sae.algorithmiquegestion.sauvegardes.LectureNomsFichier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ControleurChargementSauvegarde {

	@FXML
    private ListView<String> sauvegardeListView;
    
    private String selectedItem;
    
    @FXML
    private void initialize() {
        // Initialisez votre liste de noms de sauvegardes ici
    	ArrayList<String> nomsDesFichiers = new ArrayList<>();
    	nomsDesFichiers = LectureNomsFichier.listeNomsFichier();
    	ObservableList<String> sauvegardes = FXCollections.observableArrayList(nomsDesFichiers);
        sauvegardeListView.setItems(sauvegardes);
        
        quelElementCliquer();
//        System.out.println("sortie");
//        hihi();
        
    }
    
	private void gestionErreurChargement() {
		if (selectedItem != null) {
        	if (CreationEtChargementLabyrinthe.chargementLabyrinthePossible(selectedItem)) {
	        	ControleurNavigation.changerVue("PartieLabyrinthe.fxml");
	        } else {
	        	ControleurAlerte.alerte("Impossible de charger la sauvegarde...", "Sauvegarde Corrompue", AlertType.ERROR);
	        }
        }
	}

	@FXML
	private void retour() {
		ControleurNavigation.changerVue("MenuPrincipal.fxml");
//		ControleurNavigation.changerVue("PartieLabyrinthe.fxml");
	}
	
	private void quelElementCliquer() {
		sauvegardeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        sauvegardeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectedItem = (String) sauvegardeListView.getSelectionModel().getSelectedItem();
                System.out.println("Élément sélectionné : " + selectedItem);
                gestionErreurChargement();
            }
        });
	}
}
