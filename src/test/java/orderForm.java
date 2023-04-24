import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class orderForm {
    private WebDriver driver;

    public orderForm(WebDriver driver) {
        this.driver = driver;
    }
    private By nameField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");
    private By surnameField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");
    private By addressField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");
    private By undergroundStation = By.className("select-search__input");
    private By phoneNumberField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");
    private By nextButton = By.xpath(".//button[text()='Далее']");

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setUndergroundStationField(String station) {
        driver.findElement(undergroundStation).sendKeys(station);
    }

    public void setPhoneNumberField(String customNumber) {
        driver.findElement(phoneNumberField).sendKeys(customNumber);
    }
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    public void waitForLoadHeader() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(nextButton));
    }
    public void login(String name, String surname, String address, String station, String customNumber) {
        setNameField(name);
        setSurnameField(surname);
        setAddressField(address);
        setUndergroundStationField(station);
        setPhoneNumberField(customNumber);
        clickNextButton();
    }
}
