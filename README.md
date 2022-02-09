# Arbre_Classification

Projet test de compétences OnePoint.

Testé et réalisé avec Android 12 (API level 32).

L'application a été réalisé avec JetPack Compose.

Une clean architecture et le pattern MVVM ont été utilisé pour structurer le projet.

### Fonctionnement général

Au lancement, une requête API est lancée afin de récupérer la liste des arbres à afficher. 
Le ViewModel créé en fonction du résultat de la requête un objet de type TreesListState contenant la liste des arbres en cas de succès, ou bien un message d'erreur qui ser affiché en cas d'échec.
Dans le cas du succès, le numéro de l'arbre est affiché, ainsi que son espèce et qu'un bouton pour avoir les autres informations.

Lorsqu'on clique sur le bouton, l'application récupère la position de l'arbre dans la requête grâce à son recordid.
Une seconde requête est alors construite, en demandant un seul arbre, situé à la position trouvée précédemment.
On récupère alors les informations de l'arbre, et on créé, de manière analogue à TreeListstate, un objet de type TreeState, contenant les informations de l'arbre, ou un message d'erreur.

La MainActivity contient un NavController permettant de passer d'un écran à l'autre.
Lors du click sur un arbre, on "navigate" vers l'écran de détail de l'arbre. En appuyant sur le retour arrière du téléphone, on retourne sur la liste des arbres.


### API

Les appels API ont été réalisé avec Retrofit.
Une des requêtes récupère 20 arbres, avec le minimum d'informations possible (afin de ne pas ralentir l'application dans le cas où l'on souhaiterait récupérer un plus grand nombre d'arbre).
La seconde requêtes récupère un unique arbre, avec toutes informations (grâce à sa position dans les fichiers de opendata.paris.fr)

### Injection de dépendances

Les injections de dépendances sont réalisés grâce à Dagger-Hilt version 2.38.1

