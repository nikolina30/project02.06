import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CustomerPage extends BasePage {

    public CustomerPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }
    public void customerLogIn() {
        WebElement customerLogInButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[1]/button"));
        customerLogInButton.click();
        Select userSelect = new Select(driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSelect"))));
        userSelect.selectByVisibleText("Nina Milosevic");
        WebElement loginButton = driverWait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/form/button"))));
        loginButton.click();
        WebElement userNameTitle = driverWait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[1]/strong/span"))));
        WebElement welcomeUserTitle = driverWait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[1]/strong"))));
        String welcomeUserName = "Welcome " + userNameTitle.getText() + " !!";
        Assert.assertTrue(welcomeUserTitle.getText().contains(welcomeUserName));
    }
    public void deposit() {
        try {
            WebElement depositTabButton = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")));
            depositTabButton.click();
            Thread.sleep(1000);
            WebElement amount = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")));
            amount.sendKeys("1000");
            Thread.sleep(1000);
            WebElement depositButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button"));
            depositButton.click();
            Thread.sleep(1000);
            WebElement despositSuccessfulTitle = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/span")));
            String despositTitle = "Deposit Successful";
            Assert.assertEquals(despositSuccessfulTitle.getText(), despositTitle);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void withdraw() {
        try {
            WebElement withdrawTabButton = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[3]")));
            withdrawTabButton.click();
            Thread.sleep(1000);
            WebElement amount = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")));
            amount.sendKeys("1000");
            Thread.sleep(1000);
            WebElement withdrawButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button"));
            withdrawButton.click();
            Thread.sleep(1000);
            WebElement withdrawSuccessfulTitle = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/span")));
            String withdrawTitle = "Transaction successful";
            Assert.assertEquals(withdrawSuccessfulTitle.getText(), withdrawTitle);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void customerLogout() {
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/div/div/div[1]/button[2]"));
        logoutButton.click();
        Select userSelect = new Select(driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSelect"))));
        Assert.assertNotNull(userSelect);
    }
}
