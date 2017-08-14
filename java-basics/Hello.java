import java.io.Console;

public class Hello {
  public static void main(String[] args) {
    Console console = System.console();

    String firstName = console.readLine("What is your name?  ");

    console.printf("Hello, %s.", firstName);
  }
}
