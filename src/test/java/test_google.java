import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class test_google {

    @Test
    public void test() {

        long startTime = 0;
        long stopTime = 0;



        StopWatch time = new StopWatch();
        //Firefox 47 and above
       //System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/lib/geckodriver");
        System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"/lib/geckodriver");
        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);


        startTime = System.currentTimeMillis();

        driver.get("http://www.google.com");

        stopTime = System.currentTimeMillis();


        System.out.println("*****Time taken"+ (stopTime-startTime));



        driver.close();
        driver.quit();
    }


}
