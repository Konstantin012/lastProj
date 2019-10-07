package com.telesens.rozetka.bdd.step;

import io.cucumber.java.en.Given;

public class RozetkaSteps {
    private String baseURL = "https://rozetka.com.ua/";
    private TestHelper testHelper;
    @Given("Я нахожусь на домашней странице")
    public void IAmOnHome(){
//        driver.get(baseURL);
        System.out.println("**Я нахожусь на домашней странице**");

    }
}
