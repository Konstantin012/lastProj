package com.telesens.rozetka;

import com.telesens.framework.page.BasePage;
import com.telesens.framework.test.BaseTest;

import com.telesens.rozetka.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static com.telesens.rozetka.page.HomePage.startFromHome;


@Listeners(TestListener.class)
public class RozetkaTests extends BaseTest {
    private String loginRoz;
    private String passworRoz;
    private static final String DEFAULT_PATH = "src/main/resources/rozetka.properties";
    private String baseUrl;
    private Properties propertRoz;


    @BeforeClass(alwaysRun = false)
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

    @Test(enabled = true,dataProvider = "rozetkaTestAuthIncorPasw")
    public void wrongAuthentication(String login,String passwor,String errorMes)  {
        BasePage basePage = startFromHome(driver, baseUrl)
                .clickOnAccount()
                .enterYourData(login, passwor)
                .checkLoginPasswordFilled()
                .goToAccountError()
                .checkErrorPassMessage(errorMes);
    }

    @Test(enabled = false)
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

}
