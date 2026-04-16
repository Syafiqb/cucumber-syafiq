package com.syafiq.cucumber;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.syafiq.cucumber.drivers.DriverSingleton;
import com.syafiq.cucumber.pages.InventoryPage;
import com.syafiq.cucumber.pages.LoginPage;
import com.syafiq.cucumber.utils.Constants;
import org.slf4j.Logger;
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest {

    Logger logger = org.slf4j.LoggerFactory.getLogger(LoginTest.class);

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Before
    public void initPageObjects() {
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Given("the user is on the login page")
    public void user_is_on_login_page() {
        logger.info("Navigating to login page (clearing browser state)");
        driver.get("about:blank");
        driver.get(Constants.URL);
        // Clear sessionStorage/localStorage to remove any leftover React error state,
        // then refresh to bypass bfcache and load a clean login page from server
        ((JavascriptExecutor) driver).executeScript(
            "window.sessionStorage.clear(); window.localStorage.clear();");
        driver.navigate().refresh();
        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT))
            .until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
        com.syafiq.cucumber.utils.Utils.delay(1);
        logger.info("Login page is ready");
    }   

    @When("the user enters valid username and password")
    public void user_enters_username_and_password() {
        logger.info("User enters username and password");
        loginPage.setCredentials("standard_user", "secret_sauce");
    }

    @And("clicks the login button")
    public void clicks_on_login_button() {
        logger.info("User clicks on login button");
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the inventory page")
    public void user_should_be_redirected_to_inventory_page() {
        logger.info("Waiting for redirect to inventory page");
        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT))
            .until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
            "Expected URL to contain 'inventory' but was: " + driver.getCurrentUrl());
        logger.info("Redirected to: {}", driver.getCurrentUrl());
    }

    @And("the page header should display Products")
    public void page_header_should_display_products() {
        logger.info("Page header displays Products");
        Assert.assertEquals(inventoryPage.getPageHeader(), "Products", "Page header should display Products");
    }

    @When("the user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        logger.info("User enters invalid username and password");
    }

    @Then("the user should see an error message")
    public void user_should_see_error_message() {
        logger.info("User sees error message");
    }


}
