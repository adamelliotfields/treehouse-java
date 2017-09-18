package io.github.adamelliotfields.service;

import io.github.adamelliotfields.repository.TaskRepository;
import io.github.adamelliotfields.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
  private TaskRepository taskRepository;

  @Autowired
  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Iterable<Task> findAllByUsername(String username) {
    return taskRepository.findAllByUsername(username);
  }

  @Override
  public Task findOne(String id) {
    return taskRepository.findOne(id);
  }

  @Override
  public void toggleComplete(String id) {
    Task task = taskRepository.findOne(id);
    task.setComplete(!task.isComplete());
    taskRepository.save(task);
  }

  @Override
  public void save(Task task) {
    taskRepository.save(task);
  }
}
