package io.github.adamelliotfields.dao;

import io.github.adamelliotfields.entity.Course;
import java.util.List;
import org.mongodb.morphia.dao.DAO;

public interface CourseDAO<T, K> extends DAO<T, K> {
  List<T> getAllCoursesAsList();

  String getIdAsString(Course course);
}
