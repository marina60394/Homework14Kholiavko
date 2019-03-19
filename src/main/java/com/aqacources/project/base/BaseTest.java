package com.aqacources.project.base;

import com.aqacources.project.pages.HomePage;
import com.aqacources.project.utils.YamlParser;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

/**
 * Created by Marina on 24.02.2019.
 */
public class BaseTest {

    // Instance of WebDriver
    private WebDriver driver;

    // Logger
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    // Rule
    @Rule
    public RunTestRule runTestRule = new RunTestRule(this);

    // Product loader element
    private static final String LOADING_PRODUCT = "//ul[@class='product_list grid row']/p";

    /**
     * Constructor
     */
    public BaseTest() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);

        setWebDriver(driver);
        driver.manage().window().maximize();
    }

    /**
     * Return instance of Driver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Open site and return instance of HomePage
     *
     * @return HomePage
     */
    public HomePage openSite() {
        open(YamlParser.getYamlData().getUrl());
        return new HomePage(this);
    }

    /**
     * Close site with driver.quit()
     */
    public void closeSite() {
        driver.quit();
    }

    /**
     * Wait till element is visible
     *
     * @param element
     */
    public void waitTillElementIsVisible(WebElement element) {
        $(element).is(Condition.visible);
    }

    /**
     * Wait till Loader isn't present
     */
    public void waitTillLoaderIsNotPresent() {
        $$(By.xpath(LOADING_PRODUCT)).exclude(Condition.visible);
    }

    /**
     * Wait till text to be present in element
     */
    public void waitTillTextToBePresentInElementValue(String locator, int expectedValue) {
        $(byXpath(locator)).shouldHave(attribute("value", (String.valueOf(expectedValue))));
    }

    /**
     * Wait till Element is Present
     *
     * @param locator
     */
    public void waitTillElementIsPresent(String locator) {
        $(byXpath(locator)).shouldBe(exist);
    }

    /**
     * Wait till Element is Invisible
     *
     * @param element
     */
    public void waitTillElementIsInvisible(WebElement element) {
        $(element).shouldBe(Condition.not(Condition.visible));
    }

    /**
     * Write down info message
     *
     * @param message
     */
    public void log(String message) {
        logger.info(message);
    }

    /**
     * Write down error message
     *
     * @param error
     */
    public void error(String error) {
        logger.error(error);
    }

    /**
     * Get current date and time
     *
     * @return current date and time
     */
    public String getDateTime() {
        return new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
    }
}
