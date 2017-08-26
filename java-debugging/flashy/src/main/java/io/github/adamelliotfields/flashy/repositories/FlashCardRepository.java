package io.github.adamelliotfields.flashy.repositories;

import io.github.adamelliotfields.flashy.domain.FlashCard;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface FlashCardRepository extends CrudRepository<FlashCard, Long> {
  List<FlashCard> findByIdNotIn(Collection<Long> ids);

  List<FlashCard> findAll();
}
