package io.github.adamelliotfields.controllers;

import java.util.Arrays;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import io.github.adamelliotfields.model.Attempt;
import io.github.adamelliotfields.model.AttemptKind;

public class Home {
  @FXML
  private VBox container;

  @FXML
  private Label title;

  private Attempt currentAttempt;
  private StringProperty timerText;
  private Timeline timeline;
  private final AudioClip applause;

  public Home() {
    this.timerText = new SimpleStringProperty();
    this.applause = new AudioClip(getClass().getResource("/sounds/applause.mp3").toExternalForm());

    setTimerText("00:00");
  }

  public String getTimerText() {
    return timerText.get();
  }

  public void setTimerText(String timerText) {
    this.timerText.set(timerText);
  }

  public void setTimerText(int remainingSeconds) {
    int minutes = remainingSeconds / 60;
    int seconds = remainingSeconds % 60;

    setTimerText(String.format("%02d:%02d", minutes, seconds));
  }

  // StringProperty uses the JavaBeans naming convention
  // http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm
  public StringProperty timerTextProperty() {
    return timerText;
  }

  public void playTimer() {
    container.getStyleClass().add("playing");
    timeline.play();
  }

  public void pauseTimer() {
    container.getStyleClass().remove("playing");
    timeline.pause();
  }

  private void addAttemptStyle(AttemptKind kind) {
    container
        .getStyleClass()
        .add(kind.toString().toLowerCase());
  }

  private void clearAttemptStyles() {
    container.getStyleClass().remove("playing");

    Arrays
        .stream(AttemptKind.values())
        .forEach(kind -> container.getStyleClass().remove(kind.toString().toLowerCase()));
  }

  private void resetTimeline() {
    clearAttemptStyles();

    if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
      timeline.stop();
    }
  }

  private void prepareAttempt(AttemptKind kind) {
    // Reset the timeline, otherwise you'll have overlapping timelines
    resetTimeline();

    currentAttempt = new Attempt(kind);
    timeline = new Timeline();

    addAttemptStyle(kind);

    setTimerText(currentAttempt.getRemainingSeconds());

    title.setText(kind.getDisplayName());

    timeline.setCycleCount(kind.getTotalSeconds());
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000.00), event -> {
      currentAttempt.tick();
      setTimerText(currentAttempt.getRemainingSeconds());
    }));
    timeline.setOnFinished(event -> {
      applause.play();
      prepareAttempt(
          currentAttempt.getKind() == AttemptKind.FOCUS
              ? AttemptKind.BREAK
              : AttemptKind.FOCUS
      );
    });
  }

  public void handleRestart(ActionEvent actionEvent) {
    prepareAttempt(AttemptKind.FOCUS);
    playTimer();
  }

  public void handlePlay(ActionEvent actionEvent) {
    if (currentAttempt == null) {
      handleRestart(actionEvent);
    } else {
      playTimer();
    }
  }

  public void handlePause(ActionEvent actionEvent) {
    pauseTimer();
  }
}
