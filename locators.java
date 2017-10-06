
package Scenarios_Reg;

public interface locators
{

		// ***************************** Evidences Path ************************************
		static String Text_path=System.getProperty("user.dir") + "\\Execution_Results\\Fail_Documents\\";	// Fail_Reports
		static String Scrennshot_path=System.getProperty("user.dir") + "\\Execution_Results\\Screenshots\\";
		// ***************************** Test Data Excel Path *******************************
		static String excelpath=System.getProperty("user.dir") + "\\src\\Scenarios_Reg\\Regression_Test_Result.xls";
		// ***************************** Web Drivers Path *******************************
		static String Local_Driver_Chrome=System.getProperty("user.dir") + "\\lib\\chromedriver.exe";
		static String Local_Driver_IE=System.getProperty("user.dir") + "\\lib\\IEDriverServer.exe";
		// ***************************** Global Wait ****************************************
		static int t=0;
		// ***************************** Environments Path *******************************
		String NATbaseUrl="http://172.29.248.126:9790/CassiopaeTRNAT7/";
		//String NATbaseUrl="http://192.168.27.53:8380/CassiopaeBackTomcat";
		// String NATbaseUrl="https://back.cassiopae.com/CassiopaeTRNAT7/";
		// ""https://back.cassiopae.com/CassiopaeTRNAT7/";
		String iphost="172.29.248.112";// 192.168.24.253
		String dbsid="DBNAT11G";// DB11G1
		String username="TRNAT7";
		String password="tres5prg";
		String Contract_M1="//a[contains(text(),'Dossiers')]";
		String Contract_DropDown1="//tr[contains(@id,'modulesMenu:s_tu_abn')]/td[2]";
		String Expense_M1="//a[contains(text(),'Dépenses')]";
		String Expense_DropDown1="//*[@id=\"modulesMenu:s_tu_ace\"]/td[2]";
		static int Excel_Status_Column=3;
		static int Excel_Testdata_Column=4;
		static int Excel_Error_Column=5;
		static int Issue_Column=6;
		static String Excel_SheetName="NL_Contract_Status";
		String login_button1="//button[contains(@id,'s_s0_aas')]";
		String txtbox_username1="//input[contains(@id,'s_s0_aan::content')]";
		String txtbox_pwd1="//input[contains(@id,'s_s0_aap::content')]";
		String Validate="//button[contains(text(),'Validate')]";
		String Suivant="//button[contains(text(),'Suivant')]";
		String Terminer="//button[contains(text(),'Terminer')]";
		String Fermer="//button[contains(text(),'Fermer')]";
		String Retour="//button[contains(text(),'Retour')]";
		String Confirm="//button[contains(text(),'Confirm')]";
		String Valider="//button[contains(text(),'Valider')]";
		String Next="//button[contains(text(),'Next')]";
		String Finish="//button[contains(text(),'Finish')]";
		String Close="//button[contains(text(),'Close')]";
		String Rollback="//button[contains(text(),'Rollback')]";
		
		String Attachment_Date=Utility.Dates("Attachment_Date");  // start date of contract=sysdate
		String NL_Statement_Process=Utility.Dates("NL_Statement_Process");// 30/10/2016 sysdate + 6 months
		String Next_1_yr_date=Utility.Dates("Next_1_yr_date");
		String Start_date_facture=Utility.Dates("Start_date_facture"); // Attachment_Date -15 days
		String Start_date=Utility.Dates("Start_date");
		String rec_end_date=Utility.Dates("rec_end_date"); // sysdate + 1 month
		String Startdate2=Utility.Dates("Startdate2");
		String Attachment_Date_NL_3048=Utility.Dates("Attachment_Date_NL_3048"); // sysdate - 1 month
		String montsStartdate=Utility.Dates("montsStartdate");
		String casflodate=Utility.Dates("casflodate");// One week later date for 3782
		String juridate=Utility.Dates("juridate"); // for 3782
		String renegoDate=Utility.Dates("renegoDate");; // next day date
		String Adj_Date_3048=Utility.Dates("NLSCUAT_3782_EVD_SUB");
		String statement_end=Utility.Dates("statement_end");// sys+5 months
		String auto_debit=Utility.Dates("auto_debit"); // sys+3 months
		String sale_date=Utility.Dates("sale_date");// sys+5 months
		String statement2_start=Utility.Dates("statement2_start");// sys+6 months
		String statement2_end=Utility.Dates("statement2_end");// sys+7 months
}