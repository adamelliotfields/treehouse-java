import lombok.Getter;

public class PezDispenser {
  // The private keyword makes the variable accessible only within its own class
  // The final keyword makes the variable immutable once assigned
  @Getter
  private final String name;
  // The static keyword makes the variable accessible at the class level
  public static final int MAX_PEZ = 12;
  private int pezCount;

  public PezDispenser(String name) {
    this.name = name;
    pezCount = 0;
  }

  public boolean dispense() {
    boolean wasDispensed = false;

    if (!isEmpty()) {
      pezCount--;
      wasDispensed = true;
    }

    return wasDispensed;
  }

  // Use the void keyword when a method does not return anything
  public void fillDispenser() {
    pezCount = MAX_PEZ;
  }

  // Example of method overloading
  // You can have methods with the same name as long as they have different parameters
  // This provides a way to give methods default values
  public void fillDispenser(int amount) {
    if (amount + pezCount > MAX_PEZ) {
      throw new IllegalArgumentException("Too many PEZ!");
    } else {
      pezCount += amount;
      System.out.println("Added " + amount + " PEZ!");
    }
  }

  public boolean isEmpty() {
    return pezCount == 0;
  }
}
