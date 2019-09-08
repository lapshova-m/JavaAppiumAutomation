package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String titleBeforeRotation = articlePageObject.getArticleTitle();

        try {
            this.rotateScreenLandscape();
            String titleAfterRotation = articlePageObject.getArticleTitle();

            assertEquals("Article title have been changes after screen rotation",
                    titleBeforeRotation, titleAfterRotation);

            this.rotateScreenPortrait();
            String titleAfterSecondRotation = articlePageObject.getArticleTitle();

            assertEquals("Article title have been changes after screen rotation",
                    titleBeforeRotation, titleAfterSecondRotation);
        } finally {
            this.rotateScreenPortrait();
        }
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String searchSubstring = "Object-oriented programming language";
        searchPageObject.waitForSearchResult(searchSubstring);
        this.backgroundApp(2);
        searchPageObject.clickByArticleWithSubstring(searchSubstring);
    }
}
