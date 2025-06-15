package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="\\src\\test\\java\\cucumber",glue="joshuaacademy.stepDefinitions", monochrome=true, 
plugin = {"html:targer\\cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	

}
