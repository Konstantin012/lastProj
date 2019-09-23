package com.telesens.rozetka.page;

import com.telesens.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TShirtPage extends BasePage {
    public TShirtPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[id='Orsay'] + label")
    private WebElement itemOrsay;

    @FindBy(css = "span.goods-tile__title > strong")
    private WebElement currentLabel;

    @FindBy(css = "span.catalog-more__text")
    private WebElement showMoreGoodsOnPage;

    @FindBy(css = "div.pagination > ul")
    private WebElement counterLine;
    private String counterLineLocator ="ul.pagination__list li";


        private String currentPage
                = "div.pagination a[class=\"pagination__link pagination__link_state_active\"]:nth-of-type(1)";

//    div.pagination ul[class="pagination__list"]  a:nth-of-type(1)[class="pagination__link pagination__link_state_active"]
    public TShirtPage getAllLables(){
        waitingJava(10000);
        new HomePage(driver).closeSupportWindow();  //НЕ РАБОТАЕТ!!
        waitingJava(10000);
        itemOrsay.click();
        waitingJava(10000);
        List<WebElement> elements = currentLabel.findElements(By.cssSelector(counterLineLocator));
        WebElement a = elements.get(elements.size()-1);
        int aa = Integer.parseInt(a.getText());
        for (int i = 1; i <= aa; i++) {
            showMoreGoodsOnPage.click();
            WebElement curPage = driver.findElement
                    (By.cssSelector("div.pagination a[class=\"pagination__link" +
                            " pagination__link_state_active\"]:nth-of-type("+i+")"));
            curPage.getText();

            waitingExpectedElementContaince(curPage,20,String.valueOf(i));
        }

        return this;
    }


}
