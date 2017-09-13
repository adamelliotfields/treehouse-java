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

  // GET / displays all gifs ("Index" template)
  // GET /gifs/gifId displays a single gif ("Gif" template)
  // GET /gifs/gifId.gif sends the gif image data
  // GET /gifs/favorites displays all gifs marked as favorite ("Favorites" template)
  // GET /gifs/gifId/edit displays the edit gif form ("GifForm" template)
  // POST /gifs/gifId sends the updated gif
  // POST /gifs/gifId/delete removes the gif
  // POST /gifs/gifId/favorite marks/unmarks a gif as favorite

  // GET /upload displays the upload form ("GifForm" template)

  // GET /search sends a query string to search gifs by keyword

  // GET /categories displays all categories ("Categories" template)
  // GET /categories/categoryId displays a single category ("Category" template)
  // GET /categories/add displays the new category form ("CategoryForm" template)
  // GET /categories/categoryId/edit displays the edit category form ("CategoryForm" template)
  // POST /categories/categoryId updates an existing category
  // POST /categories adds a new category
  // POST /categories/categoryId/delete removes a category
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
