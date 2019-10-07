package lib.ui.ios.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListsPageObject {


    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        ARTICLE_BY_DESCRIPTION_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{DESCRIPTION}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//li[contains(@title, '{TITLE}')]//div[contains(@class, 'watched')]";
        TIME_EDITED_TPL = "xpath://a[h3[contains(text(), '{TITLE}')]]//*[@class='last-modified-bar__text']";
    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
