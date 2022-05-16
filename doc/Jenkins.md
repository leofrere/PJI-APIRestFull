# Jenkins

## Sommaire

1. [Résumé](../README.md)
2. **Jenkins**
3. [Log](Log.md)
4. [Analyse Time](AnalyseTime.md)
5. [Analyse Compile](AnalyseCompile.md)
6. [Analyse Tests Phase](AnalyseTestsPhase.md)
7. [Analyse Tests Classe](AnalyseTestsClasse.md)
8. [Analyse Error](AnalyseError.md)
9. [Evolution Variable](EvolutionVariable.md)
10. [GraphQL Request](GraphQLRequest.md)


## Description

Dans l'API, nous avons besoin de pouvoir se connecter à un serveur Jenkins afin d'y récupérer les logs de build Maven. Dans ce but, il existe une entité **JenkinsBuild** stockée dans une base de données pour permettre d'avoir les informations nécessaire, URL, nom de projet, username et password , pour se connecter sur Jenkins et obtenir les données d'un projet.

---

### Ajouter un projet Jenkins

methode : **POST**

path : `/jenkins/build/create?url=<url>&pj=<project name>&user=<username>&pass=<password or token of project>&type=<type of project>&time<boolean>`
- url : url du serveur Jenkins
- pj : nom du projet cible sur le serveur Jenkins
- user : username pour se connecter au projet (optionnel)
- pass : mot de passe ou le token pour se connecter au projet (optionnel)
- type : type de build maven (singleBuild, multiBuilds et multiModules)
- time : vrie si le temps est affiché sinon faux

### Supprimer un projet Jenkins

methode : **POST**

path : `/jenkins/build/delete/{projectName}`
- projectName : nom du projet cible sur le serveur Jenkins

### Changer le mot de passe ou token

methode : **POST**

path : `/jenkins/build/update/password/{projectName}?pass=<password or token of project>`
- projectName : nom du projet cible sur le serveur Jenkins
- pass : nouveau mot de passe ou le token pour se connecter au projet

### Changer le username

methode : **POST**

path : `/jenkins/build/update/username/{projectName}/{username}`
- projectName : nom du projet cible sur le serveur Jenkins
- username : nouveau nom d'utilisateur pour se connecter au projet

### Changer l'url

methode : **POST**

path : `/jenkins/build/update/url/{projectName}?url=<url>`
- projectName : nom du projet cible sur le serveur Jenkins
- url : nouveau url du serveur Jenkins  

### Changer le nom du projet jenkins

methode : **POST**

path : `/jenkins/build/update/project/{projectName}/{newProjectName}`
- projectName : nom du projet cible sur le serveur Jenkins
- newProjectName : nouveau nom du projet cible sur le serveur Jenkins