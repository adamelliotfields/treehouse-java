package hangman;

import java.util.Scanner;

/**
 * The command line interface to Game.
 */
public class Prompter {
  private Game game;

  /**
   * Prompter constructor method.
   * @param game the game object
   */
  public Prompter(Game game) {
    this.game = game;
  }

  /**
   * Prompts the user for a guess.
   */
  public void promptForGuess() {
    Scanner scanner = new Scanner(System.in);

    boolean isAcceptable = false;

    do {
      System.out.print("Enter a letter:  ");

      String guessInput = scanner.nextLine();

      try {
        game.applyGuess(guessInput);

        isAcceptable = true;
      } catch (IllegalArgumentException error) {
        System.out.println(error.getMessage());
      }

    } while (!isAcceptable);
  }

  /**
   * Displays the current progress based on which characters have been guessed.
   */
  public void displayProgress() {
    System.out.printf(
        "You have %d tries left to solve: %s%n",
        game.getRemainingTries(),
        game.getCurrentProgress()
    );
  }

  /**
   * Displays whether the user won or lost the game.
   */
  public void displayOutcome() {
    if (game.isWon()) {
      System.out.printf(
          "Congratulations! You won with %d tries remaining.%n",
          game.getRemainingTries()
      );
    } else {
      System.out.printf(
          "Bummer! The word was %s :(%n",
          game.getAnswer()
      );
    }
  }
}
