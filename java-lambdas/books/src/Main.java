import java.util.Comparator;
import java.util.List;

import book.Book;
import book.BookList;

public class Main {
  public static void main(String[] args) {
    List<Book> books = BookList.all();

    // Collections.sort(books, new Comparator<Book>() {
    //   @Override
    //   public int compare(Book b1, Book b2) {
    //     return b1.getTitle().compareTo(b2.getTitle());
    //   }
    // });

    // Call sort directly on the books List
    // books.sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));

    // Use the Comparator comparing method
    // Note: The parens around b are not required
    // books.sort(Comparator.comparing((b) -> b.getTitle()));


    // Use method referencing
    books.sort(Comparator.comparing(Book::getTitle));

    // for (Book book : books) {
    //   System.out.println(book);
    // }
    books.forEach(System.out::println);
  }
}
