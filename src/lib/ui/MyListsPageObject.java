package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        ARTICLE_BY_DESCRIPTION_TPL;

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getSavedArticleXpathByDescription(String articleDescription) {
        return ARTICLE_BY_DESCRIPTION_TPL.replace("{DESCRIPTION}", articleDescription);
    }
    /* TEMPLATES METHODS */

    public MyListsPageObject(AppiumDriver driver) {
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

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXPath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(articleXPath,
                "Cannot find saved article " + articleTitle);
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXPath, "Cannot find saved article");
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
}
