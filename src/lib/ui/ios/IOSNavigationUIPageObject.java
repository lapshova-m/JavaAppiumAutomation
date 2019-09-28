package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUIPageObject extends NavigationUI {

    static {
        MY_LISTS_LINK = "id:Saved";
        MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
    }

    public IOSNavigationUIPageObject(AppiumDriver driver) {
        super(driver);
    }
}
