package com.example.rashil.glamstr;

/**
 * Created by rashil on 16/7/18.
 */

public class Profile {
    Profile(String name, String eligibility, String gender, String city, String education, String skin, String eyes, String martial, String languages, String training, String passport, String cwh, String biography, String age, String height) {
        this.name = name;
        this.eligibility = eligibility;
        this.gender = gender;
        this.city = city;
        this.education = education;
        this.skin = skin;
        this.eyes = eyes;
        this.martial = martial;
        this.languages = languages;
        this.training = training;
        this.passport = passport;
        this.cwh = cwh;
        this.biography = biography;
        this.age = age;
        this.height = height;
    }

    private String name, eligibility, gender, city, education, skin, eyes, martial, languages, training, passport, cwh, biography;

    public String getName() {
        return name;
    }

    public String getEligibility() {
        return eligibility;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getEducation() {
        return education;
    }

    public String getSkin() {
        return skin;
    }

    public String getEyes() {
        return eyes;
    }

    public String getMartial() {
        return martial;
    }

    public String getLanguages() {
        return languages;
    }

    public String getTraining() {
        return training;
    }

    public String getPassport() {
        return passport;
    }

    public String getCwh() {
        return cwh;
    }

    public String getBiography() {
        return biography;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    private String age;
    private String height;


}
