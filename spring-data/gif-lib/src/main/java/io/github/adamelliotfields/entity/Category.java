package io.github.adamelliotfields.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Category {
  @Id
  private String id;

  private String name;
  private String colorCode;

  public Category(String name, String colorCode) {
    this.name = name;
    this.colorCode = colorCode;
  }
}
