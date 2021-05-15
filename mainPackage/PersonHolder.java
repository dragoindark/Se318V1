//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface PersonHolder{
	public boolean addCriminalPerson(CriminalPerson person);
	public boolean deleteCriminalPerson(CriminalPerson person);
	public CriminalPerson getCriminalPerson(int id);
	public CriminalPerson getCriminalPersonByName(String person);
	public CriminalPerson getCriminalPersonByReport(CrimeReport report);
	public HashMap<Integer,CriminalPerson> getCriminalPersonMap();
	public void printAllCriminalPersons();
	public boolean checkCriminalPersonExists(CriminalPerson person);
	public boolean checkCriminalPersonExistsWithName(String name);
}

class CriminalPersonObject implements PersonHolder{
	private CriminalPersonObject() {personHolder=new HashMap<Integer,CriminalPerson>();}
	public static CriminalPersonObject CriminalPersonObject() {
		if(persons_instance==null) {
			persons_instance=new CriminalPersonObject();
		}
		return persons_instance;
	}
	public CriminalPerson getCriminalPersonByReport(CrimeReport report) {
		CriminalPerson personToReturn=null;
		for(Map.Entry<Integer,CriminalPerson> map:this.personHolder.entrySet()) {
			CriminalPerson person=map.getValue();
			if(person.getReport().getID()==report.getID()) {
				personToReturn=person;
			}
		}
		return personToReturn;
	}
	public boolean addCriminalPerson(CriminalPerson person) {
		if(person==null) {
			return false;
		}else {
			try {
				this.personHolder.put(person.getID(),person);
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
	}
	public boolean deleteCriminalPerson(CriminalPerson person) {
		if(personHolder.containsKey(person.getID())) {
			try {
				personHolder.remove(person.getID());
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
		
	}
	public CriminalPerson getCriminalPerson(int id) {
		if(personHolder.containsKey(id)) {
			return this.personHolder.get(id);
		}else {
			System.out.println("CriminalPerson not found");
			return null;
		}
	}
	public HashMap<Integer, CriminalPerson> getCriminalPersonMap() {
		return this.personHolder;
	}
	public void printAllCriminalPersons() {
		for(Map.Entry<Integer,CriminalPerson> map:this.personHolder.entrySet()) {
			CriminalPerson person=map.getValue();
			person.toString();
		}
	}
	public CriminalPerson getCriminalPersonByName(String name) {
		CriminalPerson personToReturn=null;
		for(Map.Entry<Integer,CriminalPerson> map:this.personHolder.entrySet()) {
			CriminalPerson person=map.getValue();
			if(person.getName().equalsIgnoreCase(name)) {
				personToReturn=person;
			}
		}
		if(personToReturn==null) {
			System.out.println("CriminalPerson not found");
		}
		return personToReturn;
	}
	public boolean checkCriminalPersonExistsWithName(String name) {
		boolean bool=false;
		for(Map.Entry<Integer,CriminalPerson> map:this.personHolder.entrySet()) {
			CriminalPerson person=map.getValue();
			if(person.getName().equalsIgnoreCase(name)) {
				bool=true;
			}
		}
		return bool;
	}
	public boolean checkCriminalPersonExists(CriminalPerson person) {
		return this.personHolder.containsKey(person.getID());
	}
	
	private static CriminalPersonObject persons_instance=null;
	protected HashMap<Integer,CriminalPerson> personHolder;
}
//this is a facade class that Faruk Burak Gurel and Nur created, this basically makes whole process easier.
class personFacade{
	public static personFacade personFacade() {
		if(person_facade==null) {
			person_facade=new personFacade();
		}
		return person_facade;
	}
	private personFacade() {
		holder=CriminalPersonObject.CriminalPersonObject();
		reports=new HashMap<Integer,CrimeReport>();
	}
	public boolean createCriminalPerson(String name, int age, String phoneNumber, CrimeReport report,String type) {
		CriminalPerson person;
		boolean creationSuccessfull=false;
		if(type.equalsIgnoreCase("missing")) {
			person=new MissingPeople(name,age,phoneNumber,report);
			System.out.println("Creation successfull");
			creationSuccessfull=true;
		}else {
			person=null;
		}
		this.holder.addCriminalPerson(person);
		return creationSuccessfull;
	}
	public CriminalPerson getCriminalPerson(String name) {
		return holder.getCriminalPersonByName(name);
	}
	
	public boolean checkCriminalPerson(CriminalPerson person) {
		return holder.checkCriminalPersonExists(person);
	}
	public boolean checkCriminalPersonWithName(String name) {
		return holder.checkCriminalPersonExistsWithName(name);
	}
	public boolean changeCrimeReportWithName(String reportName,String reportLocation,String reportContent) {
		boolean bool=false;
		for(CrimeReport report: this.reports.values()) {
			if(report.getReportName().equalsIgnoreCase(reportName)) {
				int id=report.getID();
				this.reports.get(id).setReportName(reportName);
				this.reports.get(id).setReportLocation(reportLocation);
				this.reports.get(id).setReportContent(reportContent);
				bool=true;
			}
		}
		return bool;
	}
	public CrimeReport createCrimeReport(String reportName, String reportLocation, String reportContent,String type) {
		CrimeReport report;
		if(type.equalsIgnoreCase("report")) {
			report=new MissingPeopleReport(reportName,reportLocation,reportContent);
			this.reports.put(report.getID(), report);
		}else if(type.equalsIgnoreCase("complaint")) {
			report=new ComplaintReport(reportName,reportLocation,reportContent);
			this.reports.put(report.getID(), report);
		}
		else {
			report=null;
		}
		return report;
	}
	public boolean criminalPersonPrompt(CrimeReport report) {
		System.out.println("Welcome to the Crime File Management System");
		Scanner input=new Scanner(System.in);
		input.nextLine();
		boolean personProcess=false;
		String loginAsk="Enter the person details, to exit anytime type exit or -1 \n"
				+"Enter persons name,phone number,age and type \n"
				+ "Please enter the crime type, missing for missing people and wanted for wanted people";
		while(!personProcess) {
			System.out.println(loginAsk);
			String type=input.nextLine();
			if(type.equalsIgnoreCase("exit") || type.equalsIgnoreCase("-1")) {
				System.out.println("Exiting");
				break;
			}
			System.out.println("Please enter the persons name");
			String name=input.nextLine();
			if(name.equalsIgnoreCase("exit") || name.equalsIgnoreCase("-1")) {
				System.out.println("Exiting");
				break;
			}else {
				System.out.println("Please enter the persons phone number");
				String phoneNumber=input.nextLine();
				if(phoneNumber.equalsIgnoreCase("exit") || phoneNumber.equalsIgnoreCase("-1")) {
					System.out.println("Exiting");
					break;
				}else {
					System.out.println("Please enter persons age");
					int age=input.nextInt();
					this.createCriminalPerson(name, age, phoneNumber,report, type);
					System.out.println("Creation successfull");
					personProcess=true;
				}
			}
		}
		input.close();
		return personProcess;
	}
	public CrimeReport crimeReportPrompt() {
		System.out.println("Please create the report ");
		CrimeReport reportReturn=null;
		Scanner input=new Scanner(System.in);
		boolean reportProcess=false;
		String reportAsk="Please enter the report details to exit anytime press exit or -1 \n"
				+ "Enter the report name, report location, report content and the report type \n"
				+"Please enter the report type missing for missing people and wanted for wanted people";
		while(!reportProcess) {
			System.out.println(reportAsk);
			String type=input.nextLine();
			if(type.equalsIgnoreCase("exit") || type.equalsIgnoreCase("-1")) {
				System.out.println("Exiting");
				break;
			}
			System.out.println("Please enter the report name");
			String name=input.nextLine();
			if(name.equalsIgnoreCase("exit") || name.equalsIgnoreCase("-1")) {
				System.out.println("Exiting");
				break;
			}else {
				System.out.println("Please enter the report location");
				String reportLocation=input.nextLine();
				if(reportLocation.equalsIgnoreCase("exit") || reportLocation.equalsIgnoreCase("-1")) {
					System.out.println("Exiting");
					break;
				}else {
					System.out.println("Please enter report content");
					String reportContent=input.nextLine();
					reportReturn=this.createCrimeReport(name, reportLocation, reportContent, type);
					System.out.println("Creation successfull");
					reportProcess=true;
				}
			}
		}
		input.close();
		return reportReturn;
	}
	public void personWithReportPrompt() {
		System.out.println("Welcome to the crime file management system");
		CrimeReport newReport=this.crimeReportPrompt();
		this.criminalPersonPrompt(newReport);
	}
	PersonHolder holder;
	private static personFacade person_facade=null;
	protected HashMap<Integer,CrimeReport> reports;
}



