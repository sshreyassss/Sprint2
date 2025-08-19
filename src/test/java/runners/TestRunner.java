package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {  "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "testng:target/testng-cucumber.xml"}

)
public class TestRunner extends AbstractTestNGCucumberTests {
}

