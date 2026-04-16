package com.syafiq.cucumber;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.syafiq.cucumber.drivers.DriverSingleton;
import com.syafiq.cucumber.pages.CartPage;
import com.syafiq.cucumber.pages.CheckoutPage;
import com.syafiq.cucumber.pages.SortPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortAndCheckoutTest {

    private static final Logger logger = LoggerFactory.getLogger(SortAndCheckoutTest.class);

    private WebDriver driver;
    private SortPage sortPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Before
    public void initPageObjects() {
        driver = DriverSingleton.getDriver();
        sortPage = new SortPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        logger.info("Page objects initialized");
    }

    // ---- Sort steps ----

    @When("the user sorts products by price low to high")
    public void user_sorts_by_price_low_to_high() {
        logger.info("Sorting products by price: low to high");
        sortPage.sortByPriceLowToHigh();
    }

    @Then("the products should be sorted from cheapest to most expensive")
    public void products_sorted_cheapest_to_most_expensive() {
        logger.info("Verifying products are sorted by price ascending");
        boolean sorted = sortPage.isProductsSortedByPriceAscending();
        Assert.assertTrue(sorted, "Products are not sorted by price low to high");
        logger.info("Products are correctly sorted by price ascending. Cheapest: {} at {}",
                sortPage.getCheapestProductName(), sortPage.getCheapestProductPrice());
    }

    // ---- Cart steps ----

    @And("the user adds the cheapest product to cart")
    public void user_adds_cheapest_product_to_cart() {
        String name = sortPage.getCheapestProductName();
        String price = sortPage.getCheapestProductPrice();
        logger.info("Adding cheapest product to cart: {} at {}", name, price);
        sortPage.addCheapestItemToCart();
        logger.info("Product added to cart successfully");
    }

    @And("the user navigates to the cart")
    public void user_navigates_to_cart() {
        logger.info("Clicking cart icon to navigate to cart page");
        sortPage.goToCart();
    }

    @Then("the cart page should be displayed")
    public void cart_page_should_be_displayed() {
        logger.info("Verifying cart page is displayed");
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page is not displayed");
        logger.info("Cart page displayed. Item count: {}", cartPage.getCartItemCount());
    }

    // ---- Checkout positive steps ----

    @When("the user clicks the checkout button")
    public void user_clicks_checkout_button() {
        logger.info("Clicking checkout button");
        cartPage.clickCheckout();
    }

    @And("the user fills checkout info with first name {string} last name {string} postal code {string}")
    public void user_fills_checkout_info(String firstName, String lastName, String postalCode) {
        logger.info("Filling checkout info — firstName: '{}', lastName: '{}', postalCode: '{}'",
                firstName, lastName, postalCode);
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
    }

    @And("the user clicks the continue button")
    public void user_clicks_continue_button() {
        logger.info("Clicking continue button");
        checkoutPage.clickContinue();
    }

    @Then("the order overview page should be displayed")
    public void order_overview_page_should_be_displayed() {
        logger.info("Verifying order overview page is displayed");
        Assert.assertTrue(checkoutPage.isOverviewPageDisplayed(),
                "Overview page is not displayed");
        logger.info("Order overview displayed. Total: {}", checkoutPage.getOrderTotal());
    }

    @When("the user clicks the finish button")
    public void user_clicks_finish_button() {
        logger.info("Clicking finish button to complete the order");
        checkoutPage.clickFinish();
    }

    @Then("the order confirmation should display {string}")
    public void order_confirmation_should_display(String expectedHeader) {
        String actualHeader = checkoutPage.getConfirmationHeader();
        logger.info("Verifying order confirmation — expected: '{}', actual: '{}'",
                expectedHeader, actualHeader);
        Assert.assertEquals(actualHeader, expectedHeader,
                "Order confirmation header mismatch");
        logger.info("Order confirmed: {} — {}", actualHeader, checkoutPage.getConfirmationText());
    }

    // ---- Checkout negative steps ----

    @Then("the checkout error should display {string}")
    public void checkout_error_should_display(String expectedError) {
        logger.info("Verifying checkout error message — expected: '{}'", expectedError);
        Assert.assertTrue(checkoutPage.isErrorMessageDisplayed(),
                "Error message is not displayed on checkout page");
        String actualError = checkoutPage.getErrorMessage();
        logger.info("Error message displayed: '{}'", actualError);
        Assert.assertEquals(actualError, expectedError,
                "Checkout error message mismatch");
    }
}
