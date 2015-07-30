package com.istuary.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by jliu on 23/07/15.
 */
public class TopoPageObjects {

    private WebDriver driver;
    private WebDriverWait wait;
    private int DEFAULT_DELAY = 15;

    public TopoPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_DELAY);
    }

    @FindBy(id = "topology/singleTopo_file_topologySelector")
    private WebElement uploadFirstTopoBtn;

    @FindBy(id = "topology/singleTopo_button_uploadTopologyModal")
    private WebElement uploadBtn;

    @FindBy(id = "topology/singleTopo_button_downloadTopo")
    private WebElement downloadBtn;

    @FindBy(id = "topologySelector")
    private WebElement topoSelectorBtn;

    @FindBy(id = "header_li_monitor")
    private WebElement monitorTab;

    public void uploadTopo(String filePath) {

        filePath = System.getProperty("user.dir") + filePath;
        while (true){
            try {
                wait.until(ExpectedConditions.visibilityOf(monitorTab));
                break;
            }catch (StaleElementReferenceException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

        if (monitorTab.getCssValue("opacity").equals("1")) {
            uploadBtn.click();
            driver.findElement(By.id("topologySelector"));
            topoSelectorBtn.sendKeys(filePath);
        }else {
            uploadFirstTopoBtn.sendKeys(filePath);
        }

    }

}
