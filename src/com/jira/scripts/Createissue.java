package com.jira.scripts;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.jira.generic.WaitstatementLib;



public class Createissue  

{
   
	public static WebDriver driver;
	
    @Test
	public void createIssue() throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException
	{
		
		
		//file input from the excel sheet 1 for login and browser
		
		String filepath = "./testdata/jiratestscriptdata.xlsx";
		Workbook wb=WorkbookFactory.create(new FileInputStream(new File(filepath)));
		Row rw1 = wb.getSheet("sheet1").getRow(1);
		String brn = rw1.getCell(0).getStringCellValue();
		System.out.println(brn);
		String url=rw1.getCell(1).getStringCellValue();
		System.out.println(url);
		String loginid=rw1.getCell(2).getStringCellValue();
		System.out.println(loginid);
		String password=rw1.getCell(3).getStringCellValue();
		System.out.println(password);
		int countrow = (int) wb.getSheet("sheet1").getRow(5).getCell(1).getNumericCellValue();
		System.out.println(countrow);
		
		//*************************************Wait Statement***************************************
		
		
		WaitstatementLib w= new WaitstatementLib();
		
		
		//*******************************************************************************************************
		
		if(brn.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
		driver = new ChromeDriver();
		}
		else if(brn.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "./exefiles/");
			driver = new InternetExplorerDriver();
		}
		else if(brn.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./exefiles/");
			driver = new FirefoxDriver();
		}
    	driver.manage().window().maximize();
    	driver.get(url);
		
		driver.findElement(By.id("google-signin-button")).click();
		
		//driver.findElement(By.linkText("Use another account")).click();
		
		driver.findElement(By.id("identifierId")).sendKeys(loginid);
		w.isleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		w.isleep(5000);
		driver.findElement(By.name("password")).sendKeys(password);
		w.isleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		w.isleep(50000);
		
		
		//*********************************jira  open**************
		
		
		
		//*********************************create Issues*******************************************
		
		
		Sheet sh2 = wb.getSheet("sheet2");
		for (int i = 1; i <=countrow; i++) 
		{	
		
		driver.findElement(By.xpath("//span[@aria-label='Create']")).click();
		
		
		Row rw2 = sh2.getRow(i);
		   
		    
		    // ******************************** selection of project ***************************
     	
		    w.isleep(5000);
		    WebElement pf = driver.findElement(By.id("project-field"));
			
			pf.click();
			pf.sendKeys(Keys.DELETE);
			pf.sendKeys(rw2.getCell(0).getStringCellValue());
			pf.sendKeys(Keys.ENTER);
			
			//***********selection of issue type********************************
			
			w.isleep(2000);
			WebElement ist = driver.findElement(By.id("issuetype-field"));
			
			ist.click();
			ist.sendKeys(Keys.DELETE);
			ist.sendKeys(rw2.getCell(1).getStringCellValue());
			ist.sendKeys(Keys.ENTER);   			
			w.isleep(2000);
			
			//***************** Summary(title of the issue)*******************************
			
			
			driver.findElement(By.id("summary")).sendKeys(rw2.getCell(2).getStringCellValue());
			w.isleep(2000);
		
			
			//********************** Reporter *******************************************
			
			WebElement re = driver.findElement(By.id("reporter-field"));
			
            re.click();
			re.sendKeys(Keys.DELETE);
			re.sendKeys(rw2.getCell(3).getStringCellValue());
			re.sendKeys(Keys.ENTER);
			w.isleep(2000);
			
			
           //***********************************scroll *******************************************************
			

//			JavascriptExecutor jse = (JavascriptExecutor)driver;
//			jse.executeScript("scroll(0, 250)");
//			w.isleep(2000);
			
			
			//*****************************Component*****************************
			
			//driver.findElement(By.id("components-multi-select")).sendKeys(rw2.getCell(4).getStringCellValue());
			
			
			//***************************catogory************************
			
			WebElement cat = driver.findElement(By.id("customfield_19000"));
			Select sc= new Select(cat);
			sc.selectByVisibleText(rw2.getCell(5).getStringCellValue());
			w.isleep(2000);
			
			//******************************* Description**********************************************
			
			
			driver.findElement(By.id("description")).sendKeys(rw2.getCell(6).getStringCellValue());
			w.isleep(2000);
			
			
			
			
			//**************************************** priority ***********************************************
			
			
			WebElement pr = driver.findElement(By.id("priority-field"));
			
			pr.click();
			pr.sendKeys(Keys.DELETE);
			pr.sendKeys(rw2.getCell(7).getStringCellValue());
			pr.sendKeys(Keys.ENTER);
			w.isleep(2000);
			
			//**************************************Environement *****************************
			
			
			driver.findElement(By.id("environment")).sendKeys(rw2.getCell(8).getStringCellValue());
			w.isleep(2000);
			
			
			//**********************************************Assignee****************************
			
			
			WebElement assignee = driver.findElement(By.id("assignee-field"));
			
			assignee.click();
			assignee.sendKeys(Keys.DELETE);
			assignee.sendKeys(rw2.getCell(9).getStringCellValue());
			assignee.sendKeys(Keys.ENTER);
			w.isleep(2000);
			
			//******************************Sprint********************************
			
			
//			WebElement sp = driver.findElement(By.id("customfield_12300-field"));
//			act.doubleClick(sp).perform();
//			sp.click();
//			sp.sendKeys(Keys.DELETE);
//			sp.sendKeys(rw2.getCell(10).getStringCellValue());
//			sp.sendKeys(Keys.ENTER);
			
			
			//***************************************************Click On Create*****************************************
			
			
			//driver.findElement(By.id("create-issue-submit")).click();
			
			
			
	   }
		
		
		// ***********************************************Close the windows************************************************
		
		
		driver.quit();
		
	
	}
	
	
	
}
