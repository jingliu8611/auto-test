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
    private WebDriverWait wait;
    private int DEFAULT_DELAY = 15;

    public NavigationBarObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_DELAY);
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

    @FindBy(id = "rule_a_ipmac")
    private WebElement ipMacTab;

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

//    @FindBy(className = "alert-success")
//    public WebElement successAlert;

    @FindBy(className = "close")
    public WebElement alertCloseBtn;

    public void goToTopology() {

//        driver.findElement(By.id("header_a_navUser"));
        wait.until(ExpectedConditions.visibilityOf(topologyTab));
        topologyTab.click();

    }

    public void goToMonitor() {

//        driver.findElement(By.id("header_a_navUser"));
        wait.until(ExpectedConditions.visibilityOf(monitorTab));
        monitorTab.click();

    }

    public void goToRule() {

//        driver.findElement(By.id("header_a_navUser"));
        wait.until(ExpectedConditions.visibilityOf(ruleTab));
        ruleTab.click();

    }

    public void goToIPMAC() {

        wait.until(ExpectedConditions.visibilityOf(ruleTab));
        ruleTab.click();
        wait.until(ExpectedConditions.visibilityOf(ipMacTab));
        ipMacTab.click();

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

        wait.until(ExpectedConditions.visibilityOf(userIcon));
        Actions action = new Actions(driver);
        action.moveToElement(userIcon).perform();
        wait.until(ExpectedConditions.visibilityOf(signOut));
        signOut.click();

    }

    public void closeAlert() {
        wait.until(ExpectedConditions.visibilityOf(alertCloseBtn));
        alertCloseBtn.click();
    }

    public boolean isActionSuccess() {

        try {
            String result = driver.findElement(By.className("alert-success")).getCssValue("display");
            if (result.equals("block")) {
                return true;
            }
            return false;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
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
