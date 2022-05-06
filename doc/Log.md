# Log

## Sommaire

1. [Résumé](../README.md)
2. [Jenkins](Jenkins.md)
3. **Log**
4. [Analyse Time](AnalyseTime.md)
5. [Analyse Compile](AnalyseCompile.md)
6. [Analyse Tests Phase](AnalyseTestsPhase.md)
7. [Analyse Tests Classe](AnalyseTestsClasse.md)
8. [Analyse Error](AnalyseError.md)
9. [Evolution Variable](EvolutionVariable.md)
10. [GraphQL Request](GraphQLRequest.md)

## Description

Pour aider la lecture de build Maven, une entité **Log** permet de synthétyser les informations, sur les phases de compilation, test et package, contenues dans les logs brut Maven.

---

### Ajouter un log

methode : **POST**

path : `/log/create/{projectName}/{buildNumber}`
- projectName : nom du projet cible
- buildNumber : numéro du build cible

### Ajouter tous les logs manquant

methode : **POST**

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

methode : **POST**

path : `/log/delete/{n}`
- n : n-ème log à supprimer