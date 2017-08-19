/**
 * The main hangman game app.
 */
public class Main {

  /**
   * The program's main method.
   * <p>Requires a string to be passed from the command line.</p>
   * @param args an array of strings (only the first string will be used)
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java Hangman <answer>");
      System.err.println("Fatal: answer is required.");
      System.exit(0);
    }

    Game game = new Game(args[0]);
    Prompter prompter = new Prompter(game);

    while (game.getRemainingTries() > 0 && !game.isWon()) {
      prompter.displayProgress();
      prompter.promptForGuess();
    }

    prompter.displayOutcome();
  }
}
