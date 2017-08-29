package io.github.adamelliotfields;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import io.github.adamelliotfields.courseidea.CourseIdea;
import io.github.adamelliotfields.courseidea.CourseIdeaDAO;
import io.github.adamelliotfields.user.User;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class App {
  public static void main(String[] args) {
    CourseIdeaDAO DAO = new CourseIdeaDAO();
    Gson gson = new Gson();

    staticFiles.location("public");

    before("/api/*", (req, res) -> res.type("application/json"));

    before("/api/ideas", (req, res) -> User.unauthorized(req));

    before("/api/ideas/*", (req, res) -> User.unauthorized(req));

    get("/api/login", (req, res) -> {
      Map<String, String> model = new HashMap<>();

      if (User.isLoggedIn(req)) {
        model.put("username", User.getUserName(req));
      }

      return model;
    }, gson::toJson);

    post("/api/login", (req, res) -> {
      Map<String, String> model = new HashMap<>();
      String username = req.queryParams("username");

      model.put("username", username);

      res.cookie("username", username);
      res.redirect("/api/login");

      return null;
    });

    get("/api/ideas", (req, res) -> {
      Map<String, Object> model = new HashMap<>();

      model.put("username", User.getUserName(req));
      model.put("ideas", DAO.findAll());

      return model;
    }, gson::toJson);

    post("/api/ideas", (req, res) -> {
      String title = req.queryParams("title");
      CourseIdea courseIdea = new CourseIdea(title, req.cookie("username"));

      DAO.add(courseIdea);

      res.redirect("/api/ideas");

      return null;
    });

    get("/api/ideas/:slug", (req, res) -> {
      Map<String, Object> model = new HashMap<>();

      model.put("username", User.getUserName(req));
      model.put("idea", DAO.findBySlug(req.params("slug")));

      return model;
    }, gson::toJson);

    post("/api/ideas/:slug/vote", (req, res) -> {
      CourseIdea courseIdea = DAO.findBySlug(req.params("slug"));

      courseIdea.addVoter(req.cookie("username"));

      res.redirect("/api/ideas/" + req.params("slug"));

      return null;
    });

    get("/*", (req, res) -> {
      Map<?, ?> model = new HashMap<>();

      return new ThymeleafTemplateEngine().render(new ModelAndView(model, "index"));
    });
  }
}
