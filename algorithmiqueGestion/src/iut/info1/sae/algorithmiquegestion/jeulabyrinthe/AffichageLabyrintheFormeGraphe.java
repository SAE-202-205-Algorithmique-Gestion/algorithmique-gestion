/*
 * AffichageLabyrintheFormeGraphe.java								 30 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package iut.info1.sae.algorithmiquegestion.jeulabyrinthe;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;

/**
 * Affichage sur console texte d'un jeu de labyrinthe représenté
 * sous forme de Graphe avec un point de départ (l'entrée) E, un point
 * de sortie S et une position courante X.
 * 
 * @author Jonathan GUIL
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 * @author Samuel LACAM
 * @author Tom DOUAUD
 */
public class AffichageLabyrintheFormeGraphe {
	
	public final static String LIAISON_HORIZONTALE = "----";
	public final static String VIDE_HORIZONTAL = "\t";
	
	public final static String LIAISON_VERTICALE = "|    ";
	
	public final static String VIDE_VERTICAL = "     ";
	
	public final static String CASE = "#";
	
	public final static String COMMANDES =
	"""
	- H : déplacement vers le haut
	- B : déplacement vers le bas
	- G : déplacement vers la gauche
	- D : déplacement vers la droite
	""";
	
	public final static String LANCEMENT_JEU =
	"""
	Bienvenue sur ce jeu de Labyrinthe !
	
	Veuillez d'abord renseigner la longueur et la largeur. // TODO
	Voici la liste des commandes utilisables dans la console texte :
	"""
	+ COMMANDES +
	"""
	Tapez la touche entrée une fois que votre liste de commandes correspond à l'action souhaitée.

	Vous commencez sur la case E et devez atteindre la case S.
	Votre position courante est représentée par X.
	Si vous revenez sur la case E (l'entrée), X ne sera plus affiché.
	
	Vous devez vous déplacer là où une liaison apparaît.
	Dans le cas contraire rien ne se passe.
	""";
	
	private final static String PARTIE_GAGNEE = "\nBravo !\nVous avez gagné, la"
												+ " partie est terminée !";
	
	private final static String ERREUR_SAISIE =
	"""
	\nVous avez entré une commande inconnue !
	
	Voici la liste des commandes utilisables dans la console texte :
	"""
	+ COMMANDES;
	
	private final static String DEMANDE_COMMANDE
	= "\n\nEntrez votre/vos commande(s) : ";
	
	/**
	 * 
	 */
	public static void main(String[] args) {
		
        int nombreLignes = 5;
	    int nombreColonnes = 5 ;

	    System.out.println(LANCEMENT_JEU);
		
		Labyrinthe labyrinthe = new Labyrinthe(nombreLignes, nombreColonnes);
		
		int ligne;
		do {
			ligne = 0;
			for (int i = 0; i < labyrinthe.getGraphe().getNombreSommets(); i++) {
				
				if (labyrinthe.getGraphe().getListeSommets()[i].getCoordonneeY() == ligne + 1) {
					System.out.println("");
					ligne++;
					
					for (int rang = 0; rang < labyrinthe.getGraphe().getNombreColonnesLabyrinthe(); rang++) {
						if (labyrinthe.getGraphe().getListeSommets()[i + rang]
							.liaisonExiste
							(labyrinthe.getGraphe().getListeSommets()[i + rang - labyrinthe.getGraphe()
																.getNombreColonnesLabyrinthe()])) {
							
							System.out.print(LIAISON_VERTICALE);
						} else {
							System.out.print (VIDE_VERTICAL);
						}
					}
					System.out.println("");
					
				}
				/* Affichage des sommets :
				 * Entrée */
				if (labyrinthe.getEntree() == labyrinthe.getGraphe().getListeSommets()[i]) {
					System.out.print(labyrinthe.getEntreeSymbole());
					
				/* Sortie */
				} else if (labyrinthe.getSortie() == labyrinthe.getGraphe().getListeSommets()[i]) {
					System.out.print(labyrinthe.getSortieSymbole());
					
				/* Position actuelle */
				} else if (labyrinthe.getPositionActuelle() != labyrinthe.getEntree()
						   && labyrinthe.getPositionActuelle() == labyrinthe.getGraphe().getListeSommets()[i]) {
					System.out.print(labyrinthe.getSommetActuelleSymbole());
				
				/* les autres sommets */
				} else {
					System.out.print(CASE);
				}
					
				if (i < labyrinthe.getGraphe().getNombreSommets() - 1) {
					if (labyrinthe.getGraphe().getListeSommets()[i].getCoordonneeY() == labyrinthe.getGraphe().getListeSommets()[i + 1].getCoordonneeY()) {
						if (labyrinthe.getGraphe().getListeSommets()[i].liaisonExiste(labyrinthe.getGraphe().getListeSommets()[i + 1])) {
							System.out.print(LIAISON_HORIZONTALE);
						} else {
							System.out.print("    ");
						}
					}
				}
				
			}
			
			System.out.println(DEMANDE_COMMANDE);
			if (!labyrinthe.demandeDeplacement()) {
				System.out.print(ERREUR_SAISIE);
			}
			System.out.println();
			
		} while (labyrinthe.getPositionActuelle() != labyrinthe.getSortie());
		System.out.println(PARTIE_GAGNEE);
	}
}
