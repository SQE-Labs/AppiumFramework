import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowser extends BrowserBaseTest{

    @Test
    public void browserTest() throws InterruptedException {
//        driver.get("https://www.google.com");
//        System.out.println(driver.getTitle());
//        driver.findElement(By.name("q")).sendKeys("youtube");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//        Thread.sleep(5000);

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
        driver.findElement(By.cssSelector("a[routerlink*='products']")).click();
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)","");
        String txt =driver.findElement(By.cssSelector("a[href*='products/3']")).getText();
        driver.findElement(By.cssSelector("a[href*='products/3']")).click();
        Assert.assertEquals(txt , "Devops");
    }
}
