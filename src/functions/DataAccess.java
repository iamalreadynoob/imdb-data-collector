package functions;

import fileReading.TextReading;
import fileWriting.TextWriting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class DataAccess
{
    private WebDriver driver;
    private String date;
    public DataAccess(WebDriver driver)
    {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        Date current = new Date();
        date = format.format(current);

        this.driver = driver;

        access("https://www.imdb.com/chart/tvmeter/?ref_=nv_tvv_mptv", "data/raw-tv.txt", 0);
        access("https://www.imdb.com/chart/moviemeter/?ref_=nv_mv_mpm", "data/raw-movie.txt", 1);
    }

    private void access(String link, String txt, int type)
    {
        driver.get(link);
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait driverWait = new WebDriverWait(driver, duration);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@class='lister-list']")));

        String data = driver.findElement(By.xpath("//tbody[@class='lister-list']")).getText();

        TextWriting.write(txt, data);

        ArrayList<String> lines = new ArrayList<>();
        lines.add("rank,name,year,score");

        ArrayList<String> shows = TextReading.read(txt);
        int rank = 1;

        for (int i = 0; i < shows.size(); i += 3)
        {
            String name = shows.get(i).substring(0, shows.get(i).length() - 7);
            name.replace(",", "");

            String place = Integer.toString(rank);

            String year = shows.get(i).substring(shows.get(i).length() - 5, shows.get(i).length() - 1);

            String score = "NA";
            if (i+2 < shows.size() && shows.get(i+2).contains(".") && shows.get(i+2).length() == 3) {score = shows.get(i+2);}
            else {i -= 1;}

            rank++;
            lines.add(place + "," + name + "," + year + "," + score);
        }

        String root;
        if (type == 0) root = "data/tv/";
        else root = "data/movie/";

        TextWriting.write(root + date + ".csv", lines);
    }
}
