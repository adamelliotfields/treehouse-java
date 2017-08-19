import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prompter {
  private BufferedReader reader;
  private Set<String> censoredWords;
  private String story;

  public Prompter() {
    reader = new BufferedReader(new InputStreamReader(System.in));
    loadCensoredWords();
  }

  private void loadCensoredWords() {
    censoredWords = new HashSet<>();
    Path file = Paths.get("resources", "censored_words.txt");
    List<String> words = null;

    try {
      words = Files.readAllLines(file);
    } catch (IOException error) {
      System.out.println("Error: Couldn't load censored words...");
      error.printStackTrace();
    }

    censoredWords.addAll(words);
  }

  public void run(Template template) {
    List<String> words = null;

    try {
      words = promptForWords(template);
    } catch (IOException error) {
      System.out.println("There was a problem prompting for words");
      error.printStackTrace();
      System.exit(0);
    }
    // Print out the results that were gathered here by rendering the template
    String results = template.render(words);
    System.out.printf("Your story:%n%n%s", results);
  }

  /**
   * Prompts user for each of the blanks
   *
   * @param template The compiled template
   * @return
   * @throws IOException
   */
  public List<String> promptForWords(Template template) throws IOException {
    List<String> words = new ArrayList<>();

    for (String phrase : template.getPlaceHolders()) {
      String word = promptForWord(phrase);
      words.add(word);
    }

    return words;
  }

  public String promptForStory() {
    String story = null;

    try {
      story = reader.readLine();
    } catch (IOException error) {
      error.printStackTrace();
    }

    return story;
  }

  /**
   * Prompts the user for the answer to the fill in the blank.  Value is guaranteed to be not in the censored words list.
   *
   * @param phrase The word that the user should be prompted.  eg: adjective, proper noun, name
   * @return What the user responded
   */
  public String promptForWord(String phrase) {
    // Prompt the user for the response to the phrase, make sure the word is censored, loop until you get a good response.
    String word = null;
    System.out.printf("Give me a(n) %s:  ", phrase);

    try {
      word = reader.readLine();
    } catch (IOException error) {
      error.printStackTrace();
    }

    if (censoredWords.contains(word)) {
      System.out.println("That's a censored word, try again.");
      word = promptForWord(phrase);
    }

    return word;
  }
}
