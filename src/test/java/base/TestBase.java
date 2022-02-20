package base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.*;

public class TestBase extends Listeners {

    public Logger logger;

    @BeforeMethod
    public void setup() {
        logger = Logger.getLogger("Selenium");//added Logger
        PropertyConfigurator.configure("log4j.properties"); //added logger

        BrowserUtils.openBrowser();
        logger.info("Browser opened");
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.closeBrowser();
        logger.info("Browser Closed\n");
        logger.info("---------------------------------------------\n");
    }

}