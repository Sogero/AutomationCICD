package joshuaacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports getReportObject() {

	    String path = System.getProperty("user.dir") + "//test-output//index.html";
	    ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	    reporter.config().setEncoding("utf-8");
	    reporter.config().setTheme(Theme.STANDARD);
	    reporter.config().setReportName("Web Automation Results");
	    reporter.config().setDocumentTitle("Test Results");

	    extent = new ExtentReports();
	    extent.attachReporter(reporter);
	    extent.setSystemInfo("Environment", "SIT-QA");
	    extent.setSystemInfo("Tester", "Joshua Mendoza");

	    return extent;
	}


}
