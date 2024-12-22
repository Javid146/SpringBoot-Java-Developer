package com.cydeo;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Human;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/human")
public class HumanController {

    @GetMapping("/form")
    public String form(Model model) {

        model.addAttribute("human", new Human());
        return "human-form";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute("human") Human human) {

        System.out.println(human.toString());
        return "/human/profile-submission";
    }

    @GetMapping("/humans")
    public String profile(Model model) {

        model.addAttribute("humans", DataGenerator.createHumans());
        return "/human/humans";
    }
}
