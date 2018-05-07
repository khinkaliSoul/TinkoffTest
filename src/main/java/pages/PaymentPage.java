package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    private WebDriver driver;

    public PaymentPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }
    public void choosePaymentCategoryByText(String text)
    {
        $(By.xpath("//a[@title='"+text+"']/span")).click();
    }

}
