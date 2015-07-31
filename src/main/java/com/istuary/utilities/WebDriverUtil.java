package com.istuary.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by jliu on 22/07/15.
 */
public class WebDriverUtil {

    public static WebDriver createChrome() {

        String osName;
        System.out.println("createChrome is called");
        osName = System.getProperty("os.name");
        if (osName.indexOf("Win") >= 0) {
            System.setProperty("webdriver.chrome.driver", "resources//chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "resources//chromedriver");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        System.out.println("createChrome is ended");

        return driver;

    }

    public static WebDriver createFF() {

        System.out.println("createFF is called");

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        System.out.println("createChrome is ended");

        return driver;

    }

}
