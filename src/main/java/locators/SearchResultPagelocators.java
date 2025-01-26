package locators;

public class SearchResultPagelocators
{
    public static String dateField="//div[@data-testid=\"searchbox-dates-container\"]";
    public static String pageTitle="//h1[@class=\"f6431b446c d5f78961c3\"]";
    public static String priceSlider="(//div[@data-testid=\"filters-group-histogram\"]/following-sibling::div/child::div[@role=\"group\"])[1]";
    public static String minimumPriceValue="(//div[@id=\"filter_group_price_:rm:\"]/descendant::div[@role='none'])[1]";
    public static String maximumPriceValue="(//div[@id=\"filter_group_price_:rm:\"]/descendant::div[@role='none'])[2]";
    public static String reservationPolicies="//div[@id='filter_group_fc_:r42:']/descendant::div[@data-testid='filters-group-label-content']";
    public static String facilities="//div[@id=\"filter_group_hotelfacility_:r1a:\"]/descendant::div[@data-testid=\"filters-group-label-content\"]";
    public static String reviewScore="//div[@id=\"filter_group_review_score_:r32:\"]/descendant::div[@data-testid=\"filters-group-label-content\"]";
    public static String propertyRating="//div[@id=\"filter_group_class_:r3p:\"]/descendant::div[@data-testid=\"filters-group-label-content\"]";
    public static String meals="(//legend[contains(text(),'Meals')])[1]";
    public static String roomFacilities="(//legend[contains(text(),'Room facilities')])[1]";
    public static String neighbourhood="(//legend[contains(text(),'Neighbourhood')])[1]";
    public static String properties="//div[@role='list']/descendant::div[@role='listitem' and not(preceding-sibling::div[@data-testid='sticky-container'])]";
    public static String propertyCharges="//div[@role=\"list\"]/descendant::div[@role=\"listitem\" and not(preceding-sibling::div[@data-testid=\"sticky-container\"])]/descendant::span[@data-testid=\"price-and-discounted-price\"]";
    public static String checkOutDate="//span[@aria-checked='true']/descendant::span";
    public static String searchButton="//button[@type=\"submit\"]";
    public static String price="(//div[@class=\"a53cbfa6de\"])[1]";
    public static String appliedFilters="//span[@class=\"d8ce5fca2f\"]/child::span";
    public static String villaRating="(//div[@class=\"a3b8729ab1 d86cee9b25\"])[1]/descendant::div";
    public static String villaTitle="(//div[@data-testid=\"title\"])[1]";
    public static String villaprice="(//span[@data-testid=\"price-and-discounted-price\"])[1]";

}
