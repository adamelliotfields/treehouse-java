package io.github.adamelliotfields;

import java.awt.geom.Point2D;

@Doc(desc = "Utility class for commonly used math functions")
public class MathUtils {
  private static final Double EPSILON = 0.0001;

  @Doc(
      desc = "Calculate the area of a triangle",
      params = {"Coordinates of the first vertex"},
      returnVal = "Calculated area of the triangle"
  )
  public static Double triangleArea(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
    return 0.0;
  }

  @Doc(
      desc = "Calculates the distance between the given points",
      params = {"Coordinates of one point", "Coordinates of another point"},
      returnVal = ""
  )
  public static Double distance(Point2D.Double a, Point2D.Double b) {
    return 0.0;
  }

  public static Double[] quadraticRoots(int a, int b, int c) {
    return new Double[]{};
  }

  @Doc(desc = "Displays the value of epsilon")
  public static void epsilon() {

  }

  private static boolean arePointsClose(Point2D.Double a, Point2D.Double b) {
    return false;
  }
}
