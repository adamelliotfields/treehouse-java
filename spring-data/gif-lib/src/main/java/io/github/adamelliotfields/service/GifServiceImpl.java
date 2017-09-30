package io.github.adamelliotfields.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import io.github.adamelliotfields.entity.Gif;
import io.github.adamelliotfields.form.GifForm;
import io.github.adamelliotfields.repository.CategoryRepository;
import io.github.adamelliotfields.repository.GifRepository;
import java.io.IOException;
import java.util.List;
import lombok.SneakyThrows;
import lombok.val;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class GifServiceImpl implements GifService {
  private final GifRepository gifRepository;
  private final CategoryRepository categoryRepository;
  private final MongoOperations mongoOperations;

  @Autowired
  public GifServiceImpl(GifRepository gifRepository, CategoryRepository categoryRepository, MongoOperations mongoOperations) {
    this.gifRepository = gifRepository;
    this.categoryRepository = categoryRepository;
    this.mongoOperations = mongoOperations;
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
  public List<Gif> findAllGifsByFavorite() {
    val query = new Query(where("favorite").is(true));

    return mongoOperations.find(query, Gif.class);
  }

  @Override
  public List<Gif> findAllGifsByCategoryId(String id) {
    return gifRepository.findAllByCategoryId(id);
  }

  @Override
  public long countGifsByCategoryId(String id) {
    val query = new Query(where("category.id").is(id));

    return mongoOperations.count(query, Gif.class);
  }

  @Override
  @SneakyThrows(IOException.class)
  public Gif saveGifFromForm(GifForm form) {
    val file = form.getFile();
    val description = form.getDescription();
    val categoryId = form.getCategory().getId();
    val category = categoryRepository.findOne(categoryId);
    val binary = new Binary(file.getBytes());
    val gif = new Gif(binary, description, category);

    return gifRepository.save(gif);
  }

  @Override
  public Gif favoriteGifById(String id) {
    val gif = gifRepository.findOne(id);

    gif.setFavorite(!gif.isFavorite());

    return gifRepository.save(gif);
  }

  @Override
  @SneakyThrows(IOException.class)
  public void updateGifByIdFromForm(String id, GifForm form) {
    val file = form.getFile();
    val formDescription = form.getDescription();
    val formCategoryId = form.getCategory().getId();
    val gif = gifRepository.findOne(id);
    val gifCategoryId = gif.getId();
    val gifDescription = gif.getDescription();

    // If the file is not null and not empty, set the new file
    if (file != null && !file.isEmpty()) {
      val binary = new Binary(file.getBytes());

      gif.setFile(binary);
    }

    // If the form category doesn't equal the GIF category, set the new category
    if (!gifCategoryId.equals(formCategoryId)) {
      val category = categoryRepository.findOne(formCategoryId);

      gif.setCategory(category);
    }

    // If the form description doesn't equal the GIF description, set the new description
    if (!gifDescription.equals(formDescription)) {
      gif.setDescription(formDescription);
    }

    gifRepository.save(gif);
  }

  @Override
  public void deleteGif(Gif gif) {
    gifRepository.delete(gif);
  }
}
