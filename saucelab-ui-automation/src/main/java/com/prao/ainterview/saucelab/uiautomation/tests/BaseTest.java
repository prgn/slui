

import com.prao.ainterview.saucelab.uiautomation.util.*;
import com.slickqa.webdriver.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
public class BaseTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeClass(alwaysRun = true)
    public void setupClass() throws Exception {
        SetUpUtil.setupEnvironment();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpBaseUITest(ITestContext ctx) throws Exception {
        WebDriverWrapper browser;
        try {
            browser = UIThreadManager.getBrowser();
            if (browser == null) {
                throw new Exception ("Failed to create a new Browser instance!");
            }
            browser.goTo("http://" + System.getProperty(SetUpUtil.sauceHostJavaOpt));
        }
        catch (Exception e) {
            logger.debug("Failed during setup method", e);
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void cleanupBaseUITest(ITestResult testResult) {
        WebDriverWrapper browser = UIThreadManager.getDriverIfItExists();
        if (browser != null) {
            try {
                browser.getDriver().quit();
            } catch (Exception e) {
                logger.debug("Failed during cleanup method", e);
                throw e;
            }
            UIThreadManager.setBrowser(null);
        }
    }
}