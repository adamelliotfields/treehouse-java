import java.io.Console;

public class MadLibs {
  public static void main(String[] args) {
    Console console = System.console();

    String ageString = console.readLine("How old are you?  ");

    int age = Integer.parseInt(ageString);

    if (age < 13) {
      console.printf("Sorry, you must be at least 13 to use this program.\n");
      System.exit(0);
    }

    String name = console.readLine("Enter a name:  ");
    String adjective = console.readLine("Enter an adjective:  ");
    String noun;
    boolean isValidWord;

    do {
      noun = console.readLine("Enter a noun:  ");
      isValidWord = noun.equalsIgnoreCase("dork") || noun.equalsIgnoreCase("jerk");

      if (isValidWord) {
        console.printf("That language is not allowed. Try again.\n");
      }
    } while (isValidWord);

    String adverb = console.readLine("Enter an adverb:  ");
    String verb = console.readLine("Enter a verb ending with -ing:  ");

    console.printf("%s is a %s %s. ", name, adjective, noun);
    console.printf("They are always %s %s.\n", adverb, verb);
  }
}
