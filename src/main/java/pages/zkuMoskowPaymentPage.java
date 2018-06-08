package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ByAll;

import java.security.PublicKey;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.executeJavaScript;
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
       // actions().click($(byText(title))).perform();
        $(By.xpath("//div[@data-qa-file=\"PlatformLayout\"][2]")).click();
        driver.findElement(new ByAll(xpath(".//ancestor::label/span[contains(text(),'"+title+"')]"),
                     xpath(".//following::span[@class=\"ui-input__label_placeholder-text\"][contains(text(),'"+title+"')]"))).sendKeys(value);


//        switch (title) {
//            case "Код плательщика за ЖКУ в Москве":
//                $(By.xpath("//span[contains(text(),'" + title + "')]//..//../input")).setValue(value).pressEnter();
//                $(By.xpath("//span[contains(text(),'Код плательщика за ЖКУ в Москве')]//..//../input")).setValue(value).pressEnter();
//                break;
//            case "За какой период оплачиваете коммунальные услуги":
//                $(By.xpath("//span[contains(text(),'" + title + "')]//..//../input")).setValue(value).pressEnter();
//                break;
//            case "Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве":
//                $(By.xpath("//span[contains(text(),'" + title + "')]//../div/input")).setValue(value).pressEnter();
//                $(By.xpath("//span[contains(text(),'Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве')]//../div/input")).setValue(value).pressEnter();
//                break;
//            case "Сумма платежа":
//                actions().click($(By.xpath("//span[contains(text(),'" + title + "')]//../div"))).build().perform();
//                $(By.xpath("//span[contains(text(),'" + title + "')]//..//../input")).setValue(value).pressEnter();
//                break;
//        }

    }

    public String getErrorMassageByInputTitle(String title) {
        String errorMessage = null;
        switch (title) {
            case "Код плательщика за ЖКУ в Москве":
                errorMessage = $(xpath("//span[contains(text(),'Код плательщика за ЖКУ в Москве')]//..//..//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
                break;
            case "За какой период оплачиваете коммунальные услуги":
                errorMessage = $(xpath("//span[contains(text(),'За какой период оплачиваете коммунальные услуги')]//..//..//..//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
                break;
            case "Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве":
                errorMessage = $(xpath("//span[contains(text(),'Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве')]//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
                break;
            case "Сумма платежа":
                errorMessage = $(xpath("//span[contains(text(),'Сумма платежа')]//..//..//..//div[@data-qa-file='UIFormRowError']")).getText();
                break;


        }
        return errorMessage;
    }
}



