package com.shaip.basetest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import com.shaip.factories.DriverBase;
import com.shaip.page.cp.LoginPage_CP;
import com.shaip.page.ep.LoginPage_EP;
import com.shaip.utils.EmailUtility;
import com.shaip.utils.PropertiesOperationsShaip;

public class BaseTest extends TestCommons {
	
	//String browser= System.getProperty("browser");
	protected BaseTest() {
		
	}
	
	    public SoftAssert sa = new SoftAssert();
	    public LoginPage_CP loginPagecp;
		public LoginPage_EP loginPage;
	


	
	@BeforeMethod(alwaysRun = true)
	protected void launchShaipCloud() { 
		
		String browser = PropertiesOperationsShaip.getPropertyValueByKey("browser");
		
		
		System.out.println("Before invoked method************************");

		try {
			DriverBase.initDrivers(browser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		loginPagecp = new LoginPage_CP();
		loginPage = new LoginPage_EP();
		System.out.println("Driver invoked************************");
	}

	/**
	 * Terminates the browser instance
	 */
	
	@AfterMethod(alwaysRun = true)
	protected void tearDown() {
		
		System.out.println("Gonna quite drivers");

		DriverBase.quitDriver();
	}
	

	/*@AfterSuite(alwaysRun = true)
	protected void sendemail() throws Exception {
		
		System.out.println("Gonna send email");

		EmailUtility email=new EmailUtility(); 
		System.out.println("Email is gonna be send fro after suit");

		email.doSendEmail();
	}*/
	

	  protected static void zipFile(String filePath) {
	        try {
	            File file = new File(filePath);
	            String zipFileName = file.getName().concat(".zip");
	 
	            FileOutputStream fos = new FileOutputStream(zipFileName);
	            ZipOutputStream zos = new ZipOutputStream(fos);
	 
	            zos.putNextEntry(new ZipEntry(file.getName()));
	 
	            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
	            zos.write(bytes, 0, bytes.length);
	            zos.closeEntry();
	            zos.close();
	            System.out.println("Zip file created");
	 
	        } catch (FileNotFoundException ex) {
	            System.err.format("The file %s does not exist", filePath);
	        } catch (IOException ex) {
	            System.err.println("I/O error: " + ex);
	        }
	    }


	  protected static void rarFile(String filePath) throws RarException, IOException {
		  
		  File f = new File(filePath);
          Archive archive = new Archive(f);
          archive.getMainHeader().print();
          FileHeader fh = archive.nextFileHeader();
          while(fh!=null){        
                  File fileEntry = new File(fh.getFileName());
                  System.out.println(fileEntry.getAbsolutePath());
                  FileOutputStream os = new FileOutputStream(fileEntry);
                  archive.extractFile(fh, os);
                  os.close();
                  fh=archive.nextFileHeader();
          }
	  
		
	  }
	  
	  protected String getDate()

		{

			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyHHmmss");
			Date date = new Date();
			String actualDate = format.format(date);
			return actualDate;

		}

}
