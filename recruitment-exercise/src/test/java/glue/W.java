package glue;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class W {
    private static W instance = null;

    public static W get() {
        if (instance == null) {
            instance = new W();
        }
        return instance;
    }

    protected WebDriver driver;

    private W() {
        WebDriverManager.chromedriver().clearDriverCache();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    public static void close() {
        if (instance != null) {
            instance.driver.close();
            instance = null;
        }
    }

}
