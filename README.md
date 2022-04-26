# PJI-APIRestFull

## Résumé

L'intégration continue (CI/CD, pour Continuous Integration /Continuous Delivery) propose d'automatiser le pipeline de compilation et d'assemblage des logiciels pour assurer la reproductibilité de ce processus. Dans le cadre de ce projet, nous nous intéressons à l'étude des exécutions (builds) d'un projet qui aurait recours à un processus d'intégration continue afin d'étudier l'évolution dans le temps de ses différentes phases (e.g., compilation, test, packaging), afin de détecter de potentielles dérives (e.g., explosion du nombre de tests unitaires et du temps passé dans cette phase).

Mots Clés : continuous integration, microservice, REST, software engineering

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
