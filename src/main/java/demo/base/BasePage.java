package demo.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import demo.utils.OptionsManager;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsmanager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize driver basis of given driver...
	 * 
	 * @param browser
	 * @return
	 */
	public WebDriver Base_init(String browser) {
		// browser = prop.getProperty("browser");
		// System.out.println("value of browser is : "+browser);
		highlight = prop.getProperty("highlight");
		optionsmanager = new OptionsManager(prop);
		String browserVersion = prop.getProperty("browserversion").trim();
		if (browser.equalsIgnoreCase("chrome")) {

//Remote
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			} else {
//Local
				tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
				// driver = new ChromeDriver();
			}
		} else if (browser.equalsIgnoreCase("firefox")) {

//Remote		
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			} else {
				// driver = new SafariDriver();
//Local
				tlDriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
			}
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Constant.Time_Out,
		// TimeUnit.SECONDS);
		return getDriver();
	}

	/**
	 * RemoteWebdriver configuration
	 * 
	 * @param browser
	 */
	private void init_remoteDriver(String browser) {
		if (browser.equals("chrome")) {

			/*
			 * USE BELOW ONLY IF YOU ARE USING 3.*.* VERSION OF SELENIUM // Selenium 3.x.x
			 * // DesiredCapabilities cap = DesiredCapabilities.chrome();
			 * 
			 * // Selenium 4.x.x /* in 4.x.x desiredcap is depricated so we are passing
			 * capabilities in chromoption DesiredCapabilities cap = new
			 * DesiredCapabilities();
			 * 
			 * cap.setBrowserName("chrome"); /* THIS THING TO SET FOR SELENOID
			 */
			/*
			 * cap.setCapability("browserName", "chrome");
			 * cap.setCapability("browserVersion", browserVersion);
			 * cap.setCapability("enableVNC", true);
			 * cap.setCapability(ChromeOptions.CAPABILITY,
			 * optionsmanager.getChromeOptions());
			 */

			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		} else if (browser.equals("firefox")) {
//Selenium 3.x.x
			// DesiredCapabilities cap = DesiredCapabilities.firefox();

//Selenium 4.x.x
			// DesiredCapabilities cap = new DesiredCapabilities();
			// cap.setBrowserName("firefox");
			/*
			 * THIS THING TO SET FOR SELENOID cap.setCapability("browserName", "firefox");
			 * cap.setCapability("browserVersion", "57.0"); cap.setCapability("enableVNC",
			 * true);
			 */
			// cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS,
			// optionsmanager.getFirefoxOptions());
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getFirefoxOptions()));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * Get driver using thread local
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties Base_properties() {
		try {
			prop = new Properties();
			// for MacBook
			FileInputStream file = new FileInputStream("/Users/rajatkumarprajapati/eclipse-workspace/Demoapp/src"
					+ "/main/java/demo/config/config.properties");
//			System.out.println(System.getProperty("user.dir")+"\\src\\main\\java\\demo\\config\\config.properties");
//			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\demo\\config\\config.properties");

			prop.load(file);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return prop;
	}

	/**
	 * this method is use to take screenshot it will return path of screenshot
	 */
	public String getScreenshot() {
		File Srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		// for macbook
//		String path = "/Users/rajatkumarprajapati/eclipse-workspace/Demoapp/screenshots/" + System.currentTimeMillis()
//		+ ".png";

		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(Srcfile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
