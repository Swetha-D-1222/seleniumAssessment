package pages;

import locators.CheckOutPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage
{
    WebDriver driver;
    WebDriverWait wait;
    public CheckOutPage(WebDriver driver, WebDriverWait wait)
    {
        this.driver=driver;
        this.wait=wait;
    }

    public void fillDetails(String firstname, String lastname, String email, String address, String city, String zipcode, String country, String phoneno)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CheckOutPageLocators.pagetitle)));
        driver.findElement(By.name(CheckOutPageLocators.firstName)).sendKeys(firstname);
        driver.findElement(By.name(CheckOutPageLocators.lastName)).sendKeys(lastname);
        driver.findElement(By.name(CheckOutPageLocators.email)).sendKeys(email);
        driver.findElement(By.name(CheckOutPageLocators.address)).sendKeys(address);
        driver.findElement(By.name(CheckOutPageLocators.city)).sendKeys(city);
        WebElement countryDropdown=driver.findElement(By.id(CheckOutPageLocators.country));
        countryDropdown.click();
        Select select=new Select(countryDropdown);
        select.selectByVisibleText(country);
        driver.findElement(By.name(CheckOutPageLocators.phoneNumber)).sendKeys(phoneno);
        driver.findElement(By.xpath(CheckOutPageLocators.checkBox)).click();


    }
}
