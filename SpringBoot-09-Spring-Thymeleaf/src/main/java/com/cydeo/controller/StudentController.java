package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {

//    @GetMapping IS EXACTLY SAME AS @RequestMapping
    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("students", DataGenerator.createStudents());
        return "student/register";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "student/welcome";
    }

}
