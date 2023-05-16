package functions;

import com.beust.ah.A;
import fileReading.DataReading;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class DictPublish
{

    public DictPublish(WebDriver driver)
    {
        driver.get("https://normalsozluk.com/");
        login(driver);
        publish(driver);
    }

    private void login(WebDriver driver)
    {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait driverWait = new WebDriverWait(driver, duration);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='nav-link button_signin']")));

        driver.findElement(By.xpath("//a[@class='nav-link button_signin']")).click();

        try {Thread.sleep(3000);}
        catch (Exception e){e.printStackTrace();}

        driver.findElement(By.id("input_signin_email")).sendKeys("username");
        driver.findElement(By.id("input_signin_password")).sendKeys("password");
        driver.findElement(By.xpath("//input[@id='button_signin_submit']")).click();

        try {Thread.sleep(4000);}
        catch (Exception e){e.printStackTrace();}
    }

    private void publish(WebDriver driver)
    {
        driver.get("https://normalsozluk.com/b/imdb--22972");

        try {Thread.sleep(3000);}
        catch (Exception e){e.printStackTrace();}

        driver.findElement(By.xpath("//textarea[@id='input_entrybody']")).sendKeys(text());

        driver.findElement(By.xpath("//i[@class='fa fa-close']")).click();

        try {Thread.sleep(1500);}
        catch (Exception e){e.printStackTrace();}
    }

    private String text()
    {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        Date current = new Date();
        String date = format.format(current);

        String script = date + " imdb en popüler içerikleri(u: ilk 20)\n\nönemli not: bu liste tamamen otomatik bir biçimde oluşturulmaktadır, bu sebeple verilerin alınışı/işlenişi sırasında yaşanacak hatalarla listede bozukluk meydana gelebilir.\n\ndizilerde top 20:\n\n";

        DataReading reading = new DataReading();
        reading.scan("data/tv/" + date + ".csv");

        ArrayList<String> names = reading.getColumn("name");
        ArrayList<String> rankings = reading.getColumn("rank");
        ArrayList<String> years = reading.getColumn("year");
        ArrayList<String> scores = reading.getColumn("score");

        for (int i = 0; i < 3; i++) script += rankings.get(i) + ". sırada " + names.get(i) + " bulunuyor, kendisi " + years.get(i) + " yılı yapımı ve güncel imdb puanı " + scores.get(i).replace(".", ",") + ". ";
        script += "\n\ndiğer sıralar ise şu şekilde:\n\n";
        for (int i = 3; i < 20; i++) script += rankings.get(i) + " => " + names.get(i).replace("I", "i") + " - " + years.get(i) + " - " + scores.get(i) + " puan\n";

        DataReading mReading = new DataReading();
        mReading.scan("data/movie/" + date + ".csv");
        ArrayList<String> mNames = mReading.getColumn("name");
        ArrayList<String> mRankings = mReading.getColumn("rank");
        ArrayList<String> mYears = mReading.getColumn("year");
        ArrayList<String> mScores = mReading.getColumn("score");

        script += "\nfilmlerde top 20:\n\n";

        for (int i = 0; i < 3; i++) script += mRankings.get(i) + ". sırada " + mNames.get(i) + " bulunuyor, kendisi " + mYears.get(i) + " yılı yapımı ve güncel imdb puanı " + mScores.get(i) + "; ";
        script += "\n\ndiğer sıralar ise şu şekilde:\n\n";
        for (int i = 3; i < 20; i++) script += mRankings.get(i) + " => " + mNames.get(i).replace("I", "i") + " - " + mYears.get(i) + " - " + mScores.get(i) + " puan\n";

        script += "\n\nlistenin devamına (link: https://github.com/iamalreadynoob/imdb-top-100::buradan) ulaşabilirsiniz. ayrıca kullandığım programı da (gbkz: github) (link: https://github.com/iamalreadynoob::sayfamdan) bulabilirsiniz.";

        return script;
    }

}
