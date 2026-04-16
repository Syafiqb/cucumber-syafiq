package com.syafiq.cucumber;

import com.syafiq.cucumber.drivers.DriverSingleton;
import com.syafiq.cucumber.utils.Constants;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
    features = "src/main/resources/features",
    glue = "com.syafiq.cucumber",
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"}
)

public class TestRunner extends AbstractTestNGCucumberTests {

    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    @BeforeSuite
    public void globalSetUp() {
        logger.info("Suite setup: initializing WebDriver ({})", Constants.CHROME);
        DriverSingleton.getInstance(Constants.CHROME);
        logger.info("WebDriver initialized");
    }

    @AfterSuite
    public void globalTearDown() {
        logger.info("Suite teardown: closing WebDriver");
        DriverSingleton.closeObjectInstance();
        logger.info("WebDriver closed");
    }
}


