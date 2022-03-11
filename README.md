# Arbre_Classification

Projet test de compétences OnePoint.

Testé et réalisé avec Android 12 (API level 32), sous Android Studio Bumblebee

L'application a été réalisé avec JetPack Compose.

Une clean architecture et le pattern MVVM ont été utilisé pour structurer le projet.

Pour l'installation, simplement cloner ou télécharger le projet.

### Fonctionnement général

Au lancement, une requête API est lancée afin de récupérer la liste des arbres à afficher. 
Le ViewModel crée, en fonction du résultat de la requête, un objet de type TreesListState contenant la liste des arbres en cas de succès, ou bien un message d'erreur qui ser affiché en cas d'échec.
Dans le cas du succès, le numéro de l'arbre est affiché, ainsi que son espèce et un bouton pour avoir les autres informations.
En cas d'erreur (API ou connection) un message d'erreur est affiché.

La MainActivity contient un NavController permettant de passer d'un écran à l'autre.
Lors du click sur un arbre, on "navigate" vers l'écran de détail de l'arbre. On utilise la libraire NavDestination de raamcosta pour transférer l'arbre d'un écran à l'autre. En appuyant sur le retour arrière du téléphone, on retourne sur la liste des arbres.


### API

Les appels API ont été réalisé avec Retrofit.

Une première requêtes récupère 20 arbres, à parti d'un index, pour réaliser du Lazy Loading.
Une seconde requête récupère les arbres déjà présent dans le cache Retrofit. Cette requête est réalisée par défaut si le cache n'excède pazs une heure.

La sealed class ApiResponse gère les erreurs, et envoit la data ou un message d'erreur selon le code reçu.

###Room

Dans le cas où le cache Retrofit serait trop vieux, et que le téléphone n'a pas accès à Internet, l'application récupère les arbres enregistrées en base de données avec RoomDB.

Il est alors possible de récupérer les arbres, mais aussi d'en ajouter et d'en supprimer (DismissState utilisé avec JetPack Compose)

### Injection de dépendances

Les injections de dépendances sont réalisées grâce à Dagger-Hilt version 2.38.1

### Features optionelles

Une feature d'ajout d'arbre sur l'api a été ajouté (bien qu'il nous soit interdit d'en ajouter), afin de démontrer l'architecture avec différents uses cases.
On peut supprimer des arbres directement depuis la liste, ou bien sur l'écran de détail.
On peut également rechercher un arbre dans la liste.
Le code n'a pas été offusqué pour des raisons de simplicité.
