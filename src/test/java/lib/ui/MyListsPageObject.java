package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        REMOVE_FROM_SAVED_BUTTON,
        ARTICLE_BY_DESCRIPTION_TPL,
        TIME_EDITED_TPL;

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    private static String getSavedArticleXpathByDescription(String articleDescription) {
        return ARTICLE_BY_DESCRIPTION_TPL.replace("{DESCRIPTION}", articleDescription);
    }

    private static String getTimeEditedByTitle(String title) {
        return TIME_EDITED_TPL.replace("{TITLE}", title);
    }
    /* TEMPLATES METHODS */

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        this.waitForElementAndClick(getFolderXpathByName(nameOfFolder),
                "Cannot find folder by name " + nameOfFolder, 5);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementPresent(getSavedArticleXpathByTitle(articleTitle),
                "Cannot find saved article by title " + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(getSavedArticleXpathByTitle(articleTitle),
                "Saved article still present with title " + articleTitle, 15);
    }

    public WebElement waitForTimeElement(String title) {
        return this.waitForElementPresent(getTimeEditedByTitle(title), "Cannot find time when article was edited");
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXPath = getSavedArticleXpathByTitle(articleTitle);
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(articleXPath,
                    "Cannot find saved article " + articleTitle);
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(removeLocator,
                    "Cannot click button to remove article from saved",
                    10);
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXPath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void openSavedArticleByTitle(String articleTitle) {
        this.waitForElementAndClick(getSavedArticleXpathByTitle(articleTitle),
                "Cannot find saved article by title " + articleTitle, 15);
    }

    public void openSavedArticleByDescription(String articleDescription) {
        this.waitForElementAndClick(getSavedArticleXpathByDescription(articleDescription),
                "Cannot find saved article by description " + articleDescription, 15);
    }

    public String getTimeInListWhenArticleWasEdited(String title) {
        WebElement timeElement = waitForTimeElement(title);
        if (Platform.getInstance().isMw()) {
            return timeElement.getText();
        } else {
            System.out.println("Method getTimeWhenArticleWasEdited does nothing for platform " + Platform.getInstance().getPlatformVar());
            return null;
        }
    }
}
