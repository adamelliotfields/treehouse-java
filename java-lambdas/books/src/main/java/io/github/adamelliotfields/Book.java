package io.github.adamelliotfields;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Book {
  @Getter
  private String title;

  private String author;
  private int publicationDate;

  public Book(String title, String author, int publicationDate) {
    this.title = title;
    this.author = author;
    this.publicationDate = publicationDate;
  }

  // @Override
  // public String toString() {
  //   String string = "Book { title="
  //       + title
  //       + ", author="
  //       + author
  //       + ", publicationDate="
  //       + publicationDate
  //       + " }";
  //
  //   return string;
  // }
}
