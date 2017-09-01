package io.github.adamelliotfields.dao;

import io.github.adamelliotfields.entity.Course;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class CourseDAOImpl<T, K> extends BasicDAO<T, K> implements CourseDAO<T, K> {
  private final Datastore datastore;
  private final Class<T> clazz;

  public CourseDAOImpl(Class<T> clazz, Datastore datastore) {
    super(clazz, datastore);

    this.clazz = clazz;
    this.datastore = datastore;
  }

  @Override
  public List<T> getAllCoursesAsList() {
    return datastore.find(clazz).asList();
  }

  @Override
  public String getIdAsString(Course course) {
    return course.getId().toHexString();
  }
}
