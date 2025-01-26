Feature: Book a room for location mumbai

  @positive
  Scenario Outline: Search and book a room for location 'Mumbai'

    Given  The user launches the Booking.com website
    When   The user selects the location "<location>"
    And    Select the checkIn and checkOut dates
    And    Select the number of people and click search button "<adultcount>","<childrencount>","<childrenage>","<roomscount>"
    Then   View and validate the search results

    When   The user sets the price range "<minprice>","<maxprice>"
    Then   validate if the price range is updated in the page

    When   The user applies the filter expecting the following facilities "<reservation>","<reviewscore>","<facilities>" and "<rating>"
    Then   Verify if the filters are applied

    When   The user selects the property with the highest charges
    And    The user changes the return day plus one and reserves the booking
    Then   Validate if the selected villa name and price are same

    When   The user enters their details "<firstname>","<lastname>","<email>","<address>","<city>","<zip>","<country>","<phonenumber>"
    Then   Check if the user is navigated to the filling of booking details page

    Examples:
      | location | adultcount | childrencount | childrenage | roomscount | minprice | maxprice | reservation       | reviewscore   | facilities | rating  | firstname | lastname | email              | city       | zip    | country | phonenumber | address|
      | Mumbai   | 2          | 1             | 3           | 1          | 15000    | 95000    | Free cancellation | very good: 8+ | parking    | 3 stars | Abcd      | efgh     | abcdefgh@gmail.com | Coimbatore |  | India   | 9876543210  | 103, Kothari nagar, Singanallur|