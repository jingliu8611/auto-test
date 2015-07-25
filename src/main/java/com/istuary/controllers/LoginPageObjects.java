package com.istuary.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jliu on 22/07/15.
 */
public class LoginPageObjects {

    private WebDriver driver;

    public LoginPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//    @FindBy(className = "login-input-username")
    @FindBy(id = "login_text_username")
    private WebElement userName;

//    @FindBy(className = "login-input-password")
    @FindBy(id = "login_text_password")
    private WebElement userPassword;

//    @FindBy(className = "btn-login")
    @FindBy(id = "login_button_loginButton")
    private WebElement loginBtn;

//    @FindBy(id = "lst-ib")
//    public WebElement googleSearch;

    public void login(String usrname, String passwd) {

        driver.findElement(By.id("login_button_loginButton"));
//        if (loginBtn.isDisplayed()) {
//            System.out.println("aa");
//        }
        userName.sendKeys(usrname);
        userPassword.sendKeys(passwd);
        loginBtn.click();

//        if (loginBtn.isDisplayed()) {
//            System.out.println("aa");
//        }

    }

//    public void googleTest(String searchString) {
//
//        googleSearch.sendKeys(searchString);
//
//    }

}
