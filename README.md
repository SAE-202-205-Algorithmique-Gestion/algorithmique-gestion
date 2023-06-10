# algorithmique-gestion

Bienvenue dans ce projet de labyrinthe !

## Configuration du JDK

Ce projet nécessite JDK-20 pour être compilé et exécuté correctement. Assurez-vous d'avoir JDK-20 installé sur votre système. Si vous ne l'avez pas encore, vous pouvez le télécharger à partir du site officiel d'Oracle et l'installer avant de poursuivre la configuration du projet.
N'oubliez pas de configurer votre build path sous Eclipse afin d'y ajouter jdk-20.

## Configuration des bibliothèques Gson

Ce projet utilise la bibliothèque Gson pour le traitement des données JSON. Assurez-vous de configurer les bibliothèques Gson dans votre environnement de développement.

1. Téléchargez la bibliothèque Gson (version 2.10.1) à partir du site officiel ou d'un référentiel Maven.
2. Placez les fichiers JAR de la bibliothèque Gson à la racine du projet.
   - gson-2.10.1.jar
   - gson-2.10.1-javadoc.jar
3. Dans votre IDE, ajoutez les bibliothèques Gson à votre configuration de projet ou de dépendances en vous assurant de spécifier les chemins corrects vers les fichiers JAR.

Cela permettra de résoudre les dépendances de Gson et de vous assurer que le projet peut être compilé et exécuté correctement.

Si vous utilisez un système de gestion de dépendances tel que Maven ou Gradle, vous pouvez également configurer Gson dans votre fichier de configuration respectif en ajoutant les dépendances appropriées.

## Configuration du dictionnaire français

Ce projet utilise un dictionnaire personnalisé français pour la vérification orthographique dans votre IDE. Pour configurer le dictionnaire français, suivez ces étapes :

1. Copiez le fichier `dictionnaire_fr_utf8.txt` à la racine du projet.
2. Ouvrez votre IDE et accédez aux préférences ou aux paramètres spécifiques à l'éditeur de texte.
3. Recherchez les options de vérification orthographique ou de dictionnaire (General, Editors, Text editor, Spelling).
4. Configurez le chemin du dictionnaire français en sélectionnant le fichier `dictionnaire_fr_utf8.txt` que vous avez copié à la racine du projet.
5. Enregistrez les modifications.

Une fois que vous avez configuré le dictionnaire français, votre IDE utilisera ce dictionnaire pour la vérification orthographique dans vos fichiers de code source français.

## Contribuer

Si vous souhaitez contribuer à ce projet, vous pouvez suivre ces étapes :

1. Fork (Bifurquer) ce référentiel et clonez-le sur votre machine locale.
2. Assurez-vous de configurer correctement votre environnement de développement avec JDK-20 et les bibliothèques Gson.
3. Effectuez les modifications souhaitées.
4. Testez votre code pour vous assurer qu'il fonctionne correctement.
5. Soumettez une demande d'extraction (Pull Request) en expliquant les modifications que vous avez apportées.

N'hésitez pas à signaler tout problème ou à proposer des améliorations en créant une issue dans ce référentiel.
