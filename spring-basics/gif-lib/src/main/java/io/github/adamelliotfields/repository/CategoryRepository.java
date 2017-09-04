package io.github.adamelliotfields.repository;

import io.github.adamelliotfields.model.CategoryModel;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepository {
  @Getter
  private final List<CategoryModel> categories = Arrays.asList(
      new CategoryModel(1, "Technology"),
      new CategoryModel(2, "People"),
      new CategoryModel(3, "Destruction")
  );

  public CategoryModel findById(int id) {
    return categories.stream()
                     .filter(category -> category.getId() == id)
                     .findFirst()
                     .orElse(null);
  }
}
