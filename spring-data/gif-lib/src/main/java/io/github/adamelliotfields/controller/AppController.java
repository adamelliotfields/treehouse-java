package io.github.adamelliotfields.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
  @RequestMapping("/")
  public ModelAndView getIndex() throws Exception {
    ModelAndView modelAndView = new ModelAndView("index");
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("http://api.giphy.com/v1/gifs/search?api_key=dc6zaTOxFJmzC&q=meme&limit=12", String.class);
    ObjectMapper objectMapper = new ObjectMapper();

    JsonNode body = objectMapper.readValue(response.getBody(), JsonNode.class);
    JsonNode data = body.path("data");
    List<Map> gifs = new ArrayList<>();

    data.elements().forEachRemaining(element -> {
      Map<String, Object> gif = new HashMap<>();
      String id = element.path("id").textValue();
      gif.put("id", id);
      gif.put("favorite", true);
      gifs.add(gif);
    });

    modelAndView.addObject("title", "Home");
    modelAndView.addObject("gifs", gifs);

    return modelAndView;
  }

  @RequestMapping("/gifs/{gifId}")
  public ModelAndView getGif(@PathVariable String gifId) throws Exception {
    ModelAndView modelAndView = new ModelAndView("index");

    Map<String, String> category = new HashMap<>();
    Map<String, Object> gif = new HashMap<>();

    category.put("id", gifId);
    category.put("name", "Random");
    category.put("colorCode", "rebeccapurple");

    gif.put("id", gifId);
    gif.put("username", "me");
    gif.put("favorite", true);
    gif.put("description", "A random GIF from Giphy!");
    gif.put("timeSinceUploaded", "2 days");
    gif.put("category", category);

    modelAndView.addObject("title", gif.get("description"));
    modelAndView.addObject("gif", gif);

    return modelAndView;
  }
}
