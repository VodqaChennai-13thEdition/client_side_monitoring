import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

//import org.browsermob.proxy.ProxyServer;


/**
 * Created by Vijayaragavan on 21/04/17.
 */
public class browser_mob {

    @Test
    public void testproxy() throws IOException {
        // start the proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

       //System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/lib/geckodriver");
        System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"/lib/geckodriver");

        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        WebDriver driver = new FirefoxDriver(capabilities);


        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // create a new HAR with the label "thoughtworks.com"
        proxy.newHar("thoughtworks.com");

        // open thoughtwoks.com
        driver.get("http://thoughtworks.com");

        // get the HAR data
        Har har = proxy.getHar();

        har.writeTo(new File("test.har"));

        driver.close();
        proxy.stop();

    }
}
