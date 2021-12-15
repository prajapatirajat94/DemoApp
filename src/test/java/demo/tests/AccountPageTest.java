package demo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import demo.base.BaseTest;
import demo.utils.Constant;

public class AccountPageTest extends BaseTest {

@BeforeClass
public void AccountPagesetup() {
	accountpage =loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
}
@Test(priority=1)
public void verifyaccountpageTest() {
	String page =accountpage.verifyAccountpage();
	Assert.assertEquals(page, Constant.Get_Accountpage_Title);
}
@Test(priority=2)
public void verifyMyaccountsectionCounttest() {
	accountpage.getAccountSectionaccount();
	Assert.assertTrue(accountpage.getAccountSectionaccount()== Constant.Account_Section);
}
@Test(priority=3)
public void verifyMyaccountsectionlistTest() {
	accountpage.getaccountsectionlist();
	Assert.assertEquals(accountpage.getaccountsectionlist(), Constant.getAccountSectionlist());
}
@Test(priority=4)
public void Searchtest() {
	Assert.assertTrue(accountpage.dosearch("imac"));
}
}
