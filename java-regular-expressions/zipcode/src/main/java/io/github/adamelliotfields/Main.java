package io.github.adamelliotfields;

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter your zip code:  ");

    String zip = scanner.nextLine();

    // ^ identifies the start of the line
    // \\d matches all digits (a single slash is an escape character)
    // {5} is a quantifier for exactly 5 matches
    // () creates a capturing group
    // ? makes the group optional
    // $ identifies the end of the line
    if (zip.matches("^\\d{5}(-\\d{4})?$")) {
      System.out.printf("%s is a valid zipcode%n", zip);
    } else {
      System.out.printf("%s is NOT a valid zipcode%n", zip);
    }
  }
}
