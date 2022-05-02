# Analyse Error

## Sommaire

1. [Résumé](../README.md)
2. [Jenkins](Jenkins.md)
3. [Log](Log.md)
4. [Analyse Time](AnalyseTime.md)
5. [Analyse Compile](AnalyseCompile.md)
6. [Analyse Tests Phase](AnalyseTestsPhase.md)
7. [Analyse Tests Classe](AnalyseTestsClasse.md)
8. [Evolution Variable](EvolutionVariable.md)
9. **Analyse Error**

## Description

**Analyse Compile** est la partie de l'API qui va permettre d'extraire des tendances sur le nombre de fichiers compilés pour les différentes phase (compilation, test, package).

---

### Proportion de build ayant échoué

methode : **GET**

path : `/analyse/error/proportion/{projectName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Nombre de fois qu'apparait chaque type d'erreur

methode : **GET**

path : `/analyse/error/types/number/{projectName}?log1=0|<int>&log2=-1|<int>&module=0|<int>`
- projectName : nom du projet cible
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0