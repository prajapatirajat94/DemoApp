package demo.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import demo.base.BaseTest;

public class ProductInfoTests extends BaseTest {

@BeforeClass
public void productinfosetup() {
	accountpage =loginpage.doLogin(prop.getProperty("username"), "password");	
}

@Test
public void verifyProductinfoTest_Macbook() {	
		
	Assert.assertTrue(productinfopage.getproductimages()==4);
	Map<String, String>productinformap=productinfopage.getproductinfo();
	System.out.println(productinformap);
	//{Brand=Apple, Availability=Out Of Stock, price=$2,000.00, name=MacBook Pro, Product Code=Product 18, 
	//Reward Points=800, exTaxPrice=$2,000.00}
	Assert.assertEquals(productinformap.get("name"),"MacBook Pro" );
	Assert.assertEquals(productinformap.get("Brand"),"Apple" );
	Assert.assertEquals(productinformap.get("Product Code"),"Product 18" );
	Assert.assertEquals(productinformap.get("price"),"$2,000.00" );
	Assert.assertEquals(productinformap.get("Reward Points"),"800" );
}
@Test
public void productinfotitleTest() {
	accountpage.dosearch("Macbook");
	productinfopage=accountpage.Selectproductfromresult("MacBook Pro");
	productinfopage.getproductinfopagetitle("MacBook Pro");
	Assert.assertEquals(productinfopage.getproductinfopagetitle("MacBook Pro"), "MacBook Pro");
}
}
