package io.github.adamelliotfields.repository;

import io.github.adamelliotfields.entity.Task;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
  // TODO: Only find tasks that belong to the signed in user
  List<Task> findAllByUsername(String username);
}
