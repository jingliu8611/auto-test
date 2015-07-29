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
public class NavigationBarObjects {

    private WebDriver driver;

    public NavigationBarObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//    @FindBy(css = "a[href='/monitor/overview']")
//    @FindBy(id = "header_li_monitor")
    @FindBy(xpath = "//li[@id='header_li_monitor']/a[1]")
    private WebElement monitorTab;

//    @FindBy(css = "a[href='/topology/singleTopo']")
//    @FindBy(id = "header_li_topology")
    @FindBy(xpath = "//li[@id='header_li_topology']/a[1]")
    private WebElement topologyTab;

//    @FindBy(css = "a[href='/monitor/signature/']")
//    @FindBy(id = "header_li_rule")
    @FindBy(xpath = "//li[@id='header_li_rule']/a[1]")
    private WebElement ruleTab;

//    @FindBy(css = "a[href='/monitor/signature/?tab=policyManagement']")
//    private WebElement policyManagementTab;
//
//    @FindBy(css = "button[ng-click='policies.createPolicy()']")
//    private WebElement createPolicyBtn;

//    //	@FindBy(css = "a[ng-click='tab.activateTab(\'signatures\');editor.changeURL(\'signatures\')']")
//    @FindBy(xpath = "//ul[@class='nav nav-tabs nav-tabs-gray']/li[2]")
//    private WebElement signatureTab;

//    @FindBy(className = "fa-user")
    @FindBy(id = "header_a_navUser")
    private WebElement userIcon;

//    @FindBy(className = "fa-sign-out")
//    @FindBy(id = "header_li_logOut")
    @FindBy(xpath = "//li[@id='header_li_logOut']/a[1]")
    private WebElement signOut;

    public void goToTopology() {

        driver.findElement(By.id("header_a_navUser"));
        topologyTab.click();

    }

    public void goToMonitor() {

        driver.findElement(By.id("header_a_navUser"));
        monitorTab.click();

    }

    public void goToRule() {

        driver.findElement(By.id("header_a_navUser"));
        ruleTab.click();

    }

//    public void goToPolicyManagement() {
//
//        policyManagementTab.click();
//    }
//
//    public void createPolicy() {
//        createPolicyBtn.click();
//    }
//
//    public void createSignature() {
//        signatureTab.click();
//    }

    public void signOut() {

        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement successAlert = wait.until(ExpectedConditions
                .visibilityOf(driver.findElement(By.id("header_a_navUser"))));
        driver.findElement(By.id("header_a_navUser"));
        Actions action = new Actions(driver);
        action.moveToElement(userIcon).perform();
        driver.findElement(By.id("header_li_logOut"));
        successAlert = wait.until(ExpectedConditions
                .visibilityOf(driver.findElement(By.id("header_li_logOut"))));
        signOut.click();

    }

    public void testFunc() throws InterruptedException {

        goToTopology();
        Thread.sleep(1000);
        goToRule();
        Thread.sleep(1000);
        goToMonitor();
        Thread.sleep(1000);
        signOut();

    }

}
