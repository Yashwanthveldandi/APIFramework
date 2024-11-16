package com.pages;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Core {

    // Report-related variables
    static ExtentHtmlReporter htmlReporter = null;
    public static ExtentReports extent = null;
    public static ExtentTest test = null;
    public static String reportPath = null;

    // Test Configuration
    static String key = "Bar12345Bar12345";
    static String initVector = "RandomInitVector";

    @BeforeSuite
    public static void beforeSuite() {
        // Reset progress before starting tests
        resetProgress();
    }

    @AfterSuite
    public static void endTestProcess() {
        // Flush the report at the end of the test suite
        extent.flush();

        // Optionally, open the report file after the suite completes
        if (Common.readProperty("./src/main/resources/progress.properties", "reportOpen").equals("true")) {
            // Open the generated report file (if the property is set to true)
            try {
                Desktop desktop = Desktop.getDesktop();
                File file = new File(reportPath);
                if (file.exists()) {
                    desktop.open(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to create and initialize the test report
    public static void createReport() {
        reportPath = System.getProperty("user.dir") + "/reports/ExecutionReport_" + getCurrentDateTime()
                .replace("/", "_").replace(":", "_").replace("[", "").replace("] ", "").replace(" ", "_");
        new File(reportPath).mkdir();
        new File(reportPath + "/files").mkdir();

        htmlReporter = new ExtentHtmlReporter(reportPath + "/extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    // Method to generate a new test case report
    public static void generateReports(String testCase) {
        test = extent.createTest(testCase);
    }

    // Method to log messages in the report
    public static void logReport(Status status, String message, boolean displayConsole) {
        test.log(status, message);
        if (displayConsole) {
            System.out.println(getCurrentDateTime() + message);
        }
    }

    // Method to handle test result logging
    public static void endOfTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logReport(Status.FAIL, "Test Failed: " + result.getThrowable(), true);
        } else if (result.getStatus() == ITestResult.SKIP) {
            logReport(Status.SKIP, "Test Skipped: " + result.getThrowable(), true);
        } else {
            logReport(Status.PASS, "Test Passed", true);
        }

        extent.flush();
    }

//    // Method to make HTTP GET requests (for API testing)
//    public static String makeGetRequest(String url) {
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            HttpGet request = new HttpGet(url);
//            try (CloseableHttpResponse response = httpClient.execute(request)) {
//                int statusCode = response.getStatusLine().getStatusCode();
//                String responseBody = EntityUtils.toString(response.getEntity());
//
//                // Log the response status and body
//                logReport(Status.INFO, "Response Code: " + statusCode, true);
//                logReport(Status.INFO, "Response Body: " + responseBody, true);
//
//                if (statusCode >= 200 && statusCode < 300) {
//                    logReport(Status.PASS, "API request successful", true);
//                } else {
//                    logReport(Status.FAIL, "API request failed with status: " + statusCode, true);
//                }
//
//                return responseBody;
//            }
//        } catch (IOException e) {
//            logReport(Status.FAIL, "Error occurred during GET request: " + e.getMessage(), true);
//            return null;
//        }
//    }

    // Helper method to get current date and time as a formatted string
    public static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return "[" + dtf.format(now) + "] ";
    }

    // Helper method to reset test progress (used for tracking test statuses)
    private static void resetProgress() {
        Common.writeProperty("./src/main/resources/progress.properties", "iterationComplete", "0");
        Common.writeProperty("./src/main/resources/progress.properties", "iterationAll", "0");
        Common.writeProperty("./src/main/resources/progress.properties", "overallComplete", "0");
        Common.writeProperty("./src/main/resources/progress.properties", "overallAll", "0");
        Common.writeProperty("./src/main/resources/progress.properties", "pass", "0");
        Common.writeProperty("./src/main/resources/progress.properties", "fail", "0");
        Common.writeProperty("./src/main/resources/progress.properties", "skip", "0");
        Common.writeProperty("./src/main/resources/progress.properties", "reportOpen", "false");
    }

    // Helper method to track test progress and update the progress properties
    private static void addOneForProgress(String property) {
        Common.writeProperty("./src/main/resources/progress.properties", property, Integer.toString(
                1 + Integer.parseInt(Common.readProperty("./src/main/resources/progress.properties", property))));
    }

    // Example method to send a POST request (you can modify for other HTTP methods as needed)
    public static String makePostRequest(String url, String jsonPayload) {
        // Implement POST request logic here (e.g., using Apache HttpClient or HttpURLConnection)
        return "";
    }

    // Method to log progress in a text file after the test run
    public static void htmlReader() {
        String passedCount = "0";  // This is just an example, replace with actual logic for reading passed count from report
        String failedCount = "0";  // Replace with actual failed count logic

        try {
            PrintWriter writer = new PrintWriter(reportPath + "/TestResults.txt", "UTF-8");
            writer.println("Passed Count: " + passedCount);
            writer.println("Failed Count: " + failedCount);
            writer.println("Test Execution Completed.");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // A utility method to wait for a condition to be true (replace with relevant API wait logic)
    public static void waitUntilCondition(String condition) throws InterruptedException {
        // Simulate waiting for an API condition to be met
        Thread.sleep(2000);  // Replace with actual condition check
    }

    // Utility method to reset a progress property value (if needed)
    public static void resetProgressProperty(String propertyName) {
        Common.writeProperty("./src/main/resources/progress.properties", propertyName, "0");
    }
}
