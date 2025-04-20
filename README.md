# main.spell.Wizard main.spell.Spell Manager

This project is a command-line application for managing and casting spells using various design patterns in Java.

## Design Patterns Implemented

1. **Singleton Pattern**
    - **Class**: `main.spell.SpellBook`
    - **Purpose**: Ensures that only one instance of the `main.spell.SpellBook` class exists.

2. **Factory Pattern**
    - **Class**: `main.spell.SpellFactory`
    - **Purpose**: Creates objects using only a string as input.

3. **main.spell.Command Pattern**
    - **Classes**: `main.spell.Command`, `main.spell.CastSpellCommand`
    - **Purpose**: Encapsulates a request as an object. It supports cast and undo operations on spells.

4. **Observer Pattern**
    - **Classes**: `main.spell.SpellBook`, `main.spell.SpellLogger`
    - **Purpose**: Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. Here, `main.spell.SpellLogger` observes changes in `main.spell.SpellBook`.

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
    java -cp out main.main
   

## Usage

When you run the application, you will be presented with a menu to choose an action:
1. **Add main.spell.Spell**: Enter the type of spell to add (here FireBall or Necromancer).
2. **Cast main.spell.Spell**: Displays the list of available spells and you can prompt the spell that you want to cast.
3. **Undo Last main.spell.Spell**: Undo the last spell casted.
4. **Undo All Spells**: Undo all the spells casted.
5. **List Spells**: Lists all the spells that have been added.
4. **Exit**: Exit the application.

## Classes

- **main.main**: Main class that contains the application.
- **main.spell.SpellBook**: Singleton class that manages the list of spells.
- **main.spell.SpellFactory**: Factory class that creates spells.
- **main.spell.Spell**: Interface for all spells.
- **FireBall**: Class that implements the `main.spell.Spell` interface.
- **Necromancer**: Class that implements the `main.spell.Spell` interface.
- **main.spell.Command**: Interface for all commands.
- **main.spell.CastSpellCommand**: Class that implements the `main.spell.Command` interface.
- **main.spell.SpellLogger**: Observer class that logs all the spells casted.
