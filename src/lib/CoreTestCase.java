package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp () throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = getDriverByPlatformEnv(capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception{
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (platform) {
            case PLATFORM_ANDROID:
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", "AndroidTestDevice");
                capabilities.setCapability("platformVersion", "8.0");
                capabilities.setCapability("automationName", "Appium");
                capabilities.setCapability("appPackage", "org.wikipedia");
                capabilities.setCapability("appActivity", ".main.MainActivity");
                capabilities.setCapability("app", "/Users/sasha/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia.apk");
                break;
            case PLATFORM_IOS:
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("deviceName", "iPhone SE");
                capabilities.setCapability("platformVersion", "11.4");
                capabilities.setCapability("app", "/Users/alex/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
                break;
            default:
                throw new Exception("Cannot run platform from env variable. Platform value " + platform);
        }

        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv(DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");
        AppiumDriver driver;

        switch (platform) {
            case PLATFORM_ANDROID:
                driver = new AndroidDriver(new URL(AppiumURL), capabilities);
                break;
            case PLATFORM_IOS:
                driver = new IOSDriver(new URL(AppiumURL), capabilities);
                break;
            default:
                throw new Exception("Cannot create driver for platform " + platform);
        }

        return driver;
    }
}
