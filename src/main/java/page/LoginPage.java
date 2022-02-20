package page;

import base.BrowserUtils;
import org.openqa.selenium.By;


public class LoginPage {

    private static final By divLogin = By.id("divLogin");
    private static final By txtUsername = By.id("txtUsername");
    private static final By txtPassword = By.id("txtPassword");
    private static final By btnLogin = By.id("btnLogin");
    private static final By invalidLoginMessage = By.xpath("//*[@id='spanMessage' and contains(text(),'Invalid credentials')]");
    private static final By loginCredentials = By.xpath("//*[contains(text(),'Username : Admin | Password : admin123' )]");

    public void VerifyLoginPageIsOpen() {
        BrowserUtils.verifyElementDisplayed(divLogin);
    }

    public void Login(String username, String password) {
        BrowserUtils.clearInputTextArea(txtUsername);
        BrowserUtils.waitAndSendKeys(txtUsername, username);
        BrowserUtils.clearInputTextArea((txtPassword));
        BrowserUtils.waitAndSendKeys(txtPassword, password);
        BrowserUtils.waitAndClick(btnLogin);
    }

    public void VerifyInvalidLoginMessage() {
        BrowserUtils.verifyElementDisplayed(invalidLoginMessage);
    }

    public String[] CaptureLoginCredentials() {
        String info = BrowserUtils.getText(loginCredentials);

        String[] infoArray = info.split("\\|");
        String username = infoArray[0].split(":")[1].trim();
        String password = infoArray[1].split(":")[1].trim().substring(0, infoArray[1].split(":")[1].trim().length() - 1).replaceAll(" ","");

        String[] loginCredentials = new String[2];
        loginCredentials[0] = username;
        loginCredentials[1] = password;
        return loginCredentials;
    }


}



