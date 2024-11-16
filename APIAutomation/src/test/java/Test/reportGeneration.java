package Test;

import java.text.ParseException;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.Core;

public class reportGeneration extends Core {
	@BeforeSuite
	public static void generateReport() {
		createReport();
	}

	@BeforeMethod
	public static void beforeMethod() throws ParseException {
	
	}

	@Test
	public void testExample() {
		
	}
}
