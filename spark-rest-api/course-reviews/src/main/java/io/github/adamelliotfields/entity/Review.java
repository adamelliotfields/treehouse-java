package io.github.adamelliotfields.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.adamelliotfields.util.ObjectIdJsonSerializer;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Data
@NoArgsConstructor
@Entity("reviews")
public class Review {
  @JsonSerialize(using = ObjectIdJsonSerializer.class)
  @Id
  private ObjectId id;

  @NotNull private String courseId;
  @NotNull int rating;
  @NotNull String comment;

  public Review(String courseId, int rating, String comment) {
    this.courseId = courseId;
    this.rating = rating;
    this.comment = comment;
  }
}
