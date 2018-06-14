package pages;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.*;

public class Tests {
    public static WebDriver driver;


    @BeforeClass
    public static void setUp() {
        browser = "chrome";
        driver = getWebDriver();

    }

    @Before
    public void setUpBeforeTest() {
        open("https://www.tinkoff.ru/");
    }

    @Test
    public void firstTest() throws Exception {

        TopBar topBar = new TopBar(driver);
        topBar.clickSecondMenuTabByText("Платежи");
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.choosePaymentCategoryByText("ЖКХ");
        CommunalPage communalPage = new CommunalPage(driver);
        if (!communalPage.getCity().equals("Москва")) {
            communalPage.chengeCity("Москва");
        }
        sleep(1000);
        String firstProvider = communalPage.getProviderName("1");
        assertEquals("ЖКУ-Москва", firstProvider);
        communalPage.chooseProviderByNumber("1");
        sleep(1000);
        String expectedUrl = driver.getCurrentUrl();
        zkuMoskowPaymentPage zkuMoskowPaymentPage = new zkuMoskowPaymentPage(driver);
        zkuMoskowPaymentPage.selectTabByText("Оплатить ЖКУ в Москве");
        topBar.goToMainPage();
        topBar.clickSecondMenuTabByText("Платежи");
        topBar.search(firstProvider);
        assertTrue(topBar.isSuggestElementOnNumber("1", firstProvider));
        topBar.selectSuggestElementOnNumber("1", firstProvider);
        sleep(1000);
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void secondTest() throws Exception {
        TopBar topBar = new TopBar(driver);
        topBar.clickSecondMenuTabByText("Платежи");
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.choosePaymentCategoryByText("ЖКХ");
        CommunalPage communalPage = new CommunalPage(driver);
        if (!communalPage.getCity().equals("Москва")) {
            communalPage.chengeCity("Москва");
        }
        sleep(1000);
        String moskowProvider = communalPage.getProviderName("1");
        communalPage.chengeCity("Санкт-Петербург");
        String spbProvider = communalPage.getProviderName("1");
        assertNotEquals(moskowProvider, spbProvider);
    }


}