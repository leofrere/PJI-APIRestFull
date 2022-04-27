# Log

## Sommaire

1. [Résumé](https://github.com/leofrere/PJI-APIRestFull/blob/master/README.md)
2. [Jenkins](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Jenkins.md)
3. **Log**
4. [Analyse Time](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTime.md)
5. [Analyse Compile](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseCompile.md)

### Ajouter un log

methode : **GET**

path : `/log/create/{projectName}/{buildNumber}`
- projectName : nom du projet cible
- buildNumber : numéro du build cible

### Ajouter tous les logs manquant

methode : **GET**

path : `/log/creates/{projectName}`
- projectName : nom du projet où ajouter tous les logs manquant

### Afficher le n-ème log

methode : **GET**

path : `/log/{n}`
- n : n-ème log à afficher

### Afficher les logs d'un projet

methode : **GET**

path : `/log/project/{projectName}`
- projectName : nom du project à afficher les logs

### Afficher le n-ème log d'un projet

methode : **GET**

path : `/log/project/{projectName}/{n}`
- projectName : nom du projet cible
- n : n-ème log à afficher

### Supprimer le n-ème log

methode : **GET**

path : `/log/delete/{n}`
- n : n-ème log à supprimer