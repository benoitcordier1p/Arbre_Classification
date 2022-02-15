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

Lorsqu'on clique sur le bouton, l'application convertit l'arbre en JSON string, puis le repasse en objet pour la navigation.
On récupère alors les informations de l'arbre sur le viewModel, et on créé, de manière analogue à TreeListstate, un objet de type TreeState, contenant les informations de l'arbre, ou un message d'erreur.

La MainActivity contient un NavController permettant de passer d'un écran à l'autre.
Lors du click sur un arbre, on "navigate" vers l'écran de détail de l'arbre. En appuyant sur le retour arrière du téléphone, on retourne sur la liste des arbres.


### API

Les appels API ont été réalisé avec Retrofit.

Une des requêtes récupère 20 arbres, avec le minimum d'informations possible (afin de ne pas ralentir l'application dans le cas où l'on souhaiterait récupérer un plus grand nombre d'arbre).
### Injection de dépendances

Les injections de dépendances sont réalisées grâce à Dagger-Hilt version 2.38.1

### Tests

Un Unit test a été réalisé. Il teste que la fonction retournant la position en fonction du "recordId".
Un UI test a été réalisé. Il teste que la liste d'arbre s'affiche bien après la requête API. Un second test ne fonctionne pas encore (problème avec le performClcik())

### Features optionelles

Un début de feature d'ajout d'arbre sur l'api a été ajouté (bien qu'il nous soit interdit d'en ajouter), afin de démontrer l'architecture avec différents uses cases.
Le code n'a pas été offusqué pour des raisons de simplicité.
