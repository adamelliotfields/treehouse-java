package io.github.adamelliotfields.repository;

import io.github.adamelliotfields.entity.Gif;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GifRepository extends MongoRepository<Gif, String> {
}
