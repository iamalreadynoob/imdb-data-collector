package gui;

import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame
{
    protected static JButton start;
    public Screen(WebDriver driver)
    {
        this.setSize(400, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("IMDb Collector");
        this.setLayout(null);

        this.getContentPane().setBackground(Color.BLACK);

        start = new JButton();
        this.add(start);
        start.setBounds(100, 75, 200, 50);
        start.setBorder(null);
        start.setBackground(Color.WHITE);
        start.setText("start");

        new Button(driver);

        this.setVisible(true);
    }

}
