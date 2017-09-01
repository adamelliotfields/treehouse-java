package io.github.adamelliotfields.dao;

import static org.junit.Assert.assertEquals;

import com.github.fakemongo.Fongo;
import io.github.adamelliotfields.entity.Course;
import io.github.adamelliotfields.entity.Review;
import io.github.adamelliotfields.service.FongoService;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

public class ReviewDAOTest {
  private Fongo fongo;
  private CourseDAO<Course, ObjectId> courseDAO;
  private ReviewDAO<Review, ObjectId> reviewDAO;

  @Before
  public void setUp() throws Exception {
    FongoService fongoService = FongoService.getInstance();
    Datastore datastore = fongoService.getDatastore();

    fongo = fongoService.getFongo();
    courseDAO = new CourseDAOImpl<>(Course.class, datastore);
    reviewDAO = new ReviewDAOImpl<>(Review.class, datastore);
  }

  @After
  public void tearDown() throws Exception {
    fongo.dropDatabase("test");
  }

  @Test
  public void addReview() throws Exception {
    Course testCourse = new Course("Test Course", "http://test.com");
    courseDAO.save(testCourse);

    Review testCourseReview = new Review(courseDAO.getIdAsString(testCourse), 5, "Test Comment");
    reviewDAO.addReview(testCourseReview);

    String expected = testCourseReview.getId().toHexString();

    String actual = courseDAO
                      .get(testCourse.getId())
                      .getReviews()
                      .get(0);

    assertEquals(expected, actual);
  }
}
