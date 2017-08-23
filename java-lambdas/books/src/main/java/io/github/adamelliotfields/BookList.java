package io.github.adamelliotfields;

import java.util.ArrayList;
import java.util.List;

public class BookList {
  public static List<Book> all() {
    List<Book> books = new ArrayList<>();

    books.add(new Book("Functional Programming in Java", "Venkat Subramaniam", 2014));
    books.add(new Book("Clean Code", "Robert C. Martin", 2008));
    books.add(new Book("Java Generics and Collections", "Maurice Naftalin and Philip Wadler", 2008));
    books.add(new Book("Effective Java", "Joshua Bloch", 2008));
    books.add(new Book("Pragmatic Unit Testing in Java 8 with JUnit", "Jeff Langr", 2015));
    books.add(new Book("JavaFX Essentials", "Mohamed Taman", 2015));

    return books;
  }
}
