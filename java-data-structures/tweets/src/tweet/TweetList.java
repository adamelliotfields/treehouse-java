package tweet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TweetList {
  public static void save(Tweet[] tweets) {
    // This is a special type of try block known as "try with resources"
    // This will automatically close any streams that were opened
    try (
      FileOutputStream fileOutputStream = new FileOutputStream("tweets.ser");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    ) {
      objectOutputStream.writeObject(tweets);
    } catch (IOException error) {
      System.out.println("Problem saving Tweets...");
      error.printStackTrace();
    }
  }

  public static Tweet[] load() {
    Tweet[] tweets = new Tweet[0];

    try (
      FileInputStream fileInputStream = new FileInputStream("tweets.ser");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    ) {
      tweets = (Tweet[]) objectInputStream.readObject();
    } catch (IOException error) {
      System.out.println("Error reading file...");
      error.printStackTrace();
    } catch (ClassNotFoundException error) {
      System.out.println("Error loading Tweets...");
      error.printStackTrace();
    }

    return tweets;
  }
}
