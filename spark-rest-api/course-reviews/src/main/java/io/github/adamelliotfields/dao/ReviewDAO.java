package io.github.adamelliotfields.dao;

import io.github.adamelliotfields.entity.Review;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.DAO;

public interface ReviewDAO<T, K> extends DAO<T, K> {
  Key<Review> addReview(Review review);
}
