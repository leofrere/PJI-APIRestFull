# PJI-APIRestFull

## Sommaire

1. [Analyse Time](#analyse-time)
2. [Analyse Compile](#analyse-compile)

## Analyse Time

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

## Analyse Compile

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
