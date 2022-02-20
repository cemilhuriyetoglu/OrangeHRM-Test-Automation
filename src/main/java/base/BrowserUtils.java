package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {

    public static void openBrowser() {
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Driver.get().get(ConfigurationReader.get("baseUrl"));
    }

    public static void closeBrowser() {
        Driver.closeDriver();
    }

    /**
     * @param by By class is a mechanism used to locate elements within a document.
     *           eg. By.id("username")
     * @brief Checks if the WebElement given is found multiple times which is
     * incorrect.
     */
    public static void checkWebElementCount(By by) {
        try {
            if (Driver.get().findElements(by).size() > 1) {
                Assert.fail("There are more than one WebElement of " + by.toString() + " . There has to be one.");

            }
        } catch (InvalidSelectorException e) {
            Assert.fail(e.getMessage());
        }
    }


    /**
     * @param xPATH Needs xPath in double quotes.
     * @brief Waits until the WebElement is located in the page and it is clickable,
     * then clicks to the element.
     */
    public static void waitAndClick(String xPATH) {
        waitAndClick(By.xpath(xPATH));
    }

    /**
     * @param by By class is a mechanism used to locate elements within a document.
     *           eg. By.id("username")
     * @brief Waits until the WebElement is located in the page and it is clickable,
     * then clicks to the element.
     */
    public static void waitAndClick(By by) {
        checkWebElementCount(by);
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Driver.get().findElement(by).click();
    }

    /**
     * @param xPATH Needs xPath in double quotes.
     * @brief Clear input text area.
     */
    public static void clearInputTextArea(String xPATH) {
        clearInputTextArea(By.xpath(xPATH));
    }

    /**
     * @param by By class is a mechanism used to locate elements within a document.
     *           eg. By.id("username")
     * @brief Clear input text area.
     */
    public static void clearInputTextArea(By by) {
        Driver.get().findElement(by).clear();
    }

    /**
     * @param xPATH Needs xPath in double quotes.
     * @param input Takes the input. Needs to be written in double quotes.
     * @brief Waits until the WebElement is located and visible then enters the
     * input.
     */
    public static void waitAndSendKeys(String xPATH, String input) {
        waitAndSendKeys(By.xpath(xPATH), input);
    }

    /**
     * @param by By class is a mechanism used to locate elements within a document.
     *           eg. By.id("username") * @param input
     * @brief Waits until the WebElement is located and visible then enters the
     * input.
     */
    public static void waitAndSendKeys(By by, String input) {
        checkWebElementCount(by);
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        By webElem = by;
        // Wait until element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.presenceOfElementLocated(webElem));
        Driver.get().findElement(webElem).sendKeys(input);
    }

    /**
     * Moves the mouse to given element
     *
     * @param element on which to hover
     */
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).perform();
    }

    /**
     * @param by By class is a mechanism used to locate elements within a document.
     *           eg. By.id("username")
     * @brief Gets text of the WebElement then returns it as a String.
     */
    public static String getText(By by) {
        checkWebElementCount(by);
        String text = "";
        // Wait until element is visible
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        text = Driver.get().findElement(by).getText();

        return text;
    }

    /**
     * @param xPATH Needs to be written in double quotes.
     * @return
     * @brief Gets text of a WebElement.
     */
    public static String getText(String xPATH) {
        return getText(By.xpath(xPATH));
    }


    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element
     * @param timeToWaitInSec
     * @return
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element matching the locator to be visible on the page
     *
     * @param locator
     * @param timeout
     * @return
     */
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return
     */
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for element matching the locator to be clickable
     *
     * @param locator
     * @param timeout
     * @return
     */
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Verifies whether the element matching the provided locator is displayed on page
     *
     * @param by
     * @throws AssertionError if the element matching the provided locator is not found or not displayed
     */
    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue(Driver.get().findElement(by).isDisplayed(), "Element not visible: " + by);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);

        }
    }

    /**
     * Verifies whether the element matching the provided locator is NOT displayed on page
     *
     * @param by
     * @throws AssertionError the element matching the provided locator is displayed
     */
    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse(Driver.get().findElement(by).isDisplayed(), "Element should not be visible: " + by);
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
    }


    /**
     * Verifies whether the element is displayed on page
     *
     * @param element
     * @throws AssertionError if the element is not found or not displayed
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed(), "Element not visible: " + element);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);

        }
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.get()).doubleClick(element).build().perform();
    }


}