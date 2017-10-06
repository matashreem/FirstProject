
package Scenarios_Reg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.IAttributes;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Scenarios_Reg.locators;
import Scenarios_Reg.Utility;

public class NLSCUAT_3_2596 extends Utility implements locators
{

		private int Excel_Row_No=0;
		String Classname=Thread.currentThread().getStackTrace()[1].getClassName();
		public static String Scenario_Name="NL_Contract_Creation";
		String Short_name1=null;
		String part2=null;
		String App_ver=null;
		String part224=null;
		int i=0;
		String App_Version=null;
		String Contract_Ref=null;
		String Asset_reference=null;
		ArrayList<String> tabs=null;
		EventFiringWebDriver driver=null;
		WebDriverEventListener eventListener=null;
		String Issue=null;
		String Issue_Document_Path=null;
		String Issue_Reproduce_steps_logs=null;
		String Issue_Video_path=null;
		String summary=null;
		Logger logger=null;
		WebElement Contract_M;
		String Asste_URL=null;
		WebElement Expense_M;
		WebElement Expense_DropDown;
		WebElement Search_Button;
		WebElement Expense_DF_Search_Ref;
		WebElement Expense_Ref_NO;
		WebElement Contract_DropDown;
		String className=this.getClass().getSimpleName();
		String Sceenshot_Folder_Path=null;
		String New_Video_Location=null;
		String browser1=null;
		WebDriver driver1=null;

		@BeforeSuite // ***
		public void Start_Test_Class() throws InterruptedException,IOException
		{
				// Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\kill_chrome_tasks.bat");
				// .sleep(15000);
			//	IE_SETTING();
		}

		@BeforeTest
		public void Start_Test_Cases_Execution()
		{
		}

		@Parameters(
		{ "browser" })
		@BeforeClass
		public void Reset_All_TC_Status(@Optional("IE") String browser) throws Throwable
		{
				browser1=browser;
				driver1=new Augmenter().augment(Utility.DriverInitializer(browser));
		}

		@BeforeMethod
		public void Method_Started(Method method) throws Throwable
		{
			    driver=new EventFiringWebDriver(driver1);
			    eventListener=new Utility();
			     driver.register(eventListener);
				Excel_Row_No=getClass().getMethod(method.getName(),java.lang.reflect.Method.class).getAnnotation(org.testng.annotations.Test.class).priority();
				Scenario_Name=method.getName();
				summary=Scenario_Name;
				logger=Logger.getLogger(Scenario_Name);
				Issue_Reproduce_steps_logs=System.getProperty("user.dir") + "\\Execution_Results\\Console_logs\\" + Today() + "\\" + className + "\\" + Scenario_Name + "\\" + Scenario_Name + "_" + ReportDateTime1() + ".html";
				PropertyConfigurator.configure(Logger_file(Scenario_Name,Issue_Reproduce_steps_logs));
				Delete_Existing_Screenshot_Folder(Scrennshot_path + Today() + "\\" + className + "\\" + Scenario_Name);
				logger.info("//*******Execution Started for Test - :- " + Scenario_Name + " ********//");
				Contract_Ref=Utility.getdata(Excel_Testdata_Column,27,Excel_SheetName);
				logger.info(Contract_Ref);
				String USERPWD="ORFI";
				if (Scenario_Name.equals("NL_Expense_Event_Passage_en_bon_à_payer_trésorerie_de_dépenses"))
				{
						USERPWD="URBAIN";
				}
				try
				{
						NL_2596_Login_to_Application(USERPWD);
				}
				catch (Exception e1)
				{
						System.out.println("NL_2596_Login_to_Application :-");
						e1.printStackTrace();
				}
		}

		public void NL_2596_Login_to_Application(String USERPWD) throws Throwable
		{
			 logger.info("Nevigate to url - " + NATbaseUrl);
			  driver1.get(NATbaseUrl);
			  driver1.manage().window().maximize();
			  driver1.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
			  String actual=driver1.getTitle();
			  logger.info("Windows Title : " + actual);
			  logger.info("Application launched Successfully");
			  logger.info("Login with USER/PWD : - ORFI/ORFI");
			  logger.info("Enter Username : ORFI");
			  driver1.findElement(By.xpath("//input[contains(@id,'s_s0_aan')]")).sendKeys(USERPWD);
			  logger.info("Enter Password : ORFI");
			  driver1.findElement(By.xpath("//input[contains(@id,'s_s0_aap')]")).sendKeys(USERPWD );
			  logger.info("Click on login button");
			  driver1.findElement(By.xpath("//button[contains(@id,'s_s0_aas')]")).click();
			  
			  
			  WebElement rxBtn=driver.findElement(By.xpath("//a[contains(@id,'s_tu_aak')]/span"));
			  Assert.assertEquals(true,rxBtn.isDisplayed());
			  App_Version=rxBtn.getText().split(":")[1];
			  logger.info("Login into Application Successfully on version - " + App_Version);
		}

		public void NLSCUAT_2596_Logout_From_Application() throws Throwable
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Logged Out button");
				driver.findElement(By.xpath("//a[contains(@id,'s_tu_aa0')]")).click();
				logger.info("Logged Out Successfully");
		}

		@Test(priority=27,enabled=true)
		public void NL_2596Create_Contract(Method method) throws Throwable
		{
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				logger.info("Click on Contract Sub-Menu");
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				Client_Frame(driver);
				logger.info("Clcik on New icon ");
				driver.findElement(By.xpath("//img[contains( @id,'s_n5_aao:tbTableToolbar:new::icon')]")).click();
				Short_name1="NL Automation Test_" + App_Version + " _" + ReportDateTime();
				logger.info("Short Name  :" + Short_name1);
				logger.info("Select Société : 1-NATIXIS LEASE");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aak::content')]"))).selectByVisibleText("1-NATIXIS LEASE");
				logger.info("Select Activité	:Location financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aaq::content')]"))).selectByVisibleText("Location financière");
				logger.info("Select Product :SC-1_Location Financière Groupe BP");
				WebElement text=driver.findElement(By.xpath("//select/option[contains(text(),'SC-1_Location Financière Groupe BP')]"));
				text.click();
				logger.info("Select Devise:Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aas::content')]"))).selectByVisibleText("Euro");
				logger.info("Select Regimal fiscal::Régime général");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aau::content')]"))).selectByVisibleText("Régime général");
				logger.info("Eneter Short Name" + Short_name1);
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_aav::content')]")).sendKeys(Short_name1 + Keys.ENTER);
				logger.info("Select Pool :Seul");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aax::content')]"))).selectByVisibleText("Seul");
				logger.info("Select secteur de gestion : Exploitation ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aa0::content')]"))).selectByVisibleText("Exploitation");
				logger.info("Origin bien finances : Achat à un tiers ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aa2::content')]"))).selectByVisibleText("Achat à un tiers");
				logger.info("Click on Mandat find icon");
				driver.findElement(By.xpath("//img[contains( @id,'secId:mainBody:vboxlist:bSr_s_m9_aal::icon')]")).click();
				logger.info("Enter Mandat No. : M000004");
				driver.findElement(By.xpath("//input[contains(@id,'srcId:s_be8_aaj:innerTbl:mannum::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'srcId:s_be8_aaj:innerTbl:mannum::content')]")).sendKeys("M000004" + Keys.ENTER);
				logger.info("Enter Mandat Name. : MANDAT BP VAL DE FRANCE ");
				driver.findElement(By.xpath("//input[contains(@id,'srcId:s_be8_aaj:innerTbl:mannom::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'srcId:s_be8_aaj:innerTbl:mannom::content')]")).sendKeys("MANDAT BP VAL DE FRANCE ");
				logger.info("Click on Mandat find icon");
				driver.findElement(By.xpath("//img[contains( @id,'srcId:s_be8_aaj:tbTableToolbar:find::icon')]")).click();
				logger.info("Click on Valider");
				driver.findElement(By.xpath("//button[contains(text(),'Valider')]")).click();
				logger.info("select Marque ::BAIL Matériel");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'CMBCCHSID138::content')]"))).selectByVisibleText("BAIL Matériel");
				logger.info("Select Réseau commercial:BP VF VERSAILLES");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'CMBCCHSID145::content')]"))).selectByVisibleText("BP VF VERSAILLES");
				logger.info("Enter No of Period(Duree)Anne  :4");
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abe::content')]")).sendKeys("4" + Keys.ENTER);
				logger.info("Start Date" + Attachment_Date);
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abn::content')]")).sendKeys(Attachment_Date);
				logger.info("Enter Montant dem. Financement	:364202.54");
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abs::content')]")).sendKeys("36420.54" + Keys.ENTER);
				logger.info("Eneter Short Name");
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_aav::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_aav::content')]")).sendKeys(Short_name1 + Keys.ENTER);
				logger.info("Navigate to Attached Actors tab");
				driver.findElement(By.xpath("//a[contains(text(),'Acteurs affectés')]")).click();
				logger.info("Click on New to add 0040409 Actor ");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select role :Client preneur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Client preneur");
				logger.info("Enter actor code:0040409");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("0040409" + Keys.ENTER);
				try
				{
						logger.info("Check whether record present if not click on new icon");
						driver.findElement(By.xpath("//span[contains(@id,'s_kx_aai:innerTbl:0:adresse')]")).isEnabled();
				}
				catch (Exception e)
				{
						logger.info("Click on New icon at select New Address");
						driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				}
				logger.info("Select Addresses :TARNAC 19170 France");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kt_aad::content')]"))).selectByVisibleText("TARNAC 19170 France");
				logger.info("Enter Liste des Décaissement  details:Lettre chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Lettre chèque");
				logger.info("Click on new Icon to add 2 0098721  actor");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select Role :Fournisseur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Fournisseur");
				logger.info("Enter code:0098721");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("0098721" + Keys.ENTER);
				logger.info("click on New icon to add address");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Fournisseur");
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bev_aac::icon')]")).click();
				try
				{
						logger.info("Check whether record present if not click on new icon");
						driver.findElement(By.xpath("//span[contains(@id,'s_kx_aai:innerTbl:0:adresse')]")).isEnabled();
				}
				catch (Exception e)
				{
						logger.info("Click on New icon at select New Address");
						driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				}
				logger.info("Select  Address");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kt_aad::content')]"))).selectByIndex(1);
				logger.info("Enter Liste des paiements details");
				logger.info("Paiements Décaissement s:Virement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Virement automatique");
				logger.info("Select bqank account (Compte) :FR 76 30066109480001011480176 FILPACK INDUSTRIE SAS");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:bankid::content')]"))).selectByVisibleText("FR 76 30066109480001011480176 FILPACK INDUSTRIE SAS");
				logger.info("Click on New ICon add to 3 Actor");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select Role:Apporteur d'affaires BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Apporteur d'affaires BP");
				logger.info("Enter Code actor :087 ");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("087" + Keys.ENTER);
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Apporteur d'affaires BP");
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bev_aac::icon')]")).click();
				logger.info("Clikc on new icon of  Liste des addresses");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Address :9 AV NEWTON - PLACE WICLOW - ST QUENTIN YVELINES 78183 France");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kt_aad::content')]"))).selectByVisibleText("9 AV NEWTON - PLACE WICLOW - ST QUENTIN YVELINES 78183 France");
				logger.info("Enter liste des Décaissement:Virement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Virement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:bankid::content')]"))).selectByVisibleText("FR 76 18707000665400030900209 PréfiVERSAILLES");
				logger.info("Enter Liste des Encaissement details:Chèque délégation");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque délégation");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque délégation");
				WebElement myElement=driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:dapdelaipmt::content')]"));
				Actions move=new Actions(driver);
				move.moveToElement(myElement).clickAndHold();
				move.moveByOffset(125,0);
				move.release();
				move.perform();
				//JavascriptExecutor js=(JavascriptExecutor) driver;
				driver.executeScript("arguments[0].scrollIntoView()",myElement);
				logger.info("Select Deli :Fin de mois");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:dapdelaipmt::content')]"))).selectByVisibleText("Fin de mois");
				logger.info("Click on New ICon to add 4 Actor");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select Role:Garant 'Banque Populaire");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Garant 'Banque Populaire'");
				logger.info("Enter Actor code: 087");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("087" + Keys.ENTER);
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Garant 'Banque Populaire'");
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bev_aac::icon')]")).click();
				logger.info("Enter Liste des addresses");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Address :9 AV NEWTON - PLACE WICLOW - ST QUENTIN YVELINES 78183 France");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kt_aad::content')]"))).selectByVisibleText("9 AV NEWTON - PLACE WICLOW - ST QUENTIN YVELINES 78183 France");
				logger.info("Enter Liste des paiements details:Virement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Virement automatique");
				logger.info("Select compte:FR 76 18707000665400030900209 PréfiVERSAILLES ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:bankid::content')]"))).selectByVisibleText("FR 76 18707000665400030900209 PréfiVERSAILLES");
				logger.info("Enter Liste des paiements details:Prélèvement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque délégation");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque délégation");
				WebElement element31=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:dapdelaipmt::content')]"));// for Scroll Up Page
				logger.info("select Delai under liste des paiement");
				Actions move1=new Actions(driver);
				move1.moveToElement(element31).clickAndHold();
				move1.moveByOffset(125,0);
				move1.release();
				move1.perform();
				logger.info("Select Deli :Fin de mois");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:dapdelaipmt::content')]"))).selectByVisibleText("Fin de mois");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:dapdelaipmt::content')]"))).selectByVisibleText("Fin de mois");
				logger.info("Navigate to Pool  tab");
				driver.findElement(By.xpath("//a[contains(text(),'Pool')]")).click();
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//div[contains(@id,'s_ny_aai:tbTableToolbar:new')]")).click();
				String Short_name2="Pool_risque_NL Automation Test_" + App_Version + " _" + ReportDateTime();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_nx_aad::content')]")).sendKeys(Short_name2 + Keys.ENTER);
				logger.info("Type de guarantie:Garantie BANQUE POPULAIRE CBM");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_nx_aag:innerTbl:0:tgatypeconvention::content')]"))).selectByVisibleText("Garantie BANQUE POPULAIRE CBM");
				logger.info("Clicked on Rubriques financières Tab ");
				driver.findElement(By.xpath("//a[contains(@id,'s_mx_aab::disAcr')]")).click();
				logger.info("Click on New icon");
				driver.findElement(By.xpath("//div[contains(@id,'s_mx_aac:tbTableToolbar:new')]")).click();
				logger.info("Imput analytique:Loyer");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_aae::content')]"))).selectByVisibleText("Loyer");
				logger.info("Select *Tax::T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_abh::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("Select Type de montage :Classique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_aal::content')]"))).selectByVisibleText("Classique");
				logger.info("Select Montage :Echéances fixées classiques");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_aam::content')]"))).selectByVisibleText("Echéances fixées classiques");
				logger.info("Select Mode de calcu :Précomptés");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_aap::content')]"))).selectByVisibleText("Précomptés");
				logger.info("Select Bases des décomptes (jours):365 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_abl::content')]"))).selectByVisibleText("365 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_abm::content')]"))).selectByVisibleText("365 jours / an");
				WebElement Qualification=driver.findElement(By.xpath("//select/option[contains(text(),'perçus d')]"));
				Qualification.click();
				logger.info("Select Nature :FIXe");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_abu::content')]"))).selectByVisibleText("Fixe");
				logger.info("Eneter Taux nominal:3.5");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lg_aas::content')]")).sendKeys("3.5" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'s_lg_ab5::content')]")).clear();
				logger.info("Enter VR %: 2");
				driver.findElement(By.xpath("//input[contains(@id,'s_lg_ab5::content')]")).sendKeys("2" + Keys.ENTER);
				logger.info("Select Progression under Flux:Paliers de montants ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_ll_aak:innerTbl:0:drfprogression::content')]"))).selectByVisibleText("Paliers de montants");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpnbperiode::content')]")).clear();
				logger.info("Enter  Paliers:1");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpnbperiode::content')]")).sendKeys("1" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpmt::content')]")).clear();
				logger.info("Enter Montant/Poids :5000");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpmt::content')]")).sendKeys("5000" + Keys.ENTER);
				logger.info("Click on new icon in Paliers");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:tbTableToolbar:new::icon')]")).click();
				logger.info("Enter  Paliers:11");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpnbperiode::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpnbperiode::content')]")).sendKeys("11" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpmt::content')]")).clear();
				logger.info("Enter Montant/Poids  : 600");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpmt::content')]")).sendKeys("600" + Keys.ENTER);
				logger.info("Click on new icon in Paliers");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:tbTableToolbar:new::icon')]")).click();
				logger.info("Enter  Paliers:12");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpnbperiode::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpnbperiode::content')]")).sendKeys("12" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpmt::content')]")).clear();
				logger.info("Enter Montant/Poids  : 1800");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpmt::content')]")).sendKeys("1800" + Keys.ENTER);
				logger.info("Click on new icon in Paliers");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:tbTableToolbar:new::icon')]")).click();
				logger.info("Enter  Paliers:12");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpnbperiode::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpnbperiode::content')]")).sendKeys("12" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpmt::content')]")).clear();
				logger.info("Enter Montant/Poids  : 600");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:innerTbl:0:dfpmt::content')]")).sendKeys("600" + Keys.ENTER);// 800
				logger.info("Click on Calculate button");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lz_aab:tbTableToolbar:btnStepCalculation')]")).click();
				logger.info("Click on Payment Shecdual");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_ll_aak:tbTableToolbar:btnPaymentSchedule::icon')]")).click();
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				logger.info("click on close button");
				/// Disbursement Element/retribution section tab
				logger.info("Clicked on Disbursement Element/retribution  section ");
				driver.findElement(By.xpath("//a[contains(@id,'s_mx_aah::disAcr')]")).click();
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//div[contains(@id,'s_mx_aai:tbTableToolbar:new')]")).click();
				logger.info("Select Imputation Analytique :Commission de garantie commissionnaire ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aae::content')]"))).selectByVisibleText("Commission de garantie commissionnaire");
				logger.info("Select Acteur :BP VAL DE FRANCE - Garant 'Banque Populaire'");
				WebElement supplier=driver.findElement(By.xpath("//select/option[contains(text(),'Garant')]"));
				supplier.click();
				logger.info("Select Taxe :T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa1::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("Select Type de montage :Assiette brute financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aam::content')]"))).selectByVisibleText("Assiette brute financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa5::content')]"))).selectByVisibleText("365 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa6::content')]"))).selectByVisibleText("365 jours / an");
				logger.info("Enter No. of Period : 36");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfnbperiode::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfnbperiode::content')]")).sendKeys("36" + Keys.ENTER);
				logger.info("Enter Taux under Flux tab ");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).clear();
				logger.info("Enter Taux 0.51");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).sendKeys(Keys.BACK_SPACE + "0.51" + Keys.ENTER);
				logger.info("Click on Payment Schedule ");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:tbTableToolbar:btnPaymentScheduleDisAcc::icon')]")).click();
				logger.info("click on close button");
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				logger.info("Click on New icon to add 2 Element");
				driver.findElement(By.xpath("//div[contains(@id,'s_mx_aai:tbTableToolbar:new')]")).click();
				logger.info("Select Imputation Analytique :Rémunération de commissionnaire ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aae::content')]"))).selectByVisibleText("Rémunération de commissionnaire");
				WebElement supplier2=driver.findElement(By.xpath("//select/option[contains(text(),'Apporteur')]"));
				supplier2.click();
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa1::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("Select Type assiette:Rémunération commissionaire");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aam::content')]"))).selectByVisibleText("Rémunération commissionaire");
				logger.info("Select Bases des decomptes: 365 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa5::content')]"))).selectByVisibleText("365 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa6::content')]"))).selectByVisibleText("365 jours / an");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfnbperiode::content')]")).clear();
				logger.info("Enter period 1");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfnbperiode::content')]")).sendKeys("1" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).clear();
				logger.info("Enter Taux in Flux tab");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).sendKeys("3" + Keys.ENTER);
				logger.info("Enter Value in Assiette:3");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).sendKeys("3" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aaf:innerTbl:0:dr1nombre::content')]")).clear();
				logger.info("Enter Nombre in Assiette:100");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aaf:innerTbl:0:dr1nombre::content')]")).sendKeys("100" + Keys.ENTER);
				logger.info("Click on Payment Scheduel icon ");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:tbTableToolbar:btnPaymentScheduleDisAcc::icon')]")).click();
				logger.info("Click on Close");
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				logger.info("click on Save button");
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bey_aab::icon')]")).click();
				Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@id,'s_m2_aak')]/label")).isDisplayed(),true);
				String String4=driver.findElement(By.xpath("//span[contains(@id,'s_m2_aak')]/label")).getText();
				logger.info(String4);
				String[] parts=String4.split(": < ");
				String part1=parts[1];
				logger.info(part1);
				String[] parts2=part1.split(" >");
				Contract_Ref=parts2[0];
				logger.info("Contract Number is::" + Contract_Ref);
				String sqlexp2="select * from dossier where dosnom='" + Short_name1 + "'";
				String col_name2="DOSNUM";
				String NL_2596Create_Contract_ref=DBvalidation(sqlexp2,col_name2,Scenario_Name);
				logger.info("Contract Ref from DB: " + NL_2596Create_Contract_ref);
				Assert.assertEquals(NL_2596Create_Contract_ref,Contract_Ref,"DB Validation Failed for Contract creation");
				logger.info("DBVAlidation is pass");
		}

		@Test(priority=32,enabled=false)
		public void Contract_2_Creation_Contrat_6_Rub_retribution_cplt(Method method) throws Throwable
		{
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				Client_Frame(driver);
				logger.info("Enter Contract ref Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref);
				logger.info("Click on Search Icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contrct" + Contract_Ref);
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				logger.info("Navigate to  Disbursement Element/retribution  section ");
				driver.findElement(By.xpath("//a[contains(@id,'s_mx_aah::disAcr')]")).click();
				logger.info("Click on new icon add new elment");
				driver.findElement(By.xpath("//div[contains(@id,'s_mx_aai:tbTableToolbar:new')]")).click();
				logger.info("Select Imputation analytique:Complément de rémunération commissionnaire");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aae::content')]"))).selectByVisibleText("Complément de rémunération commissionnaire");
				driver.findElement(By.xpath("//select[contains(@id,'s_mb_aaf::content')]")).click();
				logger.info("Select Acture :VAL DE FRANCE");
				WebElement supplier2=driver.findElement(By.xpath("//select/option[contains(text(),'VAL DE FRANCE')]"));
				supplier2.click();
				logger.info("Select Tax :T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa1::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("Select Type assiette :Complément de rémunération commissionaire");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aam::content')]"))).selectByVisibleText("Complément de rémunération commissionaire");
				logger.info("Select Bases des décomptes (jours) :365 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa5::content')]"))).selectByVisibleText("365 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa6::content')]"))).selectByVisibleText("365 jours / an");
				Actions action=new Actions(driver);
				WebElement actor_menu=driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:tbTableToolbar:btnPaymentScheduleDisAcc::icon')]"));
				action.moveToElement(actor_menu).build().perform();
				logger.info("Enter Tax : 0.50");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).sendKeys("0,50" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drftxassiette::content')]")).sendKeys("0,50" + Keys.ENTER);
				logger.info("Click on Payment Schedule icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:tbTableToolbar:btnPaymentScheduleDisAcc::icon')]")).click();
				logger.info("Click on ok");
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				logger.info("Click on Save");
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bey_aab::icon')]")).click();
				String newsize=driver.findElement(By.xpath("//td/span[contains(text(),'3 lignes')]")).getText();
				logger.info("NEW COUNT IS:" + newsize);
				Assert.assertEquals("3 lignes",newsize);
				logger.info("Add retribution Element is pass");
		}

		@Test(priority=33,enabled=false)
		public void Contract_2_Creation_Contrat_7_Acteurs_CPLT(Method method) throws Throwable
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				Client_Frame(driver);
				logger.info("Enter Contract Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref);
				logger.info("Click on Search iocn");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contract");
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				logger.info("Navigate to Attached Actors tab");// a[contains(text(),'Identification')]
				driver.findElement(By.xpath("//a[contains(text(),'Acteurs affectés')]")).click();
				int oldsize=driver.findElements(By.xpath("//div[contains(@id,'secId:mainBody:vboxlist:s_kx_aad:innerTbl::db')]/table/tbody/tr")).size();
				logger.info("OLD COUNT IS:" + oldsize);
				logger.info("Click on New Icon to 1083975  actor ");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select role :Assureur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Assureur");
				logger.info("Enter Code :1083975 ");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("1083975" + Keys.ENTER);
				logger.info("Click on New Icon under Address");
				logger.info("Click on New icon at select New Addresse");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				try
				{
						logger.info("Check whether record present if not click on new icon");
						driver.findElement(By.xpath("//span[contains(@id,'s_kx_aai:innerTbl:0:adresse::content')]")).isDisplayed();
				}
				catch (Exception e)
				{
						logger.info("Click on New icon at select New Address");
						driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				}
				logger.info("Select Addresses :10 BD ALEXANDRE OYON - LE MANS 72030 France ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kt_aad::content')]"))).selectByVisibleText("10 BD ALEXANDRE OYON - LE MANS 72030 France");  // L'ANGELIAC - LOMBEZ 32220 France //79 AVENUE LECONTE DE LISLE - ST DENIS 97490 France
				logger.info("Enter Liste des paiements details:Virement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Virement automatique");
				logger.info("Select compte ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:bankid::content')]"))).selectByVisibleText("FR 76 30004008410001610013033 BNP");
				logger.info("Click on New Icon to 0058074 second  Actor");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select role :Assuré");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Assuré");
				logger.info("Enter Actor Code :0058074 ");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("0058074" + Keys.ENTER);
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Assuré");
				logger.info("Click on New Icon to Add 0058074 Actor");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select role :Garant");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Garant");
				logger.info("Enter Actor Code  : 0058074 ");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("0058074" + Keys.ENTER);
				logger.info("Enter Liste des paiements details:Lettre chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Lettre chèque");
				logger.info("Enter Liste des paiements details:Réglé par chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByValue("1");
				logger.info("Click on New Icon for 0092155  Actor");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select role :Assureur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Fournisseur");
				logger.info("enter Code 0092155");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("0092155" + Keys.ENTER);
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Fournisseur");
				logger.info("click on expand  icon ");
				try
				{
						driver.findElement(By.xpath("//img[contains(@title,'Développer Liste des adresses')]")).click();
				}
				catch (Exception e)
				{
						logger.info(e.getMessage());
				}
				logger.info("Click on New icon to add address");
				logger.info("Click on New icon at select New Addresse");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				try
				{
						logger.info("Check whether record present if not click on new icon");
						driver.findElement(By.xpath("//span[contains(@id,'s_kx_aai:innerTbl:0:adresse::content')]")).isDisplayed();
				}
				catch (Exception e)
				{
						logger.info("Click on New icon at select New Address");
						driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				}
				logger.info("Select Addresses :BP 2 - 132 RUE DE SORAS - DAVEZIEUX 07430 France ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kt_aad::content')]"))).selectByVisibleText("BP 2 - 132 RUE DE SORAS - DAVEZIEUX 07430 France");  // L'ANGELIAC - LOMBEZ 32220 France //79 AVENUE LECONTE DE LISLE - ST DENIS 97490 France
				logger.info("Click on Save Icon ");
				driver.findElement(By.xpath("//img[contains(@id,'secId:s_m4_aad:s_bey_aab::icon')]")).click();
				String newsize=driver.findElement(By.xpath("//td/span[contains(text(),'8 lignes')]")).getText();
				logger.info("NEW COUNT IS:" + newsize);
				Assert.assertEquals("8 lignes",newsize);
				logger.info("Add Actors Element is pass");
		}

		@Test(priority=34,enabled=false)
		public void Contract_2_Creation_Contrat_8_Rub_accessoires(Method method) throws Exception
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				logger.info("Enter Contract Number" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contract");
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				logger.info("Navigate to Rubriques accessoires tab");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques accessoires')]")).click();
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:frmDossElementsTblAccElement:s_mx_aaf:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Imputation analytique:Refacturation frais de greffe");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aae::content')]"))).selectByVisibleText("Refacturation frais de greffe");
				logger.info("Select Acture :SAMAT DISTRIBUTION - Client preneur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aaf::content')]"))).selectByVisibleText("SAMAT DISTRIBUTION - Client preneur");
				logger.info("Select Type de montage :Montants explicites");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aak::content')]"))).selectByVisibleText("Montants explicites");
				logger.info("Select TAX  :T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa5::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("Select Bases des décomptes (jours)  :365 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa9::content')]"))).selectByVisibleText("365 jours / an");
				logger.info("enter the Amount :23");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drfmt::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drfmt::content')]")).sendKeys("23" + Keys.ENTER);
				logger.info("Click on Payment Schedule");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:tbTableToolbar:btnPaymentScheduleAcc::icon')]")).click();
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:frmDossElementsTblAccElement:s_mx_aaf:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Imputation analytique:Frais de dossiers R");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aae::content')]"))).selectByVisibleText("Frais de dossiers R(SC)");
				logger.info("Select Acture :SAMAT DISTRIBUTION - Client preneur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aaf::content')]"))).selectByVisibleText("SAMAT DISTRIBUTION - Client preneur");
				logger.info("Select TAX  :T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa5::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("Select Type de montage :Montants explicites");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aak::content')]"))).selectByVisibleText("Montants explicites");
				logger.info("enter the Amount :150");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drfmt::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drfmt::content')]")).sendKeys("150" + Keys.ENTER);
				logger.info("Click on Payment Schedule");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:tbTableToolbar:btnPaymentScheduleAcc::icon')]")).click();
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:frmDossElementsTblAccElement:s_mx_aaf:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Imputation analytique:Deces PTIA");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aae::content')]"))).selectByVisibleText("Deces PTIA");
				logger.info("Select Acture :SAMAT DISTRIBUTION - Client preneur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aaf::content')]"))).selectByVisibleText("SAMAT DISTRIBUTION - Client preneur");
				logger.info("Select TAX  :Exonere de TVA");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa5::content')]"))).selectByVisibleText("Exonere de TVA");
				logger.info("Select Type de montage :Assiette x taux (en dehors)");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aak::content')]"))).selectByVisibleText("Assiette x taux (en dehors)");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drfnbperiode::content')]")).clear();
				logger.info("Enter Periods :36");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drfnbperiode::content')]")).sendKeys("36" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drftxassiette::content')]")).clear();
				logger.info("enter the tax .52");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drftxassiette::content')]")).sendKeys("0.52" + Keys.ENTER);
				logger.info("Click on Payment Schedule");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:tbTableToolbar:btnPaymentScheduleAcc::icon')]")).click();
				logger.info("Click on fermer");
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				Actions action=new Actions(driver);
				WebElement actor_menu=driver.findElement(By.xpath("//a[contains(text(),'Reversement')]"));
				action.moveToElement(actor_menu).build().perform();
				logger.info("Click on Reversement tab");// a[contains(text(),'Identification')]
				driver.findElement(By.xpath("//a[contains(text(),'Reversement')]")).click();
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_ms_aae::content')]"))).selectByVisibleText("MMA IARD ASSURANCES - Assureur");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_ms_aag::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_ms_aag::content')]")).sendKeys("80,00000");
				logger.info("Click on Assurance tab");// a[contains(text(),'Identification')]
				driver.findElement(By.xpath("//a[contains(text(),'Assurance')]")).click();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:tf_s_ns_aae::content')]")).sendKeys("23.144");//// 127 107 199 (B.M.)
				driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_ns_aag::content')]")).sendKeys(Keys.ENTER);
				logger.info("select Assure: BIDET JEAN LOUIS");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'_ns_aaf')]"))).selectByVisibleText("BIDET JEAN LOUIS");
				logger.info("Click on Save icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:s_m4_aad:s_bey_aab::icon')]")).click();
				String newsize=driver.findElement(By.xpath("//td/span[contains(text(),'3 lignes')]")).getText();
				logger.info("NEW COUNT IS:" + newsize);
				Assert.assertEquals("3 lignes",newsize);
		}

		@Test(priority=35,enabled=false)
		public void Contract_2_Creation_Contrat_9_Penalites(Method method) throws Exception
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				logger.info("Enter Contract Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("OPen Contacrt");
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				logger.info("Navigate to Rubriques accessoires tab");// a[contains(text(),'Identification')]
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques accessoires')]")).click();
				logger.info("Navigate to Garanties tab");// a[contains(text(),'Identification')]
				driver.findElement(By.xpath("//a[contains(text(),'Garanties')]")).click();
				logger.info("Navigate to Pénalité fin anticipée tab");// a[contains(text(),'Identification')]
				driver.findElement(By.xpath("//a[contains(text(),'Pénalité fin anticipée')]")).click();
				logger.info("Select Evt cible: TOUS  ");// a[contains(text(),'Identification')]
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_no_aag:innerTbl:0:dratargetevt::content')]"))).selectByVisibleText("Tous");
				logger.info("Click on Save icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:s_m4_aad:s_bey_aab::icon')]")).click();
				String EVT=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_no_aag:innerTbl:0:dratargetevt::content')]")).getAttribute("title");
				logger.info("EVT:" + EVT);
				Assert.assertEquals("Tous",EVT);
		}

		@Test(priority=36,enabled=false)
		public void Contract_2_Creation_Contrat_10_Garanties(Method method) throws Exception
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				logger.info("Enter Contract Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref);
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				logger.info("Navigate to Rubriques accessoires tab");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques accessoires')]")).click();
				logger.info("Navigate to Garanties tab");
				driver.findElement(By.xpath("//a[contains(text(),'Garanties')]")).click();
				logger.info("click on New icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_k4_aad:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Grant :BIDET JEAN LOUIS [7 - Garant] ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_k1_aaf::content')]"))).selectByVisibleText("BIDET JEAN LOUIS [7 - Garant]");
				logger.info("Select Type de Grantie :Caution solidaire ");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_k1_aaq::content')]"))).selectByVisibleText("Caution solidaire");
				logger.info("Click on Save icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:s_m4_aad:s_bey_aab::icon')]")).click();
				String Type=driver.findElement(By.xpath("//span[contains(@id,'secId:mainBody:vboxlist:s_k4_aad:innerTbl:0:tgacode::content')]")).getText();
				logger.info("Type" + Type);
				Assert.assertEquals("Caution solidaire",Type);
		}

		@Test(priority=37,enabled=false)
		public void Creation_Contrat_11_Materiels_Add_New_Asset(Method method) throws Throwable 
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				Client_Frame(driver);
				logger.info("Enter Contract Number" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref);
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				driver.findElement(By.xpath("//a[contains(text(),'Acteurs affectés')]")).click();
				logger.info("Clicked on Pool Tab ");
				driver.findElement(By.xpath("//a[contains(text(),'Pool')]")).click();
				logger.info("Clicked on Rubriques financières Tab ");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques financières')]")).click();
				logger.info("Clicked on Rubriques accessoires Tab ");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques accessoires')]")).click();
				logger.info("Clicked on Rubriques de rétributions Tab ");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques de rétributions')]")).click();
				logger.info("Clicked on Rubriques de préfinancement Tab ");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques de préfinancement')]")).click();
				logger.info("Clicked on Asset  section ");
				driver.findElement(By.xpath("//a[contains(@id,'s_kz_aab::disAcr')]")).click();
				driver.findElement(By.xpath("//div[contains(@id,'s_kz_aac:tbTableToolbar:new')]")).click();
				driver=Utility.getHandleToWindow("Cassiopae Application",driver);
				driver.manage().window().maximize();
				String actual=driver.getTitle();
				logger.info("\n\n\n" + actual);
				logger.info("--------------------- Navigate to Information Generales Tab of Asset Window ---------------------");
				logger.info("Select  Socity :1-NATIXIS LEASE");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'mainBody:vboxlist:s_cg_aad::content')]"))).selectByVisibleText("1-NATIXIS LEASE");
				logger.info("Enter Asset Name");
				String Asset_Name=" Automation_D_" + ReportDateTime();
				driver.findElement(By.xpath("//input[contains(@id,'s_cg_aaf::content')]")).sendKeys(Asset_Name + Keys.ENTER);
				logger.info("Select  Currancy :Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_cg_aag::content')]"))).selectByVisibleText("Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_cg_aah::content')]"))).selectByVisibleText("Exploitation");
				driver.findElement(By.xpath("//a[contains(text(),'Liste des éléments')]")).click();
				logger.info("Select  Activité :Location financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aae::content')]"))).selectByVisibleText("Location financière");
				logger.info("Select Product:SC-1_Location Financière Groupe BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aaf::content')]"))).selectByVisibleText("SC-1_Location Financière Groupe BP");
				driver.findElement(By.xpath("//input[contains(@id,'tf_s_ca_aa4::content')]")).sendKeys(Keys.ENTER);
				logger.info("Enter Code :0092155");
				driver.findElement(By.xpath("//input[contains(@id,'tf_s_ca_aa4::content')]")).sendKeys("0092155" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_aai::content')]")).clear();
				logger.info("Enter Description");
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_aai::content')]")).sendKeys("Description");
				logger.info("Enter Montant:30000");
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_abp::content')]")).sendKeys("30000" + Keys.ENTER);
				logger.info("Select Imputation analytique	:Produits de l'industrie auto");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'mainBody:vboxlist:s_ca_abq::content')]"))).selectByVisibleText("Produits de l'industrie auto");
				logger.info("Select Catégorie de bien		:Voitures particulières");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'mainBody:vboxlist:s_ca_abs::content')]"))).selectByVisibleText("Voitures particulières");
				logger.info("Select TAXE:T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abr::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abx::content')]"))).selectByVisibleText("Euro");
				logger.info("Click on New icon to list de Component");
				String count=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
				System.out.println(count);
				boolean flag=true;
				String x="1";
				while (flag == true)
				{
						driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:tbTableToolbar:new')]")).click();
						count=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
						if (count.equalsIgnoreCase(x))
						{
								flag=false;
						}
				}
				driver.findElement(By.xpath("//input[contains(@id,'mainBody:vboxlist:s_b2_aav::content')]")).clear();
				logger.info("Enter N series");
				driver.findElement(By.xpath("//input[contains(@id,'s_b0_aak')]")).sendKeys("NOSERIE14" + Keys.ENTER);
				logger.info("Click on new icon to add rule");
				String counta=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
				boolean flaga=true;
				String xa="1";
				while (flaga == true)
				{
						driver.findElement(By.xpath("//div[contains(@id,'s_b3_aaj:tbTableToolbar:new')]")).click();
						counta=driver.findElement(By.xpath("//div[contains(@id,'s_b3_aaj:innerTbl::db')]/table")).getAttribute("_rowcount");
						if (counta.equalsIgnoreCase(xa))
						{
								flaga=false;
						}
				}
				driver.findElement(By.xpath("//img[contains(@id,'mainBody:vboxlist:s_bu_aad:tbTableToolbar:new::icon')]")).click();
				logger.info("Click on new to add  new asset Element");
				logger.info("Select  Activité :Location financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aae::content')]"))).selectByVisibleText("Location financière");
				logger.info("Select Product:SC-1_Location Financière Groupe BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aaf::content')]"))).selectByVisibleText("SC-1_Location Financière Groupe BP");
				driver.findElement(By.xpath("//input[contains(@id,'tf_s_ca_aa4::content')]")).sendKeys(Keys.ENTER);
				logger.info("Enter Code :0092155");
				driver.findElement(By.xpath("//input[contains(@id,'tf_s_ca_aa4::content')]")).sendKeys("0092155" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_aai::content')]")).clear();
				logger.info("Enter Description :CARTE GRISE	");
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_aai::content')]")).sendKeys("CARTE GRISE	" + Keys.ENTER);
				logger.info("Enter Montant:420.54");
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_abp::content')]")).sendKeys("420.54" + Keys.ENTER);
				logger.info("Select Catégorie de bien: Carte Grise Immo **");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'mainBody:vboxlist:s_ca_abs::content')]"))).selectByVisibleText("Carte Grise Immo **");
				logger.info("Select TAX :Exonere de TVA");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abr::content')]"))).selectByVisibleText("Exonere de TVA");
				logger.info("Select currency as : Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abx::content')]"))).selectByVisibleText("Euro");
				logger.info("Select Catégorie de bien: Carte Grise Immo **");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'mainBody:vboxlist:s_ca_abs::content')]"))).selectByVisibleText("Carte Grise Immo **");
				logger.info("Click on New icon to add Component");
				String count1=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
				boolean flag1=true;
				String x1="1";
				while (flag1 == true)
				{
						driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:tbTableToolbar:new')]")).click();
						count1=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
						if (count1.equalsIgnoreCase(x1))
						{
								flag1=false;
						}
				}
				driver.findElement(By.xpath("//input[contains(@id,'s_b2_aad::content')]")).sendKeys(Keys.ENTER);
				logger.info("Click on new icon to add rule");
				String counta1=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
				boolean flaga1=true;
				String xa1="1";
				while (flaga1 == true)
				{
						driver.findElement(By.xpath("//div[contains(@id,'s_b3_aaj:tbTableToolbar:new')]")).click();
						counta1=driver.findElement(By.xpath("//div[contains(@id,'s_b3_aaj:innerTbl::db')]/table")).getAttribute("_rowcount");
						if (counta1.equalsIgnoreCase(xa1))
						{
								flaga1=false;
						}
				}
				driver.findElement(By.xpath("//select[contains(@id,'s_b3_aaj:innerTbl:0:tlfcode::content')]")).sendKeys(Keys.ENTER);
				logger.info("Click on new for adding 3rd Element");
				driver.findElement(By.xpath("//img[contains(@id,'mainBody:vboxlist:s_bu_aad:tbTableToolbar:new::icon')]")).click();
				logger.info("Select  Activité :Location financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aae::content')]"))).selectByVisibleText("Location financière");
				logger.info("Select Product:SC-1_Location Financière Groupe BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aaf::content')]"))).selectByVisibleText("SC-1_Location Financière Groupe BP");
				logger.info("Enter Code :0098721");
				driver.findElement(By.xpath("//input[contains(@id,'tf_s_ca_aa4::content')]")).sendKeys("0098721" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_aai::content')]")).clear();
				logger.info("Enter Description :AUTRE  ASSET	");
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_aai::content')]")).sendKeys("AUTRE  ASSET" + Keys.ENTER);
				logger.info("Enter Montant:6000");
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_abp::content')]")).sendKeys("6000" + Keys.ENTER);
				logger.info("Select Activtiy :Machines bureau et mat. info **");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'mainBody:vboxlist:s_ca_abq::content')]"))).selectByVisibleText("Machines bureau et mat. info");
				logger.info("Select Catagories Asst:Machines à écrire et à calculer **");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'mainBody:vboxlist:s_ca_abs::content')]"))).selectByVisibleText("Machines à écrire et à calculer");
				logger.info("Select TAX as:T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abr::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("Select currency as : Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abx::content')]"))).selectByVisibleText("Euro");
				logger.info("Click on New icon to add Component");
				String count2=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
				boolean flag2=true;
				String x2="1";
				while (flag2 == true)
				{
						driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:tbTableToolbar:new')]")).click();
						count2=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
						if (count2.equalsIgnoreCase(x2))
						{
								flag2=false;
						}
				}
				driver.findElement(By.xpath("//input[contains(@id,'mainBody:vboxlist:s_b2_aav::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'mainBody:vboxlist:s_b2_aav::content')]")).sendKeys("NOSERIE");
				logger.info("Click on new icon to add Lois d'amortissement fiscal");
				String counta2=driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:innerTbl::db')]/table")).getAttribute("_rowcount");
				boolean flaga2=true;
				String xa2="1";
				while (flaga2 == true)
				{
						driver.findElement(By.xpath("//div[contains(@id,'s_b3_aaj:tbTableToolbar:new')]")).click();
						counta2=driver.findElement(By.xpath("//div[contains(@id,'s_b3_aaj:innerTbl::db')]/table")).getAttribute("_rowcount");
						if (counta2.equalsIgnoreCase(xa2))
						{
								flaga2=false;
						}
				}
				logger.info("Click on save");
				driver.findElement(By.xpath("//div[contains(@id,'s_ce_aab:s_bey_aab')]")).click();
				driver=getHandleToWindow("Cassiopae",driver);
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bey_aab::icon')]")).click();
				logger.info("click on save button");
				Asset_reference=driver.findElement(By.xpath("//a[contains(@id, 'secId:mainBody:vboxlist:s_kz_aac:innerTbl:0:itrnum')]")).getText();
				String sqlexp="select * from immotranche where ITRNOM='" + Asset_Name + "'";
				String col_name="ITRNUM";
				String Asset_Ref_DB=Utility.DBvalidation(sqlexp,col_name,Scenario_Name);
				logger.info("Asset reference by DB : " + Asset_Ref_DB);
				Assert.assertEquals(Asset_reference,Asset_Ref_DB);
				logger.info("*********************  NL Contract :" + method.getName() + " : Pass **********************");
		}

		@Test(priority=38,enabled=false)
		public void Contract_2_Creation_Contrat_12_EVD_ENGA_en_phase_Engagement(Method method) throws Throwable
		{
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				logger.info("Enter Contract Number" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contract:" + Contract_Ref);
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				driver.switchTo().defaultContent();
				driver.findElement(By.linkText("More >>")).click();
				logger.info("Click on Event EVD_ENGA");
				driver.findElement(By.xpath("//span[contains(@title,'EVD_ENGA')]")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Click on Finish button");
				WebElement finish_button=driver.findElement(By.xpath("//button[contains(@id,'button_bar:btnFinish')]"));
				finish_button.click();
				logger.info("Click on Valider button");
				WebElement close_button=driver.findElement(By.xpath("//button[contains(text(),'Valider')]"));
				close_button.click();
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				String Step=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa6::content')]")).getAttribute("title");
				logger.info(Step);
				Assert.assertEquals("Engagé",Step);
		}

		@Test(priority=39,enabled=false)
		public void Contract_2_Creation_Contrat_13_EVD_MJALON(Method method) throws Throwable
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				Client_Frame(driver);
				logger.info("Enter Contract ref Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contract:" + Contract_Ref);
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				driver.switchTo().defaultContent();
				driver.findElement(By.linkText("More >>")).click();
				logger.info("Click on Event EVD_MJALON:Caractéristiques signalétiques");
				driver.findElement(By.xpath("//span[contains(@title,'EVD_MJALON')]")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Enter Value at LGD à l'Octroi:-8");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:TFDCCHSID1690::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:TFDCCHSID1690::content')]")).sendKeys("8");
				logger.info("Click on Button:Terminer ");
				WebElement finish_button=driver.findElement(By.xpath("//button[contains(@id,'button_bar:btnFinish')]"));
				finish_button.click();
				WebElement close_button=driver.findElement(By.xpath("//button[contains(@id,'s_u4_aav')]"));
				close_button.click();
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				String Step=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa6::content')]")).getAttribute("title");
				logger.info(Step);
				Assert.assertEquals("Engagé",Step);
		}

		@Test(priority=40,enabled=false)
		public void NL_Contract_Event_Mise_à_jour_du_statut_de_la_RUM(Method method) throws Throwable
		{
				logger.info("Switch to Default_Frame");
				Default_Frame(driver);
				logger.info("Click on Dossiers Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Dossiers option in sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				logger.info("Switch to client Frame");
				Client_Frame(driver);
				logger.info("Enter  Dossiers Ref. No: " + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'dosnum::content')]")).sendKeys(Contract_Ref);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Dossiers : " + Contract_Ref);
				driver.findElement(By.xpath("//a[contains(@id,'innerTbl:0:dosnum')]")).click();
				logger.info("Switch to Default Frame");
				Default_Frame(driver);
				try
				{
						logger.info("Check whether Event 'Mise a jour du statut de la RUM' is present if not Click on event 'Creation d'une RUM'");
						driver.findElement(By.xpath("//span[contains(@title,'EVD_MDTCHG') and contains(text(),'Mise à jour du statut de la RUM')]")).click();
				}
				catch (Exception e)
				{
						logger.info("Click on Event : EVD_RUMNEW - Creation d'une RUM");
						driver.findElement(By.xpath("//span[contains(@title,'EVD_RUMNEW')]")).click();
						logger.info("Switch to Client Frame");
						Client_Frame(driver);
						logger.info("Switch to Child Frame");
						Child_Frame(driver);
						logger.info("Click on Search icon next to IBAN field");
						driver.findElement(By.xpath("//img[contains(@id,'bSr_s_km_aaf::icon')]")).click();
						logger.info("Enter Initule du compte as : THERM ISOL");
						logger.info("Click on Find icon");
						driver.findElement(By.xpath("//img[contains(@id,'tbTableToolbar:find::icon')]")).click();
						logger.info("Click on Valider");
						driver.findElement(By.xpath("//button[contains(text(),'Valider')]")).click();
						logger.info("Click on Suivant");
						driver.findElement(By.xpath(Suivant)).click();
						logger.info("Click on Terminer");
						driver.findElement(By.xpath(Terminer)).click();
						logger.info("Click on Valider");
						driver.findElement(By.xpath(Valider)).click();
				}
				finally
				{
						try
						{
								Client_Frame(driver);
								Child_Frame(driver);
								driver.findElement(By.xpath("//button[contains(text(),'Annuler')]")).click();
						}
						catch (Exception e)
						{
						}
						logger.info("Switch to Default Frame");
						Default_Frame(driver);
						logger.info("Click on Event : 'Mise a jour du statut de la RUM'");
						driver.findElement(By.xpath("//span[contains(@title,'EVD_MDTCHG') and contains(text(),'Mise à jour du statut de la RUM')]")).click();
						logger.info("Switch to Client Frame");
						Client_Frame(driver);
						logger.info("Switch to Child Frame");
						Child_Frame(driver);
						logger.info("click on 'Données' first element check box ");
						int rowcount=Integer.parseInt(driver.findElement(By.xpath("//div[contains(@id,'1pc9:innerTbl::db')]//table")).getAttribute("_rowcount"));
						logger.info("Get the count of records present - Need to make Statut as Valide all present records");
						logger.info("row count:-" + rowcount);
						for (int i=0; i < rowcount; i++)
						{
								if (!driver.findElement(By.xpath("//input[contains(@id,':innerTbl:" + i + ":flagselected::content')]")).isSelected())
								{
										driver.findElement(By.xpath("//input[contains(@id,':innerTbl:" + i + ":flagselected::content')]")).click();
								}
								logger.info("select Statut as : Valide");
								WebElement text=driver.findElement(By.xpath("//select/option[contains(text(),'Valide')]"));
								text.click();
						}
						logger.info("Click on Suivant");
						driver.findElement(By.xpath(Suivant)).click();
						logger.info("Click on Terminer");
						driver.findElement(By.xpath(Terminer)).click();
						logger.info("Click on Valider");
						driver.findElement(By.xpath(Valider)).click();
				}
				logger.info("Switch to Default Frame");
				Default_Frame(driver);
				logger.info("Switch to Client Frame");
				Client_Frame(driver);
				String Phase=driver.findElement(By.xpath("//select[contains(@id,'s_m9_aa4::content')]")).getAttribute("title");
				logger.info("New Phase of contract is : " + Phase);
				String Step=driver.findElement(By.xpath("//select[contains(@id,'s_m9_aa6::content')]")).getAttribute("title");
				logger.info("New Step of contract is : " + Step);
				Assert.assertEquals(Phase,"Engagement");
				Assert.assertEquals(Step,"Engagé");
		}

		@Test(priority=41,enabled=false)
		public void NL_Contract_Event_Passage_en_phase_Réalisation(Method method) throws Throwable
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Dossiers')]")).click();
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]")).click();
				logger.info("Click on Contract Sub-Menu");
				Client_Frame(driver);
				logger.info("Enter Contract ref Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contract:" + Contract_Ref);
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				logger.info("Navigate to Rubriques accessoires tab");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques accessoires')]")).click();
				logger.info("Navigate to Rubriques accessoires tab");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques accessoires')]")).click();
				logger.info("Click on First Row");
				driver.findElement(By.xpath("//span[contains(@id,'secId:mainBody:vboxlist:frmDossElementsTblAccElement:s_mx_aaf:innerTbl:0:drulibelle::content')]")).click();
				logger.info("Click on Frais de dossiers R Row");
				driver.findElement(By.xpath("//span[contains(@id,'secId:mainBody:vboxlist:frmDossElementsTblAccElement:s_mx_aaf:innerTbl:1:drulibelle::content')]")).click();
				logger.info("Select Imputation analytique:Frais de dossiers R");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aae::content')]"))).selectByVisibleText("Frais de dossiers");
				logger.info("Click on Payment Schedule");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:tbTableToolbar:btnPaymentScheduleAcc::icon')]")).click();
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				logger.info("Click on Save icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:s_m4_aad:s_bey_aab::icon')]")).click();
				driver.switchTo().defaultContent();
				driver.findElement(By.linkText("More >>")).click();
				logger.info("Click on EVD_REAL Event");
				driver.findElement(By.xpath("//span[contains(@title,'EVD_REAL')]")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Click on Terminer");
				WebElement finish_button=driver.findElement(By.xpath("//button[contains(@id,'button_bar:btnFinish')]"));
				finish_button.click();
				logger.info("Click on Fermer");
				WebElement close_button=driver.findElement(By.xpath("//button[contains(@id,'s_u4_aav')]"));
				close_button.click();
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				String Phase=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa4::content')]")).getAttribute("title");
				logger.info(Phase);
				Assert.assertEquals("En cours",Phase);
		}

		@Test(priority=42,enabled=false)
		public void New_Expense_Creation(Method method) throws Throwable
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Expense Menu");
				Expense_M=driver.findElement(By.xpath(Expense_M1));
				Expense_M.click();
				logger.info("Click on Expense Sub-Menu");
				Expense_DropDown=driver.findElement(By.xpath(Expense_DropDown1));
				Expense_DropDown.click();
				Client_Frame(driver);
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//*[contains( @src,'new_ena.png')]")).click();
				logger.info("Select Company/Société");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'aae::content')]"))).selectByVisibleText("1-NATIXIS LEASE");
				logger.info("Select Phase  Validée");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_g7_aad::content')]"))).selectByVisibleText("Validée");
				logger.info("Select *Activité::Location financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_g7_aao::content')]"))).selectByVisibleText("Location financière");
				logger.info("Select Product::SC-1_Location Financière Groupe BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_g7_aaf::content')]"))).selectByVisibleText("SC-1_Location Financière Groupe BP");
				logger.info("Enter Actor Reference / Code acteur  :0092155");
				driver.findElement(By.xpath("//input[contains(@id,'aa6::content')]")).sendKeys("0092155" + Keys.ENTER);
				logger.info("Enter  Reference / Référence externe :TEST");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_g7_abb::content')]")).sendKeys("Test" + Keys.ENTER);
				logger.info("Click on New Icon under Liste des taxes");
				driver.findElement(By.xpath("//div[contains(@id,'abn:tbTableToolbar:new')]")).click();
				logger.info("Select Type des taxes/Normal VAT :Exonere de TVA");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'abn:innerTbl:0:taxcode::content')]"))).selectByVisibleText("Exonere de TVA");
				logger.info("Enter Amount/Montant");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_g7_abn:innerTbl:0:dtamtht::content')]")).click();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_g7_abn:innerTbl:0:dtamtht::content')]")).sendKeys("50000" + Keys.ENTER);
				logger.info("Navigate to Lignes tab");// a[contains(text(),'Identification')]
				driver.findElement(By.xpath("//a[contains(text(),'Lignes')]")).click();
				driver.findElement(By.xpath("//div[contains(@id,'aac:tbTableToolbar:new')]")).click();
				logger.info("Enter Contract Number" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'aak::content')]")).sendKeys(Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'aag::content')]")).sendKeys(Keys.ENTER);
				logger.info("Select Type de ligne:Charges");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_hd_aad::content')]"))).selectByVisibleText("Charges");
				logger.info("Select Company/Société");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_hd_aat::content')]"))).selectByIndex(1);
				logger.info("Select Type des taxes/Normal VAT :Exonere de TVA");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_hd_aa5')]"))).selectByVisibleText("Exonere de TVA");
				logger.info("Click on Save");
				driver.findElement(By.id("secId:s_g5_aab:s_bey_aab::icon")).click();
				boolean process=driver.findElement(By.xpath("//td[@id='secId:s_g4_aai']")).isDisplayed();
				Assert.assertEquals(true,process);
				String String1=driver.findElement(By.xpath("//td[@id='secId:s_g4_aai']")).getText();
				logger.info(String1);
				String[] parts=String1.split(" > Créée le");
				String part1=parts[0];
				logger.info("part1 :" + part1);
				String[] parts2=part1.split("< ");
				part2=parts2[1];
				logger.info("part2 :" + part2);
				Putdata(Excel_Testdata_Column,Excel_Row_No,part2,Excel_SheetName);
				String Référence=null;
				String sqlexp="select * from DEPENSE where DEPREFEXTERNE='" + Référence + "'";
				String col_name="DEPNUM";
				String Expense_ref_DB=DBvalidation(sqlexp,col_name,Scenario_Name);
				Assert.assertEquals(part2,Expense_ref_DB);
				logger.info("Expense creation pass");
		}

		@Test(priority=43,enabled=false)
		public void NL_Contract_Event_Mise_en_service(Method method) throws Throwable
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Contract sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				Client_Frame(driver);
				logger.info("Enter Contract Number" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Enter Open Contract Number" + Contract_Ref);
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				driver.switchTo().defaultContent();
				driver.findElement(By.linkText("More >>")).click();
				logger.info("Click on Mise en service Evnet");
				driver.findElement(By.xpath("//span[contains(@title,'EVD_MEL')]")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				driver.findElement(By.xpath("//button[contains(@id,'button_bar:btnNext')]")).click();//
				logger.info("Enter  Refrence facture 1 ");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:0:extlabel::content')]")).sendKeys("FACT FOUR 1" + Keys.ENTER);
				logger.info("Enter  Start date ");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:0:facdtfacture::content')]")).sendKeys(Start_date + Keys.ENTER);
				logger.info("click on first check box");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:0:selected')]")).click();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:0:selected')]")).click();
				if (!driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:0:selected')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:0:selected')]")).click();
				}
				logger.info("Enter Montant amount 6 000,05");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aad:innerTbl:0:dtamttaxe')]")).sendKeys();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aad:innerTbl:0:dtamttaxe')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aad:innerTbl:0:dtamttaxe')]")).sendKeys("6000,05" + Keys.ENTER);
				logger.info("Enter  Refrence : FACTURE 2 FLIP ");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:extlabel::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:extlabel::content')]")).sendKeys("FACTURE 2 FLIP" + Keys.ENTER);
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:facdtfacture::content')]")).click();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:facdtfacture::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:facdtfacture::content')]")).sendKeys(Start_date + Keys.ENTER);
				logger.info("click on second check box");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:selected::content')]")).click();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:selected::content')]")).click();
				if (!driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:selected::content')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:selected::content')]")).click();
				}
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:1:extlabel::content')]")).click();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:extlabel::content')]")).click();
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aaa:innerTbl:2:extlabel::content')]")).click();
				logger.info("Enter amount:2200 ");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:s_iw_aah::content')]")).sendKeys("2200" + Keys.ENTER + Keys.ENTER);//
				driver.findElement(By.xpath(Suivant)).click();//
				driver.findElement(By.xpath(Suivant)).click();//
				logger.info("click on check box  facture echennicer");
				driver.findElement(By.xpath("//label[contains(text(),'Facture échéancier')]/input")).click();
				driver.findElement(By.xpath("//label[contains(text(),'Facture échéancier')]/input")).click();
				if (!driver.findElement(By.xpath("//label[contains(text(),'Facture échéancier')]/input")).isSelected())
				{
						driver.findElement(By.xpath("//label[contains(text(),'Facture échéancier')]/input")).click();
				}
				logger.info("click on check box Relevé d'encours");
				driver.findElement(By.xpath("//*[@id='eventTasks:pnlTaskFirst:s_bga_aae::content']/li[3]/ul/li[2]/label/input")).click();
				driver.findElement(By.xpath("//*[@id='eventTasks:pnlTaskFirst:s_bga_aae::content']/li[3]/ul/li[2]/label/input")).click();
				if (!driver.findElement(By.xpath("//*[@id='eventTasks:pnlTaskFirst:s_bga_aae::content']/li[3]/ul/li[2]/label/input")).isSelected())
				{
						driver.findElement(By.xpath("//*[@id='eventTasks:pnlTaskFirst:s_bga_aae::content']/li[3]/ul/li[2]/label/input")).click();
				}
				WebElement finish_button=driver.findElement(By.xpath("//button[contains(@id,'button_bar:btnFinish')]"));
				finish_button.click();
				WebElement close_button=driver.findElement(By.xpath("//button[contains(@id,'s_u4_aat')]"));
				close_button.click();
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				String Phase=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa4::content')]")).getAttribute("title");
				logger.info(Phase);
				String Step=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa6::content')]")).getAttribute("title");
				logger.info(Step);
				Assert.assertEquals("Exploitation",Phase);
		}

		@Test(priority=44,enabled=false)
		public void NL_Expense_Event_Passage_en_bon_à_payer_trésorerie_de_dépenses(Method method) throws Throwable
		{
				logger.info("\n" + "*************** Contract  " + method.getName() + " start from hare  ***************");
				logger.info("Click on Expense Menu");
				driver.findElement(By.xpath(Expense_M1)).click();
				logger.info("Click on Expense Sub-Menu");
				driver.findElement(By.xpath(Expense_DropDown1)).click();
				Client_Frame(driver);
				String depense1=Utility.getdata(Excel_Testdata_Column,42,Excel_SheetName);
				logger.info("search an Dépenses no:-" + depense1);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_gm_aao:innerTbl:numdepense::content')]")).sendKeys(depense1 + Keys.ENTER);
				logger.info("click on search button");
				driver.findElement(By.xpath("//img[contains(@id,':s_gm_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("open a Dépenses");
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_gm_aao:innerTbl:0:numdepense')]")).click();
				logger.info("Click on Event :Passage en bon à payer technique d'une dépense");
				driver.switchTo().defaultContent();
				driver.findElement(By.linkText("Passage en bon à payer technique d'une dépense")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Click on Terminer");
				driver.findElement(By.xpath(Terminer)).click();
				logger.info("Click on Fermer");
				driver.findElement(By.xpath(Fermer)).click();
				driver.switchTo().defaultContent();
				logger.info("Click on Event :Passage en bon à payer trésorerie de dépenses / Authorize expense payment");
				driver.findElement(By.linkText("Passage en bon à payer trésorerie de dépenses")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Click on Terminer");
				driver.findElement(By.xpath(Terminer)).click();
				logger.info("Click on Fermer");
				driver.findElement(By.xpath(Fermer)).click();
				driver.switchTo().defaultContent();
				boolean process11=driver.findElement(By.linkText("Blocage du règlement des dépenses")).isDisplayed();
				Assert.assertEquals(true,process11);
				logger.info("Click on Event :Modification de l'échéancier d'une dépense");
				driver.findElement(By.xpath("//span[contains(@title,'EVDEP_MODIFECH')]")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Select Mode de règlement (Décaissement):");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_gv_aau::content')]"))).selectByVisibleText("Lettre chèque");
				logger.info("Click on Finish");
				WebElement finish_button=driver.findElement(By.xpath("//button[contains(@id,'button_bar:btnFinish')]"));
				finish_button.click();
				logger.info("Click on Fermer");
				WebElement close_button=driver.findElement(By.xpath("//button[contains(@id,'s_u4_aav')]"));
				close_button.click();
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				String Montant=driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:tabIdentificationPg6:s_g7_abf::content')]")).getAttribute("Value");
				logger.info("Montant" + Montant);
				Assert.assertEquals("50 000,00",Montant);
		}

		@AfterMethod
		public void Method_Ended(Method method,ITestResult testResult) throws Throwable
		{
				long time=testResult.getEndMillis() - testResult.getStartMillis();
				time=(time / 60000);
				try
				{
						NLSCUAT_2596_Logout_From_Application();
						driver.unregister(eventListener);
						driver.quit();
						driver1=new Augmenter().augment(Utility.DriverInitializer(browser1));
				}
				catch (Exception e1)
				{
						driver.unregister(eventListener);
						driver.quit();
						driver1=new Augmenter().augment(Utility.DriverInitializer(browser1));
						logger.info("NLSCUAT_3049_Logout_From_Application :-" + e1.getMessage());
				}
				try
				{
						Sceenshot_Folder_Path=Scrennshot_path + Today() + "\\" + className + "\\" + method.getName();
						New_Video_Location=Scrennshot_path + Today() + "\\" + className + "\\" + method.getName() + "_Video" + ".mp4";
						Issue_Video_path=Utility.Video_Creation(New_Video_Location,Sceenshot_Folder_Path);
						logger.info(Issue_Video_path);
						Delete_Existing_Screenshot_Folder(Scrennshot_path + Today() + "\\" + className + "\\" + Scenario_Name);
				}
				catch (Exception e1)
				{
						e1.printStackTrace();
				}
				if (testResult.isSuccess() == false)
				{
						try
						{
								logger.info("****" + method.getName() + " is Failed");
								logger.info("****Exception on " + method.getName() + " - " + testResult.getThrowable().getMessage().split("Command")[0]);
								Utility.Putdata(Excel_Status_Column,Excel_Row_No,"Fail",Excel_SheetName);
								Utility.Putdata(Excel_Error_Column,Excel_Row_No,testResult.getThrowable().getMessage().split("Command")[0],Excel_SheetName);
								// Issue = NewIssueCreation( Project, summary, username1, password1, description, assignee, issuetype, components, Module, Feature );
								// AttachedDocumetToJira( Issue, Issue_Reproduce_steps_logs, Issue_Video_path, username1, password1);
								// Putdata( Issue_Column, Excel_Row_No, Issue, Excel_SheetName );
								// logger.info( "Attached document for issue on " + method.getName() + " - " + Issue + " Is successfull" );
								if (method.getName().equals("NL_2596Create_Contract"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,Contract_Ref,Excel_SheetName);
										for (int i=1; i <= 4; i++)
										{
												Utility.Putdata(Excel_Status_Column,Excel_Row_No + i,"Fail",Excel_SheetName);
										}
								}
								if (method.getName().equals("Creation_Contrat_11_Materiels_Add_New_Asset"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,"Fail",Excel_SheetName);
								}
								if (method.getName().equals("New_Expense_Creation"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,"Fail",Excel_SheetName);
								}
						}
						catch (Exception e)
						{
								logger.info(e.getMessage());
						}
				}
				else
				{
						try
						{
								logger.info("****" + method.getName() + " is Passed");
								Putdata(Excel_Status_Column,Excel_Row_No,"Pass",Excel_SheetName);
								if (method.getName().equals("NL_2596Create_Contract"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,Contract_Ref,Excel_SheetName);
										for (int i=1; i <= 4; i++)
										{
												Utility.Putdata(Excel_Status_Column,Excel_Row_No + i,"Pass",Excel_SheetName);
										}
								}
								if (method.getName().equals("Creation_Contrat_11_Materiels_Add_New_Asset"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,Asset_reference,Excel_SheetName);
								}
								if (method.getName().equals("New_Expense_Creation"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,part2,Excel_SheetName);
								}
						}
						catch (Exception e)
						{
								logger.info(e.getMessage());
						}
				}
		}

		@AfterClass
		public void Terminatebrowser()
		{
				driver1.quit();
		}

		@AfterTest
		public void AfterTest()
		{
		}

		@AfterSuite
		public void End_Test_Class()
		{
		}
}