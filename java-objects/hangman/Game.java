package hangman;

/**
 * The Hangman game logic.
 */
public class Game {
  private String answer;
  private String hits;
  private String misses;

  /**
   * The maximum number of misses a player can make before the game ends.
   * <p>7 is the default value.</p>
   */
  public static final int MAX_MISSES = 7;

  /**
   * Returns the number of remaining tries the user has left.
   * @return int
   */
  public int getRemainingTries() {
    return MAX_MISSES - misses.length();
  }

  /**
   * Game constructor method.
   * @param answer the string the players are trying to guess
   */
  public Game(String answer) {
    this.answer = answer;
    hits = "";
    misses = "";
  }

  /**
   * Checks if the guessed letter is a valid letter and if it has already been guessed.
   * @param  letter the guessed letter
   * @throws IllegalArgumentException if the guessed letter is not a valid letter or if it has
   *         already been guessed
   * @return char
   */
  private char normalizeGuess(char letter) {
    if (!Character.isLetter(letter)) {
      throw new IllegalArgumentException("A letter is required");
    }

    letter = Character.toLowerCase(letter);

    if (misses.indexOf(letter) != -1 || hits.indexOf(letter) != -1) {
      throw new IllegalArgumentException(letter + " has already been guessed!");
    }

    return letter;
  }

  /**
   * Returns the correct answer string.
   * @return String
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Returns a boolean if the guessed letter is contained in the answer string.
   * @param  letter the char the player guessed
   * @return boolean
   */
  public boolean applyGuess(char letter) {
    letter = normalizeGuess(letter);

    boolean isHit = answer.indexOf(letter) != -1;

    if (isHit) {
      hits += letter;
    } else {
      misses += letter;
    }

    return isHit;
  }

  /**
   * Returns the first letter of a string, if a string is passed as a guess.
   * @param  letters a string of letters
   * @throws IllegalArgumentException if an empty string is entered
   * @return char
   */
  public boolean applyGuess(String letters) {
    if (letters.length() == 0) {
      throw new IllegalArgumentException("No letter found.");
    }

    return applyGuess(letters.charAt(0));
  }

  /**
   * Gets the current progress of the game and returns a string of correctly guessed letters.
   * @return String
   */
  public String getCurrentProgress() {
    // Use StringBuilder when generating a string iteratively
    StringBuilder progress = new StringBuilder();

    for (char letter : answer.toCharArray()) {
      char display = '-';

      if (hits.indexOf(letter) != -1) {
        display = letter;
      }
      // Instead of string concatenation, append each character
      progress.append(display);
    }

    return progress.toString();
  }

  /**
   * Determines if the answer string has been guessed.
   * @return boolean
   */
  public boolean isWon() {
    return getCurrentProgress().indexOf('-') == -1;
  }
}
