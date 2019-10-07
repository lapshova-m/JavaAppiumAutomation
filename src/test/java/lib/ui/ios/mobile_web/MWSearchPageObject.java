package lib.ui.ios.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:div>button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@title][//*[contains(text(), '{SUBSTRING_TITLE}')] and //*[contains(text(), '{SUBSTRING_DESCRIPTION}')]]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        LIST_ITEMS_IN_RESULT = "xpath://*[@class='page-list thumbs actionable']";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
