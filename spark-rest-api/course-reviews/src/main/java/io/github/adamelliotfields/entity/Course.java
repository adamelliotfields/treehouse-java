package io.github.adamelliotfields.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.adamelliotfields.util.ObjectIdJsonSerializer;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Data
@NoArgsConstructor
@Entity("courses")
public class Course {
  @JsonSerialize(using = ObjectIdJsonSerializer.class)
  @Id
  private ObjectId id;

  @NotNull private String name;
  @NotNull private String url;

  private List<String> reviews;

  public Course(String name, String url) {
    this.name = name;
    this.url = url;
    this.reviews = new ArrayList<>();
  }
}
