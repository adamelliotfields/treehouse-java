import java.util.Scanner;

public class Skills {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a comma-separated list of skills:  ");
    String skills = scanner.nextLine();

    // \\W matches non-word characters
    // + matches one or more times
    // and matches the literal word "and"
    for (String skill : skills.split("\\W+(and\\W+)?")) {
      System.out.printf("Skill: %s%n", skill);
    }
  }
}
