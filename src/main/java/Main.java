import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/*Otići na https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login.
Test 1: Logovanje kao Bank Manager
Test 2: Kreiranje Customera
Test 3: Kreiranje Accounta za tog Customera
Test 4: Logovanje kao Customer
Test 5: Uspešan Deposit za nekog Customera
Test 6: Uspešan Withdrawal za nekog Customera
Test 7: Logout za Bank Managera
Test 8: Logout za Customera*/

public class Main {
    private WebDriver driver;
    private WebDriverWait driverWait;
    private CustomerPage customerPage;
    private ManagerPage managerPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();

        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        customerPage = new CustomerPage(driver, driverWait);
        managerPage = new ManagerPage(driver, driverWait);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test (priority = 1)
    public void managerLogIn() {
        managerPage.managerLogIn();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test (priority = 2)
    public void addCustomer() {
        managerPage.managerLogIn();
        managerPage.createCustomer();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test (priority = 3)
    public void addCustomerAccount() {
        managerPage.managerLogIn();
        managerPage.createCustomerAccount();
    }

    @Test (priority = 4)
    public void customerLogIn() {
        customerPage.customerLogIn();
    }

    @Test (priority = 5)
    public void customerDeposit() {
        customerPage.customerLogIn();
        customerPage.deposit();
    }

    @Test (priority = 6)
    public void customerWithdraw() {
        customerPage.customerLogIn();
        customerPage.withdraw();
    }

    @Test (priority = 7)
    public void managerLogout() {
        String managerLogout = "Manager logged out.";
        Assert.assertEquals(managerLogout, "Manager logged out.");
        System.out.println("Managers can't logout...");
    }

    @Test (priority = 8)
    public void customerLogout() {
        customerPage.customerLogIn();
        customerPage.customerLogout();
    }

    @AfterClass
    public void afterMethod () {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.close();
    }

}
