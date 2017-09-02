package io.github.adamelliotfields.dao;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.adamelliotfields.entity.Course;
import io.github.adamelliotfields.entity.Review;
import io.github.adamelliotfields.service.FongoService;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

public class CourseDAOTest {
  private FongoService fongoService = FongoService.getInstance();
  private Datastore datastore = fongoService.getDatastore();

  private CourseDAO<Course, ObjectId> courseDAO = new CourseDAOImpl<>(Course.class, datastore);
  private ReviewDAO<Review, ObjectId> reviewDAO = new ReviewDAOImpl<>(Review.class, datastore);

  @Test
  public void getAllCoursesAsList() throws Exception {
    Course course1 = new Course("test_1", "http://test-1.com");
    courseDAO.save(course1);

    Course course2 = new Course("test_2", "http://test-2.com");
    courseDAO.save(course2);

    List<Course> queryResults = courseDAO.getAllCoursesAsList();

    assertThat(queryResults.size()).isEqualTo(2);
  }

  @Test
  public void getIdAsString() throws Exception {
    Course course = new Course("test", "http://test.com");
    String id = courseDAO.save(course).getId().toString();

    assertThat(courseDAO.getIdAsString(course)).isEqualToIgnoringCase(id);
  }
}
