# Application Pokedex
Projet de programmation web orientée mobile 4A en __Kotlin__.

## Présentation

Au lancement de l'application, un écran de chargement apparaît suivi d'un écran de connexion. L'utilisateur peut se connecter s'il possède déjà un compte sinon, un pop-up lui indique qu'il faut en créer un. L'écran de création de compte gère si l'utilisateur a bien rentré un email et un mot de passe. Il peut désormais accéder à la liste de Pokémons et au détail de chacun de ceux-ci en cliquant sur les éléments. 

API utilisée : https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json 

## Prérequis

* Installation d'Android Studio 
* Acquis 3A (en Java) : 
    * Affichage d’écrans simples (activity)
    * Affichage liste (recyclerview)
    * Récupération de données en utilisant une API Rest (retrofit)
    * Stockage de données (SharedPreferences)
    * Architecture logicielle MVC
    * Utilisation de Github

## Consignes respectées

* Langage Kotlin 
* Architecture MVVM
* Clean Architecture
* Appel Webservice à une API REST 
* Affichage d’une liste 
* Design
* Création d'une base données avec utilisation de Room 
* Fonction(s) supplémentaire(s) : 
   * Ecran avec le détail d'un élément de la liste 
   * Quelques tests unitaires
   * Utilisation de Glide 

## Fonctionnalités

### Logo de l'application Pokedex

![github-small](https://github.com/MarieDouguet/Android4A/blob/master/images/logo.jpg)

### Ecran Splash

Animations 

<img src="https://github.com/MarieDouguet/Android4A/blob/master/images/splash.gif" width="190" height="400" />

### Ecran de login 

Animation de l'image 

Si l'utilisateur n'a pas de compte, un pop-up lui indique de s'en créer un ou sinon il peut directement accéder à la création de compte via le bouton "create account".
Le mot de passe peut être rendu visible ou pas. 

<div class = "images">
   
<img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/sign_in_password_invisible.jpg" width="190" height="400"> <img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/sign_in_password_visible.jpg" width="190" height="400"> <img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/pop_up_creer_compte.jpg" width="190" height="400">
    
</div>

### Ecran de création de compte 

Gestion des erreurs si l'email et/ou le mot de passe manquent. 
Aussi, un bouton "cancel" permet de revenir à l'écran de login. 
Après création du compte, un pop-up indique que le compte a été créé et il est automatiquement redirigé vers l'écran de login. 

<div class = "images">
   
<img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/creation_compte.jpg" width="190" height="400"> <img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/email_password_required.jpg" width="190" height="400"> 
<img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/email_required.jpg" width="190" height="400"> <img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/password_required.jpg" width="190" height="400"> 
<img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/account_created.jpg" width="190" height="400">
    
</div>

### Ecran liste de Pokémon 

Après s'être connecté, l'utilisateur accède directement au Pokedex. 

RecyclerView avec appel API
Liste des Pokémons sous forme de CardView
Utilisation de Glide pour récupérer les images de chaque pokémon

<img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/list.jpg" width="190" height="400">


### Ecran de détail d'un Pokémon

Utilisation de picasso pour récupérer les images de chaque pokémon après avoir cliqué sur la card view.

<img src ="https://github.com/MarieDouguet/Android4A/blob/master/images/detail_element.jpg" width="190" height="400">
