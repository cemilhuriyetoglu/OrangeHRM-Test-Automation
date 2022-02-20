package testCases;

import page.HomePage;
import page.LoginPage;
import base.Helper;
import base.TestBase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({base.Listeners.class})
public class TestOrangeHRM extends TestBase {

    @Test(priority = 1)
    public void InvalidLogin() {
        logger.info("Invalid Login Test is Started");
        String username = Helper.stringGenerator();
        System.out.println(username);
        String password = Helper.stringGenerator();
        System.out.println(password);

        LoginPage LoginPage = new LoginPage();
        LoginPage.VerifyLoginPageIsOpen();
        LoginPage.Login(username, password);
        LoginPage.VerifyInvalidLoginMessage();
        logger.info("Invalid Login Test is Completed");

    }

    @Test(priority = 2)
    public void SuccessfulLogin() {
        logger.info("Successful Login Test is Started");
        LoginPage loginPage = new LoginPage();
        loginPage.VerifyLoginPageIsOpen();
        String[] credentialsArray = loginPage.CaptureLoginCredentials();
        loginPage.Login(credentialsArray[0], credentialsArray[1]);

        HomePage homePage = new HomePage();
        homePage.VerifyHomePageIsOpen();
        String loggedUserName = homePage.GetLoggedUserName();

        logger.info("Logged in User:" + " " + loggedUserName);
        logger.info("Successful Login Test is Completed");
    }

    //I used the wrong name and Checked the Login, for the wrong case to appear in the report
    @Test(priority = 3)
    public void FailCase() {

        logger.info("FailCase Test is Started");
        String username = Helper.stringGenerator();
        System.out.println(username);
        String password = Helper.stringGenerator();
        System.out.println(password);

        LoginPage LoginPage = new LoginPage();
        LoginPage.VerifyLoginPageIsOpen();
        LoginPage.Login(username, password);

        HomePage homePage = new HomePage();
        homePage.VerifyHomePageIsOpen();

        logger.info("FailCase Test is Completed");
    }

}
