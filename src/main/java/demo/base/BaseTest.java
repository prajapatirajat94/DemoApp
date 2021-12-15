package demo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import demo.page.AccountPage;
import demo.page.LoginPage;
import demo.page.ProductInfoPage;

public class BaseTest {
	public LoginPage loginpage;
	public BasePage basepage;
	public AccountPage accountpage;
	public ProductInfoPage productinfopage;
	public WebDriver driver;
	public Properties prop;
	
@BeforeTest
public void Setup() {	
	basepage = new BasePage();
	prop =basepage.Base_properties();
	/*String browser = prop.getProperty("browser");
	System.out.println(browser);*/
	driver =basepage.Base_init(prop.getProperty("browser"));
    loginpage = new LoginPage(driver);
    driver.get(prop.getProperty("url"));
	}
@AfterTest
public void teardown() {
 driver.quit();
}
}
