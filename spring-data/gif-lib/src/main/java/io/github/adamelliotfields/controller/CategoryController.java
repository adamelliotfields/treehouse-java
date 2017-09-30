package io.github.adamelliotfields.controller;

import io.github.adamelliotfields.form.CategoryForm;
import io.github.adamelliotfields.service.CategoryService;
import io.github.adamelliotfields.service.GifService;
import io.github.adamelliotfields.web.Color;
import io.github.adamelliotfields.web.FlashMessage;
import io.github.adamelliotfields.web.FlashMessage.Status;
import java.util.Arrays;
import javax.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/categories")
public class CategoryController {
  private final CategoryService categoryService;
  private final GifService gifService;

  @Autowired
  public CategoryController(CategoryService categoryService, GifService gifService) {
    this.categoryService = categoryService;
    this.gifService = gifService;
  }

  // Get all categories
  @GetMapping(path = {"", "/"})
  public ModelAndView getCategories() {
    val categories = categoryService.findAllCategories();
    val model = new ModelAndView("category/index");

    model.addObject("categories", categories);

    return model;
  }

  // Post a new category and redirect to all categories
  @PostMapping(path = {"", "/"})
  public String postNewCategory(@Valid @ModelAttribute CategoryForm form, BindingResult result, RedirectAttributes attributes) {
    // hasErrors() returns a boolean if there were validation errors
    if (result.hasErrors()) {
      // Include validation errors on redirect
      // Use the fully qualified class name
      // "category" refers to our Category entity
      attributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
      // Add category if invalid data was received
      // This will be used to populate the form with existing data upon redirection
      // Flash attributes only survive one redirect
      attributes.addFlashAttribute("category", form);

      return "redirect:/categories/add";
    }

    // Check if the category name exists
    String name = form.getName();

    if (categoryService.doesCategoryExist(name)) {
      val message = String.format("Category %s already exists!", name);
      val flash = new FlashMessage(message, Status.FAILURE);

      attributes.addFlashAttribute("flash", flash);
      attributes.addFlashAttribute("category", form);

      return "redirect:/categories/add";
    }

    // Save the new category and add a flash message
    val category = categoryService.saveCategoryFromForm(form);
    val message = String.format("%s category successfully added!", category.getName());
    val flash = new FlashMessage(message, Status.SUCCESS);

    attributes.addFlashAttribute("flash", flash);

    return "redirect:/categories";
  }

  // Get category form
  @GetMapping(path = {"/form", "/form/"})
  public String getCategoryForm(Model model) {
    val form = new CategoryForm();
    val colors = Arrays.asList(Color.values());

    // Because we redirect to /categories/add from an invalid form submission,
    // we need to make sure we don't have an existing category object from adding the flashAttribute
    if (!model.containsAttribute("category")) {
      model.addAttribute("category", form);
    }

    model.addAttribute("colors", colors);
    model.addAttribute("action", "/categories");
    model.addAttribute("method", "post");
    model.addAttribute("heading", "New Category");
    model.addAttribute("button", "Add");

    return "category/form";
  }

  // Get category details by ID
  @GetMapping(path = {"/{id}", "/{id}/"})
  public ModelAndView getCategoryById(@PathVariable String id) {
    val model = new ModelAndView("category/details");
    val category = categoryService.findCategoryById(id);
    val gifs = gifService.findAllGifsByCategoryId(id);

    model.addObject("category", category);
    model.addObject("gifs", gifs);

    return model;
  }

  // Update a category by ID
  @PutMapping(path = {"/{id}", "/{id}/"})
  public String postCategoryEdit(@PathVariable String id, @Valid @ModelAttribute CategoryForm form, BindingResult result, RedirectAttributes attributes) {
    if (result.hasErrors()) {
      attributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
      attributes.addFlashAttribute("category", form);

      return String.format("redirect:/categories/%s/edit", id);
    }

    val message = String.format("%s category successfully updated!", form.getName());
    val flash = new FlashMessage(message, Status.SUCCESS);

    categoryService.updateCategoryByIdFromForm(id, form);

    attributes.addFlashAttribute("flash", flash);

    return "redirect:/categories";
  }

  @DeleteMapping(path = {"/{id}", "/{id}/"})
  public String deleteCategory(@PathVariable String id, RedirectAttributes attributes) {
    val gifCount = gifService.countGifsByCategoryId(id);
    val category = categoryService.findCategoryById(id);
    val flashSuccess = new FlashMessage("Category deleted!", Status.SUCCESS);
    val flashFailure = new FlashMessage("Only empty categories can be deleted!", Status.FAILURE);

    if (gifCount > 0) {
      attributes.addFlashAttribute("flash", flashFailure);

      return String.format("redirect:/categories/%s/edit", id);
    }

    categoryService.deleteCategory(category);

    attributes.addFlashAttribute("flash", flashSuccess);

    return "redirect:/categories/";
  }

  // Get the category edit form
  @GetMapping(path = {"/{id}/edit", "/{id}/edit/"})
  public String getCategoryEditForm(@PathVariable String id, Model model) {
    val category = categoryService.findCategoryById(id);
    val colors = Arrays.asList(Color.values());

    if (!model.containsAttribute("category")) {
      model.addAttribute("category", category);
    }

    model.addAttribute("colors", colors);
    model.addAttribute("action", String.format("/categories/%s", category.getId()));
    model.addAttribute("method", "put");
    model.addAttribute("heading", "Edit Category");
    model.addAttribute("button", "Update");

    return "category/form";
  }
}
