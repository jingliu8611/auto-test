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
public class BlacklistPageObjects {

    private WebDriver driver;
    private WebDriverWait wait;
    private int DEFAULT_DELAY = 15;

    public BlacklistPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_DELAY);
    }

    @FindBy(id = "rule-blackList_li_policyManagement")
    private WebElement policyMgmtTab;

    @FindBy(id = "rule-blackList_li_deployedPanel")
    private WebElement deployedPanelTab;

    @FindBy(id = "rule-blackList-policyDetail_button_createRules")
    private WebElement createRuleBtn;

    @FindBy(id = "rule-blackList-editor_checkbox_selectAll")
    private WebElement selectAllCb;

    @FindBy(id = "rule-blackList-editor_i_movePreDep")
    private WebElement addPreDepBtn;

    @FindBy(id = "rule-blackList-editor_button_deploy")
    private WebElement deployBtn;

    @FindBy(id = "rule-whiteList-editor_button_deployConfirm")
    private WebElement deployConfirmBtn;

    public void deployBlacklistRule() {

        wait.until(ExpectedConditions.visibilityOf(policyMgmtTab));
        policyMgmtTab.click();
        wait.until(ExpectedConditions.visibilityOf(createRuleBtn));
        createRuleBtn.click();
        wait.until(ExpectedConditions.visibilityOf(selectAllCb));
        selectAllCb.click();
        wait.until(ExpectedConditions.visibilityOf(addPreDepBtn));
        addPreDepBtn.click();
        wait.until(ExpectedConditions.visibilityOf(deployBtn));
        deployBtn.click();
        wait.until(ExpectedConditions.visibilityOf(deployConfirmBtn));
        deployConfirmBtn.click();

    }

}
