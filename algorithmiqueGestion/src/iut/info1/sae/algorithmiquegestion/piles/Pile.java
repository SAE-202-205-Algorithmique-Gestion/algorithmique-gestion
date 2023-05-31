/*
 * Pile.java											  			 12 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */

package iut.info1.sae.algorithmiquegestion.piles;

import java.util.Arrays;

/**
 * Collection d'objets à loi de gestion LIFO = Last In First Out.
 * <p>
 * Classe non immuable (collection).
 * 
 * @author Simon GUIRAUD
 * @author Loïc FAUGIERES
 */
public class Pile {
	
	/**	Nombre max d'objets empilables initialement */
	private static final int CAPACITE_INITIALE = 10;

	/** Representation contigue = bloc de références des objets empilés */
	private Object[] contenu;
	
	/** Nombre effectif d'objets empilés */
	private int taille;
	
	/**
	 * Initialisation d'une pile vide et instanciation d'une
	 * liste d'objets de taille CAPACITE_INITIALE.
	 */
	public Pile() {
		super();
		contenu = new Object[CAPACITE_INITIALE];
		// taille = 0; // pile vide
	}
	
	/**
	 * Prédicat vérifiant que cette pile ne référence aucun élément.
	 * 
	 * @return true si aucun élément empilé, false sinon.
	 */
	public boolean isVide() {
		return taille == 0;
	}
	
	/**
	 * Ajoute un élément aAjouter au sommet de cette pile.
	 * <p>
	 * Commande modifiant l'état de this.</p>
	 * 
	 * @param aAjouter Référence de l'élément à empiler (non nulle).
	 * @return Référence de cette pile après empilement.
	 * @throws NullPointerException si aAjouter est null.
	 */
	public Pile empiler(Object aAjouter) {
		if (aAjouter == null) {
			throw new NullPointerException("Impossible d'empiler la réf. null");
		}
		verifierTailleElements();
		contenu[taille] = aAjouter;
		taille++;
		return this;
	}
	
	/**
	 * Retire de cette pile la référence du dernier élément
	 * empilé en suivant la méthode LIFO (DAPS en français).
	 * 
	 * @return Cette pile après dépilement.
	 * @throws PileVideException si cette pile est vide.
	 */
	public Pile depiler() {
		preConditionPileNonVide();
		taille--;
		verifierTailleElements();
		return this;
	}
	
	/**
	 * Reallocation d'un nouveau tableau lorsque le tableau elements
	 * est plein et recopie des anciens éléments.
	 */
	private void verifierTailleElements() {
		final double GROSSISSEMENT = 1.25; // 25% de taille
		if (taille == contenu.length) {
			contenu = Arrays.copyOf(contenu,
									(int) Math.ceil(taille * GROSSISSEMENT));
		} else if (taille < contenu.length) {
			contenu = Arrays.copyOf(contenu, taille + 1);
		}
	}
	
	/**
	 * Accès au dernier empilé sur cette pile.
	 * 
	 * @return Référence du dernier élément empilé.
	 * @throws PileVideException si cette pile est vide.
	 */
	public Object sommet() {
		preConditionPileNonVide();
		return contenu[taille - 1];
	}
	
	/**
	 * Vérifie une pré-condition non vide avant opération.
	 * 
	 * @throws PileVideException si pile vide.
	 */
	private void preConditionPileNonVide() throws PileVideException {
		/* pré-condition : pile non vide */
		if (isVide()) {
			throw new PileVideException("Pré-condition pile non vide échoue");
		}
	}
	
}
