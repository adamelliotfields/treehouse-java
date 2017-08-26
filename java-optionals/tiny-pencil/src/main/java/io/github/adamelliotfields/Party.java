package io.github.adamelliotfields;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Party {
  private List<Sheet> sheets;

  public Party(String... names) {
    sheets = Stream
                 .of(names)
                 .map(Sheet::new)
                 .collect(Collectors.toList());
  }

  public Sheet getSheetFor(String name) {
    return sheets
               .stream()
               .filter(sheet -> sheet.getName().equalsIgnoreCase(name))
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("Couldn't find sheet for " + name));
  }

  public List<Sheet> getSheets() {
    return sheets;
  }

  public void takeScore(String name, int hole, int score) {
    getSheetFor(name).setScoreForHole(hole, score);
  }

  public String getWinner() {
    return sheets
               .stream()
               .filter(sheet -> sheet.getRemainingHoleCount() < Sheet.PAR.length)
               .min(Comparator.comparing(Sheet::getScore))
               .map(Sheet::getName)
               .orElse("No winner yet");
  }


  public String getAdjustedWinner() {
    return sheets
               .stream()
               .filter(sheet -> sheet.getRemainingHoleCount() < Sheet.PAR.length)
               .min(Comparator.comparing(Sheet::getAdjustedScore))
               .map(Sheet::getName)
               .orElse("No winner currently");
  }

  public List<Sheet> ranked() {
    return sheets
               .stream()
               .sorted(Comparator.comparing(Sheet::getAdjustedScore))
               .collect(Collectors.toList());
  }
}
