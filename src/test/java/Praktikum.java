import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class Praktikum {

    @RunWith(Parameterized.class)
    public static class TestData {
        private final String name;
        private final  String surname;
        private final String address;
        private final String station;
        private final String customNumber;
        private final String date;
        private final String duration;

        public TestData(String name, String surname, String address, String station, String customNumber, String date, String duration) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.station = station;
            this.customNumber = customNumber;
            this.date = date;
            this.duration = duration;
        }

        @Parameterized.Parameters
        public static Object[][] getCredentials() {
            return new Object[][]{
                    {"Анна", "Дубровина", "Кутузовский проспект дом 4", "Кутузовская", "88005553535", "20.05.2030", "четыре дня"},
                    {"Иванов", "Иван", "Бабушкина 2", "Бабушкинская", "5", "1.1.0000", "2 дня"},
                    {"Петр", "Петров", "Охотный ряд 3", "Лефортово", "ььь", "44.35.20430", "ссссссс"},
            };
        }

        @Test
        public void checkOrderForm(){
            WebDriver driver = new ChromeDriver();
            driver.get("https://qa-scooter.praktikum-services.ru");
            HomePageSamokat objHomePage = new HomePageSamokat(driver);
            objHomePage.clickAcceptCookiesButton();
            objHomePage.clickOrderButtonUpper();
            orderForm objOrderForm = new orderForm(driver);
            objOrderForm.waitForLoadHeader();
            objOrderForm.login(name, surname, address, station, customNumber);
            orderDetails objOrderDetails = new orderDetails(driver);
            objOrderDetails.waitForLoadOrderButton();
            objOrderDetails.setOrderDetails(date,duration);
            objOrderDetails.clickColorOfItemIsBlack();
            objOrderDetails.clickOrderButton();
        }
        @After
        public void tearDown(){
            WebDriver driver = new ChromeDriver();
            driver.quit();
        }
    }
    @Test
    public void checkFirstAnswer(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        objHomePage.waitForLoadHomePage();
        objHomePage.clickAcceptCookiesButton();
        listOfAnswers objListOfAnswers = new listOfAnswers(driver);
        objListOfAnswers.waitForLoadQuestions();
        objListOfAnswers.getIntoViewFirstQuestion();
        objListOfAnswers.waitForLoadQuestions();
        objListOfAnswers.clickToGetFirstAnswer();
        String expected = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        String actual = objListOfAnswers.getTextFirstAnswer();
        assertEquals("Полученные значения должны быть равны", expected, actual);
    }
    @Test
    public void checkLastAnswer(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        objHomePage.waitForLoadHomePage();
        objHomePage.clickAcceptCookiesButton();
        listOfAnswers objListOfAnswers = new listOfAnswers(driver);
        objListOfAnswers.waitForLoadQuestions();
        objListOfAnswers.getIntoViewLastQuestion();
        objListOfAnswers.waitForLoadQuestions();
        objListOfAnswers.clickToGetLastAnswer();
        String expected = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
        String actual = objListOfAnswers.getTextLastAnswer();
        assertEquals("Полученные значения должны быть равны", expected, actual);
    }
}
