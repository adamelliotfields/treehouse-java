package io.github.adamelliotfields.repository;

import io.github.adamelliotfields.document.Gif;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GifRepository extends MongoRepository<Gif, String> {
}
