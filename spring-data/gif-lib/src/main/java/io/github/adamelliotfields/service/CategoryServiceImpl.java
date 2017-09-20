package io.github.adamelliotfields.service;

import io.github.adamelliotfields.entity.Category;
import io.github.adamelliotfields.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Category> findAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category findCategoryById(String id) {
    return categoryRepository.findOne(id);
  }

  @Override
  public Category saveCategory(Category category) {
    categoryRepository.save(category);

    return category;
  }

  @Override
  public void deleteCategory(Category category) {
    categoryRepository.delete(category);
  }
}
