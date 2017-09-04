package io.github.adamelliotfields.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class CategoryModel {
  @NonNull private final int id;
  @NonNull private final String name;
}
