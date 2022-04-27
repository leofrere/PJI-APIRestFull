# Analyse Error

## Sommaire

1. [Résumé](https://github.com/leofrere/PJI-APIRestFull/blob/master/README.md)
2. [Jenkins](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Jenkins.md)
3. [Log](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Log.md)
4. [Analyse Time](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTime.md)
5. [Analyse Compile](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseCompile.md)
6. [Analyse Tests Phase](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTestsPhase.md)
7. [Analyse Tests Classe](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTestsClasse.md)
8. **Analyse Error**

## Description

**Analyse Compile** est la partie de l'API qui va permettre d'extraire des tendances sur le nombre de fichiers compilés pour les différentes phase (compilation, test, package).

---

### Proportion de build ayant échoué

methode : **GET**

path : `/analyse/error/proportion/{projectName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0

### Nombre de fois qu'apparait chaque type d'erreur

methode : **GET**

path : `/analyse/error/types/number/{projectName}?log1=<int>&log2=<int>&module=0|<int>`
- projectName : nom du projet cible
- log1 : numéro du premier log
- log2 : numéro du deuxième log
- module (optionnel) : numéro du module ciblé par défaut il vaut 0