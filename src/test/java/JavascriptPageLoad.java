import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.JavascriptExecutor;

public class JavascriptPageLoad{

    @Test
    public void test() {

        //Firefox 47 and above
//       //System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/lib/geckodriver");
        System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"/lib/geckodriver");
        
        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.get("http://thoughtworks.com");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Long LoadTime = (Long) js.executeScript("return (window.performance.timing.domComplete - window.performance.timing.navigationStart)");
        System.out.println("The time taken for the page to load :"+LoadTime+"ms.");
        driver.close();
        driver.quit();
    }


}
