package locators;

import java.security.SecureRandom;

public class HomePageLocators
{
    public static String destinationSearchBox=":rh:";
    public static String alertDismiss="//button[@aria-label=\"Dismiss sign in information.\"]";
    public static String selectOption="autocomplete-result-0";
    public static String dateField="//div[@data-testid=\"searchbox-dates-container\"]";
    public static String nextMonthArrow="//button[@aria-label=\"Next month\"]";
    public static String currentMonth="(//h3[@class=\"e1eebb6a1e ee7ec6b631\"])[1]";
    public static String selectPeople="//div[@class=\"d777d2b248\"]";
    public static String peopleCount="//div[@class=\"bfb38641b0\"]/child::button[@class=\"a83ed08757 c21c56c305 f38b6daa18 d691166b09 ab98298258 bb803d8689 f4d78af12a\"]";
    public static String childrenAgeField="age";
    public static String allowPets="//span[@class='ade77cee67']";
    public static String doneButton="//button[@class='a83ed08757 c21c56c305 bf0537ecb5 ab98298258 a2abacf76b af7297d90d c213355c26 b9fd3c6b3c']";
    public static String searchButton="//span[contains(text(),'Search')]";
    public static String selectedPeopleCount="//span[@class=\"a8887b152e c7ce171153\"]";
}
