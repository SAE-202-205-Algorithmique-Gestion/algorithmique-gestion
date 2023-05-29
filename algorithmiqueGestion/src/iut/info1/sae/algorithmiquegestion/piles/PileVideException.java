/*
 * PileVideException.java											 12 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package iut.info1.sae.algorithmiquegestion.piles;

/**
 * Classe d'exception héritée de RuntimeException rapportant
 * l'erreur d'une pile vide.
 * 
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class PileVideException extends RuntimeException {

	/**
	 * Numéro de version UID de l'exception.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construit une nouvelle exception d'exécution avec null
	 * comme message de détail.
	 */
	public PileVideException() {
		super();		
	}
	
	/**
	 * Construit une nouvelle exception d'exécution
	 * avec un message de détail passé en paramètre.
	 * 
	 * @param message Message de détail donné pour l'exception d'exécution.
	 */
	public PileVideException(String message) {
		super(message);
	}

}
