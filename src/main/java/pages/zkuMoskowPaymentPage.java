package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ByAll;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.xpath;


public class zkuMoskowPaymentPage {
    private WebDriver driver;

    public zkuMoskowPaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void selectTabByText(String text) {
        $(xpath("//span[contains(text(),'" + text + "')]")).click();
    }

    public void setValueByInputTitle(String title, String value) {
        SelenideElement input = $(new ByAll(xpath("//span[contains(text(),'" + title + "')]/../../input"), xpath("//span[contains(text(),'" + title + "')]//..//input")));
        actions().click(input).perform();
        input.setValue(value).pressEnter();
    }

    public String[] getErrorMessages() {
        String[] list;
        list = $$(By.xpath("//div[@data-qa-file='UIFormRowError']")).getTexts();
//        String errorMessage = null;
//        switch (title) {
//            case "Код плательщика за ЖКУ в Москве":
//                errorMessage = $(xpath("//span[contains(text(),'Код плательщика за ЖКУ в Москве')]//..//..//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
//                break;
//            case "За какой период оплачиваете коммунальные услуги":
//                errorMessage = $(xpath("//span[contains(text(),'За какой период оплачиваете коммунальные услуги')]//..//..//..//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
//                break;
//            case "Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве":
//                errorMessage = $(xpath("//span[contains(text(),'Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве')]//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
//                break;
//            case "Сумма платежа":
//                errorMessage = $(xpath("//span[contains(text(),'Сумма платежа')]//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
//                break;
        return list;
    }
}


