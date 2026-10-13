# PROG5121-POE-PART2

**PROG5121 POE, Part 2: EasyKanban**
**Author:** Richard Pettitt, ST10090394

Repository: [PROG5121-POE-ALL-3-PARTS](https://github.com/MrPettitt/PROG5121-POE-ALL-3-PARTS). Each part of the POE lives on its own branch: `part-1`, `part-2` (this branch) and `part-3` (final).

Part 2 builds on the Part 1 registration and login by adding **EasyKanban**, a task management menu that opens after a successful login.

## Features

### Registration and login (from Part 1)
- Captures the user's first and last name.
- **Username:** must contain an underscore (`_`) and be no more than 5 characters long.
- **Password:** at least 8 characters, no spaces, with a capital letter, a number and a special character (`!@#$%^&*`).
- Invalid input is re-prompted until it is valid, then the user logs in with the registered details.

### EasyKanban (new in Part 2)
After a successful login the user sees a menu:

| Option | Action |
| ------ | ------ |
| 1 | **Add tasks**: enter how many tasks to add, then a name, description, developer details and duration (in hours) for each. The status is chosen from To Do, Doing or Done. Each task's details are shown, followed by the total hours. |
| 2 | **Show report**: placeholder ("Coming Soon"). |
| 3 | **Quit** |

Each task gets a **Task ID** in the form `first two letters of the task name : task number : last three letters of the developer details`, for example `LO:1:SON`. Cancelling a dialog returns you to the menu, and non-numeric input is re-asked.

## Project Structure

```
├── src/st10090394/
│   ├── ST10090394.java   # Main class: registration, login and the EasyKanban menu
│   ├── Login.java        # Registration, validation and login logic
│   ├── Task.java         # Task details, Task ID, status and total hours
│   └── Message.java      # Simple message class
├── test/st10090394/      # JUnit tests (LoginTest, TaskTest, MessageTest, ST10090394Test)
├── testresources/        # JUnit console launcher JAR
└── nbproject/, build.xml # NetBeans / Ant project files (optional)
```

## Requirements

- Java JDK 8 or later
- A desktop environment, as the menu uses Swing dialogs (`JOptionPane`)
- Optional: NetBeans or VS Code with the Extension Pack for Java

## Running the Program

**In an IDE:** open the project and run `ST10090394`.

**From the command line** (from the project root):

```bash
javac -d build/classes src/st10090394/*.java
java -cp build/classes st10090394.ST10090394
```

Registration is entered in the terminal; login and the EasyKanban menu use pop-up dialogs.

## Running the Tests

The tests use JUnit 5 through the bundled console launcher. From the project root:

```bash
javac -cp testresources/junit-platform-console-standalone-1.6.0.jar -d build/classes src/st10090394/*.java test/st10090394/*.java
java -jar testresources/junit-platform-console-standalone-1.6.0.jar -cp build/classes --scan-classpath
```

Methods that open dialogs (`Task.updateTaskStatus` and `Task.displayTaskDetails`) are not unit tested because they need a display.

## References

Farrell, J. 2018. *Java Programming*. Ninth Edition. Boston: Cengage Learning.
