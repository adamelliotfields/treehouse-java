package io.github.adamelliotfields;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.adamelliotfields.dao.CourseDAO;
import io.github.adamelliotfields.dao.CourseDAOImpl;
import io.github.adamelliotfields.dao.ReviewDAO;
import io.github.adamelliotfields.dao.ReviewDAOImpl;
import io.github.adamelliotfields.entity.Course;
import io.github.adamelliotfields.entity.Review;
import io.github.adamelliotfields.service.MorphiaService;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

// TODO: Add API error handling
// TODO: Add Database error handling
// TODO: Add PUT routes for course and review
// TODO: Add route unit tests
public class App {
  public static void main(String[] args) {
    MorphiaService morphiaService = MorphiaService.getInstance();
    ObjectMapper objectMapper = new ObjectMapper();

    Datastore datastore = morphiaService.getDatastore();
    datastore.getDB().dropDatabase();

    CourseDAO<Course, ObjectId> courseDAO = new CourseDAOImpl<>(Course.class, datastore);
    ReviewDAO<Review, ObjectId> reviewDAO = new ReviewDAOImpl<>(Review.class, datastore);

    // GET all courses
    get("/courses", "application/json", (req, res) -> {
      return courseDAO.getAllCoursesAsList();
    }, objectMapper::writeValueAsString);

    // POST a new course
    post("/courses", "application/json", (req, res) -> {
      Course course = objectMapper.readValue(req.body(), Course.class);

      courseDAO.save(course);

      res.status(201);
      res.redirect("/courses/" + courseDAO.getIdAsString(course));

      return null;
    });

    // GET a course by ID
    get("/courses/:id", "application/json", (req, res) -> {
      ObjectId id = new ObjectId(req.params("id"));

      return courseDAO.get(id);
    }, objectMapper::writeValueAsString);

    // POST a new review
    post("/courses/:id/reviews", "application/json", (req, res) -> {
      String id = req.params("id");
      Review review = objectMapper.readValue(req.body(), Review.class);

      reviewDAO.addReview(review);

      res.redirect("/courses/" + id);

      return null;
    });

    // GET all reviews by Course ID
    get("/courses/:id/reviews", (req, res) -> {
      String id = req.params("id");

      return courseDAO.get(new ObjectId("id")).getReviews();
    }, objectMapper::writeValueAsString);

    // GET a review by ID
    get("/courses/:courseId/reviews/:reviewId", (req, res) -> {
      ObjectId id = new ObjectId(req.params("reviewId"));

      return reviewDAO.get(id);
    });

    // API Error
    // exception(ApiError.class, (err, req, res) -> {
    //   ApiError error = (ApiError) err;
    //   Map<String, Object> model = new HashMap<>();
    //
    //   model.put("statusCode", error.getStatusCode());
    //   model.put("error", error.getError());
    //
    //   res.type("application/json");
    //   res.status(err.getStatusCode());
    //
    //   try {
    //     res.body(objectMapper.writeValueAsString(model));
    //   } catch (JsonProcessingException jpe) {
    //     jpe.printStackTrace();
    //   }
    // });

    after((req, res) -> res.type("application/json"));
  }
}
