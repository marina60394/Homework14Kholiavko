package com.aqacources.project.tests;

import com.aqacources.project.base.BaseTest;
import com.aqacources.project.pages.HomePage;
import com.aqacources.project.pages.LoginPage;
import com.aqacources.project.pages.MyAccountPage;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Marina on 19.02.2019.
 */
public class CheckUesInformationTest extends BaseTest {

    /**
     * Open site and authorize user, check customer name and sign out
     */
    @Test
    public void testOpenSiteAndClickTheLinkTest() {

        String loginPageTitle;
        String myAccountPageTitle;

        // Initialize Home page
        HomePage homePage = openSite();
        log("Opened site");

        // Click on Sign In link
        LoginPage loginPage = homePage.clickSignInLink();
        log("Clicked Sign In");

        // get Title for Login Page
        loginPageTitle = loginPage.getTitle();
        log("Got Title for page after Log In - Login Page");

        // Sign In with login and password
        MyAccountPage myAccountPage = loginPage.signIn();
        log("Signed In on the site");

        // check customer name
        myAccountPage.checkCustomerName();
        log("Checked Customer Name");

        // log out
        myAccountPage.logOut();
        log("Logged Out");

        // get Title after Sign Out
        myAccountPageTitle = loginPage.getTitle();
        log("Got Title from page after log out  Login Page");

        // check that page after click Sign In and page when user is Log Out is the same
        Assert.assertEquals("Check title from loginPage and MyAccountPage after logout:", loginPageTitle, myAccountPageTitle);
        log("Checked that page after clickSignIn Link and after log out is the same");

        closeSite();
        log("Closed site");

    }
}
