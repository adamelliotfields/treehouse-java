package books;

public class Book {
  private String title;
  private String author;
  private int publicationDate;

  public Book(String title, String author, int publicationDate) {
    this.title = title;
    this.author = author;
    this.publicationDate = publicationDate;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getPublicationDate() {
    return publicationDate;
  }

  // Override the native toString method
  @Override
  public String toString() {
    String string = "Book { title="
        + title
        + ", author="
        + author
        + ", publicationDate="
        + publicationDate
        + " }";

    return string;
  }
}
