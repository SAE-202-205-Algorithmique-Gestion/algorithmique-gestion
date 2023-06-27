/**
 * ControleurNavigation.java
 * Aucun droit d'auteur.
 */
package iut.info1.sae.algorithmiquegestion.controleurs;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

/**
 * Classe permettant la navigation entre les vues de 
 * l'application.
 * 
 * @author jonathan
 */
public class ControleurNavigation {
	
	/**
	 * Chemin vers le dossier racine des vues à partir du 
	 * contrôleur courant.
	 */
	private static final String RACINE_VUES = "/iut/info1/sae/algorithmiquegestion/vue/";

	/**
	 * Scène courante définie.
	 * 
	 * <p>
	 * La scène par défaut est celle définie directement 
	 * par le Main.java au lancement de l'application : 
	 * le menu principal.
	 */
	private static Scene sceneCourante;
	
	/**
	 * (Re)définie directement l'objet de la scène
	 * courante. Cette information est utile lors du 
	 * changement de scène via la méthode de changement
	 * de vue.
	 * 
	 * @param nouvelleScene
	 */
	public static void setSceneCourante(Scene nouvelleScene) {
		sceneCourante = nouvelleScene;
	}
	
	/**
	 * Permet le changement de vue vers celle envoyée en 
	 * paramètre.
	 * 
	 * @param routeVueFXML Nom du fichier FXML de la vue 
	 * cible
	 */
	public static void changerVue(String routeVueFXML) {
		if (sceneCourante == null) {
			System.out.println("ERREUR : aucune scène courante !");
			return;  // TEMP
		}

		try {
			System.out.println(ControleurNavigation.class.getResource(
							RACINE_VUES + routeVueFXML));
			Parent racine;
			racine = FXMLLoader.load(
					ControleurNavigation.class.getResource(
							RACINE_VUES + routeVueFXML));
			
			sceneCourante.setRoot(racine);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Retourne la scène courante.
	 * 
	 * @return La scène courante
	 */
	public static Scene getScene() {
		return sceneCourante;
	}
	
}
