import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp () throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/marina/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchInputText() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementPresent(By.xpath("//*[contains(@text, 'Search…')]"), "Cannot find search input");
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);

//        Looking for the word "Java"
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

//        Checking that result of search is present
        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"),
                "Cannot find results of searching",
                15);

//        Cancel search
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                5);

//        Checking that result of search isn't present
        waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list"),
                "Cannot find results of searching",
                5);
    }

    @Test
    public void testWordsInSearchList() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);

//        Looking for the word "Java"
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

//        Checking word in all element of the list
        List<WebElement> searchElements =  waitForListOfElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find list of search result", 15);
        for (WebElement element : searchElements) {
            Assert.assertTrue("The title " + element.getAttribute("text") + " doesn't contain 'Java'",
                    element.getAttribute("text").toLowerCase().contains("java"));
        }
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String searchString = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchString,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' title",
                5);

        By titleLocator = By.id("org.wikipedia:id/view_page_title_text");

        String titleOfFirstArticle = waitForElementAndGetAttribute(
                titleLocator,
                "text",
                "Cannot find title of the first article", 15);

//        Add the first article to the list
        By moreOptionsLocator = By.xpath("//android.widget.ImageView[@content-desc='More options']");
        waitForElementAndClick(
                moreOptionsLocator,
                "Cannot find button to open article options",
                5);

        By addToReadingListLocator = By.xpath("//*[@text='Add to reading list']");
        waitForElementAndClick(
                addToReadingListLocator,
                "Cannot find option to add article to reading list",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5);

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5);

        String nameOfFolder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                nameOfFolder,
                "Cannot put text into articles folder input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5);

//        Find the second article and add it to the list
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchString,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[contains(@text, 'Set of several computer')]"),
                "Cannot find 'Object-oriented programming language' title",
                5);

        String titleOfSecondArticle = waitForElementAndGetAttribute(
                titleLocator,
                "text",
                "Cannot find title of the second article",
                15);

        waitForElementAndClick(
                moreOptionsLocator,
                "Cannot find button to open article options",
                5);

        waitForElementAndClick(
                addToReadingListLocator,
                "Cannot find option to add article to reading list",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + nameOfFolder + "']"),
                "Cannot find the folder " + nameOfFolder + " of articles",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5);

//        Open list of articles
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My lists",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text='" + nameOfFolder + "']"),
                "Cannot find created folder",
                5);

//        Delete the first article and check the second one
        swipeElementToLeft(
                By.xpath("//*[@text='" + titleOfFirstArticle + "']"),
                "Cannot find saved the first article");

        waitForElementAndClick(
                By.xpath("//*[@text='" + titleOfSecondArticle + "']"),
                "There isn't the second article after deleting on the first one",
                5);

        String titleOfTheLastArticle = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text", "Cannot find title of article",
                15);

        Assert.assertEquals("Article title have been changes after opening from list",
                titleOfSecondArticle, titleOfTheLastArticle);
    }

    @Test
    public void testTitleOfArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String searchString = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchString,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' title",
                5);

        By titleLocator = By.id("org.wikipedia:id/view_page_title_text");
        assertElementPresent(titleLocator, "We haven't found title in the page");
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String searchLine = "Java";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                searchLine,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchLine,
                15);

        String titleBeforeRotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text", "Cannot find title of article", 15);

        try {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            String titleAfterRotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text777"),
                    "text", "Cannot find title of article", 15);

            Assert.assertEquals("Article title have been changes after screen rotation",
                    titleBeforeRotation, titleAfterRotation);

            driver.rotate(ScreenOrientation.PORTRAIT);

            String titleAfterSecondRotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                    "text", "Cannot find title of article", 15);

            Assert.assertEquals("Article title have been changes after screen rotation",
                    titleBeforeRotation, titleAfterSecondRotation);
        } finally {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private List<WebElement> waitForListOfElementsPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int)(size.height * 0.8);
        int endY = (int)(size.height * 0.2);
        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private  void assertElementNotPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + "\n" + errorMessage);
        }
    }

    private void assertElementPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements == 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(defaultMessage + "\n" + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
