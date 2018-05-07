package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.security.PublicKey;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class ZKU_MoskowPaymentPage {
    private WebDriver driver;

    public ZKU_MoskowPaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void selectTabByText(String text) {
        $(By.xpath("//span[contains(text(),'" + text + "')]")).click();
    }

    public void setValueByInputTitle(String title, String value) {
        switch (title) {
            case "Код плательщика за ЖКУ в Москве":
                $(By.xpath("//span[contains(text(),'" + title + "')]//..//../input")).setValue(value).pressEnter();
                $(By.xpath("//span[contains(text(),'Код плательщика за ЖКУ в Москве')]//..//../input")).setValue(value).pressEnter();
                break;
            case "За какой период оплачиваете коммунальные услуги":
                $(By.xpath("//span[contains(text(),'" + title + "')]//..//../input")).setValue(value).pressEnter();
                break;
            case "Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве":
                $(By.xpath("//span[contains(text(),'" + title + "')]//../div/input")).setValue(value).pressEnter();
                $(By.xpath("//span[contains(text(),'Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве')]//../div/input")).setValue(value).pressEnter();
                break;
            case "Сумма платежа":
                actions().click($(By.xpath("//span[contains(text(),'" + title + "')]//../div"))).build().perform();
                $(By.xpath("//span[contains(text(),'" + title + "')]//..//../input")).setValue(value).pressEnter();
                break;
        }

    }

    public String getErrorMassageByInputTitle(String title) {
        String errorMessage = null;
        switch (title) {
            case "Код плательщика за ЖКУ в Москве":
                errorMessage = $(By.xpath("//span[contains(text(),'Код плательщика за ЖКУ в Москве')]//..//..//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
                break;
            case "За какой период оплачиваете коммунальные услуги":
                errorMessage = $(By.xpath("//span[contains(text(),'За какой период оплачиваете коммунальные услуги')]//..//..//..//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
                break;
            case "Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве":
                errorMessage = $(By.xpath("//span[contains(text(),'Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве')]//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
                break;
            case "Сумма платежа":
                errorMessage = $(By.xpath("//span[contains(text(),'Сумма платежа')]//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
                break;


        }
        return errorMessage;
    }
}



