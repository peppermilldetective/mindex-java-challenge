# Added

## ReportingStructure

- The `ReportingStructure` class has been added under `com.mindex.challenge.data`.
  - The class includes an `employeeId` field of type `String` and a `numberOfReports` field of type `int`.
- The `ReportingStructureService` interface has been added under `com.mindex.challenge.service`.
- The `ReportingStructureServiceImpl` class has been added under `com.mindex.challenge.service.impl`.
  - The class includes a `read()` function for fetching the `ReportingStructure` of a given employee ID.
- The `ReportingStructureController` class has been added under `com.mindex.challenge.controller`.
  - The class includes a REST endpoint at `"/directReports/{id}"` for fetching a `ReportingStructure` using a given `employeeId`.

## Compensation

- The `Compensation` class has been added under `com.mindex.challenge.data`.
  - The class includes a the `employeeId` field of type `String`, a `salary` field of type `double`, and an `effectiveDate` field of type `Date`.
    - `effectiveDate` requires a string date in the form `yyyy-MM-dd`. This is set in `application.properties` and can be changed if desired.
- The `CompensationService` interface has been added.
- The `CompensationServiceImpl` class has been added.
  - Includes a `create` function for creating a `Compensation` instance and inserting it into the persistance layer.
  - Includes a `read` function for fetching a `Compensation` for a specified `employeeId`.
- The `CompensationRepository` class has been added.
- The `CompensationController` class has been added.
  - Includes two REST endpoints:
    - `create` as a POST method for creating a new `Compensation`. This is persisted.
    - `read` as a GET method for fetching a `Compensation` via `employeeId`.
- As part of the addition of the `Compensation` class and its associated parts, the `MongoConfig` was updated to use `basePackages = "com.mindex.challenge.dao"`.

## Mindset & Reasonings
- Where applicable, a functional approach was utilized. This is due to my own preference for functional programming as I find it easier to approach and understand the transformations. This is most evident in `ReportingStructureServiceImpl`, where a functional approach made fetching the total number of direct reports easier to me.
- For `Compensation` and `ReportingStructure`, all of their setters return the instance to make function chaining possible. This makes initialization easier to perform and keeps things cleaner. A good example of this can be found in the various tests for `Compensation` and `ReportingStructure`.
- `Employee.directReports` was changed from `List<Employee>` to `List<String>`. This is because the database does not fill out the whole `Employee` class when fetching/storing the `directReport` field. As such, to remove unused data and reduce payload size (and make it easier to use), the field was changed to not use an `Employee`.

## Tests
- Where possible, tests and their names and data matched the existing test for `EmployeeServiceImplTest` as an attempt to adhere to a coding standard.
- Included with the repository are Postman collections that can be used to directly test the API. They do not include tests, but can act as a visual of the API working as designed.

## Repository
- The repository includes a `.gitignore` that is taken from [github's official gradle gitignore template](https://github.com/github/gitignore/blob/main/Gradle.gitignore).
- A license is not included because I was unsure if one was desired. This can be changed if desired.