package io.github.adamelliotfields.dao;

import com.github.fakemongo.Fongo;
import io.github.adamelliotfields.entity.Course;
import io.github.adamelliotfields.entity.Review;
import io.github.adamelliotfields.service.FongoService;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

public class CourseDAOTest {
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
  public void getAllCoursesAsList() throws Exception {
    Course course1 = new Course("test_1", "http://test-1.com");
    courseDAO.save(course1);

    Course course2 = new Course("test_2", "http://test-2.com");
    courseDAO.save(course2);

    List<Course> results = courseDAO.getAllCoursesAsList();

    // assert that results is an array with size 2
  }

  @Test
  public void getIdAsString() throws Exception {
  }
}
