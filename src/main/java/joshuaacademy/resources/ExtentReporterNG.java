package joshuaacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports getReportObject() {

	    String path = System.getProperty("user.dir") + "//Reports//index.html";
	    ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	    reporter.config().setReportName("Web Automation Results");
	    reporter.config().setDocumentTitle("Test Results");

	    extent = new ExtentReports();
	    extent.attachReporter(reporter);
	    extent.setSystemInfo("Environment", "SIT-QA");
	    extent.setSystemInfo("Tester", "Joshua Mendoza");

	    return extent;
	}


}
