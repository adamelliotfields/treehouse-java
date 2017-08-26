package io.github.adamelliotfields.jobs.model;

import com.google.api.client.util.Key;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ResultsPage {
  public static int MAX_PER_PAGE = 25;

  @Key("results")
  public List<Job> jobs;

  @Key
  public int start;

  @Key
  public int end;

  @Key
  public int pageNumber;

  @Key
  public int totalResults;
}
