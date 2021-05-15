package mainPackage;
import java.util.ArrayList;
import java.util.Scanner;

class UpdateSystem{
    //(temporary) this refers to the missingPeople table in the database
    private static ArrayList<MissingPerson> missingPeople= new ArrayList<>();

    public static void updateMissingPerson(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the details of missing person");
        System.out.println("Name :");
        String name = input.next();
        System.out.println("Surname");
        String surname = input.next();
        System.out.println("Phone number: ");
        String phone= input.next();
        System.out.println("Details about missing person");
        String details = input.next();

        MissingPerson missingPerson= new MissingPerson();
        missingPerson.setName("ali");
        missingPerson.setSurname("veli");
        missingPerson.setDetails("lost");
        missingPerson.setPhoneNumber("05555555555");
        missingPeople.add(missingPerson);

        boolean found= false;
        for(int i=0;i<missingPeople.size();i++){
            if(missingPeople.get(i).getPhoneNumber()==phone){
                found =true;//exists
                break;
            }
        }
        MissingPerson missingPerson1= new MissingPerson();
        missingPerson1.setName(name);
        missingPerson1.setSurname(surname);
        missingPerson1.setDetails(details);
        missingPerson1.setPhoneNumber(phone);
        if(found==false){
            missingPeople.add(missingPerson1);
            System.out.println("Missing person has been added to the system");
            missingPerson1.displayPersonInfo();
        }
        else{
            System.out.println("Missing person with phone number: "+missingPerson1.getPhoneNumber()+" already exists in the system");

        }
    }


}


