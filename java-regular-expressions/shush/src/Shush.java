import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shush {
  public static void main(String[] args) {
    String script = "Procrastination is surely not the destination, should we talk about shiny things?";

    // \\w matches word characters
    // * matches zero or more times
    // the CASE_INSENSITIVE constant is equivalent to the regex modifier (?i)
    Pattern pattern = Pattern.compile("(\\w*(sh|ti|su)\\w*)", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(script);

    while (matcher.find()) {
      System.out.printf(
          "%s is a shush-y word because of the letters %s.%n",
          matcher.group(1),
          matcher.group(2)
      );
    }
  }
}
