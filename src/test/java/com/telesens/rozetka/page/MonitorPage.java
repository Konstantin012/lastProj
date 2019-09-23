package com.telesens.rozetka.page;

import com.telesens.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class MonitorPage extends BasePage {

    Actions actions = new Actions(driver);

    @FindBy(css = "div[name='goods_list_container']  div.g-price > div.g-price-uah")
    private List<WebElement> prices;

    @FindBy(css="#sort_view > a")
    private WebElement sortWindow;

    @FindBy(xpath="//*[@id='sort_view']/div/ul/li/a[contains(text(), 'от дешевых')]")
    private WebElement fromCheap;

    @FindBy(css = "input[id=\"price[min]\"]")  //С ДРУГИМ ОДНИМ СЛЄШЕМ НЕ РАБОТАЕТ!
    private WebElement minPrice;

    @FindBy(css = "input[id=\"price[max]\"]")
    private WebElement maxPrice;

    @FindBy(css = "div#sort_price button")
    private WebElement okAfterEnterPrice;



    private String WaitingSortedAfterSelectFromList = "//div[contains(@class, 'sort-popup') and contains(@style, 'visibility: hidden')]";
    private String WaitingSortedAfterEnterPrice = "//form[contains(@class, 'filter-parametrs pos-fix')" +
            "and contains(@id, 'filter_parameters_form')]";



    public MonitorPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<Integer> getAllPricesToIneger(){
        return prices.stream()
                .map(WebElement::getText)
                .map(s -> s.replaceAll("[^\\d]", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }




    public MonitorPage sortFromCheapToExpens(){
        sortWindow.click();
        fromCheap.click();
        waitingUntilPresence(By.xpath(WaitingSortedAfterSelectFromList), 10);
        return this;
    }

    public MonitorPage enterPrice(){
        minPrice.click();
        actions.sendKeys("30000").build().perform();
        actions.sendKeys(Keys.TAB).build().perform();
        actions.sendKeys("40000").build().perform();
        return this;
    }

    //РАБОТАЮТ НЕКОРЕКТНО!
    public MonitorPage enterMinPrice(String price){
        inputTextField(minPrice, price);
        return this;
    }
    public MonitorPage enterMaxPrice(String price){
        inputTextField(minPrice, price);
        return this;
    }

    public MonitorPage pressOkAfrerEnterPrices(){
        okAfterEnterPrice.click();
        waitingUntilPresence(By.xpath(WaitingSortedAfterEnterPrice),10);
        return this;
    }

}
