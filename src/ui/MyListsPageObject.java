package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }
    /* TEMPLATES METHODS */

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        this.waitForElementAndClick(By.xpath(getFolderXpathByName(nameOfFolder)),
                "Cannot find folder by name " + nameOfFolder, 5);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {

        this.waitForElementPresent(By.xpath(getFolderXpathByName(articleTitle)),
                "Cannot find saved article by title " + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {

        this.waitForElementNotPresent(By.xpath(getFolderXpathByName(articleTitle)),
                "Saved article still present with title " + articleTitle, 15);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        this.swipeElementToLeft(By.xpath(getSavedArticleXpathByTitle(articleTitle)),
                "Cannot find saved article " + articleTitle);
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void openSavedArticleByTitle(String articleTitle) {
        this.waitForElementAndClick(By.xpath(getFolderXpathByName(articleTitle)),
                "Cannot find saved article by title " + articleTitle, 15);
    }
}
