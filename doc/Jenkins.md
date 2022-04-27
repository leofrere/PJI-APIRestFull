# Jenkins

1. [Résumé](https://github.com/leofrere/PJI-APIRestFull/blob/master/README.md)
2. **Jenkins**
3. [Analyse Time](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTime.md)
4. [Analyse Compile](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseCompile.md)


### Ajouter un projet Jenkins

methode : **GET**

path : `/jenkins/build/create?url=<url>&pj=<project name>&user=<username>&pass=<password or token of project>`
- url : url du serveur Jenkins
- pj : nom du projet cible sur le serveur Jenkins
- user : username pour se connecter au projet
- pass : mot de passe ou le token pour se connecter au projet

### Supprimer un projet Jenkins

methode : **GET**

path : `/jenkins/build/delete/{projectName}`
- projectName : nom du projet cible sur le serveur Jenkins

### Changer le mot de passe ou token

methode : **GET**

path : `/jenkins/build/update/password/{projectName}?pass=<password or token of project>`
- projectName : nom du projet cible sur le serveur Jenkins
- pass : nouveau mot de passe ou le token pour se connecter au projet

### Changer le username

methode : **GET**

path : `/jenkins/build/update/username/{projectName}/{username}`
- projectName : nom du projet cible sur le serveur Jenkins
- username : nouveau nom d'utilisateur pour se connecter au projet

### Changer l'url

methode : **GET**

path : `/jenkins/build/update/url/{projectName}?url=<url>`
- projectName : nom du projet cible sur le serveur Jenkins
- url : nouveau url du serveur Jenkins  

### Changer le nom du projet jenkins

methode : **GET**

path : `/jenkins/build/update/project/{projectName}/{newProjectName}`
- projectName : nom du projet cible sur le serveur Jenkins
- newProjectName : nouveau nom du projet cible sur le serveur Jenkins