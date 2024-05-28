package jenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver",
        		"C:\\Users\\cinna\\OneDrive\\Documents\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:80");
        
        System.out.println("Actual Page Title: " + driver.getTitle());

        if (driver.getTitle().contains("Cool Maths for Kids")) {
            System.out.println("Match");
        } else {
            System.out.println("Unmatch");
        }


        driver.quit();
    }
}
