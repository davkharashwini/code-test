package glue;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.List;

public class GoogleSteps {

    @Given("url {string} is launched")
    public void launchUrl(String url) {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    private static void acceptCookiesIfWarned() {
        try {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    @When("About page is shown")
    public void pageShown() {
        W.get().driver.findElement(By.linkText("About")).click();
    }

    @Then("page displays {string}")
    public void pageDisplays(String expectedText) {
        WebElement bodyElement = W.get().driver.findElement(By.tagName("body"));
        Assert.assertTrue(bodyElement.getText().contains(expectedText));
    }

    @When("searching for {string}")
    public void searchByText(String query) {
        W.get().driver.switchTo().frame(0);
        W.get().driver.findElement(By.xpath("//button[text()='No thanks']")).click();
        W.get().driver.switchTo().defaultContent();
        WebElement searchBox = W.get().driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    @Then("results contain {string}")
    public void findSearchResult(String expectedText) {
        List<WebElement> results = W.get().driver.findElements(By.cssSelector(".LC20lb.DKV0Md"));
        for(WebElement e: results){
            String str = e.getText();
            if(str.contains(expectedText)){
                Assert.assertEquals(str,expectedText);
                break;
            }
        }
    }

    @Then("result stats are displayed")
    public void validateResultDisplayed() {
        WebElement resultStats = W.get().driver.findElement(By.cssSelector("div#result-stats"));
        Assert.assertNotNull(resultStats.getAttribute("innerHTML"));
    }

    @Then("number of {string} is more than {int}")
    public void validateResultsAndSeconds(String type, int expectedNumber) {
        WebElement resultStats = W.get().driver.findElement(By.cssSelector("div#result-stats"));
        String statsText = resultStats.getAttribute("innerHTML");
        String[] parts = statsText.split(" ");
        if (type.equals("results")) {
            String resultsStr = parts[1].replace(",", "");
            long resultsCount = Long.parseLong(resultsStr);
            Assert.assertTrue(resultsCount > expectedNumber);
        } else if (type.equals("seconds")) {
            String secondsStr = parts[3].replace("(", "").replace(")", "").replace("s", "");
            double seconds = Double.parseDouble(secondsStr);
            Assert.assertTrue(seconds > expectedNumber);
        }
    }
}
