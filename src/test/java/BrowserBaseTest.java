import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BrowserBaseTest {

    AndroidDriver driver;
    AppiumDriverLocalService service;

    @BeforeClass
    public void configureApp() throws URISyntaxException, MalformedURLException {
        // start the server
        // appiumServiceBuilder internally extends the AppiumDriverLocalService
        //  create session ...
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\ITCae\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();

        // with IPAddress we dont require this format
        // withIPAddress it automatically concate the "http://"
        // http://127.0.0.1:4723 -> withIPAddress("127.0.0.1")
        service.start();
        // Create an instance of UiAutomator2Options to specify the desired capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        // set the emaulator to run the apk
        options.setDeviceName("Samsung SM-A336E API 34 ");
        options.setChromedriverExecutable("C:\\Users\\ITCae\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        options.setCapability("browserName", "Chrome");
//        options.setDeviceName("TestingDevice");
        // provide apk and open in the device .
//        options.setApp("C:\\Users\\ITCae\\OneDrive\\Desktop\\AppiumFramework\\apps\\General-Store.apk");
//        options.setApp("C:\\Users\\ITCae\\OneDrive\\Desktop\\AppiumFramework\\apps\\General-Store.apk");


        // Initialize the AndroidDriver with the URL of the Appium server and the specified options
        // The server URL should point to where Appium is running (usually on port 4723)
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        service.stop();
    }

}
