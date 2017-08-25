package io.github.adamelliotfields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// "@interface" indicates that this will be an annotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Doc {
  /** Description of class or method */
  String desc() default "";

  /** Description of parameters, if annotated element is a method and has parameters */
  String[] params() default {};

  /** Description of return value, if annotated element is a method and isn't void */
  String returnVal() default "";
}
