import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class MyJUnit {

    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();

        //see in maximum screen...
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Check website title") //Test Title
    @Test
    public void visitSite() {
        driver.get("https://demoqa.com/"); //test step
        String titile = driver.getTitle(); //actual result
        String titileExpectedResult = "DEMOQA"; //expected result
        Assertions.assertEquals(titile, titileExpectedResult); //test status
    }


    @DisplayName("Submit From test")
    @Test
    public void submitForm() {

        //Ajax syntex - &&('#idname') in console panel;

        //Get form page
        driver.get("https://demoqa.com/text-box");

        //Input value into form
        driver.findElement(By.id("userName")).sendKeys("Test User");
        driver.findElement(By.cssSelector("[type=email]")).sendKeys("provashish@gmail.com");

        List<WebElement> elements = driver.findElements(By.className("form-control"));
        elements.get(2).sendKeys("Dhaka");
        elements.get(3).sendKeys("Dhaka");

        //click submit button after scrolling...
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");

        //List<WebElement> btnElement = driver.findElements(By.tagName("button"));
        driver.findElements(By.tagName("button")).get(1).click();

        //Get actual value after input
        List<WebElement> textElements = driver.findElements(By.tagName("p"));
        String nameActual = textElements.get(0).getText();
        String emailActual = textElements.get(1).getText();
        String currentAddActual = textElements.get(2).getText();
        String parmanentAddActual = textElements.get(3).getText();

        //Check input and output value
        Assertions.assertTrue(nameActual.contains("Test User"));
        Assertions.assertTrue(emailActual.contains("provashish@gmail.com"));
        Assertions.assertTrue(currentAddActual.contains("Dhaka"));
        Assertions.assertTrue(parmanentAddActual.contains("Dhaka"));

    }

    @DisplayName("Button test")
    @Test
    public void doubleClick() {
        //Get button page
        driver.get("https://demoqa.com/buttons");

        //Click button
        Actions actions = new Actions(driver);

        List<WebElement> btnElements = driver.findElements(By.tagName("button"));

        actions.doubleClick(btnElements.get(1)).perform(); //double click
        actions.contextClick(btnElements.get(2)).perform(); // Right click
        actions.click(btnElements.get(3)).perform();
    }

    @DisplayName("Alert Test")
    @Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");

//        driver.findElement(By.id("alertButton")).click();
//        Thread.sleep(3000);
//        driver.switchTo().alert().accept();

//        driver.findElement(By.id("timerAlertButton")).click();
//        Thread.sleep(6000);
//        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirmButton")).click();
        Thread.sleep(6000);
        driver.switchTo().alert().dismiss();
    }

    @DisplayName("Date Test")
    @Test
    public void setDate() {
        driver.get("https://demoqa.com/date-picker");
        WebElement txtDate = driver.findElement(By.id("datePickerMonthYearInput"));

        Actions action = new Actions(driver);
        // action.click(txtDate).sendKeys(Keys.CONTROL+"a").sendKeys(Keys.BACK_SPACE).sendKeys("07/01/2025").sendKeys(Keys.ENTER).perform();

        txtDate.click();
        txtDate.sendKeys(Keys.CONTROL + "a");
        txtDate.sendKeys(Keys.BACK_SPACE);
        txtDate.sendKeys("07/01/2025");
        txtDate.sendKeys(Keys.ENTER);
    }

    @DisplayName("Select dropdown Test")
    @Test
    public void selectDropdown() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
//        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
//        select.selectByVisibleText("Green");

        List<WebElement> dropdown = driver.findElements(By.className("css-1hwfws3"));
        dropdown.get(1).click();
        Thread.sleep(2000);

//      dropdown.get(1).sendKeys(Keys.ARROW_DOWN);
//      dropdown.get(1).sendKeys(Keys.ENTER);

        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

        //Select text from up
        for (int i = 0; i < 2; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }
        actions.sendKeys(Keys.ENTER).perform();

    }

    @DisplayName("Mouse Hover Test")
    @Test
    public void mouseHover() {
        driver.get("https://www.aiub.edu/");
        Actions actions = new Actions(driver);

        //Use X-PATH
        //X-PATH syntex in console pannel = $x('//tagname[contains(text(), "text-under-tag")]');
        actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(), \"About\")]"))).perform();
    }

    @DisplayName("Multiple Tab Test")
    @Test
    public void handleMultipleTab() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        Thread.sleep(3000);
        ArrayList<String> arrayList = new ArrayList(driver.getWindowHandles());
        System.out.println(arrayList);
        driver.switchTo().window(arrayList.get(1));
        String heading = driver.findElement(By.id("sampleHeading")).getText();
        System.out.println(heading);
        driver.close();
        driver.switchTo().window(arrayList.get(0));
    }

    @AfterAll
    public void teardown() {
        // driver.quit();
        //close website
    }
}
