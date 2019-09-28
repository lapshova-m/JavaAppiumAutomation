package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
        ARTICLE_BY_DESCRIPTION_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{DESCRIPTION}')]";
    }

    public IOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
