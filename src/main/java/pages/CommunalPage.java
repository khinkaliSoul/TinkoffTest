package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import static com.codeborne.selenide.Selenide.$;


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
        $(By.xpath("//div[@data-qa-file='UIRegions']/div/span/a/span[contains(text(),'"+city+"')]")).click();
    }

    public void chooseProviderByText(String providerName)
    {
        $(By.xpath("//span[contains(text(),'"+providerName+"')]")).click();
    }
}
