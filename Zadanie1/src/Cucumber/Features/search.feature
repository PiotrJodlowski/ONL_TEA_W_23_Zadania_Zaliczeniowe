Feature: Google search

Scenario Outline: Create new user at Mystore Test Lab

    Given an open browser with Mystore Test Lab
    Then SignIn icon is clicked
    # When Log in Page is open Click 'No account? Create one here
    # When Create account page is open fill up and click save
    When Login Page is loaded login to store
    Then Click Addresses
    When No address message is displayed click Create new address
    Then Fill up New address form with <alias>, <address>, <city>, <postal>, <phone>
    When Address is created verify results with <alias>, <address>, <city>, <postal>, <phone>
#    And close browser

    Examples:
    |alias |address | city  |  postal    |  phone     |
    |Pan   |Polna   | Lodz  |  93200     |  555666222 |

