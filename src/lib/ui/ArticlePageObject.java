package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_EXIST_LIST_NAME_TPL = "xpath://*[@text = '{FOLDER_NAME}']",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
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

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
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

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link", 5);
    }
 }
