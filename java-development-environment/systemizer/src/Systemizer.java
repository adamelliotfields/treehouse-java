import java.util.Set;
import java.util.TreeSet;

public class Systemizer {
  public static void main(String[] args) {
    System.out.printf(
        "This is the classpath: %s%n",
        System.getProperty("java.class.path")
    );
    // Because Set is given an explicit type of String, we can pass the diamond operator to TreeSet
    // This is syntactic sugar for Set<String> propNames = new TreeSet<String>...
    Set<String> propNames = new TreeSet<>(System.getProperties().stringPropertyNames());

    for (String propertyName : propNames) {
      System.out.printf(
          "%s is %s%n",
          propertyName,
          System.getProperty(propertyName)
      );
    }
  }
}
