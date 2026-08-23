package com.adamelliotfields.repository;

import com.adamelliotfields.entity.Gif;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GifRepository extends MongoRepository<Gif, String> {
  List<Gif> findAllByCategoryId(String id);
}
