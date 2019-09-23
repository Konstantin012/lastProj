package com.telesens.rozetka.page;

import com.telesens.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDropListPage extends BasePage {
    public AccountDropListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='header-dropdown__list-item']/a[text()=' Выход ']")
    private WebElement cancel;

    public HomePage exitFromAccount() {
        cancel.click();
        waitingJava(500);
        return new HomePage(driver);
    }
}
