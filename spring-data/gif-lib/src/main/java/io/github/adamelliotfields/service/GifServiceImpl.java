package io.github.adamelliotfields.service;

import io.github.adamelliotfields.document.Category;
import io.github.adamelliotfields.document.Gif;
import io.github.adamelliotfields.form.GifForm;
import io.github.adamelliotfields.repository.CategoryRepository;
import io.github.adamelliotfields.repository.GifRepository;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Log
@Service
public class GifServiceImpl implements GifService {
  private GifRepository gifRepository;
  private CategoryRepository categoryRepository;

  @Autowired
  public GifServiceImpl(GifRepository gifRepository, CategoryRepository categoryRepository) {
    this.gifRepository = gifRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Gif> findAllGifs() {
    return gifRepository.findAll();
  }

  @Override
  public Gif findGifById(String id) {
    return gifRepository.findOne(id);
  }

  @Override
  public Gif save(GifForm gifForm) {
    MultipartFile file = gifForm.getFile();
    String description = gifForm.getDescription();
    Category category = gifForm.getCategory();

    Gif gif = new Gif();
    gif.setDescription(description);
    gif.setCategory(category);

    if (!file.isEmpty()) {
      gif.setFile(file);
    }

    return gifRepository.save(gif);
  }

  @Override
  public void delete(Gif gif) {
    gifRepository.delete(gif);
  }
}
