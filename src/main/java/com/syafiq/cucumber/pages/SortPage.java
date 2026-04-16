package com.syafiq.cucumber.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.syafiq.cucumber.utils.Constants;

public class SortPage {

    private final WebDriverWait wait;

    public SortPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "select[data-test='product-sort-container']")
    private WebElement sortDropdown;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    public void sortByPriceLowToHigh() {
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByValue("lohi");
    }

    public boolean isProductsSortedByPriceAscending() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemPrices));
        List<Double> prices = new ArrayList<>();
        for (WebElement priceEl : itemPrices) {
            String raw = priceEl.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(raw));
        }
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public String getCheapestProductName() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemNames));
        return itemNames.get(0).getText();
    }

    public String getCheapestProductPrice() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemPrices));
        return itemPrices.get(0).getText();
    }

    public void addCheapestItemToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(0))).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }
}
