package skills;

import java.io.Console;

public class Skills {
  public static void main(String[] args) {
    Console console = System.console();

    String skills = console.readLine("Enter a comma-separated list of skills:  ");

    // \\W matches non-word characters
    // + matches one or more times
    // and matches the literal word "and"
    for (String skill : skills.split("\\W+(and\\W+)?")) {
      System.out.printf("Skill: %s%n", skill);
    }
  }
}
