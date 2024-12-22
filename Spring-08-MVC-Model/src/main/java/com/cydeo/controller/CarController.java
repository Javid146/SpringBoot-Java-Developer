package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/car")
public class CarController {

    /*URL = http://localhost:8080/car/info?make=Jaguar&year=2024
     * car-info.html = <h1 th:text="${make} + ' is my car and year is: ' + ${year}"></h1>*/
    @RequestMapping("/info")
    public String info(Model model, @RequestParam String make, @RequestParam int year) {

        model.addAttribute("make", make);
        model.addAttribute("year", year);
        return "car/car-info";
    }

    /*URL = http://localhost:8080/car/info2
    We do not give any query param in URL but still app does not give error, because we use required = false.*/
    @RequestMapping("info2")
    public String info(Model model, @RequestParam(value = "make", required = false) String make) {

        model.addAttribute("make", make);
        return "car/car-info";
    }

    /*URL = http://localhost:8080/car/info3/Volvo/2025
    car-info.html = <h1 th:text="${make} + ' is my car and year is: ' + ${year}"></h1>
    we get parameter after "/" */
    @RequestMapping("info3/{make}/{year}")
    public String info2(Model model, @PathVariable String make, @PathVariable Integer year){

        model.addAttribute("make", make);
        model.addAttribute("year", year);
        return "car/car-info";
    }
}
