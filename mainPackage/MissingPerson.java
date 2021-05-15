package mainPackage;
public class MissingPerson extends Person {
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public void displayPersonInfo(){
        super.displayPersonInfo();
        System.out.println("Details: ");
        System.out.println(details);
    }

}
