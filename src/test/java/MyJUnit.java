import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

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

    @AfterAll
    public void teardown() {
        driver.quit();
    }
}
