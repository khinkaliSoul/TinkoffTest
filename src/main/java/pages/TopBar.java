package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class TopBar {
    private WebDriver driver;

    public TopBar (WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickSecondMenuTabByText(String text)
    {

        $(By.xpath("//div[@data-qa-file='Tabs']//span[contains(text(),'"+text+"')]")).click();

        /*driver.findElement(By.xpath("//a[contains(@data-qa-file,'Link')]/span[contains(text(),'"+text+"')]"))
                .click();*/
    }
    public void goToMainPage()
    {
        $(By.xpath("//div[@data-qa-file='FirstMenu']/a/span[contains(text(),'Тинькофф')]")).click();
    }

    public void search(String query)
    {
        $(By.xpath("//input[@data-qa-file='SearchInput']")).click();
      $(By.xpath("//input[@data-qa-file='SearchInput']")).setValue(query);
    }

    public boolean isSuggestElementOnNumber(String num, String providerName)
    {
        try {
            $(By.xpath("//div[@data-qa-file='SuggestBlock']/div[@data-qa-file='Grid']/div[" + num + "]//div/div[contains(text(),'" + providerName + "')]"));
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void selectSuggestElementOnNumber(String num, String providerName) {
        $(By.xpath("//div[@data-qa-file='SuggestBlock']/div[@data-qa-file='Grid']/div[" + num + "]//div/div[contains(text(),'" + providerName + "')]")).click();
    }



}
