package io.github.adamelliotfields.user;

import static spark.Spark.halt;

import spark.Request;

public class User {
  public static boolean isLoggedIn(Request req) {
    return req.cookie("username") != null;
  }

  public static void unauthorized(Request req) {
    if (!User.isLoggedIn(req)) {
      halt(401, "Unauthorized");
    }
  }

  public static String getUserName(Request req) {
    if (isLoggedIn(req)) {
      return req.cookie("username");
    } else {
      return "";
    }
  }
}
