package io.github.adamelliotfields.jobs.model;

import com.google.api.client.util.Key;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Job {
  @Key
  private String jobtitle;

  @Getter
  @Key
  private String company;

  @Getter
  @Key
  private String city;

  @Getter
  @Key
  private String state;

  @Getter
  @Key
  private String country;

  @Getter
  @Key
  private String snippet;

  @Key
  private String date;

  public String getTitle() {
    return jobtitle;
  }

  public String getDateTimeString() {
    return date;
  }

  public String getCaption() {
    return String.format(
        "%s is looking for a %s in %s, %s",
        getCompany(),
        getTitle(),
        getCity(),
        getState()
    );
  }
}
