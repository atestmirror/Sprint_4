import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
public class orderDetails {
    private WebDriver driver;

    public orderDetails(WebDriver driver) {
        this.driver = driver;
    }
    private By dateOfOrder = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div[1]/div/input");
    private By durationOfRent = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div[1]/div[1]");
    private By colorOfItemIsBlack = By.xpath("//*[@id='black']");
    private By getColorOfItemIsGrey = By.xpath("//*[@id='grey']");
    private By orderButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");

    public void setDateOfOrder(String date) {
        driver.findElement(dateOfOrder).sendKeys(date);
    }

    public void setDurationOfRent(String duration) {
        ((JavascriptExecutor) driver).executeScript("document.getElementByXpath(\"//*[@id='root']/div/div[2]/div[3]/button[2]\").setAttribute('value', duration)");
    }

    public void clickColorOfItemIsBlack() {
        driver.findElement(colorOfItemIsBlack).click();
    }

    public void clickColorOfItemIsGrey() {
        driver.findElement(getColorOfItemIsGrey).click();
    }
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    public void waitForLoadOrderButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }
    public void setOrderDetails(String date, String duration) {
        setDateOfOrder(date);
        setDurationOfRent(duration);
    }
}
