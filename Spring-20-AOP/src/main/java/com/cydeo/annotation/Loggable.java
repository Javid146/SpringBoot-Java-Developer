package com.cydeo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//custom annotation by me, this is not class, it is called annotation
@Target({ElementType.METHOD}) //method level annotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {



}
