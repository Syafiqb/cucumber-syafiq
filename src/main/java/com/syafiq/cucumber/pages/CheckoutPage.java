package com.syafiq.cucumber.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.syafiq.cucumber.utils.Constants;

public class CheckoutPage {

    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    // --- Step One: Fill Info ---

    @FindBy(css = "[data-test='firstName']")
    private WebElement firstNameField;

    @FindBy(css = "[data-test='lastName']")
    private WebElement lastNameField;

    @FindBy(css = "[data-test='postalCode']")
    private WebElement postalCodeField;

    @FindBy(css = "[data-test='continue']")
    private WebElement continueButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    // --- Step Two: Overview ---

    @FindBy(xpath = "//span[@class='title']")
    private WebElement pageTitle;

    @FindBy(css = ".summary_total_label")
    private WebElement totalLabel;

    @FindBy(css = "[data-test='finish']")
    private WebElement finishButton;

    // --- Step Three: Confirmation ---

    @FindBy(css = ".complete-header")
    private WebElement confirmationHeader;

    @FindBy(css = ".complete-text")
    private WebElement confirmationText;

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public boolean isOverviewPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                .getText().equalsIgnoreCase("Checkout: Overview");
    }

    public String getOrderTotal() {
        return wait.until(ExpectedConditions.visibilityOf(totalLabel)).getText();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getConfirmationHeader() {
        return wait.until(ExpectedConditions.visibilityOf(confirmationHeader)).getText();
    }

    public String getConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOf(confirmationText)).getText();
    }
}
