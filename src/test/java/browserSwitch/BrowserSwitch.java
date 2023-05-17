package browserSwitch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class BrowserSwitch {
    public WebDriver launchBrowserSwitch(browserList browserName) {
        WebDriver driver = null;
        switch (browserName) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }
}
