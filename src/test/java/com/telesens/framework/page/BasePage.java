package com.telesens.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void inputTextField(WebElement textField, String value) {
        Actions actions = new Actions(driver);
        textField.click();
        textField.clear();
        textField.sendKeys(value);
        actions.sendKeys(Keys.TAB).build().perform();
    }

    protected void waitingUntilPresence(By locator, int time){
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.presenceOfElementLocated(
                        locator
                ));
    }

    protected void waitingExpectedElement(WebElement locator, int time){
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.visibilityOf(
                        locator
                ));
    }

    protected void waitingExpectedElementContaince(WebElement locator, int time, String value){
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.attributeContains(
                        locator,"text",value
                ));
    }

    protected void checkTextFromLocator(WebElement element, String expectedText){
        String actualText = element.getText();
        Assert.assertEquals(actualText,expectedText);
    }

    protected void getAtributeAdnCheck(WebElement element,String attribute, String expectedValue){
        String actualValue = element.getAttribute(attribute);
        Assert.assertEquals(actualValue,expectedValue);
    }

    protected void waitingJava(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean tryFindElement(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
