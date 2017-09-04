package io.github.adamelliotfields.controller;

import io.github.adamelliotfields.model.CategoryModel;
import io.github.adamelliotfields.model.GifModel;
import io.github.adamelliotfields.repository.CategoryRepository;
import io.github.adamelliotfields.repository.GifRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {
  private final CategoryRepository categoryRepository;
  private final GifRepository gifRepository;

  @Autowired
  public CategoryController(CategoryRepository categoryRepository, GifRepository gifRepository) {
    this.categoryRepository = categoryRepository;
    this.gifRepository = gifRepository;
  }

  @RequestMapping("/categories")
  public String getCategories(ModelMap modelMap) {
    List<CategoryModel> categories = categoryRepository.getCategories();
    modelMap.put("categories", categories);

    return "categories";
  }

  @RequestMapping("/categories/{id}")
  public String getCategory(@PathVariable int id, ModelMap modelMap) {
    CategoryModel category = categoryRepository.findById(id);
    modelMap.put("category", category);

    List<GifModel> gifs = gifRepository.findByCategoryId(id);
    modelMap.put("gifs", gifs);

    return "category";
  }
}
