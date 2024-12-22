package com.cydeo.controller;

import com.cydeo.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @GetMapping("/register")
    public String showForm(Model model) {

        model.addAttribute("mentor", new Mentor());

        List<String> batchList = Arrays.asList("JD1", "JD2", "JD3", "JD4", "JD5");
        model.addAttribute("batchList", batchList);

        return "mentor/mentor-register";
    }

    @PostMapping("/confirm")
    //@ModelAttribute("mentor") catches mentor model attrbiute above
    public String showForm2(@ModelAttribute("mentor") Mentor mentor) {

        System.out.println("mentor.toString() = " + mentor.toString());
        return "mentor/mentor-confirmation";
//        this line is below is same as above because in mentor-register we use:
//        <form th:action="@{/mentor/confirm}" method="post" th:object="${mentor}">
//        return "redirect:/mentor/register";
    }
}
