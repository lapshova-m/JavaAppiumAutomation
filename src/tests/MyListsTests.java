package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import lib.ui.*;

public class MyListsTests extends CoreTestCase {

    private static final String nameOfFolder = "Learning programming";
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyNewList(nameOfFolder);
        } else {
            articlePageObject.addArticleToMySaved();
            articlePageObject.ClosePopupAfterSaving();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }
        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticleToMyListAndDeleteOne() throws InterruptedException {
        String searchString = "Java";
        String descriptionOfFirstArticle = "Object-oriented programming language";
        String descriptionOfSecondArticle = "Set of several computer";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchString);
        searchPageObject.clickByArticleWithSubstring(descriptionOfFirstArticle);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String titleOfFirstArticle = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyNewList(nameOfFolder);
        } else {
            articlePageObject.addArticleToMySaved();
            articlePageObject.ClosePopupAfterSaving();
        }
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        if (Platform.getInstance().isAndroid()) {
            searchPageObject.typeSearchLine(searchString);
        }
        searchPageObject.clickByArticleWithSubstring(descriptionOfSecondArticle);

        articlePageObject.waitForTitleElement();
        String titleOfSecondArticle = null;
        if (Platform.getInstance().isAndroid()) {
            titleOfSecondArticle = articlePageObject.getArticleTitle();
        }
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyExistList(nameOfFolder);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }

        myListsPageObject.swipeByArticleToDelete(titleOfFirstArticle);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openSavedArticleByTitle(titleOfSecondArticle);
            String titleOfTheLastArticle = articlePageObject.getArticleTitle();

            assertEquals("Article title have been changes after opening from list",
                    titleOfSecondArticle, titleOfTheLastArticle);
        } else {
            myListsPageObject.openSavedArticleByDescription(descriptionOfSecondArticle);
        }
    }
}
