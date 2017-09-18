package io.github.adamelliotfields.repository;

import io.github.adamelliotfields.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  User findByUsername(String username);
}
