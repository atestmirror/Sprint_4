import browserSwitch.BrowserSwitch;
import browserSwitch.browserList;
import orderFormTest.orderDetails;
import orderFormTest.orderForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderFormTest {
    WebDriver driver;
    private final browserList browser;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String customNumber;
    private final String date;
    private final String duration;
    private final String color;
    private final String comment;

    public OrderFormTest(browserList browser, String name, String surname, String address, String station, String customNumber, String date, String duration, String color, String comment) {
        this.browser = browser;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.customNumber = customNumber;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }
    @Before
    public void preSetup(){
        BrowserSwitch br = new BrowserSwitch();
        driver = br.launchBrowserSwitch(browser);
        driver.get("https://qa-scooter.praktikum-services.ru");
    }
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {browserList.FIREFOX, "Анна", "Дубровина", "Кутузовский проспект дом 4", "Кутузовская", "88005553535", "понедельник, 15-е мая 2023 г.", "сутки", "черный", "Позвоните за полчаса до доставки"},
                {browserList.FIREFOX, "Александр", "Пушкин", "Ленинградский проспект 34 корпус 3", "Черкизовская", "89999999999", "воскресенье, 7-е мая 2023 г.", "пятеро суток", "без разницы", "Домофон 0#222"},
                {browserList.FIREFOX, "Иван", "Иванов", "Невский проспект 1", "Лужники", "3333333333453", "пятница, 5-е мая 2023 г.", "трое суток", "серый", "Доставку примет кот Барсик, почешите за ушком"},
                {browserList.CHROME, "Анна", "Дубровина", "Кутузовский проспект дом 4", "Кутузовская", "88005553535", "понедельник, 15-е мая 2023 г.", "сутки", "черный", "Позвоните за полчаса до доставки"},
                {browserList.CHROME, "Александр", "Пушкин", "Ленинградский проспект 34 корпус 3", "Черкизовская", "89999999999", "воскресенье, 7-е мая 2023 г.", "пятеро суток", "без разницы", "Домофон 0#222"},
                {browserList.CHROME, "Иван", "Иванов", "Невский проспект 1", "Лужники", "3333333333453", "пятница, 5-е мая 2023 г.", "трое суток", "серый", "Доставку примет кот Барсик, почешите за ушком"},
        };
    }
    @Test
    public void checkUpperOrderForm() {
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        objHomePage.clickAcceptCookiesButton();
        objHomePage.clickOrderButtonUpper();
        orderForm objOrderForm = new orderForm(driver);
        objOrderForm.waitForLoadHeader();
        objOrderForm.login(name, surname, address, station, customNumber);
        orderDetails objOrderDetails = new orderDetails(driver);
        objOrderDetails.waitForLoadOrderButton();
        objOrderDetails.setOrderDetailsInBulk(date, duration, color, comment);
        objOrderDetails.waitForLoadConfirmButton();
        objOrderDetails.clickConfirmButton();
        objOrderDetails.waitForLoadStatus();
        String actual = objOrderDetails.getStatusButtonText();
        assertEquals("Посмотреть статус", actual);
    }
    @Test
    public void checkLowerOrderForm() {
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        objHomePage.clickAcceptCookiesButton();
        objHomePage.clickOrderButtonUpper();
        orderForm objOrderForm = new orderForm(driver);
        objOrderForm.waitForLoadHeader();
        objOrderForm.login(name, surname, address, station, customNumber);
        orderDetails objOrderDetails = new orderDetails(driver);
        objOrderDetails.waitForLoadOrderButton();
        objOrderDetails.setOrderDetailsInBulk(date, duration, color, comment);
        objOrderDetails.waitForLoadConfirmButton();
        objOrderDetails.clickConfirmButton();
        objOrderDetails.waitForLoadStatus();
        String actual = objOrderDetails.getStatusButtonText();
        assertEquals("Посмотреть статус", actual);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
