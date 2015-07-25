package com.istuary.controllers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jliu on 24/07/15.
 */
public class SignaturePageObjects {

    private WebDriver driver;

    public SignaturePageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    
}
