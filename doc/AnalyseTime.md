# Analyse Time

## Sommaire

1. [Résumé](https://github.com/leofrere/PJI-APIRestFull/blob/master/README.md)
2. **Analyse Time**
3. [Analyse Compile](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseCompile.md)

### Différence de temps entre deux logs

methode : **GET**

path : `/analyse/time/difference/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du deuxième log

### Différence de temps entre deux logs en pourcentage

methode : **GET**

path : `/analyse/time/difference/percent/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du deuxième log

### Différence de temps en moyenne entre deux logs consécutif

methode : **GET**

path : `/analyse/time/mean/difference/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log

### Différence de temps en moyenne entre deux logs consécutif en pourcentage

methode : **GET**

path : `/analyse/time/mean/difference/percent/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log

### Temps moyen

methode : **GET**

path : `/analyse/time/mean/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test, package)
- log1 : numéro du premier log
- log2 : numéro du dernier log