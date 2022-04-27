# Analyse Time

## Sommaire

1. [Résumé](https://github.com/leofrere/PJI-APIRestFull/blob/master/README.md)
2. [Jenkins](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Jenkins.md)
3. [Log](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Log.md)
4. **Analyse Time**
5. [Analyse Compile](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseCompile.md)
6. [Analyse Tests Phase](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTestsPhase.md)

## Description

**Analyse Time** est la partie de l'API qui va permettre d'extraire des tendances sur le temps pour les différentes phase (compilation, test, package).

---

### Différence de temps entre deux logs

methode : **GET**

path : `/analyse/time/difference/{projectName}/{phaseName}?log1=<int>&log=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence de temps entre deux logs en pourcentage

methode : **GET**

path : `/analyse/time/difference/percent/{projectName}/{phaseName}?log1=<int>&log=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence de temps en moyenne entre deux logs consécutif

methode : **GET**

path : `/analyse/time/mean/difference/{projectName}/{phaseName}?log1=<int>&log=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence de temps en moyenne entre deux logs consécutif en pourcentage

methode : **GET**

path : `/analyse/time/mean/difference/percent/{projectName}/{phaseName}?log1=<int>&log=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Temps moyen

methode : **GET**

path : `/analyse/time/mean/{projectName}/{phaseName}?log1=<int>&log=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0