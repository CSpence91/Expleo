package Tests;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PageLayout {
    private static ChromeDriver driver;
    WebElement element;

    @Before
    public void openBrowser()
    {
        // Telling the system where to find the Chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\christopher.spence\\Webdrivers\\chromedriver.exe");
        //Manage Browser Window
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
        //Below code is for maximising the browser window
        //driver.manage().window().maximize();
    }

    @Test //Asserting that the page title is correct
    public void pageTitle()
    {
        String pageTitle = driver.getTitle();
        assertEquals(pageTitle, "The Internet");
    }

    @Test //Asserting the git hub url is correct
    public void gitHubLogo()
    {
        String gitHubURL = driver.findElementByXPath("/html/body/div[2]/a").getAttribute("href");
        assertEquals(gitHubURL, "https://github.com/tourdedave/the-internet");
    }

    @Test //Checking first button for Baz, Bar, Qux or Foo
    public void button()
    {
        String buttonText = driver.findElementByClassName("button").getText();
        if (buttonText.equals("foo"))
        {
            System.out.println("Button is equal to: " + buttonText + " ");
        }
        else if (buttonText.equals("baz"))
        {
            System.out.println("Button is equal to: " + buttonText);
        }
        else if (buttonText.equals("qux"))
        {
            System.out.println("Button is equal to: " + buttonText);
        }
        else if (buttonText.equals("bar"))
        {
            System.out.println("Button is equal to: " + buttonText);
        }
        else
        {
            Assert.fail("The button does not display the expected text");
        }
    }

    @Test //Checking second button for Baz, Bar, Qux or Foo
    public void buttonAlert()
    {
        String buttonAlertText = driver.findElementByCssSelector("[class='button alert']").getText();
        if (buttonAlertText.equals("foo"))
        {
            System.out.println("Button is equal to: " + buttonAlertText + " ");
        }
        else if (buttonAlertText.equals("baz"))
        {
            System.out.println("Button is equal to: " + buttonAlertText);
        }
        else if (buttonAlertText.equals("qux"))
        {
            System.out.println("Button is equal to: " + buttonAlertText);
        }
        else if (buttonAlertText.equals("bar"))
        {
            System.out.println("Button is equal to: " + buttonAlertText);
        }
        else
        {
            Assert.fail("The button does not display the expected text");
        }
    }

    @Test //Checking third button for Baz, Bar, Qux or Foo
    public void buttonSuccess()
    {
        String buttonSuccessText = driver.findElementByCssSelector("[class='button success']").getText();
        if (buttonSuccessText.equals("foo"))
        {
            System.out.println("Button is equal to: " + buttonSuccessText + " ");
        }
        else if (buttonSuccessText.equals("baz"))
        {
            System.out.println("Button is equal to: " + buttonSuccessText);
        }
        else if (buttonSuccessText.equals("qux"))
        {
            System.out.println("Button is equal to: " + buttonSuccessText);
        }
        else if (buttonSuccessText.equals("bar"))
        {
            System.out.println("Button is equal to: " + buttonSuccessText);
        }
        else
        {
            Assert.fail("The button does not display the expected text");
        }
    }

    @Test
    public void tablePresent()
    {
        int rowCount=driver.findElements(By.xpath("//*[@id='content']/div/div/div/div[2]/table/tbody/tr")).size();
        System.out.println(rowCount);
        Assert.assertEquals(rowCount, 10);
    }

@Test //Asserting button updates the canvas value
public void buttonUpdateCanvas() //Stack Overflow https://stackoverflow.com/questions/50276000/how-to-get-element-details-from-script-tag-using-javascript-in-selenium-webdrive
{
    final By SCRIPT = By.tagName("script");
    List<WebElement> scripts = new WebDriverWait(driver, 5)
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(SCRIPT));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String query = "return document.getElementsByTagName(\"script\")[arguments[0]].innerHTML;";
    System.out.println(query);

    Pattern p = Pattern.compile("canvas.strokeText\\('Answer: \\d+'");
    Pattern p2 = Pattern.compile("\\d+");

    String numberBefore = IntStream.range(0, scripts.size())
            .mapToObj(i -> (String)js.executeScript(query, i))
            .map(string -> p.matcher(string))
            .filter(m -> m.find())
            .map(m -> p2.matcher(m.group()))
            .filter(m -> m.find())
            .map(m -> m.group())
            .findFirst()
            .orElse(null);
    System.out.println(numberBefore);  // prints the number rendered on the page

    driver.findElementByCssSelector("[class='button']").click();

    final By SCRIPT1 = By.tagName("script");
    List<WebElement> scripts1 = new WebDriverWait(driver, 5)
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(SCRIPT1));
    JavascriptExecutor js2 = (JavascriptExecutor) driver;
    String query2 = "return document.getElementsByTagName(\"script\")[arguments[0]].innerHTML;";
    System.out.println(query);

    Pattern pa = Pattern.compile("canvas.strokeText\\('Answer: \\d+'");
    Pattern pa2 = Pattern.compile("\\d+");

    String numberAfter = IntStream.range(0, scripts1.size())
            .mapToObj(i -> (String)js2.executeScript(query2, i))
            .map(string -> pa.matcher(string))
            .filter(m -> m.find())
            .map(m -> pa2.matcher(m.group()))
            .filter(m -> m.find())
            .map(m -> m.group())
            .findFirst()
            .orElse(null);
    System.out.println(numberAfter);  // prints the number rendered on the page
    assertNotEquals(numberBefore, numberAfter);
}

    @Test //Asserting button alert updates the canvas value
    public void buttonAlertUpdateCanvas() //Stack Overflow https://stackoverflow.com/questions/50276000/how-to-get-element-details-from-script-tag-using-javascript-in-selenium-webdrive
    {
        final By SCRIPT = By.tagName("script");
        List<WebElement> scripts = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(SCRIPT));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String query = "return document.getElementsByTagName(\"script\")[arguments[0]].innerHTML;";
        System.out.println(query);

        Pattern p = Pattern.compile("canvas.strokeText\\('Answer: \\d+'");
        Pattern p2 = Pattern.compile("\\d+");

        String numberBefore = IntStream.range(0, scripts.size())
                .mapToObj(i -> (String)js.executeScript(query, i))
                .map(string -> p.matcher(string))
                .filter(m -> m.find())
                .map(m -> p2.matcher(m.group()))
                .filter(m -> m.find())
                .map(m -> m.group())
                .findFirst()
                .orElse(null);
        System.out.println(numberBefore);  // prints the number rendered on the page

        driver.findElementByCssSelector("[class='button alert']").click();

        final By SCRIPT1 = By.tagName("script");
        List<WebElement> scripts1 = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(SCRIPT1));
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        String query2 = "return document.getElementsByTagName(\"script\")[arguments[0]].innerHTML;";
        System.out.println(query);

        Pattern pa = Pattern.compile("canvas.strokeText\\('Answer: \\d+'");
        Pattern pa2 = Pattern.compile("\\d+");

        String numberAfter = IntStream.range(0, scripts1.size())
                .mapToObj(i -> (String)js2.executeScript(query2, i))
                .map(string -> pa.matcher(string))
                .filter(m -> m.find())
                .map(m -> pa2.matcher(m.group()))
                .filter(m -> m.find())
                .map(m -> m.group())
                .findFirst()
                .orElse(null);
        System.out.println(numberAfter);  // prints the number rendered on the page
        assertNotEquals(numberBefore, numberAfter);
    }

    @Test //Asserting button alert updates the canvas value
    public void buttonSuccessUpdateCanvas() //Stack Overflow https://stackoverflow.com/questions/50276000/how-to-get-element-details-from-script-tag-using-javascript-in-selenium-webdrive
    {
        final By SCRIPT = By.tagName("script");
        List<WebElement> scripts = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(SCRIPT));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String query = "return document.getElementsByTagName(\"script\")[arguments[0]].innerHTML;";
        System.out.println(query);

        Pattern p = Pattern.compile("canvas.strokeText\\('Answer: \\d+'");
        Pattern p2 = Pattern.compile("\\d+");

        String numberBefore = IntStream.range(0, scripts.size())
                .mapToObj(i -> (String)js.executeScript(query, i))
                .map(string -> p.matcher(string))
                .filter(m -> m.find())
                .map(m -> p2.matcher(m.group()))
                .filter(m -> m.find())
                .map(m -> m.group())
                .findFirst()
                .orElse(null);
        System.out.println(numberBefore);  // prints the number rendered on the page

        driver.findElementByCssSelector("[class='button success']").click();

        final By SCRIPT1 = By.tagName("script");
        List<WebElement> scripts1 = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(SCRIPT1));
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        String query2 = "return document.getElementsByTagName(\"script\")[arguments[0]].innerHTML;";
        System.out.println(query);

        Pattern pa = Pattern.compile("canvas.strokeText\\('Answer: \\d+'");
        Pattern pa2 = Pattern.compile("\\d+");

        String numberAfter = IntStream.range(0, scripts1.size())
                .mapToObj(i -> (String)js2.executeScript(query2, i))
                .map(string -> pa.matcher(string))
                .filter(m -> m.find())
                .map(m -> pa2.matcher(m.group()))
                .filter(m -> m.find())
                .map(m -> m.group())
                .findFirst()
                .orElse(null);
        System.out.println(numberAfter);  // prints the number rendered on the page
        assertNotEquals(numberBefore, numberAfter);
    }

    @Test //Asserting that the page footer points to the correct URL
    public void pageFooter()
    {
        String footerURL = driver.findElementByXPath("//*[@id='page-footer']/div/div/a").getAttribute("href");
        assertEquals(footerURL,"http://elementalselenium.com/");
    }

    @After
    public void closeBrowser()
    {
        // Close Browser and end session
        driver.quit();
    }
}