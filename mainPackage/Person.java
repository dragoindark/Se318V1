package mainPackage;
public abstract class Person {
    private String name;
    private String surname;
    private String phoneNumber;

    public void displayPersonInfo(){
        System.out.println("Name: "+name);
        System.out.println("Surname: "+surname);
        System.out.println("phoneNumber: "+phoneNumber);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
