package com.adamelliotfields.repository;

import com.adamelliotfields.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {}
