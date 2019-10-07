package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
        ARTICLE_BY_DESCRIPTION_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{DESCRIPTION}')]";
    }

    public IOSMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
