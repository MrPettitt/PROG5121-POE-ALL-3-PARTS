[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/k9JAxeir)

# PROG5121-POE-PART3

**PROG5121 POE, Part 3 (Final): EasyKanban**
**Author:** Richard Pettitt, ST10090394

Repository: [PROG5121-POE-ALL-3-PARTS](https://github.com/MrPettitt/PROG5121-POE-ALL-3-PARTS). Each part of the POE lives on its own branch (`part-1`, `part-2` and `part-3`). This is the final part, so `part-3` is the default branch.

This is the final part of the POE. It builds on the Part 1 registration and login and the Part 2 task capture by adding a **report menu** for searching, reporting on and deleting tasks.

## Features

### Registration and login
- Captures the user's first and last name.
- **Username:** must contain an underscore (`_`) and be no more than 5 characters long.
- **Password:** at least 8 characters, no spaces, with a capital letter, a number and a special character (`!@#$%^&*`).
- Invalid input is re-prompted until it is valid, then the user logs in with the registered details.

### EasyKanban main menu
| Option | Action |
| ------ | ------ |
| 1 | **Add tasks**: enter how many tasks to add, then a name, description, developer details and duration (in hours) for each. The status is chosen from To Do, Doing or Done. Descriptions are limited to 50 characters and are re-asked if too long. Each task's details are shown, followed by the total hours. |
| 2 | **Show report**: opens the report menu below. |
| 3 | **Quit** |
| 4 | **Load sample data**: adds four sample tasks (one is Done, the longest is 11 hours) so the report menu can be tried without typing tasks in. It can be loaded once per run. |

### Report menu
| Option | Action |
| ------ | ------ |
| 1 | Display the developer, task name and duration of every task with status **Done**. |
| 2 | Display the developer and duration of the task with the **longest duration**. |
| 3 | **Search** for a task by task name. |
| 4 | **Search** for all tasks assigned to a developer. |
| 5 | **Delete** a task by task name. |
| 6 | Display the **full details** of all tasks. |
| 7 | Go back to the main menu. |

Searches ignore upper and lower case. Each task gets a **Task ID** in the form `first two letters of the task name : task number : last three letters of the developer details`, for example `LO:1:SON`. Cancelling a dialog returns you to the previous menu, and non-numeric input is re-asked.

## Project Structure

```
├── src/st10090394/
│   ├── ST10090394.java   # Main class: registration, login and the EasyKanban menus
│   ├── Login.java        # Registration, validation and login logic
│   ├── Task.java         # Task details, Task ID, status and total hours
│   ├── TaskReport.java   # Report, search and delete logic for the task list
│   ├── SampleData.java   # Sample tasks for the "Load sample data" option and the tests
│   └── Message.java      # Simple message class
├── test/st10090394/      # JUnit tests (LoginTest, TaskTest, TaskReportTest, MessageTest, ST10090394Test)
│   └── TestData.java     # Gives the tests access to the sample tasks
├── testresources/        # JUnit console launcher JAR
├── nbproject/, build.xml # NetBeans / Ant project files (optional)
└── .github/workflows/    # GitHub Actions workflow (PROGPOE.yml)
```

## Requirements

- Java JDK 17 or later (the project is set to Java 17)
- A desktop environment, as the menus use Swing dialogs (`JOptionPane`)
- Optional: NetBeans or VS Code with the Extension Pack for Java

## Running the Program

**In an IDE:** open the project and run `ST10090394`.

**From the command line** (from the project root):

```bash
javac -d build/classes src/st10090394/*.java
java -cp build/classes st10090394.ST10090394
```

Registration is entered in the terminal; login and the EasyKanban menus use pop-up dialogs.

## Running the Tests

The tests use JUnit 5 through the bundled console launcher. From the project root:

```bash
javac -cp testresources/junit-platform-console-standalone-1.6.0.jar -d build/classes src/st10090394/*.java test/st10090394/*.java
java -jar testresources/junit-platform-console-standalone-1.6.0.jar -cp build/classes --scan-classpath
```

The report, search and delete logic is tested through `TaskReportTest`. Methods that open dialogs (`Task.updateTaskStatus` and `Task.displayTaskDetails`) are not unit tested because they need a display.

## Continuous Integration

`.github/workflows/PROGPOE.yml` runs on every push or pull request to `part-3`. It sets up JDK 17, compiles the source and tests into `build/classes`, then runs every test class with the JUnit console launcher. The run fails if any test fails. It can also be started manually from the **Actions** tab.

## References

Farrell, J. 2018. *Java Programming*. Ninth Edition. Boston: Cengage Learning.
