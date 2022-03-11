# Arbre_Classification

Projet test de compétences OnePoint.

Testé et réalisé avec Android 12 (API level 32), sous Android Studio Bumblebee

L'application a été réalisé avec JetPack Compose.

Une clean architecture et le pattern MVVM ont été utilisé pour structurer le projet.

Pour l'installation, simplement cloner ou télécharger le projet.

### Branches

RoomDB contient le projet classique, avec la gestion de réseau, mais contient également une base de données Room, mettant les arbres en cache et les affiche en mode offline. Avec JetPack Compose. Possibilité d'ajouter des arbres dans la base de données en développement. Possibilité d'ajouter un arbre (l'ajoute dans le cache) et d'en supprimer. Récupère les arbres avec Retrofit et son cache

Master contient le projet classique, Clean Architecture, MVVVM, Retrofit, Dagger-Hilt, Lazy Loading, avec Jetpack Compose.

Databinding contient le projet classique, mais utilise les layout XML et du databinding à la place de JetPack Compose, et contient également la gestion de gain et perte de connexion.

RealmDB est similaire à la branche RoomDB, mais utilise Realm au lieu de Room.
