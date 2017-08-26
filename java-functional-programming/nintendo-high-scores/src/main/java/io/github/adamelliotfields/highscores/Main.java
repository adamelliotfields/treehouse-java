package io.github.adamelliotfields.highscores;

import io.github.adamelliotfields.highscores.model.Score;
import io.github.adamelliotfields.highscores.service.ScoreService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  public static Map<String,Long> getWordCountFromGameTitlesImperatively(List<Score> scores) {
    Map<String, Long> wordByCount = new HashMap<>();
    for (Score score : scores) {
      String title = score.getGame().toLowerCase();
      String[] words = title.split("\\W+");
      for (String word : words) {
        if (word.isEmpty()) {
          continue;
        }
        long count = wordByCount.getOrDefault(word, 0L);
        wordByCount.put(word, ++count);
      }
    }
    return wordByCount;
  }

  public static Map<String,Long> getWordCountFromGameTitlesDeclaratively(List<Score> scores) {
    return scores.stream()
                 .map(score -> score.getGame())
                 .map(game -> game.toLowerCase())
                 .map(game -> game.split("\\W+"))
                 .flatMap(Stream::of)
                 .collect(Collectors.groupingBy(
                    Function.identity(),
                    Collectors.counting()
                 ));
  }

  public static List<String> getFirstFiveAmazingPlayersDeclaratively(List<Score> scores) {
    return scores.stream()
                 .filter(score -> score.getAmount() > 100000)
                 .map(Score::getPlayer)
                 .limit(5)
                 .collect(Collectors.toList());
  }

  public static List<Score> getFirstThreeNintendoScoresDeclaratively(List<Score> scores) {
   return scores.stream()
                .filter(score -> score.getPlatform().equals("Nintendo Entertainment System"))
                .limit(3)
                .collect(Collectors.toList());
  }

  public static void printBurgerTimeScoresDeclaratively(List<Score> scores) {
    scores.stream()
          .filter(score -> score.getGame().equals("Burger Time"))
          .filter(score -> score.getAmount() >= 20000)
          .forEach(System.out::println);
  }

  public static void main(String[] args) {
    ScoreService service = new ScoreService();
    List<Score> scores = service.getAllScores();

    System.out.printf("There are %d total scores. %n %n", scores.size());
    System.out.println("Imperatively - Word count of all game titles");

    Map<String, Long> wordCounts = getWordCountFromGameTitlesImperatively(scores);

    wordCounts.forEach((key, value) -> System.out.printf("%s occurs %d times %n", key, value));

    System.out.println();
    System.out.println("Declaratively - Word count of all game titles");

    // NOTE: Reassignment
    wordCounts = getWordCountFromGameTitlesDeclaratively(scores);
    wordCounts.forEach((key, value) -> System.out.printf("%s occurs %d times %n", key, value));
  }
}
