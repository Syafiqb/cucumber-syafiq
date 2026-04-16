package com.syafiq.cucumber.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.syafiq.cucumber.utils.Constants;

public class CartPage {

    private final WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> cartItemNames;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> cartItemPrices;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;

    @FindBy(css = "[data-test='continue-shopping']")
    private WebElement continueShoppingButton;

    public boolean isCartPageDisplayed() {
        wait.until(ExpectedConditions.urlContains("cart"));
        return wait.until(ExpectedConditions.textToBe(
                By.xpath("//span[@class='title']"), "Your Cart"));
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.size();
    }

    public String getFirstCartItemName() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItemNames));
        return cartItemNames.get(0).getText();
    }

    public String getFirstCartItemPrice() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItemPrices));
        return cartItemPrices.get(0).getText();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
