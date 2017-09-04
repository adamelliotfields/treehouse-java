package io.github.adamelliotfields.repository;

import io.github.adamelliotfields.model.GifModel;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class GifRepository {
  @Getter
  private final List<GifModel> gifs = Arrays.asList(
      new GifModel(3, "android-explosion", LocalDate.of(2015,2,13), "Chris Ramacciotti", false),
      new GifModel(2, "ben-and-mike", LocalDate.of(2015,10,30), "Ben Jakuben", true),
      new GifModel(1, "book-dominos", LocalDate.of(2015,9,15), "Craig Dennis", false),
      new GifModel(1, "compiler-bot", LocalDate.of(2015,2,13), "Ada Lovelace", true),
      new GifModel(2, "cowboy-coder", LocalDate.of(2015,2,13), "Grace Hopper", false),
      new GifModel(2, "infinite-andrew", LocalDate.of(2015,8,23), "Marissa Mayer", true)
  );

  public GifModel findByName(String name) {
    return gifs.stream()
               .filter(gif -> gif.getName().equals(name))
               .findFirst()
               .orElse(null);
  }

  public List<GifModel> findByCategoryId(int id) {
    return gifs.stream()
               .filter(gif -> gif.getCategoryId() == id)
               .collect(Collectors.toList());
  }
}
