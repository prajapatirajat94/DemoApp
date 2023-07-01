package demo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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

@Parameters({"browser","browserversion"})
@BeforeTest
public void Setup(String browser,String browserVersion) {	
	basepage = new BasePage();
	prop =basepage.Base_properties();
 
	if(browser!=null) {
	 prop.setProperty("browser", browser);
	 prop.setProperty("browserversion", browserVersion);
	 }
 

	driver =basepage.Base_init(prop.getProperty("browser"));
    loginpage = new LoginPage(driver);
    driver.get(prop.getProperty("url"));
	}
@AfterTest
public void teardown() {
 driver.quit();
}
}
