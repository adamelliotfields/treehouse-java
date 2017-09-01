package io.github.adamelliotfields.dao;

import io.github.adamelliotfields.entity.Course;
import io.github.adamelliotfields.entity.Review;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

public class ReviewDAOImpl<T, K> extends BasicDAO<T, K> implements ReviewDAO<T, K> {
  private final Datastore datastore;

  public ReviewDAOImpl(Class<T> clazz, Datastore datastore) {
    super(clazz, datastore);

    this.datastore = datastore;
  }

  public void addReview(Review review) {
    Key key = datastore.save(review);
    String reviewId = key.getId().toString();
    ObjectId courseId = new ObjectId(review.getCourseId());

    Query<Course> query = datastore
                            .createQuery(Course.class)
                            .filter("id", courseId);

    UpdateOperations<Course> update = datastore
                                        .createUpdateOperations(Course.class)
                                        .push("reviews", reviewId);

    datastore.update(query, update);
  }
}
