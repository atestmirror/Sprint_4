package orderFormTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class orderForm {
    private final WebDriver driver;

    public orderForm(WebDriver driver) {
        this.driver = driver;
    }
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By undergroundStation = By.xpath(".//div[@class='select-search__value']");
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

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
        driver.findElement(undergroundStation).click();
        String stationXpath = ".//div[@class='select-search__select']//*[text()='"+station+"']";
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stationXpath)));
        driver.findElement(By.xpath(stationXpath)).click();

    }
    public void setPhoneNumberField(String customNumber) {
        driver.findElement(phoneNumberField).sendKeys(customNumber);
    }
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    public void waitForLoadHeader() {
        new WebDriverWait(driver, 5)
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
