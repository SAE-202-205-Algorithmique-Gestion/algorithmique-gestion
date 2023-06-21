package iut.info1.sae.algorithmiquegestion.affichage;

public class AttributsDesAffichages {

	private final static String MUR_VERTICALE = "---";
    private final static String MUR_HORIZONTALE = " | ";

    private final static String LIAISON = "   ";

    private static final String MUR_BORDURE = "----";

    private static final String BORDURE_DROITE = "|";
    private static final String BORDURE_GAUCHE = "\n|";

    private static final String COIN_DE_MUR = "+";

    private static final char CASE = ' ';

    private final static String DEMANDE_COMMANDE = "\n\nEntrez votre/vos commande(s) : ";

    private final static String COMMANDES = """
            - Z : déplacement vers le haut
            - S : déplacement vers le bas
            - Q : déplacement vers la gauche
            - D : déplacement vers la droite
            """;

    private final static String CONSIGNES_JEU = "______________________ COMMANDES - JEU ______________________\n" + """
            \nVoici la liste des commandes utilisables dans la console texte :
            """ + COMMANDES + """
            \nTapez la touche 'Entrée' une fois que votre liste de commandes correspond à l'action souhaitée.

            Vous commencez sur la case E et devez atteindre la case S.
            Votre position courante est représentée par X lorsque vous quittez l'entrée E.

            Vous devez vous déplacer là où une liaison apparaît.
            """;

    private final static String PARTIE_GAGNEE = "\nBravo !\nVous avez gagné, la" + " partie est terminée !";

    private final static String PARCOURS_FIN = "\nVoici le parcours de résolution : ";

    private final static String ERREUR_SAISIE = """
            \nVous avez entré une commande invalide ou un déplacement impossible !

            Voici la liste des commandes utilisables dans la console texte :
            """ + COMMANDES;

	public static String getMurVerticale() {
		return MUR_VERTICALE;
	}

	public static String getMurHorizontale() {
		return MUR_HORIZONTALE;
	}

	public static String getLiaison() {
		return LIAISON;
	}

	public static String getMurBordure() {
		return MUR_BORDURE;
	}

	public static String getBordureDroite() {
		return BORDURE_DROITE;
	}

	public static String getBordureGauche() {
		return BORDURE_GAUCHE;
	}

	public static String getCoinDeMur() {
		return COIN_DE_MUR;
	}

	public static char getCase() {
		return CASE;
	}

	public static String getDemandeCommande() {
		return DEMANDE_COMMANDE;
	}

	public static String getCommandes() {
		return COMMANDES;
	}

	public static String getConsignesJeu() {
		return CONSIGNES_JEU;
	}

	public static String getPartieGagnee() {
		return PARTIE_GAGNEE;
	}

	public static String getParcoursFin() {
		return PARCOURS_FIN;
	}

	public static String getErreurSaisie() {
		return ERREUR_SAISIE;
	}
    
}
