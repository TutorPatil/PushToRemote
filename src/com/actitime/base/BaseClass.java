package com.actitime.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestListener;
import org.testng.annotations.BeforeMethod;

import com.google.common.io.Files;



public class BaseClass {
	
	public static WebDriver driver;	

	@BeforeMethod(alwaysRun = true)
	public static void launchApplication() throws IOException
	{			
			String browser = getConfigData("browser");	
			
			switch (browser)
			{
			case "chrome":
			{
				System.setProperty("webdriver.chrome.driver", "./utilities/chromedriver.exe");
				driver = new ChromeDriver();	
				break;
			}
			case "firefox":
			{
				System.setProperty("webdriver.gecko.driver", "./utilities/geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			}
			default:
			{
				System.setProperty("webdriver.chrome.driver", "./utilities/chromedriver.exe");
				driver = new ChromeDriver();
				break;
			}
			
			}		
		
			// Applying implicit wait of 20 seconds
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			String url = getConfigData("url");
			driver.get(url);			
			driver.manage().window().maximize();
			
		
	}
	public static String getConfigData(String key) throws IOException
	{
		String value = "";
		
		File f = new File("./data/config.properties");
		
		FileInputStream fio = new FileInputStream(f);
		
		Properties prop = new Properties();
		prop.load(fio);
		
		value=prop.getProperty(key);
		
		return value;
		
		
	}
	
	public static void captureScreenShotOnFailure(String fileName) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File dest = new File("./results/screenshots/"+fileName+".png");
		
		Files.copy(src, dest);
		
		
	}
	
	
	
	
	public static String getLocatoDataFromExcel(String pageName, String elementName) throws IOException
	{
		String locator = "";
		
		File f = new File("./data/locatordata.xlsx");
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rows = ws.getLastRowNum();
		
		//System.out.println(rows);	
		
		for( int x=1; x<=rows; x++)
		{
			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();
			
			if((pageName.equalsIgnoreCase(page)) && (elementName.equalsIgnoreCase(element)))
					{
						locator = ws.getRow(x).getCell(2).getStringCellValue();
						break;
					}
			
		}
		
		wb.close();		
		return locator;
		
	}
	
	public static String getTestDataFromExcel(String pageName, String elementName) throws IOException
	{
		String data = "";
		
		File f = new File("./data/testdata.xlsx");
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rows = ws.getLastRowNum();
		
		//System.out.println(rows);	
		
		for( int x=1; x<=rows; x++)
		{
			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();
			
			if((pageName.equalsIgnoreCase(page)) && (elementName.equalsIgnoreCase(element)))
					{
						data = ws.getRow(x).getCell(2).getStringCellValue();
						break;
					}
			
		}
		
		wb.close();		
		return data;
		
	}

	public static void writeResults(String testCaseID, String status) throws IOException
	{
		File f = new File("./results/results.txt") ;
		
		FileWriter fw = new FileWriter(f,true);
		
		fw.write(testCaseID+" ------ "+status+"\n");
		fw.flush();
		fw.close();
		writeLogs(testCaseID+"----"+status);
		
	}
	
	public static void writeLogs(String msg) throws IOException
	{
		File f = new File("./results/logs.txt") ;
		
		FileWriter fw = new FileWriter(f,true);
		
		fw.write(msg+"\n");
		System.out.println(msg);
		fw.flush();
		fw.close();
		
	}
	
	public static void closeDriver()
	{
		driver.quit();
	}
	
	enum ModuleName{
		Tasks,
		Users,
		Reports,	
		
	}
	
}
