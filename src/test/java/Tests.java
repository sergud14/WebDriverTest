import jdk.jfr.Timespan;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Tests {
    WebDriver driver;
    @Before
    public void  setUp()
    {
        driver=new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","C:\\chdriver\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.get("https://pikabu.ru/");
    }

  @Test
  public void  PikabuTest() throws InterruptedException {
      Assert.assertEquals("Горячее – самые интересные и обсуждаемые посты | Пикабу", driver.getTitle());
      driver.findElement(By.cssSelector(".header-right-menu__login-button")).click();
      driver.findElement(By.xpath("//div[@class='auth-modal']//div[text()='Войти']")).isDisplayed();
      driver.findElement(By.xpath("//div[@class='auth-modal']//input[@placeholder='Логин']")).isDisplayed();
      driver.findElement(By.xpath("//div[@class='auth-modal']//input[@placeholder='Пароль']")).isDisplayed();
      driver.findElement(By.xpath("//div[@class='auth-modal']//span[text()='Войти']")).isDisplayed();
      driver.findElement(By.xpath("//div[@class='auth-modal']//input[@placeholder='Логин']")).sendKeys("Qwerty");
      driver.findElement(By.xpath("//div[@class='auth-modal']//input[@placeholder='Пароль']")).sendKeys("Qwerty");
      driver.findElement(By.xpath("//div[@class='auth-modal']//span[text()='Войти']")).click();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='overlay']//span[@class='auth__error auth__error_top']"))));
      String text=driver.findElement(By.xpath("//div[@class='overlay']//span[@class='auth__error auth__error_top']")).getText();
      Assert.assertEquals("Ошибка. Вы ввели неверные данные авторизации", text);
  }

    @After
    public void  tearDown()
    {
        driver.quit();
    }
}
