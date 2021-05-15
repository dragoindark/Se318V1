//Faruk Burak Gürel@dragoindark, Nur Ruşen

package mainPackage;

import java.util.Scanner;
//this is the base class where we run our application
//This class is coded by both Faruk Burak and Nur
public class Application {

	public static void main(String[] args) {
		System.out.println("Welcome to the crime file management system");
		boolean loginBool=false;
		userCreationFacade uFacade=userCreationFacade.userCreationFacade();
		personFacade pFacade=personFacade.personFacade();
		Scanner input=new Scanner(System.in);
		boolean systemRunning=true;
		while(systemRunning) {
			while(!loginBool) {
				System.out.println("Inside login system");
				System.out.println("To login please enter 1, to create account enter 2");
				String login=input.nextLine();
				if(login.equalsIgnoreCase("exit")) {
					System.out.println("Exited");
					break;
				}
				if(login.equalsIgnoreCase("1")) {
					System.out.println("Entering user");
					System.out.println("Please enter username");
					String name=input.nextLine();
					System.out.println("Please enter password");
					String password=input.nextLine();
					loginBool=uFacade.validateUserWithName(name, password);
					if(!loginBool) {
						System.out.println("User not found");
					}else {
						System.out.println("Welcome");
					}
				}else if(login.equalsIgnoreCase("2")) {
					System.out.println("Creating account");
					System.out.println("Please enter name");
					String name=input.nextLine();
					System.out.println("Please enter password");
					String password=input.nextLine();
					uFacade.createUser(name, password, "normal");
					loginBool=true;				
				}
			}
			boolean crimeReportBool=false;
			if(!crimeReportBool && loginBool) {
				System.out.println("Do you want to file report or change/create user \ntype report for report system\ntype login for login system");
				String action=input.nextLine();
				if(action.equalsIgnoreCase("login")) {
					loginBool=false;
				}else if(action.equalsIgnoreCase("report")) {
					if(loginBool) {
						crimeReportBool=false;
					}
				}
			}
			while(!crimeReportBool && loginBool) {
				System.out.println("Please enter 1 to create report or complaint with type,2 to edit report or complaint with type");
				System.out.println("Type exit to return to the main system");
				String report=input.nextLine();
				if(report.equalsIgnoreCase("1")) {
					System.out.println("Please enter report name");
					String reportName=input.nextLine();
					System.out.println("Please enter report location");
					String reportLocation=input.nextLine();
					System.out.println("Please enter report content");
					String reportContent=input.nextLine();
					System.out.println("Please enter report type, type report for reports and complaints for complaint");
					String type=input.nextLine();
					if(type.equalsIgnoreCase("report")) {
						pFacade.createCrimeReport(reportName, reportLocation, reportContent,"report");
						System.out.println("Report created successfully");
					}else if(type.equalsIgnoreCase("compalint")) {
						pFacade.createCrimeReport(reportName, reportLocation, reportContent,"complaint");			
						System.out.println("Report created successfully");
					}else {
						System.out.println("Illegal type try again");
					}
					
				}else if(report.equalsIgnoreCase("2")) {
					System.out.println("Please enter report name to change");
					String reportName=input.nextLine();
					System.out.println("Please enter report location");
					String reportLocation=input.nextLine();
					System.out.println("Please enter report content");
					String reportContent=input.nextLine();
					crimeReportBool=pFacade.changeCrimeReportWithName(reportName, reportLocation, reportContent);
					if(crimeReportBool) {
						System.out.println("Changed successfully");
					}else {
						System.out.println("Not changed");
					}
				}else if(report.equalsIgnoreCase("exit")) {
					System.out.println("Returning to the program base");
					crimeReportBool=true;
					break;
				}
			}
			System.out.println("Please enter course of action in the system \n enter login for login panel \n enter report for report panel"
					+ "\nPlease enter missing to report details and information about the missing people");
			String courseOfAction=input.nextLine();
			if(courseOfAction.equalsIgnoreCase("login")) {
				loginBool=false;
				System.out.println(loginBool);
			}else if(courseOfAction.equalsIgnoreCase("report")) {
				if(loginBool) {
					crimeReportBool=false;
				}else {
					System.out.println("You should login first");
				}
			}else if(courseOfAction.equalsIgnoreCase("missing")){
				//pFacade.createCriminalPerson(name, age, phoneNumber, report, type)
				System.out.println("Please enter the information person information");	
				System.out.println("Please enter persons name");
				String name=input.nextLine();
				System.out.println("Please enter persons phoneNumber");
				String phoneNumber=input.nextLine();
				System.out.println("Please enter information regarding the report of person");
				
				System.out.println("Please enter report name");
				String reportName=input.nextLine();
				System.out.println("Please enter report location");
				String reportLocation=input.nextLine();
				System.out.println("Please enter report content");
				String reportContent=input.nextLine();
				System.out.println("Please enter the age of person");
				int age=input.nextInt();
				CrimeReport report=new MissingPeopleReport(reportName,reportLocation,reportContent);
				pFacade.createCriminalPerson(reportName, age, phoneNumber, report, "missing");
			}
		}	
	}

}
