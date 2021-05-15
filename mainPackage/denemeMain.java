//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

//this is my class for trials before writing them inside the application

public class denemeMain {

	public static void main(String[] args) {
		String password="burak";
		String password2="burak";
		Encrypter encryptObject=new Encrypter();
		String outcome=encryptObject.shaWithMD5Encrypter(password);
		System.out.println(outcome);
		LocalDate dateNow=LocalDate.now();
		System.out.println(dateNow);
		Calendar now=Calendar.getInstance();
		System.out.println(now.get(Calendar.HOUR_OF_DAY));
		System.out.println(now.get(Calendar.MINUTE));
		System.out.println(now.get(Calendar.MILLISECOND));
		ZonedDateTime timeNow=ZonedDateTime.now();
		ZoneId zoneId=timeNow.getZone();
		System.out.println(zoneId);
		System.out.println(ZonedDateTime.now());
		System.out.println(timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear());
		String currentDate=timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear();
		System.out.println(currentDate);
		String currentTime=timeNow.getHour()+":"+timeNow.getMinute()+":"+timeNow.getSecond()+"."+timeNow.getNano();
		System.out.println(currentTime);
		System.out.println("After creating the new user");
		BasicUser newUser=new NormalUser("Burak","1234");
		System.out.println(newUser.getDate());
		System.out.println(newUser.getUserName());
		System.out.println(newUser.getLastLogin());
		System.out.println(newUser.checkUserData("Burak","1234"));
		User newUser2=new NormalUser("Ahmet","1234");
		System.out.println(password.charAt(0));
		
		Scanner input=new Scanner(System.in);
		String deneme=input.nextLine();
		System.out.println(deneme);
		personFacade pFacade=personFacade.personFacade();
		pFacade.criminalPersonPrompt(new MissingPeopleReport("burak","burak","burak"));
		

	}

}
