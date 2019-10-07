package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUIPageObject extends NavigationUI {

    static {
        MY_LISTS_LINK = "id:Saved";
        MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
    }

    public IOSNavigationUIPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
