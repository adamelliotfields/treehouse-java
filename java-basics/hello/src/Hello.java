import java.util.Scanner;

public class Hello {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("What is your name?:  ");

    String firstName = scanner.nextLine();

    System.out.printf("Hello, %s.", firstName);
  }
}
