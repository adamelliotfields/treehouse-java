package io.github.adamelliotfields.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import io.github.adamelliotfields.entity.Category;
import io.github.adamelliotfields.form.CategoryForm;
import io.github.adamelliotfields.repository.CategoryRepository;
import java.util.List;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;
  private final MongoOperations mongoOperations;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository, MongoOperations mongoOperations) {
    this.categoryRepository = categoryRepository;
    this.mongoOperations = mongoOperations;
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
  public boolean doesCategoryExist(String name) {
    val query = new Query(where("name").is(name));

    return mongoOperations.exists(query, Category.class);
  }

  @Override
  public Category saveCategoryFromForm(CategoryForm form) {
    val name = form.getName();
    val colorCode = form.getColorCode();
    val category = new Category(name, colorCode);

    return categoryRepository.save(category);
  }

  @Override
  public void updateCategoryByIdFromForm(String id, CategoryForm form) {
    val name = form.getName();
    val colorCode = form.getColorCode();
    val category = categoryRepository.findOne(id);

    if (!category.getName().equals(name)) {
      category.setName(name);
    }

    if (!category.getColorCode().equals(colorCode)) {
      category.setColorCode(colorCode);
    }

    categoryRepository.save(category);
  }

  @Override
  public void deleteCategory(Category category) {
    categoryRepository.delete(category);
  }
}
