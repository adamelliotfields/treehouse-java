package io.github.adamelliotfields.dao;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.adamelliotfields.entity.Course;
import io.github.adamelliotfields.entity.Review;
import io.github.adamelliotfields.service.FongoService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

public class ReviewDAOTest {
  private FongoService fongoService = FongoService.getInstance();
  private Datastore datastore = fongoService.getDatastore();

  private CourseDAO<Course, ObjectId> courseDAO = new CourseDAOImpl<>(Course.class, datastore);
  private ReviewDAO<Review, ObjectId> reviewDAO = new ReviewDAOImpl<>(Review.class, datastore);

  @Test
  public void addReview() throws Exception {
    Course testCourse = new Course("Test Course", "http://test.com");
    Key<Course> testCourseKey = courseDAO.save(testCourse);
    String testCourseId = testCourseKey.getId().toString();

    Review testReview = new Review(courseDAO.getIdAsString(testCourse), 5, "Test Comment");
    Key<Review> testReviewKey = reviewDAO.addReview(testReview);
    String testReviewId = testReviewKey.getId().toString();

    String queryResult = courseDAO.get(new ObjectId(testCourseId))
                                  .getReviews()
                                  .get(0);

    assertThat(testReviewId).isEqualToIgnoringCase(queryResult);
  }
}
