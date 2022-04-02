package demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import demo.base.BaseTest;
import demo.utils.Constant;

public class LoginPageTest extends BaseTest {

@Test(priority=3)
public void Logincheck() {
	loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}
@Test(priority=2)
public void Titlecheck() {
	String title =loginpage.getTitle();
	Assert.assertEquals(title,Constant.Get_Login_Title);
}
@Test(priority=4)
public void verifyAccountpageNavigationTest() {
	String homepage =loginpage.verifyHomePage();
	Assert.assertEquals(homepage,Constant.Get_Accountpage_Title);
}
@Test(priority=1)
public void Forgetpasswordlink() {
	Assert.assertTrue(loginpage.Accountfrgtpasswordvisible());
	}
	
}
