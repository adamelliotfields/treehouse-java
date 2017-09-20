package io.github.adamelliotfields.controller;

import io.github.adamelliotfields.entity.Category;
import io.github.adamelliotfields.entity.Gif;
import io.github.adamelliotfields.form.GifForm;
import io.github.adamelliotfields.service.CategoryService;
import io.github.adamelliotfields.service.GifService;
import io.github.adamelliotfields.web.FlashMessage;
import io.github.adamelliotfields.web.FlashMessage.Status;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GifController {
  private GifService gifService;
  private CategoryService categoryService;

  @Autowired
  public GifController(GifService gifService, CategoryService categoryService) {
    this.gifService = gifService;
    this.categoryService = categoryService;
  }

  @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
  public String getIndex() {
    return "redirect:/gifs";
  }

  @RequestMapping(path = "/upload", method = RequestMethod.GET)
  public String getGifUploadForm(Model model) {
    GifForm gif = new GifForm();
    List<Category> categories = categoryService.findAllCategories();

    if (!model.containsAttribute("gif")) {
      model.addAttribute("gif", gif);
    }

    model.addAttribute("categories", categories);
    model.addAttribute("action", "/gifs");
    model.addAttribute("heading", "Upload");
    model.addAttribute("submit", "Add");

    return "gif/form";
  }

  @RequestMapping(path = {"/gifs", "/gifs/"}, method = RequestMethod.GET)
  public ModelAndView getGifs() {
    List<Gif> gifs = gifService.findAllGifs();

    ModelAndView model = new ModelAndView("gif/index");
    model.addObject("gifs", gifs);

    return model;
  }

  @RequestMapping(path = "/gifs", method = RequestMethod.POST)
  public String postNewGif(@ModelAttribute GifForm gifForm, RedirectAttributes attributes, HttpServletRequest request) {
    Gif gif = gifService.save(gifForm);

    // Add flash message
    FlashMessage flash = new FlashMessage("GIF uploaded successfully!", Status.SUCCESS);
    attributes.addFlashAttribute("flash", flash);

    return String.format("redirect:/gifs/%s", gif.getId());
  }

  @RequestMapping(path = {"/gifs/{id}", "/gifs/{id}/"}, method = RequestMethod.GET)
  public ModelAndView getGifDetailsById(@PathVariable String id) {
    ModelAndView model = new ModelAndView("gif/details");

    Gif gif = gifService.findGifById(id);
    model.addObject("gif", gif);

    return model;
  }

  // Response body binds the return value to the response body instead of rendering a view
  @ResponseBody
  @RequestMapping(path = "/gifs/{id}.gif", method = RequestMethod.GET, produces = MediaType.IMAGE_GIF_VALUE)
  public byte[] getGifImage(@PathVariable String id) {
    try {
      byte[] bytes = gifService
                       .findGifById(id)
                       .getFile()
                       .getBytes();

      return bytes;
    } catch (IOException exception) {
      byte[] bytes = {};

      return bytes;
    }
  }

  // Form for editing an existing GIF
  @RequestMapping(path = {"/gifs/{id}/edit", "/gifs/{id}/edit/"}, method = RequestMethod.GET)
  public String getGifEditForm(@PathVariable String id, Model model) {
    Gif gif = gifService.findGifById(id);
    List<Category> categories = categoryService.findAllCategories();

    if (!model.containsAttribute("gif")) {
      model.addAttribute("gif", gif);
    }

    model.addAttribute("categories", categories);
    model.addAttribute("action", String.format("/gifs/%s", id));
    model.addAttribute("heading", "Edit GIF");
    model.addAttribute("submit", "Update");

    return "gif/form";
  }

  // TODO: Update only the description and category
  // Update an existing GIF
  @RequestMapping(path = "/gifs/{id}", method = RequestMethod.POST)
  public String postGifUpdate(@PathVariable String id, @ModelAttribute GifForm gifForm, RedirectAttributes attributes) {
    gifService.save(gifForm);

    // Flash message
    FlashMessage flash = new FlashMessage("GIF successfully updated!", FlashMessage.Status.SUCCESS);
    attributes.addFlashAttribute("flash", flash);

    return String.format("redirect:/gifs/%s", id);
  }
}
