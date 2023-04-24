import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class listOfAnswers {
    private WebDriver driver;

    public listOfAnswers(WebDriver driver) {
        this.driver = driver;
    }

    private By firstQuestion = By.xpath("//*[@id=\"root\"]/div/div/div[5]/div[2]/div/div[1]");
    private By firstAnswer = By.xpath("//*[@id='accordion__panel-0']/p");
    private By lastQuestion = By.xpath("//*[@id=\"accordion__heading-7\"]");
    private By lastAnswer = By.xpath("//*[@id='accordion__panel-7']/p");
    public void waitForLoadQuestions(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(firstQuestion));
    }
    public void getIntoViewFirstQuestion() {
        WebElement element = driver.findElement(firstQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void getIntoViewLastQuestion() {
        WebElement element = driver.findElement(lastQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void clickToGetFirstAnswer(){
        driver.findElement(firstQuestion).click();
    }
    public void clickToGetLastAnswer(){
        driver.findElement(lastQuestion).click();
    }
    public String getTextFirstAnswer() {
        return driver.findElement(firstAnswer).getText();
    }
    public String getTextLastAnswer() {
        return driver.findElement(lastAnswer).getText();
    }
}
