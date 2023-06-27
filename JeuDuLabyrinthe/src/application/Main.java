package application;
	
import iut.info1.sae.algorithmiquegestion.controleurs.ControleurNavigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
//	private final String FXML_VIEW_PATH = "../iut/info1/sae/algorithmiquegestion/vue/MenuPrincipal";
	private static final String FXML_VIEW_PATH = "../iut/info1/sae/algorithmiquegestion/vue/MenuPrincipal.fxml";

	
	@Override
	public void start(Stage primaryStage) {
		FXMLLoader chargeurFXML;
		Parent racine;
		Scene scene;
		
		try {
//			BorderPane root = new BorderPane();
			chargeurFXML = new FXMLLoader(getClass().getResource(FXML_VIEW_PATH));
			racine = chargeurFXML.load();
			scene = new Scene(racine);
			ControleurNavigation.setSceneCourante(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
//			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
