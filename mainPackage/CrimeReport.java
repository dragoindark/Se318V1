//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.time.Duration;
import java.time.ZonedDateTime;

//this is the main class where we will create objects for crime reports, they will be derived from the base interface
interface CrimeReport {
	public String getReportName(); //the name of the report
	public String getReportContent(); //content about the case,how it happened etc
	public String getReportLocation(); //details about the location where the case happened
	public String getReportDate(); // the date when the report was created
	public String getReportStatus(); //the current status of the crime report
	public int getID();
	public String createTimeNow();
	public boolean isFinished(String status);
	public void setReportName(String reportName);
	public void setReportContent(String reportContent);
	public void setReportLocation(String reportLocation);
	public void setReportStatus(String reportStatus);
	public void setReportDate();
	public String getReportSpecificData();
}


abstract class BaseCrimeReportClass implements CrimeReport{
	public BaseCrimeReportClass(String reportName,String reportLocation,String reportContent) {
		this.reportName=reportName;
		this.reportLocation=reportLocation;
		this.reportContent=reportContent;
		this.setReportDate();
		this.reportStatus="Started";
		this.id=idCreator.idCreator().createID();
	}
	public int getID() {
		return this.id;
	}
	public void setReportName(String reportName) {
		this.reportName=reportName;
	}
	public String getReportName() {
		return this.reportName;
	}
	public void setReportContent(String reportContent) {
		this.reportContent=reportContent;
	}
	public String getReportContent() {
		return this.reportContent;
	}
	public void setReportLocation(String reportLocation) {
		this.reportLocation=reportLocation;
	}
	public String getReportLocation() {
		return this.reportLocation;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus=reportStatus;
	}
	public String getReportStatus() {
		return this.reportStatus;
	}
	public void setReportDate() {
		this.reportDate=this.createTimeNow();
	}
	public String getReportDate() {
		return this.reportDate;
	}
	public String createTimeNow() {
		ZonedDateTime timeNow=ZonedDateTime.now();
	    return timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear()+" "+
				timeNow.getHour()+":"+timeNow.getMinute()+":"+timeNow.getSecond()+"."+timeNow.getNano();
	}
	
	public boolean isFinished(String status) {
		if(status.equalsIgnoreCase("finished") || status.equalsIgnoreCase("done") || status.equalsIgnoreCase("found") ) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		return "Report name is "+reportName+" report content is \n"+reportContent+" report location is\n"+reportLocation;
				}
	
	protected String reportName;
	protected String reportContent;
	protected String reportLocation;
	protected String reportDate;
	protected String reportStatus;
	protected int id;
}

class MissingPeopleReport extends BaseCrimeReportClass{

	public MissingPeopleReport(String reportName, String reportLocation, String reportContent) {
		super(reportName, reportLocation, reportContent);
	}
	
	public void personFound(String foundAt) {
		this.reportStatus="found";
		this.foundAt=foundAt;
		this.foundDate=this.createTimeNow();
		this.amountOfDaysMissing=this.calculateDayDifference();
	}
	
	public int calculateDayDifference() {
		try {
			Duration duration=Duration.between(ZonedDateTime.parse(reportDate),ZonedDateTime.parse(foundDate));
			return Math.toIntExact(duration.toDays());	
		}catch(Exception e){
			e.printStackTrace();
			return 0;			
		}	
	}
	protected String foundAt;
	protected String foundDate;
	protected int amountOfDaysMissing;
	public String getReportSpecificData() {
		return null;
	}
}

class ComplaintReport extends BaseCrimeReportClass{

	public ComplaintReport(String reportName, String reportLocation, String reportContent) {
		super(reportName, reportLocation, reportContent);
	}

	public String getReportSpecificData() {
		
		return null;
	}
	
}
















