package page;

import base.BrowserUtils;
import org.openqa.selenium.By;

public class HomePage {

    private final By welcomeMessageTxt = By.id("welcome");
    private final By btnLogout = By.xpath("//*[text()='Logout']");


    public void VerifyHomePageIsOpen() {
        BrowserUtils.verifyElementDisplayed(welcomeMessageTxt);
    }

    public String GetLoggedUserName() {
        String welcomeMessage = BrowserUtils.getText(welcomeMessageTxt);
        String loggedUserName = welcomeMessage.replace("Welcome", "").trim();
        return loggedUserName;
    }

    public void Logout() {
        BrowserUtils.waitAndClick(welcomeMessageTxt);
        BrowserUtils.waitAndClick(btnLogout);
    }


}
