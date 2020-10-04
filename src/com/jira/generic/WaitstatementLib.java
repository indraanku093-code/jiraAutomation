package com.jira.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitstatementLib 

{

	WebDriver driver;
	
	/****************************Hard wait**************************/
	
	
	public void isleep(int sec)
	{
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	/***************************implicity wait **********************************/
	
	public void iwait(WebDriver driver,int sec)
	{
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}
	
	
	/*******************************Explicit Wait*********************************/
	
	public void ewait(int sec, WebElement ele)
	{
		WebDriverWait wait= new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
}
