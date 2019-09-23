package com.telesens.rozetka;

import com.telesens.framework.page.BasePage;
import com.telesens.framework.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static com.telesens.rozetka.page.HomePage.startFromHome;

public class RozetkaTests extends BaseTest {
    private String loginRoz;
    private String passworRoz;
    private static final String DEFAULT_PATH = "src/main/resources/rozetka.properties";
    private String baseUrl;
    private Properties propertRoz;


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        String automationPracticePath = System.getProperty("rozetkaProp");
        if (automationPracticePath==null)
            automationPracticePath = DEFAULT_PATH;
        propertRoz = new Properties();
        propertRoz.load(new FileReader(automationPracticePath));
        baseUrl = propertRoz.getProperty("Roz.url");
    }


    @Test(enabled = false)
    public void testSort() {
        List<Integer> pricesSortedActual =
                startFromHome(driver, baseUrl)
                        .selectMonitors()
                        .sortFromCheapToExpens()
                        .getAllPricesToIneger();

        List<Integer> pricesSortedExpected = new ArrayList<>(pricesSortedActual);
        Collections.sort(pricesSortedExpected);

        Assert.assertEquals(pricesSortedActual, pricesSortedExpected);
    }


    @Test(enabled = false)
    public void testSort2() {
        List<Integer> allSortedPrices = startFromHome(driver, baseUrl)
                .selectMonitors()
                .enterPrice()
//                .enterMinPrice("30000") //НЕ РАБОТАЕТ
//                .enterMaxPrice("40000")
                .pressOkAfrerEnterPrices()
                .getAllPricesToIneger();

        Collections.sort(allSortedPrices);

        Assert.assertTrue(allSortedPrices.get(0) > 30000
                & allSortedPrices.get(allSortedPrices.size() - 1) < 40000);
    }

    @Test(enabled = false,dataProvider = "rozetkaTestAuthSuccessProvider")
    public void authenticationSuccess(String login,String passwor, String ownAccountName, String acountSign)  {
        startFromHome(driver, baseUrl)
                .clickOnAccount()
                .enterYourData(login,passwor )
                .checkLoginPasswordFilled()
                .goToAccount()
                .checAcountName(ownAccountName)
                .openAccountDropList()
                .exitFromAccount()
                .checAcountName(acountSign);
    }

    @Test(enabled = false,dataProvider = "rozetkaTestAuthIncorPasw")
    public void wrongAuthentication(String login,String passwor,String errorMes)  {
        BasePage basePage = startFromHome(driver, baseUrl)
                .clickOnAccount()
                .enterYourData(login, passwor)
                .checkLoginPasswordFilled()
                .goToAccountError()
                .checkErrorPassMessage(errorMes);

//        EnterToAccounPage basePage1 =
//                (EnterToAccounPage) basePage;   //НЕ РАБОТАЕТ ПРИВЕДЕНИЕ

    }

    @Test(enabled = true)
    public void filterOrsay()  {
        startFromHome(driver, baseUrl)
        .selectTShirts()
        .getAllLables()
        ;
    }





    //DATA PROVIDERS
    @DataProvider(name = "rozetkaTestAuthSuccessProvider")
    public Object[][] rozetkaTestAuthSuccessProvider() {
        loginRoz = propertRoz.getProperty("login");
        passworRoz = propertRoz.getProperty("password");
        String ownAccountName = propertRoz.getProperty("ownAccountName");
        String acountSign = propertRoz.getProperty("acountSign");
        return new Object[][]{
                {loginRoz, passworRoz, ownAccountName, acountSign}
        };
    }
    @DataProvider(name = "rozetkaTestAuthIncorPasw")
    public Object[][] rozetkaTestAuthIncorPasw() {
        loginRoz = propertRoz.getProperty("login");
        String incorPassworRoz = propertRoz.getProperty("incorectPassword");
        String erroeM = propertRoz.getProperty("erroeMes");
        return new Object[][]{
                {loginRoz, incorPassworRoz, erroeM}
        };
    }






//Метод написан напрямую через селениум, без Pageobject
//    @Test
//    @Ignore
//    public void testSort() {
//        driver.get(baseUrl);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(By.cssSelector(mainMenuCSS)))
//                .perform();
//
//        driver.findElement(By.partialLinkText("Мониторы")).click();
//        List<String> prices =
//                driver.findElements(By.cssSelector(priceCSS)).stream()
//                        .map(WebElement::getText)
//                        .map(s -> s.replaceAll("[^\\d]", ""))
//                        .collect(Collectors.toList());
//        System.out.println(prices);
//
//        driver.findElement(By.cssSelector("#sort_view > a")).click();
//        driver.findElement(By.xpath("//*[@id='sort_view']/div/ul/li/a[contains(text(), 'от дешевых')]")).click();
//
//        //div[contains(@class, 'sort-popup') and contains(@style, 'visibility: hidden')]
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(
//                        By.xpath("//div[contains(@class, 'sort-popup') and contains(@style, 'visibility: hidden')]")
//                ));
//        List<Integer> pricesSortedActual =
//                driver.findElements(By.cssSelector(priceCSS)).stream()
//                        .map(WebElement::getText)
//                        .map(s -> s.replaceAll("[^\\d]", ""))
//                        .map(Integer::parseInt)
//                        .collect(Collectors.toList());
//
//        List<Integer> pricesSortedExpected = new ArrayList<>(pricesSortedActual);
////        pricesSortedExpected.sort(Integer::compareTo); //через компаратор
////        pricesSortedExpected.sort((s1,s2)-> {return Integer.compare(s1,s2);}); //через компаратор
//        Collections.sort(pricesSortedExpected);
//
//
//        System.out.println("pricesSortedActual: " + pricesSortedActual);
//        System.out.println("pricesSortedExpected: " + pricesSortedExpected);
//
//        Assert.assertEquals(pricesSortedActual, pricesSortedExpected);
//    }
}
