package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;


public class CommunalPage {
    private WebDriver driver;

    public CommunalPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public String getCity()
    {
        return $(By.xpath("//div[contains(text(),'ЖКХ')]/span/span")).getText();
    }

    public void chengeCity(String city)
    {
        $(By.xpath("//div[contains(text(),'ЖКХ')]/span/span")).click();
        $(By.xpath("//span[contains(text(),'"+city+"')]")).click();
    }

    public void chooseProviderByNumber(String providerNumber)
    {
        $(By.xpath("//li[@data-qa-file='UIMenuItemProvider']["+providerNumber+"]")).click();
        //$(By.xpath("//span[contains(text(),'"+providerNumber+"')]")).click();
    }
    public String getProviderName(String providerNumer)
    {
        return $(By.xpath("//li[@data-qa-file='UIMenuItemProvider'][1]")).text();
    }
}
