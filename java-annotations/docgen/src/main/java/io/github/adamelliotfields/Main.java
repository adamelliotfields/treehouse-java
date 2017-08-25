package io.github.adamelliotfields;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    // Get a class object
    Class<?> klass = MathUtils.class;

    // Get all declared methods
    // Use the Method class from the reflect package
    // getDeclaredMethods returns only declared methods
    Method[] methods = klass.getDeclaredMethods();

    // Iterate over methods
    Arrays.stream(methods)
        .forEach(method -> {
          // Display method name
          System.out.println("Name: " + method.getName());
          // Display parameter count
          System.out.println("Params: " + method.getParameterCount());
          // Display return type
          System.out.println("Return Type: " + method.getReturnType());
          // Display modifiers
          System.out.println("Modifiers: " + Modifier.toString(method.getModifiers()));
          System.out.println();
        });

    // Process the MathUtils class's annotations
    DocProcessor.process(MathUtils.class);
  }
}
