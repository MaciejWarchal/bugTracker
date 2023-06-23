package com.example.bugtracker.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"},
        features = "src/test/Features",
        glue = "/test/java/com/example/bugtracker/stepDefinitions",
        publish = true)
public class TestRunner {

}