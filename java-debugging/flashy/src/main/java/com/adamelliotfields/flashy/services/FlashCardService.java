package com.adamelliotfields.flashy.services;

import com.adamelliotfields.flashy.domain.FlashCard;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FlashCardService {
  Long getCurrentCount();

  FlashCard getFlashCardById(Long id);

  FlashCard getNextUnseenFlashCard(Collection<Long> seenIds);

  FlashCard getNextFlashCardBasedOnViews(Map<Long, Long> idToViewCounts);

  List<FlashCard> getRandomFlashCards(int i);
}
