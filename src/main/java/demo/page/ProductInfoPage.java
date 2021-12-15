package demo.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import demo.utils.ElementUtil;

public class ProductInfoPage {
public ElementUtil Util;
private WebDriver driver;
private By Productnameheader = By.cssSelector("div#content h1");
private By ProductMetadata = By.cssSelector("div#content .list-unstyled:nth-of-type(1) li");
private By Productprice = By.cssSelector("div#content .list-unstyled:nth-of-type(2) li");
private By quantity = By.id("input-quantity");
private By addToCart = By.id("button-cart");
private By productimages = By.cssSelector(".thumbnails li img");
public ProductInfoPage(WebDriver driver) {
	this.driver= driver;
	Util = new ElementUtil(this.driver);
}
public String getproductinfopagetitle(String productname) {
	return Util.waitForTitleIs(productname, 20);
}
public Map<String, String> getproductinfo() {
	Map<String,String> Productinfomap = new HashMap<>();
	Productinfomap.put("name", Util.doGetText(Productnameheader));
	List<WebElement> productmetadatalist=Util.getElements(ProductMetadata);
	for(WebElement e: productmetadatalist) {
		Productinfomap.put(e.getText().split(":")[0].trim(),e.getText().split(":")[1].trim() );
	}
	List<WebElement> ProductpriceList =Util.getElements(Productprice);
		Productinfomap.put("price",ProductpriceList.get(0).getText() );
		Productinfomap.put("exTaxPrice",ProductpriceList.get(1).getText().split(":")[1].trim());
		return Productinfomap;
}
public void SelectQuantity(String Quantity) {
	Util.doSendKeys(quantity, Quantity);
}
public void addtocart() {
	Util.doClick(addToCart);
}
public int getproductimages() {
	 int imagescount= Util.getElements(productimages).size();
	 System.out.println("Total number of images for product : "+imagescount);
	 return imagescount;
}

}
