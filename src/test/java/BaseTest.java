import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collections;


public class BaseTest {

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
        // this is similar to chrome options.
        UiAutomator2Options options = new UiAutomator2Options();
        // set the emaulator to run the apk
//        options.setDeviceName("Samsung SM-A336E API 34 ");
//        options.setChromedriverExecutable("C:\\Users\\ITCae\\Downloads\\chromedriver-win64.zip\\chromedriver-win64");

        options.setDeviceName("TestingDevice");
        // provide apk and open in the device .
//        options.setApp("C:\\Users\\ITCae\\OneDrive\\Desktop\\AppiumFramework\\apps\\ApiDemos-debug.apk");
//        options.setApp("C:\\Users\\ITCae\\Downloads\\Android-MyDemoAppRN.1.3.0.build-244.apk");
//        options.setApp("C:\\Users\\ITCae\\OneDrive\\Desktop\\AppiumFramework\\apps\\General-Store.apk");
        options.setApp("C:\\Users\\ITCae\\Downloads\\kadepay-develop-app.apk");


        // Initialize the AndroidDriver with the URL of the Appium server and the specified options
        // The server URL should point to where Appium is running (usually on port 4723)
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    public void longPress(WebElement ele) {
        // all these are javascrpit functions
        // mobile: longClickGesture
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000
                ));
    }

    public void scrollToEnd() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 1.0
            ));
        } while (canScrollMore);
    }

    public void swipeByJS(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }


    public void tap(AndroidDriver driver, WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centreOfEle = getCentreOfElement(location, size);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                // go to the center of element
                // (wait for seconds , origin , x and y )
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfEle))
                // just similar to click left on mouse
                // finger press
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                // after wait for 2 sec
                // finger release
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        driver.perform(Collections.singletonList(sequence));
    }

    public void doubleTap(AndroidDriver driver, WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centreOfEle = getCentreOfElement(location, size);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)

                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfEle))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // press
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg())) // release
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // press
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg())) ; // release

        driver.perform(Collections.singletonList(sequence));
    }


    public void longPress(AndroidDriver driver, WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centreOfEle = getCentreOfElement(location, size);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)

                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfEle))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // press
                .addAction(new Pause(finger1, Duration.ofSeconds(2)))  // change in milliseconds to seconds.
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // release


        driver.perform(Collections.singletonList(sequence));
    }

    public Point getCentreOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth() / 2,
                location.getY() + size.getHeight() / 2);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        service.stop();
    }

}
