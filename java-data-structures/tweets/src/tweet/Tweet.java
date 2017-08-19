package tweet;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// Implement the Comparable interface which will allow Tweets to be sortable
// Normally, Arrays.sort requires a comparable to sort
//
// Use the generic parameter Tweet when implementing Comparable
// This will allow you to skip type casting when overriding the compareTo method
public class Tweet implements Comparable<Tweet>, Serializable {
  // Add a serial version to the class
  // If this class changes, any file with this UID will still be able to be loaded
  private static final long serialVersionUID = -91628531270740319L;

  private String author;
  private String description;
  private Date date;

  public Tweet(String author, String description, Date date) {
    this.author = author;
    this.description = description;
    this.date = date;
  }

  public String getAuthor() {
    return author;
  }

  public String getDescription() {
    return description;
  }

  public Date getDate() {
    return date;
  }

  public List<String> getWords() {
    String[] words = description.toLowerCase().split("[^\\w#@']+");

    return Arrays.asList(words);
  }

  private List<String> getWordsPrefixedWith(String prefix) {
    List<String> results = new ArrayList<>();

    for (String word : getWords()) {
      if (word.startsWith(prefix)) {
        results.add(word);
      }
    }

    return results;
  }

  public List<String> getHashTags() {
    return getWordsPrefixedWith("#");
  }

  public List<String> getMentions() {
    return getWordsPrefixedWith("@");
  }

  // This annotation tells the compiler that you are overriding an inherited method
  // Useful, for example, if you misspell the method, the compiler will let you know
  @Override
  public String toString() {
    // DateFormat.format returns a String
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    // Gets the system new line character
    // More portable than "\n"
    String newLine = System.getProperty("line.separator");

    return "Author: " + author + newLine
        + "Description: " + description + newLine
        + "Date: " + dateFormat.format(date) + newLine;
  }

  // Override the compareTo method inherited from Comparable
  // compareTo returns either a -1, 0, or 0 which is used to determine sort order
  @Override
  public int compareTo(Tweet tweet) {
    // Check that the objects being compared are equal in VALUE not REFERENCE
    if (equals(tweet)) {
      return 0;
    }

    // If the values are different, compare their date stamps
    int dateComparison = date.compareTo(tweet.date);

    // If the dates are the same, compare their description texts
    if (dateComparison == 0) {
      return description.compareTo(tweet.description);
    }

    return dateComparison;
  }
}
