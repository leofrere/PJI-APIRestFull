# Analyse Time

## Sommaire

1. [Résumé](../README.md)
2. [Jenkins](Jenkins.md)
3. [Log](Log.md)
4. **Analyse Time**
5. [Analyse Compile](AnalyseCompile.md)
6. [Analyse Tests Phase](AnalyseTestsPhase.md)
7. [Analyse Tests Classe](AnalyseTestsClasse.md)
8. [Analyse Error](AnalyseError.md)
9. [Evolution Variable](EvolutionVariable.md)
10. [GraphQL Request](GraphQLRequest.md)

## Description

**Analyse Time** est la partie de l'API qui va permettre d'extraire des tendances sur le temps pour les différentes phase (compilation, test, package).

---

### Différence de temps entre deux logs

methode : **GET**

path : `/analyse/time/difference/{projectName}/{phaseName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence de temps entre deux logs en pourcentage

methode : **GET**

path : `/analyse/time/difference/percent/{projectName}/{phaseName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence de temps en moyenne entre deux logs consécutif

methode : **GET**

path : `/analyse/time/mean/difference/{projectName}/{phaseName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence de temps en moyenne entre deux logs consécutif en pourcentage

methode : **GET**

path : `/analyse/time/mean/difference/percent/{projectName}/{phaseName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Temps moyen

methode : **GET**

path : `/analyse/time/mean/{projectName}/{phaseName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Temps médian

methode : **GET**

path : `/analyse/time/median/{projectName}/{phaseName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Temps premier quartile

methode : **GET**

path : `/analyse/time/quartile/first/{projectName}/{phaseName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Temps troisième quartile

methode : **GET**

path : `/analyse/time/quartile/third/{projectName}/{phaseName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0