package Test;

import java.text.ParseException;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.Core;

public class Test1 extends Core {
	@BeforeSuite
	public static void generateReport() {
		//createReport();
	}

	@BeforeMethod
	public static void beforeMethod() throws ParseException {
		generateReports("Test1");
	}

	@Test
	public void testExample() {
		// Log information during the test
		Reporter.log("Test started.");

		int actual = 3;
		int expected = 3;

		if (actual == expected) {
			// Mark the test as passed
			logReport(Status.PASS,"Test Passed: Values are equal.",true);
		} else {
			// Mark the test as failed
			logReport(Status.FAIL,"Test Passed: Values are equal.",true);
		}
	}
}
