package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath:(//XCUIElementTypeSearchField[@name=\"Search Wikipedia\"])[1]";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeLink[(contains(@name, '{SUBSTRING_TITLE}')) and (contains(@name, '{SUBSTRING_DESCRIPTION}'))]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        LIST_ITEMS_IN_RESULT = "xpath://XCUIElementTypeLink";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
