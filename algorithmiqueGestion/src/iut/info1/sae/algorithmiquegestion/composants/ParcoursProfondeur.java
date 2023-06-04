package iut.info1.sae.algorithmiquegestion.composants;

import iut.info1.sae.algorithmiquegestion.composants.Graphe;
import iut.info1.sae.algorithmiquegestion.composants.Sommet;
import iut.info1.sae.algorithmiquegestion.jeuxlabyrinthe.Labyrinthe;
import iut.info1.sae.algorithmiquegestion.piles.Pile;
import java.util.ArrayList;

public class ParcoursProfondeur {
    
    
    public void parcoursProfondeur() {
       Pile parcours = new Pile();
       Sommet sommetCourant;
       
       parcours.empiler(getEntree());
       getEntree().setParcouru(true);
       do {
           sommetCourant = (Sommet)parcours.sommet();
           ArrayList<Sommet> liaisonsCourantes = sommetCourant.getLiaisons();
           for (int indiceLiaisons = 0; indiceLiaisons < liaisonsCourantes.size(); indiceLiaisons++) {
               if (liaisonsCourantes.get(indiceLiaisons).isParcouru()) {
                   
               }
           }
           
           
       } while (sommetCourant != getSortie());
    }
    
    public Sommet SommetLieAleatoire(ArrayList listeSommets) {
        
    }
}
