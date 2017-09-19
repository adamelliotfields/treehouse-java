package io.github.adamelliotfields.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Color {
  AQUA("Aqua","#59b3b3"),
  BLUE("Blue","#5976b3"),
  PURPLE("Purple","#7e59b3"),
  FUCHSIA("Fucshia","#b35986"),
  ORANGE("Orange","#b36859"),
  GOLD("Gold","#b38f59");

  @Getter
  private final String name;

  @Getter
  private final String hexCode;
}
