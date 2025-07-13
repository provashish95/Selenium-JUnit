import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyJUnit {
    public void setup() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();

        System.out.println(title);
    }
}
