package com.syafiq.cucumber.drivers.strategies;

import org.openqa.selenium.WebDriver;

public interface DriverStrategy {
    WebDriver setStrategy();
    WebDriver setStrategy(String browser);
}
