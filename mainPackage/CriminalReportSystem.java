//Faruk Burak Gürel@dragoindark

//this class is not used,because it was not my part 
package mainPackage;

import java.time.ZonedDateTime;

//this is the interface for persons involved in criminal cases,missing,wanted 
interface CriminalPerson{
	public String getName();
	public int getAge();
	public String getDate();
	public String getDetails();
	public String getPhoneNumber();
	public int getID();
	public void setCrimeReport(CrimeReport report);
	public void setName(String name);
	public void setAge(int age);
	public void setDate();
	public void setDetails(String details);
	public void setPhoneNumber(String phoneNumber);
	public CrimeReport getReport();
}
//this is the class where we will derive the concrete classes,written to reduce redundancy
abstract class CriminalBaseClass implements CriminalPerson{
	public CriminalBaseClass(String name,int age,String phoneNumber,CrimeReport report) {
		if(name==null || details==null || phoneNumber==null || report==null) {
			System.out.println("Wrong inputs exiting");
		}else {
			this.name=name;
			this.age=age;
			this.phoneNumber=phoneNumber;
			this.setDate();
			this.report=report;
			this.details="Details not known";
			this.id=idCreator.idCreator().createID();
		}	
	}
	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public String getDate() {
		return this.date;
	}
	public String getDetails() {
		return this.details;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public CrimeReport getReport() {
		if(this.report==null) {
			System.out.println("There are no reports attached to the person");
			return null;
		}else {
			return this.report;	
		}
	}
	public void setCrimeReport(CrimeReport report) {
		if(report==null) {
			System.out.println("Wrong value, report can not be null");
		}else {
			this.report=report;	
		}	
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setDate() {
		this.date=this.createTimeNow();
	}
	public void setDetails(String details) {
		this.details=details;
	}
	public String createTimeNow() {
		ZonedDateTime timeNow=ZonedDateTime.now();
	    return timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear()+" "+
				timeNow.getHour()+":"+timeNow.getMinute()+":"+timeNow.getSecond()+"."+timeNow.getNano();
	}
	
	public String toString() {
		return "Persons name is "+name+" , age is "+age+" the person is entered in to the system at  "+date+" the details are as follows \n"+details;
	}
	
	protected int id; //persons id in the system
	protected String name; //persons name
	protected int age; //persons age
	protected String date; //the date of the person being submitted to the system
	protected String details; //optional details of the person, like hair color,height,if they have a scar or tatto etc.
	protected String phoneNumber; //the phone number of the person
	protected CrimeReport report; //the crime report about the person
}

class MissingPeople extends CriminalBaseClass{

	public MissingPeople(String name, int age, String phoneNumber, CrimeReport report) {
		super(name, age, phoneNumber, report);
	}
	
	
	protected boolean isFound;
	protected String personStatus;

	
	
}























