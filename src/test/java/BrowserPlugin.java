import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

public class BrowserPlugin{
	 @Test
    public void test() {
    	//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/lib/geckodriver");
		System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"/lib/geckodriver");
		
    	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		
		FirefoxProfile profile = new FirefoxProfile();
        File firebug = new File(System.getProperty("user.dir")+"/lib/firebug-2.0.1.xpi");
        File netExport = new File(System.getProperty("user.dir")+"/lib/netExport-0.9b7.xpi");
        
        try
        {
            profile.addExtension(firebug);
            profile.addExtension(netExport);
        }
        catch (Exception err)
        {
            System.out.println(err);
        }

        // Set default Firefox preferences
        profile.setPreference("app.update.enabled", false);

        String domain = "extensions.firebug.";

        // Set default Firebug preferences
        profile.setPreference(domain + "currentVersion", "2.0.1");
        profile.setPreference(domain + "allPagesActivation", "on");
        profile.setPreference(domain + "defaultPanelName", "net");
        profile.setPreference(domain + "net.enableSites", true);

        // Set default NetExport preferences
        profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
        profile.setPreference(domain + "netexport.showPreview", false);

        //profile.setPreference(domain + "netexport.defaultLogDir", "/Users/antosukesh/Projects/Working/Framework/MerchandiseAppPerformance/target/HAR");
        profile.setPreference(domain + "netexport.defaultLogDir", System.getProperty("user.dir")+"/HAR");
        //driver = new FirefoxDriver(profile);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        WebDriver driver =  new FirefoxDriver(capabilities);

        driver.get("http://google.com");
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }


}