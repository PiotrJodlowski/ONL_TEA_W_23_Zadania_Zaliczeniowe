Feature: MyStore Test Lab

Scenario Outline: Purchase test at Mystore Test Lab

    Given an open browser with Mystore Test Lab
    Then SignIn icon is clicked
    When Login Page is loaded login to store
    # Then Click Addresses
    # When No address message is displayed click Create new address
    # Then Fill up New address form with <alias>, <address>, <city>, <postal>, <phone>
    Then Go to clothes in store and buy Hummingbird Printed Sweater <Size> and <Quantity>
    Then Verify address results with <alias>, <address>, <city>, <postal>, <phone>
    Then Choose Shipping Method PrestaShop
    Then Choose Payment Pay by Check
    And close browser

    Examples:
    |alias |address | city  |  postal    |  phone     | Size | Quantity |
    |Pan   |Polna   | Lodz  |  93200     |  555666222 |  L   |  5       |

