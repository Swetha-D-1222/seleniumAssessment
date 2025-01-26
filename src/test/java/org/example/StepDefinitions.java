package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.CheckOutPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CheckOutPage;
import pages.HomePage;
import pages.PropertyPage;
import pages.SearchResultPage;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class StepDefinitions
{

    WebDriver driver;
    HomePage homePage;
    SearchResultPage searchResult;
    WebDriverWait wait;
    PropertyPage propertyPage;
    CheckOutPage checkOutPage;
    @Given("The user launches the Booking.com website")
    public void theUserLaunchesTheBookingComWebsite()
    {
        ChromeOptions option=new ChromeOptions();
        option.addArguments("--start-fullscreen");
        driver=new ChromeDriver(option);
        driver.navigate().to("https://www.booking.com/");

        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        homePage=new HomePage(driver,wait);
        searchResult = new SearchResultPage(driver,wait);
        propertyPage=new PropertyPage(driver,wait);
        checkOutPage=new CheckOutPage(driver,wait);
    }

    @When("The user selects the location {string}")
    public void theUserSelectsTheLocation(String location) throws InterruptedException
    {
        homePage.selectlocation(location);
    }

    @And("Select the checkIn and checkOut dates")
    public void selectTheCheckInAndCheckOutDates()
    {
        homePage.selectCheckInCheckOutDates();

    }

    int adultcount=0,childrencount=0,roomscount=0;
    @And("Select the number of people and click search button {string},{string},{string},{string}")
    public void selectTheNumberOfPeopleAndClickSearchButton(String adultCount, String childrenCount, String childrenAge, String roomsCount) throws InterruptedException
    {
        adultcount=Integer.parseInt(adultCount);
        childrencount = Integer.parseInt(childrenCount);
        roomscount = Integer.parseInt(roomsCount);
        homePage.enterPeopleDetails(adultcount,childrencount,childrenAge,roomscount);
    }

    @Then("View and validate the search results")
    public void viewAndValidateTheSearchResults()
    {
        String peoplecount=homePage.getPeopleCount();
        Assert.assertTrue(peoplecount.contains(adultcount+" adults"),"Adult count wrong");
        Assert.assertTrue(peoplecount.contains(childrencount+" child"),"children count wrong");
        Assert.assertTrue(peoplecount.contains(roomscount+" room"),"room count wrong");
        Assert.assertTrue(peoplecount.contains("Pets"),"Pets not included");

    }

    String minimumPrice;
    String maximumPrice;
    @When("The user sets the price range {string},{string}")
    public void theUserSetsThePriceRange(String minprice, String maxprice) throws InterruptedException
    {
        minimumPrice=minprice;
        maximumPrice=maxprice;
        searchResult.setPriceRange(minimumPrice,maximumPrice);
    }

    @Then("validate if the price range is updated in the page")
    public void validateIfThePriceRangeIsUpdatedInThePage()
    {
        String price=searchResult.getPriceRange();
        Assert.assertTrue(price.contains("15,000"),"Minimum price do not match your expectations");
    }

    List<String> filtersToApply=new LinkedList<>();
    int expectedRating=0;
    @When("The user applies the filter expecting the following facilities {string},{string},{string} and {string}")
    public void theUserAppliesTheFilterExpectingTheFollowingFacilitiesAnd(String reservation, String review, String facility, String rating)
    {
        filtersToApply.add(reservation);
        filtersToApply.add(review);
        filtersToApply.add(facility);
        filtersToApply.add(rating);
        expectedRating=Integer.parseInt(rating.replaceAll("[^0-9]", ""));
        searchResult.applyFilters(reservation,review,facility,rating);
    }
    String villaPrice="";
    String villaName="";
    @Then("Verify if the filters are applied")
    public void verifyIfTheFiltersAreApplied()
    {
        villaName=searchResult.getVillaName();
        villaPrice=searchResult.getVillaPrice();
        List<String> filtersApplied=searchResult.getFilters();
        float rating=searchResult.getRating();
        System.out.println(filtersApplied);
        System.out.println(filtersToApply);
        Assert.assertTrue(rating>expectedRating,"Villa with expected rating not found");

    }


    @When("The user selects the property with the highest charges")
    public void theUserSelectsThePropertyWithTheHighestCharges()
    {
        searchResult.selectProperty();
    }

    String propertyname;
    String propertyprice;
    @And("The user changes the return day plus one and reserves the booking")
    public void theUserChangesTheReturnDayPlusOneAndReservesTheBooking()
    {
        propertyprice=propertyPage.getVillaPrice();
        propertyname=propertyPage.getVillaName();
        propertyPage.changeReturnDateAndReserve();
    }

    @Then("Validate if the selected villa name and price are same")
    public void validateIfTheSelectedVillaNameAndPriceAreSame()
    {
        Assert.assertEquals(villaName,propertyname,"The same villa is not selected");
        Assert.assertEquals(villaPrice,propertyprice,"price of the villa is not same!!");
    }


    @When("The user enters their details {string},{string},{string},{string},{string},{string},{string},{string}")
    public void theUserEntersTheirDetails(String firstname, String lastname, String email, String address, String city, String zipcode, String country, String phoneno)
    {
        String url=driver.getCurrentUrl();
        driver.navigate().to(url);
        checkOutPage.fillDetails(firstname,lastname,email,address,city,zipcode,country,phoneno);
    }
    @Then("Check if the user is navigated to the filling of booking details page")
    public void checkIfTheUserIsNavigatedToTheFillingOfBookingDetailsPage()
    {

        Assert.assertEquals(driver.findElement(By.xpath(CheckOutPageLocators.pagetitle)).getText(),"Enter your details","You are not the checkoutpage!");
    }


}
