package com.istuary.controllers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jliu on 16/08/15.
 */
public class IPMACBindingPageObjects {

    private WebDriver driver;
    private WebDriverWait wait;
    private int DEFAULT_DELAY = 15;

    public IPMACBindingPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_DELAY);
    }

    @FindBy(id = "rule-ipmac_button_enterEdit")
    private WebElement ipMacEditBtn;

    @FindBy(id = "rule-ipmac_text_tableMac1")
    private WebElement macInputBox;

    @FindBy(id = "rule-ipmac_checkbox_tableToggle1")
    private WebElement ipmacToggleBtn;

    @FindBy(id = "rule-ipmac_button_confirmEdit")
    private WebElement confirmEditBtn;

    @FindBy(id = "rule-ipmac_button_deploy")
    private WebElement deployBtn;

    @FindBy(id = "rule-whiteList-editor_button_deployConfirm")
    private WebElement confirmDeployBtn;

    public void deployIPMACBindingRule() {

        String testMac = "aa:aa:aa:bb:bb:bb";
        wait.until(ExpectedConditions.visibilityOf(ipMacEditBtn));
        ipMacEditBtn.click();
        wait.until(ExpectedConditions.visibilityOf(macInputBox));
        if (macInputBox.getText().equals("")) {
//            macInputBox.sendKeys(testMac);
            System.out.println("Enter Testing Mac Address");
        }
//        wait.until(ExpectedConditions.visibilityOf(ipmacToggleBtn));
        ipmacToggleBtn.click();
        wait.until(ExpectedConditions.visibilityOf(confirmEditBtn));
        confirmEditBtn.click();
        wait.until(ExpectedConditions.visibilityOf(deployBtn));
        deployBtn.click();
        wait.until(ExpectedConditions.visibilityOf(confirmDeployBtn));
        confirmDeployBtn.click();

    }

}
