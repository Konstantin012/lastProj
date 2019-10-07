package com.telesens.selenide;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Selenide {

    @BeforeClass
    public void setUp(){
        baseUrl="http://automationpractice.com/index.php";
        browser = "firefox";
        timeout =10000;
        startMaximized=true;
    }


    @Test
    public void firstTest(){
        open(baseUrl);
        $(byLinkText("Sign in")).click();
        $(byId("email")).setValue("oleg.kh81@gmail.com");
        $(byId("passwd")).setValue("123qwerty");
        $(byId("SubmitLogin")).click();
        $(byText("OLEG AFANASIEV")).shouldBe();
        $(byLinkText("Sign out")).click();

    }

//            driver.get(baseUrl);
//        driver.findElement(By.linkText("Sign in")).click();
//    WebElement element = driver.findElement(By.id("email"));
//        element.click();
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("email")).sendKeys("oleg.kh81@gmail.com");
//        driver.findElement(By.id("passwd")).click();
//        driver.findElement(By.id("passwd")).clear();
//        driver.findElement(By.id("passwd")).sendKeys("123qwerty");
////         driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot your password?'])[1]/following::span[1]")).click();
//        driver.findElement(By.id("SubmitLogin")).click(); // #SubmitLogin
////        Assert.assertEquals("", "Oleg....");
}
