package orderFormTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class orderDetails {
    private final WebDriver driver;
    public orderDetails(WebDriver driver) {
        this.driver = driver;
    }
    private final By dateOfOrder = By.xpath(".//div[@class='Order_MixedDatePicker__3qiay']");
    private final By durationOfRent = By.xpath(".//div[@class='Dropdown-control']");
    private final By colorOfItemIsBlack = By.xpath("//*[@id='black']");
    private final By getColorOfItemIsGrey = By.xpath("//*[@id='grey']");
    private final By commentForCourier = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    private final By confirmForm = By.className("Order_Modal__YZ-d3");
    private final By confirmButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");
    private final By showStatus = By.xpath(".//button[text()='Посмотреть статус']");
    public void setDateOfOrder(String date) {
        driver.findElement(dateOfOrder).click();
        String dateXpath = ".//div[@aria-label='Choose "+date+"']";
        driver.findElement(By.xpath(dateXpath)).click();
}
    public void setDurationOfRent(String duration){
        driver.findElement(durationOfRent).click();
        String durationXpath = ".//div[@class='Dropdown-option' and text()='"+duration+"']";
        driver.findElement(By.xpath(durationXpath)).click();

    }
    public void clickColorOfItem(String color){
        if (color.equals("чёрный жемчуг") || color.equals("черный") || color.equals("чёрный") || color.equals("black")){
            driver.findElement(colorOfItemIsBlack).click();
        }
        else if (color.equals("серая безысходность") || color.equals("серый") || color.equals("grey")){
            driver.findElement(getColorOfItemIsGrey).click();
        }
    }
    public void setCommentForCourier(String comment) {
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    public void waitForLoadOrderButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(orderButton));
    }
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    public void setOrderDetailsInBulk(String date, String duration, String color, String comment){
        setDateOfOrder(date);
        setDurationOfRent(duration);
        clickColorOfItem(color);
        setCommentForCourier(comment);
        WebElement element = driver.findElement(orderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        clickOrderButton();
    }
    public void waitForLoadConfirmButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(confirmForm));
    }
    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }
    public void waitForLoadStatus() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(showStatus));
    }
    public String getStatusButtonText() {
        return driver.findElement(showStatus).getText();
    }
}
