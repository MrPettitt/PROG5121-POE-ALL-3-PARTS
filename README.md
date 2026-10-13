[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/GHE8yEmT)

# PROG5121-POE-PART1

**PROG5121 POE Part 1: User Registration and Login**
**Author:** Richard Pettitt, ST10090394

This is Part 1 of the POE and lives on the `part-1` branch. Parts 2 and 3 (final) will have their own branches.

A Java console application that registers a user and then logs them in. It checks that the username and password meet set rules.

## Features

- Captures the user's first and last name.
- Validates the **username**: it must contain an underscore (`_`) and be no more than 5 characters long.
- Validates the **password**: it must be at least 8 characters long, with no spaces, and contain:
  - a capital letter
  - a number
  - a special character (`!@#$%^&*`)
- Registers the user and welcomes them by name.
- Logs the user in by comparing the entered credentials with the registered ones, then reports `Login successful` or `Login failed`.

## Project Structure

```
├── src/st10090394/
│   ├── ST10090394.java   # Main class (program entry point)
│   ├── Login.java        # Registration, validation and login logic
│   └── Message.java      # Simple message class
├── test/st10090394/      # JUnit tests (LoginTest, MessageTest, ST10090394Test)
├── testresources/        # JUnit console launcher JAR
├── nbproject/, build.xml # NetBeans / Ant project files
└── .github/workflows/    # GitHub Actions workflow (PROGPOE.yml)
```

## Requirements

- Java JDK 8 or later
- Optional: [Apache NetBeans](https://netbeans.apache.org/), which the project files are set up for

## Running the Program

**In NetBeans:** open the project and click **Run**.

**From the command line** (from the project root):

```bash
javac -d build/classes src/st10090394/*.java
java -cp build/classes st10090394.ST10090394
```

You will be prompted for:

1. First name and last name
2. A username and a password to register (they must pass the rules above)
3. The username and password to log in with

## Running the Tests

The tests use JUnit 5 through the bundled console launcher. From the project root:

```bash
javac -cp testresources/junit-platform-console-standalone-1.6.0.jar -d build/classes src/st10090394/*.java test/st10090394/*.java
java -jar testresources/junit-platform-console-standalone-1.6.0.jar -cp build/classes --scan-classpath
```

## Continuous Integration

`.github/workflows/PROGPOE.yml` runs on every push or pull request to `main`. It compiles the source and tests, then runs `MessageTest` and `LoginTest` with the JUnit console launcher. It can also be started manually from the **Actions** tab.
