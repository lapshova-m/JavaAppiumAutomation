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
    private static final String
        login = "TestMarina",
        password = "MarinaTest";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyNewList(nameOfFolder);
        } else if (Platform.getInstance().isIOS()){
            articlePageObject.addArticleToMySaved();
            articlePageObject.ClosePopupAfterSaving();
        } else if (Platform.getInstance().isMw()) {
            articlePageObject.addArticleToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();

            Auth.enterLoginData(login, password);
            Auth.submitForm();

            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    articleTitle, articlePageObject.getArticleTitle());

            articlePageObject.addArticleToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }
        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticleToMyListAndDeleteOne() {
        String searchString = "Java";
        String descriptionOfFirstArticle = "bject-oriented programming language";
        String descriptionOfSecondArticle = "et of several computer";

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
        } else if (Platform.getInstance().isIOS()){
            articlePageObject.addArticleToMySaved();
            articlePageObject.ClosePopupAfterSaving();
        } else if (Platform.getInstance().isMw()) {
            articlePageObject.addArticleToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();

            Auth.enterLoginData(login, password);
            Auth.submitForm();

            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    titleOfFirstArticle, articlePageObject.getArticleTitle());

            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMw()) {
            searchPageObject.typeSearchLine(searchString);
        }
        searchPageObject.clickByArticleWithSubstring(descriptionOfSecondArticle);

        articlePageObject.waitForTitleElement();
        String titleOfSecondArticle = null;
        String whenTitleWasEdited = null;
        if (Platform.getInstance().isAndroid()) {
            titleOfSecondArticle = articlePageObject.getArticleTitle();
        }
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyExistList(nameOfFolder);
        } else {
            titleOfSecondArticle = articlePageObject.getArticleTitle();
            whenTitleWasEdited = articlePageObject.getTimeWhenArticleWasEdited();
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
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
        } else if (Platform.getInstance().isIOS()) {
            myListsPageObject.openSavedArticleByDescription(descriptionOfSecondArticle);
        } else {
            assertTrue("There is another article in list",
                    myListsPageObject.getTimeInListWhenArticleWasEdited(titleOfSecondArticle).toLowerCase().
                            contains(whenTitleWasEdited.toLowerCase()));
        }
    }
}
