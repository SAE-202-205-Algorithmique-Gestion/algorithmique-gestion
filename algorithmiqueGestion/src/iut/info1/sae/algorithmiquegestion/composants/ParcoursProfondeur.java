package iut.info1.sae.algorithmiquegestion.composants;

//import iut.info1.sae.algorithmiquegestion.composants.Graphe;
//import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.piles.Pile;
import java.util.ArrayList;
import java.util.Random;

public class ParcoursProfondeur {
	
	static Labyrinthe labyrinthe = AffichageLabyrinthe.getLabyrinthe();
    
    
    public static void algorithmeParcours() {
        Pile parcours = new Pile();
        Sommet sommetCourant;
        Sommet sommetAEmpiler;
        ArrayList<Sommet> listeSommetsAEmpiler = new ArrayList<>();
        
        
        parcours.empiler(labyrinthe.getEntree());
        labyrinthe.getEntree().setParcouru(true);
        do {
            sommetCourant = (Sommet)parcours.sommet();
            listeSommetsAEmpiler = listeSommetsLiesNonParcourus(sommetCourant);
            
            if (listeSommetsAEmpiler.size() == 0) {
                parcours.depiler();
            } else {
				sommetAEmpiler = SommetLieAleatoire(listeSommetsAEmpiler);
				parcours.empiler(sommetAEmpiler);
                sommetAEmpiler.setParcouru(true);
			}  
           
        } while (sommetCourant != labyrinthe.getSortie());
        parcours.empiler(labyrinthe.getSortie());
       
        /*for (int i = 0; i < parcours.getContenu().length; i++) {
    	    System.out.println(parcours.getContenu()[i]);
        }*/
        
        System.out.println("\n");
        while (!parcours.isVide()) {
			System.out.println(parcours.sommet());
			parcours.depiler();
		}
    }
    
    public static Sommet SommetLieAleatoire(ArrayList<Sommet> listeSommets) {
		/**
		* entiers aléatoires correspondant à un indice dans la liste des sommets
		*/
		int indiceSommetAleatoire;
		
		indiceSommetAleatoire = new Random().nextInt(listeSommets.size());
		
		Sommet sommetsAleatiores = listeSommets.get(indiceSommetAleatoire);
		return sommetsAleatiores;
    }
    
    public static ArrayList<Sommet> listeSommetsLiesNonParcourus(Sommet sommet) {
		
		ArrayList<Sommet> liaisonsCourantes = sommet.getLiaisons();
		ArrayList<Sommet> liaisonsNonParcourues = new ArrayList<>();
		
		
		for (int indiceLiaisons = 0; indiceLiaisons < liaisonsCourantes.size(); indiceLiaisons++) {
            if (!liaisonsCourantes.get(indiceLiaisons).isParcouru()) {
				liaisonsNonParcourues.add(liaisonsCourantes.get(indiceLiaisons));
            }
    
		}
		return liaisonsNonParcourues;
    }
    
}
