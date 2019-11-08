package com.telesens.framework.test;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static final Logger LOG =  LogManager.getLogger(BaseTest.class);
    protected String chromeDriverP;
    protected String firefoxDriverP;
    protected Properties propSel;
    private static final String SELENIUM_PATH_ = "src/main/resources/selenium.properties";

    protected EventFiringWebDriver driver;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("firefox") String browser) throws Exception {
        String seleniumPaths = System.getProperty("browProp");
        if(seleniumPaths==null)
            seleniumPaths=SELENIUM_PATH_;

        propSel = new Properties();
        propSel.load(new FileReader(seleniumPaths));
        chromeDriverP = propSel.getProperty("driver.chrome");
        firefoxDriverP = propSel.getProperty("driver.firefox");

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriverP);
            driver = new EventFiringWebDriver(new ChromeDriver());
            driver.register(new WebDriverEventListenerImpl());
        }
        else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefoxDriverP);
            driver = new EventFiringWebDriver(new FirefoxDriver());
            driver.register(new WebDriverEventListenerImpl());
        }


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] params) {
        LOG.info("Start test {} with parameters {}",
                method.getName(), Arrays.toString(params));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        LOG.info("Finish test {}", method.getName());
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Attachment(value = "Page screenshot" ,type = "image/png")
    public byte[] saveScreenshotPNG(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
