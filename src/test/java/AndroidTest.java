import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AndroidTest extends BaseTest {


    @Test(enabled = false)
    public void wifiSettings() throws InterruptedException {

        // Appium code -> appium server -> Mobile
        // xpath , id , accessibilityId ,className , androidUIAutomator.

        //tagname[@attribute='value']


        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String actualTittle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(actualTittle, "WiFi settings");
        driver.findElement(By.id("android:id/edit")).sendKeys("Testing Wifi");
        driver.findElement(By.id("android:id/button1")).click();


    }

    @Test(enabled = true, description = "for long press gesture")
    public void longPress() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        longPress(ele);
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Sample menu']")).isDisplayed());
    }

    @Test(enabled = false, description = "scroll gesture using Appium ")
    public void scrollGestureByAppium() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(3000);

        // scroll using appium ..
        String scrollableUiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"WebView\"));";
        driver.findElement(AppiumBy.androidUIAutomator(scrollableUiAutomator));

        // using java script one disadvantage is
        // js use the coordinate to stop the scroll function not a text .

    }

    @Test(enabled = false, description = "scroll gesture using javascrpit")
    public void scrollGestureByJS() throws InterruptedException {

        // using java script exec ...
        // if use scroll until end then use the javascrpit function otherwise use Appium .
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(3000);
        scrollToEnd();
    }

    @Test(enabled = false, description = "scroll gesture using javascrpit")
    public void swipeGesture() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        // check when i land on this page initially first photo is selected ..
        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true");

        // swipe with js

        swipeByJS(firstImage, "left");

        // Wait for a moment to allow the swipe to take effect
        Thread.sleep(1000); // Adjust as needed
        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");


    }


    @Test(enabled = false, description = "dragAndDrop gesture using javascrpit")
    public void dragAndDrop() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement element = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));


        // u need to find x and y coordinate by using uiAutomator.
        // u need to find x and y coordinate by using uiAutomator.
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", 615,
                "endY", 619
        ));
        Thread.sleep(3000);
        String txt = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(txt, "Dropped!");
    }


    @Test(enabled = false, description = "scroll gesture using javascrpit")
    public void miscellaneousActivity() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(By.id("android:id/checkbox")).click();

        // Rotate device to landscape mode .
        DeviceRotation landscape = new DeviceRotation(0, 0, 90);
        driver.rotate(landscape);
        Thread.sleep(3000);
        driver.rotate(landscape);

        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String actualTittle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(actualTittle, "WiFi settings");
        System.out.println("PassAssertion");
        // copy to clipboard ...
        driver.setClipboardText("Testing Wifi1");

        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(3000);
        // This is the some key events ...
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        Thread.sleep(3000);


    }


    @Test(enabled = false, description = "scroll gesture using javascrpit")
    public void miscellaneousActivity2() throws InterruptedException {
        Thread.sleep(3000);

        // App package and activity name ..

        // io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies
        Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
//        driver.startActivity(activity);

        // another way to execute
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of("intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
                ));

        driver.findElement(By.id("android:id/checkbox")).click();

        // Rotate device to landscape mode .
        DeviceRotation landscape = new DeviceRotation(0, 0, 90);
        driver.rotate(landscape);
        Thread.sleep(3000);
        driver.rotate(landscape);

        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String actualTittle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(actualTittle, "WiFi settings");
        System.out.println("PassAssertion");
        // copy to clipboard ...
        driver.setClipboardText("Testing Wifi1");

        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(3000);
        // This is the some key events ...
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        Thread.sleep(3000);


    }


}

