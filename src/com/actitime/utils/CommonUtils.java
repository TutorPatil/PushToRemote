package com.actitime.utils;

import java.io.IOException;

import org.openqa.selenium.By;

import com.actitime.base.BaseClass;

public class CommonUtils extends BaseClass {
	
	public static boolean loginToActiTimeApplication() throws IOException
	{		
		driver.findElement(By.xpath(getLocatoDataFromExcel("LoginPage", "UserNameEditBox"))).
		sendKeys("admin");
		driver.findElement(By.xpath(getLocatoDataFromExcel("LoginPage", "PasswordEditBox"))).
		sendKeys("manager");
		driver.findElement(By.xpath(getLocatoDataFromExcel("LoginPage", "LoginButton"))).click();
		boolean isDisplayed = false;
		try{
			isDisplayed = driver.findElement(By.xpath(getLocatoDataFromExcel("HomePage", "LogoutLink"))).isDisplayed();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return isDisplayed;
	}
	

	public static void loginToActiTimeApplication(String userName, String password) throws IOException
	{
		driver.findElement(By.xpath(getLocatoDataFromExcel("LoginPage", "UserNameEditBox"))).
		sendKeys(userName);
		driver.findElement(By.xpath(getLocatoDataFromExcel("LoginPage", "PasswordEditBox"))).
		sendKeys(password);
		driver.findElement(By.xpath(getLocatoDataFromExcel("LoginPage", "LoginButton"))).click();
	}
	
	
	
	public static void selectModule(String moduleName) throws IOException
	{
		String xpath = getLocatoDataFromExcel("HomePage", "ModuleNameTab");
		String xpath1 = xpath.replace("--TEXTREPLACE--", moduleName);
		
		driver.findElement(By.xpath(xpath1)).click();
	}
	

}
