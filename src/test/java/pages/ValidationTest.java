package pages;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Map;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ValidationTest {
    private Map<String, String> values;
    private Map<String, String> results;


    public ValidationTest(Map values, Map results) {
        this.values = values;
        this.results = results;

    }

    @Parameterized.Parameters(name = "{index}:validation({0}+{1}+{2}+{3})=({4}+{5}+{6}+{7})")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {Map.ofEntries(Map.entry("Код плательщика за ЖКУ в Москве", "123")), Map.ofEntries(Map.entry("Код плательщика за ЖКУ в Москве", "Поле неправильно заполнено"))},
                {Map.ofEntries(Map.entry("За какой период оплачиваете коммунальные услуги", "25.2018")), Map.ofEntries(Map.entry("За какой период оплачиваете коммунальные услуги", "Поле заполнено некорректно"))},
                {Map.ofEntries(Map.entry("Сумма платежа", "9")), Map.ofEntries(Map.entry("Сумма платежа", "Минимум — 10 \u20BD"))},
                {Map.ofEntries(Map.entry("Сумма платежа", "15001")), Map.ofEntries(Map.entry("Сумма платежа", "Максимум — 15 000 \u20BD"))},
                {Map.ofEntries(Map.entry("Сумма платежа", "15001"), Map.entry("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве", "15002")),
                        Map.ofEntries(Map.entry("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве", "Сумма добровольного страхования не может быть больше итоговой суммы."))}
        });
    }

    public static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        browser = "chrome";
        driver = getWebDriver();

    }

    @Test
    public void validationTest() {
        open("https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay");
        zkuMoskowPaymentPage zkuMoskowPaymentPage = new zkuMoskowPaymentPage(driver);
        for (Map.Entry<String, String> entry : values.entrySet()) {
            zkuMoskowPaymentPage.setValueByInputTitle(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : results.entrySet()) {
            assertEquals(entry.getValue(), zkuMoskowPaymentPage.getErrorMessage(entry.getKey()));
        }


    }
}
