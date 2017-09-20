package io.github.adamelliotfields.service;

import io.github.adamelliotfields.entity.Category;
import java.util.List;

public interface CategoryService {
  // Return a list of all categories
  List<Category> findAllCategories();

  // Return a single category by ID
  Category findCategoryById(String id);

  // Save a single category and return it
  Category saveCategory(Category category);

  // Delete a category
  void deleteCategory(Category category);
}
