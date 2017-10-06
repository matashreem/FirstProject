
package Scenarios_Reg;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NLSCUAT_14_3051 extends Utility implements locators
{

		static String Scenario_Name=null;
		String Short_name1=null;
		String part2=null;
		String App_ver=null;
		String part224=null;
		int i=0;
		String App_Version=null;
		String Contract_Ref=null;
		String Asset_Ref=null;
		String Exp_ref=null;
		EventFiringWebDriver driver=null;
		WebDriverEventListener eventListener=null;
		String Issue=null;
		String Issue_Document_Path=null;
		String Issue_Reproduce_steps_logs=null;
		String Issue_Video_path=null;
		String summary=null;
		Logger logger=null;
		String className=this.getClass().getSimpleName();
		int Excel_Row_No=0;
		WebDriver driver1=null;
		String browser1=null;

		@BeforeSuite // ***
		public void Start_Test_Class() throws InterruptedException,IOException
		{
				// Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\kill_chrome_tasks.bat");
				// .sleep(15000);
				IE_SETTING();
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
				Excel_Row_No=getClass().getMethod(method.getName(),java.lang.reflect.Method.class).getAnnotation(org.testng.annotations.Test.class).priority();
				Scenario_Name=method.getName();
				summary=Scenario_Name;
				logger=Logger.getLogger(Scenario_Name);
				Issue_Reproduce_steps_logs=System.getProperty("user.dir") + "\\Execution_Results\\Console_logs\\" + Today() + "\\" + className + "\\" + Scenario_Name + "\\" + Scenario_Name + "_" + ReportDateTime1() + ".html";
				PropertyConfigurator.configure(Logger_file(Scenario_Name,Issue_Reproduce_steps_logs));
				Delete_Existing_Screenshot_Folder(Scrennshot_path + Today() + "\\" + className + "\\" + Scenario_Name);
				logger.info("//*******Execution Started for Test - :- " + Scenario_Name + " ********//");
				Contract_Ref=Utility.getdata(Excel_Testdata_Column,233,Excel_SheetName);
				logger.info(Contract_Ref);
				String USERPWD="ORFI";
				if (Scenario_Name.equals("Expense_Event_Passage_en_bon_à_payer_trésorerie_de_dépenses"))
				{
						USERPWD="URBAIN";
				}
				try
				{
						NL_3051_Login_to_Application(USERPWD);
				}
				catch (Exception e1)
				{
						System.out.println("NL_3051_Login_to_Application :-");
						e1.printStackTrace();
				}
		}

		public void NL_3051_Login_to_Application(String USERPWD) throws Throwable 
		{
				logger.info("Nevigate to url - " + NATbaseUrl);
				driver1.get(NATbaseUrl);
				driver1.manage().window().maximize();
				String actual=driver1.getTitle();
				logger.info("Windows Title : " + actual);
				logger.info("Application launched Successfully");
				logger.info("Login with USER/PWD : - ORFI/ORFI");
				logger.info("Enter Username : ORFI");
				driver1.findElement(By.xpath("//input[contains(@id,'s_s0_aan')]")).sendKeys(USERPWD + Keys.ENTER);
				logger.info("Enter Password : ORFI");
				driver1.findElement(By.xpath("//input[contains(@id,'s_s0_aap')]")).sendKeys(USERPWD + Keys.ENTER);
				logger.info("Country  :- French (France) ");
				driver1.findElement(By.xpath("//select[contains(@id,'s_s0_aar::content')]")).sendKeys(Keys.TAB);
				new Select(driver1.findElement(By.xpath("//select[contains(@id,'s_s0_aar::content')]"))).selectByVisibleText("French (France)");
				logger.info("Click on login button");
				driver1.findElement(By.xpath("//button[contains(@id,'s_s0_aas')]")).click();
				driver=new EventFiringWebDriver(driver1);
				eventListener=new Utility();
				driver.register(eventListener);
				WebElement rxBtn=driver.findElement(By.xpath("//a[contains(@id,'s_tu_aak')]/span"));
				Assert.assertEquals(true,rxBtn.isDisplayed());
				App_Version=rxBtn.getText().split(":")[1];
				logger.info("Login into Application Successfully on version - " + App_Version);
		}

		public void NLSCUAT_3051_Logout_From_Application() throws Throwable 
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Logged Out button");
				driver.findElement(By.xpath("//a[contains(@id,'s_tu_aa0')]")).click();
				logger.info("Logged Out Successfully");
		}

		@Test(priority=233,enabled=true)
		public void NLSCUAT_3051_Contract_Creation(Method method) throws Throwable  
		{
				logger.info(method.getName() + " Starts from here ******************************");
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Contract sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				driver.switchTo().frame(2);
				logger.info("Click on New icon ");
				driver.findElement(By.xpath("//img[contains( @id,'s_n5_aao:tbTableToolbar:new::icon')]")).click();
				logger.info("------------------ Informations générales Tab ------------------");
				logger.info("Select Société : 1-NATIXIS LEASE");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aak::content')]"))).selectByVisibleText("1-NATIXIS LEASE");
				logger.info("Select Activité	: Crédit bail mobilier");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aaq::content')]"))).selectByVisibleText("Crédit bail mobilier");
				logger.info("Select Produit	: 01_CBM Groupe BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aar::content')]"))).selectByVisibleText("01_CBM Groupe BP");
				logger.info("Select Devise: Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aas::content')]"))).selectByVisibleText("Euro");
				logger.info("Select Regimal Fiscal : Régime général");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aau::content')]"))).selectByVisibleText("Régime général");
				Short_name1="NL_3051" + "_" + ReportDateTime();
				logger.info("Eneter Short Name" + Short_name1);
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_aav::content')]")).sendKeys(Short_name1);
				logger.info("Select Pool : Pool, partenaire en direct");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aax::content')]"))).selectByVisibleText("Pool, chef de file");
				logger.info("Select Secteur de gestion : Exploitation");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_m9_aa0::content')]"))).selectByVisibleText("Exploitation");
				logger.info("Select Délégation : Hors Délégation");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:CMBCCHSID702::content')]"))).selectByVisibleText("Hors Délégation");
				new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
				logger.info("Select Lease back	: Oui");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:CMBCCHSID716::content')]"))).selectByVisibleText("Non");
				logger.info("Enter LGD à l'Octroi : 2");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:TFDCCHSID1690::content')]")).sendKeys("2");
				String Start_date=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				logger.info("Enter Date de début	 : " + Start_date);
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_aa9::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_aa9::content')]")).sendKeys(Start_date);
				logger.info("Enter Date dem. Financement	: " + Start_date);
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abn::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abn::content')]")).sendKeys(Start_date);
				logger.info("Enter No of Period(Duree) :40");
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abg::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abg::content')]")).sendKeys("40");
				logger.info("Enter Montant dem. Financement	:6789.12");
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abs::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_m9_abs::content')]")).sendKeys("6789.12");
				// --------------------------------------------------------------- Navigate to Attached Actors Tab ----------------------------------------------------
				logger.info("Navigate to Attached Actors Tab ************");
				driver.findElement(By.xpath("//a[contains(text(),'Acteurs affectés')]")).click();
				// ------------------------------------------------- Adding 1 Actor -------------------------------------------------------------------------------------
				logger.info("Click on New Icon to Add 1st Actor");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aad:tbTableToolbar:new::icon')]")).click();
				logger.info("Select role :Client preneur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Client preneur");
				logger.info("Enter actor code : 1291958");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("1299459" + Keys.ENTER);
				logger.info("Click on New Icon to Add Actor Address");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Addresses as:-147 RUE DU GENERAL DE GAULLE - VILLIERS SUR MARNE 94350 France");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kt_aad::content')]"))).selectByVisibleText("147 RUE DU GENERAL DE GAULLE - VILLIERS SUR MARNE 94350 France");
				logger.info("Scrolling on Liste des paiements");
				logger.info("Select  Moyen de Paiment for Decaissement : Décaissement");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:daptype::content')]"))).selectByVisibleText("Décaissement");
				logger.info("Select  Moyen de Paiment for Decaissement : Encaissement");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:daptype::content')]"))).selectByVisibleText("Encaissement");
				logger.info("Select  Moyen de Paiment for Decaissement : Lettre chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Lettre chèque");
				logger.info("Select  Moyen de Paiment for Encaissement : Prélèvement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Prélèvement automatique");
				logger.info("Select  Compte for Encaissement :FR 76 16807001653206179021345 SAS NEO CONSTRUCTION");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:bankid::content')]"))).selectByVisibleText("FR 76 16807001653206179021345 SAS NEO CONSTRUCTION");
				// ------------------------------------------------- Adding 2 Actor -------------------------------------------------------------------------------------
				logger.info("--------------------- Click on New Icon to Add 2nd Actor -----------------------");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select Role :Fournisseur");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Fournisseur");
				logger.info("Enter code : 0092155");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("0092155" + Keys.ENTER);
				logger.info("Click on New icon at select New Address");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Addresses :-284 ROUTE DE LA REPUBLIQUE - DAVEZIEUX 07430 France");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kt_aad::content')]"))).selectByVisibleText("284 ROUTE DE LA REPUBLIQUE - DAVEZIEUX 07430 France");
				logger.info("Scrolling on Liste des paiements");
				logger.info("Enter Liste des Decaissement details :-Virement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Virement automatique");
				logger.info("Select  Compte for Decaissement : FR 76 10468026651083970020042 CAROSSERIE FRAPPA SAS");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:bankid::content')]"))).selectByVisibleText("FR 76 10468026651083970020042 CAROSSERIE FRAPPA SAS");
				logger.info("Enter2nd Encaissement details:-Chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque");// cheque manual
				// ------------------------------------------------- Adding 3 Actor -------------------------------------------------------------------------------------
				logger.info("--------------------- Click on New ICon to Add 3th Actor ---------------------");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select Role:Partenaire en Pool");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Partenaire en Pool");
				logger.info("Enter code : 0066004");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("0066004" + Keys.ENTER);
				logger.info("Click on opnen_all image icon");
				driver.findElement(By.xpath("//img[contains(@src,'open_all.png')]")).click();
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Addresses :-59 AV DE CHATOU - RUEIL-MALMAISON 92853 France");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kt_aad::content')]"))).selectByVisibleText("59 AV DE CHATOU - RUEIL-MALMAISON 92853 France");
				logger.info("Scrolling on Liste des paiements");
				logger.info("Select liste des Decaissement :Lettre chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Lettre chèque");
				logger.info("Select liste des Encaissement :Chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque");
				// ------------------------------------------------- Adding 4 Actor -------------------------------------------------------------------------------------
				logger.info("Scrolling on Acteurs affectés/ new Icon");
				logger.info("--------------------- Click on New ICon to Add 4rd Actor ---------------------");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select Role:Apporteur d'affaires BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Apporteur d'affaires BP");
				logger.info("Enter code : 087");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("087" + Keys.ENTER);
				logger.info("Click on New icon at select New Address");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Addresses : 9 AV NEWTON - PLACE WICLOW - ST QUENTIN YVELINES 78183 France");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kt_aad::content')]"))).selectByVisibleText("9 AV NEWTON - PLACE WICLOW - ST QUENTIN YVELINES 78183 France");
				logger.info("Scrolling on Liste des paiements");
				logger.info("Select liste des paiments : Virement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Virement automatique");
				logger.info("Select  Compte for Decaissement :FR 76 18707000665527030900256 Risq Apport");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:bankid::content')]"))).selectByVisibleText("FR 76 18707000665527030900256 Risq Apport");
				logger.info("Enter2nd paiements details:-Chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque");
				// ------------------------------------------------- Adding 5 Actor -------------------------------------------------------------------------------------
				logger.info("Scrolling on Acteurs affectés/ new Icon");
				logger.info("--------------------- Click on New ICon to Add 5th Actor ---------------------");
				driver.findElement(By.xpath("//div[contains(@id,'s_kx_aad:tbTableToolbar:new')]")).click();
				logger.info("Select Role:Garant 'Banque Populaire'");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ks_aae::content')]"))).selectByVisibleText("Garant 'Banque Populaire'");
				logger.info("Enter code : 087");
				driver.findElement(By.xpath("//input[contains(@id,'vboxlist:tf_s_ks_aaf::content')]")).sendKeys("087" + Keys.ENTER);
				logger.info("Click on New icon at select New Address");
				driver.findElement(By.xpath("//img[contains(@id,'s_kx_aai:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Addresses :-9 AV NEWTON - PLACE WICLOW - ST QUENTIN YVELINES 78183 France");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kt_aad::content')]"))).selectByVisibleText("9 AV NEWTON - PLACE WICLOW - ST QUENTIN YVELINES 78183 France");
				logger.info("Scrolling on Liste des paiements");
				logger.info("Select liste des paiments : Virement automatique");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:0:tmpcode::content')]"))).selectByVisibleText("Virement automatique");
				logger.info("Select  Compte for Decaissement : FR 76 18707000665527030900256 Risq Apport");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_kx_aao:innerTbl:0:bankid::content')]"))).selectByVisibleText("FR 76 18707000665527030900256 Risq Apport");
				logger.info("Enter2nd paiements details:-Chèque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_kx_aao:innerTbl:1:tmpcode::content')]"))).selectByVisibleText("Chèque");
				// ------------------------------------------------- Navigate To Pool Tab -------------------------------------------------------------------------------------
				logger.info("------------------------------------------------------------ Navigate To Pool  Tab -------------------------------------------------------------------- ");
				driver.findElement(By.xpath("//a[contains(text(),'Pool')]")).click();
				try
				{
						driver.findElement(By.xpath("//div[contains(@id,'s_ny_aae:innerTbl::db')]//tr")).isDisplayed();
				}
				catch (Exception e)
				{
						logger.info("Click on New Icon");
						driver.findElement(By.xpath("//img[contains(@id,'s_ny_aae:tbTableToolbar:new::icon')]")).click();
				}
				WebElement man1=driver.findElement(By.xpath("//div[contains(@id,'s_nw_aah:innerTbl::db')]//tr[2]"));
				Assert.assertEquals(true,man1.isDisplayed());
				logger.info("check for CDF check box for 2nd element if not then check it");
				if (!driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:0:partcdf::content')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:0:partcdf::content')]")).click();
				}
				logger.info("check for TVA CDF check box for 2nd element if not then check it");
				if (driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:0:pactvacdffin::content')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:0:pactvacdffin::content')]")).click();
				}
				logger.info("Enter No. of Period : 50");
				driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:0:partqpsplit::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:0:partqpsplit::content')]")).sendKeys("50" + Keys.ENTER);
				logger.info("check for TVA CDF check box for 1nd element");
				driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:0:pactvacdffin')]")).click();
				logger.info("Enter No. of Period : 50");
				driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:1:partqpsplit::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:1:partqpsplit::content')]")).sendKeys("50" + Keys.ENTER);
				logger.info("check for TEn direct sur Fin. check box for 2nd element");
				driver.findElement(By.xpath("//input[contains(@id,'s_nw_aah:innerTbl:1:pactvacdffin')]")).click();
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_ny_aai:tbTableToolbar:new')]")).click();
				new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
				logger.info("Select Type De Garantie : Garantie BANQUE POPULAIRE CBM");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_nx_aag:innerTbl:0:tgatypeconvention')]"))).selectByVisibleText("Garantie BANQUE POPULAIRE CBM");

				logger.info("\n" + "--------------------- Click on Rubriques financières Tab ---------------------" + "\n");
				driver.findElement(By.xpath("//a[contains(@id,'s_mx_aab::disAcr')]")).click();
				logger.info("Click on New icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_mx_aac:tbTableToolbar:new::icon')]")).click();
				logger.info("Imput analytique : Loyer");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_aae::content')]"))).selectByVisibleText("Loyer");
				driver.findElement(By.xpath("//select/option[contains(text(),' Client preneur')]")).click();
				logger.info("Enter Taux nominal : 4.28");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lg_aas::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lg_aas::content')]")).sendKeys("4.28" + Keys.ENTER);
				logger.info("Select Nature : Fixe");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_abu::content')]"))).selectByVisibleText("Fixe");
				logger.info("Enter VR % : 1.00");
				driver.findElement(By.xpath("//input[contains(@id,'s_lg_ab5::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_lg_ab5::content')]")).sendKeys("1" + Keys.ENTER);
				logger.info("Select Taxe : T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_abh::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("select Bases des décomptes (jours)	 as:-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_abl::content')]"))).selectByVisibleText("360 jours / an");
				logger.info("select Bases des décomptes (jours)	part2 as:360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lg_abm::content')]"))).selectByVisibleText("360 jours / an");
				logger.info("Scrolling on Liste des paiements");
				logger.info("Select Perception: Avance ou à échoir");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_ll_aak:innerTbl:0:drfperception::content')]"))).selectByVisibleText("Avance ou à échoir");
				logger.info("Enter Nombre de Periods : 40");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_ll_aak:innerTbl:0:drfnbperiode::conten')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_ll_aak:innerTbl:0:drfnbperiode::conten')]")).sendKeys("40" + Keys.ENTER);
				logger.info("Click on Calculate button");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_ll_aak:tbTableToolbar:btnFlowAmountCalculation::icon')]")).click();
				logger.info("Click on Payment Schedule");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_ll_aak:tbTableToolbar:btnPaymentSchedule::icon')]")).click();
				logger.info("Click on Close Button");
				driver.findElement(By.xpath("//button[contains(text(),'Fermer')]")).click();
				logger.info(" --------------------------------------------- Navigate to Rubriques accessoires tab.  ---------------------------------------------");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques accessoires')]")).click();
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:frmDossElementsTblAccElement:s_mx_aaf:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Imputation analytique :-Frais de dossiers");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aae::content')]"))).selectByVisibleText("Frais de dossiers");
				logger.info("select  Acteur/Client as preneur actor");
				driver.findElement(By.xpath("//select/option[contains(text(),' Client preneur')]")).click();
				logger.info("select  Taxe as :-T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa5::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("select  Devise as :-Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa6::content')]"))).selectByVisibleText("Euro");
				logger.info("select  Bases des décomptes (jours)	 as :-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa9::content')]"))).selectByVisibleText("360 jours / an");
				logger.info("select  Bases des décomptes (jours)	part 2 as :-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aba::content')]"))).selectByVisibleText("360 jours / an");
				if (!driver.findElement(By.xpath("//input[contains(@id,'s_lc_abl')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'s_lc_abl')]")).click();
				}
				logger.info("select on New Période as:-Jour(s)");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drfperiode')]"))).selectByVisibleText("Jour(s)");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drftxassiette::content')]")).clear();
				logger.info("Enter Taux: 1");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drftxassiette::content')]")).sendKeys("1"+Keys.ENTER);
				logger.info("Click on Payment Schedule");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:tbTableToolbar:btnPaymentScheduleAcc::icon')]")).click();
				logger.info("Click on close button");
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				// -------------------------------------------------------------- Adding 2 Rubriques accessoires -----------------------------------------------------------------
				new Actions(driver).sendKeys(Keys.PAGE_UP).perform();
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:frmDossElementsTblAccElement:s_mx_aaf:tbTableToolbar:new::icon')]")).click();
				logger.info("Select Refacturation frais de greffe");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aae::content')]"))).selectByVisibleText("Refacturation frais de greffe");
				logger.info("select actor type as :-Client preneur");
				driver.findElement(By.xpath("//select/option[contains(text(),' Client preneur')]")).click();
				logger.info("select  Type de montage as :-Montants explicites");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aak::content')]"))).selectByVisibleText("Montants explicites");
				logger.info("select  Taxe as :-T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa5::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("select  Devise as :-Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa6::content')]"))).selectByVisibleText("Euro");
				logger.info("select  Bases des décomptes (jours)	 as :-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aa9::content')]"))).selectByVisibleText("360 jours / an");
				logger.info("select  Bases des décomptes (jours)	part 2 as :-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lc_aba::content')]"))).selectByVisibleText("360 jours / an");
				if (driver.findElement(By.xpath("//input[contains(@id,'s_lc_abl')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'s_lc_abl')]")).click();
				}
				logger.info("select on New Période as:-Jour(s)");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:innerTbl:0:drfperiode')]"))).selectByVisibleText("Jour(s)");
				driver.findElement(By.xpath("//input[contains(@id,'s_lj_aah:innerTbl:0:drfmt')]")).clear();
				logger.info("Enter montenet: 1");
				driver.findElement(By.xpath("//input[contains(@id,'s_lj_aah:innerTbl:0:drfmt')]")).sendKeys("23" + Keys.ENTER);
				logger.info("Click on Payment Schedule");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lj_aah:tbTableToolbar:btnPaymentScheduleAcc::icon')]")).click();
				logger.info("Click on Fermer button");
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				// ------------------------------------------------- Rubriques de rétributions Tab -------------------------------------------------------------------------------------
				logger.info("\n" + "--------------------- Clicked on Rubriques de rétributions tab. ---------------------" + "\n");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques de rétributions')]")).click();
				logger.info("Click on New Icon to Add 1 element");
				driver.findElement(By.xpath("//div[contains(@id,'s_mx_aai:tbTableToolbar:new')]")).click();
				logger.info("Select Imputation analytique : Commission de risque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aae::content')]"))).selectByVisibleText("Commission de risque");
				logger.info("Select Acteur : Garant");
				driver.findElement(By.xpath("//select/option[contains(text(),'Garant ')]")).click();
				logger.info("Select Taxe : Assiette brute financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_mb_aam::content')]"))).selectByVisibleText("Assiette brute financière");
				logger.info("Select Taxe : T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa1::content')]"))).selectByVisibleText("Exonere de TVA");
				logger.info("select Bases des décomptes (jours)	 as:-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_mb_aa5::content')]"))).selectByVisibleText("360 jours / an");
				logger.info("select Bases des décomptes (jours)	part2 as:-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_mb_aa6::content')]"))).selectByVisibleText("360 jours / an");
				if (driver.findElement(By.xpath("//input[contains(@id,'s_mb_abg')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'s_mb_abg')]")).click();
				}
				logger.info("Enter nomber : 25");
				driver.findElement(By.xpath("//input[contains(@id,'s_lk_aaf:innerTbl:0:dr1nombre')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_lk_aaf:innerTbl:0:dr1nombre')]")).sendKeys("25" + Keys.ENTER);
				driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfperiode::content')]")).click();
				logger.info("select Bases des décomptes :-Mois");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfperiode::content')]"))).selectByVisibleText("Mois");
				logger.info("Enter Period : 40");
				driver.findElement(By.xpath("//input[contains(@id,'s_lk_aah:innerTbl:0:drfnbperiode')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_lk_aah:innerTbl:0:drfnbperiode')]")).sendKeys("40" + Keys.ENTER);
				driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfperiode::content')]")).click();
				logger.info("Enter Taxe : 3");
				driver.findElement(By.xpath("//input[contains(@id,'innerTbl:0:drftxassiette')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'innerTbl:0:drftxassiette')]")).sendKeys("3"+Keys.ENTER);
				logger.info("Click on Payment Schedule ");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:tbTableToolbar:btnPaymentScheduleDisAcc::icon')]")).click();
				logger.info("Click on Close Button");
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				// ------------------------------------------------- Adding 2nd Rubriques de rétributions Tab -------------------------------------------------------------------------------------
				logger.info("Scrolling on New icon");
				logger.info("------------------------- Click on New Icon to Add 2nd  Rubriques de rétributions Element -------------------------");
				driver.findElement(By.xpath("//div[contains(@id,'s_mx_aai:tbTableToolbar:new')]")).click();
				logger.info("Select Imputation analytique : Commission de risque");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aae::content')]"))).selectByVisibleText("Commission Apporteur");
				logger.info("Select Acteur : Apporteur");
				driver.findElement(By.xpath("//select/option[contains(text(),'Apporteur')]")).click();
				logger.info("Select Type assiette : Assiette brute financière");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aam::content')]"))).selectByVisibleText("Assiette brute financière");
				logger.info("Select Taxe : T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_mb_aa1::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("select Bases des décomptes (jours)	 as:-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_mb_aa5::content')]"))).selectByVisibleText("360 jours / an");
				logger.info("select Bases des décomptes (jours)	part2 as:-360 jours / an");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_mb_aa6::content')]"))).selectByVisibleText("360 jours / an");
				if (!driver.findElement(By.xpath("//input[contains(@id,'s_mb_abg')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'s_mb_abg')]")).click();
				}
				logger.info("Enter nomber : 50");
				driver.findElement(By.xpath("//input[contains(@id,'s_lk_aaf:innerTbl:0:dr1nombre')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_lk_aaf:innerTbl:0:dr1nombre')]")).sendKeys("50" + Keys.ENTER);
				driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfperiode::content')]")).click();
				logger.info("select Bases des décomptes :-Mois");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfperiode::content')]"))).selectByVisibleText("Jour(s)");
				driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:innerTbl:0:drfperiode::content')]")).click();
				logger.info("Enter Taxe : 3");
				driver.findElement(By.xpath("//input[contains(@id,'innerTbl:0:drftxassiette')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'innerTbl:0:drftxassiette')]")).sendKeys("3" + Keys.ENTER);
				logger.info("Click on Payment Schedule ");
				driver.findElement(By.xpath("//img[contains(@id,'secId:mainBody:vboxlist:s_lk_aah:tbTableToolbar:btnPaymentScheduleDisAcc::icon')]")).click();
				logger.info("Click on Close Button");
				driver.findElement(By.xpath("//button[contains(@id,'s_mo_aao')]")).click();
				logger.info("click on save button");
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bey_aab::icon')]")).click();
				WebElement man2=driver.findElement(By.xpath("//span[contains(@id,'s_m2_aak')]/label"));
				Assert.assertEquals(true,man2.isDisplayed());
				String String7=driver.findElement(By.xpath("//span[contains(@id,'s_m2_aak')]/label")).getText();
				logger.info(String7);
				String[] parts=String7.split(": < ");
				String part1=parts[1];
				logger.info(part1);
				String[] parts2=part1.split(" >");
				part2=parts2[0];
				Contract_Ref=part2;
				logger.info("Contract Number is::" + part2);
				String sqlexp="Select dosnum from Dossier where Dosnom='" + Short_name1 + "'";
				logger.info(sqlexp);
				String col_name="Dosnum";
				String Contr_ref=DBvalidation(sqlexp,col_name,Scenario_Name);
				logger.info("Contract reference from database" + Contr_ref);
				Assert.assertEquals(Contract_Ref,Contr_ref);
				logger.info("Contract Creation is Pass");
				logger.info("********************************************** Contract " + method.getName() + " Pass******************************************");
		}

		@Test(priority=234,enabled=false)
		public void NLSCUAT_3051_Add_Matériels_1(Method method) throws Throwable 
		{
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Contract sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				Client_Frame(driver);
				logger.info("contract No=" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref);
				logger.info("Click on Search");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Click on Contract Reference to Open Contract Detail Page");
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
				logger.info("Clicked on Asset  section ");
				driver.findElement(By.xpath("//a[contains(text(),'Matériels')]")).click();
				logger.info("Clicked on New Icon ");
				driver.findElement(By.xpath("//img[contains(@id,'s_kz_aac:tbTableToolbar:new::icon')]")).click();
				Thread.sleep(40000);
				Utility.getHandleToWindow("Cassiopae Application",driver);
				String actual=driver.getTitle();
				logger.info("\n\n\n" + actual);
				logger.info("--------------------- Navigate to Information Generales Tab of Asset ---------------------");
				logger.info("Select Société as:-1-NATIXIS LEASE");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_cg_aad::content')]"))).selectByVisibleText("1-NATIXIS LEASE");
				String Asset_Name="Materiel_" + Utility.ReportDateTime();
				logger.info("Enter Intitulé as:-" + Asset_Name);
				driver.findElement(By.xpath("//input[contains(@id,'s_cg_aaf::content')]")).sendKeys(Asset_Name);
				logger.info("Select Devise as:-Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_cg_aag::content')]"))).selectByVisibleText("Euro");
				logger.info("Select Secteur de gestion as:-Exploitation");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_cg_aah::content')]"))).selectByVisibleText("Exploitation");
				logger.info("--------------------- Navigate to Liste des éléments Tab ---------------------");
				driver.findElement(By.xpath("//a[contains(@id,'s_bu_aab::disAcr')]")).click();
				try
				{
						driver.findElement(By.xpath("//div[contains(@id,'mainBody:vboxlist:s_bu_aad:innerTbl::db')]//tr")).isDisplayed();
				}
				catch (Exception e)
				{
						logger.info("---------------------------- Click on New Icon  to Add 1st Element ----------------------------");
						driver.findElement(By.xpath("//img[contains(@id,'s_bu_aad:tbTableToolbar:new::icon')]")).click();
				}
				logger.info("Select Activité  as:-Crédit bail mobilier");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aae::content')]"))).selectByVisibleText("Crédit bail mobilier");
				logger.info("Select Produit  as : 01_CBM Groupe BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aaf::content')]"))).selectByVisibleText("01_CBM Groupe BP");
				logger.info("Select Régime fiscal as : Régime général");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_aag::content')]"))).selectByVisibleText("Régime général");
				logger.info("Enter Code Fournisseur as:-0092155");
				driver.findElement(By.xpath("//input[contains(@id,'tf_s_ca_aa4::content')]")).sendKeys("0092155" + Keys.ENTER);
				logger.info("Scrolling on Montant section");
				JavascriptExecutor js=(JavascriptExecutor) driver;
				WebElement element=driver.findElement(By.xpath("//input[contains(@id,'mainBody:vboxlist:tfdFacIdSubview:s_ca_aa3::content')]"));
				js.executeScript("arguments[0].scrollIntoView(true);",element);
				Thread.sleep(3000);
				logger.info("Enter Montant amount  As : 6789.12");
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_abp::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'s_ca_abp::content')]")).sendKeys("6789.12" + Keys.ENTER);
				logger.info("Select Imputation Analytique :Machines et equipement");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abq::content')]"))).selectByVisibleText("Machines et equipement");
				Thread.sleep(3000);
				logger.info("Select Taxe  as:-T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abr::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				Thread.sleep(3000);
				logger.info("Select Catégorie de bien as:Voitures de voyageurs");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abs::content')]"))).selectByVisibleText("Compacteuses/Rouleaux compress.");
				logger.info("Select Devise : Euro");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_ca_abx::content')]"))).selectByVisibleText("Euro");
				logger.info("--------------------- Scroll to  Liste des composants ---------------------");
				element=driver.findElement(By.xpath("//h2[contains(text(),'Liste des composants')]"));
				js.executeScript("arguments[0].scrollIntoView(true);",element);
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//div[contains(@id,'s_b1_aac:tbTableToolbar:new')]")).click();
				logger.info("---------------------------- Scroll to Lois d'amortissement fiscal ----------------------------");
				element=driver.findElement(By.xpath("//h1[contains(text(),'amortissement fiscal')]"));
				js.executeScript("arguments[0].scrollIntoView(true);",element);
				try
				{
						driver.findElement(By.xpath("//select[contains(@id,'s_b3_aaj:innerTbl:0:tlfcode::content')]")).isDisplayed();
				}
				catch (Exception e)
				{
						logger.info("---------------------------- Click on New Icon  to Add 1st Element ----------------------------");
						driver.findElement(By.xpath("//div[contains(@id,'s_b3_aaj:tbTableToolbar:new')]//img")).click();
				}
				logger.info("click on Save icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_ce_aab:s_bey_aab::icon')]")).click();
				driver=Utility.getHandleToWindow("Cassiopae",driver);
				driver.switchTo().defaultContent();
				driver.switchTo().frame(2);
				logger.info("Click on Save Button");
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bey_aab::icon')]")).click();
				WebElement man=driver.findElement(By.xpath("//span[contains(@id,'s_m2_aak')]/label"));
				Assert.assertEquals(true,man.isDisplayed());
				String String7=driver.findElement(By.xpath("//a[contains(@id,'secId:mainBody:vboxlist:s_kz_aac:innerTbl:0:itrnum')]")).getText();
				logger.info(String7);
				Asset_Ref=String7;
				logger.info("Asset_Ref");
				String sqlexp="select * from immotranche where ITRNOM='" + Asset_Name + "'";
				String col_name="ITRNUM";
				String Asset_Ref_DB=Utility.DBvalidation(sqlexp,col_name,Scenario_Name);
				logger.info("Asset reference by DB : " + Asset_Ref);
				Assert.assertEquals(Asset_Ref,Asset_Ref_DB);
				logger.info("********************************************** Contract " + method.getName() + " Pass******************************************");
		}

		@Test(priority=235,enabled=false)
		public void NLSCUAT_3051_Add_Pénalité_fin_anticipée(Method method) throws Throwable 
		{
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Contract sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				Client_Frame(driver);
				logger.info("Enter Contract Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'dosnum::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contacrt");
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				logger.info("Navigate to Rubriques accessoires tab");
				driver.findElement(By.xpath("//a[contains(text(),'Rubriques accessoires')]")).click();
				logger.info("Navigate to Garanties tab");
				driver.findElement(By.xpath("//a[contains(text(),'Garanties')]")).click();
				logger.info("Clicked on Pénalité fin anticipée  tab ");
				driver.findElement(By.xpath("//a[contains(@id,'s_no_aab::disAcr')]")).click();
				try
				{
						driver.findElement(By.xpath("//div[contains(@id,'secId:mainBody:vboxlist:s_no_aag:innerTbl::db')]//table//tr")).isDisplayed();
				}
				catch (Exception e)
				{
						logger.info("Eneter months as 12:-");
						driver.findElement(By.xpath("//input[contains(@id,'s_no_aad::content')]")).clear();
						driver.findElement(By.xpath("//input[contains(@id,'s_no_aad::content')]")).sendKeys("12" + Keys.ENTER);
						logger.info("Enter Date au plus tôt d'appel de l'option : " + Next_1_yr_date);
						driver.findElement(By.xpath("//input[contains(@id,'s_no_aae::content')]")).clear();
						driver.findElement(By.xpath("//input[contains(@id,'s_no_aae::content')]")).sendKeys(Next_1_yr_date + Keys.ENTER);
						logger.info("Click on New Icon");
						driver.findElement(By.xpath("//img[contains(@id,'s_no_aag:tbTableToolbar:new::icon')]")).click();
						logger.info("Enter Date debut : " + Start_date);
						driver.findElement(By.xpath("//input[contains(@id,'s_no_aag:innerTbl:0:dradtbeg::content')]")).clear();
						driver.findElement(By.xpath("//input[contains(@id,'s_no_aag:innerTbl:0:dradtbeg::content')]")).sendKeys(Start_date + Keys.ENTER);
						logger.info("Enter % encours financier :-5");
						driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_no_aag:innerTbl:0:drapctpenaliteachat::content')]")).clear();
						driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_no_aag:innerTbl:0:drapctpenaliteachat::content')]")).sendKeys("5" + Keys.ENTER);
						logger.info("select Type de pénalité : Pourcentage sur encours financier + Forfait");
						new Select(driver.findElement(By.xpath("//select[contains(@id,'s_no_aag:innerTbl:0:dratypepenaliteachat::content')]"))).selectByVisibleText("Pourcentage sur encours financier + Forfait");
				}
				logger.info("Select value as:-Tous");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_no_aag:innerTbl:0:dratargetevt::content')]"))).selectByVisibleText("Tous");
				logger.info("Select Value as:-Loyers restants dûs à dern fact.");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_no_aak::content')]"))).selectByVisibleText("Loyers restants dûs à dern fact.");
				logger.info("click on save button");
				driver.findElement(By.xpath("//img[contains(@id,'s_m4_aad:s_bey_aab::icon')]")).click();
				String EVT=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_no_aag:innerTbl:0:dratargetevt::content')]")).getAttribute("title");
				logger.info("EVT:-" + EVT);
				Assert.assertEquals("Tous",EVT);
		}

		@Test(priority=236,enabled=false)
		public void NLSCUAT_3051_EVD_ENGA_en_phase_Engagement(Method method) throws Throwable 
		{
				logger.info("\n" + "******************************* Contract Event" + method.getName() + " *********************************");
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Contract sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				Client_Frame(driver);
				logger.info("Enter Contract Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'dosnum::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contacrt");
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				driver.switchTo().defaultContent();
				logger.info("Click on Event EVD_ENGA");
				WebElement man=driver.findElement(By.xpath("//span[contains(@title,'EVD_ENGA')]"));
				Assert.assertEquals(true,man.isDisplayed());
				driver.findElement(By.xpath("//span[contains(@title,'EVD_ENGA')]")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Click on Terminer button");
				driver.findElement(By.xpath(Terminer)).click();
				logger.info("Click on Valider button");
				driver.findElement(By.xpath("//button[contains(text(),'Valider')]")).click();
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				String Phase=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa4::content')]")).getAttribute("title");
				logger.info(Phase);
				String Step=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa6::content')]")).getAttribute("title");
				logger.info(Step);
				Assert.assertEquals(Phase,"Engagement");
				Assert.assertEquals(Step,"Engagé");
				logger.info("\n" + "****************************** " + method.getName() + " Pass ******************************");
		}

		@Test(priority=237,enabled=false)
		public void NLSCUAT_3051_NL_Contract_Event_Mise_à_jour_du_statut_de_la_RUM(Method method) throws Throwable   
		{
				logger.info("\n" + "******************************* Contract Event" + method.getName() + " *********************************");
				driver.switchTo().defaultContent();
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Contract sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				Client_Frame(driver);
				logger.info("Enter Contract Number:" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'dosnum::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				logger.info("Click on Search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Open Contract");
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				driver.switchTo().defaultContent();
				try
				{
						boolean enga=driver.findElement(By.xpath("//span[contains(@title,'EVD_MDTCHG')]")).isDisplayed();
						logger.info("Click on Event :-EVD_MDTCHG");
						driver.findElement(By.xpath("//span[contains(@title,'EVD_MDTCHG')]")).click();
						Client_Frame(driver);
						Child_Frame(driver);
						logger.info("click on 'DonnÃ©es' first element check box ");
						int rowcount=Integer.parseInt(driver.findElement(By.xpath("//div[contains(@id,'1pc9:innerTbl::db')]//table")).getAttribute("_rowcount"));
						logger.info("row count:-" + rowcount);
						for (int i=0; i < rowcount; i++)
						{
								if (!driver.findElement(By.xpath("//input[contains(@id,':innerTbl:" + i + ":flagselected::content')]")).isSelected())
								{
										driver.findElement(By.xpath("//input[contains(@id,':innerTbl:" + i + ":flagselected::content')]")).click();
								}
								logger.info("select Statut as :-Valide");
								WebElement text=driver.findElement(By.xpath("//select/option[contains(text(),'Valide')]"));
								text.click();
						}
						logger.info("Click on Suivant");
						driver.findElement(By.xpath(Suivant)).click();
						logger.info("Click on Terminer");
						driver.findElement(By.xpath(Terminer)).click();
						logger.info("Click on Confirm button");
						driver.findElement(By.xpath("//button[contains(text(),'Valider')]")).click();
						driver.switchTo().defaultContent();
						Client_Frame(driver);
						String Phase=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa4::content')]")).getAttribute("title");
						logger.info("New Phase  : " + Phase);
						String Step=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa6::content')]")).getAttribute("title");
						logger.info("New Step  :  " + Step);
						driver.switchTo().defaultContent();
						driver.findElement(By.linkText("More >>")).click();
				}
				catch (Exception e)
				{
						WebElement enga1=driver.findElement(By.xpath("//span[contains(@title,'EVD_RUMNEW')]"));
						Assert.assertEquals(true,enga1.isDisplayed());
						logger.info("Click on Event :-EVD_RUMNEW");
						driver.findElement(By.xpath("//span[contains(@title,'EVD_RUMNEW')]")).click();
						Client_Frame(driver);
						Child_Frame(driver);
						driver.findElement(By.xpath("//img[contains(@id,'vboxlist:bSr_s_km_aaf::icon')]")).click();
						driver.findElement(By.xpath("//img[contains(@id,'srchDlg:s_be8_aaj:tbTableToolbar:find::icon')]")).click();
						logger.info("Click on Valider");
						driver.findElement(By.xpath("//button[contains(text(),'Valider')]")).click();
						logger.info("Click on Suivant");
						driver.findElement(By.xpath(Suivant)).click();
						logger.info("Click on Terminer");
						driver.findElement(By.xpath(Terminer)).click();
						logger.info("Click on Confirm button");
						driver.findElement(By.xpath("//button[contains(text(),'Valider')]")).click();
						driver.switchTo().defaultContent();
						logger.info("Click on More >>");
						driver.findElement(By.linkText("More >>")).click();
						try
						{
								logger.info("Click on Event :-EVD_MDTCHG");
								driver.findElement(By.xpath("//span[contains(@title,'EVD_MDTCHG')]")).click();
								Client_Frame(driver);
								Child_Frame(driver);
								logger.info("click on 'DonnÃ©es' first element check box ");
								int rowcount=Integer.parseInt(driver.findElement(By.xpath("//div[contains(@id,'1pc9:innerTbl::db')]//table")).getAttribute("_rowcount"));
								logger.info("row count:-" + rowcount);
								for (int i=0; i < rowcount; i++)
								{
										if (!driver.findElement(By.xpath("//input[contains(@id,':innerTbl:" + i + ":flagselected::content')]")).isSelected())
										{
												driver.findElement(By.xpath("//input[contains(@id,':innerTbl:" + i + ":flagselected::content')]")).click();
										}
										logger.info("select Statut as :-Valide");
										WebElement text=driver.findElement(By.xpath("//select/option[contains(text(),'Valide')]"));
										text.click();
								}
								logger.info("Click on Suivant");
								driver.findElement(By.xpath(Suivant)).click();
								logger.info("Click on Terminer");
								driver.findElement(By.xpath(Terminer)).click();
								logger.info("Click on Fermer button");
								driver.findElement(By.xpath(Fermer)).click();
						}
						catch (Exception ew)
						{
						}
				}
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				String Phase=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa4::content')]")).getAttribute("title");
				logger.info("New Phase  : " + Phase);
				String Step=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa6::content')]")).getAttribute("title");
				logger.info("New Step  :  " + Step);
				driver.switchTo().defaultContent();
				driver.findElement(By.linkText("More >>")).click();
				WebElement rel=driver.findElement(By.xpath("//span[contains(@title,'EVD_REAL')]"));
				Assert.assertEquals("Engagement",Phase);
				Assert.assertEquals("Engagé",Step);
				Assert.assertEquals(true,rel.isDisplayed());
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Run Event- " + method.getName() + " Pass >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		}

		@Test(priority=238,enabled=false)
		public void NLSCUAT_3051_NL_Contract_Event_Passage_en_phase_Réalisation(Method method) throws Throwable  
		{
				driver.switchTo().defaultContent();
				logger.info("\n" + "******************************* Contract Event" + method.getName() + " *********************************");
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Contract sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				Client_Frame(driver);
				logger.info("Enter  Contract Ref. No: " + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'dosnum::content')]")).sendKeys(Contract_Ref);
				logger.info("Click on Search Icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Click on Contract Ref to Open Contract Detail Page");
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				driver.switchTo().defaultContent();
				logger.info("Click on More >>");
				driver.findElement(By.linkText("More >>")).click();
				WebElement man=driver.findElement(By.xpath("//span[contains(@title,'EVD_REAL')]"));
				Assert.assertEquals(true,man.isDisplayed());
				logger.info("Click on event EVD_REAL");
				driver.findElement(By.xpath("//span[contains(@title,'EVD_REAL')]")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Click on Terminer button");
				driver.findElement(By.xpath(Terminer)).click();
				logger.info("Click on Fermer button");
				driver.findElement(By.xpath(Fermer)).click();
				Client_Frame(driver);
				// driver.switchTo().defaultContent();
				String Phase=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa4::content')]")).getAttribute("title");
				logger.info("New Phase : " + Phase);
				String Step=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa6::content')]")).getAttribute("title");
				logger.info("New Step : " + Step);
				Assert.assertEquals("En cours",Phase);
				Assert.assertEquals("En cours",Step);
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Run Event- " + method.getName() + " Pass >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		}

		@Test(priority=239,enabled=false)
		public void NLSCUAT_3051_New_Expense_Creation_1(Method method) throws Throwable  
		{
				logger.info("Click on Expense Menu");
				driver.findElement(By.xpath(Expense_M1)).click();
				logger.info("Click on Expense Sub-Menu");
				driver.findElement(By.xpath(Expense_DropDown1)).click();
				Client_Frame(driver);
				logger.info("Click on New Icon");
				driver.findElement(By.xpath("//img[contains( @src,'new_ena.png')]")).click();
				logger.info("Select Company/Société as:-1-NATIXIS LEASE");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:tabIdentificationPnlIdentification1:s_g7_aae::content')]"))).selectByVisibleText("1-NATIXIS LEASE");
				logger.info("Select Activité as:-Crédit bail mobilier");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:tabIdentificationPnlIdentification1:s_g7_aao::content')]"))).selectByVisibleText("Crédit bail mobilier");
				logger.info("Select Produit as :-01_CBM Groupe BP");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:tabIdentificationPnlIdentification1:s_g7_aaf::content')]"))).selectByVisibleText("01_CBM Groupe BP");
				logger.info("Enter Actor Reference / Code acteur :-0092155");
				driver.findElement(By.xpath("//input[contains(@id,'aa6::content')]")).sendKeys("0092155" + Keys.ENTER);
				logger.info("Enter  Reference / Référence externe :-TEST TNR");
				String Référence="Test_" + Contract_Ref;
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_g7_abb::content')]")).sendKeys(Référence+ Keys.ENTER);
				logger.info("Click on New Icon under Liste des taxes to Add 1st Item");
				driver.findElement(By.xpath("//div[contains(@id,'secId:mainBody:vboxlist:s_g7_abn:tbTableToolbar:new')]")).click();
				logger.info("Select Type des taxes :-T.V.A. au taux normal métropole");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'abn:innerTbl:0:taxcode::content')]"))).selectByVisibleText("T.V.A. au taux normal métropole");
				logger.info("Enter Amount/Montant:-13578.24");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_g7_abn:innerTbl:0:dtamtht::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_g7_abn:innerTbl:0:dtamtht::content')]")).sendKeys("13578.24" + Keys.ENTER);
				logger.info("---------------------------- Navigate to Lignes Tab ----------------------------");
				driver.findElement(By.xpath("//a[contains(text(),'Lignes')]")).click();
				try
				{
						driver.findElement(By.xpath("//span[contains(@id,'aac:innerTbl:0:dliordre::content')]")).isDisplayed();
				}
				catch (Exception e)
				{
						logger.info("Click on New Icon");
						driver.findElement(By.xpath("//div[contains(@id,'s_b3_aaj:tbTableToolbar:new')]//img")).click();
				}
				logger.info("Enter Contract Number : " + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'aak::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				//	driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_hd_aa0::content')]")).clear();
				//	driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_hd_aa0::content')]")).sendKeys("13578.24" + Keys.ENTER);
				logger.info("Click on Identification Tab.");
				driver.findElement(By.xpath("//a[contains(text(),'Identification')]")).click();
				logger.info("Select Phase : Validée");
				new Select(driver.findElement(By.xpath("//select[contains(@id,'s_g7_aad::content')]"))).selectByVisibleText("Validée");
				logger.info("Enter Amount/Montant:-13578.24");
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_g7_abn:innerTbl:0:dtamtht::content')]")).clear();
				driver.findElement(By.xpath("//input[contains(@id,'secId:mainBody:vboxlist:s_g7_abn:innerTbl:0:dtamtht::content')]")).sendKeys("13578.24" + Keys.TAB);
				logger.info("Click on Save icon");
				driver.findElement(By.id("secId:s_g5_aab:s_bey_aab::icon")).click();
				WebElement man=driver.findElement(By.xpath("//td[@id='secId:s_g4_aai']"));
				Assert.assertEquals(true,man.isDisplayed());
				String String2=driver.findElement(By.xpath("//td[@id='secId:s_g4_aai']")).getText();
				logger.info(String2);
				String[] parts=String2.split(" > Créée le");
				String part1=parts[0];
				logger.info("part1 :" + part1);
				String[] parts2=part1.split("< ");
				part2=parts2[1];
				logger.info("part2 :" + part2);
				Exp_ref=part2;
				String sqlexp="select * from DEPENSE where DEPREFEXTERNE='" + Référence + "'";
				String col_name="DEPNUM";
				String Expense_ref_DB=DBvalidation(sqlexp,col_name,Scenario_Name);
				Assert.assertEquals(Exp_ref,Expense_ref_DB);
				logger.info("Expense creation pass");
				logger.info("Expense Created Successfully with Reference : " + part2);
				driver.switchTo().defaultContent();
				logger.info("Click on Event Passage en bon à payer technique d'une dépense");
				WebElement man1=driver.findElement(By.linkText("Passage en bon à payer technique d'une dépense"));
				Assert.assertEquals(true,man1.isDisplayed());
				logger.info("******************** Expense Creation : Pass ********************");
				logger.info("Click on Event :Passage en bon à payer technique d'une dépense/Confirm expense");
				driver.findElement(By.linkText("Passage en bon à payer technique d'une dépense")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Click on Terminer button");
				driver.findElement(By.xpath(Terminer)).click();
				logger.info("Click on Fermer button");
				driver.findElement(By.xpath(Fermer)).click();
				driver.switchTo().defaultContent();
				WebElement man2=driver.findElement(By.linkText("Blocage du règlement des dépenses"));
				Assert.assertEquals(true,man2.isDisplayed());
				WebElement man3=driver.findElement(By.linkText("Modification de l'échéancier d'une dépense"));
				Assert.assertEquals(true,man3.isDisplayed());
		}

		@Test(priority=242,enabled=true)
		public void Expense_Event_Passage_en_bon_à_payer_trésorerie_de_dépenses(Method method) throws Throwable 
		{
				Default_Frame(driver);
				logger.info("Click on Depenses Menu");
				driver.findElement(By.xpath(Expense_M1)).click();
				logger.info("Click on Depenses Sub-Menu");
				driver.findElement(By.xpath(Expense_DropDown1)).click();
				Client_Frame(driver);
				logger.info("Enter Dossier ref to search expense");
				driver.findElement(By.xpath("//input[contains( @id,'secId:tf_s_gm_aa7::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				logger.info("Click on expense search");
				driver.findElement(By.xpath("//img[contains(@id,'secId:s_gm_aao:tbTableToolbar:find::icon')]")).click();
				Assert.assertEquals(true,driver.findElement(By.id("secId:s_gm_aao:innerTbl:0:numdepense")).isDisplayed());
				String Expense=driver.findElement(By.id("secId:s_gm_aao:innerTbl:0:numdepense")).getText();
				logger.info("Open Expense:" + Expense);
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_gm_aao:innerTbl:0:numdepense')]")).click();
				Default_Frame(driver);
				WebElement man4=driver.findElement(By.linkText("Passage en bon à payer trésorerie de dépenses"));
				Assert.assertEquals(true,man4.isDisplayed());
				logger.info("Click on Event :Passage en bon à payer trésorerie de dépenses / Authorize expense payment");
				driver.findElement(By.linkText("Passage en bon à payer trésorerie de dépenses")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				logger.info("Click on Terminer");
				driver.findElement(By.xpath(Terminer)).click();
				logger.info("Click on Fermer");
				driver.findElement(By.xpath(Fermer)).click();
				Client_Frame(driver);
				WebElement man5=driver.findElement(By.xpath("//select[contains(@title,'Bon à payer trésorerie')]"));
				Assert.assertEquals(true,man5.isDisplayed());
		}

		@Test(priority=272,enabled=true)
		public void NLSCUAT_3051_Contract_Event_Mise_en_service_EVD_MEL(Method method) throws Throwable 
		{
				logger.info("\n" + "******************************  Run Event-Event_" + method.getName() + " from hare ******************************");
				logger.info("Click on Contract Menu");
				driver.findElement(By.xpath(Contract_M1)).click();
				logger.info("Click on Contract sub Menu");
				driver.findElement(By.xpath(Contract_DropDown1)).click();
				Client_Frame(driver);
				logger.info("Enter Contract Number:-" + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:s_n5_aao:innerTbl:dosnum::content')]")).sendKeys(Contract_Ref + Keys.ENTER);
				driver.findElement(By.xpath("//img[contains(@id,'s_n5_aao:tbTableToolbar:find::icon')]")).click();
				driver.findElement(By.xpath("//a[contains(@id,'secId:s_n5_aao:innerTbl:0:dosnum')]")).click();
				driver.switchTo().defaultContent();
				logger.info("Click on Mise en service Evnet");
				driver.findElement(By.xpath("//a[contains(@title,'EVD_MEL')]")).click();
				Client_Frame(driver);
				Child_Frame(driver);
				if (!driver.findElement(By.xpath("//input[contains(@id,'13pc9')]")).isSelected())
				{
						driver.findElement(By.xpath("//input[contains(@id,'13pc9')]")).click();
				}
				driver.findElement(By.xpath(Suivant)).click();// 2
				driver.findElement(By.xpath(Suivant)).click();// 3
				driver.findElement(By.xpath(Suivant)).click();// 4
				driver.findElement(By.xpath(Suivant)).click();
				WebElement finish_button=driver.findElement(By.xpath(Terminer));
				finish_button.click();
				try
				{
						driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).isDisplayed();
				}
				catch (Exception e)
				{
						logger.info("Click on Valider button");
						driver.findElement(By.xpath("//button[contains(text(),'Valider')]")).click();
				}
				driver.switchTo().defaultContent();
				Client_Frame(driver);
				String Phase=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa4::content')]")).getAttribute("title");
				logger.info(Phase);
				String Step=driver.findElement(By.xpath("//select[contains(@id,'secId:mainBody:vboxlist:s_m9_aa6::content')]")).getAttribute("title");
				logger.info(Step);
				Assert.assertEquals("Exploitation",Phase);
				Assert.assertEquals("En service",Step);
				Default_Frame(driver);
				logger.info("Click on Facturation Menu");
				driver.findElement(By.xpath("//a[contains(text(),'Facturation')]")).click();
				logger.info("Click on Facturation in Sub-Menu");
				driver.findElement(By.xpath("//tr[contains(@id,'modulesMenu:s_tu_ab8')]/td[2]")).click();
				logger.info("Switch to Client Frame");
				Client_Frame(driver);
				logger.info("Enter Dossier Number : " + Contract_Ref);
				driver.findElement(By.xpath("//input[contains(@id,'secId:tf_s_q2_aa9')]")).sendKeys(Contract_Ref + Keys.ENTER);
				logger.info("Click on search icon");
				driver.findElement(By.xpath("//img[contains(@id,'s_q2_aao:tbTableToolbar:find::icon')]")).click();
				logger.info("Getting Facturation number ");
				String Facturation_EVD_MEL_ref=driver.findElement(By.xpath("//a[contains(@id,'secId:frmFactLstTblSV:s_q2_aao:innerTbl:0:facnum')]")).getText();
				logger.info("Facturation number " + Facturation_EVD_MEL_ref);
				Assert.assertNotNull(Facturation_EVD_MEL_ref,null);
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Run Event- " + method.getName() + " Pass >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		}

		@AfterMethod
		public void Method_Ended(Method method,ITestResult testResult) throws Throwable 
		{
				long time=testResult.getEndMillis() - testResult.getStartMillis();
				time=(time / 60000);
				try
				{
						NLSCUAT_3051_Logout_From_Application();
						driver.unregister(eventListener);
						driver.quit();
						driver1=new Augmenter().augment(Utility.DriverInitializer(browser1));
				}
				catch (Exception e1)
				{
						driver.unregister(eventListener);
						// driver.quit();
						driver1=new Augmenter().augment(Utility.DriverInitializer(browser1));
						logger.info("NLSCUAT_3051_Logout_From_Application :-" + e1.getMessage());
				}
				try
				{
						String Sceenshot_Folder_Path=Scrennshot_path + Today() + "\\" + className + "\\" + method.getName();
						String New_Video_Location=Scrennshot_path + Today() + "\\" + className + "\\" + method.getName() + "_Video" + ".mp4";
						String Issue_Video_path=Utility.Video_Creation(New_Video_Location,Sceenshot_Folder_Path);
						logger.info(Issue_Video_path);
				}
				catch (Exception e1)
				{
						e1.printStackTrace();
				}
				try
				{
						NLSCUAT_3051_Logout_From_Application();
				}
				catch (Exception e1)
				{
						System.out.println("NLSCUAT_3310_Logout_From_Application :-");
						e1.printStackTrace();
				}
				if (testResult.isSuccess() == false)
				{
						try
						{
								logger.info("****" + method.getName() + " is Failed");
								logger.info("****Exception on " + method.getName() + " - " + testResult.getThrowable().getMessage().split("Command")[0]);
								Putdata(Excel_Status_Column,Excel_Row_No,"Fail",Excel_SheetName);
								Putdata(Excel_Error_Column,Excel_Row_No,testResult.getThrowable().getMessage().split("Command")[0],Excel_SheetName);
								// Issue = NewIssueCreation( Project, summary, username1, password1, description, assignee, issuetype, components, Module, Feature );
								// AttachedDocumetToJira( Issue, Issue_Reproduce_steps_logs, Issue_Video_path, username1, password1);
								// Putdata( Issue_Column, Excel_Row_No, Issue, Excel_SheetName );
								// logger.info( "Attached document for issue on " + method.getName() + " - " + Issue + " Is successfull" );
								if (method.getName().equals("NLSCUAT_3051_Contract_Creation"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,Contract_Ref,Excel_SheetName);
										for (int i=1; i <= 9; i++)
										{
												Putdata(Excel_Status_Column,Excel_Row_No + i,"Fail",Excel_SheetName);
										}
								}
								if (method.getName().equals("NLSCUAT_3051_Add_Matériels_1"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,Asset_Ref,Excel_SheetName);
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
								if (method.getName().equals("NLSCUAT_3051_Contract_Creation"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,Contract_Ref,Excel_SheetName);
										for (int i=1; i <= 9; i++)
										{
												Putdata(Excel_Status_Column,Excel_Row_No + i,"Pass",Excel_SheetName);
										}
								}
								if (method.getName().equals("NLSCUAT_3051_Add_Matériels_1"))
								{
										Utility.Putdata(Excel_Testdata_Column,Excel_Row_No,Asset_Ref,Excel_SheetName);
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