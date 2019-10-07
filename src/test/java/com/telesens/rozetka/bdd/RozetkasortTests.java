package com.telesens.rozetka.bdd;


import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


@CucumberOptions(
        features = {"src/main/resources/features"},
        glue = "com.telesens.rozetka.bdd.step.RozetkaSteps",
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"}
)

public class RozetkasortTests {
    private TestNGCucumberRunner runner;

    @BeforeClass
    public void setUpClass() throws IOException {
        runner = new TestNGCucumberRunner(this.getClass());
    }



    @Test(dataProvider = "featureProvider")
    public void testProductSorting(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
//        System.out.println("**Я нахожусь на домашней странице**");
        runner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider(name ="featureProvider")
    public Object[][] featureProvider(){
            return runner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        runner.finish();
    }
}

