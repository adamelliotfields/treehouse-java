package com.adamelliotfields.service;

import com.adamelliotfields.entity.Task;

public interface TaskService {
  Iterable<Task> findAllByUsername(String username);
  Task findOne(String id);
  void toggleComplete(String id);
  void save(Task task);
}
