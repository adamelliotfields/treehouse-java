package io.github.adamelliotfields.flashy.domain;

import lombok.Getter;
import lombok.Setter;

public class BootstrapOptions {
  @Setter
  private boolean shouldFork;

  @Getter
  @Setter
  private String githubOauth;

  public boolean isShouldFork() {
    return shouldFork;
  }
}
