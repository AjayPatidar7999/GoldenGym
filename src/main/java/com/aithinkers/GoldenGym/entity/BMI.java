package com.aithinkers.GoldenGym.entity;



import jakarta.persistence.*;

@Entity
public class BMI {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double height; // in meters
    private double weight; // in kg
    private double bmi;
    private String category;

    public BMI() {}

    public BMI(double height, double weight, double bmi, String category) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

