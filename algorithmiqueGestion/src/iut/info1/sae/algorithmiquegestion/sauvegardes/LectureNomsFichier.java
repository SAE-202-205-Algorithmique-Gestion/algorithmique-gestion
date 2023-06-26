package iut.info1.sae.algorithmiquegestion.sauvegardes;

import java.io.File;

public class LectureNomsFichier {

	private static final String CHEMIN_REPERTOIRE = "sauvegarde";
	
	private static File repertoire;
	
	private static File[] fichiers;
    
	private static void initialisation() {
        // Créez une instance de la classe File avec le chemin du répertoire
        repertoire = new File(CHEMIN_REPERTOIRE);

        // Vérifiez si le chemin spécifié est un répertoire existant
        if (repertoire.isDirectory()) {
        	// Obtenez la liste des fichiers dans le répertoire
        	fichiers = repertoire.listFiles();
        } else {
            System.out.println("Erreur interne : "
            		+ "Le chemin spécifié n'est pas un répertoire valide.");
        }
    }
    
    public static boolean isNomFichierDejaExistant(String nomFichier) {
    	initialisation();
    	nomFichier = ChargementEtCreationSauvegarde.ajoutExtensionNomSauvegarde(nomFichier);
    	
    	for (File fichier : fichiers) {
    		if (nomFichier.equals(fichier.getName())) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void listeNomsFichiers() {
    	initialisation();
    	for (File fichier : fichiers) {
    		System.out.println("- " + fichier.getName());
    	}
    }
    
    public static boolean isListeFichiersVide() {
    	initialisation();
    	if (fichiers.length == 0) {
    		return true;
    	}
    	return false;
    }
}
