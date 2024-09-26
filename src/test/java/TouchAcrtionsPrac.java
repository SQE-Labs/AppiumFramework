import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TouchAcrtionsPrac extends  BaseTest{
    @Test(enabled = false)
    public void tapAction() throws InterruptedException {
        // Touch Action is depreceate in new Appium version
        // Touch actions failing for android automation after upgrading from appium 2.1 to latest 2.5 version
//        TouchAction action = new TouchAction(driver);
//        WebElement ele =  driver.findElement(AppiumBy.accessibilityId("Preference"));
////        action.tap(TapOptions.tapOptions().withElement(ElementOption.element(ele))).perform();
//        action.tap(PointOption.point(252, 1471))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//                .perform();
//        Thread.sleep(3000);
        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("Preference"));
        tap(driver,openMenu);
    }

    @Test(enabled = true)
    public void doubleTap() throws InterruptedException {

        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
        doubleTap(driver,openMenu);
    }


    @Test(enabled = false)
    public void LongPress() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        longPress(driver,ele);
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Sample menu']")).isDisplayed());

    }

}
