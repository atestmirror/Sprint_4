import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class Praktikum {
    //Тест: проверка заказа для двух кнопок - верхней и нижней
    @RunWith(Parameterized.class)
    public static class TestData {
        private final String name;
        private final String surname;
        private final String address;
        private final String station;
        private final String customNumber;
        private final String date;
        private final String duration;
        private final String color;
        private final String comment;

        public TestData(String name, String surname, String address, String station, String customNumber, String date, String duration, String color, String comment) {
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

        @Parameterized.Parameters
        public static Object[][] getCredentials() {
            return new Object[][]{
                    {"Анна", "Дубровина", "Кутузовский проспект дом 4", "Кутузовская", "88005553535", "понедельник, 15-е мая 2023 г.", "сутки", "черный", "Позвоните за полчаса до доставки"},
                    {"Александр", "Пушкин", "Ленинградский проспект 34 корпус 3", "Черкизовская", "89999999999", "воскресенье, 7-е мая 2023 г.", "пятеро суток", "без разницы", "Домофон 0#222"},
                    {"Иван", "Иванов", "Невский проспект 1", "Лужники", "3333333333453", "пятница, 5-е мая 2023 г.", "трое суток", "серый", "Доставку примет кот Барсик, почешите за ушком"},
            };
        }

        @Test
        public void checkUpperOrderForm() {
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
            objOrderDetails.setOrderDetailsInBulk(date, duration, color, comment);
            objOrderDetails.waitForLoadConfirmButton();
            objOrderDetails.clickConfirmButton();
            objOrderDetails.waitForLoadStatus();
            String actual = objOrderDetails.getStatusButtonText();
            assertEquals("Посмотреть статус", actual);
            driver.quit();
        }
        @Test
        public void checkLowerOrderForm() {
            WebDriver driver = new ChromeDriver();
            driver.get("https://qa-scooter.praktikum-services.ru");
            HomePageSamokat objHomePage = new HomePageSamokat(driver);
            objHomePage.clickAcceptCookiesButton();
            objHomePage.getIntoViewOrderButtonLower();
            objHomePage.clickOrderButtonLower();
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
            driver.quit();
        }

        //Тест: проверка отображения ответов на вопросы
        @RunWith(Parameterized.class)
        public static class TestClass {
            private final String question;
            private final String answer;

            public TestClass(String question, String answer) {
                this.question = question;
                this.answer = answer;
            }

            //Тестовые параметры - текст вопроса и текст ответа
            @Parameterized.Parameters
            public static Object[][] getAnswers() {
                return new Object[][]{
                        {"Сколько это стоит?",
                                "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                        {"Хочу сразу несколько самокатов!",
                                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                        {"Как рассчитывается время аренды?",
                                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                        {"Можно ли заказать самокат прямо на сегодня?",
                                "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                        {"Можно ли продлить заказ или вернуть самокат раньше?",
                                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                        {"Вы привозите зарядку вместе с самокатом?",
                                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                        {"Можно ли отменить заказ?",
                                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                        {"Я жизу за МКАДом, привезёте?",
                                "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
                };
            }

            @Test
            public void checkListOfAnswers() {
                WebDriver driver = new ChromeDriver();
                driver.get("https://qa-scooter.praktikum-services.ru");
                HomePageSamokat objHomePage = new HomePageSamokat(driver);
                objHomePage.waitForLoadHomePage();
                objHomePage.clickAcceptCookiesButton();
                listOfAnswers objListOfAnswers = new listOfAnswers(driver);
                objListOfAnswers.waitForLoadQuestions();
                objListOfAnswers.getIntoViewQuestion(question);
                objListOfAnswers.clickToGetAnswer(question);
                String actual = objListOfAnswers.getTextAnswer(answer);
                assertEquals(answer, actual);
                driver.quit();
            }
            @After
            public void tearDown() {
                WebDriver driver = new ChromeDriver();
                driver.quit();
            }
        }
    }
}
