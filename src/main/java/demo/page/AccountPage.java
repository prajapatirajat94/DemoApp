package demo.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import demo.base.BasePage;
import demo.utils.ElementUtil;

public class AccountPage extends BasePage {
private WebDriver driver;
public ElementUtil Util;
private By accountsectionheaders = By.cssSelector("div#content h2");
private By Searchtext = By.cssSelector("div#search input[name='search']");
private By Searchbutton = By.cssSelector("div#search button[type='button']");
private By searchiconresult = By.cssSelector(".product-layout .product-thumb");
private By ResultItems = By.cssSelector(".product-thumb h4 a");
public AccountPage(WebDriver driver) {
	this.driver= driver;
	Util = new ElementUtil(this.driver);
}
public String verifyAccountpage() {
	return driver.getTitle();
}
public int getAccountSectionaccount() {
	return Util.getElements(accountsectionheaders).size();
	//return driver.findElements(accountsectionheaders).size();
}
public List<String> getaccountsectionlist() {
	List<String>accountlist = new ArrayList<String>();
	//List<WebElement>accountsectionlist=driver.findElements(accountsectionheaders);
	List<WebElement>accountsectionlist=Util.getElements(accountsectionheaders);
	for(WebElement e:accountsectionlist) {
		accountlist.add(e.getText());
	}
	return accountlist;
}
public boolean dosearch(String productname) {
//	driver.findElement(Searchtext).sendKeys(productname);
	Util.doSendKeys(Searchtext, productname);
	//driver.findElement(Searchbutton).click();
	Util.doClick(Searchbutton);
	/*if(driver.findElements(searchiconresult).size()>0) {
		return true;
	}*/
	if(Util.getElements(searchiconresult).size()>0) {
		return true;
	}
	return false;
}
public ProductInfoPage Selectproductfromresult(String productname) {
	List<WebElement> product =Util.getElements(ResultItems);
	for(WebElement e: product) {
		if(e.getText().equals(productname)) {
			e.click();
			break;
		}
	}
	return new ProductInfoPage(driver);
}

}
