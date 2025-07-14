import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
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
    public void submitForm(){

        //Ajax syntex - &&('#idname') in console panel;

        //Get form page
        driver.get("https://demoqa.com/text-box");

        //Input value into form
        driver.findElement(By.id("userName")).sendKeys("Test User");
        driver.findElement(By.cssSelector("[type=email]")).sendKeys("provashish@gmail.com");

        List <WebElement> elements = driver.findElements(By.className("form-control"));
        elements.get(2).sendKeys("Dhaka");
        elements.get(3).sendKeys("Dhaka");

        //click submit button after scrolling...
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");

        //List<WebElement> btnElement = driver.findElements(By.tagName("button"));
        driver.findElements(By.tagName("button")).get(1).click();

        //Get actual value after input
        List <WebElement> textElements =  driver.findElements(By.tagName("p"));
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

    @AfterAll
    public void teardown() {
       // driver.quit();
        //close website
    }
}
