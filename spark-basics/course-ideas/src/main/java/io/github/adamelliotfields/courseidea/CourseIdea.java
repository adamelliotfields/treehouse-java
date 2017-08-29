package io.github.adamelliotfields.courseidea;

import com.github.slugify.Slugify;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class CourseIdea {
  @Getter
  @Setter
  private String title;

  @Getter
  @Setter
  private String creator;

  @Getter
  private String slug;

  private Set<String> voters;

  public CourseIdea(String title, String creator) {
    this.title = title;
    this.creator = creator;

    Slugify slugify = new Slugify();
    slug = slugify.slugify(title);

    voters = new HashSet<>();
  }

  public boolean addVoter(String voter) {
    // Calling add on a Set returns a boolean
    return voters.add(voter);
  }

  public List<String> getVoters() {
    return new ArrayList<>(voters);
  }

  public int getVoteCount() {
    return voters.size();
  }
}
