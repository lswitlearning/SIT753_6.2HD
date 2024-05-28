package testJenkins;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestMain {
    @Test
    public void testPageTitle() {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\cinna\\OneDrive\\Documents\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:80");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Cool math for kids";
        assertEquals(expectedTitle,actualTitle);

        driver.quit();
    }

}
