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

    public String getErrorMessage(String title) {

        return $(By.xpath("//span[contains(text(),'" + title + "')]//following::div[@data-qa-file='UIFormRowError'][1]")).text();
    }
}


