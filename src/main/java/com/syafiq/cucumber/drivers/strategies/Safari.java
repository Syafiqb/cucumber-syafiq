package com.syafiq.cucumber.drivers.strategies;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class Safari implements DriverStrategy {

    @Override
    public WebDriver setStrategy(String browser) {
        SafariOptions options = new SafariOptions();
        if (browser.contains("headless")) {
           
        }
        return new SafariDriver(options);
    }

    @Override
    public WebDriver setStrategy() {
        return setStrategy("safari");
    }
}
