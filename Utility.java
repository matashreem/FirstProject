
package Scenarios_Reg;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.TestListenerAdapter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

import Decoder.BASE64Encoder;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Image;
import word.w2004.elements.Paragraph;

class Utility extends TestListenerAdapter implements WebDriverEventListener, locators, ITestListener
{

		static List<String> list=null;
		static String excelpath=System.getProperty("user.dir") + "\\src\\Scenarios_Reg\\Regression_Test_Result.xls";
		static String Local_Driver_IE=System.getProperty("user.dir") + "\\lib\\IEDriverServer.exe";  // IEDriverServer3.3_64_bit IEDriverServer_x64_3.5.1.exe

		// ********************* Event Listener Methods ***********************//
		public void afterChangeValueOf(WebElement arg0,WebDriver arg1)
		{
		}

		public void afterClickOn(WebElement arg0,WebDriver arg1)
		{
		}

		public void afterFindBy(By arg0,WebElement arg1,WebDriver arg2)
		{
		}

		public void beforeFindBy(By arg0,WebElement arg1,WebDriver arg2)
		{
				takeScreenShotMethod(arg2);
				WebDriverWait wait=new WebDriverWait(arg2, t);
				try
				{
						waitForInvisibilityOfLoadnIcon(wait,arg2);
						WaitForVisibilyElement(wait,arg0,arg2);
						waitForInvisibilityOfLoadnIcon(wait,arg2);
				}
				catch (Throwable ee)
				{
				}
		}

		public void afterNavigateBack(WebDriver arg0)
		{
		}

		public void afterNavigateForward(WebDriver arg0)
		{
		}

		public void afterNavigateTo(String arg0,WebDriver arg1)
		{
		}

		public void afterScript(String arg0,WebDriver arg1)
		{
		}

		public void beforeChangeValueOf(WebElement arg0,WebDriver arg1)
		{
		}

		public void beforeClickOn(WebElement arg0,WebDriver arg1)
		{
		}

		public void beforeNavigateBack(WebDriver arg0)
		{
		}

		public void beforeNavigateForward(WebDriver arg0)
		{
		}

		public void beforeNavigateTo(String arg0,WebDriver arg1)
		{
		}

		public void beforeScript(String arg0,WebDriver arg1)
		{
		}

		public void onException(Throwable arg0,WebDriver arg1)
		{
				try
				{
						takeScreenShotMethod(arg1);
						System.err.println(checkAlert(arg1));
				}
				catch (Throwable e)
				{
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		}

		// ************************* Wait for Loading Functions ***************//
		public static void WaitForVisibilyElement(WebDriverWait wait,By arg0,WebDriver arg1) throws Throwable
		{
				for (int count=1; count <= 2; count++)
				{
						try
						{
								WebElement staledElement=arg1.findElement(arg0);
								staledElement.getLocation();
								staledElement.sendKeys(Keys.TAB);
						}
						catch (Exception test)
						{
								try
								{
										JavascriptExecutor jse=(JavascriptExecutor) arg1;
										jse.executeScript("arguments[0].scrollIntoView(false);",arg1.findElement(arg0));
								}
								catch (Exception e)
								{
										continue;
								}
								continue;
						}
						break;
				}
		}

		public static void waitForInvisibilityOfLoadnIcon(WebDriverWait wait,WebDriver arg2) throws Throwable
		{
				try
				{
						String Temp_driver="default";
						try
						{
								JavascriptExecutor js=(JavascriptExecutor) arg2;
								Temp_driver=js.executeScript("return window.frameElement.id;").toString();
						}
						catch (org.openqa.selenium.WebDriverException e)
						{
								Temp_driver="default";
						}
						arg2.switchTo().defaultContent();
						WebDriverWait wait1=new WebDriverWait(arg2, 60);
						boolean test=wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[contains(@src,'pleaseWait.gif')]")));
						if (test == false)
						{
								Thread.sleep(60000);
						}
						if (Temp_driver.equals("default"))
						{
								arg2.switchTo().defaultContent();
						}
						else if (Temp_driver.equals("__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f"))
						{
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
						}
						else if (Temp_driver.equals("s_bes_aae"))
						{
								try
								{
										arg2.findElement(By.xpath("//iframe[contains(@id,'s_bes_aae')]")).isDisplayed();
										arg2.switchTo().frame(arg2.findElement(By.xpath("//iframe[contains(@id,'s_bes_aae')]")));
								}
								catch (Exception e)
								{
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'s_bes_aae')]")));
								}
						}
						else if (Temp_driver.equals("s_buy_aae"))
						{
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'s_buy_aae')]")));
						}
						else if (Temp_driver.equals("header:s_bes_aae"))
						{
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'s_bes_aae')]")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'header:s_bes_aae')]")));
						}
				}
				catch (Exception e)
				{
				}
		}

		public static String checkAlert(WebDriver arg2) throws Throwable
		{
				String MSG_On_Alert=null;
				;
				try
				{
						WebDriverWait wait=new WebDriverWait(arg2, t);
						wait.until(ExpectedConditions.alertIsPresent());
						try
						{
								String Temp_driver="default";
								try
								{
										JavascriptExecutor js=(JavascriptExecutor) arg2;
										Temp_driver=js.executeScript("return window.frameElement.id;").toString();
								}
								catch (org.openqa.selenium.WebDriverException e)
								{
										Temp_driver="default";
								}
								Alert alert=arg2.switchTo().alert();
								MSG_On_Alert=alert.getText();
								alert.accept();
								arg2.switchTo().defaultContent();
								if (Temp_driver.equals("default"))
								{
										arg2.switchTo().defaultContent();
								}
								else if (Temp_driver.equals("__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f"))
								{
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
								}
								else if (Temp_driver.equals("s_bes_aae"))
								{
										try
										{
												arg2.findElement(By.xpath("//iframe[contains(@id,'s_bes_aae')]")).isDisplayed();
												arg2.switchTo().frame(arg2.findElement(By.xpath("//iframe[contains(@id,'s_bes_aae')]")));
										}
										catch (Exception e)
										{
												wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
												wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'s_bes_aae')]")));
										}
								}
								else if (Temp_driver.equals("s_buy_aae"))
								{
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'s_buy_aae')]")));
								}
								else if (Temp_driver.equals("header:s_bes_aae"))
								{
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'s_bes_aae')]")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'header:s_bes_aae')]")));
								}
						}
						catch (Exception e)
						{
						}
				}
				catch (Exception e)
				{
				}
				return MSG_On_Alert;
		}

		// ********************* Frame Switching Methods ***************//
		public static void Child_Frame(WebDriver driver) throws Throwable
		{
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'s_bes_aae')]")));
		}

		public static void Client_Frame(WebDriver driver) throws Throwable
		{
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f')]")));
		}

		public static void Default_Frame(WebDriver driver) throws Throwable
		{
				WebDriverWait wait=new WebDriverWait(driver, t);
				waitForInvisibilityOfLoadnIcon(wait,driver);
				driver.switchTo().defaultContent();
		}

		public static void Other_frame(WebDriver driver) throws Throwable
		{
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'header:s_bes_aae')]")));
		}

		// ********************************* Driver Definition ***************//
		public static WebDriver DriverInitializer(String browser) throws Throwable
		{
				WebDriver driver=null;
				try
				{
						if (browser.equalsIgnoreCase("IE"))
						{
								System.setProperty("webdriver.ie.driver",Local_Driver_IE);
								DesiredCapabilities dc=DesiredCapabilities.internetExplorer();
								dc.setCapability("requireWindowFocus",false);
								dc.setCapability("nativeEvents",false);
								dc.setCapability("ignoreZoomSetting",true);
								dc.setCapability("ignoreProtectedModeSettings",true);
								// dc.setCapability("unexpectedalertbehaviour", "accept");
								driver=new InternetExplorerDriver(dc);
								// driver.quit();
								// driver = new InternetExplorerDriver(dc);
						}
				}
				catch (WebDriverException e)
				{
						e.printStackTrace();
				}
				return driver;
		}

		public static void IE_SETTING()
		{
				// Reset IE
				String cmd1="rundll32.exe inetcpl.cpl,ClearMyTracksByProcess 4351";
				String cmd46="REG ADD \"HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Internet Explorer\\Main\" /v \"IE10RecommendedSettingsNo\" /t REG_DWORD /d 1 /f";
				String cmd3="REG ADD \"HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\1\" /v \"2500\" /t REG_DWORD /d 0 /f";
				String cmd4="REG ADD \"HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\" /v \"2500\" /t REG_DWORD /d 0 /f";
				String cmd5="REG ADD \"HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\" /v \"2500\" /t REG_DWORD /d 0 /f";
				String cmd6="REG ADD \"HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\4\" /v \"2500\" /t REG_DWORD /d 0 /f";
				// turn off popup blocker //yes/no
				String cmd7="REG ADD \"HKCU\\SOFTWARE\\Microsoft\\Internet Explorer\\New Windows\" /F /V \"PopupMgr\" /T REG_SZ /D \"yes\"";
				String cmd45="REG ADD \"HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\New Windows\\Allow\" /v \"192.168.27.53\" /t REG_BINARY /d 0000 /f";
				// Set Zoom level 75% http://192.168.27.53:8380/CassiopaeBackTomcat/
				String cmd8="REG ADD \"HKCU\\SOFTWARE\\Microsoft\\Internet Explorer\\Zoom\" /v \"ZoomFactor\" /t REG_DWORD /d 75000 /f";
				try
				{
						Runtime.getRuntime().exec(cmd1);
						Thread.sleep(10000);
						Runtime.getRuntime().exec(cmd46);
						Runtime.getRuntime().exec(cmd3);
						Runtime.getRuntime().exec(cmd4);
						Runtime.getRuntime().exec(cmd5);
						Runtime.getRuntime().exec(cmd6);
						Runtime.getRuntime().exec(cmd7);
						Runtime.getRuntime().exec(cmd45);
						Runtime.getRuntime().exec(cmd8);
						System.out.println("Dharmaraj out of IE setting  ");
				}
				catch (Exception e)
				{
						System.out.println("Error ocured!");
				}
		}

		public static EventFiringWebDriver getHandleToWindow(String title,EventFiringWebDriver driver) throws Throwable
		{
				WebDriver driver1=null;
				EventFiringWebDriver efwd=null;
				EventFiringWebDriver efwd1=driver;
				WebDriverEventListener eventListener=null;
				Thread.sleep(25000);
				Set<String> windowIterator=driver.getWindowHandles();
				System.err.println("No of windows :  " + windowIterator.size());
				for (String s : windowIterator)
				{
						String windowHandle=s;
						driver1=driver.switchTo().window(windowHandle);
						if (driver1.getTitle().equals(title))
						{
								efwd=new EventFiringWebDriver(driver1);
								eventListener=new Utility();
								efwd.register(eventListener);
								System.out.println(" Switch on windows successfully :- " + title);
								break;
						}
						else
						{
								efwd=efwd1;
						}
				}
				return efwd;
		}

		// ****************************** Date and Time definitions ***************//
		public static String Dates(String Expected_Date)
		{
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				Calendar c=null;
				String output=null;
				switch (Expected_Date)
				{
				case "Attachment_Date":
				case "Start_date":
				case "Startdate2":
				case "montsStartdate":
				case "juridate":
						c=Calendar.getInstance();
						c.add(Calendar.DATE,0); // System Date
						output=sdf.format(c.getTime());
						break;
				case "statement_end":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,5);// sysdate + 5 month
						output=sdf.format(c.getTime());
						break;
				case "NL_Statement_Process":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,6); // sysdate + 6 months
						output=sdf.format(c.getTime());
						break;
				case "Next_1_yr_date":
						c=Calendar.getInstance();
						c.add(Calendar.YEAR,1);
						output=sdf.format(c.getTime());
						break;
				case "Start_date_facture":
						c=Calendar.getInstance();
						c.add(Calendar.DATE,-15); // Attachment_Date -15 days
						output=sdf.format(c.getTime());
						break;
				case "Reject_Collection":
						c=Calendar.getInstance();
						c.add(Calendar.DATE,15); // Attachment_Date -15 days
						output=sdf.format(c.getTime());
						break;
				case "rec_end_date":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,1);// sysdate + 1 month
						output=sdf.format(c.getTime());
						break;
				case "Attachment_Date_NL_3048":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,-1); // sysdate - 1 month
						output=sdf.format(c.getTime());
						break;
				case "casflodate":
						c=Calendar.getInstance();
						c.add(Calendar.DATE,7); // Adding 7 days
						output=sdf.format(c.getTime());
						break;
				case "renegoDate":
						c=Calendar.getInstance();
						c.add(Calendar.DATE,+1); // Adding 1 days
						output=sdf.format(c.getTime());
						break;
				case "NLSCUAT_3782_Renego":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,3);// sysdate + 1 month
						c.add(Calendar.DATE,+1); // Adding 1 days
						output=sdf.format(c.getTime());
						break;
				case "NLSCUAT_3782_EVD_SUB":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,3);// sysdate + 1 month
						c.add(Calendar.DATE,-2); // Adding 1 days
						output=sdf.format(c.getTime());
						break;
				case "auto_debit":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,3); // sysdate + 3month
						output=sdf.format(c.getTime());
						break;
				case "sale_date":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,5); // sysdate + 5month
						output=sdf.format(c.getTime());
						break;
				case "statement2_end":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,7); // sysdate + 7month
						output=sdf.format(c.getTime());
						break;
				case "statement2_start":
						c=Calendar.getInstance();
						c.add(Calendar.MONTH,6); // sysdate + 6month
						output=sdf.format(c.getTime());
						break;
						
				default:
				}
				return output;
		}

		public static String ReportDateTime()
		{
				DateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss"); // 11-05-2016
				Date date=new Date();
				return dateFormat.format(date);
		}

		public static String Today()
		{
				DateFormat dateFormat=new SimpleDateFormat("dd_MM_YYYY"); // 11-05-2016
				Date date=new Date();
				return dateFormat.format(date);
		}

		public static String ReportDateTime1()
		{
				DateFormat dateFormat=new SimpleDateFormat("HH_mm_ss"); // 11-05-2016
				Date date=new Date();
				return dateFormat.format(date);
		}

		// *************************** Excel Read/Write methods ***************//
		public synchronized static void Putdata(int row,int column,String value,String sheetName) throws Throwable
		{
				String inputFile=excelpath;
				Boolean flag=false;
				WritableWorkbook copy=null;
				Path BackupFile=Paths.get(excelpath.replace(".xls","") + "_Last_Backup.xls");
				File inputWorkbook=new File(inputFile);
				if (inputWorkbook.exists() && inputWorkbook.length() > 0)
				{
						try
						{
								Files.copy(Paths.get(excelpath),BackupFile);
						}
						catch (Exception e1)
						{
								System.out.println("Exception while creating backup file before writing data to Excel");
						}
						Workbook w=Workbook.getWorkbook(inputWorkbook);
						for (int i=0; i < w.getNumberOfSheets(); i++)
						{
								if (w.getSheet(i).getName().equals(sheetName))
								{
										flag=true;
										break;
								}
						}
						if (flag)
						{
								try
								{
										copy=Workbook.createWorkbook(new File(excelpath),w);
										WritableSheet sheet=copy.getSheet(sheetName);
										Label label=new Label(row, column, value);
										sheet.addCell(label);
										copy.write();
										copy.close();
										System.out.println("Successfully written data to Excelsheet >> Sheetname : " + sheetName + ",Row : " + row + ",Column : " + column + ",Value : " + value);
								}
								catch (Exception e)
								{
										System.out.println("Exception while writing data to Excelsheet >> Sheetname : " + sheetName + ",Row : " + row + ",Column : " + column + ",Value : " + value);
								}
								if (inputWorkbook.length() > 0)
								{
										try
										{
												Files.delete(BackupFile);
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting backup file after writing data to Excel");
										}
								}
								else
								{
										System.out.println("Excel file is corrupt > Deleting corrupted file and renaming backupfile");
										try
										{
												Files.delete(inputWorkbook.toPath());
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting corrupted excel file");
										}
										System.out.println("Again trying to put data in excel file");
										System.out.println("If your are seeing this message more than 3 times >>> Stop excution >> Major issue with excelsheet");
										try
										{
												Files.copy(BackupFile,Paths.get(excelpath));
												Putdata(row,column,value,sheetName);
										}
										catch (Exception e)
										{
												System.out.println("Exception while copying backup file to original");
										}
								}
						}
						else
						{
								System.out.println("Excel sheetName you have passed is not present in Excelsheet ::" + sheetName);
								if (inputWorkbook.length() > 0)
								{
										try
										{
												Files.delete(BackupFile);
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting backup file after writing data to Excel");
										}
								}
								else
								{
										System.out.println("Excel file is corrupt > Deleting corrupted file and renaming backupfile");
										try
										{
												Files.delete(inputWorkbook.toPath());
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting corrupted excel file");
										}
										try
										{
												Files.copy(BackupFile,Paths.get(excelpath));
										}
										catch (Exception e)
										{
												System.out.println("Exception while copying backup file to original");
										}
								}
						}
				}
				else
				{
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						System.out.println("Excel file you are trying to access is either Corrupted or dosen't exist");
				}
		}

		public synchronized static String getdata(int column,int row,String sheetName) throws Throwable,Throwable
		{
				String data=null;
				String inputFile=excelpath;
				Boolean flag=false;
				Path BackupFile=Paths.get(excelpath.replace(".xls","") + "_Last_Backup.xls");
				File inputWorkbook=new File(inputFile);
				if (inputWorkbook.exists() && inputWorkbook.length() > 0)
				{
						try
						{
								Files.copy(Paths.get(excelpath),BackupFile);
						}
						catch (Exception e1)
						{
								System.out.println("Exception while creating backup file before reading data from Excel");
						}
						Workbook w=Workbook.getWorkbook(inputWorkbook);
						for (int i=0; i < w.getNumberOfSheets(); i++)
						{
								if (w.getSheet(i).getName().equals(sheetName))
								{
										flag=true;
										break;
								}
						}
						if (flag)
						{
								try
								{
										Sheet sheet=w.getSheet(sheetName);
										Cell cell=sheet.getCell(column,row);
										data=cell.getContents();
								}
								catch (Exception e)
								{
										System.out.println("Exception while reading data from Excelsheet >> Sheetname : " + sheetName + ",Row : " + row + ",Column : " + column);
								}
								if (inputWorkbook.length() > 0)
										try
										{
												Files.delete(BackupFile);
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting backup file after writing data to Excel");
										}
								else
								{
										System.out.println("Excel file is corrupt > Deleting corrupted file and renaming backupfile");
										try
										{
												Files.delete(inputWorkbook.toPath());
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting corrupted excel file");
										}
										System.out.println("Again trying to read data from backup excel file");
										System.out.println("?????????????????????????????????????????????????????????????????????????????????");
										System.out.println("If your are seeing this message more than 3 times >>> Stop execution >> Major issue with excelsheet");
										try
										{
												Files.copy(BackupFile,Paths.get(excelpath));
												data=getdata(column,row,sheetName);
										}
										catch (Exception e)
										{
												System.out.println("Exception while copying backup file to original");
										}
								}
						}
						else
						{
								System.out.println("Excel sheetName you have passed is not present in Excelsheet");
								if (inputWorkbook.length() > 0)
										try
										{
												Files.delete(BackupFile);
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting backup file after writing data to Excel");
										}
								else
								{
										System.out.println("Excel file is corrupt > Deleting corrupted file and renaming backupfile");
										try
										{
												Files.delete(inputWorkbook.toPath());
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting corrupted excel file");
										}
										try
										{
												Files.copy(BackupFile,Paths.get(excelpath));
										}
										catch (Exception e)
										{
												System.out.println("Exception while copying backup file to original");
										}
								}
						}
				}
				else
				{
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						System.out.println("Excel file you are trying to access is either Corrupted or dosen't exist");
				}
				if (!(data == null))
				{
						System.out.println("Sucessfull read from excel Data is : " + data);
						return data;
				}
				else
				{
						System.out.println("Data from Excel is NULL, Please check Excel,row,cloumn,data");
						return "NULL";
				}
		}

		public synchronized static HashMap<String, String> getTestData(String sheetName,int...row)
		{
				Workbook w=null;
				String value=null;
				String key=null;
				Boolean flag=false;
				Path BackupFile=Paths.get(excelpath.replace(".xls","") + "_Last_Backup.xls");
				HashMap<String, String> result=new HashMap<String, String>();
				File inputWorkbook=new File(excelpath);
				if (inputWorkbook.exists() && inputWorkbook.length() > 0)
				{
						try
						{
								Files.copy(Paths.get(excelpath),BackupFile);
						}
						catch (Exception e1)
						{
								System.out.println("Exception while creating backup file before reading data from Excel");
						}
						try
						{
								w=Workbook.getWorkbook(inputWorkbook);
						}
						catch (BiffException | IOException e1)
						{
								e1.printStackTrace();
						}
						for (int i=0; i < w.getNumberOfSheets(); i++)
						{
								if (w.getSheet(i).getName().equals(sheetName))
								{
										flag=true;
										break;
								}
						}
						if (flag)
						{
								try
								{
										w=Workbook.getWorkbook(inputWorkbook);
										Sheet sheet=w.getSheet(sheetName);
										for (int singlerow : row)
										{
												for (int i=0; i <= sheet.getRow(singlerow).length - 1; i++)
												{
														Cell cell=sheet.getCell(i,singlerow);
														key=cell.getContents();
														cell=sheet.getCell(i,singlerow + 1);
														value=cell.getContents();
														if (key != null && value != null)
														{
																result.put(key,value);
																key=null;
																value=null;
														}
												}
										}
								}
								catch (Exception e)
								{
										System.out.println("Exception while reading data from Excelsheet >> Sheetname : " + sheetName);
								}
								if (inputWorkbook.length() > 0)
										try
										{
												Files.delete(BackupFile);
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting backup file after writing data to Excel");
										}
								else
								{
										System.out.println("Excel file is corrupt > Deleting corrupted file and renaming backupfile");
										try
										{
												Files.delete(inputWorkbook.toPath());
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting corrupted excel file");
										}
										try
										{
												Files.copy(BackupFile,Paths.get(excelpath));
										}
										catch (Exception e)
										{
												System.out.println("Exception while copying backup file to original");
										}
								}
						}
						else
						{
								System.out.println("Excel sheetName you have passed is not present in Excelsheet");
								if (inputWorkbook.length() > 0)
										try
										{
												Files.delete(BackupFile);
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting backup file after writing data to Excel");
										}
								else
								{
										System.out.println("Excel file is corrupt > Deleting corrupted file and renaming backupfile");
										try
										{
												Files.delete(inputWorkbook.toPath());
										}
										catch (Exception e)
										{
												System.out.println("Exception while deleting corrupted excel file");
										}
										try
										{
												Files.copy(BackupFile,Paths.get(excelpath));
										}
										catch (Exception e)
										{
												System.out.println("Exception while copying backup file to original");
										}
								}
						}
				}
				else
				{
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						System.out.println("Excel file you are trying to access is either Corrupted or dosen't exist");
				}
				return result;
		}

		// *************************** Database retrieve methods ***************//
		public static String DBvalidation(String sqlexpression,String required_col_value,String Scenarios_name)
		{
				Connection conn=null;
				Statement st=null;
				ResultSet rs=null;
				String required_entry=null;
				String iphost="172.29.248.112";
				String dbsid="DBNAT11G";
				String username="TRNAT7";
				String password="tres5prg";
				try
				{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						String sqlexp=sqlexpression;
						conn=DriverManager.getConnection("jdbc:oracle:thin:@" + iphost + ":1521:" + dbsid,username,password);
						st=conn.createStatement();
						rs=st.executeQuery(sqlexp);
						while (rs.next())
						{
								required_entry=rs.getString(required_col_value);
								// ("\n**Required_entry is "+
								// required_entry+"\n");
						}
				}
				catch (Exception e)
				{
						System.out.print(e.getMessage());
				}
				finally
				{
						try
						{
								rs.close();
								st.close();
								conn.close();
						}
						catch (SQLException e1)
						{
								System.out.print(e1.getMessage());
						}
				}
				return required_entry;
		}

		// ********************* Screenshot/Logger/Video/Exception Methods ***************//
		public synchronized static void takeScreenShotMethod(WebDriver driver)
		{
				StackTraceElement trace[]=Thread.currentThread().getStackTrace();
				String className=null;
				for (int i=0; i < trace.length; i++)
				{
						if (trace[i].getClassName().contains("Scenarios_Reg.NLSCUAT"))
						{
								className=trace[i].getClassName();
								break;
						}
				}
				Class<?> objClass=null;
				try
				{
						objClass=Class.forName(className);
				}
				catch (ClassNotFoundException e1)
				{
						e1.printStackTrace();
				}
				Field f=null;
				try
				{
						f=objClass.getDeclaredField("Scenario_Name");
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
				Object obj=null;
				Object value=null;
				try
				{
						value=f.get(obj);
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
				className=className.split("_Reg.")[1];
				try
				{
						File screenShot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(screenShot,new File(Scrennshot_path + Today() + "\\" + className + "\\" + value.toString() + "\\" + value.toString() + "_" + ReportDateTime() + ".jpg"));
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
		}

		public synchronized static String Logger_file(String Scenario_Name,String Issue_Reproduce_steps_logs) throws Throwable
		{
				FileOutputStream os=null;
				String Property_folder=System.getProperty("user.dir") + "\\Execution_Results\\Console_logs\\Temp_Prop_Files";
				try
				{
						File file=new File(Property_folder);
						if (!file.exists())
						{
								file.mkdir();
						}
						else
						{
						}
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
				String Property_file=System.getProperty("user.dir") + "\\Execution_Results\\Console_logs\\Temp_Prop_Files\\" + Scenario_Name + ".properties";
				try
				{
						File file=new File(Property_file);
						if (!file.exists())
						{
								file.createNewFile();
						}
						else
						{
								file.delete();
								file.createNewFile();
						}
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
				os=new FileOutputStream(Property_file);
				Properties prop=new Properties();
				// Here we have defined root logger
				prop.setProperty("log4j.rootLogger","INFO,CONSOLE");
				prop.setProperty("log4j.appender.CONSOLE","org.apache.log4j.ConsoleAppender");
				prop.setProperty("log4j.appender.CONSOLE.layout","org.apache.log4j.PatternLayout");
				prop.setProperty("log4j.appender.CONSOLE.layout.ConversionPattern","%5p [%t] (%F:%L)- %m%n");
				// Here we have defined scenarios logger
				prop.setProperty("log4j.logger." + Scenario_Name,"INFO," + Scenario_Name);
				// Here we define the appender
				prop.setProperty("log4j.appender." + Scenario_Name,"org.apache.log4j.FileAppender");
				// Here we define log file location
				prop.setProperty("log4j.appender." + Scenario_Name + ".File",Issue_Reproduce_steps_logs);
				// Here we define the layout and pattern
				prop.setProperty("log4j.appender." + Scenario_Name + ".layout","org.apache.log4j.HTMLLayout");
				prop.setProperty("log4j.appender." + Scenario_Name + ".layout.Title","Application log");
				prop.setProperty("log4j.appender." + Scenario_Name + ".layout.LocationInfo","true");
				prop.store(os,null);
				os.close();
				prop.load(new FileInputStream(Property_file));
				return Property_file;
		}

		public synchronized static String Exception(String error,Method method,WebDriver driver) throws Throwable
		{
				File Driver_screenShot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File Screenshot=new File(Scrennshot_path + "Error_Screenshot_" + method.getName() + ReportDateTime() + ".jpg");
				FileUtils.copyFile(Driver_screenShot,Screenshot);
				IDocument doc=new Document2004();
				doc.addEle(Paragraph.with(" 1." + method.getName() + " :- ").create());
				doc.addEle(new BreakLine(2));
				doc.addEle(Image.from_FULL_LOCAL_PATHL(System.getProperty("user.dir") + "//Execution_Results//Screenshots//" + Screenshot.getName()).setHeight("300").setWidth("500").create().getContent());
				doc.addEle(new BreakLine(2));
				doc.addEle(Paragraph.with("2. Exception logs : - ").create());
				doc.addEle(new BreakLine(1));
				doc.addEle(Paragraph.with(error).create());
				File fileObj=new File(System.getProperty("user.dir") + "\\Execution_Results\\Fail_Reports\\" + "Error_Document_" + method.getName() + ReportDateTime() + ".doc");
				PrintWriter writer=new PrintWriter(fileObj);
				writer.println(doc.getContent());
				writer.close();
				driver.switchTo().defaultContent();
				return fileObj.getAbsolutePath();
		}

		public synchronized static String Video_Creation(String New_Video_Location,String Sceenshot_Folder_Path) throws IOException
		{
				Dimension screenBounds;
				list=null;
				IMediaWriter writer=ToolFactory.makeWriter(New_Video_Location);
				screenBounds=Toolkit.getDefaultToolkit().getScreenSize();
				writer.addVideoStream(0,0,ICodec.ID.CODEC_ID_MPEG4,screenBounds.width,screenBounds.height);
				File folder=new File(Sceenshot_Folder_Path);
				File[] listOfFiles=folder.listFiles();
				list=new ArrayList<>();
				for (File file : listOfFiles)
				{
						if (file.isFile() && file.getName().contains(".jpg"))
						{
								list.add(file.getPath());
						}
				}
				System.out.println(list.size());
				Collections.sort(list);
				int getHeight=0;
				int getWidth=0;
				for (int index=0; index <= list.size() - 1; index++)
				{
						BufferedImage screen=getImage(index);
						if (index == 0)
						{
								getWidth=screen.getWidth();
								getHeight=screen.getHeight();
						}
						BufferedImage bgrScreen=convertToType(screen,BufferedImage.TYPE_3BYTE_BGR,getWidth,getHeight);
						writer.encodeVideo(0,bgrScreen,300 * index,TimeUnit.MILLISECONDS);
				}
				writer.close();
				return New_Video_Location;
		}

		public static BufferedImage convertToType(BufferedImage sourceImage,int targetType,int getWidth,int getHeight)
		{
				BufferedImage image;
				if (sourceImage.getType() == targetType)
				{
						image=sourceImage;
				}
				else
				{
						image=new BufferedImage(getWidth, getHeight, targetType);
						image.getGraphics().drawImage(sourceImage,0,0,null);
				}
				return image;
		}

		public static BufferedImage getImage(int index)
		{
				try
				{
						String img=list.get(index);
						File test=new File(img);
						BufferedImage in=null;
						if (test != null)
						{
								in=ImageIO.read(test);
						}
						return in;
				}
				catch (Exception e)
				{
						e.printStackTrace();
						return null;
				}
		}

		public void Delete_Existing_Screenshot_Folder(String Path)
		{
				File Test=new File(Path);
				if (Test.exists())
				{
						String[] entries=Test.list();
						for (String s : entries)
						{
								File currentFile=new File(Test.getPath(), s);
								currentFile.delete();
						}
				}
				try
				{
						Test.delete();
				}
				catch (Exception e)
				{
				}
		}

		// ********************* Issue creation and Evidence attachment Methods ***************//
		public synchronized static String NewIssueCreation(String Project,String summary,String username,String password,String description,String assignee,String issuetype,String components,String Module,String Feature) throws ClientProtocolException,IOException
		{
				String output=null;
				try
				{
						Client client=Client.create();
						client.addFilter(new HTTPBasicAuthFilter(username, password));
						WebResource webResource=client.resource("http://172.29.248.52:8080/jira/rest/api/2/issue");
						String input="{\"fields\":{" + "\"project\":{\"key\":\"" + Project + "\"}," + "\"summary\":\"" + summary + "\"," + "\"description\":\"" + description + "\", " + "\"assignee\": {\"name\": \"" + assignee + "\"}," + "\"issuetype\":{\"name\":\"" + issuetype + "\"}," + "\"components\":[{\"name\":\"" + components + "\"}]," + "\"customfield_15200\": {\"value\": \"" + Module + "\", \"child\": {\"value\":\"" + Feature + "\"} }" + "}}";
						ClientResponse response=webResource.type("application/json").post(ClientResponse.class,input);
						output=response.getEntity(String.class).split("\"key\":\"")[1];
						output=output.split("\"")[0];
						return output;
				}
				catch (Exception e)
				{
						e.printStackTrace();
						return output;
				}
		}

		public synchronized static void AttachedDocumetToJira(String Issue,String Issue_Reproduce_steps_logs,String Issue_Video_path,String username1,String password1) throws ClientProtocolException,IOException
		{ // AttachedDocumetToJira(Issue,Issue_Document_Path,Issue_Reproduce_steps_logs,Issue_Video_path,username1,password1);
				try
				{
						File fileUpload=new File(Issue_Reproduce_steps_logs);
						File fileUpload2=new File(Issue_Video_path);
						// File fileUpload3 = new File(Issue_Document_Path);
						HttpClient httpClient=HttpClientBuilder.create().build();
						HttpPost postRequest=new HttpPost("http://172.29.248.52:8080/jira/rest/api/2/issue/" + Issue + "/attachments");
						BASE64Encoder base=new BASE64Encoder();
						String Credentials=username1 + ":" + password1;
						String encoding=base.encode(Credentials.getBytes());
						postRequest.setHeader("Authorization","Basic " + encoding);
						postRequest.setHeader("X-Atlassian-Token","nocheck");
						MultipartEntityBuilder entity=MultipartEntityBuilder.create();
						entity.addPart("file",new FileBody(fileUpload));
						entity.addPart("file",new FileBody(fileUpload2));
						// entity.addPart("file", new FileBody(fileUpload3));
						postRequest.setEntity(entity.build());
						HttpResponse response=httpClient.execute(postRequest);
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
		}

		// ********************* @Override Methods ***************//
		@Override
		public void beforeNavigateRefresh(WebDriver driver)
		{
		}

		@Override
		public void afterNavigateRefresh(WebDriver driver)
		{
		}

		@Override
		public void beforeChangeValueOf(WebElement element,WebDriver driver,CharSequence[] keysToSend)
		{
		}

		@Override
		public void afterChangeValueOf(WebElement element,WebDriver driver,CharSequence[] keysToSend)
		{
		}

		@Override
		public void afterAlertAccept(WebDriver arg0)
		{
				// TODO Auto-generated method stub
		}

		@Override
		public void afterAlertDismiss(WebDriver arg0)
		{
				// TODO Auto-generated method stub
		}

		@Override
		public void beforeAlertAccept(WebDriver arg0)
		{
				// TODO Auto-generated method stub
		}

		@Override
		public void beforeAlertDismiss(WebDriver arg0)
		{
				// TODO Auto-generated method stub
		}
}
