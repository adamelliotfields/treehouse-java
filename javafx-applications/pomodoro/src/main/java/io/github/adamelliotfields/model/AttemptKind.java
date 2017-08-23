package io.github.adamelliotfields.model;

import lombok.Getter;

public enum AttemptKind {
  FOCUS(25 * 60, "Focus Time"),
  BREAK(5 * 60, "Break Time");

  @Getter
  private int totalSeconds;

  @Getter
  private String displayName;

  AttemptKind(int totalSeconds, String displayName) {
    this.totalSeconds = totalSeconds;
    this.displayName = displayName;
  }
}
