package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions(features="C:\\Users\\patwa\\Selenium_Training\\RecipeHackathon1\\Feature\\recipee.feature",
glue={"stepDefinitions"}, 
dryRun=false,
monochrome=true,
plugin ={"pretty:target/cucumber-pretty.txt", 
	"html:target/cucumber-html-report",
	"junit:target/cucumber-results.xml",
	"json:target/cucumber.json",
	"rerun:target/failedrerun.txt"})



public class testRun {

}
