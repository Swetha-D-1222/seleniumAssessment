package pages;

import locators.HomePageLocators;
import locators.SearchResultPagelocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SearchResultPage
{
    WebDriver driver;
    WebDriverWait wait;
    public SearchResultPage(WebDriver driver, WebDriverWait wait)
    {
        this.driver=driver;
        this.wait=wait;
    }


   public void setPriceRange(String minprice, String maxprice) throws InterruptedException
   {
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchResultPagelocators.pageTitle)));

       WebElement minimum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SearchResultPagelocators.minimumPriceValue)));
       WebElement maximum = driver.findElement(By.xpath(SearchResultPagelocators.maximumPriceValue));

       WebElement sliderTrack = driver.findElement(By.xpath(SearchResultPagelocators.priceSlider));
       int sliderWidth = sliderTrack.getSize().getWidth();

       int sliderMinValue = 0;
       int sliderMaxValue = 30000;

       int minPriceValue = Integer.parseInt(minprice);
       int maxPriceValue = Integer.parseInt(maxprice);
       int minOffset = (int) (((double) (minPriceValue - sliderMinValue) / (sliderMaxValue - sliderMinValue)) * sliderWidth);
       int maxOffset = (int) (((double) (maxPriceValue - sliderMinValue) / (sliderMaxValue - sliderMinValue)) * sliderWidth);

       Actions actions = new Actions(driver);
       actions.clickAndHold(minimum).moveByOffset(minOffset, 0).release().perform();
       actions.clickAndHold(maximum).moveByOffset(maxOffset - sliderWidth, 0).release().perform();
   }

    public void applyFilters(String reservation, String review, String facility, String rating) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomePageLocators.alertDismiss))).click();
            System.out.println("Alert dismissed successfully.");
        } catch (Exception e) {
            System.out.println("No alert appeared");
        }

        List<WebElement> facilities = driver.findElements(By.xpath(SearchResultPagelocators.facilities));
        for (WebElement element : facilities) {
            if (element.getText().equalsIgnoreCase(facility)) {
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                js.executeScript("arguments[0].click();", element);
                System.out.println("Selected facility: " + facility);
                break;
            }
        }

        WebElement roomFacilities = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchResultPagelocators.roomFacilities)));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", roomFacilities);

        List<WebElement> reviewScores = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(SearchResultPagelocators.reviewScore)));
        for (WebElement element : reviewScores) {
            if (element.getText().equalsIgnoreCase(review)) {
                js.executeScript("arguments[0].click();", element);
                System.out.println("Selected review score: " + review);
                break;
            }
        }

        WebElement neighbourhood = driver.findElement(By.xpath(SearchResultPagelocators.neighbourhood));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", neighbourhood);

        List<WebElement> reservations = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(SearchResultPagelocators.reservationPolicies)));
        for (WebElement element : reservations) {
            if (element.getText().equalsIgnoreCase(reservation)) {
                js.executeScript("arguments[0].click();", element);
                System.out.println("Selected reservation policy: " + reservation);
                break;
            }
        }

        List<WebElement> ratings = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(SearchResultPagelocators.propertyRating)));
        for (WebElement element : ratings) {
            if (element.getText().equalsIgnoreCase(rating)) {
                js.executeScript("arguments[0].click();", element);
                System.out.println("Selected property rating: " + rating);
                break;
            }
        }

        System.out.println("All filters applied successfully.");
    }



    public void updateCheckOutDate() {

        driver.findElement(By.xpath(SearchResultPagelocators.dateField)).click();


        WebElement selectedCheckInDateElement = driver.findElement(By.xpath("(//span[@aria-checked='true'])[1]"));
        String selectedCheckInDateStr = selectedCheckInDateElement.getAttribute("data-date");
        System.out.println("Selected Check-in Date: " + selectedCheckInDateStr);


        WebElement selectedCheckOutDateElement = driver.findElement(By.xpath("(//span[@aria-checked='true'])[6]"));
        String selectedCheckOutDateStr = selectedCheckOutDateElement.getAttribute("data-date");
        System.out.println("Selected Checkout Date: " + selectedCheckOutDateStr);


        LocalDate checkInDate = LocalDate.parse(selectedCheckInDateStr);
        LocalDate checkOutDate = LocalDate.parse(selectedCheckOutDateStr);

        LocalDate newCheckOutDate = checkOutDate.plusDays(1);

        String newCheckOutDateStr = newCheckOutDate.toString();
        selectedCheckOutDateElement.click();

        WebElement newCheckInDateElement = driver.findElement(By.xpath("//span[@data-date='" + selectedCheckInDateStr + "']"));
        newCheckInDateElement.click();

        WebElement newCheckOutDateElement = driver.findElement(By.xpath("//span[@data-date='" + newCheckOutDateStr + "']"));
        newCheckOutDateElement.click();

        System.out.println("Updated checkout date to: " + newCheckOutDateStr);

        driver.findElement(By.xpath(SearchResultPagelocators.searchButton)).click();
        selectProperty();
    }


    public void selectProperty()
    {
        List<Integer> price=new LinkedList<>();
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(SearchResultPagelocators.propertyCharges)));
        System.out.println(elements.size());
        for(WebElement element:elements)
        {
            String amount=element.getText().replaceAll("₹ ","").replaceAll(",","");
            price.add(Integer.parseInt(amount));
        }
        selectHighPriceProperty(price.indexOf(Collections.max(price)));

    }

    private void selectHighPriceProperty(int index)
    {
        List<WebElement> property=driver.findElements(By.xpath(SearchResultPagelocators.properties));
        property.get(index).click();
        String originalTab = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allTabs = driver.getWindowHandles();
        for (String handle : allTabs) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        PropertyPage propertyPage = new PropertyPage(driver, wait);
        propertyPage.changeReturnDateAndReserve();
    }


    public String getPriceRange()
    {
        String priceRange=driver.findElement(By.xpath(SearchResultPagelocators.price)).getText();
        return priceRange;
    }

    public List<String> getFilters()
    {
        List<WebElement> elements=driver.findElements(By.xpath(SearchResultPagelocators.appliedFilters));
        List<String> filters=new LinkedList<>();
        for (WebElement element : elements) {
            String name = element.getText();
                filters.add(name);
        }
        return filters;
    }

    public float getRating()
    {
        float rate= Float.parseFloat(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchResultPagelocators.villaRating))).getText().replaceAll("Scored ",""));
        return rate;
    }

    public String getVillaPrice()
    {
        String villaPrice=driver.findElement(By.xpath(SearchResultPagelocators.villaprice)).getText();
        return villaPrice;
    }

    public String getVillaName()
    {
        String villaName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchResultPagelocators.villaTitle))).getText();
        return villaName;
    }
}
