package com.istuary.controllers;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jliu on 17/08/15.
 */
public class WhitelistPageObjects {

    private WebDriver driver;
    private WebDriverWait wait;
    private int DEFAULT_DELAY = 15;

    public WhitelistPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_DELAY);
    }

    @FindBy(id = "rule-whiteList_li_whiteList_li_policyManagement")
    private WebElement policyMgmtTab;

    @FindBy(id = "rule-whiteList-policyManagement_button_createRules")
    private WebElement createRuleBtn;

    @FindBy(id = "learningInterval_text_hours")
    private WebElement learningHr;

    @FindBy(id = "rule-whiteList-editor_button_setInterval")
    private WebElement setLearningTimeBtn;

    @FindBy(xpath = "//li[@class='learning-item ng-scope']")
    private WebElement learningTask;

    @FindBy(id = "rule-whiteList-editor_button_stopLearning")
    private WebElement stopLearningBtn;

    @FindBy(id = "rule-whiteList-editor_checkbox_learningCheckAll")
    private WebElement selectAllCb;

    @FindBy(id = "rule-whiteList-editor_i_movePreDep")
    private WebElement addPreDepBtn;

    @FindBy(id = "rule-whiteList-editor_button_deploy")
    private WebElement deployBtn;

    @FindBy(id = "rule-whiteList-editor_button_deployConfirm")
    private WebElement deployConfirmBtn;

    public void startLearning() {

        String hr = "1";
        wait.until(ExpectedConditions.visibilityOf(policyMgmtTab));
        policyMgmtTab.click();
        wait.until(ExpectedConditions.visibilityOf(createRuleBtn));
        createRuleBtn.click();
        wait.until(ExpectedConditions.visibilityOf(learningHr));
        learningHr.sendKeys(hr);
        wait.until(ExpectedConditions.visibilityOf(setLearningTimeBtn));
        setLearningTimeBtn.click();


    }

    public void finishLearning() throws InterruptedException {

//        wait.until(ExpectedConditions.visibilityOf(learningTask));
        while (true){
            try {
                wait.until(ExpectedConditions.visibilityOf(learningTask));
                learningTask.click();
                break;
            }catch (StaleElementReferenceException e) {
                e.printStackTrace();
                continue;
            }
        }
//        learningTask.click();
        Thread.sleep(8000);
        wait.until(ExpectedConditions.visibilityOf(stopLearningBtn));
        stopLearningBtn.click();

    }

    public void deployWhitelistRule() {

        wait.until(ExpectedConditions.visibilityOf(selectAllCb));
        selectAllCb.click();
        wait.until(ExpectedConditions.visibilityOf(addPreDepBtn));
        addPreDepBtn.click();
//        wait.until(ExpectedConditions.visibilityOf(deployBtn));
        wait.until(ExpectedConditions.elementToBeClickable(deployBtn));
        deployBtn.click();
        wait.until(ExpectedConditions.visibilityOf(deployConfirmBtn));
        deployConfirmBtn.click();

    }


}
