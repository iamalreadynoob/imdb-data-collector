import gui.Screen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args)
    {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        WebDriver driver = new FirefoxDriver();

        new Screen(driver);
    }
}