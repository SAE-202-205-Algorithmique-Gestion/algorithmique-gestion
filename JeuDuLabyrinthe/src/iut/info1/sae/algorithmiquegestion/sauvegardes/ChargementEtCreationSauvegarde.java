package iut.info1.sae.algorithmiquegestion.sauvegardes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
//import java.util.Scanner;

//import iut.info1.sae.algorithmiquegestion.MenuLabyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.modeles.PartieLabyrinthe2;

public class ChargementEtCreationSauvegarde {
	
	private final static String CHEMIN_REPERTOIRE = "sauvegarde\\";

	private static Labyrinthe labyrinthe;
	
	private static GsonBuilder gsonBuilder;
	
//	private static ArrayList<String> listeNomFichierSauvegarde;

//	private static Gson gson;
	
	private static void initialisation() {
		gsonBuilder = new GsonBuilder();
	}
	
	public static void creerUneSauvegarde(String nomSauvegarde) {
		String nomSauvegardeAvecExtension;
//		labyrinthe = MenuLabyrinthe.getLabyrinthe();
		labyrinthe = PartieLabyrinthe2.getLabyrinthe();
		initialisation();
		
        Gson gson = gsonBuilder.setPrettyPrinting().create();
		
        /* Affichage de la sauvegarde Json sur la console */
//		String affichageSauvegarde = gson.toJson(labyrinthe);
//		System.out.println(affichageSauvegarde);
        
        nomSauvegardeAvecExtension = ajoutExtensionNomSauvegarde(nomSauvegarde);
		try (Writer writer = new FileWriter(CHEMIN_REPERTOIRE + nomSauvegardeAvecExtension)) {
			gson.toJson(labyrinthe, writer);
		} catch (IOException e) {
			System.out.println("Erreur, la sauvegarde n'a pa pu se faire : " + e.getMessage());
//			e.printStackTrace();
		}
	}
	
	public static Labyrinthe chargerUneSauvegarde(String nomSauvegarde) {
		String nomSauvegardeAvecExtension;
		initialisation();
	    // Créez une instance Gson
		Gson gson = gsonBuilder.create();
		
		nomSauvegardeAvecExtension = ajoutExtensionNomSauvegarde(nomSauvegarde);
        try (FileReader reader = new FileReader(CHEMIN_REPERTOIRE + nomSauvegardeAvecExtension)) {
            // Désérialisez le contenu JSON en un objet Java
            labyrinthe = gson.fromJson(reader, Labyrinthe.class);

        } catch (IOException e) {
        	System.out.println("Le fichier JSON n'a pas été trouvé : " + e.getMessage());
//            e.printStackTrace();
        }
        
        return labyrinthe;
	}
	
	public static String ajoutExtensionNomSauvegarde(String nomSauvegarde) {
		final String JSON_EXTENSION = ".json";
		String recuperationExtension = "";
		
		if (nomSauvegarde.length() < 5) {
			nomSauvegarde += JSON_EXTENSION;
		} else {
			for (int i = nomSauvegarde.length() -5; i <= nomSauvegarde.length() -1; i++) {
				recuperationExtension += nomSauvegarde.charAt(i);
			}
			
			if (!recuperationExtension.equals(JSON_EXTENSION)) {
				nomSauvegarde += JSON_EXTENSION;
			}
		}
		
		
		return nomSauvegarde;
	}
}
