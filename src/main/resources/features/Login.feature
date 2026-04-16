Feature: SauceDemo Login Functionality
    Scenario: Successful login with valid credentials
        Given the user is on the login page
        When the user enters valid username and password
        And clicks the login button
        Then the user should be redirected to the inventory page
        And the page header should display Products
    
    Scenario: Login failure with incorrect credentials
        Given the user is on the login page
        When the user enters invalid username and password
        And clicks the login button
        Then the user should see an error message

    # Scenario: Successful logout from the application
    #     Given the user is logged in to the application
    #     When the user clicks the logout button from the sidebar menu
    #     Then the user should be redirected to the login page