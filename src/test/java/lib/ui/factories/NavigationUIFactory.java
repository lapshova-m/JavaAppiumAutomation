package lib.ui.factories;

import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigationUIPageObject;
import lib.ui.ios.IOSNavigationUIPageObject;
import lib.ui.ios.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory
{
    public static NavigationUI get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUIPageObject(driver);
        } else if (Platform.getInstance().isIOS()){
            return new IOSNavigationUIPageObject(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
