import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class listOfAnswers {
    private final WebDriver driver;

    public listOfAnswers(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstQuestion = By.xpath(".//div[@class='accordion__item']//div[contains(text(),'Сколько это стоит?')]");

    public void waitForLoadQuestions() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(firstQuestion));
    }

    public void getIntoViewQuestion(String question) {
        String questionXpath = ".//div[@class='accordion__item']//div[contains(text(),'" + question + "')]";
        WebElement element = driver.findElement(By.xpath(questionXpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickToGetAnswer(String question) {
        String questionXpath = ".//div[@class='accordion__item']//div[contains(text(),'" + question + "')]";
        driver.findElement(By.xpath(questionXpath)).click();
    }
    public String getTextAnswer(String answer) {
        String answerXpath = ".//div[@class='accordion__panel']//p[contains(text(),'" + answer + "')]";
        return driver.findElement(By.xpath(answerXpath)).getText();
    }

}