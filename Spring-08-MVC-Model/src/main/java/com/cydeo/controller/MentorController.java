package com.cydeo.controller;

import com.cydeo.enums.Gender;
import com.cydeo.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MentorController {

    @RequestMapping("/mentors")
    public String mentors(Model model){

        List<Mentor> mentorList = new ArrayList<>();
        mentorList.add(new Mentor("Javid","Mammadli",33, Gender.MALE));
        mentorList.add(new Mentor("Juju","Sebastiani",30, Gender.FEMALE));
        mentorList.add(new Mentor("Sevda","Alizade",59, Gender.FEMALE));

        model.addAttribute("mentorList", mentorList);

        return "/mentor/mentor-list";
    }
}
