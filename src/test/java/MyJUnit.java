import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
    @Test
    public void visitSite(){
        driver.get("https://demoqa.com/");
    }

    public void teardown(){
        driver.quit();
    }
}
