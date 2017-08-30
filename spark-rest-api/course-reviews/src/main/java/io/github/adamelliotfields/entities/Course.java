package io.github.adamelliotfields.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Data
@NoArgsConstructor
@Entity("courses")
public class Course {
  @Id
  private ObjectId id;

  private String name;
  private String url;

  public List<ObjectId> reviews;

  public Course(String name, String url) {
    this.name = name;
    this.url = url;
    this.reviews = new ArrayList<>();
  }
}
