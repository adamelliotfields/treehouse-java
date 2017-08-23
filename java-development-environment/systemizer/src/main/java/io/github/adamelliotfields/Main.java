package io.github.adamelliotfields;

import java.util.Set;
import java.util.TreeSet;

public class Main {
  public static void main(String[] args) {
    System.out.println("This is the classpath: " + System.getProperty("java.class.path"));

    // Because Set is given an explicit type of String, we can pass the diamond operator to TreeSet
    // This is syntactic sugar for Set<String> propNames = new TreeSet<String>...
    Set<String> propNames = new TreeSet<>(System.getProperties().stringPropertyNames());

    propNames
        .forEach(item -> System.out.println(item + ": " + System.getProperty(item)));
  }
}
