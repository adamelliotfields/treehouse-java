package io.github.adamelliotfields.courseidea;

import java.util.HashSet;
import java.util.Set;

public class CourseIdeaDAO {
  private Set<CourseIdea> ideas;

  public CourseIdeaDAO() {
    ideas = new HashSet<>();
  }

  public boolean add(CourseIdea idea) {
    return ideas.add(idea);
  }

  public Set<CourseIdea> findAll() {
    return new HashSet<>(ideas);
  }

  public CourseIdea findBySlug(String slug) {
    return ideas.stream()
                .filter(idea -> idea.getSlug().equals(slug))
                // findFirst returns an Optional
                .findFirst()
                .orElseThrow(RuntimeException::new);
  }
}
