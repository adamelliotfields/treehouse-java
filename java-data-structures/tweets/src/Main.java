/*If Main was in the module root and Tweet was in the package,
 * you would have to import it:
 *
 * import tweets.Tweet;
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tweet.Tweet;
import tweet.TweetList;

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

    // Create an array of tweets in reverse order, then sort
    Tweet[] tweets = {tweet2, tweet1};
    Arrays.sort(tweets);

    // Save the tweets to a .ser file
    TweetList.save(tweets);

    // Load tweets from the file
    Tweet[] loadedTweets = TweetList.load();

    for (Tweet tweet : loadedTweets) {
      System.out.println(tweet);
    }

    Set<String> allHashTags = new HashSet<>();
    Set<String> allMentions = new HashSet<>();

    for (Tweet tweet : tweets) {
      allHashTags.addAll(tweet.getHashTags());
      allMentions.addAll(tweet.getMentions());
    }

    System.out.printf("Hashtags: %s %n", allHashTags);
    System.out.printf("Mentions: %s %n", allMentions);

    Map<String, Integer> hashTagCounts = new HashMap<>();

    for (Tweet tweet : tweets) {
      for (String hashTag : tweet.getHashTags()) {
        Integer count = hashTagCounts.get(hashTag);

        if (count  == null) {
          count = 0;
        }

        count++;
        hashTagCounts.put(hashTag, count);
      }
    }

    System.out.printf("Hashtag Counts: %s %n", hashTagCounts);

    Map<String, List<Tweet>> tweetsByAuthor = new HashMap<>();

    for (Tweet tweet : tweets) {
      List<Tweet> authoredTweets = tweetsByAuthor.get(tweet.getAuthor());

      if (authoredTweets == null) {
        authoredTweets = new ArrayList<>();
        tweetsByAuthor.put(tweet.getAuthor(), authoredTweets);
      }

      authoredTweets.add(tweet);
    }

    System.out.printf("Tweets by author: %s %n", tweetsByAuthor);
  }
}
