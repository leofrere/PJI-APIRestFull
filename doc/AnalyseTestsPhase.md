# Analyse Tests Phase

## Sommaire

1. [Résumé](https://github.com/leofrere/PJI-APIRestFull/blob/master/README.md)
2. [Jenkins](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Jenkins.md)
3. [Log](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Log.md)
4. [Analyse Compile](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTime.md)
5. [Analyse Compile](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseCompile.md)
6. **Analyse Tests Phase**
7. [Analyse Tests Classe](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTestsClasse.md)
8. [Analyse Error](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseError.md)

## Description

**Analyse Tests Phase** est la partie de l'API qui va permettre d'extraire des tendances sur l'ensemble des tests.

---

### Différence du nombre de tests entre deux logs

methode : **GET**

path : `/analyse/test/phase/difference/{projectName}/{testType}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de tests entre deux logs en pourcentage

methode : **GET**

path : `/analyse/test/phase/difference/percent/{projectName}/{testType}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de tests en moyenne entre deux logs consécutif

methode : **GET**

path : `/analyse/test/phase/mean/difference/{projectName}/{testType}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de tests en moyenne entre deux logs consécutif en pourcentage

methode : **GET**

path : `/analyse/test/phase/mean/difference/percent/{projectName}/{testType}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Nombre de tests en moyenne

methode : **GET**

path : `/analyse/test/phase/mean/{projectName}/{testType}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0