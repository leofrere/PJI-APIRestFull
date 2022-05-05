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
  packagePhasesOfTime(op: String module: String project: String time: String): [PackagePhase]
  testPhasesByProject(module: String, project: String): [TestPhase]
  packagePhasesByProject(module: String, project: String): [PackagePhase]
  compilePhaseByTime(op: String module: String project: String time: String): [CompilePhase]
  testPhaseById(id: Long!): TestPhase
  log(n: Int!): Log
  testPhasesOfTest(op: String test: String module: String nbTest: Float! project: String): [TestPhase]
  testClasses: [TestClasse]
  testClassesOfTest(op: String test: String module: String nbTest: Float! project: String): [TestClasse]
  testClassesOfTime(op: String module: String project: String time: String): [TestClasse]
  testPhaseByCompiled(op: String compiled: Int! module: String project: String): [TestPhase]
  testClassesTimeByTest(test: String module: String project: String): [Map_String_FloatScalar]
  logs: [Log]
  order(id: Long!): Order
  testClassesOfClass(module: String project: String class: String): [TestClasse]
  testPhases: [TestPhase]
  compilePhasesByProject(module: String, project: String): [CompilePhase]
  testPhasesTimeByCompiledClass(module: String project: String): [Map_String_FloatScalar]
  compilePhases: [CompilePhase]
  testPhasesTimeByTest(test: String module: String project: String): [Map_String_FloatScalar]
  packagePhases: [PackagePhase]
  compilePhaseByCompiled(op: String compiled: Int! module: String project: String): [CompilePhase]
  testClassesOfProject(module: String, project: String): [TestClasse]
  logsByProject(project: String): [Log]
  orders: [Order]
  compilePhasesTimeByCompiledClass(module: String project: String): [Map_String_FloatScalar]
  testPhaseOfTime(op: String module: String project: String time: String): [TestPhase]
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
  id: Long
  module: String
  project: String
  status: String
  time: String
  timeFloat: Float!
}

type TestPhase {
  build: Int!
  compiledClasses: Int!
  errorsTrace: String
  id: Long
  module: String
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
  id: Long
  jarPath: String
  module: String
  project: String
  status: String
  time: String
  timeFloat: Float!
}

type TestClasse {
  build: Int!
  classe: String
  id: Long
  module: String
  project: String
  testsError: Int!
  testsFailed: Int!
  testsRun: Int!
  testsSkipped: Int!
  time: String
  timeFloat: Float!
}
```