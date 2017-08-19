public class Explore {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

  public static void explore(String assumption, boolean result) {
    StringBuilder builder = new StringBuilder();

    if (result) {
      builder.append(String.format("%sYAY!%s", ANSI_GREEN, ANSI_RESET));
    } else {
      builder.append(String.format("%sWHAT?%s", ANSI_RED, ANSI_RESET));
    }

    builder.append(" ");
    builder.append(assumption);

    if (!result) {
      builder.append(" (Your assumption is incorrect)");
    }

    System.out.println(builder.toString());
  }

  public static void main(String[] args) {
    int firstNumber = 12;
    int secondNumber = 12;

    explore(
        "Primitives use double equals.",
        firstNumber == secondNumber
    );

    Object firstObject = new Object();
    Object secondObject = new Object();

    explore(
        "Object references use double equals to check if they refer to the same object in memory. Seemingly equal objects are not equal.",
        firstObject != secondObject
    );

    Object sameObject = firstObject;

    explore(
        "Object references must refer to the same object to use double equals.",
        firstObject == sameObject
    );

    String firstString = "Adam";
    String secondString = "Adam";

    explore(
        "String literals are actually referring to the same object.",
        firstString == secondString
    );

    String differentString = new String("Adam");

    explore(
        "String objects that contain the same characters, but point to different objects, cannot use double equals.",
        firstString != differentString
    );

    String anotherString = new String("Adam");

    explore(
        "String Interning adds to the same string pool where literals live, so you get back the same reference.",
        anotherString.intern() == firstString
    );

    explore(
        "All objects should use equals to check equality",
        firstString.equals(differentString)
    );
  }
}
