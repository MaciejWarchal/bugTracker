package com.example.bugtracker.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    @Given("Open login page")
    public void open_login_page() {
        System.out.println("Open login page");

    }
    @When("User enters username and password")
    public void user_enters_username_and_password() {
        System.out.println("User enters username and password");

    }
    @When("Clics on login button")
    public void clics_on_login_button() {
        System.out.println("Clics on login button");

    }
    @Then("The validation message is displayed")
    public void the_validation_message_is_displayed() {
        System.out.println("The validation message is displayed");

    }
}
