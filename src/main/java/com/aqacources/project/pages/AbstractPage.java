package com.aqacources.project.pages;

import com.aqacources.project.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Marina on 19.02.2019.
 */
public class AbstractPage {

    String currentPageURL;

    // Web Elements
    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInLink;

    @FindBy(xpath = "//div[@id='page']")
    protected WebElement divPage;

    @FindBy(xpath = "//div[@class='header_user_info']/a[@class='logout']")
    private WebElement logOut;

    @FindBy(
            xpath =
                    "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']"
    )
    private WebElement menuDresses;

    @FindBy(
            xpath =
                    "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='T-shirts']"
    )
    private WebElement menuTShirts;

    @FindBy(xpath = "//div[@class='product-container']")
    private WebElement productContainer;

    @FindBy(xpath = "//div[@class='breadcrumb clearfix']")
    private WebElement breadcrumb;

    // Instances of BaseTest
    protected BaseTest testClass;

    /**
     * Constructor
     *
     * @param testClass
     */
    public AbstractPage(BaseTest testClass) {
        this.testClass = testClass;
        page(this);
        testClass.waitTillElementIsVisible(divPage);
    }

    /**
     * Click on Sign In link
     *
     * @return new instance of LoginPage
     */
    public LoginPage clickSignInLink() {
        testClass.waitTillElementIsVisible(signInLink);
        $(signInLink).click();
        return new LoginPage(testClass);
    }

    /**
     * get Current URL
     *
     * @param
     * @return
     */
    public String getTitle() {
        return title();
    }

    /**
     * Click Log Out
     */
    public void logOut() {
        testClass.waitTillElementIsVisible(logOut);
        $(logOut).click();
    }

    /**
     * Click to menu Dresses
     *
     * @return new instance of Category page
     */
    public DressesPage clickMenuDresses() {
        testClass.waitTillElementIsVisible(menuDresses);
        $(menuDresses).click();
        return new DressesPage(testClass);
    }

    /**
     * Click to menu T-shirt
     *
     * @return new instance of T-shirt page
     */
    public TShirtPage clickMenuTShirt() {
        testClass.waitTillElementIsVisible(menuTShirts);
        $(menuTShirts).click();
        return new TShirtPage(testClass);
    }

    /**
     * Check breadcrumb
     *
     * @param expectedBreadcrumbs
     */
    public void checkBreadcrumb(String expectedBreadcrumbs) {
        testClass.waitTillElementIsVisible(breadcrumb);
        String breadcrumbs = breadcrumb.getAttribute("innerText");

        String actualBreadCrumbs = breadcrumbs.replace(" > ", "").replace(">", " ");

        //  verify breadcrumb from page with breadcrumb from enum
        Assert.assertEquals(actualBreadCrumbs, expectedBreadcrumbs);
    }
}
