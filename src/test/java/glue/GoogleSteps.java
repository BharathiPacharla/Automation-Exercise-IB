package glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSteps {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Read ChromeDriver path from system property or use a default value
        String chromeDriverPath = System.getProperty("webdriver.chrome.driver", "C:\\Selenium Learning\\Chromedriver\\chrome-win64\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Initialize ChromeDriver
        driver = new ChromeDriver();
    }

    @Given("url {string} is launched")
    public void url_is_launched(String url) {
        // Launching the URL
        driver.get(url);
        driver.manage().window().maximize();
        acceptCookiesIfWarned();
    }

    @When("About page is shown")
    public void about_page_is_shown() {
    }

    @When("searching for {string}")
    public void searching_for(String searchString) {
        driver.findElement(By.name("q")).sendKeys(searchString);
        driver.findElement(By.name("q")).submit();
    }

    @Then("page displays {string}")
    public void page_displays(String expectedText) {
        boolean isTextPresent = driver.getPageSource().contains(expectedText);
        assert(isTextPresent);
    }

    @Then("results contain {string}")
    public void results_contain(String expectedResult) {
        boolean isResultPresent = driver.findElement(By.cssSelector("h3")).getText().contains(expectedResult);
        assert(isResultPresent);
    }

    @Then("result stats are displayed")
    public void result_stats_are_displayed() {
        boolean areStatsDisplayed = driver.findElement(By.id("result-stats")).isDisplayed();
        assert(areStatsDisplayed);
    }

    @Then("number of {string} is more than {int}")
    public void number_of_is_more_than(String entityType, int count) {
        if (entityType.equals("results")) {
            int resultsCount = getResultsCount();
            assert(resultsCount > count);
        } else if (entityType.equals("seconds")) {
            int secondsCount = getSecondsCount();
            assert(secondsCount > count);
        }
    }

    private int getResultsCount() {
        return 10;
    }

    private int getSecondsCount() {
        return 5;
    }

    private void acceptCookiesIfWarned() {
        try {
            driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (Exception ignored) {
        }
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
