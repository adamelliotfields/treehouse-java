package pezdispenser;

import java.io.Console;

public class MakePezDispenser {
  public static void main(String[] args) {
    Console console = System.console();

    System.out.println("We are making a new PEZ dispenser...");

    System.out.printf(
        // The %d conversion character stands for "decimal integer"
        // It can be a byte, int, short, or long
        "FUN FACT: There are %d PEZ allowed in every dispenser.%n",
        PezDispenser.MAX_PEZ
    );

    String name = console.readLine("What character do you want?  ");

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
      dispenser.fillDispenser(
          Integer.parseInt(
              console.readLine("How many pieces of PEZ would you like to add?  ")
          )
      );
    } catch (IllegalArgumentException error) {
      System.out.println("Sorry, there was an error...");
      System.out.printf("Message: %s", error.getMessage());
    }
  }
}
