package iut.info1.sae.algorithmiquegestion.sauvegardes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
//import java.util.Scanner;

import iut.info1.sae.algorithmiquegestion.MenuLabyrinthe;
import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;

public class ChargementEtCreationSauvegarde {

	private static Labyrinthe labyrinthe;
	
	private static GsonBuilder gsonBuilder;

//	private static Gson gson;
	
	private static void initialisation() {
		gsonBuilder = new GsonBuilder();
//		gsonBuilder.registerTypeAdapter(Scanner.class, new ScannerTypeAdapter());
	}
	
	public static void creerUneSauvegarde() {
		labyrinthe = MenuLabyrinthe.getLabyrinthe();
		initialisation();
//		Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .registerTypeAdapter(Scanner.class, new ScannerTypeAdapter())  // Utilisation de l'adaptateur de type personnalisé pour la classe Scanner
//                .create();
		
        Gson gson = gsonBuilder/*.excludeFieldsWithoutExposeAnnotation()*/
        		/*.enableComplexMapKeySerialization()*/.setPrettyPrinting().create();
		
        /* Affichage de la sauvegarde Json sur la console */
//		String affichageSauvegarde = gson.toJson(labyrinthe);
//		System.out.println(affichageSauvegarde);
		
		try (Writer writer = new FileWriter("labyrinthe.json")) {
			gson.toJson(labyrinthe, writer);
		} catch (IOException e) {
			System.out.println("Erreur, la sauvegarde n'a pa pu se faire : " + e.getMessage());
//			e.printStackTrace();
		}
	}
	
	public static Labyrinthe chargerUneSauvegarde() {
		initialisation();
	    // Créez une instance Gson
		Gson gson = gsonBuilder.create();
        try (FileReader reader = new FileReader("labyrinthe.json")) {
            // Désérialisez le contenu JSON en un objet Java
            labyrinthe = gson.fromJson(reader, Labyrinthe.class);

        } catch (IOException e) {
        	System.out.println("Le fichier JSON n'a pas été trouvé : " + e.getMessage());
//            e.printStackTrace();
        }
        
        return labyrinthe;
	}
}
