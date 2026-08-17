# SeleniumWithJava

Java-based UI/API automation practice project.

## Modernization

The project is being migrated from the legacy Gradle/Selenium stack to:

- Java 17 toolchain
- Gradle 9.6.1
- Selenium 4.46.0
- TestNG
- REST Assured
- Maven Central

Generated IDE/build/test artifacts are excluded through `.gitignore`.

## Run

```bash
./gradlew test
```

The existing test suite may require incremental source-level compatibility fixes as legacy Selenium APIs are replaced.
