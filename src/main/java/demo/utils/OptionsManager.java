package demo.utils;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
public OptionsManager(Properties prop) {
	this.prop=prop;
}
public ChromeOptions getChromeOptions() {
	
	 co = new ChromeOptions();
	 //co.addArguments("--no-sandbox"); // By pass Os security model
     //co.addArguments("--disable-dev-shm-usage"); //overcome limited resources problem	 
	co.addArguments("--remote-allow-origins=*");	
	//below code will be executed if remote=true
	 if (Boolean.parseBoolean(prop.getProperty("remote"))) {
		 
	            if(Boolean.parseBoolean(prop.getProperty("selenoidgrid"))) {	 
	                 co.setBrowserVersion(prop.getProperty("browserversion")); 
	                  HashMap<String,Object> addcap= new HashMap();
	                  addcap.put("enableVNC", true);	 
	                  co.setCapability("selenoid:options",addcap);
	                  }
	 co.setCapability("browserName", "chrome");
	 //or 
	// co.setCapability("enableVNC", true);
	 }
	 
	 
	 
	 if(prop.getProperty("headless").trim().equals("true"))co.addArguments("--headless"); 
	 if(prop.getProperty("incongnito").trim().equals("true"))co.addArguments("--incongnito");
	 return co;
}
public FirefoxOptions getFirefoxOptions() {
	 fo = new FirefoxOptions();
	 fo.addArguments("--remote-allow-origins=*");
	 if(prop.getProperty("headless").trim().equals("true"))fo.addArguments("--headless"); 
	 if(prop.getProperty("incongnito").trim().equals("true"))fo.addArguments("--incongnito");
	 return fo;
}
}
