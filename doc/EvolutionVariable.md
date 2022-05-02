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

## Description

**Evolution Variable** est la partie de l'API qui va permettre le suivi de l'évolution d'une variable.

---

### Evolution d'une variable d'une phase

methode : **GET**

path : `/phase/{projectName}/{phaseName}/{variable}?module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- - variable : nom de la variable (commune : time, status, errorsTrace | compile : compiledClasses, testsRun, testsError, testsSkipped, testsFailed | test : compiledClasses, | package : jarPath)
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Evolution d'une varaible d'un TestClasse

methode : **GET**

path : `/test/{projectName}/{classeName}/{variable}?module=0|<int>`
- projectName : nom du projet cible
- classeName : nom de la classe cible (ex: package-Class)
- variable : nom de la variable (testsRun, testsError, testsSkipped, testsFailed, time)
- module (optionnel) : numéro du module ciblé par défaut il vaut 0