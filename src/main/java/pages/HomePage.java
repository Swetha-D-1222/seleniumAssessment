package pages;

import locators.HomePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomePage
{
    WebDriver driver;
    WebDriverWait wait;
    public HomePage(WebDriver driver,WebDriverWait wait)
    {
        this.driver=driver;
        this.wait=wait;
    }
    public void selectlocation(String location) throws InterruptedException
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HomePageLocators.destinationSearchBox))).sendKeys(location);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomePageLocators.alertDismiss))).click();
            System.out.println("  Alert dismissed successfully.");
        } catch (Exception e) {
            System.out.println("No alert appeared");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HomePageLocators.selectOption))).click();

    }


    public void selectCheckInCheckOutDates()
    {

    LocalDate currentDate = LocalDate.now();
    LocalDate checkInDate = currentDate.plusDays(90);
    LocalDate checkOutDate = checkInDate.plusDays(5);
    System.out.println("Check-in Date: " + checkInDate);
    System.out.println("Check-out Date: " + checkOutDate);
    DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");

    String checkInMonthYear = checkInDate.format(monthYearFormatter);
    String checkInDay = String.valueOf(checkInDate.getDayOfMonth());
    String checkOutMonthYear = checkOutDate.format(monthYearFormatter);
    String checkOutDay = String.valueOf(checkOutDate.getDayOfMonth());

    while (true) {
        String displayedMonthYear = driver.findElement(By.xpath(HomePageLocators.currentMonth)).getText();
        if (displayedMonthYear.equals(checkInMonthYear)) {
            break;
        }
        driver.findElement(By.xpath(HomePageLocators.nextMonthArrow)).click();
    }

    WebElement checkInElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[.='" + checkInDay + "']")));
    checkInElement.click();

    if (!checkInMonthYear.equals(checkOutMonthYear)) {
        while (true) {
            String displayedMonthYear = driver.findElement(By.xpath(HomePageLocators.currentMonth)).getText();
            if (displayedMonthYear.equals(checkOutMonthYear)) {
                break;
            }
            driver.findElement(By.xpath(HomePageLocators.nextMonthArrow)).click();
        }
    }

    WebElement checkOutElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[.='" + checkOutDay + "']")));
    checkOutElement.click();
    }

    public void enterPeopleDetails(int adultCount, int childrenCount, String childrenAge, int roomsCount) throws InterruptedException {
        driver.findElement(By.xpath(HomePageLocators.selectPeople)).click();
        List<WebElement> list=driver.findElements(By.xpath(HomePageLocators.peopleCount));
        for(int i=0;i<adultCount-2;i++)
        {
            list.get(0).click();
        }
        for(int i=0;i<childrenCount;i++)
        {
            list.get(1).click();
        }
        WebElement childrenAgeField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(HomePageLocators.childrenAgeField)));
        childrenAgeField.click();
        Select select=new Select(childrenAgeField);
        select.selectByIndex(Integer.parseInt(childrenAge)-1);
        for(int i=0;i<roomsCount-1;i++)
        {
            list.get(2).click();
        }
        driver.findElement(By.xpath(HomePageLocators.allowPets)).click();
        driver.findElement(By.xpath(HomePageLocators.doneButton)).click();
        driver.findElement(By.xpath(HomePageLocators.searchButton)).click();
    }

    public String getPeopleCount()
    {
        String people=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomePageLocators.selectedPeopleCount))).getText();
        return people;
    }
}
