package com.cydeo.component_annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan //gets all the classes with @Component annot. and creates beans from them. Easy version that does not require from us to
//create @Bean manually
public class ConfigCourse {
}
