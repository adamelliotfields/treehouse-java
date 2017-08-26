package io.github.adamelliotfields.highscores.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Score {
  private static final NumberFormat usFormat = NumberFormat.getInstance(Locale.US);

  @Getter
  private final String game;

  @Getter
  private final String measuringUnit;

  @Getter
  private final String amountAsString;

  @Getter
  private final String player;

  @Getter
  private final String platform;

  public Score(String game, String measuringUnit, String platform, String amountAsString, String player) {
    this.game = game;
    this.measuringUnit = measuringUnit;
    this.amountAsString = amountAsString;
    this.platform = platform;
    this.player=  player;
  }

  public long getAmount() {
    try {
      return usFormat.parse(amountAsString).longValue();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
