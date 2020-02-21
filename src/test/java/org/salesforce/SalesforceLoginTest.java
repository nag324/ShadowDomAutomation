package org.salesforce;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.BatchInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URI;
import java.net.URISyntaxException;


public  class SalesforceLoginTest {
    static private String  appName  = "Salesforce";

    // change the value of testName so that it has a unique value on your Eyes system
    static private String  testName = "Salesforce Login";

    // if you have a dedicated Eyes server, set the value of the variable serverURLstr to your URL
    static String serverURLstr = "https://eyesapi.applitools.com";

    //set the value of runAsBatch to true so that the tests run as a single batch
    static private Boolean runAsBatch = true;

    // set the value of changeTest to true to introduce changes that Eyes will detect as mismatches
    static private Boolean changeTest = false;

    static private String  weburl = "https://nagsftest-dev-ed.lightning.force.com/";

    public static void main(String[] args) {
        URI serverURL;
        try {
            serverURL = new URI(serverURLstr);
        } catch (URISyntaxException e) {
            System.out.println("URI Exception ");
            return;
        }
        Eyes eyes = new Eyes();
        eyes.setServerUrl(serverURL);
        setup(eyes);
        String path = "C://chrome//chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        WebDriver innerDriver = new ChromeDriver();  // Open a Chrome browser.
        innerDriver.manage().window().maximize();
       // RectangleSize viewportSizeLandscape = new RectangleSize(/*width*/ 1024, /*height*/ 768 );
      //  RectangleSize viewportSizePortrait = new RectangleSize(/*width*/ 500, /*height*/ 900 );
        SalesforceLogin(innerDriver,eyes);

        innerDriver.quit();
        innerDriver.close();

   }

    static private void SalesforceLogin(WebDriver innerDriver, Eyes eyes) {
        // Start the test and set the browser's viewport size
        WebDriver driver = eyes.open(innerDriver, appName, testName);
        try {
            driver.get(weburl);
            driver.findElement(By.name("username")).sendKeys("nag@nznetwork.com");
            eyes.checkWindow("Salesforce Login");                 // Visual checkpoint 1

            /*driver.findElement(By.id("name")).sendKeys("My Name"); //enter the name
            eyes.checkWindow("After enter name");                  // Visual checkpoint 2

            driver.findElement(By.tagName("button")).click();      // Click the  button
            eyes.checkWindow("After Click"); */                      // Visual checkpoint 3

            TestResults result = eyes.close(false); //false means don't thow exception for failed tests
            handleResult(result);
        } finally {
            eyes.abortIfNotClosed();
        }
    }

    static private  void handleResult(TestResults result) {
        String resultStr;
        String url;
        if (result == null) {
            resultStr = "Test aborted";
            url = "undefined";
        } else {
            url = result.getUrl();
            int totalSteps = result.getSteps();
            if (result.isNew()) {
                resultStr = "New Baseline Created: " + totalSteps + " steps";
            } else if (result.isPassed()) {
                resultStr = "All steps passed:     " + totalSteps + " steps";
            } else {
                resultStr = "Test Failed     :     " + totalSteps + " steps";
                resultStr += " matches=" +  result.getMatches();      /*  matched the baseline */
                resultStr += " missing=" + result.getMissing();       /* missing in the test*/
                resultStr += " mismatches=" + result.getMismatches(); /* did not match the baseline */
            }
        }
        resultStr += "\n" + "results at " + url;
        System.out.println(resultStr);
    }


    static private void setup(Eyes eyes) {
        String apiKey = "KFT6wNjOm4BGeK4JZS9OVAYwOF4nc1119I99pYW5MmP4YM110";
        eyes.setApiKey(apiKey);
        if (runAsBatch) {
            BatchInfo batchInfo = new BatchInfo("Salesforce");
            eyes.setBatch(batchInfo);
        }

        //eliminate artifacts caused by a blinking cursor - on by default in latest SDK
        eyes.setIgnoreCaret(true);
    }
}

