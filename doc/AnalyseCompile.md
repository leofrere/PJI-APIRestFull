# Analyse Compile

## Sommaire

1. [Résumé](https://github.com/leofrere/PJI-APIRestFull/blob/master/README.md)
2. [Jenkins](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Jenkins.md)
3. [Log](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Log.md)
4. [Analyse Time](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTime.md)
5. **Analyse Compile**
6. [Analyse Tests Phase](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTestsPhase.md)
7. [Analyse Tests Classe](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTestsClasse.md)
8. [Analyse Error](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseError.md)

## Description

**Analyse Compile** est la partie de l'API qui va permettre d'extraire des tendances sur le nombre de fichiers compilés pour les différentes phase (compilation, test, package).

---

### Différence du nombre de classe compilée entre deux logs

methode : **GET**

path : `/analyse/error/difference/{projectName}/{phaseName}?log1=<int>&log=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de classe compilée entre deux logs en pourcentage

methode : **GET**

path : `/analyse/compile/difference/percent/{projectName}/{phaseName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de classe compilée en moyenne entre deux logs consécutif

methode : **GET**

path : `/analyse/compile/mean/difference/{projectName}/{phaseName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de classe compilée en moyenne entre deux logs consécutif en pourcentage

methode : **GET**

path : `/analyse/compile/mean/difference/percent/{projectName}/{phaseName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Nombre de classe compilée en moyenne

methode : **GET**

path : `/analyse/compile/mean/{projectName}/{phaseName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Médian de classe compilée

methode : **GET**

path : `/analyse/compile/median/{projectName}/{phaseName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Premier quartile de classe compilée

methode : **GET**

path : `/analyse/compile/quartile/first/{projectName}/{phaseName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Troisième quartile de classe compilée

methode : **GET**

path : `/analyse/compile/quartile/third/{projectName}/{phaseName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0