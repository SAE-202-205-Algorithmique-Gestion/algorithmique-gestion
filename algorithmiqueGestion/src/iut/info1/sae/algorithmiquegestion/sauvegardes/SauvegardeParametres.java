/**
 * SauvegardeParametres.java										 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.sauvegardes;

import iut.info1.sae.algorithmiquegestion.parametres.ParametresLabyrinthe;

/*  TODO!
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
*/

/**
 * Classe de gestion de la sauvegarde des paramètres 
 * sélectionnés par l'utilisateur.
 * 
 * @author Jonathan GUIL
 */
public class SauvegardeParametres {
	
	private String cheminSauvegardeParametres;
	
	private ParametresLabyrinthe parametresLabyrinthe;
	
	public SauvegardeParametres(String cheminSauvegardeParametres, 
								ParametresLabyrinthe parametresLabyrinthe) {
		
		super();
		
		this.cheminSauvegardeParametres = cheminSauvegardeParametres;
		this.parametresLabyrinthe = parametresLabyrinthe;
		
	}
	
	public void sauvegarderParametres() {
		// TODO: faire la méthode de sauvegarde des paramètres.
	}

}
