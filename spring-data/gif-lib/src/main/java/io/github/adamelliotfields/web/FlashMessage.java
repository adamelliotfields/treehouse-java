package io.github.adamelliotfields.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FlashMessage {
  @Getter
  private String message;

  @Getter
  private Status status;

  public enum Status {
    SUCCESS, FAILURE
  }
}
