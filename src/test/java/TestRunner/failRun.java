package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions(features="@target/failedrerun.txt",
glue={"stepDefinitions"}, 
dryRun=false,
monochrome=true,
plugin ={"pretty", 
		"rerun:target/failedrerun.txt"},
publish=true)


public class failRun {

}
