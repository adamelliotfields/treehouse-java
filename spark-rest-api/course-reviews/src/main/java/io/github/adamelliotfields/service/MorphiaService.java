package io.github.adamelliotfields.service;

import com.mongodb.MongoClient;
import lombok.Getter;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

public class MorphiaService {
  @Getter
  private static final MorphiaService instance = new MorphiaService();

  @Getter
  private final MongoClient mongoClient;

  @Getter
  private final Datastore datastore;

  private MorphiaService() {
    Morphia morphia = new Morphia();
    ValidationExtension validationExtension = new ValidationExtension(morphia);

    String packageName = "io.github.adamelliotfields.entity";
    String dbName = "treehouse_course_reviews";

    morphia.mapPackage(packageName);

    this.mongoClient = new MongoClient();
    this.datastore = morphia.createDatastore(mongoClient, dbName);
  }
}
