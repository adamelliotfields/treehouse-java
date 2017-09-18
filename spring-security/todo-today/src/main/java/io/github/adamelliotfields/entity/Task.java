package io.github.adamelliotfields.entity;

import io.github.adamelliotfields.dto.TaskDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Task {
  @Id
  private String id;

  private String description;
  private String username;
  private boolean complete;

  public Task(TaskDto taskDto, User user) {
    this.description = taskDto.getDescription();
    this.username = user.getUsername();
    this.complete = false;
  }
}
