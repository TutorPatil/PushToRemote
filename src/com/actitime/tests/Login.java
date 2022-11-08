package com.actitime.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.actitime.base.BaseClass;
import com.actitime.utils.CommonUtils;

public class Login extends BaseClass{	
	
	
	@Test
	public static void login_001() throws IOException
	{
		try{
			writeLogs(" Starting the test case Login_001");					
			boolean result = CommonUtils.loginToActiTimeApplication();			
			writeLogs(" The actitime login is "+result);
			
			CommonUtils.selectModule("Tasks");
			Thread.sleep(2000);
			CommonUtils.selectModule("Users");
			if(result)			
				writeResults("Login_001", "Pass");			
			else			
			{
				writeResults("Login_001", "Fail");
				captureScreenShotOnFailure("Login_001");
			}
		}catch(Exception e)
		{
			e.printStackTrace();			
			writeResults("Login_001", "Fail");
			captureScreenShotOnFailure("Login_001");
		}
		closeDriver();
		
	}
	
	public static void login_002() throws IOException
	{
		try{
			writeLogs(" Starting the test case Login_002");
			launchApplication();	
			CommonUtils.loginToActiTimeApplication("admin123", "manager12");
			
			boolean result = driver.findElement(By.xpath(getLocatoDataFromExcel("LoginPage", "ErrorMessageText"))).isDisplayed();
			
			writeLogs(" Error message is seen now ending the login 002 test case");
			
			if(result)			
				writeResults("Login_002", "Pass");			
			else			
			{
				writeResults("Login_002", "Fail");
				captureScreenShotOnFailure("Login_002");
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			writeResults("Login_002", "Fail");
			captureScreenShotOnFailure("Login_002");
		}
		
		closeDriver();
	}

	
	public static void login_003() throws IOException
	{
		try{
			writeLogs(" Starting the test case Login_002");
			launchApplication();	
			CommonUtils.loginToActiTimeApplication("admin123", "manager12");
			
			boolean result = driver.findElement(By.xpath(getLocatoDataFromExcel("LoginPage", "ErrorMessageText"))).isDisplayed();
			
			writeLogs(" Error message is seen now ending the login 002 test case");
			
			if(result)			
				writeResults("Login_002", "Pass");			
			else			
			{
				writeResults("Login_002", "Fail");
				captureScreenShotOnFailure("Login_002");
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			writeResults("Login_002", "Fail");
			captureScreenShotOnFailure("Login_002");
		}
		
		closeDriver();
	}

	
}
