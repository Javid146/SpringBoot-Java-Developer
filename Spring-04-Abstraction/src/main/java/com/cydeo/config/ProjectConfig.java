package com.cydeo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.cydeo") //all pacakges within cydeo. or as below, 1 by 1
//@ComponentScan(basePackages = {"com.cydeo.proxy", "com.cydeo.repository", "com.cydeo.service"})
public class ProjectConfig {
}
