import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        SpellBook spellBook = SpellBook.getInstance();
        Wizard wizard = new Wizard();
        Scanner scanner = new Scanner(System.in);

        SpellLogger logger = new SpellLogger();
        spellBook.addObserver(logger);

        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Add Spell");
            System.out.println("2. Cast Spell");
            System.out.println("3. Undo Last Spell");
            System.out.println("4. Undo All Spells");
            System.out.println("5. List Spells");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter spell type (FireBall/Necromancer):");
                    String spellType = scanner.nextLine();
                    Spell spell = SpellFactory.createSpell(spellType);
                    spellBook.addSpell(spell);
                    System.out.println(spellType + " spell added.");
                    break;
                case 2:
                    System.out.println("Enter spell type to cast (FireBall/Necromancer):");
                    String castSpellType = scanner.nextLine();
                    Spell castSpell = spellBook.getSpell(castSpellType);
                    if (castSpell != null) {
                        Command castCommand = new CastSpellCommand(castSpell);
                        wizard.castSpell(castCommand);
                        System.out.println(castSpellType + " spell cast.");
                    } else {
                        System.out.println("Spell not found.");
                    }
                    break;
                case 3:
                    wizard.undoLastSpell();
                    System.out.println("Last spell undone.");
                    break;
                case 4:
                    wizard.undoAllSpell();
                    System.out.println("All spells undone.");
                    break;
                case 5:
                    System.out.println("List of spells:");
                    for (Spell s : spellBook.getSpells()) {
                        System.out.println(s.getClass().getSimpleName());
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            return;
        }
    }
}
