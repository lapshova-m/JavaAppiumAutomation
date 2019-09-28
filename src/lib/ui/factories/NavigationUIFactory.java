package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigationUIPageObject;
import lib.ui.ios.IOSNavigationUIPageObject;

public class NavigationUIFactory
{
    public static NavigationUI get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUIPageObject(driver);
        } else {
            return new IOSNavigationUIPageObject(driver);
        }
    }
}
