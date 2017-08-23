package io.github.adamelliotfields.model;

import lombok.Getter;

public class Attempt {
  @Getter
  private int remainingSeconds;

  @Getter
  private AttemptKind kind;

  public Attempt(AttemptKind kind) {
    this.kind = kind;
    this.remainingSeconds = kind.getTotalSeconds();
  }

  public void tick() {
    remainingSeconds--;
  }
}
