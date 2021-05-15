//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface UserHolder{
	public boolean addUser(User user);
	public boolean deleteUser(User user);
	public User getUser(int id);
	public User getUserByName(String name);
	public HashMap<Integer,User> getUserMap();
	public void printAllUsers();
	public boolean checkUserExists(User user);
	public boolean checkUserExistsWithName(String name);
}

class UserObject implements UserHolder{
	private UserObject() {userHolder=new HashMap<Integer,User>();}
	public static UserObject UserObject() {
		if(users_instance==null) {
			users_instance=new UserObject();
		}
		return users_instance;
	}
	public boolean addUser(User user) {
		if(user==null) {
			return false;
		}else {
			try {
				this.userHolder.put(user.getID(),user);
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
	}
	public boolean deleteUser(User user) {
		if(userHolder.containsKey(user.getID())) {
			try {
				userHolder.remove(user.getID());
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
		
	}
	public User getUser(int id) {
		if(userHolder.containsKey(id)) {
			return this.userHolder.get(id);
		}else {
			System.out.println("User not found");
			return null;
		}
	}
	public HashMap<Integer, User> getUserMap() {
		return this.userHolder;
	}
	public void printAllUsers() {
		for(Map.Entry<Integer,User> map:this.userHolder.entrySet()) {
			User user=map.getValue();
			user.toString();
		}
	}
	public User getUserByName(String name) {
		User userToReturn=null;
		for(Map.Entry<Integer,User> map:this.userHolder.entrySet()) {
			User user=map.getValue();
			if(user.getUserName().equalsIgnoreCase(name)) {
				userToReturn=user;
			}
		}
		if(userToReturn==null) {
			System.out.println("User not found");
		}
		return userToReturn;
	}
	public boolean checkUserExistsWithName(String name) {
		boolean bool=false;
		for(Map.Entry<Integer,User> map:this.userHolder.entrySet()) {
			User user=map.getValue();
			if(user.getUserName().equalsIgnoreCase(name)) {
				bool=true;
			}
		}
		return bool;
	}
	public boolean checkUserExists(User user) {
		return this.userHolder.containsKey(user.getID());
	}
	
	private static UserObject users_instance=null;
	protected HashMap<Integer,User> userHolder;
	
	
	
}


class userCreationFacade{
	public static userCreationFacade userCreationFacade() {
		if(user_facade_instance==null) {
			user_facade_instance=new userCreationFacade();
		}
		return user_facade_instance;
	}
	private userCreationFacade() {
		holder=UserObject.UserObject();
	}
	public void createUser(String userName,String password,String type) {
		User user;
		if(type.equalsIgnoreCase("normal")) {
			user=new NormalUser(userName,password);
			System.out.println("User created successfully");
		}else {
			user=null;
		}
		this.holder.addUser(user);
	}
	public User getUser(String name) {
		return holder.getUserByName(name);
	}
	
	public boolean checkUser(User user) {
		return holder.checkUserExists(user);
	}
	public boolean checkUserWithName(String name) {
		return holder.checkUserExistsWithName(name);
	}
	
	public boolean validateUserWithName(String name,String password) {
		try {
			return this.getUser(name).checkUserData(name, password);			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean promptUser() {
		Scanner input=new Scanner(System.in);
		boolean loginSuccessfull=false;
		while(!loginSuccessfull) {
			System.out.println("If you want to exit anytime please type exit or -1");
			System.out.println("Please enter user name");
			String name=input.nextLine();
			if(this.checkUserWithName(name)){
				System.out.println("User found");
				System.out.println("Please enter your password");
				String password=input.nextLine();
				if(this.validateUserWithName(name, password)) {
					System.out.println("Login successfull \n Welcome to the Crime File Management System");
					loginSuccessfull=true;
				}
			}else if(name.equalsIgnoreCase("-1 ") || name.equalsIgnoreCase("exit")) {
				System.out.println("Exiting user login");
				break;
			}else {
				System.out.println("User not found please login with valid name");
			}	
		}
		input.close();
		return loginSuccessfull;
	}
	//after user creation continue with that user maybe ?
	public boolean createUserPrompt() {
		Scanner input=new Scanner(System.in);
		boolean userCreated=false;
		while(!userCreated) {
			System.out.println("For exiting please type exit or -1");
			System.out.println("Please select a username");
			String name=input.nextLine();
			if(this.checkUserWithName(name)) {
				System.out.println("User name is already in use please use another name");
			}else if(name.equalsIgnoreCase("exit") || name.equalsIgnoreCase("-1")) {
				System.out.println("Exiting user creation");
			}else {
				System.out.println("User name is valid \n Please enter password for your account");
				String password=input.nextLine();
				this.createUser(name, password, "normal");
				System.out.printf("Account created with name %s ",name);
				userCreated=true;
			}
		}
		input.close();
		return userCreated;
	}
	public boolean loginSystemPropmpt() {
		System.out.println("Welcome to the Crime File Management System");
		Scanner input=new Scanner(System.in);
		boolean loginProcess=false;
		String loginAsk="For user login enter login or 1 \n For user creation enter create or 2 \n To exit the system type exit or -1";
		while(!loginProcess) {
			System.out.println(loginAsk);
			String choice=input.nextLine();
			if(choice.equalsIgnoreCase("login") || choice.equalsIgnoreCase("1")) {
				boolean loginBool=this.promptUser();
				if(loginBool) {
					System.out.println("Please login now");
					loginProcess=true;
				}
			}else if(choice.equalsIgnoreCase("create") || choice.equalsIgnoreCase("2")) {
				boolean creationBool=this.createUserPrompt();
				if(creationBool) {
					System.out.println("Creation Successfull");
					loginProcess=true;
				}
			}else if(choice.equalsIgnoreCase("exit")) {
				System.out.println("Exiting the program");
				System.exit(0);
			}else {
				System.out.println("Wrong input try again");
			}
		}
		input.close();
		return loginProcess;
	}
	UserHolder holder;
	private static userCreationFacade user_facade_instance=null;
}


