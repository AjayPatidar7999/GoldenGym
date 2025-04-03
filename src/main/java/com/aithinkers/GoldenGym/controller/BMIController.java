package com.aithinkers.GoldenGym.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aithinkers.GoldenGym.entity.BMI;
import com.aithinkers.GoldenGym.service.BMICalculatorService;

@Controller
public class BMIController {

    @Autowired
    private BMICalculatorService bmiCalculatorService;

    @GetMapping("/bmi")
    public String showBMIForm() {
        return "BMI/bmiForm";
    }

    @PostMapping("/calculate-bmi")
    public String calculateBMI(@RequestParam double height, @RequestParam double weight, Model model) {
        BMI bmi = bmiCalculatorService.calculateBMI(height, weight);
        model.addAttribute("bmi", bmi);
        return "BMI/bmiResult";
    }
}
