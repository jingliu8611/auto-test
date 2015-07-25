package com.istuary.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by jliu on 23/07/15.
 */
public class TopoPageObjects {

    private WebDriver driver;

    public TopoPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
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
//        driver.findElement(By.id("topology/singleTopo_button_uploadTopologyModal"));
//        uploadBtn.click();
        if (monitorTab.getCssValue("opacity").equals("1")) {
            uploadBtn.click();
            driver.findElement(By.id("topologySelector"));
            topoSelectorBtn.sendKeys(filePath);
        }else {
            uploadFirstTopoBtn.sendKeys(filePath);
        }
////        driver.findElement((By) topoSelectorBtn);
//        driver.findElement(By.id("topologySelector"));
////        topoSelectorBtn.click();
//        topoSelectorBtn.sendKeys(filePath);
//        Assert.assertTrue(true);
    }

}
