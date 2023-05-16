package gui;

import functions.DataAccess;
import functions.DictPublish;
import functions.GitHubPublish;
import org.openqa.selenium.WebDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button
{
    protected Button(WebDriver driver)
    {
        Screen.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new DataAccess(driver);
                new GitHubPublish(driver);
                new DictPublish(driver);
            }
        });
    }
}
