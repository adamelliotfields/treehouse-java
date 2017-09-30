package io.github.adamelliotfields.controller;

import io.github.adamelliotfields.entity.Gif;
import io.github.adamelliotfields.form.GifForm;
import io.github.adamelliotfields.service.CategoryService;
import io.github.adamelliotfields.service.GifService;
import io.github.adamelliotfields.web.FlashMessage;
import io.github.adamelliotfields.web.FlashMessage.Status;
import java.io.IOException;
import javax.validation.Valid;
import lombok.val;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/gifs")
public class GifController {
  private final GifService gifService;
  private final CategoryService categoryService;

  @Autowired
  public GifController(GifService gifService, CategoryService categoryService) {
    this.gifService = gifService;
    this.categoryService = categoryService;
  }

  // Get all GIFs
  @GetMapping(path = {"", "/"})
  public ModelAndView getGifs() {
    val gifs = gifService.findAllGifs();
    val model = new ModelAndView("gif/index");

    model.addObject("gifs", gifs);

    return model;
  }

  // Post a new GIF
  @PostMapping(path = {"", "/"})
  public String postNewGif(@Valid @ModelAttribute GifForm form, BindingResult result, RedirectAttributes attributes) {
    val file = form.getFile();

    String message;
    FlashMessage flash;
    Gif gif;

    // First check if a user even uploaded a GIF
    if (file == null || file.isEmpty()) {
      message = "You must upload a GIF.";
      flash = new FlashMessage(message, Status.FAILURE);

      attributes.addFlashAttribute("flash", flash);
      attributes.addFlashAttribute("gif", form);

      return "redirect:/gifs/form";
    }

    // Check to ensure that the uploaded file is actually a GIF
    // A valid GIF with an incorrect file extension will be allowed
    // Note: For basic detection (file extension), you can use multipartFile.getContentType()
    // Note: You can also use the Lombok @Cleanup annotation instead of try-with-resources
    try  (val stream = file.getInputStream()) {
      val tika = new Tika();
      val mimeType = tika.detect(stream);

      if (!mimeType.equals("image/gif")) {
        message = String.format("%s is not a valid GIF.", form.getFile().getOriginalFilename());
        flash = new FlashMessage(message, Status.FAILURE);

        attributes.addFlashAttribute("flash", flash);
        attributes.addFlashAttribute("gif", form);

        return "redirect:/gifs/form";
      }
    } catch (IOException exception) {
      message = exception.getMessage();
      flash = new FlashMessage(message, Status.FAILURE);

      attributes.addFlashAttribute("flash", flash);
      attributes.addFlashAttribute("gif", form);

      return "redirect:/gifs/form";
    }

    // Then check if the user added Description and Category fields
    if (result.hasErrors()) {
      attributes.addFlashAttribute("org.springframework.validation.BindingResult.gif", result);
      attributes.addFlashAttribute("gif", form);

      return "redirect:/gifs/form";
    }

    // Finally, attempt to save the GIF
    gif = gifService.saveGifFromForm(form);
    message = "GIF uploaded successfully!";
    flash = new FlashMessage(message, Status.SUCCESS);

    attributes.addFlashAttribute("flash", flash);

    return String.format("redirect:/gifs/%s", gif.getId());
  }

  // GIF details page
  @GetMapping(path = {"/{id}", "/{id}/"})
  public ModelAndView getGifDetailsById(@PathVariable String id) {
    val model = new ModelAndView("gif/details");
    val gif = gifService.findGifById(id);

    model.addObject("gif", gif);

    return model;
  }

  // Toggle a gif as favorite
  // The RequestHeader annotation gives us access to the HTTPServletRequest object
  // The Referer string is intentionally misspelled and must start with a capital R
  @PostMapping(path = {"/{id}", "/{id}/"})
  public String favoriteGif(@PathVariable String id, @RequestHeader("Referer") String referer) {
    val gif = gifService.findGifById(id);

    gifService.favoriteGifById(id);

    // Omit the "/" after redirect, as the referer is a fully-qualified URL
    return String.format("redirect:%s", referer);
  }

  // Update an existing GIF
  @PutMapping(path = {"/{id}", "/{id}/"})
  public String postGifUpdate(@PathVariable String id, @Valid @ModelAttribute GifForm form, BindingResult result, RedirectAttributes attributes) {
    // First check for validation errors before attempting to save
    if (result.hasErrors()) {
      attributes.addFlashAttribute("org.springframework.validation.BindingResult.gif", result);
      attributes.addFlashAttribute("gif", form);

      return String.format("redirect:/gifs/%s/edit", id);
    }

    // Attempt to update the GIF
    gifService.updateGifByIdFromForm(id, form);

    val message = "GIF successfully updated!";
    val flash = new FlashMessage(message, FlashMessage.Status.SUCCESS);

    attributes.addFlashAttribute("flash", flash);

    return String.format("redirect:/gifs/%s", id);
  }

  // Delete a GIF
  @DeleteMapping(path = {"/{id}", "/{id}/"})
  public String deleteGif(@PathVariable String id, RedirectAttributes attributes) {
    val gif = gifService.findGifById(id);
    val flash = new FlashMessage("Deleted GIF!", Status.SUCCESS);

    gifService.deleteGif(gif);

    attributes.addFlashAttribute("flash", flash);

    return "redirect:/gifs";
  }

  // Serves the GIF image data
  // Response body binds the return value to the response body instead of rendering a view
  @ResponseBody
  @GetMapping(path = "/{id}.gif", produces = MediaType.IMAGE_GIF_VALUE)
  public byte[] getGifImage(@PathVariable String id) {
    return gifService
             .findGifById(id)
             .getFile()
             .getData();
  }

  // Get GIF upload form
  @GetMapping(path = {"/form", "/form/"})
  public String getGifUploadForm(Model model) {
    val form = new GifForm();
    val categories = categoryService.findAllCategories();

    if (!model.containsAttribute("gif")) {
      model.addAttribute("gif", form);
    }

    model.addAttribute("categories", categories);
    model.addAttribute("action", "/gifs");
    model.addAttribute("method", "post");
    model.addAttribute("heading", "Upload");
    model.addAttribute("button", "Add");

    if (categories.size() == 0) {
      val message = "You need to add a Category first!";
      val flash = new FlashMessage(message, Status.FAILURE);

      model.addAttribute("flash", flash);
    }

    return "gif/form";
  }

  // Form for editing an existing GIF
  @GetMapping(path = {"/{id}/edit", "/{id}/edit/"})
  public String getGifEditForm(@PathVariable String id, Model model) {
    val gif = gifService.findGifById(id);
    val categories = categoryService.findAllCategories();

    if (!model.containsAttribute("gif")) {
      model.addAttribute("gif", gif);
    }

    model.addAttribute("categories", categories);
    model.addAttribute("action", String.format("/gifs/%s", id));
    model.addAttribute("method", "put");
    model.addAttribute("heading", "Edit GIF");
    model.addAttribute("button", "Update");

    return "gif/form";
  }

  @GetMapping(path = {"/favorites", "/favorites/"})
  public ModelAndView getFavoriteGifs() {
    val gifs = gifService.findAllGifsByFavorite();
    val model = new ModelAndView("gif/favorites");

    model.addObject("gifs", gifs);

    System.out.println(gifs);

    return model;
  }
}
