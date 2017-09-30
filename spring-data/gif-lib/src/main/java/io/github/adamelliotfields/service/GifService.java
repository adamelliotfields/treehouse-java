package io.github.adamelliotfields.service;

import io.github.adamelliotfields.entity.Gif;
import io.github.adamelliotfields.form.GifForm;
import java.util.List;

public interface GifService {
  // Return a list of all GIFS
  List<Gif> findAllGifs();

  // Return a single GIF by ID
  Gif findGifById(String id);

  // Return a list of favorite GIFs
  List<Gif> findAllGifsByFavorite();

  // Return a list of GIFS by Category ID
  List<Gif> findAllGifsByCategoryId(String id);

  // Return a long count of GIFs by Category ID
  long countGifsByCategoryId(String id);

  // Save a single GIF and return it
  Gif saveGifFromForm(GifForm form);

  // Toggle a GIF as a favorite by ID
  Gif favoriteGifById(String id);

  // Update a GIF by ID
  void updateGifByIdFromForm(String id, GifForm form);

  // Delete a GIF
  void deleteGif(Gif gif);
}
