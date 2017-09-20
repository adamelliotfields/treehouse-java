package io.github.adamelliotfields.service;

import io.github.adamelliotfields.entity.Gif;
import io.github.adamelliotfields.form.GifForm;
import java.util.List;

public interface GifService {
  // Return a list of all Gifs
  List<Gif> findAllGifs();

  // Return a single Gif by ID
  Gif findGifById(String id);

  // Save a single Gif and return it
  Gif save(GifForm gifForm);

  // Delete a gif
  void delete(Gif gif);
}
