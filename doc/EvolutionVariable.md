# Evolution Variable

## Sommaire

1. [Résumé](../README.md)
2. [Jenkins](Jenkins.md)
3. [Log](Log.md)
4. [Analyse Time](AnalyseTime.md)
5. [Analyse Compile](AnalyseCompile.md)
6. [Analyse Tests Phase](AnalyseTestsPhase.md)
7. [Analyse Tests Classe](AnalyseTestsClasse.md)
8. [Analyse Error](AnalyseError.md)
9. **Evolution Variable**
10. [GraphQL Request](GraphQLRequest.md)

## Description

**Evolution Variable** est la partie de l'API qui va permettre le suivi de l'évolution d'une variable.

---

### Evolution d'une variable d'une phase

methode : **GET**

path : `/evolution/phase/{projectName}/{phaseName}/{variable}?module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- - variable : nom de la variable (commune : time, status, errorsTrace | compile : compiledClasses, testsRun, testsError, testsSkipped, testsFailed | test : compiledClasses, | package : jarPath)
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Evolution d'une varaible d'un TestClasse

methode : **GET**

path : `/evolution/test/{projectName}/{classeName}/{variable}?module=0|<int>`
- projectName : nom du projet cible
- classeName : nom de la classe cible (ex: package-Class)
- variable : nom de la variable (testsRun, testsError, testsSkipped, testsFailed, time)
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Regression linéaire de l'évolution du temps

methode : **GET**

path : `/evolution/regression/{projectName}/{module}/{phase}/time`
- projectName : nom du projet cible
- module : nom du module cible
- phase : phase cible (compilePhases, testPhases, packagePhases, testClasses)

### Regression linéaire de l'évolution du nombres de tests par rapport au temps

methode : **GET**

path : `/evolution/regression/{projectName}/{module}/{phase}/tests/{type}`
- projectName : nom du projet cible
- module : nom du module cible
- phase : phase cible (testPhases, testClasses)
- type : type de test (run, failed, skipped, error)

### Regression linéaire de l'évolution du nombre de classe compilée par rapport au temps

methode : **GET**

path : `/evolution/regression/{projectName}/{module}/{phase}/compile`
- projectName : nom du projet cible
- module : nom du module cible
- phase : phase cible (compilePhases, testPhases)

### Regression linéaire de l'évolution de la variable au choix

methode : **GET**

path : `/evolution/regression/{projectName}/{module}/{phase}/{variable}`
- projectName : nom du projet cible
- module : nom du module cible
- phase : phase cible (compilePhases, testPhases, packagePhases, testClasses)
- variable : variable de la phase voulu

### Indice de la variable au choix

methode : **GET**

path : `/evolution/indice/{projectName}/{module}/{phase}/{variable}`
- projectName : nom du projet cible
- module : nom du module cible
- phase : phase cible (compilePhases, testPhases, packagePhases, testClasses)
- variable : variable de la phase voulu

### coefficient multiplicateur de la variable au choix

methode : **GET**

path : `/evolution/coef/{projectName}/{module}/{phase}/{variable}`
- projectName : nom du projet cible
- module : nom du module cible
- phase : phase cible (compilePhases, testPhases, packagePhases, testClasses)
- variable : variable de la phase voulu