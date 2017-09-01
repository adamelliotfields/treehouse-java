package io.github.adamelliotfields.service;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import io.github.adamelliotfields.entity.Course;
import io.github.adamelliotfields.entity.Review;
import lombok.Getter;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class FongoService {
  @Getter
  private static final FongoService instance = new FongoService();

  @Getter
  private final Fongo fongo;

  @Getter
  private final MongoClient fongoClient;

  @Getter
  private final Morphia morphia;

  @Getter
  private final Datastore datastore;

  private FongoService() {
    fongo = new Fongo("test");
    morphia = new Morphia();
    fongoClient = fongo.getMongo();

    morphia.map(Course.class, Review.class);

    datastore = morphia.createDatastore(fongoClient, "test");
  }
}
