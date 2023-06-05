/**
 * SauvegardeLabyrinthe.java
 * Aucun droit d'auteur.
 */
package iut.info1.sae.algorithmiquegestion.sauvegardes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import iut.info1.sae.algorithmiquegestion.parametres.ParametresLabyrinthe;

/**
 * TODO: javadoc
 */
public class SauvegardeLabyrinthe {
	private final String CHEMIN_JSON_LABYRINTHES 
		= "src/iut/info1/sae/algorithmiquegestion/donnees/labyrinthes.json";
	
	private ParametresLabyrinthe parametresLabyrinthe;
	
	public SauvegardeLabyrinthe(ParametresLabyrinthe parametresLabyrinthe) {
		super();
		
		this.parametresLabyrinthe = parametresLabyrinthe;
	}
	
	public void sauvegarderParametres() {
		File recuperationSauvegardeCourante;
		
		BufferedWriter ecritureDansFichier;
		
		FileWriter ecritureSauvegarde;
		
		JsonArray objetSauvegarde,
		          structureLabyrinthe;
		
		JsonObject objetNouvelleSauvegarde;
		
		Scanner lectureFichierSauvegarde;
		
		String donneesCourantes,
			   nouvellesDonnees;
		
		donneesCourantes = "";
		
		try {
			recuperationSauvegardeCourante 
				= new File(this.CHEMIN_JSON_LABYRINTHES);
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
			
			objetNouvelleSauvegarde
				.addProperty("point_entree_x",
							 this.parametresLabyrinthe.getPointEntreeX());
	
			objetNouvelleSauvegarde
				.addProperty("point_entree_y",
							 this.parametresLabyrinthe.getPointEntreeY());
			
			objetNouvelleSauvegarde
				.addProperty("point_sortie_x",
						 	 this.parametresLabyrinthe.getPointSortieX());
	
			objetNouvelleSauvegarde
				.addProperty("point_sortie_y",
						 	 this.parametresLabyrinthe.getPointSortieY());
			
			if (this.parametresLabyrinthe.pointActuelExistant()) {
				objetNouvelleSauvegarde
					.addProperty("point_actuel_x",
							 	 this.parametresLabyrinthe.getPointActuelX());
			
				objetNouvelleSauvegarde
					.addProperty("point_actuel_y",
						 	 	 this.parametresLabyrinthe.getPointActuelY());
			}
			
            //for (Sommet sommetCourant: )
			
			objetSauvegarde.add(objetNouvelleSauvegarde);
			
			ecritureSauvegarde = new FileWriter(this.CHEMIN_JSON_LABYRINTHES);
			
			ecritureDansFichier = new BufferedWriter(ecritureSauvegarde);
			ecritureDansFichier.write(objetSauvegarde.toString());
			ecritureDansFichier.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
