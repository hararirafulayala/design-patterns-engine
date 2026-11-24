# Design Patterns Engine (In-Memory Data Engine)

An in-memory tabular data engine written in Java that demonstrates how multiple classic object-oriented design patterns work together to model schemas, queries, mutations, validation, and iteration.

## Architecture & Packages

- `core/` — `Database` singleton managing tables and routing operations.
- `models/` — `Table`, `Row`, `Column`, and `Schema` representing the in-memory data model.
- `enums/` — `DataType` (`STRING`, `INT`, `BOOL`).
- `conditions/` — Composable row filtering predicates (`Condition`, `EqualsCondition`, `GreaterThanCondition`, `LessThanCondition`, `AndCondition`, `OrCondition`).
- `commands/` — Encapsulated write operations and execution history (`DatabaseCommand`, `InsertCommand`, `UpdateCommand`, `DeleteCommand`, `CommandInvoker`).
- `validators/` — Schema type validation (`Validator`, `ValidationStrategy`, `TypeValidationStrategy`).
- `iterators/` — Custom row traversal strategies (`RowIterator`, `InsertionOrderIterator`, `FilteredIterator`, `SortedIterator`).
- `test/` — JUnit 5 unit tests (`DatabaseTest`).

## Design Patterns Implemented

- **Singleton:** `Database.getInstance()` ensures a single database instance across the application.
- **Builder:** `Table.Builder` provides a fluent API for constructing tables and defining typed columns (`addColumn(...).build()`).
- **Prototype:** `Table.clone()` creates a deep copy of rows so changes to one table instance do not mutate cloned snapshots.
- **Command:** `InsertCommand`, `UpdateCommand`, and `DeleteCommand` implement `DatabaseCommand` and are executed through `CommandInvoker`, which records executed commands in a history list.
- **Composite:** `AndCondition` and `OrCondition` combine leaf and composite `Condition` predicates and evaluate them recursively against a `Row`.
- **Strategy:** `ValidationStrategy` and `TypeValidationStrategy` decouple column validation rules from `Validator`; row predicates and iterators also separate filtering, sorting, and traversal behaviors.
- **Iterator:** `RowIterator` with `InsertionOrderIterator`, `FilteredIterator`, and `SortedIterator` provides uniform traversal over `Row` collections.

## Running the Project

1. Open the project in your Java IDE (such as IntelliJ IDEA) with JDK and JUnit 5 configured.
2. Run `Main.main(String[])` for an end-to-end demonstration of table creation, insertion, cloning, querying, updating, iteration, and deletion.
3. Run `test.DatabaseTest` to execute the unit tests.
