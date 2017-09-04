package io.github.adamelliotfields.controller;

import io.github.adamelliotfields.model.GifModel;
import io.github.adamelliotfields.repository.GifRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GifController {
  private final GifRepository gifRepository;

  // Always use constructor dependency injection
  @Autowired
  public GifController(GifRepository gifRepository) {
    this.gifRepository = gifRepository;
  }

  @RequestMapping("/")
  public String getIndex(ModelMap modelMap) {
    List<GifModel> gifs = gifRepository.getGifs();
    modelMap.put("gifs", gifs);

    return "index";
  }

  @RequestMapping("/gif/{name}")
  public String getGifDetails(@PathVariable String name, ModelMap modelMap) {
    GifModel model = gifRepository.findByName(name);
    modelMap.put("gif", model);

    return "details";
  }
}
