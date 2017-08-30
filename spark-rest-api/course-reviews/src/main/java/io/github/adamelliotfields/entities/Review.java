package io.github.adamelliotfields.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("reviews")
public class Review {
  @Id
  private ObjectId id;

  private ObjectId courseId;
  private int rating;
  private String comment;

  public Review() {}

  public Review(ObjectId courseId, int rating, String comment) {
    this.courseId = courseId;
    this.rating = rating;
    this.comment = comment;
  }

  public ObjectId getId() {
    return id;
  }
}
