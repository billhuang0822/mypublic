package com.tsb.logging;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableLog {
    String[] maskRequestFieldFormats() default {};
    String[] maskResponseFieldFormats() default {};
    String[] maskFormats() default {};
}