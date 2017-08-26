package io.github.adamelliotfields.flashy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
public class FlashCard {
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Getter
  @Setter
  private String term;

  @Getter
  @Setter
  private String definition;

  protected FlashCard() {
    id = null;
  }

  public FlashCard(String term, String definition) {
    this();
    this.term = term;
    this.definition = definition;
  }
}
