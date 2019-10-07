package lib.ui;

import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        DESCRIPTION,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_EXIST_LIST_NAME_TPL,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        CLOSE_POPUP_AFTER_SAVING_BUTTON,
        TIME_EDITED;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getExistListNameElement(String folderName) {
        return MY_EXIST_LIST_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE,
                "Cannot find article title on page", 15);
    }

    public WebElement waitForDescriptionElement() {
        return this.waitForElementPresent(DESCRIPTION, "Cannot find article description on page", 15);
    }

    public WebElement waitForTimeElement() {
        return this.waitForElementPresent(TIME_EDITED, "Cannot find time when article was edited");
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else if (Platform.getInstance().isIOS()){
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
        }
    }

    public String getTimeWhenArticleWasEdited() {
        WebElement timeElement = waitForTimeElement();
        if (Platform.getInstance().isMw()) {
            return timeElement.getAttribute("text");
        } else {
            System.out.println("Method getTimeWhenArticleWasEdited does nothing for platform " + Platform.getInstance().getPlatformVar());
            return null;
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyNewList(String nameOfFolder) {
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options", 5);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list", 5);

        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay", 5);

        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                nameOfFolder,
                "Cannot put text into articles folder input", 5);

        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot press OK button", 5);
    }

    public void addArticleToMyExistList(String nameOfFolder) {
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options", 5);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list", 5);

        this.waitForElementAndClick(getExistListNameElement(nameOfFolder),
                "Cannot find the folder " + nameOfFolder + " of articles", 15);
    }

    public void addArticleToMySaved() {
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find options to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved", 10);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button to add an article to saved list after removing it from this list before");
        }
    }

    public void ClosePopupAfterSaving() {
        this.waitForElementAndClick(CLOSE_POPUP_AFTER_SAVING_BUTTON, "Cannot find popup window after saving new article", 5);

    }

    public void closeArticle() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link", 5);
        } else {
            System.out.println("Method closeArticle does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
 }
