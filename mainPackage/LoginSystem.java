//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.time.ZonedDateTime;
import java.util.ArrayList;
//this is a singleton class that creates id for users
class idCreator{
	private ArrayList<Integer> idKeeper;
	private static idCreator single_instance=null;
	
	private idCreator() {
		idKeeper=new ArrayList<Integer>();
	}
	public static idCreator idCreator() {
		if(single_instance==null) {
			single_instance=new idCreator();
		}
		return single_instance;
	}
	public int createID() {
		if(this.idKeeper.isEmpty()) {
			this.idKeeper.add(0);
			return 0;
		}else {
			int id=this.idKeeper.get(idKeeper.size()-1);
			this.idKeeper.add(id+1);
			return id+1;
		}
	}
}
//This is the basic interface for login system
interface User{

	public void setUserName(String userName);
	public void setUserPassword(String password);
	public String getUserName();
	public boolean checkUserData(String name,String password);
	public String getDate();
	public String getLastLogin();
	public int getID();
}

//This is the abstract class that will not be needed to written again, each subclass will just extend this class
abstract class BasicUser implements User {
	
	public BasicUser(String userName,String password) {
		encryptObject=new Encrypter();
		this.setUserName(userName);
		this.setUserPassword(password);
		this.createdAt=this.createTimeNow();
		this.id=idCreator.idCreator().createID();
	}
	public int getID() {
		return this.id;
	}
	public void setUserName(String userName) {
		this.userName=userName;
	}
	public void setUserPassword(String password) {
		if(encryptObject==null) {
			encryptObject=new Encrypter();
		}
		this.password=encryptObject.shaWithMD5Encrypter(password);
	}
	public String getUserName() {
		return this.userName;
	}
	//checks the values of the user
	public boolean checkUserData(String userName,String password) {
		if(encryptObject==null) {
			encryptObject=new Encrypter();
		}
		if(this.userName.equals(userName) && this.password.equals(encryptObject.shaWithMD5Encrypter(password))) {
			this.lastDate=this.createTimeNow();
			return true;			
		}else {
			return false;
		}
	}
	public String getDate() {
		return this.createdAt;
	}
	public String getLastLogin() {
		if(this.lastDate==null) {
			return "User never logged in";
		}else {
			return lastDate;	
		}
	}
	//creates the atomic time right now with zoneddatetime library
	public String createTimeNow() {
		ZonedDateTime timeNow=ZonedDateTime.now();
	    return timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear()+" "+
				timeNow.getHour()+":"+timeNow.getMinute()+":"+timeNow.getSecond()+"."+timeNow.getNano();
	}
	public String toString() {
		return "User Name " + userName+" user id "+id+" last login date "+lastDate+" account created at "+createdAt;
	}
	
	protected String userName;
	protected String password;
	protected String lastDate;
	protected String createdAt;
	protected Encrypter encryptObject=null;
	protected int id;
}

class NormalUser extends BasicUser{
	
	public NormalUser(String userName, String password) {
		super(userName, password);
	}
	
}
