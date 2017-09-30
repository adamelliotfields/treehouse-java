package io.github.adamelliotfields.service;

import io.github.adamelliotfields.entity.Category;
import io.github.adamelliotfields.form.CategoryForm;
import java.util.List;

public interface CategoryService {
  // Return a list of all categories
  List<Category> findAllCategories();

  // Return a single category by ID
  Category findCategoryById(String id);

  // Return a boolean if a category exists
  boolean doesCategoryExist(String name);

  // Save a single category and return it
  Category saveCategoryFromForm(CategoryForm form);

  // Update a single category by ID
  void updateCategoryByIdFromForm(String id, CategoryForm form);

  // Delete a category
  void deleteCategory(Category category);
}
