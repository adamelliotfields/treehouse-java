package io.github.adamelliotfields.entities;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {
  @Before
  public void setUp() throws Exception {
    // Connect to FakeMongo
    // Make sure connection remains open for all tests
  }

  @After
  public void tearDown() throws Exception {
    // Close connection
  }

  @Test
  public void addingCourseSetsId() throws Exception {
    Course course = new Course("Test", "http://test.com");

    ObjectId courseId = course.getId();

    // datastore.save(course);

  }
}
