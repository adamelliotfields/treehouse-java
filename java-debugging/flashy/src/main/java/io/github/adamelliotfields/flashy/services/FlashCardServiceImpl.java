package io.github.adamelliotfields.flashy.services;

import static java.util.stream.Collectors.toList;

import io.github.adamelliotfields.flashy.domain.FlashCard;
import io.github.adamelliotfields.flashy.repositories.FlashCardRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashCardServiceImpl implements FlashCardService {
  private FlashCardRepository flashCardRepository;

  @Autowired
  public void setFlashCardRepository(FlashCardRepository flashCardRepository) {
    this.flashCardRepository = flashCardRepository;
  }

  @Override
  public Long getCurrentCount() {
    return flashCardRepository.count();
  }

  @Override
  public FlashCard getFlashCardById(Long id) {
    return flashCardRepository.findOne(id);
  }

  @Override
  public FlashCard getNextUnseenFlashCard(Collection<Long> seenIds) {
    List<FlashCard> unseen;

    unseen = seenIds.size() > 0
        ? flashCardRepository.findByIdNotIn(seenIds)
        : flashCardRepository.findAll();

    FlashCard card = null;

    if (unseen.size() > 0) {
      card = unseen.get(new Random().nextInt(unseen.size()));
    }

    return card;
  }

  public FlashCard getLeastViewedFlashCard(Map<Long, Long> idToViewCounts) {
    List<Map.Entry<Long, Long>> entries = new ArrayList<>(idToViewCounts.entrySet());

    Collections.shuffle(entries);

    return entries
               .stream()
               .min(Comparator.comparing(Map.Entry::getValue))
               .map(entry -> flashCardRepository.findOne(entry.getKey()))
               .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public FlashCard getNextFlashCardBasedOnViews(Map<Long, Long> idToViewCounts) {
    FlashCard card = getNextUnseenFlashCard(idToViewCounts.keySet());

    if (card == null) {
      card = getLeastViewedFlashCard(idToViewCounts);
    }

    return card;
  }

  @Override
  public List<FlashCard> getRandomFlashCards(int amount) {
    List<FlashCard> cards = flashCardRepository.findAll();

    Collections.shuffle(cards);

    return cards
               .stream()
               .limit(amount)
               .collect(toList());
  }
}
