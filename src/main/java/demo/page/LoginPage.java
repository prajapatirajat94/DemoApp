package demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import demo.base.BasePage;
import demo.utils.ElementUtil;

public class LoginPage extends BasePage {
private WebDriver driver;
public ElementUtil Util;
private By username = By.id("input-email");
private By password = By.id("input-password");
private By signinclick = By.xpath("//input[@value ='Login']");
private By forgetpassword = By.linkText("Forgotten Password");
public LoginPage(WebDriver driver) {	
    this.driver = driver;
	Util = new ElementUtil(this.driver);
}
public String getTitle() {
	Util.getPageTitle("Account Login", 20);
	return driver.getTitle();
}
public AccountPage doLogin(String email,String passwords) {
	Util.doSendKeys(username,email);
	Util.doSendKeys(password, passwords);
	Util.doClick(signinclick);
	return new AccountPage(driver);
}
public String verifyHomePage() {
	Util.getPageTitle("My Account", 20);
	return driver.getTitle();
	 
}
public boolean Accountfrgtpasswordvisible() {
	return Util.getElement(forgetpassword).isDisplayed();
}

}
