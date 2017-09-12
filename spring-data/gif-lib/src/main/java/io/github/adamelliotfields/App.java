package io.github.adamelliotfields;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
  // Configure data validation (both web server and database)
  // Gif should contain a field for binary data
  // Gif should contain a field for the Category
  // Category should contain a reference field for all gifs with that category
  // Flash message should appear when the user incorrectly fills out the form
  // Flash message should survive for only 1 redirect

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
