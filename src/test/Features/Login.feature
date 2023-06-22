

Feature: Test OrangeHrm Login Page
    Scenario: Check login with wrong credentials
        Given Open login page
        When User enters username and password
        And Clics on login button
        Then The validation message is displayed

