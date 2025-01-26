package pages;

import locators.CheckOutPageLocators;
import locators.PropertyPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PropertyPage {
    WebDriver driver;
    WebDriverWait wait;

    public PropertyPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void changeReturnDateAndReserve()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -150);");
//        WebElement date = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertyPageLocators.dateField)));
//        js.executeScript("arguments[0].click();", date);
//        driver.findElement(By.xpath(PropertyPageLocators.changeSearchButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PropertyPageLocators.availability)));
        WebElement selectOption=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(PropertyPageLocators.selectOption)));
        selectOption.click();
        Select select=new Select(selectOption);
        select.selectByIndex(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertyPageLocators.reserveButton))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CheckOutPageLocators.pagetitle)));
        String url=driver.getCurrentUrl();

    }

    public String getVillaPrice()
    {
        String villaPrice=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertyPageLocators.villaPrice))).getText();
        return villaPrice;
    }

    public String getVillaName()
    {
        String villaName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertyPageLocators.pageTitle))).getText();
        return villaName;
    }
}

