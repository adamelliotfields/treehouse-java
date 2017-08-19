import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("We are making a new PEZ dispenser...");

    System.out.printf(
        // The %d conversion character stands for "decimal integer"
        // It can be a byte, int, short, or long
        "FUN FACT: There are %d PEZ allowed in every dispenser.%n",
        PezDispenser.MAX_PEZ
    );

    System.out.print("What character do you want?:  ");
    String name = scanner.nextLine();

    PezDispenser dispenser = new PezDispenser(name);

    System.out.printf(
        "Successfully made a %s PEZ dispenser!%n",
        dispenser.getCharacterName()
    );

    if (dispenser.isEmpty()) {
      System.out.println("Dispenser is empty...");
    }

    System.out.println("Filling the dispenser with PEZ...");

    dispenser.fillDispenser();

    if (dispenser.isEmpty()) {
      System.out.println("Dispenser is now full!");
    }

    while (dispenser.dispense()) {
      System.out.println("Dispensing PEZ!");
    }

    if (dispenser.isEmpty()) {
      System.out.println("All PEZ gone!");
    }

    try {
      System.out.print("How many pieces of PEZ would you like to add?:  ");

      dispenser.fillDispenser(Integer.parseInt(scanner.nextLine()));
    } catch (IllegalArgumentException error) {
      System.out.println("Sorry, there was an error...");
      System.out.printf("Message: %s", error.getMessage());
    }
  }
}
