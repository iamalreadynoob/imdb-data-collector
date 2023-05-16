package functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class GitHubPublish
{
    private String date;
    public GitHubPublish(WebDriver driver)
    {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        Date current = new Date();
        date = format.format(current);

        driver.get("https://www.github.com/login");
        login(driver);
        reach(driver);
    }

    private void login(WebDriver driver)
    {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait driverWait = new WebDriverWait(driver, duration);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login_field']")));

        WebElement username = driver.findElement(By.xpath("//input[@id='login_field']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement click = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block js-sign-in-button']"));

        username.sendKeys("username");
        password.sendKeys("password");
        click.click();
    }

    private void reach(WebDriver driver)
    {
        driver.get("https://github.com/iamalreadynoob/imdb-top-100/upload/main/movie");

        Duration duration = Duration.ofSeconds(30);
        WebDriverWait driverWait = new WebDriverWait(driver, duration);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='upload-manifest-files-input']")));

        File file = new File("data/movie/" + date + ".csv");
        driver.findElement(By.xpath("//input[@id='upload-manifest-files-input']")).sendKeys(file.getAbsolutePath());

        try{Thread.sleep(5000);}
        catch (Exception e){e.printStackTrace();}

        driver.findElement(By.xpath("//button[@data-edit-text='Commit changes']")).click();

        driver.get("https://github.com/iamalreadynoob/imdb-top-100/upload/main/tv");

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='upload-manifest-files-input']")));

        File tv = new File("data/tv/" + date + ".csv");
        driver.findElement(By.xpath("//input[@id='upload-manifest-files-input']")).sendKeys(tv.getAbsolutePath());
        try{Thread.sleep(5000);}
        catch (Exception e){e.printStackTrace();}
        driver.findElement(By.xpath("//button[@data-edit-text='Commit changes']")).click();
    }

}
