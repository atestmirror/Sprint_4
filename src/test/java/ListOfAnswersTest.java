import browserSwitch.BrowserSwitch;
import browserSwitch.browserList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import listOfAnswersTest.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ListOfAnswersTest {
    WebDriver driver;
    private final browserList browser;
    private final String question;
    private final String answer;

    public ListOfAnswersTest(browserList browser, String question, String answer) {
        this.browser = browser;
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void preSetup(){
        BrowserSwitch br = new BrowserSwitch();
        driver = br.launchBrowserSwitch(browser);
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        return new Object[][]{
                {browserList.FIREFOX, "Сколько это стоит?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {browserList.FIREFOX, "Хочу сразу несколько самокатов!", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {browserList.FIREFOX, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {browserList.FIREFOX, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {browserList.FIREFOX, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {browserList.FIREFOX, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {browserList.FIREFOX, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {browserList.FIREFOX, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
                {browserList.CHROME, "Сколько это стоит?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {browserList.CHROME, "Хочу сразу несколько самокатов!", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {browserList.CHROME, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {browserList.CHROME, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {browserList.CHROME, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {browserList.CHROME, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {browserList.CHROME, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {browserList.CHROME, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
    @Test
    public void checkListOfAnswers() {
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        objHomePage.waitForLoadHomePage();
        objHomePage.clickAcceptCookiesButton();
        listOfAnswers objListOfAnswers = new listOfAnswers(driver);
        objListOfAnswers.waitForLoadQuestions();
        objListOfAnswers.getIntoViewQuestion(question);
        objListOfAnswers.clickToGetAnswer(question);
        String actual = objListOfAnswers.getTextAnswer(answer);
        assertEquals(answer, actual);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}

