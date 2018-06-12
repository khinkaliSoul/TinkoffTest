package pages;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ValidationTest {
    private String valueA;
    private String valueB;
    private String valueC;
    private String valueD;
    private String expected1;
    private String expected2;
    private String expected3;
    private String expected4;


    public ValidationTest (String valueA, String valueB, String valueC, String valueD,
                            String expected1, String expected2, String expected3, String expected4) {
        this.valueA = valueA;
        this.valueB = valueB;
        this.valueC = valueC;
        this.valueD = valueD;
        this.expected1 = expected1;
        this.expected2 = expected2;
        this.expected3 = expected3;
        this.expected4 = expected4;
    }

    @Parameterized.Parameters(name = "{index}:validation({0}+{1}+{2}+{3})=({4}+{5}+{6}+{7})")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {"123", "25.2018", "15002", "15001", "Поле неправильно заполнено", "Поле заполнено некорректно", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Максимум — 15 000 \u20BD"},
                {"123", "25.2018", "15002", "9", "Поле неправильно заполнено", "Поле заполнено некорректно", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Минимум — 10 \u20BD"},
                {"", "25.2018", "15002", "9", "Поле обязательное", "Поле заполнено некорректно", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Минимум — 10 \u20BD"},
                {"123", "", "15002", "9", "Поле неправильно заполнено", "Поле обязательное", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Минимум — 10 \u20BD"},
                {"123", "25.2018", "15002", "", "Поле неправильно заполнено", "Поле заполнено некорректно","Поле обязательное","Поле обязательное"},
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
        zkuMoskowPaymentPage.setValueByInputTitle("Код плательщика за ЖКУ в Москве", valueA);
        zkuMoskowPaymentPage.setValueByInputTitle("За какой период оплачиваете коммунальные услуги", valueB);
        zkuMoskowPaymentPage.setValueByInputTitle("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве", valueC);
        zkuMoskowPaymentPage.setValueByInputTitle("Сумма платежа", valueD);
        String[] errors = zkuMoskowPaymentPage.getErrorMessages();
        assertTrue(Arrays.asList(errors).contains(expected1));
        assertTrue(Arrays.asList(errors).contains(expected2));
        assertTrue(Arrays.asList(errors).contains(expected3));
        assertTrue(Arrays.asList(errors).contains(expected4));


    }
}
