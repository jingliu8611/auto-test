package com.istuary.tests;

import com.istuary.controllers.*;
import com.istuary.utilities.ExcelUtil;
import com.istuary.utilities.SimulatorUtil;
import com.istuary.utilities.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by jliu on 22/07/15.
 */
public class SmokeTest {

    WebDriver driver;
    ExcelUtil testData;
    int DEFAULT_DELAY = 15;
    String testServerIp;
    String simulatorIp;
    String loginURL;
    String browser;
    String topoFilePath;
    String username;
    String password;
    String adminName;
    String adminPass;

    @BeforeClass
    public void initiate() throws Exception {
        testData = new ExcelUtil(
                "resources//data//data.xls");
        testServerIp = ExcelUtil.readCell("TestServerIp", 1);
        simulatorIp = ExcelUtil.readCell("SimulatorIp", 1);
        loginURL = "https://" + testServerIp + "/login";
        browser = ExcelUtil.readCell("Browser", 1);
        topoFilePath = "/resources/data/" + ExcelUtil.readCell("TopoFile", 1);
        username = ExcelUtil.readCell("Username", 1);
        password = ExcelUtil.readCell("Password", 1);
        adminName = ExcelUtil.readCell("AdminName", 1);
        adminPass = ExcelUtil.readCell("AdminPass", 1);

        if (browser.equals("Chrome")) {
            driver = WebDriverUtil.createChrome();
        }else if (browser.equals("FireFox")) {
            driver = WebDriverUtil.createFF();
        }
        driver.manage().timeouts().implicitlyWait(DEFAULT_DELAY, TimeUnit.SECONDS);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void LogInOutTest() throws InterruptedException {
        String username = ExcelUtil.readCell("Username", 1);
        String password = ExcelUtil.readCell("Password", 1);
        System.out.println("This is createAdmin");
//        driver.get("https://10.0.10.63/login");
        driver.get(loginURL);
        LoginPageObjects loginPage = new LoginPageObjects(driver);
        Thread.sleep(3000);
        loginPage.login(username, password);
//        loginPage.login("admin", "admin12345");
        Thread.sleep(3000);
        DomainPageObjects domainPage = new DomainPageObjects(driver);
        domainPage.enterAdminPassword("admin@123");
//        NavigationBarObjects navigationBar = new NavigationBarObjects(driver);
//        navigationBar.testFunc();
//        Thread.sleep(3000);
        Assert.assertTrue(true);
    }

    @Test
    public void Test1() {
        System.out.println("This is Test1");
    }

    @Test
    public void Test2() {
        System.out.println("This is Test2");
    }

    @Test
    public void createAdmin() {

        System.out.println("This is createAdmin");
        driver.get(loginURL);
        LoginPageObjects loginPage = new LoginPageObjects(driver);
        loginPage.login(username, password);
        DomainPageObjects domainPage = new DomainPageObjects(driver);
        domainPage.enterAdminPassword(adminPass);
        NavigationBarObjects navigationBar = new NavigationBarObjects(driver);
        Assert.assertTrue(navigationBar.isActionSuccess(), "Admin Created");
        navigationBar.closeAlert();
        navigationBar.signOut();

    }

//    @Test(dependsOnMethods = { "createAdmin" })
    @Test
    public void uploadTopo() {

        System.out.println("This is uploadTopo");
        driver.get(loginURL);
        LoginPageObjects loginPage = new LoginPageObjects(driver);
        loginPage.login(adminName, adminPass);
        NavigationBarObjects navigationBar = new NavigationBarObjects(driver);
        navigationBar.goToTopology();
        TopoPageObjects topoPage = new TopoPageObjects(driver);
        topoPage.uploadTopo(topoFilePath);
        Assert.assertTrue(navigationBar.isActionSuccess(), "Topo Uploaded");
        navigationBar.closeAlert();

    }

//    @Test(dependsOnMethods = { "uploadTopo" })
    @Test
    public void deploySignature() {

        System.out.println("This is deploySignature");
        NavigationBarObjects navigationBar = new NavigationBarObjects(driver);
        navigationBar.goToRule();
        BlacklistPageObjects blacklistPage = new BlacklistPageObjects(driver);
        blacklistPage.deployBlacklistRule();
        Assert.assertTrue(navigationBar.isActionSuccess(), "Blacklist Deployed");
        navigationBar.closeAlert();

    }

    @Test
    public void deployIPMACBinding () {

        System.out.println("This is deployIPMACBinding");
        NavigationBarObjects navigationBar = new NavigationBarObjects(driver);
        navigationBar.goToIPMAC();
        IPMACBindingPageObjects ipmacBindingPage = new IPMACBindingPageObjects(driver);
        ipmacBindingPage.deployIPMACBindingRule();
        Assert.assertTrue(navigationBar.isActionSuccess(), "IpMacBindingRule Deployed");
        navigationBar.closeAlert();

    }

    @Test
    public void deployWhitelist () throws InterruptedException {

        System.out.println("This is deployWhitelist");
        SimulatorUtil simulator = new SimulatorUtil();
        NavigationBarObjects navigationBar = new NavigationBarObjects(driver);
        navigationBar.goToWhitelist();
        WhitelistPageObjects whitelistPage = new WhitelistPageObjects(driver);
        whitelistPage.startLearning();
        Assert.assertTrue(navigationBar.isActionSuccess(), "Learning Started");
        navigationBar.closeAlert();

        simulator.connectSimulator(simulatorIp,testServerIp);
        simulator.sendModbusFlowData4(simulatorIp,testServerIp);
        whitelistPage.finishLearning();
        Assert.assertTrue(navigationBar.isActionSuccess(), "Learning Stopped");
        navigationBar.closeAlert();
        whitelistPage.deployWhitelistRule();
        Assert.assertTrue(navigationBar.isActionSuccess(), "Whitelist Deployed");
        navigationBar.closeAlert();

        Thread.sleep(3000);

    }

}
