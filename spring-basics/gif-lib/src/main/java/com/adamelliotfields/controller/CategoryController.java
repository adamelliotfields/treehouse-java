package com.adamelliotfields.controller;

import com.adamelliotfields.model.CategoryModel;
import com.adamelliotfields.model.GifModel;
import com.adamelliotfields.repository.CategoryRepository;
import com.adamelliotfields.repository.GifRepository;
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
