package pages;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tests {
    public static WebDriver driver;


    @BeforeClass
    public static void setUp() {
        browser = "chrome";
        driver = getWebDriver();

    }
    @Before
    public  void setUpBeforeTest()
    {
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
        assertEquals("ЖКУ-Москва",firstProvider);
        communalPage.chooseProviderByNumber("1");
        zkuMoskowPaymentPage zkuMoskowPaymentPage = new zkuMoskowPaymentPage(driver);
        zkuMoskowPaymentPage.selectTabByText("Оплатить ЖКУ в Москве");
        //ввод данных
        zkuMoskowPaymentPage.setValueByInputTitle("Код плательщика за ЖКУ в Москве", "");
        zkuMoskowPaymentPage.setValueByInputTitle("За какой период оплачиваете коммунальные услуги", "");
        zkuMoskowPaymentPage.setValueByInputTitle("Сумма платежа", "");
        //проверки ошибок
        assertEquals("Поле обязательное", zkuMoskowPaymentPage.getErrorMassageByInputTitle("Код плательщика за ЖКУ в Москве"));
        assertEquals("Поле обязательное", zkuMoskowPaymentPage.getErrorMassageByInputTitle("За какой период оплачиваете коммунальные услуги"));
        assertEquals("Поле обязательное",zkuMoskowPaymentPage.getErrorMassageByInputTitle("Сумма платежа"));
        //ввод данных
        zkuMoskowPaymentPage.setValueByInputTitle("Код плательщика за ЖКУ в Москве", "123");
        zkuMoskowPaymentPage.setValueByInputTitle("За какой период оплачиваете коммунальные услуги", "25.2018");
        zkuMoskowPaymentPage.setValueByInputTitle("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве", "10000");
        zkuMoskowPaymentPage.setValueByInputTitle("Сумма платежа", "15001");
        //проверки ошибок
        assertEquals("Поле неправильно заполнено",zkuMoskowPaymentPage.getErrorMassageByInputTitle("Код плательщика за ЖКУ в Москве"));
        assertEquals("Поле заполнено некорректно",zkuMoskowPaymentPage.getErrorMassageByInputTitle("За какой период оплачиваете коммунальные услуги"));
        assertEquals("Максимум — 15 000 \u20BD",zkuMoskowPaymentPage.getErrorMassageByInputTitle("Сумма платежа"));
        //Изменение данных
        zkuMoskowPaymentPage.setValueByInputTitle("Сумма платежа", "1500");
        //Проверка ошибки
        assertEquals("Сумма добровольного страхования не может быть больше итоговой суммы.",zkuMoskowPaymentPage.getErrorMassageByInputTitle("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве"));
        topBar.goToMainPage();
        topBar.clickSecondMenuTabByText("Платежи");
       //topBar.search(providerName);
        //assertTrue(topBar.isSuggestElementOnNumber("1",providerName));
        //topBar.selectSuggestElementOnNumber("1",providerName);





    }
}
