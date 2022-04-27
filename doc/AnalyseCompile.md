# Analyse Compile

## Sommaire

1. [Résumé](https://github.com/leofrere/PJI-APIRestFull/blob/master/README.md)
2. [Jenkins](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/Jenkins.md)
3. [Analyse Time](https://github.com/leofrere/PJI-APIRestFull/blob/master/doc/AnalyseTime.md)
4. **Analyse Compile**

---

### Différence du nombre de classe compilée entre deux logs

methode : **GET**

path : `/analyse/compile/difference/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du deuxième log

### Différence du nombre de classe compilée entre deux logs en pourcentage

methode : **GET**

path : `/analyse/compile/difference/percent/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du deuxième log

### Différence du nombre de classe compilée en moyenne entre deux logs consécutif

methode : **GET**

path : `/analyse/compile/mean/difference/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log

### Différence du nombre de classe compilée en moyenne entre deux logs consécutif en pourcentage

methode : **GET**

path : `/analyse/compile/mean/difference/percent/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log

### Nombre de classe compilée en moyenne

methode : **GET**

path : `/analyse/compile/mean/{projectName}/{phaseName}?log1=<int>&log=<int>`
- projectName : nom du projet cible
- phaseName : nom de la phase cible (compile, test)
- log1 : numéro du premier log
- log2 : numéro du dernier log