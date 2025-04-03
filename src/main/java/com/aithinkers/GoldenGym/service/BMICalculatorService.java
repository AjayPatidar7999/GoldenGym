package com.aithinkers.GoldenGym.service;

import com.aithinkers.GoldenGym.entity.BMI;

public interface BMICalculatorService {
    BMI calculateBMI(double height, double weight);
}

