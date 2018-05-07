package pages;


import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tests {

    @BeforeClass
    public static void setUp() {
        browser = "chrome";
        open("https://www.tinkoff.ru/");

    }

    @Test
    public void firstTest() throws Exception {
        TopBar topBar = new TopBar(getWebDriver());
        topBar.clickSecondMenuTabByText("Платежи");
        PaymentPage paymentPage = new PaymentPage(getWebDriver());
        paymentPage.choosePaymentCategoryByText("ЖКХ");
        CommunalPage communalPage = new CommunalPage(getWebDriver());
        if (!communalPage.getCity().equals("Москва")) {
            communalPage.chengeCity("Москва");
        }
        String providerName = "ЖКУ-Москва";
        communalPage.chooseProviderByText(providerName);
        sleep(200);
        Screenshot firstScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getWebDriver());
        BufferedImage image = firstScreenshot.getImage();
        File firstImg = new File("first.png");
        ImageIO.write(image, "png", firstImg);
        ZKU_MoskowPaymentPage zku_moskowPaymentPage = new ZKU_MoskowPaymentPage(getWebDriver());
        zku_moskowPaymentPage.selectTabByText("Оплатить ЖКУ в Москве");
        //ввод данных
        zku_moskowPaymentPage.setValueByInputTitle("Код плательщика за ЖКУ в Москве", "");
        zku_moskowPaymentPage.setValueByInputTitle("За какой период оплачиваете коммунальные услуги", "");
        zku_moskowPaymentPage.setValueByInputTitle("Сумма платежа", "");
        //проверки ошибок
        assertEquals("Поле обязательное", zku_moskowPaymentPage.getErrorMassageByInputTitle("Код плательщика за ЖКУ в Москве"));
        assertEquals("Поле обязательное", zku_moskowPaymentPage.getErrorMassageByInputTitle("За какой период оплачиваете коммунальные услуги"));
        assertEquals("Поле обязательное",zku_moskowPaymentPage.getErrorMassageByInputTitle("Сумма платежа"));
        //ввод данных
        zku_moskowPaymentPage.setValueByInputTitle("Код плательщика за ЖКУ в Москве", "123");
        zku_moskowPaymentPage.setValueByInputTitle("За какой период оплачиваете коммунальные услуги", "25.2018");
        zku_moskowPaymentPage.setValueByInputTitle("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве", "10000");
        zku_moskowPaymentPage.setValueByInputTitle("Сумма платежа", "15001");
        //проверки ошибок
        assertEquals("Поле неправильно заполнено",zku_moskowPaymentPage.getErrorMassageByInputTitle("Код плательщика за ЖКУ в Москве"));
        assertEquals("Поле заполнено некорректно",zku_moskowPaymentPage.getErrorMassageByInputTitle("За какой период оплачиваете коммунальные услуги"));
        assertEquals("Максимум — 15 000 \u20BD",zku_moskowPaymentPage.getErrorMassageByInputTitle("Сумма платежа"));
        //Изменение данных
        zku_moskowPaymentPage.setValueByInputTitle("Сумма платежа", "1500");
        //Проверка ошибки
        assertEquals("Сумма добровольного страхования не может быть больше итоговой суммы.",zku_moskowPaymentPage.getErrorMassageByInputTitle("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве"));
        topBar.goToMainPage();
        topBar.clickSecondMenuTabByText("Платежи");
        topBar.seach(providerName);
        assertTrue(topBar.isSuggestElementOnNumber("1",providerName));
        topBar.selectSuggestElementOnNumber("1",providerName);
        sleep(2000);
        Screenshot secondScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getWebDriver());
        BufferedImage secondImage = firstScreenshot.getImage();
        File secondImg = new File("second.png");
        ImageIO.write(image, "png", secondImg);
        ImageDiffer differ = new ImageDiffer();
        ImageDiff diff = differ.makeDiff(firstScreenshot,secondScreenshot);
        BufferedImage diffImage = diff.getDiffImage();
        File diffImg = new File("diff.png");
        ImageIO.write(diffImage, "png", diffImg);
        assertFalse(diff.hasDiff());





    }
}
