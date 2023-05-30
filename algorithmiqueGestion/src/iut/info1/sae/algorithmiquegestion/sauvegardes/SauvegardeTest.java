/**
 * 
 */
package iut.info1.sae.algorithmiquegestion.sauvegardes;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author jonathan
 *
 */
public class SauvegardeTest {
	
	private static final String CHEMIN_TEST
		= "src/iut/info1/sae/algorithmiquegestion/donnees/test.json";
	
	private static final String CONTENU_FICHIER_JSON
		= "{\"test\": true}";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* écriture dans le fichier de CHEMIN_TEST */
		
		FileWriter testJson;
		
		try {
			testJson = new FileWriter(CHEMIN_TEST);
			testJson.write(CONTENU_FICHIER_JSON);
			testJson.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
