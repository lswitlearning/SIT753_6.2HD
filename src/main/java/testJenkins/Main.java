package testJenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        // 设置WebDriver的路径，这里使用Chrome作为示例
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // 创建WebDriver实例
        WebDriver driver = new ChromeDriver();

        // 打开网页
        driver.get("http://localhost:80");

        // 检查页面标题是否符合预期
        if (driver.getTitle().contains("Cool Maths for Kids")) {
            System.out.println("Matched");
        } else {
            System.out.println("Not Matched");
        }

        // 关闭浏览器
        driver.quit();
    }
}
