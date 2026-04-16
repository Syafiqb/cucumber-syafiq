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


public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    // element text Products
    @FindBy(xpath = "//span[@class='title']")
    private WebElement pageHeader;

    // element logout
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> addToCartBtn;

    public void clickMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.visibilityOf(logoutLink));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void clickAddToCart(String productName) {
        String buttonXpath = String.format("//button[@name='add-to-cart-%s']", productName.toLowerCase().replace(" ", "-"));
        System.out.println("Clicking add to cart button for product: " + buttonXpath);
    }

    public List<WebElement> getAddToCartButtons() {
        return addToCartBtn;
    }

    public String getPageHeader() {
        return wait.until(ExpectedConditions.visibilityOf(pageHeader)).getText();
    }

    public boolean isMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn"))).isDisplayed();
    }

}
