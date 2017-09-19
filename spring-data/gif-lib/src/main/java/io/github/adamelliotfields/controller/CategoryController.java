package io.github.adamelliotfields.controller;

import io.github.adamelliotfields.document.Category;
import io.github.adamelliotfields.service.CategoryService;
import io.github.adamelliotfields.web.Color;
import io.github.adamelliotfields.web.FlashMessage;
import io.github.adamelliotfields.web.FlashMessage.Status;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  // Get all categories
  @RequestMapping(path = {"/categories", "/categories/"}, method = RequestMethod.GET)
  public ModelAndView getCategories() {
    List<Category> categories = categoryService.findAllCategories();

    ModelAndView model = new ModelAndView("category/index");
    model.addObject("categories", categories);

    return model;
  }

  // Post a new category and redirect to all categories
  @RequestMapping(path = "/categories", method = RequestMethod.POST)
  public String postNewCategory(@Valid @ModelAttribute Category category, BindingResult result, RedirectAttributes attributes) {
    // hasErrors() returns a boolean if there were validation errors
    if (result.hasErrors()) {
      // Include validation errors on redirect
      // Use the fully qualified class name
      attributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
      // Add category if invalid data was received
      // This will be used to populate the form with existing data upon redirection
      // Flash attributes only survive one redirect
      attributes.addFlashAttribute("category", category);

      return "redirect:/categories/add";
    }

    categoryService.saveCategory(category);

    // Add a flash message upon successful insertion
    FlashMessage flash = new FlashMessage(String.format("%s category successfully added!", category.getName()), Status.SUCCESS);
    attributes.addFlashAttribute("flash", flash);

    return "redirect:/categories";
  }

  // TODO: Replace category object with CategoryForm DTO
  // TODO: Add action, heading, and submit fields to CategoryForm
  // Get category form
  @RequestMapping(path = {"/categories/add", "/categories/add/"}, method = RequestMethod.GET)
  public String getCategoryForm(Model model) {
    Category category = new Category();
    List<Color> colors = Arrays.asList(Color.values());

    // Because we redirect to /categories/add from an invalid form submission,
    // we need to make sure we don't have an existing category object from adding the flashAttribute
    if (!model.containsAttribute("category")) {
      model.addAttribute("category", category);
    }

    model.addAttribute("colors", colors);
    model.addAttribute("action", "/categories");
    model.addAttribute("heading", "New Category");
    model.addAttribute("submit", "Add");

    return "category/form";
  }

  // TODO: Redirect to /categories if not found
  // Get category details by ID
  @RequestMapping(path = {"/categories/{id}", "/categories/{id}/"}, method = RequestMethod.GET)
  public ModelAndView getCategoryById(@PathVariable String id) {
    ModelAndView model = new ModelAndView("category/details");
    Category category = categoryService.findCategoryById(id);

    model.addObject("category", category);

    return model;
  }

  // TODO: Make this a PUT route
  // Update a category by ID
  @RequestMapping(path = {"/categories/{id}", "/categories/{id}/"}, method = RequestMethod.POST)
  public String postEditCategory(
      @PathVariable String id,
      @Valid @ModelAttribute Category category,
      BindingResult result,
      RedirectAttributes attributes
  ) {
    if (result.hasErrors()) {
      attributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
      attributes.addFlashAttribute("category", category);

      return String.format("redirect:/categories/%s/edit", id);
    }

    // Saving will overwrite an existing document if it exists, or create a new one
    // In more advanced applications, you'd want to implement an update method
    categoryService.saveCategory(category);

    FlashMessage flash = new FlashMessage(String.format("%s category successfully updated!", category.getName()), Status.SUCCESS);
    attributes.addFlashAttribute("flash", flash);

    return "redirect:/categories";
  }

  // Edit a category
  @RequestMapping(path = {"/categories/{id}/edit", "categories/{id}/edit/"}, method = RequestMethod.GET)
  public String getCategoryEditForm(@PathVariable String id, Model model) {
    Category category = categoryService.findCategoryById(id);

    if (!model.containsAttribute("category")) {
      model.addAttribute("category", category);
    }

    List<Color> colors = Arrays.asList(Color.values());
    model.addAttribute("colors", colors);
    model.addAttribute("action", String.format("/categories/%s", category.getId()));
    model.addAttribute("heading", "Edit Category");
    model.addAttribute("submit", "Update");

    return "category/form";
  }
}
