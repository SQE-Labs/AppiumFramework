import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class EcommerceTest extends BaseTest {
    String scrollableUiAutomator = "";

    @Test(enabled = false)
    public void fillForm() {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Tester");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        scrollableUiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"India\"));";
        driver.findElement(AppiumBy.androidUIAutomator(scrollableUiAutomator));
        driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");


    }

    @Test(enabled = false)
    public void scrollAndSelectShoes() {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Tester");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        scrollableUiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"India\"));";
        driver.findElement(AppiumBy.androidUIAutomator(scrollableUiAutomator));
        driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        scrollableUiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Jordan 6 Rings\"));";
        driver.findElement(AppiumBy.androidUIAutomator(scrollableUiAutomator));
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

      String lastPageText =  driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
      Assert.assertEquals(lastPageText,"Jordan 6 Rings");


    }

    @Test(enabled = false)
    public void verifyTotalAmmount() {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Tester");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        scrollableUiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"India\"));";
        driver.findElement(AppiumBy.androidUIAutomator(scrollableUiAutomator));
        driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        scrollableUiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Jordan 6 Rings\"));";
        driver.findElement(AppiumBy.androidUIAutomator(scrollableUiAutomator));
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

       List<WebElement> prodPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
       int productCount = prodPrices.size();
    double sum =0 ;
       for(int i =0 ;i<productCount ; i++){
           String ammount  = prodPrices.get(i).getText();
           Double price = Double.parseDouble(ammount.substring(1));
           sum+=price;

       }
        System.out.println(sum);

       String totalAmmount =driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double newString = Double.parseDouble(totalAmmount.substring(1));
        System.out.println(newString);
        Assert.assertEquals(sum ,newString);

        WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));

        longPress(ele);
        Assert.assertTrue(driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).isDisplayed());
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

    }

    @Test(enabled = true)
    public void hybridApp() throws InterruptedException {
        {
            driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Tester");
            driver.hideKeyboard();
            driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
            scrollableUiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"India\"));";
            driver.findElement(AppiumBy.androidUIAutomator(scrollableUiAutomator));
            driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
            driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
            scrollableUiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Jordan 6 Rings\"));";
            driver.findElement(AppiumBy.androidUIAutomator(scrollableUiAutomator));
            driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
            driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
            driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

            List<WebElement> prodPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
            int productCount = prodPrices.size();
            double sum =0 ;
            for(int i =0 ;i<productCount ; i++){
                String ammount  = prodPrices.get(i).getText();
                Double price = Double.parseDouble(ammount.substring(1));
                sum+=price;

            }
            System.out.println(sum);

            String totalAmmount =driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
            Double newString = Double.parseDouble(totalAmmount.substring(1));
            System.out.println(newString);
            Assert.assertEquals(sum ,newString);

            WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));

            longPress(ele);
            Assert.assertTrue(driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).isDisplayed());
            driver.findElement(By.id("android:id/button1")).click();
            driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
            Thread.sleep(5000);
           // after this we land on web page

            Set<String> contexts = driver.getContextHandles();
            for (String context : contexts){
                System.out.println(context);
            }
            //NATIVE_APP
            //WEBVIEW_com.androidsample.generalstore

            // switch from app to google .
            // app to webview  just change the context

            // switch to web view
            driver.context("WEBVIEW_com.androidsample.generalstore");
            // use normal webviews locators
            driver.findElement(By.name("q")).sendKeys("youtube");
            driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            driver.pressKey(new KeyEvent(AndroidKey.BACK));

            //back to andrpid app
            Thread.sleep(5000);
            driver.context("NATIVE_APP");


        }
    }


}
