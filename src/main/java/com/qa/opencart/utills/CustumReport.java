package com.qa.opencart.utills;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;


public class CustumReport 
{
	public CustumReport(String LibraryPath)
	{
	 
	 getConfigData(LibraryPath);
		
	}
	public static void main(String[] args) throws Exception {
		initializeHtmlReport();
	}
	
	private String testCaseName="TestResults";
	private String resultFilePath;
	public static String WorkingDir;
	private Integer currStepNo=0;
	private String testStepName="";
	public static String currentDir = System.getProperty("user.dir");
	public enum StepStatus{
		Pass ("Pass"),
		Fail ("Fail"),
		Warning ("Warning");
	    private final String name;       

	    StepStatus(String stepStatus) {
	        name = stepStatus;
	    }
	}
	
	private class ModuleSummary
	{
		String ModuleName="";
		Integer TotalTCs=0;
		Integer TCPassed=0;
		Integer TCKnownBug=0;
		Integer TCFailed=0;
		Integer TCWarned=0;
		String StartTime="";
		String EndTime="";
		long TotalExecutionInSeconds=0;
		public void init()
		{
			String ModuleName="";
			Integer TotalTCs=0;
			Integer TCPassed=0;
			Integer TCKnownBug=0;
			Integer TCFailed=0;
			String StartTime="";
			String EndTime="";
			long TotalExecutionInSeconds=0;		
		}
	}
	
	private class TestCaseSummary{
    	String ModuleName="";
    	String TestCaseName="";
    	String ActionName="";
    	String Status="";
    	Integer StepsPassed=0;
    	Integer StepsKnown=0;
    	Integer StepsKnownBug=0;
    	Integer StepsFailed=0;
    	Integer StepsWarned=0;
    	String StartTime="";
    	String EndTime="";
    	public void init()
    	{
        	String ModuleName="";
        	String TestCaseName="";
        	String ActionName="";
        	String Status="";
        	Integer StepsPassed=0;
        	Integer StepsKnown=0;
        	Integer StepsKnownBug=0;
        	Integer StepsFailed=0;
        	Integer StepsWarned=0;
        	String StartTime="";
        	String EndTime="";  		
    	}
    }
	
	private class TestCaseDetails
    {
    	String ModuleName="";
    	String TestCaseName="";
    	Integer StepNo=0;
    	String Status="";
    	String StepName="";
    	String Description="";
    	String StepStartTime="";
    	String StepEndTime="";
    	String ScreenShot="";
    	public void init()
    	{
        	String ModuleName="";
        	String TestCaseName="";
        	Integer StepNo=0;
        	String Status="";
        	String StepName="";
        	String Description="";
        	String StepStartTime="";
        	String StepEndTime="";
    	}
    }
	static TreeMap<String,ModuleSummary> oModuleSummaryMap = new TreeMap<String,  ModuleSummary>();
    static TreeMap<String, TestCaseSummary> oTestCasesSummaryMap = new TreeMap<String,  TestCaseSummary>();
    static TreeMap<String, TestCaseDetails> oTestCaseDetailsMap = new TreeMap<String,  TestCaseDetails>();
	public static String resultFileFolder;
	private static String htmlFileName="TestResults.html";
	private static String htmlReportSuiteName="OFS Automation";
	public static void initializeHtmlReport()throws Exception//ByVal strTxtFilePath, ByVal strHtmlFilePath
	{//String strTxtFilePath, String strHtmlFilePath
		
		try
		{
			resultFileFolder="C:\\Users\\bakorapa\\Desktop\\ConsolidatedReport\\";
		
			//Generates Required recordsets using TreeMaps
			generateReportRecordSets();
			
			//Deleting existing html and creating new file
			File objHtmlFile = new File(resultFileFolder + htmlFileName);			
			if(objHtmlFile.exists())
			{
				objHtmlFile.delete();
				objHtmlFile.createNewFile();
			}
				
			//Start Writing content to file
			FileWriter currHTMLFileWriter = new FileWriter(objHtmlFile.getAbsoluteFile(),true);
			BufferedWriter htmlbufferWrite = new BufferedWriter(currHTMLFileWriter);
			
			//Generate CSS
			generateCSS(htmlbufferWrite);
			
			//Generate Html Header
			generateHtmlHeader(htmlbufferWrite);
			

		    //Build Record set reading from Txt Log file
		    //Call generateReportRecordSets(strTxtFilePath, rsModuleSummary, rsTestCaseSummary, rsTestCaseDetails)

		    //Generate the Module Summay
		    generateReportModuleSummary(htmlbufferWrite);

		  //Generate Pie Chart for Module Summay
//		    generatePieChartModuleSummary(htmlbufferWrite);
		    
		    //Generate the TestCase  Summary
		    generateReportTestCaseSummary(htmlbufferWrite);

		    //Generate the TestCase Details
		    generateReportTestCaseDetails(htmlbufferWrite);

		    //Generate HTML Footer
		    generateReportHTMLFooter(htmlbufferWrite);
		    
			
			htmlbufferWrite.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally
		{
			
		}
	}
	private static void generateCSS(BufferedWriter htmlbufferWrite) throws Exception
	{
//		//htmlReportSuiteName
//		writeLineToFile(htmlbufferWrite, "<html>\n");
//	    writeLineToFile(htmlbufferWrite, "<title>" + htmlReportSuiteName + " Automation Execution Summary Report </title>\n");
//	    writeLineToFile(htmlbufferWrite, "<head>\n");
//	    writeLineToFile(htmlbufferWrite, "<style>.MainHead { font-family: Arial; font-size: 12pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow; color: white; background:#990000;}</style>");
//	    
//	    writeLineToFile(htmlbufferWrite, "<style> .SubHead { font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow; color: #black; background:white;}</style>\n");
//	    writeLineToFile(htmlbufferWrite, "<style> .ColumnHead {font-family: Arial; font-size: 10pt ; text-align: center; border-bottom: solid lightyellow 1.0pt; border-top: solid black 1.5pt; color: black; background:#D6D6C2;}</style>");
//	    writeLineToFile(htmlbufferWrite, "<style> .FailStatus { font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow; color: Red; background:#FFFFFF;}</style>");
//	    writeLineToFile(htmlbufferWrite, "<style> .PassStatus { font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: green; background:#FFFFFF;} </style>");
//	    writeLineToFile(htmlbufferWrite, "<style> .KnownBugStatus { font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: #DF7401; background:#FFFFFF;} </style>");
//	    writeLineToFile(htmlbufferWrite, "<style> .GeneralHead { font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;  color: black; background:#FFFFFF;} </style>");
//	    writeLineToFile(htmlbufferWrite, "<style> .RequirementsHead { font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: white; background:#CC3300;} </style>");
//	    writeLineToFile(htmlbufferWrite, "<style> .TestSummaryHead { font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow; color: white; background:#CC6600;} </style>");
//	    writeLineToFile(htmlbufferWrite, "<style> .TestDetailsHead {font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: white; background:#FF9900;} </style>");
//	    writeLineToFile(htmlbufferWrite, "<style> .WarnedStatus { font-family: Arial; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: #7030A0; background:#FFFFFF;} </style>");
//	    
//	    writeLineToFile(htmlbufferWrite, "</head>");
//	    writeLineToFile(htmlbufferWrite, "<body>");
//	    
	  //htmlReportSuiteName
		writeLineToFile(htmlbufferWrite, "<html>\n");
	    writeLineToFile(htmlbufferWrite, "<title>" + htmlReportSuiteName + " Regression Testing Report </title>\n");
	    writeLineToFile(htmlbufferWrite, "<head>\n");
	    writeLineToFile(htmlbufferWrite, "<style>.MainHead { font-family: Calibri; font-size: 15pt ; border-bottom: thin groove lightblue; border-top: thin groove lightyellow; color: white; background:#FF0000;}</style>");
	    
	    writeLineToFile(htmlbufferWrite, "<style> .SubHead { font-family: Calibri; font-size: 12pt ; border-bottom: thin groove lightblue; border-top: thin groove lightyellow; color: green; background:white;}</style>\n");
	    writeLineToFile(htmlbufferWrite, "<style> .ColumnHead {font-family: Calibri; font-size: 10pt ; text-align: center; border-bottom: solid lightyellow 1.0pt; border-top: solid black 1.5pt; color: black; background:#b0c4de;}</style>");
	    writeLineToFile(htmlbufferWrite, "<style> .FailStatus { font-family: Calibri; font-size: 10pt ;font-weight: bold; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow; color: Red; background:#FFFFFF;}</style>");
	    writeLineToFile(htmlbufferWrite, "<style> .PassStatus { font-family: Calibri; font-size: 10pt ; font-weight: bold;border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: green; background:#FFFFFF;} </style>");
	    writeLineToFile(htmlbufferWrite, "<style> .KnownBugStatus { font-family: Calibri; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: #DF7401; background:#FFFFFF;} </style>");
	    writeLineToFile(htmlbufferWrite, "<style> .GeneralHead { font-family: Calibri; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;  color: black; background:#FFFFFF;} </style>");
	    writeLineToFile(htmlbufferWrite, "<style> .RequirementsHead { font-family: Calibri; font-size: 12pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: black; background:#b0c4de;} </style>");
	    writeLineToFile(htmlbufferWrite, "<style> .TestSummaryHead { font-family: Calibri; font-size: 10pt ; border-bottom: thin groove lightblue; border-top: thin groove lightblue; color: black; background:#BC8F8F;} </style>");//
	    writeLineToFile(htmlbufferWrite, "<style> .TestDetailsHead {font-family: Calibri; font-size: 10pt ; border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: black; background:#87CEEB;} </style>");
	    writeLineToFile(htmlbufferWrite, "<style> .WarnedStatus { font-family: Calibri; font-size: 10pt ; font-weight: bold;border-bottom: thin groove lightyellow; border-top: thin groove lightyellow;color: orange; background:#FFFFFF;} </style>");
	    
	    writeLineToFile(htmlbufferWrite, "</head>");
	    writeLineToFile(htmlbufferWrite, "<body>");	
	} 
	
	private static void writeLineToFile(BufferedWriter filebufferWrite, String content) throws Exception
	{
		filebufferWrite.write(content);
		filebufferWrite.newLine();
	}

	private static void generateHtmlHeader(BufferedWriter htmlbufferWrite) throws Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	    Date tdate = new Date();
		
	    // Generate Table  for  Automation Batch Execution Summary
		writeLineToFile(htmlbufferWrite, "<table border='0' cellpadding='5' cellspacing='0' width='100%' >");
	    writeLineToFile(htmlbufferWrite, "<tr>");
	    writeLineToFile(htmlbufferWrite, "<td align=left Class=MainHead><b>" +
	                         htmlReportSuiteName + " - Regression Testing Report" + "</b></td>");
	    
	    writeLineToFile(htmlbufferWrite, "<td align=right Class=MainHead><b>ORACLE INDIA PVT LTD</b></td>");
	    writeLineToFile(htmlbufferWrite, "</tr>");
	    writeLineToFile(htmlbufferWrite, "</table>");

	    //Generate Table  for Report Generated On .*
	    writeLineToFile(htmlbufferWrite, "<table border='0' cellpadding='3' cellspacing='0' width='100%'>");
	    writeLineToFile(htmlbufferWrite, "<tr>");
	    writeLineToFile(htmlbufferWrite, "<td align=left Class=SubHead><b><u>Report Generated On:	</u></b>" + dateFormat.format(tdate) + "</td>");
	    writeLineToFile(htmlbufferWrite, "<td align=center Class=SubHead><b>Release/Patch: Test Release</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td align=right Class=SubHead><b><u>Instance URL:	</u></b>https://test.oracle.com</td>");
	    writeLineToFile(htmlbufferWrite, "</tr>");
	    writeLineToFile(htmlbufferWrite, "</table><br>");
	    
	}
	
	private static void generateReportModuleSummary(BufferedWriter htmlbufferWrite) throws Exception
	{
		Integer APTotal=0;
		Integer APPassed=0;
		Integer APFailed=0;
		Integer GWPTotal=0;
		Integer GWPPassed=0;
		Integer GWPFailed=0;
		Integer ARTotal=0;
		Integer ARPassed=0;
		Integer ARFailed=0;
		Integer CETotal=0;
		Integer CEPassed=0;
		Integer CEFailed=0;
		Integer FATotal=0;
		Integer FAPassed=0;
		Integer FAFailed=0;
		Integer GLTotal=0;
		Integer GLPassed=0;
		Integer GLFailed=0;
		Integer HRTotal=0;
		Integer HRPassed=0;
		Integer HRFailed=0;
		Integer OIETotal=0;
		Integer OIEPassed=0;
		Integer OIEFailed=0;
		Integer OTLTotal=0;
		Integer OTLPassed=0;
		Integer OTLFailed=0;
		Integer PATotal=0;
		Integer PAPassed=0;
		Integer PAFailed=0;
		
	    Integer TotalTCCount=0;
	    Integer TotalPASSTCCount=0;
	    Integer TotalKnownBugCCount=0;
	    Integer TotalFAILTCCount=0;
	    Integer TotalWARNTCCount=0;
	    long TotalExecutionTime=0;
	    long lngDays=0;
	    long lngHours=0;
	    long lngMinutes=0;
	    long lngSeconds=0;
	    long lngTotalTime=0;
	    long lngCurrExecutionTime=0;

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;
		
	    //Table for Module Test Execution summary
//		writeLineToFile(htmlbufferWrite, "<div style='float: left'>");
		writeLineToFile(htmlbufferWrite, "<div style='display: inline-block'>");
	    writeLineToFile(htmlbufferWrite, "<table border='1' cellpadding='3' cellspacing='0' width='775'>");
	    writeLineToFile(htmlbufferWrite, "<tr>");
	    writeLineToFile(htmlbufferWrite, "<td align=center Class=RequirementsHead><font face=Arial size='3'><b><u><a name = ISST_highLevel>Overall Test Execution Summary</a></u></b></font></td>");
	    writeLineToFile(htmlbufferWrite, "</tr>");
	    writeLineToFile(htmlbufferWrite, "</table>");

	    writeLineToFile(htmlbufferWrite, "<table border='2' cellpadding='0' cellspacing='0' width='775'>");
	    writeLineToFile(htmlbufferWrite, "<tr>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>Module Name</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>#Tests</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>#Passed</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>#Known Bug</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>#Scripts Failed</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>#Warned</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>Start Time</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>End Time</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>Total Execution Time</b></td>");
	    writeLineToFile(htmlbufferWrite, "</tr>");
	    
	    for(Map.Entry<String, ModuleSummary> currObjModuleSummaryMap: oModuleSummaryMap.entrySet())
	    {
	    	//Get Test Current Module Summary Row
	    	ModuleSummary currModuleSummary = currObjModuleSummaryMap.getValue();    	
	        //System.out.println(newTestCaseSummary.ModuleName + "\t" + newTestCaseSummary.TestCaseName + "\t" + "ActionName" + "\t" + newTestCaseSummary.Status + "\t" + newTestCaseSummary.StepsPassed + "\t" + newTestCaseSummary.StepsKnownBug + "\t" + newTestCaseSummary.StepsFailed + "\t" + newTestCaseSummary.StepsWarned + "\t" + newTestCaseSummary.StartTime + "\t" + newTestCaseSummary.EndTime);
	    
	        writeLineToFile(htmlbufferWrite, "<tr>");
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead><a name=" + "_rq href=#" + currModuleSummary.ModuleName + ">" + currModuleSummary.ModuleName + "</a> </td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead align=center>" + currModuleSummary.TotalTCs + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=PassStatus align=center>" + currModuleSummary.TCPassed + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=KnownBugStatus align=center>" + currModuleSummary.TCKnownBug + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=FailStatus align=center>" + currModuleSummary.TCFailed + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=WarnedStatus align=center>" + currModuleSummary.TCWarned + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead align=left>" + currModuleSummary.StartTime + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead align=left>" + currModuleSummary.EndTime + "</td>");
	        
	     
	        
	        /*
	        lngDays = (((currModuleSummary.TotalExecutionInSeconds / 60) / 60) / 24);
	        lngHours = (((((currModuleSummary.TotalExecutionInSeconds / 60) / 60) / 24) - lngDays) * 24);
	        lngMinutes = ((((((currModuleSummary.TotalExecutionInSeconds / 60) / 60) / 24) - lngDays) * 24 - lngHours) * 60);
	        lngSeconds = ((((((((currModuleSummary.TotalExecutionInSeconds / 60) / 60) / 24) - lngDays) * 24 - lngHours) * 60) - lngMinutes) * 60);
	        */
	        lngCurrExecutionTime= currModuleSummary.TotalExecutionInSeconds * 1000;
			long elapsedDays = lngCurrExecutionTime / daysInMilli;
			lngCurrExecutionTime = lngCurrExecutionTime % daysInMilli;
	 
			long elapsedHours = lngCurrExecutionTime / hoursInMilli;
			lngCurrExecutionTime = lngCurrExecutionTime % hoursInMilli;
	 
			long elapsedMinutes = lngCurrExecutionTime / minutesInMilli;
			lngCurrExecutionTime = lngCurrExecutionTime % minutesInMilli;
	 
			long elapsedSeconds = lngCurrExecutionTime / secondsInMilli;
	        
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead align=left>" + elapsedDays + "days:" + elapsedHours + "hh:" + elapsedMinutes + "min:" + elapsedSeconds + "secs" + "</td>");
	        
	        writeLineToFile(htmlbufferWrite, "</tr>");
	        TotalTCCount = TotalTCCount + currModuleSummary.TotalTCs;
	        TotalPASSTCCount = TotalPASSTCCount + currModuleSummary.TCPassed;
	        TotalKnownBugCCount = TotalKnownBugCCount + currModuleSummary.TCKnownBug;
	        TotalFAILTCCount = TotalFAILTCCount + currModuleSummary.TCFailed;
	        TotalWARNTCCount = TotalWARNTCCount + currModuleSummary.TCWarned;
	        lngTotalTime = lngTotalTime + currModuleSummary.TotalExecutionInSeconds;
	    }
	    //Total
	    
	    /*
	    lngDays = (((lngTotalTime / 60) / 60) / 24);
	    lngHours = (((((lngTotalTime / 60) / 60) / 24) - lngDays) * 24);
	    lngMinutes = ((((((lngTotalTime / 60) / 60) / 24) - lngDays) * 24 - lngHours) * 60);
	    lngSeconds = ((((((((lngTotalTime / 60) / 60) / 24) - lngDays) * 24 - lngHours) * 60) - lngMinutes) * 60);
	     */
		lngTotalTime= lngTotalTime * 1000;
		long elapsedDays = lngTotalTime / daysInMilli;
		lngTotalTime = lngTotalTime % daysInMilli;
 
		long elapsedHours = lngTotalTime / hoursInMilli;
		lngTotalTime = lngTotalTime % hoursInMilli;
 
		long elapsedMinutes = lngTotalTime / minutesInMilli;
		lngTotalTime = lngTotalTime % minutesInMilli;
 
		long elapsedSeconds = lngTotalTime / secondsInMilli;
		
		System.out.println("");
		System.out.printf(
		    "Total Modules Execution Time: %d days, %d hours, %d minutes, %d seconds%n", 
		    elapsedDays,
		    elapsedHours, elapsedMinutes, elapsedSeconds);
		
	    writeLineToFile(htmlbufferWrite, "<tr>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>Total</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>" + TotalTCCount.toString() + "</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>" + TotalPASSTCCount.toString() + "</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>" + TotalKnownBugCCount.toString() + "</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>" + TotalFAILTCCount.toString() + "</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>" + TotalWARNTCCount.toString() + "</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>-" + "</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>-" + "</b></td>");
	    writeLineToFile(htmlbufferWrite, "<td class=ColumnHead><b>" + elapsedDays + "days:" + elapsedHours + "hh:" + elapsedMinutes + "min:" + elapsedSeconds + "secs" + "</b></td>");
	    writeLineToFile(htmlbufferWrite, "</tr>");
	    writeLineToFile(htmlbufferWrite, "</table>");
	    writeLineToFile(htmlbufferWrite, "</div>");
	    
	    //Pie Chart
	    writeLineToFile(htmlbufferWrite, "<div id='piechart' style='display: inline-block'>");
	    writeLineToFile(htmlbufferWrite, "<table id='tablepiechart' border='2' cellpadding='0' cellspacing='0' width='100%'></table>");
	    writeLineToFile(htmlbufferWrite, "</div>");
	    writeLineToFile(htmlbufferWrite, "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
	    
//	    writeLineToFile(htmlbufferWrite, "<script type='text/javascript'> google.charts.load('current', {'packages':['corechart']}); google.charts.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable([['Task', 'Hours per Day'], [''," + TotalTCCount+"],  ['',"+ TotalPASSTCCount+"], ['',"+ TotalFAILTCCount+"]]); var options = {'title':'Overall Execution Summary', 'text-align': 'center', 'width':220, 'height':250};  var chart = new google.visualization.PieChart(document.getElementById('tablepiechart')); chart.draw(data, options); } </script>");
	    writeLineToFile(htmlbufferWrite, "<script type='text/javascript'> google.charts.load('current', {'packages':['corechart']}); google.charts.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable([['Passed', 'Failed'], ['Passed',"+ TotalPASSTCCount+"], ['Failed',"+ TotalFAILTCCount+"]]); var options = {title: 'Overall Execution Summary',legend: 'none',pieSliceText: 'label', 'width':200, 'height':257,hAxis: {title: 'Module',titleTextStyle: {color: '#000',fontSize:11,bold: true,italic: false}},vAxis: {title: 'Total Scripts',titleTextStyle: {color: '#000',fontSize: 11,bold: true,}},};var chart = new google.visualization.PieChart(document.getElementById('tablepiechart'));chart.draw(data, options);}</script>");
	    
	 // Bar Graph div
	    writeLineToFile(htmlbufferWrite, "<div id='barchart_material' style='display: inline-block'>");
	    writeLineToFile(htmlbufferWrite, "<table id='tablebarchart_material' border='2' cellpadding='0' cellspacing='0' width='100%'></table>");
	    writeLineToFile(htmlbufferWrite, "</div>");
	    for(Map.Entry<String, ModuleSummary> currObjModuleSummaryMap: oModuleSummaryMap.entrySet())
	    {
	    	ModuleSummary currModuleSummary = currObjModuleSummaryMap.getValue();    	
	    	if(currModuleSummary.ModuleName.equals("AP")){
	    		APTotal=  currModuleSummary.TotalTCs; APPassed=  currModuleSummary.TCPassed; APFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("AR")){
	    		ARTotal=  currModuleSummary.TotalTCs; ARPassed=  currModuleSummary.TCPassed; ARFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("CE")){
	    		CETotal=  currModuleSummary.TotalTCs; CEPassed=  currModuleSummary.TCPassed; CEFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("FA")){
	    		FATotal=  currModuleSummary.TotalTCs; FAPassed=  currModuleSummary.TCPassed; FAFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("GL")){
	    		GLTotal=  currModuleSummary.TotalTCs; GLPassed=  currModuleSummary.TCPassed; GLFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("HR")){
	    		HRTotal=  currModuleSummary.TotalTCs; HRPassed=  currModuleSummary.TCPassed; HRFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("OIE")){
	    		OIETotal=  currModuleSummary.TotalTCs; OIEPassed=  currModuleSummary.TCPassed; OIEFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("OTL")){
	    		OTLTotal=  currModuleSummary.TotalTCs; OTLPassed=  currModuleSummary.TCPassed; OTLFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("PA")){
	    		PATotal=  currModuleSummary.TotalTCs; PAPassed=  currModuleSummary.TCPassed; PAFailed=  currModuleSummary.TCFailed;
	    	}
	    	if(currModuleSummary.ModuleName.equals("GWP")){
	    		GWPTotal=  currModuleSummary.TotalTCs; GWPPassed=  currModuleSummary.TCPassed; GWPFailed=  currModuleSummary.TCFailed;
	    	}
	        
	     // Bar Graph Data Insertion
		    writeLineToFile(htmlbufferWrite, "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
		    writeLineToFile(htmlbufferWrite, " <script type='text/javascript'>google.charts.load('current', {'packages':['bar']}); google.charts.setOnLoadCallback(drawChart); function drawChart() {var data = google.visualization.arrayToDataTable([['Module', 'Total', 'Passed', 'Failed'],['AP', "+ APTotal+", "+ APPassed+","+ APFailed+"], ['AR', "+ ARTotal+", "+ ARPassed+","+ ARFailed+"], ['CE', "+ CETotal+", "+ CEPassed+","+ CEFailed+"], ['FA', "+ FATotal+", "+ FAPassed+","+ FAFailed+"], ['GL', "+ GLTotal+", "+ GLPassed+","+ GLFailed+"], ['WP', "+ GWPTotal+", "+ GWPPassed+","+ GWPFailed+"], ['HR', "+ HRTotal+", "+ HRPassed+","+ HRFailed+"], ['IE', "+ OIETotal+", "+ OIEPassed+","+ OIEFailed+"], ['TL', "+ OTLTotal+", "+ OTLPassed+","+ OTLFailed+"], ['PA', "+ PATotal+", "+ PAPassed+","+ PAFailed+"]]); var options = {title: 'Module Execution Summary',width: 340,height: 257,legend: {position: 'none'},hAxis: {title: 'Module',titleTextStyle: {color: '#000',fontSize:11,bold: true,italic: false}},vAxis: {title: 'Total Scripts',titleTextStyle: {color: '#000',fontSize: 11,bold: true,}},isStacked: '', bar: { groupWidth: '75%' }}; var chart = new google.charts.Bar(document.getElementById('tablebarchart_material')); chart.draw(data, google.charts.Bar.convertOptions(options));}</script>");
	    }
	
	}
	
	public static void generateReportTestCaseSummary(BufferedWriter htmlbufferWrite) throws Exception
	{
		
	    String strPrevReq =""; //String variable to hold the previous requirement name

	    //Table for TestCase Summary
	    writeLineToFile(htmlbufferWrite, "<br><table border='1' cellpadding='3' cellspacing='0' width='100%'>");
	    writeLineToFile(htmlbufferWrite, "<tr>");
	    writeLineToFile(htmlbufferWrite, "<td align=center Class=TestSummaryHead><font face=Arial size='3'><b><u>TestCase Execution Summary</u></b></font></td>");
	    writeLineToFile(htmlbufferWrite, "</tr>");
	    writeLineToFile(htmlbufferWrite, "</table>");

	    strPrevReq = "";
	    //Retrive dta from RecordSet TestCase Summary
	    for(Map.Entry<String, TestCaseSummary> currObjTestCasesSummaryMap: oTestCasesSummaryMap.entrySet())
	    {
	    	//Get Test Current Test Case Summary Row
	    	TestCaseSummary currTestCaseSummary = currObjTestCasesSummaryMap.getValue();	       
	        if(!strPrevReq.contentEquals(currTestCaseSummary.ModuleName))
	        {
	            if(strPrevReq.length() > 0)
	            {
	                writeLineToFile(htmlbufferWrite, "</table><br>");
	            }
	            strPrevReq = currTestCaseSummary.ModuleName;
	            //rsTestCaseSummary.Filter = "ModuleName = '" & strPrevReq & "'"
	            writeLineToFile(htmlbufferWrite, "<table border='1' cellpadding='0' cellspacing='0' width='100%'>");
	            writeLineToFile(htmlbufferWrite, "<tr>");
	            writeLineToFile(htmlbufferWrite, "<td align=left Class=TestSummaryHead><b>Module: <a name=" +
	                strPrevReq + ">" + strPrevReq + "</a> Execution Summary</td>");
	            writeLineToFile(htmlbufferWrite, "<td align=right Class=TestSummaryHead><b>GoTo</b>&nbsp;&nbsp;" +
	                 "<a href=#ISST_highLevel>Overall Summary</a></td>");
	            writeLineToFile(htmlbufferWrite, "</tr>");
	            writeLineToFile(htmlbufferWrite, "</table>");

	            writeLineToFile(htmlbufferWrite, "<table border='2' cellpadding='0' cellspacing='0' width='100%'>");
	            writeLineToFile(htmlbufferWrite, "<tr>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='36%' align='left'><b>TestCase Name</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='6%' align='left'><b>Status</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='6%'><b>#Passed</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='6%'><b>#KnownBug</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='6%'><b>#Failed</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='6%'><b>#Warned</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='17%'><b>Start Time</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='17%'><b>End Time</b></td>");
	            writeLineToFile(htmlbufferWrite, "</tr>");
	        }
	    
	        writeLineToFile(htmlbufferWrite, "<tr>");
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead><a href='#" + currTestCaseSummary.TestCaseName + "' title ='" + currTestCaseSummary.TestCaseName + "'>" + currTestCaseSummary.TestCaseName + "</a>  </td>");
	        
	        if(currTestCaseSummary.Status.startsWith("P")) //PASS
	        {
	            writeLineToFile(htmlbufferWrite, "<td Class=PassStatus align=center>" + currTestCaseSummary.Status + "</td>");
	        }
	        else if (currTestCaseSummary.Status.startsWith("K"))//KNOWNBUG
	        {
	            writeLineToFile(htmlbufferWrite, "<td Class=KnownBugStatus align=center>" + currTestCaseSummary.Status + "</td>");
	        }
	        else if (currTestCaseSummary.Status.startsWith("F"))//FAIL
	        {
	            writeLineToFile(htmlbufferWrite, "<td Class=FailStatus align=center>" + currTestCaseSummary.Status + "</td>");
	        }
	        else if (currTestCaseSummary.Status.startsWith("W"))//"WARNED"
	        {
	        	writeLineToFile(htmlbufferWrite, "<td Class=WarnedStatus align=center>" + currTestCaseSummary.Status + "</td>");
	        }	            
	        
	        writeLineToFile(htmlbufferWrite, "<td Class=PassStatus align=center>" + currTestCaseSummary.StepsPassed + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=KnownBugStatus align=center>" + currTestCaseSummary.StepsKnownBug + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=FailStatus align=center>" + currTestCaseSummary.StepsFailed + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=WarnedStatus align=center>" + currTestCaseSummary.StepsWarned + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead align=left>" + currTestCaseSummary.StartTime + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead align=left>" + currTestCaseSummary.EndTime + "</td>");
	        writeLineToFile(htmlbufferWrite, "</tr>");
	    }//End of For Loop

	    writeLineToFile(htmlbufferWrite, "</table>");
	}
	
	public static void generateReportTestCaseDetails(BufferedWriter htmlbufferWrite) throws Exception
	{
	  
	    String strPrevScript = ""; //String variable to hold the previous script name
	    String strRowStatus = ""; //String variable to represent the row pattern to be appended
	    String strRequirementName =""; //'String variable to hold the Requirement Name
	    String arrValue = "";
	    String [] arrDescription;
	    String [] arrPath;
	    Integer intCnt=0;
	    String strPath="";

	    //Generate Individual TestCase  Execution Summary Table
	    writeLineToFile(htmlbufferWrite, "<br><table border='1' cellpadding='3' cellspacing='0' width='100%'>");
	    writeLineToFile(htmlbufferWrite, "<tr>");
	    writeLineToFile(htmlbufferWrite, "<td align=center Class=TestDetailsHead><font face=Arial size='3'><b><u>Individual TestCase Execution Details</u></b></font></td>");
	    writeLineToFile(htmlbufferWrite, "</tr>");
	    writeLineToFile(htmlbufferWrite, "</table>");

	    strPrevScript = "";
	    for(Map.Entry<String, TestCaseDetails> currObjTestDetailsMap: oTestCaseDetailsMap.entrySet())
	    {
	    	//Get Test Current Test Details Row
	    	TestCaseDetails currTestCaseDetails = currObjTestDetailsMap.getValue();	    
	        strRowStatus = "GeneralHead";
	        if(! currTestCaseDetails.TestCaseName.contentEquals(strPrevScript))//Current Script Not Equal to Previous Script Name
	        {		        	
	            if(strPrevScript.length() > 0 )
	            {
	                writeLineToFile(htmlbufferWrite, "</table><br>");	                
	            }
	            strPrevScript = currTestCaseDetails.TestCaseName;
	            strRequirementName = strPrevScript.substring(0,2);
	            writeLineToFile(htmlbufferWrite, "<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
	            writeLineToFile(htmlbufferWrite, "<tr>");
	            writeLineToFile(htmlbufferWrite, "<td align=left Class=TestDetailsHead><b>Test Case: <a name='" +
	                strPrevScript + "'>" + strPrevScript + "</a> Execution Details</td>");
	            writeLineToFile(htmlbufferWrite, "<td align=right Class=TestDetailsHead><b>GoTo</b>&nbsp;&nbsp;" +
	                 "<a href=#" + currTestCaseDetails.ModuleName + ">Module Summary</a>" +
	                 " Or&nbsp;&nbsp;" + "<a href=#ISST_highLevel>Overall Summary</a></td>");
	            writeLineToFile(htmlbufferWrite, "</tr>");
	            writeLineToFile(htmlbufferWrite, "<tr>");
	            //objHtmlFile.WriteLine( "<td align=left Class=TestDetailsHead><b>Script-Action: <a name=" & _
	            //strPrevScript & ">" & strPrevScript & "</a> Execution Details</td>");
	            writeLineToFile(htmlbufferWrite, "</tr>");
	            writeLineToFile(htmlbufferWrite, "</table>");

	            writeLineToFile(htmlbufferWrite, "<table border='2' cellpadding='0' cellspacing='0' width='100%'>");
	            writeLineToFile(htmlbufferWrite, "<tr>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='4%'><b>Step #</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='4%'><b>Status</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='32%'><b>Step Name</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='44%'><b>Description</b></td>");
	            writeLineToFile(htmlbufferWrite, "<td class=ColumnHead width='16%'><b>Date Time</b></td>");
	            writeLineToFile(htmlbufferWrite, "</tr>");
	        }
	        if(!(currTestCaseDetails.Status.startsWith("P") && currTestCaseDetails.StepName.equals("Completed")))
	        {
	        if(currTestCaseDetails.Status.startsWith("F"))// = "FAIL" Then
	        {
	            strRowStatus = "FailStatus";
	        }
	        else if(currTestCaseDetails.Status.startsWith("K"))// "KNOWNBUG" Then
	        {
	            strRowStatus = "KnownBugStatus";
	        }

	        writeLineToFile(htmlbufferWrite, "<tr>");
	        writeLineToFile(htmlbufferWrite, "<td Class=GeneralHead align=center>" + currTestCaseDetails.StepNo.toString() + "</td>");
	                
	        if(currTestCaseDetails.Status.startsWith("P"))//PASS" Then
	        {
	            writeLineToFile(htmlbufferWrite, "<td Class=PassStatus align=center>" + currTestCaseDetails.Status + "</td>");
	        }
	        else
	        {
	            writeLineToFile(htmlbufferWrite, "<td Class=" + strRowStatus + " align=center>" + currTestCaseDetails.Status + "</td>");
	        }
	        
	        writeLineToFile(htmlbufferWrite, "<td Class=" + strRowStatus + ">" + currTestCaseDetails.StepName + "</td>");
	        String [] currScreenShot = currTestCaseDetails.ScreenShot.split("ScreenShots/");
	        System.err.println("Balaji Length" + currScreenShot.length);
	        if(currScreenShot.length>0){
	        
//	        if(currTestCaseDetails.Status.startsWith("F"))// "FAIL" Then
//	        {
	            /*arrValue = Split(rsTestCaseDetails("Description").Value, "Refer Screenshot at")
	            arrPath = arrValue(1)
	            strPath = Split(arrValue(1), "\")
	            */
	        	
//	        	String [] currScreenShot = currTestCaseDetails.ScreenShot.split("ScreenShots/");
	            //writeLineToFile(htmlbufferWrite, "<td Class=" + strRowStatus + ">" + arrValue(0) + "Refer ScreenShot"" " & "<a href=" & "ScreenShots" & Split(Replace(arrPath, """", ""), "ScreenShots")(1) & ">" & Replace(strPath(UBound(strPath)), """", "") & "</a> </td>")
//	 Balaji Commented //       	writeLineToFile(htmlbufferWrite, "<td Class=" + strRowStatus + ">" + currTestCaseDetails.Description + " Refer ScreenShot " + "<a href='.\\" + "ConsolidatedReport\\ScreenShots\\" + currScreenShot[1] + "'  TARGET='_new'>  <img src='.\\" + "ScreenShots\\" + currScreenShot[1] + "' alt='Click to Maximize' border='no' height='15' width = '30' /></a> </td>");
	        	
	        }
	        
	       else{
	    	     		   
	    	   	writeLineToFile(htmlbufferWrite, "<td Class=" + strRowStatus + ">" + currTestCaseDetails.Description + "</td>");
	        }
	        writeLineToFile(htmlbufferWrite, "<td Class=" + strRowStatus + ">" + currTestCaseDetails.Description + "</td>");
	        writeLineToFile(htmlbufferWrite, "<td Class=" + strRowStatus + ">" + currTestCaseDetails.StepStartTime + "</td>");
	        writeLineToFile(htmlbufferWrite, "</tr>");
	        }

	    }//End of For Loop
	    writeLineToFile(htmlbufferWrite, "</table>");
	}
	
	public static void generateReportHTMLFooter(BufferedWriter htmlbufferWrite) throws Exception
	{
		
	    Date currNow = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
	    String currYear = new SimpleDateFormat("yyyy").format(currNow);
	    
	    writeLineToFile(htmlbufferWrite, "<br><table <table border='0' cellpadding='0' cellspacing='0' width='100%'>");
	    writeLineToFile(htmlbufferWrite, "<td align=center><font face=arial size='2'>Report Developed by Test - Balaji Korapati © " + currYear + " </font></td></table><br>");
	    writeLineToFile(htmlbufferWrite, "</body>");
	    writeLineToFile(htmlbufferWrite, "</html>");
	    
	}
	
	private static void generateReportRecordSets()throws Exception
	{
	    String strHours="0";
	    String strMinutes="0";
	    String strSeconds="0";
	   
	    String strModuleName="Scion";
	    String strActionName="";
	    String strTestCaseName="";

	    String strDays="";
	    String strEndTime="";
	    Boolean blnUpdate=false;
	    
	    String strFileContent="";
	    
	    //For Calculating Module Time
	    String strTotalExecutionInSeconds="0";
	    String strTestStartTime="";
	    String strTestEndTime="";
	    String strPrevModuleName="";
	    String strPrevTestName="";
	    String strPrevStartTime="";
	    
	    //ModuleSummary newModuleSummary = new ModuleSummary();
	    
	    
	    String strModuleStep = "ModuleName" + "\t" + "TotalTCs" + "\t" + "TCPassed" + "\t" + "TCKnownBug" + "\t" + "TCFailed" + "\t" + "TCWarned" + "\t" + "StartTime" + "\t" + "EndTime" + "\t" + "TotalExecutionInSeconds";
	    
	    String strTestCaseSummary = "ModuleName" + "\t" + "TestCaseName" + "\t" + "ActionName" + "\t" + "Status" + "\t" + "StepsPassed" + "\t" + "StepsKnownBug" + "\t" + "StepsFailed" + "\t" + "StepsWarned" + "\t" + "StartTime" + "\t" + "EndTime";
	    
	    String strTestCaseDetails = "ModuleName" + "\t" + "TestCaseName" + "\t" + "StepNo" + "\t" + "Status" + "\t" + "StepName" + "\t" + "Description" + "\t" + "DateTime";
	
	    String strTestName;
	    File folder = new File(resultFileFolder);
	    File[] listOfFiles = folder.listFiles(); 
	   
	    for (int currFileNo = 0; currFileNo < listOfFiles.length; currFileNo++) 
	    {	   
	     if (listOfFiles[currFileNo].isFile()) 
	     {
	    	 strTestName = listOfFiles[currFileNo].getName();
	         if (strTestName.endsWith(".log") || strTestName.endsWith(".LOG"))
	         {
	        	//Getting Module Name from Script Name
	        	 int V_codeindex=strTestName.indexOf("_",0);
	        	 strModuleName = strTestName.substring(0,V_codeindex);
//	        	 strModuleName = strTestName.substring(0, 4);
	        	 strTestCaseName =strTestName;//strTestName.substring(strTestName.indexOf("_", 0)+1);
	        	 strTestCaseName = strTestCaseName.replace(".log", "");
	        	 
	        	 System.out.println("Module Name:" + strModuleName);
	        	 System.out.println("Test Case Name:" + strTestCaseName);
	        	 
	        	 //Creating new Test Case Summary
	        	 CustumReport TC_Summary = new CustumReport(currentDir +"\\data\\config.properties");
	        	 TestCaseSummary newTestCaseSummary=TC_Summary.new TestCaseSummary();
//	        	 TestCaseSummary newTestCaseSummary = new TestCaseSummary();
	        	 newTestCaseSummary.ModuleName = strModuleName;
        		 newTestCaseSummary.TestCaseName = strTestCaseName;  
        		 
	        	 //Preparing to read the file	        	 
	        	 BufferedReader inputStream = null;
	        	 inputStream = new BufferedReader(new FileReader(listOfFiles[currFileNo]));
	        	 String currLine="";
	        	 strTestStartTime="";
	        	 strTestEndTime="";
	        	 Integer currLineNo = 0;
		         System.out.println(strTestCaseDetails);
		         
		         //Read each line
	        	 while ((currLine = inputStream.readLine()) != null) 
	        	 {
	        		 boolean scriptAborted=true;//To Verify if script aborted
	        		 
	        		 String [] arrLine=currLine.split("\t");	//Split Current Line and add it to array
	        		 if(currLineNo==0 || currLineNo==1)
	        		 {
	        			if(currLineNo==1)//copying start date time
	        			{
	        				strTestStartTime=arrLine[4];
	        				strPrevStartTime=strTestStartTime;
	        				newTestCaseSummary.StartTime=strTestStartTime;
	        			}//End if for Currline =1
	        		 }//End If for Currline = 0 or 1
	        		 else if(arrLine[2].length()==0 && arrLine[4].length()!=0)//End Time
	        		 {
	        			 strEndTime=arrLine[4];
	        			 newTestCaseSummary.EndTime = arrLine[4];
	        		 }
	        		 else
	        		 {	       
	        			 CustumReport TCDetails_Summary = new CustumReport(currentDir +"\\data\\config.properties");
	        			 TestCaseDetails newTestCaseDetails=TCDetails_Summary.new TestCaseDetails();
//		    	         TestCaseDetails newTestCaseDetails = new TestCaseDetails();
		    	         newTestCaseDetails.ModuleName = strModuleName;
		    	         newTestCaseDetails.TestCaseName = strTestCaseName;
		    	         newTestCaseDetails.StepNo = Integer.parseInt(arrLine[0]);
		    	         newTestCaseDetails.Status = arrLine[1];
		    	         newTestCaseDetails.StepName = arrLine[2];
		    	         newTestCaseDetails.Description= arrLine[3];
		    	         newTestCaseDetails.StepStartTime = strPrevStartTime;
		    	         newTestCaseDetails.StepEndTime = arrLine[4];
		    	         if(arrLine.length == 6){
		    	        	 newTestCaseDetails.ScreenShot = arrLine[5];
		    	         }else{
		    	        	 newTestCaseDetails.ScreenShot = "";
		    	         }
		    	         strPrevStartTime=arrLine[4];
		    	         
		                 //Update Status count in TestCase Summary
		                 if (newTestCaseDetails.Status.startsWith("P")) //PASS
		                 {			      
		                	 if(! newTestCaseDetails.StepName.equals("Completed"))
		                	 {
		                       newTestCaseSummary.StepsPassed = newTestCaseSummary.StepsPassed + 1;
		                	 }
		                 }else if (newTestCaseDetails.Status.startsWith("F"))//Fail
		                 {
	                         if (newTestCaseDetails.Description.contains("KNOWNBUG"))
	                         {
	                        	 newTestCaseSummary.StepsKnownBug = newTestCaseSummary.StepsKnownBug+ 1;
	                         
	                             newTestCaseDetails.Status = "KNOWNBUG";
	                         }
	                         else
	                         {
	                        	 newTestCaseSummary.StepsFailed = newTestCaseSummary.StepsFailed + 1;
	                         }
		                 }else if (newTestCaseDetails.Status.contains("W"))//Warning
		                 {
		                     newTestCaseSummary.StepsWarned = newTestCaseSummary.StepsWarned + 1;
		                 }
		                 newTestCaseSummary.EndTime = arrLine[4];
		                 strTestEndTime = arrLine[4];
		                 oTestCaseDetailsMap.put(newTestCaseDetails.TestCaseName+getTwoDigitNumber(newTestCaseDetails.StepNo.toString()), newTestCaseDetails);
		                 System.out.println(newTestCaseDetails.ModuleName + "\t" + newTestCaseDetails.TestCaseName + "\t" + newTestCaseDetails.StepNo + "\t" + newTestCaseDetails.Status + "\t" + newTestCaseDetails.StepName + "\t" + newTestCaseDetails.Description + "\t" + newTestCaseDetails.StepStartTime + "\t" + newTestCaseDetails.StepEndTime);
	        		 }//end if for not line 0 & 1
	        		 currLineNo=currLineNo+1;
	        	 }//While reading line in current file
 				 newTestCaseSummary.StartTime=strTestStartTime;
	        	 //Verify if strTestCaseName is not found in oTestCasesSummaryMap
	        	 TestCaseSummary currTestCaseSummary = oTestCasesSummaryMap.get(strTestCaseName);
	        	 if(currTestCaseSummary==null)
	        	 {
	        		 //As currTestCaseSummary is null creating new TestCaseSummary
	        		 oTestCasesSummaryMap.put(strTestCaseName, newTestCaseSummary);
	        	 }
                strTestStartTime = "";
                strTestEndTime = "";	        	
		        oTestCasesSummaryMap.put(newTestCaseSummary.TestCaseName, newTestCaseSummary);
		         
		         /* DO NOT DELETE BELOW CODE
		         System.out.println("");
		         System.out.println("Test Summary");
		         System.out.println(strTestCaseSummary);
		         System.out.println(newTestCaseSummary.ModuleName + "\t" + newTestCaseSummary.TestCaseName + "\t" + "ActionName" + "\t" + newTestCaseSummary.Status + "\t" + newTestCaseSummary.StepsPassed + "\t" + newTestCaseSummary.StepsKnownBug + "\t" + newTestCaseSummary.StepsFailed + "\t" + newTestCaseSummary.StepsWarned + "\t" + newTestCaseSummary.StartTime + "\t" + newTestCaseSummary.EndTime);
		         */
		         
	          }//End of if new test case
	       }//End of if test case or not
	    }//End of files for loop
	    

	    /*DO NOT DELETE - Useful for debugging
	    System.out.println("");
        System.out.println("Test Summary");
        System.out.println(strTestCaseSummary);
	    for(Map.Entry<String, TestCaseSummary> currObjTestCasesSummaryMap: oTestCasesSummaryMap.entrySet())
	    {
	    	//Get Test Current Test Case Summary Row
	    	TestCaseSummary newTestCaseSummary = currObjTestCasesSummaryMap.getValue();	    	
	        System.out.println(newTestCaseSummary.ModuleName + "\t" + newTestCaseSummary.TestCaseName + "\t" + "ActionName" + "\t" + newTestCaseSummary.Status + "\t" + newTestCaseSummary.StepsPassed + "\t" + newTestCaseSummary.StepsKnownBug + "\t" + newTestCaseSummary.StepsFailed + "\t" + newTestCaseSummary.StepsWarned + "\t" + newTestCaseSummary.StartTime + "\t" + newTestCaseSummary.EndTime);
	    }
    	     */
	    
	    //Generate TestCase Summary & Update Test Module Status
	    for(Map.Entry<String, TestCaseSummary> currObjTestCasesSummaryMap: oTestCasesSummaryMap.entrySet())
	    {
	    	//Get Test Current Test Case Summary Row
	    	TestCaseSummary currTestCaseSummary = currObjTestCasesSummaryMap.getValue();
	    	
	    	//Get Required Module Row
		    ModuleSummary currModuleSummary = oModuleSummaryMap.get(currTestCaseSummary.ModuleName);
		    if(currModuleSummary==null)
		    {
		    	CustumReport MOd_Sum=new CustumReport(currentDir +"\\data\\config.properties");
		    	currModuleSummary =MOd_Sum.new ModuleSummary();
//		    	currModuleSummary = new ModuleSummary();
		    	currModuleSummary.ModuleName = currTestCaseSummary.ModuleName;
		    }
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
			Date startDate = formatter.parse(currTestCaseSummary.StartTime);
			Date endDate = formatter.parse(currTestCaseSummary.EndTime);
			long elapsedSeconds = (endDate.getTime()-startDate.getTime())/1000;
			currModuleSummary.TotalExecutionInSeconds = currModuleSummary.TotalExecutionInSeconds + elapsedSeconds;
		    
	    	//Updating Test Cases Status 
	        if(currTestCaseSummary.StepsFailed > 0)
	        {
	        	currTestCaseSummary.Status="FAIL";
	        	currModuleSummary.TCFailed =currModuleSummary.TCFailed+1; 
	        }
	        else if(currTestCaseSummary.StepsKnownBug > 0)
	        {
	        	currTestCaseSummary.Status="KNOWNBUG";
	            currModuleSummary.TCKnownBug = currModuleSummary.TCKnownBug + 1;
	        }
	        else if(currTestCaseSummary.StepsWarned > 0)
	        {
	        	currTestCaseSummary.Status="WARNED";
	            currModuleSummary.TCWarned = currModuleSummary.TCWarned + 1;
	        }
	        else if(currTestCaseSummary.StepsPassed > 0)
	        {
	        	currTestCaseSummary.Status= "PASS";
	            currModuleSummary.TCPassed = currModuleSummary.TCPassed + 1;
	        }
	        currObjTestCasesSummaryMap.setValue(currTestCaseSummary);
	        //Updating back Module Summary in TreeMap
	        oModuleSummaryMap.put(currTestCaseSummary.ModuleName, currModuleSummary);
	    }//End For Loop Test Summary

	    //Generate Module Summary
	    for(Map.Entry<String, ModuleSummary> currObjTestModuleSummaryMap: oModuleSummaryMap.entrySet())
	    {
	    	//Getting Module Summary Row
	    	ModuleSummary currModuleSummary = currObjTestModuleSummaryMap.getValue();
	    	for(Map.Entry<String, TestCaseSummary> currObjTestCasesSummaryMap: oTestCasesSummaryMap.entrySet())
	    	{
	    		//Getting TestCase Summary Row
	    		TestCaseSummary currTestCaseSummary = currObjTestCasesSummaryMap.getValue();
	    		if(currTestCaseSummary.ModuleName.contentEquals(currModuleSummary.ModuleName))
				{
	    			if(currModuleSummary.StartTime.contentEquals(""))
	    			{
	    				currModuleSummary.StartTime = currTestCaseSummary.StartTime;
	    				currModuleSummary.EndTime = currTestCaseSummary.EndTime;
	    			}
	    			else
	    			{
	    				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
	        			Date TestSummaryStartTime = formatter.parse(currTestCaseSummary.StartTime);
	        			Date ModuleSummaryStartTime = formatter.parse(currModuleSummary.StartTime);
	        			long elapsedSeconds = (ModuleSummaryStartTime.getTime()-TestSummaryStartTime.getTime())/1000;
	        			if(elapsedSeconds>0)
	        			{
	        				currModuleSummary.StartTime = formatter.format(TestSummaryStartTime);
	        			}
	        			//Calculating Time Elapse in seconds
	        			Date TestSummaryEndTime = formatter.parse(currTestCaseSummary.EndTime);
	        			Date ModuleSummaryEndTime = formatter.parse(currModuleSummary.EndTime);
	        			elapsedSeconds = (ModuleSummaryEndTime.getTime()-TestSummaryEndTime.getTime())/1000;
	        			if(elapsedSeconds<0)
	        			{
	        				currModuleSummary.EndTime = formatter.format(TestSummaryEndTime);
	        			}
	    			}
				}
		        currModuleSummary.TotalTCs = currModuleSummary.TCPassed + currModuleSummary.TCFailed + currModuleSummary.TCKnownBug;		        	    		
	    	}
	    	oModuleSummaryMap.put(currModuleSummary.ModuleName, currModuleSummary);
	    }

	    /*DO NOT DELETE Useful for debugging 
	    System.out.println("");
        System.out.println("Test Summary");
        System.out.println(strTestCaseSummary);
	    for(Map.Entry<String, TestCaseSummary> currObjTestCasesSummaryMap: oTestCasesSummaryMap.entrySet())
	    {
	    	//Get Test Current Test Case Summary Row
	    	TestCaseSummary newTestCaseSummary = currObjTestCasesSummaryMap.getValue();	    	
	        System.out.println(newTestCaseSummary.ModuleName + "\t" + newTestCaseSummary.TestCaseName + "\t" + "ActionName" + "\t" + newTestCaseSummary.Status + "\t" + newTestCaseSummary.StepsPassed + "\t" + newTestCaseSummary.StepsKnownBug + "\t" + newTestCaseSummary.StepsFailed + "\t" + newTestCaseSummary.StepsWarned + "\t" + newTestCaseSummary.StartTime + "\t" + newTestCaseSummary.EndTime);
	    }

	    System.out.println("");
        System.out.println("Test Module Summary");
        System.out.println(strModuleStep);
	    for(Map.Entry<String, ModuleSummary> currObjTestModuleSummaryMap: oModuleSummaryMap.entrySet())
	    {
	    	ModuleSummary currModuleSummary = currObjTestModuleSummaryMap.getValue();
	        System.out.println(currModuleSummary.ModuleName + "\t" + currModuleSummary.TotalTCs + "\t" + currModuleSummary.TCPassed + "\t" + currModuleSummary.TCKnownBug + "\t" + currModuleSummary.TCFailed + "\t" + currModuleSummary.TCWarned + "\t" + currModuleSummary.StartTime + "\t" + currModuleSummary.EndTime + "\t" + currModuleSummary.TotalExecutionInSeconds);

	    }
	    DO NOT DELETE*/
	}
	
	private static String getTwoDigitNumber(String number){
    	int length = number.length();
    	for(int i=0; i<2-length; i++){
    		number = "0"+number;
    	}
    	return number;
    }
	
	private static void generatePieChartModuleSummary(BufferedWriter htmlbufferWrite) throws Exception
	{
		
		writeLineToFile(htmlbufferWrite, "<div id='piechart'></div>");
	    writeLineToFile(htmlbufferWrite, "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
	    
	    writeLineToFile(htmlbufferWrite, "<script type='text/javascript'> google.charts.load('current', {'packages':['corechart']}); google.charts.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable([['Task', 'Hours per Day'],  ['Work', 8],  ['Eat', 2], ['TV', 4], ['Gym', 2], ['Sleep', 8]]); var options = {'title':'Execution Summary', 'width':550, 'height':400};  var chart = new google.visualization.PieChart(document.getElementById('piechart')); chart.draw(data, options); } </script>");
	}
	
	public void reportExecutionStatus(StepStatus status, String stepDetails, Boolean screenShot)throws Exception
	{	
		String stepName=testStepName;
		reportExecutionStatus(status,stepName,stepDetails,screenShot);
	}
	
	public void reportExecutionStatus(StepStatus status,String stepName, String stepDetails, Boolean screenShot)throws Exception
	{
		System.out.println("screen paramater while passing"+screenShot);
		if(status.equals(StepStatus.Pass))
		{
			System.out.print("Pass");
		}else if(status.equals(StepStatus.Fail))
		{
			System.out.print("Fail");
		}else if(status.equals(StepStatus.Warning))
		{
			System.out.print("Warning");
		}
		if(stepName.isEmpty() || stepName.equalsIgnoreCase("") || stepName.equals(null))
		{
			stepName=testStepName;
		}
		generateTxtReport(status,stepName,stepDetails,screenShot);
		currStepNo=currStepNo+1;//Incrementing Current Step # to +1
	}
	
	public void generateTxtReport(StepStatus status,String stepName, String stepDetails, Boolean screenShot)throws Exception
	{
		try{
			resultFileFolder=WorkingDir+"/ConsolidatedReport/";
			
			File currFile = new File(resultFilePath);			
				if(currStepNo==0)
				{
					if(currFile.exists()) currFile.delete();
					currFile.createNewFile();
				}
				
				//Writing content to file
				FileWriter currFileWriter = new FileWriter(currFile.getAbsoluteFile(),true);
				BufferedWriter bufferWrite = new BufferedWriter(currFileWriter);
				
				if(currStepNo==0)//Write Header Line
				{
					bufferWrite.write("SlNo" + "\t" + "Status" + "\t" + "StepName" + "\t" + "StepDesc" + "\t" + "DateTime" + "\t" + "ScreenShot" + "\n");
				
				}
				//To get Current Date Time and converting to Expected date time format
				SimpleDateFormat currDateTime = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
				Date currDate= new Date();
				String currTime = currDateTime.format(currDate);
				String currImage="";
				System.out.println("Screen parameter"+screenShot);
//				if(status.ordinal()==1 || screenShot==true)
				if(screenShot==true)//If status is fail or ScreenShot=true
				{
					System.out.println("screen paramater entered in to loop"+screenShot);
					//Creating ScreenShots Folder if not exists
					resultFileFolder=WorkingDir+"/ConsolidatedReport/";
					File dir = new File(resultFileFolder + "ScreenShots");
					if (!dir.exists())
					{
						dir.mkdirs();
					}
					currImage = resultFileFolder + "ScreenShots/Img_" + currTime.replace(" ", "_").replace(":", "_").replace("-", "_") + ".jpg";
					this.screenCapture(currImage);
				
				}
				String CurrStep = currStepNo + "\t" + status.toString() + "\t" + stepName.replace("\t", "").replace("\n", " ") + "\t" + stepDetails.replace("\t", " ").replace("\n", " ") + "\t" + currTime + "\t" + currImage + "\n";
				bufferWrite.write(CurrStep);
				bufferWrite.close();
		}
			
			catch(Exception e) {
			    System.err.println("Error Description: " + e.getMessage());
				}
	}
	
	private void screenCapture(String currImageTimePath) throws Exception
	{
		try{
			Robot robot = new Robot();
		    Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		    BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
		    ImageIO.write(bufferedImage, "jpg", new File(currImageTimePath));//"d:/test.jpg"));
			}
			catch(Exception e) 
			{
			    System.err.println("Error : " + e.getMessage());
			}
	}
	
	public void setStepName(String strStepName) throws Exception
	{
		testStepName=strStepName;
	}
	public void setTestCaseName(String currTestCaseName)throws Exception 
	{
		resultFileFolder=WorkingDir+"/ConsolidatedReport/";
		testCaseName = currTestCaseName;
		resultFilePath = resultFileFolder + "/" + currTestCaseName + ".log";//Appending File to Results Folder Path
		reportExecutionStatus(StepStatus.Pass, "", "", false);
	}
	
	public void getConfigData(String libPath)
	{	
		System.out.println("Entered");
//		Config C= new Config(libPath);
//		this.WorkingDir=C.ResultsPath;
		this.WorkingDir="C:/Users/bakorapa/Desktop/ConsolidatedReport/";
		System.out.println("url" + this.WorkingDir);
		resultFileFolder=this.WorkingDir;
	//	this.Region=C.Region;
		System.out.println("path:"+ resultFileFolder);
	}
}
