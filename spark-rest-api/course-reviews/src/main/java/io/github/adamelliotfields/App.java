package io.github.adamelliotfields;

import com.mongodb.MongoClient;
import io.github.adamelliotfields.entities.Course;
import io.github.adamelliotfields.entities.Review;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

public class App {
  public static void main(String[] args) {
    final Morphia morphia = new Morphia();

    morphia.mapPackage("io.github.adamelliotfields.entities");

    final Datastore datastore = morphia.createDatastore(new MongoClient(), "treehouse-course-reviews");
    datastore.getDB().dropDatabase();

    final Course learnReact = new Course("Learn React", "teamtreehouse.com/learn-react");
    datastore.save(learnReact);

    final Review learnReactReview = new Review(learnReact.getId(), 5, "Loved It!");
    datastore.save(learnReactReview);

    learnReact.reviews.add(learnReactReview.getId());
    datastore.save(learnReact);

    final Query<Course> query = datastore.find(Course.class)
                             .filter("id", learnReact.getId());

    final UpdateOperations<Course> update = datastore.createUpdateOperations(Course.class)
                                                     .set("name", "Advanced React");

    final UpdateResults updateResults = datastore.update(query, update);

    System.out.println(updateResults);

    System.out.println(
        datastore.find(Course.class)
                 .filter("id", learnReact.getId())
                 .get()
                 .toString()
    );
  }
}
