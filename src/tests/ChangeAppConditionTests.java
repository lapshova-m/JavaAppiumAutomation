package tests;

import lib.CoreTestCase;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
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
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String searchSubstring = "Object-oriented programming language";
        searchPageObject.waitForSearchResult(searchSubstring);
        this.backgroundApp(2);
        searchPageObject.clickByArticleWithSubstring(searchSubstring);
    }
}
