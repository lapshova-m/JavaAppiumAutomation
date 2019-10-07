package lib.ui.ios.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@id='ca-watch' and contains(@class, 'wikimedia-star')]";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://*[@id='ca-watch' and contains(@class, 'wikimedia-unStar')]";
        TIME_EDITED = "xpath://*[@class = 'last-modified-bar__text']/a[1]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
