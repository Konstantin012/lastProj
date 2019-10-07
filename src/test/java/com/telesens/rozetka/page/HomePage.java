package com.telesens.rozetka.page;

import com.telesens.framework.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
Actions actions = new Actions(driver);
    @FindBy(css = "body > app-root > div > div:nth-child(2) > app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(1) > a")
    private WebElement commonMenu;

    @FindBy(partialLinkText = "Мониторы")
    private WebElement monitors;

    @FindBy(partialLinkText = "Одежда, обувь и украшения")
    private WebElement clothersBootsJew;

    @FindBy(partialLinkText = "Блузки и рубашки")
    private WebElement tShirts;

    @FindBy(css = "body > div > div > div:nth-child(3) > div:nth-child(2) > button > span")
    private WebElement supportWindowClose;
    private String supportWindowCloseLocator = "body > div > div > div:nth-child(3) > div:nth-child(2) > button > span";

    @FindBy(css = "iframe#webWidget")
    public WebElement supportHeadNameWindow;
    public String supportHeadNameWindowLocator = "iframe#webWidget";

    @FindBy(css = "body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-topline > div.header-topline__user.js-rz-auth > div.header-dropdown.header-dropdown_type_attention.email-verification")
    public WebElement emailVerifyWindow;
    public String emailVerifyWindowLocator = "body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-topline > div.header-topline__user.js-rz-auth > div.header-dropdown.header-dropdown_type_attention.email-verification";

    @FindBy(css = "a[class=\"header-topline__user-link link-dashed\"]")
    private WebElement ownAccount;

    @FindBy(linkText = "Закрыть")
    public WebElement closeEmailVerifyWindow;
    public String closeEmailVerifyWindowLocator = "Закрыть";


    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public static HomePage startFromHome(WebDriver driver, String baseUrl){
        driver.get(baseUrl);
        return new HomePage(driver);
    }

    public TShirtPage selectTShirts(){
        closeSupportWindow();
        actions.moveToElement(clothersBootsJew).perform();
        tShirts.click();
        return new TShirtPage(driver);
    }

    public void closeSupportWindow(){
        if(tryFindElement(By.cssSelector(supportHeadNameWindowLocator))){
//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(supportHeadNameWindow));
            driver.switchTo().frame(supportHeadNameWindow);
            if(tryFindElement(By.cssSelector(supportWindowCloseLocator))){
                supportWindowClose.click();
                driver.switchTo().defaultContent();
            }
        }
    }

    public void closeEmailVerifyWindow(){
        waitingJava(1000);
        if(tryFindElement(By.cssSelector(emailVerifyWindowLocator))){
            if(tryFindElement(By.linkText(closeEmailVerifyWindowLocator))){
                closeEmailVerifyWindow.click();
            }
        }
    }


    public MonitorPage selectMonitors(){
        closeSupportWindow();
        actions.moveToElement(commonMenu).perform();
        monitors.click();
        return new MonitorPage(driver);
    }

    @Step("Кликаем по аккаунту")
    public EnterToAccounPage clickOnAccount() {
        ownAccount.click();
        waitingExpectedElement(new EnterToAccounPage(driver).mainWindowEnterAccount, 10);
        return new EnterToAccounPage(driver);
    }

    public AccountDropListPage openAccountDropList(){
        closeEmailVerifyWindow();
        actions.moveToElement(ownAccount).perform();
        return new AccountDropListPage(driver);
    }



    //CHECKING
    public HomePage checAcountName(String name) {
        checkTextFromLocator(ownAccount, name);
        return this;
    }





}
