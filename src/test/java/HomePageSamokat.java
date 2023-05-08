import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageSamokat {
    private final WebDriver driver;
    private final By acceptCookieButton = By.id("rcc-confirm-button");
    private final By orderButtonUpper = By.xpath(".//button[@class = 'Button_Button__ra12g' and text()='Заказать']");
    private final By orderButtonLower = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    public HomePageSamokat(WebDriver driver){
        this.driver = driver;
    }
    public void waitForLoadHomePage(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(acceptCookieButton));
    }
    public void clickAcceptCookiesButton(){
        driver.findElement(acceptCookieButton).click();
    }
    public void clickOrderButtonUpper(){
        driver.findElement(orderButtonUpper).click();
    }
    public void getIntoViewOrderButtonLower() {
        WebElement element = driver.findElement(orderButtonLower);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void clickOrderButtonLower(){
        driver.findElement(orderButtonLower).click();
    }

}
