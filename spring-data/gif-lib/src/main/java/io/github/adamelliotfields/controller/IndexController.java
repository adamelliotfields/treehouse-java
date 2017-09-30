package io.github.adamelliotfields.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
  // Index route redirects to /gifs
  @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
  public String getIndex() {
    return "redirect:/gifs";
  }
}
