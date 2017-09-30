package io.github.adamelliotfields.repository;

import io.github.adamelliotfields.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {}
