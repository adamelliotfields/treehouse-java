/*If Main was in the module root and Tweet was in the package,
 * you would have to import it:
 *
 * import tweets.TreehouseTweet;
 */

package tweets;

import java.util.Arrays;
import java.util.Date;

public class Main {
  public static void main(String[] args) {
    // Date takes a long integer (milliseconds since epoch)
    // Long integers are suffixed with L
    Tweet tweet1 = new Tweet(
        "@TheAdamFields",
        "I'm learning #spring development with @craigsdennis...",
        new Date(1502769600000L)
    );

    Tweet tweet2 = new Tweet(
        "@TheAdamFields",
        "I'm learning #android development with @craigsdennis...",
        new Date(1502856000000L)
    );

    // System.out.println("");
    // System.out.println(tweet1);
    // System.out.println("--------------------");
    // System.out.println("");
    // System.out.println(tweet2);

    // Create an array of tweets in reverse order, then sort
    Tweet[] tweets = {tweet2, tweet1};
    Arrays.sort(tweets);

    // Save the tweets to a .ser file
    Tweets.save(tweets);

    // Load tweets from the file
    Tweet[] loadedTweets = Tweets.load();

    for (Tweet tweet : loadedTweets) {
      System.out.println(tweet);
    }

    for (int i = 0; i < loadedTweets.length; i++) {
      System.out.println("Tweet " + (i + 1) + " hashtags: " + loadedTweets[i].getHashTags());
    }
  }
}
