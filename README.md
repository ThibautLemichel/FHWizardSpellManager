# Wizard Spell Manager

This project is a command-line application for managing and casting spells using various design patterns in Java.

## Design Patterns Implemented

1. **Singleton Pattern**
    - **Class**: `SpellBook`
    - **Purpose**: Ensures that only one instance of the `SpellBook` class exists.

2. **Factory Pattern**
    - **Class**: `SpellFactory`
    - **Purpose**: Creates objects using only a string as input.

3. **Command Pattern**
    - **Classes**: `Command`, `CastSpellCommand`
    - **Purpose**: Encapsulates a request as an object. It supports cast and undo operations on spells.

4. **Observer Pattern**
    - **Classes**: `SpellBook`, `SpellLogger`
    - **Purpose**: Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. Here, `SpellLogger` observes changes in `SpellBook`.

## How to Run

1. **Clone the repository**:
   ```sh
   git clone https://github.com/ThibautLemichel/wizard-spell-manager.git
   cd wizard-spell-manager

2. **Compile the code**:
   ```sh
    javac -d out src/*.java
   
   
3. **Run the application**:
   ```sh
    java -cp out main
   

## Usage

When you run the application, you will be presented with a menu to choose an action:
1. **Add Spell**: Enter the type of spell to add (here FireBall or Necromancer).
2. **Cast Spell**: Displays the list of available spells and you can prompt the spell that you want to cast.
3. **Undo Last Spell**: Undo the last spell casted.
4. **Undo All Spells**: Undo all the spells casted.
5. **List Spells**: Lists all the spells that have been added.
4. **Exit**: Exit the application.

## Classes

- **main**: Main class that contains the application.
- **SpellBook**: Singleton class that manages the list of spells.
- **SpellFactory**: Factory class that creates spells.
- **Spell**: Interface for all spells.
- **FireBall**: Class that implements the `Spell` interface.
- **Necromancer**: Class that implements the `Spell` interface.
- **Command**: Interface for all commands.
- **CastSpellCommand**: Class that implements the `Command` interface.
- **SpellLogger**: Observer class that logs all the spells casted.
