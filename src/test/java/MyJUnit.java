import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
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

    @Test
    public void submitForm(){

        //Ajax syntex - &&('#idname') in console panel;

        driver.get("https://demoqa.com/text-box");

        //Text Box Testing...
        driver.findElement(By.id("userName")).sendKeys("Test User");
        driver.findElement(By.cssSelector("[type=email]")).sendKeys("provashish@gmail.com");

        List <WebElement> elements = driver.findElements(By.className("form-control"));

        elements.get(2).sendKeys("Dhaka");
        elements.get(3).sendKeys("Dhaka");
    }

    @AfterAll
    public void teardown() {
       // driver.quit();
        //close website
    }
}
