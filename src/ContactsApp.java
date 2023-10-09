import java.io.Console;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import util.Person;

/**
* The class app contains methods relating to storing, accessing and
* editing contact information 
* @author Anni Peura
*
*/
public class ContactsApp { 

    private static final String contacts_file_path = "./util/contact_data.txt";
    private static final int MENU_QUIT_OPTION = 5;
    private ArrayList<Person> contactDetails = new ArrayList<Person>();
    Console c = System.console(); 

    /**
    * Main method runs methods to synch up information, present main menu
    * and take user's initial feed to use the application. if no text file
    * exists one is created
    *
    * @param args default parameter for main method, not used
    */
    public static void main (String[] args) {

        Path filePath = Paths.get(contacts_file_path);
        if (!Files.exists(filePath)) {
            try {    
                Files.createFile(filePath);        
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        ContactsApp app = new ContactsApp(); 
        app.listSynch();
        app.printMainMenu();
    }
    /**
    * prints out main menu choices user has to use the application
    */
    public void printMainMenu() {

        System.out.println();
        System.out.println("< CONTACTS APP >");
        System.out.println();
        System.out.println("    1 - show contacts");
        System.out.println("    2 - add a new contact");
        System.out.println("    3 - delete a contact");
        System.out.println("    4 - edit a contact");
        System.out.println("    5 - quit");
        System.out.println();

        int menuChoice = userInputMenu();
        if (menuChoice != MENU_QUIT_OPTION) {
            userChoice(menuChoice);
        } else {
            saveToContactBook();
            System.out.println("Bye!");
        }
    }
    /**
    * prints out choices to quit or return to main menu
    */
    public void printContactBook() {

        System.out.println("\n" + "Your Contacts:");

        for (Person person : contactDetails) {
            System.out.println("ID: " + person.getId());
            System.out.println("Name: " + person.getFirstName() 
            + " " + person.getLastName());
            System.out.println("Phone Number: " + person.getPhoneNumber());
            System.out.println("Email address: " + person.getEmail());
            System.out.println("Address: " + person.getAddress());
            System.out.println();
        }
    }

    /**
    * receives and validates user's input to use mainMenu/contactBookMenu
    * choices.
    *
    * @return int returns userChoice 
    */
    public int userInputMenu() {

        int menuChoice = 0;
        System.out.println("    Please input a number to proceed.");

        try {
            menuChoice = Integer.parseInt(c.readLine());
        } catch (NumberFormatException e) {      
            System.out.println("\n" + "Error: Invalid input, 
            answer must be a number.");
        }

        if (menuChoice < 0 || menuChoice > 5) {
            System.out.println("Input a number within the given options.");
            return userInputMenu();
        }
        return menuChoice;
    }
    /**
    * switch-case operating via int, runs method based on received feed
    *
    * @param command is int received from userInputMenu (0-5)
    */

    public void userChoice(int command) {
        switch (command) {
            case 1:
                printContactBook();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                deleteContact(choosingContact());
                break;
            case 4:
                editContact(choosingContact());
                break;
            default:
                System.out.println("Invalid choice.");
        }
        printMainMenu();
    }
    /**
    * method used for choosing a contact, user inputs id and method seeks it
    * from existing data
    *
    * @return returns -5 if contact's not found otherwise returns target's index
    */
    public int choosingContact() {

        System.out.println("Please type your target id.");
        String targetId = c.readLine();
        System.out.println();
        
        for(int i = 0; i<contactDetails.size(); i++) {
            String searchedId = contactDetails.get(i).getId();
            if(searchedId.equals(targetId)) {
                System.out.print("contact chosen: ");
                return i;
            } 
        }
        return -5;
    }    
    /**
    * method used for deleting a contact, if no match prints out no target found
    *
    * @param index is int used to identify chosen contact
    */
    public void deleteContact(int index) {

        if (index == -5) {
            System.out.println("no matching target found!"); 
        } else {
            System.out.println(contactDetails.get(index).getFirstName() + " " 
                + contactDetails.get(index).getLastName() + "\n" 
                + "Are you sure you want to delete " 
                + contactDetails.get(index).getFirstName() + "?");
            
            System.out.println("Y/N");
            String choice = (c.readLine().toUpperCase());

            if (choice.equals("Y")) {
                Person deletedPerson = contactDetails.remove(index);
                System.out.println(deletedPerson.getFirstName() + " " + 
                deletedPerson.getLastName() + " deleted!"); 
                saveToContactBook();
            } else {
                System.out.println("Nothing deleted.");
            }
        }
    }
    /**
    * method used for editing a contact, if no match prints out no target found
    *
    * @param index is int used to identify chosen contact
    */
    public void editContact(int index) {
        if (index == -5) {
            System.out.println("No matching target found!");
        } else {
            Person personToEdit = contactDetails.get(index);

            System.out.println(" " + personToEdit.getFirstName() 
            + " " + personToEdit.getLastName());
            System.out.println("\n" + "Current Details:");

            System.out.println("1. First Name: " 
                + personToEdit.getFirstName());
            System.out.println("2. Last Name: " 
                + personToEdit.getLastName());
            System.out.println("3. Phone Number: " 
                + personToEdit.getPhoneNumber());
            System.out.println("4. Email Address: " 
                + personToEdit.getEmail());
            System.out.println("5. Address: " 
                + personToEdit.getAddress());
            System.out.println("    0 - Return" + "\n");

            int choice = userInputMenu();

            switch (choice) {
                case 1:
                    System.out.println("Enter first name:");
                    String newFirstName = c.readLine();
                    personToEdit.setFirstName(newFirstName);
                    break;
                case 2:
                    System.out.println("Enter last name:");
                    String newLastName = c.readLine();
                    personToEdit.setLastName(newLastName);
                    break;
                case 3:
                    System.out.println("Enter phone number:");
                    String newPhoneNumber = c.readLine();
                    personToEdit.setPhoneNumber(newPhoneNumber);
                    break;
                case 4:
                    System.out.println("Enter email address:");
                    String newEmail = c.readLine();
                    personToEdit.setEmail(newEmail);
                    break;
                case 5:
                    System.out.println("Enter address:");
                    String newAddress = c.readLine();
                    personToEdit.setAddress(newAddress);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            } 
            System.out.println("    Saved!");
            saveToContactBook();
        }
    } 
    /**
    * method used for adding a new contact with mandatory details (id, first 
    * name, last name, phone number) and optional details (email and address)
    */
    public void addNewContact() {

        System.out.println("    Add new contact:");

        System.out.println("Id? (mandatory, form of DDMMYYAXXXX)");
        String idNumber = (c.readLine().trim());

        System.out.println("First name? (mandatory)");
        String firstname = (c.readLine().trim());

        System.out.println("Last name? (mandatory)");
        String lastname = (c.readLine().trim());

        System.out.println("Phone number? (mandatory)");
        String phonenumber = (c.readLine().trim());

        System.out.println("Email?");
        String email = (c.readLine().trim());

        System.out.println("Address?");
        String address = (c.readLine().trim());

        Person x = new Person(idNumber, firstname, 
            lastname, phonenumber, email, address);


        contactDetails.add(x);
        saveToContactBook();
        System.out.println("        Contact saved!");
    }
    /**
    * method used for saving array list data to a text file
    */
    public void saveToContactBook() {

        Path filePath = Paths.get(contacts_file_path);
        List<String> lines = new ArrayList<>();

        for (Person person : contactDetails) {
            lines.add(person.toString());
        }
        try {
            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    * method used for synchronizing text file data to arraylist
    */
    public void listSynch() {

        Path filePath = Paths.get(contacts_file_path);
        if (Files.exists(filePath)) {
            try {
                List<String> lines = Files.readAllLines(filePath);
                contactDetails.clear();

                for (String line : lines) {
                    String[] parts = line.split(",,,");
                    Person person = new Person(parts[0], parts[1], 
                        parts[2], parts[3], parts[4], parts[5]);
                    contactDetails.add(person);
                }
            } catch (IOException e) {
            e.printStackTrace();
            } 
        }
    }
}
