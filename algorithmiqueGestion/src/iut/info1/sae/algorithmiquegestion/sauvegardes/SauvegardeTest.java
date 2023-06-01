/*
 * SauvegardeParametres.java										 16 mai 2023
 * IUT de Rodez, pas de copyright, ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.sauvegardes;

import iut.info1.sae.algorithmiquegestion.parametres.ParametresLabyrinthe;
import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;

/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
*/

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import com.google.gson.Gson;

import java.util.Scanner;

/**
 * Classe de gestion de la sauvegarde des paramètres 
 * sélectionnés par l'utilisateur.
 * 
 * @author Jonathan GUIL
 */
public class SauvegardeTest {
	
	private final String CHEMIN_JSON_PARAMETRES 
		= "src/iut/info1/sae/algorithmiquegestion/donnees/labyrinthes.json";
	
	private String cheminSauvegardeParametres;
	
	private ParametresLabyrinthe parametresLabyrinthe;
	
	public SauvegardeTest(ParametresLabyrinthe parametresLabyrinthe) {
		
		super();
		
		this.parametresLabyrinthe = parametresLabyrinthe;
		
	}
	
	public void sauvegarderParametres() {
		File recuperationSauvegardeCourante;
		
		BufferedWriter ecritureDansFichier;
		
		FileWriter ecritureSauvegarde;
		
		JsonArray objetSauvegarde;
		
		JsonObject objetNouvelleSauvegarde;
		
		Scanner lectureFichierSauvegarde;
		
		String donneesCourantes,
			   nouvellesDonnees;
		
		donneesCourantes = "";
		
		try {
			recuperationSauvegardeCourante 
				= new File(this.CHEMIN_JSON_PARAMETRES);
			lectureFichierSauvegarde 
				= new Scanner(recuperationSauvegardeCourante);

			while (lectureFichierSauvegarde.hasNextLine()) {
				donneesCourantes += lectureFichierSauvegarde.nextLine();
			}
			
			lectureFichierSauvegarde.close();
			
			objetSauvegarde
				= JsonParser.parseString(donneesCourantes).getAsJsonArray();
			
			objetNouvelleSauvegarde
				= new JsonObject();
			
			objetNouvelleSauvegarde
				.addProperty("nom", 
							 this.parametresLabyrinthe.getNomLabyrinthe());
			
			objetNouvelleSauvegarde
				.addProperty("longueur", 
							 this.parametresLabyrinthe.getLongueurLabyrinthe());
			
			objetNouvelleSauvegarde
				.addProperty("hauteur",
							 this.parametresLabyrinthe.getHauteurLabyrinthe());
			
			objetSauvegarde.add(objetNouvelleSauvegarde);
			
			ecritureSauvegarde = new FileWriter(this.CHEMIN_JSON_PARAMETRES);
			
			ecritureDansFichier = new BufferedWriter(ecritureSauvegarde);
			ecritureDansFichier.write(objetSauvegarde.toString());
			ecritureDansFichier.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		SauvegardeTest test 
			= new SauvegardeTest(
					new ParametresLabyrinthe(
							"test1", 
							new Labyrinthe(5, 5)));
		
		test.sauvegarderParametres();
	}

}
