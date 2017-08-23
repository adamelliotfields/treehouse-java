package io.github.adamelliotfields;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {
  /**
   * The string format that is waiting to receive values
   */
  private String compiled;
  private List<String> placeholders;

  /**
   * @param text A template with double underscored surrounded placeholders. eg: Hello __name__!
   */
  public Template(String text) {
    // Match on double underscore surrounded words, like __name__ or __proper noun__
    Pattern pattern = Pattern.compile("__([^__]+)__");
    Matcher matcher = pattern.matcher(text);
    placeholders = new ArrayList<String>();

    while (matcher.find()) {
      String label = matcher.group(1);
      placeholders.add(label);
    }

    compiled = matcher.replaceAll("%s");
  }

  /**
   * @return Ordered names of placeholders in the template
   */
  public List<String> getPlaceHolders() {
    return placeholders;
  }


  /**
   * Given a list of values, replaces the fill in the blanks in order.
   *
   * @param values The replacements for the fill in the blank
   * @return The filled out TreeStory
   */
  public String render(List<String> values) {
    // String.format accepts the templates and Object... (a variable amount of objects)
    return String.format(compiled, values.toArray());
  }
}
