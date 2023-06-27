package iut.info1.sae.algorithmiquegestion.sauvegardes;

import java.lang.reflect.Field;

import iut.info1.sae.algorithmiquegestion.composants.Labyrinthe;

public class VerificationInitialisationAttributs {

    public static boolean areTousLesAttributsInitialisé(Labyrinthe labyrinthe) {
        // Obtient la classe de l'objet
        Class<?> objClass = labyrinthe.getClass();

        // Obtient la liste des attributs déclarés de la classe
        Field[] fields = objClass.getDeclaredFields();

        // Parcours tous les attributs
        for (Field field : fields) {
            field.setAccessible(true); // Permet d'accéder à des attributs privés

            try {
                // Vérifie si l'attribut a une valeur null
                if (field.get(labyrinthe) == null) {
                    return false; // Un attribut non initialisé trouvé
                }
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                // Gestion de l'exception si l'accès à l'attribut est impossible
                e.printStackTrace();
            }
        }

        // Tous les attributs sont initialisés
        return true;
    }
}
