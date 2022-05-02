# Analyse Tests Phase

## Sommaire

1. [Résumé](../README.md)
2. [Jenkins](Jenkins.md)
3. [Log](Log.md)
4. [Analyse Compile](AnalyseTime.md)
5. [Analyse Compile](AnalyseCompile.md)
6. **Analyse Tests Phase**
7. [Analyse Tests Classe](AnalyseTestsClasse.md)
8. [Analyse Error](AnalyseError.md)
9. [Evolution Variable](EvolutionVariable.md)

## Description

**Analyse Tests Phase** est la partie de l'API qui va permettre d'extraire des tendances sur l'ensemble des tests.

---

### Différence du nombre de tests entre deux logs

methode : **GET**

path : `/analyse/test/phase/difference/{projectName}/{testType}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de tests entre deux logs en pourcentage

methode : **GET**

path : `/analyse/test/phase/difference/percent/{projectName}/{testType}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de tests en moyenne entre deux logs consécutif

methode : **GET**

path : `/analyse/test/phase/mean/difference/{projectName}/{testType}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Différence du nombre de tests en moyenne entre deux logs consécutif en pourcentage

methode : **GET**

path : `/analyse/test/phase/mean/difference/percent/{projectName}/{testType}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Nombre de tests en moyenne

methode : **GET**

path : `/analyse/test/phase/mean/{projectName}/{testType}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Médianne de tests

methode : **GET**

path : `/analyse/test/phase/median/{projectName}/{testType}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Premier quartile de tests

methode : **GET**

path : `/analyse/test/phase/quartile/first/{projectName}/{testType}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Troisième quartile de tests

methode : **GET**

path : `/analyse/test/phase/quartile/third/{projectName}/{testType}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- testType : nom du type de test ciblé (run, failed, skipped, error)
- log1 : numéro du premier log
- log2 : numéro du dernier log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0