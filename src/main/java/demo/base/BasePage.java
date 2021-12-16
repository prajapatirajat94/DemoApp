package demo.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import demo.utils.Constant;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
public WebDriver driver;
public Properties prop;
public static ThreadLocal<WebDriver>tlDriver = new ThreadLocal<WebDriver>();

/**
 * This methos is used to initialize driver basis of 
 * given driver...
 * @param browser
 * @return
 */
public WebDriver Base_init(String browser) {
	//browser = prop.getProperty("browser");
	//System.out.println("value of browser is : "+browser);
	if(browser.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		tlDriver.set(new ChromeDriver());
		//driver = new ChromeDriver();
	}
	else if(browser.equalsIgnoreCase("safari")) {
		WebDriverManager.safaridriver().setup();
		//driver = new SafariDriver();
		tlDriver.set(new SafariDriver());
	}
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	//driver.manage().timeouts().implicitlyWait(Constant.Time_Out, TimeUnit.SECONDS);
	return getDriver();
}
public static synchronized WebDriver getDriver() {
	return tlDriver.get();
}
public Properties Base_properties() {
	try {
		prop = new Properties();
	FileInputStream file = new FileInputStream("C:\\Users\\praja\\eclipse-workspace\\DemoApp"
			+ "\\src\\main\\java\\demo\\config\\config.properties");
	
	prop.load(file);
	}
	catch(IOException e) {
		e.getStackTrace();
	}
	
	return prop;
}
/**
 * this method is use to take screenshot
 * it will return path of screenshot
 */
public String getScreenshot() {
	File Srcfile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	String path ="C:\\Users\\praja\\eclipse-workspace\\DemoApp\\screenshots\\"+System.currentTimeMillis()+".png";
	try {
		FileUtils.copyFile(Srcfile, new File(path));
	} catch (IOException e) {
		e.printStackTrace();
	}
	return path;
}
}
