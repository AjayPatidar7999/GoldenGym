package com.aithinkers.GoldenGym.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aithinkers.GoldenGym.entity.BMI;
import com.aithinkers.GoldenGym.repo.BMIRepository;

@Service
public class BMICalculatorServiceImpl implements BMICalculatorService {

    @Autowired
    private BMIRepository bmiRepository;

    @Override
    public BMI calculateBMI(double height, double weight) {
        double bmiValue = weight / (height * height);
        String category = categorizeBMI(bmiValue);

        BMI bmi = new BMI(height, weight, bmiValue, category);
        return bmiRepository.save(bmi);
    }

    private String categorizeBMI(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}
