# GraphQL Request

## Sommaire

1. [Résumé](../README.md)
2. [Jenkins](Jenkins.md)
3. [Log](Log.md)
4. [Analyse Time](AnalyseTime.md)
5. [Analyse Compile](AnalyseCompile.md)
6. [Analyse Tests Phase](AnalyseTestsPhase.md)
7. [Analyse Tests Classe](AnalyseTestsClasse.md)
8. [Analyse Error](AnalyseError.md)
9. [Evolution Variable](EvolutionVariable.md)
10. **GraphQL Request**

## Description

**GraphQL Request** est la partie de l'API permettant d'effectuer une requete sur graphQL à partir d'une query et d'obtenir sont résultat sous une string.

---

### Effectuer une requête

methode : **GET**

path : `/request?query=<query graphQL>`
- query : query graphQL (/!\\ ne pas oublier de donner une query encoder pour une URL avec un URLEncoder)

### Querys GraphQL

```graphql
type Query {
  ordersByProject(project: String): [Order]
  packagePhasesOfTime(op: String, project: String, time: String): [PackagePhase]
  packagePhasesByProject(project: String): [PackagePhase]
  compilePhaseByTime(op: String, project: String, time: String): [CompilePhase]
  testPhaseById(id: Long!): TestPhase
  log(n: Int!): Log
  testPhases: [TestPhase]
  testPhasesOfTest(op: String test: String nbTest: Float! project: String): [TestPhase]
  testClasses: [TestClasse]
  compilePhases: [CompilePhase]
  testClassesOfTest(op: String test: String nbTest: Float! project: String): [TestClasse]
  packagePhases: [PackagePhase]
  testClassesOfTime(op: String, project: String, time: String): [TestClasse]
  compilePhaseByCompiled(op: String compiled: Int! project: String): [CompilePhase]
  compilePhaseByProject(project: String): [CompilePhase]
  testClassesOfProject(project: String): [TestClasse]
  testPhaseByCompiled(op: String, compiled: Int!, project: String): [TestPhase]
  logsByProject(project: String): [Log]
  orders: [Order]
  logs: [Log]
  testPhaseOfTime(op: String, project: String, time: String): [TestPhase]
  order(id: Long!): Order
  testClassesOfClass(project: String, class: String): [TestClasse]
}
```

### Shemas GraphQL

```graphql
type Log {
  build: Int!
  id: Long!
  orders: [Order]
  project: String
}

type Order {
  build: Int!
  compilePhase: CompilePhase
  id: Long!
  name: String
  packagePhase: PackagePhase
  project: String
  testPhase: TestPhase
}

type CompilePhase {
  build: Int!
  compiledClasses: Int!
  errorsTrace: String
  id: Long!
  numberOfClasses: Int!
  project: String
  status: String
  time: String
  timeFloat: Float!
}

type TestPhase {
  build: Int!
  compiledClasses: Int!
  errorsTrace: String
  id: Long!
  numberOfTestClasses: Int!
  project: String
  status: String
  testsByClasse: [TestClasse]
  testsError: Int!
  testsFailed: Int!
  testsRun: Int!
  testsSkipped: Int!
  time: String
  timeFloat: Float!
}

type PackagePhase {
  build: Int!
  errorsTrace: String
  id: Long!
  jarPath: String
  project: String
  status: String
  time: String
  timeFloat: Float!
}

type TestClasse {
  build: Int!
  classe: String
  id: Long!
  project: String
  testsError: Int!
  testsFailed: Int!
  testsRun: Int!
  testsSkipped: Int!
  time: String
  timeFloat: Float!
}
```