package com.istuary.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jliu on 22/07/15.
 */
public class DomainPageObjects {

    private WebDriver driver;

    public DomainPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // @FindBy(css =
    // "input[class='ng-pristine ng-valid ng-valid-maxlength ng-touched']")
//    @FindBy(css = "input[type='password'][ng-model='domain.currentEditDomain.user.password']")
    @FindBy(id = "domain_password_newPassword")
    private WebElement passwordInput;

//    @FindBy(css = "input[type='password'][ng-model='domain.currentEditDomain.user.checkpassword']")
    @FindBy(id = "domain_password_confPassword")
    private WebElement passwordConfirm;

//    @FindBy(css = "button[class='btn btn-default btn-sm pull-right']")
    @FindBy(id = "domain_button_saveUser")
    private WebElement confirmButton;

//    @FindBy(css = "button[class='btn btn-default btn-sm pull-right']")
    @FindBy(id = "domain_button_editUser")
    private WebElement editButton;

    @FindBy(id = "header_a_navUser")
    private WebElement userIcon;

//    @FindBy(className = "fa-user")
//    public WebElement userIcon;
//
//    @FindBy(className = "fa-sign-out")
//    public WebElement signOut;

    public void enterAdminPassword(String passwd) {

        driver.findElement(By.id("header_a_navUser"));
        if (editButton.getCssValue("display").equals("block")) {
            editButton.click();
        }
        passwordInput.sendKeys(passwd);
        passwordConfirm.sendKeys(passwd);
        confirmButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement successAlert = wait.until(ExpectedConditions
                .visibilityOf(driver.findElement(By.className("alert-success"))));

    }

//    public void signOut() throws InterruptedException {
//        Thread.sleep(2000);
//        Actions action = new Actions(driver);
//        action.moveToElement(userIcon).perform();
//        Thread.sleep(2000);
//        signOut.click();
//        Thread.sleep(2000);
//    }

}
